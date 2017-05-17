package br.com.blas.hemoblas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blas.hemoblas.model.Municipio;
import br.com.blas.hemoblas.repository.MunicipioRepository;

@Service
public class MunicipioServiceImpl implements MunicipioService {

	@Autowired
	private MunicipioRepository municipioRepository;

	@Override
	public List<Municipio> listarPorUF(String uf) {
		return municipioRepository.findByUf(uf);
	}

}
