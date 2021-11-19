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

	public TransacaoModel deposito(TransacaoModel transacao) throws Exception 
	{
		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaDestino()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}
												//Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0) {
			throw new Exception("Informe um valor maior que zero.");
		}
		else if(transacao.getValor().signum() < 0) {
			throw new Exception("Não é possível realizar um deposito com um valor negativo.");
		}

		TransacaoModel deposito = new TransacaoModel();
		deposito.setTipo(5);
		deposito.setCategoria(2);
		deposito.setValor(transacao.getValor());
		deposito.setContaDestino(conta.getNumeroConta());
		transacaoRepository.save(deposito);
		workerFeignClient.credita(conta.getNumeroConta(), transacao.getValor().toString());
		return deposito;
	}

	public TransacaoModel saque(TransacaoModel transacao) throws Exception 
	{
		ContaModel conta;
		try {
			conta = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}
				//Saldo menor do que o valor do saque, lançar mensagem de saldo insuficiente
		if (conta.getSaldo().compareTo(transacao.getValor()) == -1) {
			throw new Exception("Saldo insuficiente.");
		}
			// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0) {
			throw new Exception("Informe um valor maior que zero.");
		} else if (transacao.getValor().signum() < 0) {
			throw new Exception("Não é possível realizar um saque com um valor negativo.");
		}

		TransacaoModel saque = new TransacaoModel();
		saque.setTipo(4);
		saque.setCategoria(1);
		saque.setValor(transacao.getValor());
		saque.setContaOrigem(conta.getNumeroConta());
		transacaoRepository.save(saque);
		workerFeignClient.debita(conta.getNumeroConta(), transacao.getValor().toString());
		return saque;
	}

	public List<TransacaoModel> transferencia(TransacaoModel transacao) throws Exception 
	{
		ContaModel contaOrigem;
		ContaModel contaDestino;
		try {
			contaOrigem = workerFeignClient.getByNumero(transacao.getContaOrigem()).getBody();
			contaDestino = workerFeignClient.getByNumero(transacao.getContaDestino()).getBody();
		} catch (Exception e) {
			throw new Exception("Conta não identificada.");
		}
				//Saldo menor do que o valor do saque, lançar mensagem de saldo insuficiente
		if (contaOrigem.getSaldo().compareTo(transacao.getValor()) == -1) {
			throw new Exception("Saldo insuficiente.");
		}
		
				// Se o valor de entrada for menor ou igual a zero, lança a exceção.
		if (transacao.getValor().signum() == 0) {
			throw new Exception("Informe um valor maior que zero.");
		} else if (transacao.getValor().signum() < 0) {
			throw new Exception("Não é possível realizar uma transferência com um valor negativo.");
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