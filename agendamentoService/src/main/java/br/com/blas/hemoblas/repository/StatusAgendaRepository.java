package br.com.blas.hemoblas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blas.hemoblas.model.StatusAgenda;

@Repository
public interface StatusAgendaRepository extends JpaRepository<StatusAgenda, Integer> {

	public StatusAgenda findByDescricaoIgnoreCase(String descricao);

}
