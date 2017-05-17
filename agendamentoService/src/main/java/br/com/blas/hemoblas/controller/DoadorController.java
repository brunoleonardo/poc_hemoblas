package br.com.blas.hemoblas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.blas.hemoblas.model.Doador;
import br.com.blas.hemoblas.service.DoadorService;
import br.com.blas.hemoblas.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/public/doadores")
public class DoadorController {

	public static final Logger logger = LoggerFactory.getLogger(DoadorController.class);

	@Autowired
	private DoadorService doadorService;

	@RequestMapping(value = "/busca/cpf={cpf}/", method = RequestMethod.GET)
	public ResponseEntity<Doador> buscarPorCpf(@PathVariable("cpf") String cpf) {
		logger.info("Buscando Doador por CPF {}", cpf);

		Doador doador = doadorService.buscarPorCpf(cpf);
		if (doador == null) {
			logger.error("Nenhum Doador encontrado com o CPF {}.", cpf);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity.status(HttpStatus.OK).body(doador);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@Validated @RequestBody Doador doador, Errors erros, UriComponentsBuilder ucBuilder) {
		logger.info("Salvando Doador: {}", doador);

		if (erros.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(erros));
		}

		doadorService.salvar(doador);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/doadores/{cpf}").buildAndExpand(doador.getCpf()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}
