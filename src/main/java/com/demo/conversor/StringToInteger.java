package com.demo.conversor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component // O Spring utiliza quando for necessario
public class StringToInteger implements Converter<String, Integer> {

	@Override
	public Integer convert(String text) {
		
		text = text.trim();
		// Verifica se text possui apenas digitos
		if(text.matches("[0-9]+"))
			return Integer.valueOf(text); // Converte para integer
		
		return null; // Retorna null e @NotNull de Endereco indica que o campo recebe apenas inteiro
	}
	
}
