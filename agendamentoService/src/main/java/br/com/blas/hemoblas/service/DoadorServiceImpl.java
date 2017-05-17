package br.com.blas.hemoblas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blas.hemoblas.model.Doador;
import br.com.blas.hemoblas.repository.DoadorRepository;

@Service
public class DoadorServiceImpl implements DoadorService {

	@Autowired
	private DoadorRepository doadorRepository;

	@Override
	public Doador buscarPorCpf(String cpf) {
		return doadorRepository.findOne(cpf);
	}

	@Override
	public void salvar(Doador doador) {
		doadorRepository.save(doador);
	}

	@Override
	public List<Doador> listarTodos() {
		return doadorRepository.findAll();
	}

	@Override
	public boolean doadorJaExistente(Doador doador) {
		return false;
	}

}
