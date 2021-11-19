package com.squad.dezktop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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

	public TransacaoModel deposito(String numeroConta, String valor) {
		ContaModel conta = workerFeignClient.getByNumero(numeroConta).getBody();
		if (conta != null) {
			TransacaoModel deposito = new TransacaoModel();
			deposito.setTipo(5);
			deposito.setCategoria(2);
			deposito.setValor(new BigDecimal(valor));
			deposito.setContaDestino(conta.getNumeroConta());
			transacaoRepository.save(deposito);
			workerFeignClient.credita(numeroConta, valor);
			return deposito;
		} else {
			return null;
		}
	}

	public TransacaoModel saque(String numeroConta, String valor) throws Exception {
		ContaModel conta = workerFeignClient.getByNumero(numeroConta).getBody();
		if (conta != null) {
			if (conta.getSaldo().compareTo(new BigDecimal(valor)) == -1) {
				// saldo menor do que o valor do saque, lançar msg de saldo insuficiente
				throw new Exception("Saldo insuficiente.");
			}
			TransacaoModel saque = new TransacaoModel();
			saque.setTipo(4);
			saque.setCategoria(1);
			saque.setValor(new BigDecimal(valor));
			saque.setContaOrigem(conta.getNumeroConta());
			transacaoRepository.save(saque);
			workerFeignClient.debita(numeroConta, valor);
			return saque;
		} else {
			return null;
		}
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
		if (contaOrigem.getSaldo().compareTo(transacao.getValor()) == -1) {
			// saldo menor do que o valor da transferencia, lançar msg de saldo insuficiente
			throw new Exception("Saldo insuficiente.");
		}
		TransacaoModel debito = new TransacaoModel();
		debito.setTipo(transacao.getTipo().getCod());
		debito.setCategoria(1);
		debito.setValor(transacao.getValor());
		debito.setContaOrigem(contaOrigem.getNumeroConta());
		debito.setContaDestino(contaDestino.getNumeroConta());

		TransacaoModel credito = new TransacaoModel();
		credito.setTipo(transacao.getTipo().getCod());
		credito.setCategoria(2);
		credito.setValor(transacao.getValor());
		credito.setContaOrigem(contaOrigem.getNumeroConta());
		credito.setContaDestino(contaDestino.getNumeroConta());

		transacaoRepository.save(debito);
		transacaoRepository.save(credito);
		workerFeignClient.debita(contaOrigem.getNumeroConta(), transacao.getValor().toString());
		workerFeignClient.credita(contaDestino.getNumeroConta(), transacao.getValor().toString());
		List<TransacaoModel> transacoes = new ArrayList<>();
		transacoes.add(debito);
		transacoes.add(credito);
		return transacoes;
	}
}
