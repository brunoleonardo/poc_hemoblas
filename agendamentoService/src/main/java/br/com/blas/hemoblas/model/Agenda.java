package br.com.blas.hemoblas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "{agenda.numeroProtocolo.obrigatorio}")
	// @Size(min = 32, max = 36, message = "{agenda.numeroProtocolo.tamanho}")
	private String numeroProtocolo;

	@Future(message = "{agenda.dataHora.invalida}")
	@NotNull(message = "{agenda.dataHora.obrigatoria}")
	private Date dataHora;

	@Valid
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "doador_cpf")
	private Doador doador;

	@NotNull
	private Long estabelecimentoSaudeId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status_agenda_id")
	@NotNull(message = "{agenda.status.obrigatorio}")
	private StatusAgenda status;

	public Agenda() {
		super();
	}

	public Agenda(Long id, String numeroProtocolo, Date dataHora, Doador doador, Long estabelecimentoSaudeId, StatusAgenda status) {
		super();
		this.id = id;
		this.numeroProtocolo = numeroProtocolo;
		this.dataHora = dataHora;
		this.doador = doador;
		this.estabelecimentoSaudeId = estabelecimentoSaudeId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
	}

	public Long getEstabelecimentoSaudeId() {
		return estabelecimentoSaudeId;
	}

	public void setEstabelecimentoSaude(Long estabelecimentoSaudeId) {
		this.estabelecimentoSaudeId = estabelecimentoSaudeId;
	}

	public StatusAgenda getStatus() {
		return status;
	}

	public void setStatus(StatusAgenda status) {
		this.status = status;
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
		Agenda other = (Agenda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agenda [id=" + id + ", numeroProtocolo=" + numeroProtocolo + ", dataHora=" + dataHora + ", doador=" + doador
				+ ", estabelecimentoSaudeId=" + estabelecimentoSaudeId + ", status=" + status + "]";
	}

}
