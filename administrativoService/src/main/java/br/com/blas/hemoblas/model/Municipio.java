package br.com.blas.hemoblas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Municipio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@NotNull(message = "{municipio.nome.obrigatorio}")
	private String nome;

	@NotNull(message = "{municipio.uf.obrigatorio}")
	private String uf;

	@NotNull(message = "{municipio.estado.obrigatorio}")
	private String estado;

	public Municipio() {
		super();
	}

	public Municipio(Integer id, String nome, String uf, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.uf = uf;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Municipio other = (Municipio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}
