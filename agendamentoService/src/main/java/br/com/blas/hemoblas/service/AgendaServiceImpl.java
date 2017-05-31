package br.com.blas.hemoblas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blas.hemoblas.model.Agenda;
import br.com.blas.hemoblas.model.StatusAgenda;
import br.com.blas.hemoblas.repository.AgendaRepository;
import br.com.blas.hemoblas.repository.StatusAgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private StatusAgendaRepository statusAgendaRepository;

	@Override
	public List<Agenda> listarAgendasPorHemocentroComStatus(Long estabelecimentoSaudeId, Integer statusId) {
		StatusAgenda statusAgendaAberta = statusAgendaRepository.findByDescricaoIgnoreCase("Aberta");
		if (statusAgendaAberta.getId() == statusId) {
			return agendaRepository.findByEstabelecimentoSaudeAndStatusAndDataHoraMaiorQue(estabelecimentoSaudeId, statusId, new Date());
		} else {
			return agendaRepository.findByEstabelecimentoSaudeAndStatus(estabelecimentoSaudeId, statusId);
		}
	}

	@Override
	public void agendarDoacao(Agenda agenda) {
		StatusAgenda statusAgendaAlocada = statusAgendaRepository.findByDescricaoIgnoreCase("Alocada");
		agenda.setStatus(statusAgendaAlocada);

		agendaRepository.save(agenda);
	}

	@Override
	public Agenda buscarPorNumeroProtocolo(String numeroProtocolo) {
		return agendaRepository.findByNumeroProtocolo(numeroProtocolo);
	}

}
