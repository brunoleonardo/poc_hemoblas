package br.com.blas.hemoblas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blas.hemoblas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByCpf(String cpf);

	public Usuario findByCpfAndSenha(String cpf, String senha);

}
