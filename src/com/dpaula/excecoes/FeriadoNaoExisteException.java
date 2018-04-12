package com.dpaula.excecoes;

public class FeriadoNaoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensagem;

	public FeriadoNaoExisteException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public FeriadoNaoExisteException() {
		super();
	}

	@Override
	public String toString() {
		return "Feriado não encotrador: " + mensagem;
	}
}
