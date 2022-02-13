package com.deveficiente.casadocodigov2.cadastrocategoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
//3
public class CadastraNovaCategoria {

	//1
	private CategoriaRepository categoriaRepository;

	public CadastraNovaCategoria(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional
	//2
	public Categoria executa(@Valid DadosNovaCategoria dados) {
		Categoria novaCategoria = new Categoria(dados.getNome());
		return categoriaRepository.save(novaCategoria);		
	}

}
