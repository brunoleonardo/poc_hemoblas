package br.com.blas.hemoblas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blas.hemoblas.model.EstabelecimentoSaude;
import br.com.blas.hemoblas.service.EstabelecimentoSaudeService;

@RestController
@RequestMapping("/secure/estabelecimentosSaude")
public class EstabelecimentoSaudeController {

	public static final Logger logger = LoggerFactory.getLogger(EstabelecimentoSaudeController.class);

	@Autowired
	private EstabelecimentoSaudeService estabelecimentoSaudeService;

	@RequestMapping(value = "/busca/municipioId={municipioId}&tipoUnidadeId={tipoUnidadeId}", method = RequestMethod.GET)
	public ResponseEntity<List<EstabelecimentoSaude>> buscarEstabelecimentosSaudePorMunicipioETipoUnidade(
			@PathVariable("municipioId") Integer municipioId, @PathVariable("tipoUnidadeId") Integer tipoUnidadeId) {
		logger.info("Buscando Estabelecimentos de Saúde do Município de ID {} e Tipo Unidade {}", municipioId, tipoUnidadeId);

		List<EstabelecimentoSaude> estabelecimentosSaude = estabelecimentoSaudeService
				.listarEstabelecimentosSaudePorMunicipioAndTipoUnidade(municipioId, tipoUnidadeId);
		if (estabelecimentosSaude == null || estabelecimentosSaude.isEmpty()) {
			logger.error("Nenhum Estabelecimentos de Saúde encontrado para o Município de ID {} e Tipo de Unidade {}.", municipioId,
					tipoUnidadeId);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity.status(HttpStatus.OK).body(estabelecimentosSaude);
	}

}
