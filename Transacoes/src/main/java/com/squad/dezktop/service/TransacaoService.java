package com.squad.dezktop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

	public TransacaoModel deposito(TransacaoModel transacao) throws Exception {
		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaDestino()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}
		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0 || transacao.getValor().signum() < 0) {
			throw new Exception("Informe um valor maior que zero.");
		}

		TransacaoModel deposito = new TransacaoModel();
		deposito.TransacaoModelDeposito(5, transacao.getValor(), 2, conta.getNumeroConta());
		transacaoRepository.save(deposito);
		workerFeignClient.credita(conta.getNumeroConta(), transacao.getValor().toString());
		return deposito;
	}

	public TransacaoModel saque(TransacaoModel transacao) throws Exception {
		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}
		// Saldo menor do que o valor do saque, lançar mensagem de saldo insuficiente
		if (conta.getSaldo().compareTo(transacao.getValor()) == -1) {
			throw new Exception("Saldo insuficiente.");
		}
		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0 || transacao.getValor().signum() < 0) {
			throw new Exception("Informe um valor maior que zero.");
		}

		TransacaoModel saque = new TransacaoModel();
		saque.TransacaoModelSaque(4, transacao.getValor(), 1, conta.getNumeroConta());
		transacaoRepository.save(saque);
		workerFeignClient.debita(conta.getNumeroConta(), transacao.getValor().toString());
		return saque;
	}

	public List<TransacaoModel> transferencia(TransacaoModel transacao) throws Exception {
		ContaModel contaOrigem;
		ContaModel contaDestino;
		try {
			contaOrigem = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
			contaDestino = workerFeignClient.getByNumero(transacao.getContaDestino()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}

		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0 || transacao.getValor().signum() < 0) {
			throw new Exception("Informe um valor maior que zero.");
		}
		// Saldo menor do que o valor do saque, lançar mensagem de saldo insuficiente
		if (contaOrigem.getSaldo().compareTo(transacao.getValor()) == -1 && transacao.getAgendamento() == null) {
			throw new Exception("Saldo insuficiente.");
		}

		TransacaoModel debito = new TransacaoModel(transacao.getTipo().getCod(), transacao.getValor(), 1,
				contaOrigem.getNumeroConta(), contaDestino.getNumeroConta());

		TransacaoModel credito = new TransacaoModel(transacao.getTipo().getCod(), transacao.getValor(), 2,
				contaOrigem.getNumeroConta(), contaDestino.getNumeroConta());

		List<TransacaoModel> transacoes = new ArrayList<>();

		if (transacao.getAgendamento() != null) {
	
			debito.setAgendamento(transacao.getAgendamento());
			debito.getAgendamento().setTransacao(debito);

			transacoes.add(debito);			
			transacaoRepository.saveAll(transacoes);
		}
		
		
		else {

		transacoes.add(debito);
		transacoes.add(credito);

		transacaoRepository.saveAll(transacoes);

		// Try e so realmente faz caso esteja tudo certo
		
			workerFeignClient.debita(contaOrigem.getNumeroConta(), transacao.getValor().toString());
			workerFeignClient.credita(contaDestino.getNumeroConta(), transacao.getValor().toString());

		}
		return transacoes;

	}

	public TransacaoModel transferenciaExterna(TransacaoModel transacao) throws Exception {
		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}

		// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0 || transacao.getValor().signum() < 0) {
			throw new Exception("Informe um valor maior que zero.");
		}
		// Saldo menor do que o valor do saque, lançar mensagem de saldo insuficiente
		if (conta.getSaldo().compareTo(transacao.getValor()) == -1) {
			throw new Exception("Saldo insuficiente.");
		}

		TransacaoModel transferencia = new TransacaoModel(transacao.getTipo().getCod(), transacao.getValor(), 1,
				conta.getNumeroConta(), transacao.getContaExterna());

		transferencia.getContaExterna().setTransacao(transferencia);
		transacaoRepository.save(transferencia);

		workerFeignClient.debita(conta.getNumeroConta(), transacao.getValor().toString());

		return transferencia;

	}

}