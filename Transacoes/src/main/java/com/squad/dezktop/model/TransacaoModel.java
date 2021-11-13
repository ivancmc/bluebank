package com.squad.dezktop.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.squad.dezktop.repository.CategoriaTransacao;
import com.squad.dezktop.repository.TipoTransacao;

@Entity
public class TransacaoModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int tipo;
	private Calendar momento;
	private BigDecimal valor;
	private int categoria; 
	
	@ManyToOne
	@JsonIgnoreProperties("contaOrigem")
	private ContaModel contaOrigem;
	
	@ManyToOne
	@JsonIgnoreProperties("contaDestino")
	private ContaModel contaDestino;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Calendar getMomento() {
		return momento;
	}

	public void setMomento(Calendar momento) {
		this.momento = momento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ContaModel getContaOrigem() {
		return contaOrigem;
	}
	
	public String geNumerotContaOrigem() {
		return contaOrigem.getNumeroConta();
	}

	public void setContaOrigem(ContaModel contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public ContaModel getContaDestino() {
		return contaDestino;
	}

	public String getNumeroContaDestino() {
		return contaDestino.getNumeroConta();
	}
	
	public void setContaDestino(ContaModel contaDestino) {
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
}
