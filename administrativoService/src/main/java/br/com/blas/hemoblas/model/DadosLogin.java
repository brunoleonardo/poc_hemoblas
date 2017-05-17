package br.com.blas.hemoblas.model;

import java.io.Serializable;

public class DadosLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String senha;

	public DadosLogin() {
		super();
	}

	public DadosLogin(String cpf, String senha) {
		super();
		this.cpf = cpf;
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
