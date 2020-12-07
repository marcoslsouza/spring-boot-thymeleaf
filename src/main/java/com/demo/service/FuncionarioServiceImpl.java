package com.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.FuncionarioDao;
import com.demo.domain.Funcionario;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDao dao;
	
	@Override
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Override
	public void editar(Funcionario funcionario) {
		dao.update(funcionario);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarTodos() {
		return dao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarPorNome(String nome) {
		return dao.findByName(nome);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarPorCargo(Long id) {
		return dao.findByCagoId(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> buscarPorDatas(LocalDate dataEntrada, LocalDate dataSaida) {
		if(dataEntrada != null && dataSaida != null) {
			return dao.findByDataEntradaDataSaida(dataEntrada, dataSaida);
		} else
			if(dataEntrada != null) {
				return dao.findByDataEntrada(dataEntrada, dataSaida);
			} else
				if(dataSaida != null) {
					return dao.findByDataSaida(dataEntrada, dataSaida);
				} else
					return new ArrayList<>(); // Devolve uma lista vazia
	}	
}
