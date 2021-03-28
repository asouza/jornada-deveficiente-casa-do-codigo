package com.deveficiente.casadocodigov2.detalhelivro;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import com.deveficiente.casadocodigov2.cadastrolivro.Livro;

public class DetalheSiteLivroResponse {

	private DetalheSiteAutorResponse autor;
	private String titulo;
	private String isbn;
	private int numeroPaginas;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private String dataPublicacao;

	public DetalheSiteLivroResponse(Livro livro) {
		titulo = livro.getTitulo();
		autor = new DetalheSiteAutorResponse(livro.getAutor());
		isbn = livro.getIsbn();
		numeroPaginas = livro.getNumeroPaginas();
		preco = livro.getPreco().setScale(2);
		resumo = livro.getResumo();
		sumario = livro.getSumario();		
		dataPublicacao = livro.getDataPublicacao()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	}
	
	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public DetalheSiteAutorResponse getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

}
