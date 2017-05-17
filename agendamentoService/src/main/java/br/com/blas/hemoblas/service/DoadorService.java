package br.com.blas.hemoblas.service;

import java.util.List;

import br.com.blas.hemoblas.model.Doador;

public interface DoadorService {

	Doador buscarPorCpf(String cpf);

	void salvar(Doador doador);

	List<Doador> listarTodos();

	boolean doadorJaExistente(Doador doador);

}
