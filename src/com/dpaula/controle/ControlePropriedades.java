/**
 * 
 */
package com.dpaula.controle;

import java.time.LocalDate;

import com.dpaula.shared.Utils;

/**
 * @author ferna
 *
 */
public class ControlePropriedades {

	public static final LocalDate DATA_INICIO = getDataIncio();
	public static final LocalDate DATA_FIM = getDataFim();
	public static final String ESTADO = getEstado();
	public static final String CIDADE = getCidade();

	private static LocalDate getDataIncio() {
		return LocalDate.now();
	}

	private static String getCidade() {
		String cidade = Utils.properties().getValor("prop.ControleDiaUtil.cidade");
		return cidade == null ? null : cidade.toUpperCase();
	}

	private static String getEstado() {
		String estado = Utils.properties().getValor("prop.ControleDiaUtil.estado");
		return estado == null ? null : estado.toUpperCase();
	}

	private static LocalDate getDataFim() {
		return Utils.parseDataLocalDate(Utils.properties().getValor("prop.ControleDiaUtil.dataFim"));
	}

}
