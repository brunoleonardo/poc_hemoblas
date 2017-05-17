package br.com.blas.hemoblas.service;

import java.util.List;

import br.com.blas.hemoblas.model.Municipio;

public interface MunicipioService {

	public List<Municipio> listarPorUF(String uf);

}
