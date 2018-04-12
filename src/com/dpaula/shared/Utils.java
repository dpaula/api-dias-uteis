package com.dpaula.shared;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import com.dpaula.recursos.LeitorProperties;

public class Utils {

	public static final String ESTADO_PADRAO = "SC";
	public static final String CIDADE_PADRAO = "BLUMENAU";
	public static final int QTD_ANOS_RANGE = 5;
	public static final int QTD_ANOS_RANGE_FERIADO = 10;
	public static final String ARQUIVO_PROPRIEDADES = "conf.properties";
	private static final String PADRAO_DATA = "dd/MM/yyyy";

	public static LocalDate getUltimoDiaAnoAtual() {
		LocalDate hoje = LocalDate.now();
		return hoje.with(Month.DECEMBER).withDayOfMonth(Month.DECEMBER.maxLength());

	}

	/**
	 * Retorna se a String passada está vazia ou nula
	 * 
	 * @param campo
	 * @return
	 */
	public static boolean isStringNullEmpty(String campo) {

		if (campo == null || campo.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	 * @param campo
	 * @return uma mensagem " O campo: X não pode ser nulo ou vazio!"
	 */
	public static String mensagemCampoNuloVazio(String campo) {
		return "O campo: " + campo + " não pode ser nulo ou vazio!";
	}

	/**
	 * @param valor
	 * @return Converte data String no padrão (dd/MM/yyyy) para LocalDate
	 */
	public static LocalDate parseDataLocalDate(String valor) {
		return LocalDate.parse(valor, DateTimeFormatter.ofPattern(Utils.PADRAO_DATA));
	}

	/**
	 * 
	 * @return as propriedades do sistema
	 */
	public static LeitorProperties properties() {
		LeitorProperties p = null;
		try {
			p = new LeitorProperties(Utils.ARQUIVO_PROPRIEDADES);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return p;
	}

}
