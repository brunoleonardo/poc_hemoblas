package br.com.blas.hemoblas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.blas.hemoblas.model.Usuario;
import br.com.blas.hemoblas.service.UsuarioService;
import br.com.blas.hemoblas.util.ValidationErrorBuilder;

@RestController
@RequestMapping("/public/usuarios")
public class UsuarioController {

	public static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@Validated @RequestBody Usuario usuario, Errors erros, UriComponentsBuilder ucBuilder) {
		logger.info("Salvando Usuário: {}", usuario);

		if (erros.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(erros));
		}

		usuarioService.salvar(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuarios/{cpf}").buildAndExpand(usuario.getCpf()).toUri());

		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}
