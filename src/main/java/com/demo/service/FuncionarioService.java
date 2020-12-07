package com.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.demo.domain.Funcionario;

public interface FuncionarioService {
	
	void salvar(Funcionario funcionario);
	void editar(Funcionario funcionario);
	void excluir(Long id);
	Funcionario buscarPorId(Long id);
	List<Funcionario> buscarTodos();
	List<Funcionario> buscarPorNome(String nome);
	List<Funcionario> buscarPorCargo(Long id);
	List<Funcionario> buscarPorDatas(LocalDate dataEntrada, LocalDate dataSaida);
}
