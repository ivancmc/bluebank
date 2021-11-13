package com.squad.dezktop.model;

import java.math.BigDecimal;

public class ContaModel 
{
	private String numeroConta;
	private String agencia;
	private BigDecimal saldo;
	
//	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("topico")
//	private List<Texto> topico;
	
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}