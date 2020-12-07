package com.demo.dao;

import java.util.List;

import com.demo.domain.Cargo;
import com.demo.util.PaginacaoUtil;

public interface CargoDao {
	
	void save(Cargo cargo);
	void update(Cargo cargo);
	void delete(Long id);
	Cargo findById(Long id);
	List<Cargo> findAll();
	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao);
}
