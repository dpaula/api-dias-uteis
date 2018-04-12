package com.dpaula.recursos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LeitorProperties {

	private Properties props;

	public LeitorProperties(String nomeArquivo) throws FileNotFoundException {
		props = new Properties();
		// InputStream in = this.getClass().getResourceAsStream(nomeArquivo);
		InputStream in = new FileInputStream(nomeArquivo);
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getValor(String chave) {
		return props.getProperty(chave);
	}

	public static void main(String[] args) throws FileNotFoundException {
		LeitorProperties l = new LeitorProperties("./conf.properties");

		String valor = l.getValor("prop.servicos.token");

		System.out.println(valor);
	}

}
