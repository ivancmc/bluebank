package com.squad.dezktop.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.squad.dezktop.repository.CategoriaTransacao;
import com.squad.dezktop.repository.TipoTransacao;

@Entity
@Table(name = "transacao")
public class TransacaoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int tipo;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Sao_Paulo")
	private Date momento;
	private BigDecimal valor;
	private int categoria;
	private String contaOrigem;
	private String contaDestino;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "conta_externa", referencedColumnName = "id")
	private ContaExternaModel contaExterna;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="transacao")
	private AgendamentoModel agendamento;

	public TransacaoModel() {}
	
	public TransacaoModel(int tipo, BigDecimal valor, int categoria, String contaOrigem, String contaDestino) {
		this.tipo = tipo;
		this.valor = valor;
		this.categoria = categoria;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}	
	
	public TransacaoModel(int tipo, BigDecimal valor, int categoria, String contaOrigem, String contaDestino, ContaExternaModel contaExterna) {
		this.tipo = tipo;
		this.valor = valor;
		this.categoria = categoria;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.contaExterna = contaExterna;
	}
		
	//Getters and setters
	
	@PrePersist
	protected void onCreate() {
		momento = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getMomento() {
		return momento;
	}

	public void setMomento(Date momento) {
		this.momento = momento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public TipoTransacao getTipo() {
		return TipoTransacao.toEnum(tipo);
	}

	public CategoriaTransacao getCategoria() {
		return CategoriaTransacao.toEnum(categoria);
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public ContaExternaModel getContaExterna() {
		return contaExterna;
	}

	public void setContaExterna(ContaExternaModel contaExterna) {
		this.contaExterna = contaExterna;
	}

	public AgendamentoModel getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(AgendamentoModel agendamento) {
		this.agendamento = agendamento;
	}
	
	
}
