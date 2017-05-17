package br.com.blas.hemoblas.controller;

import java.util.List;

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

import br.com.blas.hemoblas.model.Agenda;
import br.com.blas.hemoblas.service.AgendaService;
import br.com.blas.hemoblas.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/secure/agendas")
public class AgendaController {

	public static final Logger logger = LoggerFactory.getLogger(AgendaController.class);

	@Autowired
	private AgendaService agendaService;

	@RequestMapping(value = "/busca/estabelecimentoId={estabelecimentoSaudeId}&statusId={statusId}", method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> buscarAgendasPorEstabelecimentoSaudeEStatus(
			@PathVariable("estabelecimentoSaudeId") Long estabelecimentoSaudeId, @PathVariable("statusId") Integer statusId) {
		logger.info("Buscando Agendas por Hemocentro de ID {} com Status {}", estabelecimentoSaudeId, statusId);

		List<Agenda> agendas = agendaService.listarAgendasPorHemocentroComStatus(estabelecimentoSaudeId, statusId);
		if (agendas == null || agendas.isEmpty()) {
			logger.error("Nenhuma Agenda de Doação encontrada para o Hemocentro de ID {} e Status {}.", estabelecimentoSaudeId, statusId);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return ResponseEntity.status(HttpStatus.OK).body(agendas);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> agendarDoacao(@Validated @RequestBody Agenda agenda, Errors erros, UriComponentsBuilder ucBuilder) {
		logger.info("Agendar Doação: {}", agenda);

		if (erros.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(erros));
		}

		agendaService.agendarDoacao(agenda);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/agendas/{id}").buildAndExpand(agenda.getId()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}
