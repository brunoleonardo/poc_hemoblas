package br.com.blas.hemoblas.service;

import br.com.blas.hemoblas.dto.AutenticacaoDTO;
import br.com.blas.hemoblas.model.Usuario;

public interface UsuarioService {

	Usuario buscarPorCpf(String cpf);

	boolean autenticarUsuario(AutenticacaoDTO autenticacaoDTO);

}
