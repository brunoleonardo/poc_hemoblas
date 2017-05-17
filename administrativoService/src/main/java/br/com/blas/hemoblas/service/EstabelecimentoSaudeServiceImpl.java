package br.com.blas.hemoblas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blas.hemoblas.model.EstabelecimentoSaude;
import br.com.blas.hemoblas.repository.EstabelecimentoSaudeRepository;

@Service
public class EstabelecimentoSaudeServiceImpl implements EstabelecimentoSaudeService {

	@Autowired
	private EstabelecimentoSaudeRepository estabelecimentoSaudeRepository;

	@Override
	public List<EstabelecimentoSaude> listarEstabelecimentosSaudePorMunicipioAndTipoUnidade(Integer municipioId, Integer tipoUnidadeId) {
		return estabelecimentoSaudeRepository.findByMunicipioAndTipoUnidade(municipioId, tipoUnidadeId);
	}

}
