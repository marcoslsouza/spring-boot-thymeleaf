package com.demo.validator;

// Exemplo Spring validator

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.demo.domain.Funcionario;

public class FuncionarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		
		Funcionario f = (Funcionario) object;
		
		LocalDate entrada = f.getDataEntrada();
		
		if(f.getDataSaida() != null) {
			// Verifica se a data de saida é maior que a data de entrada
			if(f.getDataSaida().isBefore(entrada))
				errors.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida"); // Campo / chave referente ao erro no arquivo messages.properties
		}
	}

}
