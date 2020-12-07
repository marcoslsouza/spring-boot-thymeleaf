package com.demo.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.demo.domain.Funcionario;

@Repository
public class FuncionarioImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

	@Override
	public List<Funcionario> findByName(String name) {
		TypedQuery<Funcionario> query = getEntityManager().createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE :name", Funcionario.class);
		query.setParameter("name", name);
		return query.getResultList();
		
		// método de AbstractDao: return createQuery("select f from Funcionario f where f.nome like concat('%', ?1, '%') ", nome);
	}

	@Override
	public List<Funcionario> findByCagoId(Long id) {
		return createQuery("SELECT f FROM Funcionario f WHERE f.cargo.id = ?1", id);
	}

	@Override
	public List<Funcionario> findByDataEntradaDataSaida(LocalDate dataEntrada, LocalDate dataSaida) {
		return createQuery("SELECT f FROM Funcionario f WHERE f.dataEntrada >= ?1 AND f.dataSaida <= ?2 ORDER BY f.dataEntrada ASC", dataEntrada, dataSaida);
	}

	@Override
	public List<Funcionario> findByDataEntrada(LocalDate dataEntrada, LocalDate dataSaida) {
		return createQuery("SELECT f FROM Funcionario f WHERE f.dataEntrada = ?1 ORDER BY f.dataEntrada ASC", dataEntrada);
	}

	@Override
	public List<Funcionario> findByDataSaida(LocalDate dataEntrada, LocalDate dataSaida) {
		return createQuery("SELECT f FROM Funcionario f WHERE f.dataSaida = ?1 ORDER BY f.dataEntrada ASC", dataSaida);
	}
}
	
