package br.com.blas.hemoblas.service;

import java.util.List;

import br.com.blas.hemoblas.model.Agenda;

public interface AgendaService {

	public List<Agenda> listarAgendasPorHemocentroComStatus(Long estabelecimentoSaudeId, Integer statusId);

	public void agendarDoacao(Agenda agenda);

}
