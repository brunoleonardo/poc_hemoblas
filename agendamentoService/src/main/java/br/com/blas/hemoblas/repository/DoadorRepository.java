package br.com.blas.hemoblas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blas.hemoblas.model.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, String> {

}
