package br.com.blas.hemoblas.enumerator;

public enum TipoUnidade {

	CENTRO_ATENCAO_HEMOTERAPIA(69);

	private final int codigo;

	private TipoUnidade(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

}
