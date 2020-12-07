package com.demo.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.domain.Cargo;
import com.demo.service.CargoService;

@Component // Component porque é um bean gerenciado, porque esta sendo injetado DepartamentoService
public class StringToCargoConverter implements Converter<String, Cargo>{

	@Autowired
	private CargoService service;
	
	@Override
	public Cargo convert(String text) {
		if(text.isEmpty())
			return null;
		
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}
	
}
