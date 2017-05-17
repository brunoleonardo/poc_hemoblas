package br.com.blas.hemoblas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blas.hemoblas.model.EstabelecimentoSaude;

@Repository
public interface EstabelecimentoSaudeRepository extends JpaRepository<EstabelecimentoSaude, Long> {

	@Query("SELECT e FROM EstabelecimentoSaude e WHERE e.municipio.id = :municipioId AND e.tipoUnidade = :tipoUnidade")
	public List<EstabelecimentoSaude> findByMunicipioAndTipoUnidade(@Param("municipioId") Integer municipioId,
			@Param("tipoUnidade") Integer tipoUnidade);

}
