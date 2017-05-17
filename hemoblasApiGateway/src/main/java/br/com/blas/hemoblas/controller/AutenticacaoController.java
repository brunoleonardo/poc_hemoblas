package br.com.blas.hemoblas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blas.hemoblas.dto.AutenticacaoDTO;
import br.com.blas.hemoblas.model.UsuarioJwt;
import br.com.blas.hemoblas.service.JwtService;
import br.com.blas.hemoblas.service.UsuarioService;

@RestController
public class AutenticacaoController {

	public static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtService jwtService;

	@PostMapping(value = "/api/public/autenticarUsuario")
	public ResponseEntity<?> autenticarUsuario(@RequestBody AutenticacaoDTO autenticacao) {
		logger.info("Autenticando Usuário com CPF {} e Senha informada", autenticacao.getCpf());

		boolean usuarioAutenticado = usuarioService.autenticarUsuario(autenticacao);

		if (usuarioAutenticado) {
			UsuarioJwt usuarioJwt = new UsuarioJwt(autenticacao.getCpf(), autenticacao.getSenha());

			return ResponseEntity.ok("{\"token\":\"" + jwtService.getToken(usuarioJwt) + "\"}");

		}

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
