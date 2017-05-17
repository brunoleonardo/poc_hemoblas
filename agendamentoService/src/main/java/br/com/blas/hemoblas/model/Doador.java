package br.com.blas.hemoblas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Doador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@CPF(message = "{doador.cpf.invalido}")
	@Size(min = 14, max = 14, message = "{doador.cpf.tamanho}")
	private String cpf;

	@NotBlank(message = "{doador.nome.obrigatorio}")
	@Size(min = 6, max = 60, message = "{doador.nome.tamanho}")
	private String nome;

	@Past(message = "doador.dataNascimento.invalida")
	@NotNull(message = "doador.dataNascimento.obrigatoria")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@ManyToOne
	@JoinColumn(name = "genero_id")
	@NotNull(message = "doador.genero.obrigatorio")
	private Genero genero;

	@NotBlank(message = "doador.rg.obrigatorio")
	@Size(min = 10, max = 12, message = "{doador.rg.tamanho}")
	@Column(unique = true)
	// TODO: CRIAR CONSTRAINT PARA VALIDAR UNICIDADE
	private String rg;

	@Pattern(regexp = "\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}", message = "{doador.telefone.formato}")
	@Size(min = 14, max = 15)
	@NotNull
	private String telefone;

	@Email(message = "{doador.email.invalido}")
	@Size(max = 60, message = "{doador.email.tamanho}")
	@NotNull
	private String email;

	@NotBlank(message = "doador.endereco.obrigatorio")
	@Size(min = 16, max = 100, message = "{doador.endereco.tamanho}")
	private String endereco;

	@Size(max = 45, message = "{doador.complemento.tamanho}")
	private String complemento;

	@Pattern(regexp = "[0-9]{2}.[0-9]{3}-[0-9]{3}", message = "{doador.cep.invalido}")
	@Size(min = 10, max = 10)
	@NotNull
	private String cep;

	public Doador() {
		super();
	}

	public Doador(String cpf) {
		super();
		this.cpf = cpf;
	}

	public Doador(String cpf, String nome, Date dataNascimento, Genero genero, String rg, String telefone, String email, String endereco,
			String complemento, String cep) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.rg = rg;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cep = cep;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
		Doador other = (Doador) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Doador [cpf=" + cpf + ", nome=" + nome + "]";
	}

}
