/**
 * 
 */
package com.dpaula.controle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.dpaula.excecoes.ControleExcecoes;
import com.dpaula.excecoes.FeriadoNaoExisteException;
import com.dpaula.modelo.Feriado;
import com.dpaula.servicos.EventosServices;
import com.thoughtworks.xstream.XStream;

/**
 * Classe responsável pelo controle de feriados nacionais
 * 
 * @author dpaula
 *
 */
public class ControleFeriados {

	private String cidade;
	private String estado;
	private int ano;
	private List<Feriado> feriados = new ArrayList<Feriado>();

	/**
	 * @param cidade
	 * @param estado
	 * @param ano
	 */
	public ControleFeriados(int ano, String estado, String cidade) {
		this.setAno(ano);
		this.setEstado(estado);
		this.setCidade(cidade);
	}

	/**
	 * Através do ano informado, este método retorna uma lista com todos os feriados
	 * 
	 * @return {@link Feriado}
	 */
	public List<Feriado> retornaFeriados() {

		String eventos = EventosServices.getInstance(ano, estado, cidade);

		XStream xStream = new XStream();
		xStream.alias("event", Feriado.class);
		xStream.alias("events", ControleFeriados.class);
		xStream.addImplicitCollection(ControleFeriados.class, "feriados");
		xStream.aliasField("type_code", Feriado.class, "typeCode");
		xStream.registerConverter(new LocalDateConverter());
		xStream.ignoreUnknownElements();

		ControleFeriados feriados = (ControleFeriados) xStream.fromXML(eventos);

		return feriados.getFeriados();
	}

	/**
	 * Método estático que retorna o próximo feriado nacional e regional conforme a
	 * região passada.<br>
	 * 
	 * Retorna uma instancia de {@link Feriado} com os seguintes campos:<br>
	 * <ul>
	 * <li>Data {@link LocalDate}</li>
	 * <li>Nome {@link String}</li>
	 * <li>Descrição {@link String}</li>
	 * <li>Descrição do tipo do feriado {@link String}</li>
	 * <li>Código do tipo do feriado</li>
	 * <li>Link para uma pagina com uma descrição maior do feriado</li>
	 * </ul>
	 * 
	 * @param estado
	 * @param cidade
	 * @return Feriado
	 * @throws FeriadoNaoExisteException
	 */
	public static Feriado proximoFeriado(String estado, String cidade) throws FeriadoNaoExisteException {

		LocalDate hoje = LocalDate.now();

		List<Feriado> feriados = new ControleFeriados(hoje.getYear(), estado, cidade).retornaFeriados();

		feriados.sort(Comparator.comparing(f -> f.getDate()));

		Optional<Feriado> feriado = feriados.stream()/**/
				.filter(f -> f.getTypeCode() != 9)/**/
				.filter(f -> f.getDate().isAfter(hoje)).findFirst();

		return feriado.orElseThrow(() -> new FeriadoNaoExisteException());
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade
	 *            the cidade to set
	 */
	public void setCidade(String cidade) {

		ControleExcecoes.validaCampoString(cidade, "cidade");

		this.cidade = cidade.toUpperCase();
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {

		ControleExcecoes.validaCampoString(estado, "estado");

		this.estado = estado.toUpperCase();
	}

	/**
	 * @return the ano
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * @param ano
	 *            the ano to set
	 */
	public void setAno(int ano) {

		ControleExcecoes.validaAnoValido(ano);

		this.ano = ano;
	}

	/**
	 * @return the feriados
	 */
	public List<Feriado> getFeriados() {
		return feriados;
	}

}
