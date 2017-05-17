package br.com.blas.hemoblas.model;

import java.io.Serializable;

public class UsuarioJwt implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cpf;

	private String perfil;

	public UsuarioJwt() {
		super();
	}

	public UsuarioJwt(String cpf, String perfil) {
		super();
		this.cpf = cpf;
		this.perfil = perfil;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioJwt other = (UsuarioJwt) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
