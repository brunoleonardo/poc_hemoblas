package br.com.blas.hemoblas.service;

import br.com.blas.hemoblas.model.Usuario;

public interface UsuarioService {

	Usuario buscarPorCpf(String cpf);

	void salvar(Usuario usuario);

}
