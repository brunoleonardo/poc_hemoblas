package br.com.blas.hemoblas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blas.hemoblas.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

	public List<Municipio> findByUf(String uf);

}
