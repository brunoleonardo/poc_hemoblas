package br.com.blas.hemoblas.controller;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
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
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/secure/agendas")
public class AgendaController {

	public static final Logger logger = LoggerFactory.getLogger(AgendaController.class);

	@Autowired
	private AgendaService agendaService;

	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/busca/estabelecimentoId={estabelecimentoSaudeId}&statusId={statusId}", method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> buscarAgendasPorEstabelecimentoSaudeEStatus(@PathVariable("estabelecimentoSaudeId") Long estabelecimentoSaudeId,
			@PathVariable("statusId") Integer statusId) {
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

	@RequestMapping(value = "/comprovanteAgendamento/numeroProtocolo={numeroProtocolo}", method = RequestMethod.GET)
	public byte[] obterComprovanteAgendamento(@PathVariable("numeroProtocolo") String numeroProtocolo, HttpServletResponse response) {
		logger.info("Obter Comprovante de Agendamento. Buscando Agenda de Número de Protocolo {}", numeroProtocolo);

		Agenda agenda = agendaService.buscarPorNumeroProtocolo(numeroProtocolo);

		response.setHeader("Content-Disposition", "inline; filename=file.pdf");
		response.setContentType("application/pdf");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("PROTOCOLO_NUMERO", agenda.getNumeroProtocolo());
		params.put("AGENDAMENTO_DATA_HORA", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(agenda.getDataHora()));
		params.put("DOADOR_NOME", agenda.getDoador().getNome().toUpperCase());
		params.put("DOADOR_CPF", agenda.getDoador().getCpf());
		params.put("DOADOR_RG", agenda.getDoador().getRg());
		params.put("HEMOCENTRO_NOME", "HOSPITAL HEMOMINAS");
		params.put("HEMOCENTRO_ENDERECO", "RUA A, CENTRO, BETIM, MG");

		try {
			Resource resource = context.getResource("file:/home/hemoblas/reports/comprovanteAgendamento.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(resource.getInputStream(), params, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		} catch (Exception e) {
			logger.error("Erro ao gerar Comprovante de Agendamento.");
			e.printStackTrace();
		}

		return baos.toByteArray();
	}

}
