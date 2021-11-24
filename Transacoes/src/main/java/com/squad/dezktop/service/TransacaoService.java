package com.squad.dezktop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.squad.dezktop.WorkerFeignClient;
import com.squad.dezktop.model.ContaModel;
import com.squad.dezktop.model.TransacaoModel;
import com.squad.dezktop.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	@Autowired
	private TransacaoRepository transacaoRepository;

	public List<TransacaoModel> extrato(String numeroConta) {
		return transacaoRepository.getByConta(numeroConta);
	}

	public List<TransacaoModel> extratoPorMes(String numeroConta, String mes, String ano) {
		return transacaoRepository.getByMes(numeroConta, mes, ano);
	}

	public TransacaoModel deposito(TransacaoModel transacao) throws Exception {
		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("Informe um valor maior que zero.");
		}

		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaDestino()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}

		TransacaoModel deposito = new TransacaoModel(5, transacao.getValor(), 2, null, conta.getNumeroConta());
		transacaoRepository.save(deposito);
		workerFeignClient.credita(conta.getNumeroConta(), transacao.getValor().toString());
		return deposito;
	}

	public TransacaoModel saque(TransacaoModel transacao) throws Exception {
		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("Informe um valor maior que zero.");
		}

		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}

		// Saldo menor do que o valor do saque, lançar mensagem de saldo insuficiente
		if (conta.getSaldo().compareTo(transacao.getValor()) < 0) {
			throw new Exception("Saldo insuficiente.");
		}

		TransacaoModel saque = new TransacaoModel(4, transacao.getValor(), 1, conta.getNumeroConta(), null);
		transacaoRepository.save(saque);
		workerFeignClient.debita(conta.getNumeroConta(), transacao.getValor().toString());
		return saque;
	}

	public List<TransacaoModel> transferencia(TransacaoModel transacao) throws Exception {
		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().compareTo(new BigDecimal("0")) < 1) {
			throw new Exception("Informe um valor maior que zero.");
		}

		ContaModel contaOrigem;
		try {
			contaOrigem = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta origem não identificada.");
		}

		// Saldo menor do que o valor do saque, lança mensagem de saldo insuficiente.
		if (contaOrigem.getSaldo().compareTo(transacao.getValor()) < 0 && transacao.getAgendamento() == null) {
			throw new Exception("Saldo insuficiente.");
		}

		TransacaoModel debito = new TransacaoModel(transacao.getTipo().getCod(), transacao.getValor(), 1,
				transacao.getContaOrigem(), transacao.getContaDestino(), transacao.getContaExterna());

		List<TransacaoModel> transacoes = new ArrayList<>();
		transacoes.add(debito);

		if (transacao.getAgendamento() != null) {
			// Transação agendada
			debito.setAgendamento(transacao.getAgendamento());
			debito.getAgendamento().setTransacao(debito);
			
			if(debito.getContaExterna() != null) {
				debito.getContaExterna().setTransacao(debito);
			}
			
			transacaoRepository.saveAll(transacoes);
			return transacoes;
		}

		if (transacao.getContaDestino() == null && transacao.getContaExterna() != null
				&& transacao.getAgendamento() == null) {
			// Transferencia externa imediata
			transacao.getContaExterna().setTransacao(debito);
		} else if (transacao.getContaDestino() != null && transacao.getContaExterna() == null
				&& transacao.getAgendamento() == null) {
			// Transferencia interna imediata
			ContaModel contaDestino;
			try {
				contaDestino = workerFeignClient.getByNumero(transacao.getContaDestino()).getBody();
			} catch (Exception e) {
				throw new Exception("Conta destino não identificada.");
			}

			TransacaoModel credito = new TransacaoModel(transacao.getTipo().getCod(), transacao.getValor(), 2,
					contaOrigem.getNumeroConta(), contaDestino.getNumeroConta());

			transacoes.add(credito);
		}
		transacaoRepository.saveAll(transacoes);

		ResponseEntity<ContaModel> resposta = workerFeignClient.debita(contaOrigem.getNumeroConta(),
				transacao.getValor().toString());
		if (transacao.getContaDestino() != null && resposta.getBody() != null) {
			workerFeignClient.credita(transacao.getContaDestino(), transacao.getValor().toString());
		}
		return transacoes;

	}
}