package br.com.blas.hemoblas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EstabelecimentoSaude implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String nomeFantasia;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;

	private String cnpj;

	private Long codigoCnes;

	private Date dataAtualizacao;

	private Integer tipoUnidade;

	public EstabelecimentoSaude() {
		super();
	}

	public EstabelecimentoSaude(Long id, String nomeFantasia, Municipio municipio, String cnpj, Long codigoCnes, Date dataAtualizacao, Integer tipoUnidade) {
		super();
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.municipio = municipio;
		this.cnpj = cnpj;
		this.codigoCnes = codigoCnes;
		this.dataAtualizacao = dataAtualizacao;
		this.tipoUnidade = tipoUnidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Long getCodigoCnes() {
		return codigoCnes;
	}

	public void setCodigoCnes(Long codigoCnes) {
		this.codigoCnes = codigoCnes;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Integer getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(Integer tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
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
		EstabelecimentoSaude other = (EstabelecimentoSaude) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nomeFantasia;
	}

}
