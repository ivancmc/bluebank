package com.squad.dezktop.repository;

public enum CategoriaTransacao {
	
	DEBITO(1, "Debito"), CREDITO(2, "Credito");
	
	private int cod;
	private String nome;
	
	CategoriaTransacao(int id, String nome){
		this.cod = id;
		this.nome = nome;
	}
	
	public int getCod() {
		return cod;
	}

	public String getNome() {
		return nome;
	}
	
	public static CategoriaTransacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(CategoriaTransacao nomeEnum : CategoriaTransacao.values()) {
			if (cod.equals(nomeEnum.getCod())) {
				return nomeEnum;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}