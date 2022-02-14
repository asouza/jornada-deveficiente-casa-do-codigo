package com.deveficiente.casadocodigov2.cadastrolivro;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.deveficiente.casadocodigov2.cadastrocategoria.Categoria;
import com.deveficiente.casadocodigov2.novoautor.Autor;

@Service
@Validated
//6
public class NovoLivro {

	// 1
	private LivroRepository livroRepository;
	// 1
	private BuscadorDeEntidades buscadorDeEntidades;

	public NovoLivro(LivroRepository livroRepository,BuscadorDeEntidades buscadorDeEntidades) {
		super();
		this.livroRepository = livroRepository;
		this.buscadorDeEntidades = buscadorDeEntidades;
	}

	@Transactional
	// 1
	public Livro executa(@Valid DadosNovoLivro dados) {
		// 1
		Livro novoLivro = dados.toModel(
				// 1
				idAutor -> buscadorDeEntidades.retornaPorId(Autor.class, idAutor),
				// 1
				idCategoria -> buscadorDeEntidades.retornaPorId(Categoria.class,
						idCategoria));
		return livroRepository.save(novoLivro);
	}

}
