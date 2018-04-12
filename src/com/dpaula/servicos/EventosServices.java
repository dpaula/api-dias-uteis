package com.dpaula.servicos;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class EventosServices {

	private static final String TOKEN = "ZmVybmFuZG8uZHBhdWxhQGdtYWlsLmNvbSZoYXNoPTk4ODIyNzA1";
	private static final String TARGET = "https://api.calendario.com.br/";

	public static String getInstance(int ano, String estado, String cidade) {

		Client newClient = ClientBuilder.newClient();

		WebTarget queryParam = newClient.target(TARGET)/**/
				.queryParam("ano", ano)/**/
				.queryParam("estado", estado)/**/
				.queryParam("cidade", cidade)/**/
				.queryParam("token", TOKEN);

		return queryParam.request().get(String.class);

	}

}
