/**
 * 
 */
package com.dpaula.excecoes;

import java.time.LocalDate;

import com.dpaula.shared.Utils;

/**
 * @author dpaula
 *
 */
public class ControleExcecoes {

	/**
	 * @param isDataInicio
	 * @param inicio
	 * @throws IllegalArgumentException
	 */
	public static void validaData(LocalDate data, boolean isDataInicio) throws IllegalArgumentException {

		if (data == null) {
			throw new IllegalArgumentException(
					isDataInicio ? "Data in�cio n�o pode ser nula!" : "Data fim n�o pode ser nula!");
		}

		try {

			LocalDate hoje = LocalDate.now();
			int anosRange = Math.abs(hoje.getYear() - data.getYear());
			if (anosRange > Utils.QTD_ANOS_RANGE) {
				throw new DiasUteisExcedeLimiteException(anosRange);
			}

		} catch (DiasUteisExcedeLimiteException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Faz a valida��o do campo, lan�ando uma {@link IllegalArgumentException} caso
	 * for nulo ou vazio
	 * 
	 * @param campo
	 * @param mensagem
	 */
	public static void validaCampoString(String campo, String mensagem) {
		if (Utils.isStringNullEmpty(campo)) {
			throw new IllegalArgumentException(Utils.mensagemCampoNuloVazio(mensagem));
		}
	}

	/**
	 * Valida se o ano para busca do feriado � um ano v�lido
	 * 
	 * @param ano
	 */
	public static void validaAnoValido(int ano) {

		try {

			LocalDate hoje = LocalDate.now();
			int anosRange = Math.abs(hoje.getYear() - ano);

			if (anosRange > Utils.QTD_ANOS_RANGE_FERIADO) {
				throw new FeriadoExcedeLimiteException(anosRange);
			}

		} catch (FeriadoExcedeLimiteException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
