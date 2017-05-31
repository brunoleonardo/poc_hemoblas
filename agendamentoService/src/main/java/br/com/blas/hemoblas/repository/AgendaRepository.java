package br.com.blas.hemoblas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blas.hemoblas.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	@Query("SELECT a FROM Agenda a WHERE a.estabelecimentoSaudeId = :estabelecimentoSaudeId AND a.status.id = :statusId")
	public List<Agenda> findByEstabelecimentoSaudeAndStatus(@Param("estabelecimentoSaudeId") Long estabelecimentoSaudeId, @Param("statusId") Integer statusId);

	@Query("SELECT a FROM Agenda a WHERE a.estabelecimentoSaudeId = :estabelecimentoSaudeId AND a.status.id = :statusId AND a.dataHora > :dataHora")
	public List<Agenda> findByEstabelecimentoSaudeAndStatusAndDataHoraMaiorQue(@Param("estabelecimentoSaudeId") Long estabelecimentoSaudeId,
			@Param("statusId") Integer statusId, @Param("dataHora") Date dataHora);

	public Agenda findByNumeroProtocolo(String numeroProtocolo);

}
