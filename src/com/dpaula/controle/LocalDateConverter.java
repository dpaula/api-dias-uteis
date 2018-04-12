package com.dpaula.controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * 
 * @author dpaula
 *
 */
public class LocalDateConverter extends AbstractSingleValueConverter {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public boolean canConvert(Class type) {
		return (type != null) && LocalDate.class.getPackage().equals(type.getPackage());
	}

	public String toString(Object source) {

		LocalDate data = (LocalDate) source;

		return formatter.format(data);
	}

	public Object fromString(String str) {

		return LocalDate.parse(str, formatter);
	}

}
