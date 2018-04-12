/**
 * 
 */
package com.dpaula.controle;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ferna
 *
 */
public class ControleDiasUteisTest {

	private String estado = "SC";
	private String cidade = "BLUMENAU";

	@Test
	public void retornaQtdDiasUteisFinalDeSemanaTest() {

		// 12, 13 - 16, 17, 18, 19, 20 - 23, 24, 25, 26, 27 - 30
		// 13 dias úteis
		LocalDate inicio = LocalDate.of(2018, 4, 12);
		LocalDate fim = LocalDate.of(2018, 4, 30);

		ControleDiasUteis cdu = new ControleDiasUteis(inicio, fim, estado, cidade);

		Assert.assertEquals(13, cdu.retornaQtdDiasUteis());

	}

	@Test
	public void retornaQtdDiasUteisComFeriadoTest() {

		// 25, 26, 26 - 30 - 2, 3, 4 - 7, 8, 9, 10, 11 - 14, 15
		// 14 dias úteis
		LocalDate inicio = LocalDate.of(2018, 4, 25);
		LocalDate fim = LocalDate.of(2018, 5, 15);

		ControleDiasUteis cdu = new ControleDiasUteis(inicio, fim, estado, cidade);

		Assert.assertEquals(14, cdu.retornaQtdDiasUteis());

	}

}
