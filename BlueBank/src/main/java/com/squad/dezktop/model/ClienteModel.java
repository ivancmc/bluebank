package com.squad.dezktop.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cliente")
public class ClienteModel{
	
	@Id
	@Column(unique=true)
	@Size(min=11, max=11)
	@Pattern(regexp = "(\\d{11})")
	@ApiModelProperty(example = "73640224060")
	private String cpf;
	
	@ApiModelProperty(example = "Anna")
	@NotEmpty(message = "O nome deve ser preenchido")
	private String nome;
	
	@ApiModelProperty(example = "106975997")
	@NotEmpty(message = "O RG deve ser preenchido")
	private String rg;
	
	@ApiModelProperty(example = "São José dos Campos")
	@NotEmpty(message = "O endereço deve ser preenchido")
	private String endereco;
	
	@ApiModelProperty(example = "anna@gmail.com")
	@NotEmpty(message = "O email deve ser preenchido")
	@Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
	private String email;
	
	@ApiModelProperty(example = "01131836758")
	@NotEmpty(message = "O número de telefone deve ser preenchido")
	private String telefone;
	
	@ApiModelProperty(example = "6745")
	@Size(min = 4, max = 4)
	@Pattern(regexp = "(\\d{4})")
	private String senha;
	
	@ApiModelProperty(example = "12/12/2000")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="cliente")
	private ContaModel conta;
	
	public ContaModel getConta() {
		return conta;
	}
	public void setConta(ContaModel conta) {
		this.conta = conta;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
