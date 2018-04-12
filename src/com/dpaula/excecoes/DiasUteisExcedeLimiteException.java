package com.dpaula.excecoes;

import com.dpaula.shared.Utils;

public class DiasUteisExcedeLimiteException extends Exception {

	private static final long serialVersionUID = 1L;
	private int anosRange;

	public DiasUteisExcedeLimiteException(int anosRange) {
		this.anosRange = anosRange;
	}

	@Override
	public String toString() {
		return "A quantidade de anos (" + this.anosRange + ") excede a quantidade estipulada de "
				+ Utils.QTD_ANOS_RANGE;
	}
}
