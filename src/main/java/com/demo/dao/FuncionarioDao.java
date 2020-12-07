package com.demo.dao;

import java.time.LocalDate;
import java.util.List;

import com.demo.domain.Funcionario;

public interface FuncionarioDao {

	void save(Funcionario funcionario);
	void update(Funcionario funcionario);
	void delete(Long id);
	Funcionario findById(Long id);
	List<Funcionario> findAll();
	List<Funcionario> findByName(String name);
	List<Funcionario> findByCagoId(Long id);
	// Busca por data de entrada e data de saída
	List<Funcionario> findByDataEntradaDataSaida(LocalDate dataEntrada, LocalDate dataSaida);
	// Busca por data de entrada
	List<Funcionario> findByDataEntrada(LocalDate dataEntrada, LocalDate dataSaida);
	// Busca por data de saída
	List<Funcionario> findByDataSaida(LocalDate dataEntrada, LocalDate dataSaida);
}
