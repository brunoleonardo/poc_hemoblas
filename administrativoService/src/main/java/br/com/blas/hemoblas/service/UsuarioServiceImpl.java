package br.com.blas.hemoblas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blas.hemoblas.model.Usuario;
import br.com.blas.hemoblas.repository.UsuarioRepository;
import br.com.blas.hemoblas.util.BCryptPasswordEncoder;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario buscarPorCpf(String cpf) {
		return usuarioRepository.findOne(cpf);
	}

	@Override
	public void salvar(Usuario usuario) {
		String senhaCriptografada = BCryptPasswordEncoder.hashPassword(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);

		usuarioRepository.save(usuario);
	}

}
