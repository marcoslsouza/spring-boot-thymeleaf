package com.demo.util;

import java.util.List;

public class PaginacaoUtil<T> {
	
	// Quantidade de linhas
	private int tamanho;
	// Número da página atual
	private int pagina;
	// Total de páginas no sistema de páginação
	private long totalDePaginas;
	// Armazena os registros para serem exibidos na view
	private List<T> registros;
	
	// Armazena a ordenação ASC/DESC
	private String direcao;
	
	public PaginacaoUtil(int tamanho, int pagina, long totalDePaginas, String direcao, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDePaginas = totalDePaginas;
		this.registros = registros;
		this.direcao = direcao;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getPagina() {
		return pagina;
	}

	public long getTotalDePaginas() {
		return totalDePaginas;
	}

	public List<T> getRegistros() {
		return registros;
	}

	public String getDirecao() {
		return direcao;
	}
}
