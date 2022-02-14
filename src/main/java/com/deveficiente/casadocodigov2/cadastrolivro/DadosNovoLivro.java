package com.deveficiente.casadocodigov2.cadastrolivro;

import java.util.function.Function;

import com.deveficiente.casadocodigov2.cadastrocategoria.Categoria;
import com.deveficiente.casadocodigov2.novoautor.Autor;

public interface DadosNovoLivro {

	Livro toModel(Function<Long, Autor> carregaAutor,
			Function<Long, Categoria> carregaCategoria);
}
