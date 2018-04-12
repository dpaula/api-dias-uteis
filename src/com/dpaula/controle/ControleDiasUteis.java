package com.dpaula.controle;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dpaula.excecoes.ControleExcecoes;
import com.dpaula.modelo.Feriado;
import com.dpaula.shared.Utils;

public class ControleDiasUteis {

	private LocalDate inicio;
	private LocalDate fim;
	private String estado;
	private String cidade;

	/**
	 * @param inicio
	 * @param fim
	 */
	public ControleDiasUteis(LocalDate inicio, LocalDate fim, String estado, String cidade) {
		this.setInicio(inicio);
		this.setFim(fim);
		this.setEstado(estado);
		this.setCidade(cidade);
	}

	public ControleDiasUteis() {
		this.setInicio(ControlePropriedades.DATA_INICIO);
		this.setFim(ControlePropriedades.DATA_FIM);
		this.setEstado(ControlePropriedades.ESTADO);
		this.setCidade(ControlePropriedades.CIDADE);
	}

	/**
	 * @param inicio
	 */
	public ControleDiasUteis(LocalDate inicio) {
		this.setInicio(inicio);
		this.fim = Utils.getUltimoDiaAnoAtual();
		this.estado = Utils.ESTADO_PADRAO;
		this.cidade = Utils.CIDADE_PADRAO;
	}

	/**
	 * Atravéz dos atributos iniciados da instancia, este método retorna a
	 * quantidade de dias úteis entre o período de incio de fim indicados
	 * 
	 * @return quantidade de dias úteis
	 */
	public int retornaQtdDiasUteis() {

		List<LocalDate> feriados = retornaDatasFeriadosAnosOrdenados();

		int size = retornaQuantidadeDiasCorridos().stream()/**/
				.filter(d -> d.isAfter(inicio.minusDays(1)) && d.isBefore(fim.plusDays(1)))/**/
				.filter(d -> isNotSabadoDomingo(d))/**/
				.filter(d -> !feriados.contains(d))/**/
				.collect(Collectors.toList()).size();

		return size;
	}

	/**
	 * @param feriados
	 */
	private List<LocalDate> retornaDatasFeriadosAnosOrdenados() {

		List<LocalDate> feriados = new ArrayList<LocalDate>();

		int qtdAnos = fim.getYear() - inicio.getYear();
		if (qtdAnos > 0) {

			Object[] array = Stream.iterate(inicio.getYear(), ano -> ano + 1).limit(qtdAnos + 1).toArray();

			for (Object ano : array) {
				feriados.addAll(retornaDatasFeriados(ano));
			}
		} else {
			feriados.addAll(retornaDatasFeriados(inicio.getYear()));
		}

		feriados.sort(Comparator.comparing(f -> f));

		return feriados;
	}

	/**
	 * @return quantidade de dias corridos do período
	 */
	private List<LocalDate> retornaQuantidadeDiasCorridos() {
		List<LocalDate> dias = Stream.iterate(inicio, data -> data.plusDays(1))/**/
				.limit(qtdDiasCorridos())/**/
				.collect(Collectors.toList());
		return dias;
	}

	private long qtdDiasCorridos() {
		return ChronoUnit.DAYS.between(inicio, fim.plusDays(1));
	}

	/**
	 * @param data
	 * @return
	 */
	private static boolean isNotSabadoDomingo(LocalDate data) {
		return !data.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
	}

	/**
	 * @param ano
	 * @return
	 */
	private List<LocalDate> retornaDatasFeriados(Object ano) {
		ControleFeriados controleFeriados = new ControleFeriados((int) ano, this.estado, this.cidade);
		List<Feriado> retornaFeriados = controleFeriados.retornaFeriados();
		return retornaFeriados.stream().map(f -> f.getDate()).collect(Collectors.toList());
	}

	/**
	 * @return the inicio
	 */
	public LocalDate getInicio() {
		return inicio;
	}

	/**
	 * @param inicio
	 *            the inicio to set
	 */
	public void setInicio(LocalDate inicio) {

		ControleExcecoes.validaData(inicio, true);

		this.inicio = inicio;
	}

	/**
	 * @return the fim
	 */
	public LocalDate getFim() {
		return fim;
	}

	/**
	 * @param fim
	 *            the fim to set
	 */
	public void setFim(LocalDate fim) {

		ControleExcecoes.validaData(fim, false);

		this.fim = fim;
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

}
