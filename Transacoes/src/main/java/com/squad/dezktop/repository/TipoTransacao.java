package com.squad.dezktop.repository;

public enum TipoTransacao 
{
	TED(1, "TED"), DOC(2, "DOC"), PIX(3, "Pix"), SAQUE(4, "Saque"),
	DEPOSITO(5, "Deposito"), PAGAMENTO(6, "Pagamento");
	
	private int cod;
	private String nome;
	
	TipoTransacao(int id, String nome) {
		this.cod = id;
		this.nome = nome;
	}

	public int getCod() {
		return cod;
	}

	public String getNome() {
		return nome;
	}
	
	public static TipoTransacao toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(TipoTransacao nomeEnum : TipoTransacao.values()) {
			if (cod.equals(nomeEnum.getCod())) {
				return nomeEnum;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
