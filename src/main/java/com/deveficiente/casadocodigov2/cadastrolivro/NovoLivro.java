package com.deveficiente.casadocodigov2.cadastrolivro;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.deveficiente.casadocodigov2.cadastrocategoria.CategoriaRepository;
import com.deveficiente.casadocodigov2.novoautor.AutorRepository;

@Service
@Validated
//7
public class NovoLivro {

	//1
	private LivroRepository livroRepository;
	//1
	private AutorRepository autorRepository;
	//1
	private CategoriaRepository categoriaRepository;

	public NovoLivro(LivroRepository livroRepository,
			AutorRepository autorRepository,
			CategoriaRepository categoriaRepository) {
		super();
		this.livroRepository = livroRepository;
		this.autorRepository = autorRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional
	//1
	public Livro executa(@Valid DadosNovoLivro dados) {
		//1
		Livro novoLivro = dados.toModel(
				//1
				idAutor -> autorRepository.findById(idAutor).orElseThrow(),
				//1
				idCategoria -> categoriaRepository.findById(idCategoria)
						.orElseThrow());
		return livroRepository.save(novoLivro);
	}

}
