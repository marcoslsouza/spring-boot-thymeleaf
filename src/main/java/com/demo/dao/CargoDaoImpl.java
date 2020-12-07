package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.domain.Cargo;
import com.demo.util.PaginacaoUtil;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

	public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
		
		// Quantidade de registros por página.
		int tamanho = 2;
		// Indica o primeiro registro de cada página
		int inicio = (pagina -1) * tamanho;
		
		long totalRegistros = count();
		long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;
		
		List<Cargo> cargos = getEntityManager().createQuery("SELECT c FROM Cargo c ORDER BY c.nome " + direcao).setFirstResult(inicio).setMaxResults(tamanho).getResultList();
		
		return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, cargos);
	}
	
	public long count() {
		return getEntityManager().createQuery("SELECT COUNT(*) FROM Cargo", Long.class).getSingleResult();
	}
}
