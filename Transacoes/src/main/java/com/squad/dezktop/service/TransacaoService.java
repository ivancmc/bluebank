package com.squad.dezktop.service;

import java.math.BigDecimal;
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
//				return null;
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
	
	// TODO - Pesquisar a conta baseada no número para poder associar
	// "valor" recebe valor do body e muda o saldo da conta
	// "momento" é criado automaticamente

	// @ManyToMany - a conta tem uma lista de transações e a transação tem um metodo
	// de contas / dicionário
	// Cada transação tem um metodo com duas contas (origem e destino - são as
	// chaves)

	/**
	 * 
	 * @Temporal(TemporalType.TIMESTAMP) private Calendar createdAt;
	 * 
	 * @Column private Date criadoEm;
	 * 
	 * @PrePersist protected void onCreate() { criadoEm = new Date(); }
	 * 
	 * @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	 **/
}
