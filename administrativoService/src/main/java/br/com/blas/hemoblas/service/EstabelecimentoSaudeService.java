package br.com.blas.hemoblas.service;

import java.util.List;

import br.com.blas.hemoblas.model.EstabelecimentoSaude;

public interface EstabelecimentoSaudeService {

	public List<EstabelecimentoSaude> listarEstabelecimentosSaudePorMunicipioAndTipoUnidade(Integer municipioId,
			Integer tipoUnidadeId);

}
