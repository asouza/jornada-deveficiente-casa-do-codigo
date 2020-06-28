package com.deveficiente.casadocodigov2.detalhelivro;

import com.deveficiente.casadocodigov2.novoautor.Autor;

public class DetalheSiteAutorResponse  {

	private String nome;
	private String descricao;

	public DetalheSiteAutorResponse(Autor autor) {
		nome = autor.getNome();
		descricao = autor.getDescricao();
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
