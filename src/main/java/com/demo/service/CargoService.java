package com.demo.service;

import java.util.List;

import com.demo.domain.Cargo;
import com.demo.util.PaginacaoUtil;

public interface CargoService {
	
	void salvar(Cargo cargo);
	void editar(Cargo cargo);
	void excluir(Long id);
	Cargo buscarPorId(Long id);
	List<Cargo> buscarTodos();
	boolean cargoTemFuncionario(Long id);
	PaginacaoUtil<Cargo> buscaPorPagina(int pagina, String direcao);
}
