package com.squad.dezktop.service;

import com.squad.dezktop.model.TransacaoModel;

public class TransacaoService {

	public TransacaoModel deposito(TransacaoModel transacao) {
		TransacaoModel deposito = new TransacaoModel();
		deposito.setTipo(5);
		deposito.setCategoria(2);
		deposito.setContaDestino(transacao.getContaDestino());
		return null;
	}

	//TODO - Pesquisar a conta baseada no número para poder associar
	//"valor" recebe valor do body e muda o saldo da conta
	//"momento" é criado automaticamente

	//@ManyToMany - a conta tem uma lista de transações e a transação tem um metodo de  contas / dicionário
	//Cada transação tem um metodo com duas contas (origem e destino - são as chaves)

	/**
	 * 
	 * @Temporal(TemporalType.TIMESTAMP)
		private Calendar createdAt;

		@Column
		private Date criadoEm;

		@PrePersist
		protected void onCreate() {
		criadoEm = new Date();
		}
	 * **/
}
