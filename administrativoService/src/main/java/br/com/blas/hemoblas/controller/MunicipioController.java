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

import br.com.blas.hemoblas.model.Municipio;
import br.com.blas.hemoblas.service.MunicipioService;

@RestController
@RequestMapping("/secure/municipios")
public class MunicipioController {

	public static final Logger logger = LoggerFactory.getLogger(MunicipioController.class);

	@Autowired
	private MunicipioService municipioService;

	@RequestMapping(value = "/busca/uf={uf}", method = RequestMethod.GET)
	public ResponseEntity<List<Municipio>> buscarPorUf(@PathVariable("uf") String uf) {
		logger.info("Buscando municípios por UF {}", uf);

		List<Municipio> municipios = municipioService.listarPorUF(uf);
		if (municipios == null || municipios.isEmpty()) {
			logger.error("Nenhum município encontrado para a UF {}.", uf);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity.status(HttpStatus.OK).body(municipios);
	}

}
