package com.deveficiente.casadocodigov2.cadastrolivro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.casadocodigov2.cadastrocategoria.Categoria;
import com.deveficiente.casadocodigov2.novoautor.Autor;

@RestController
public class LivrosController {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/livros")
	@Transactional
	//1
	public String cria(@RequestBody @Valid NovoLivroRequest request) {
		//1
		Livro novoLivro = request.toModel(
				id -> manager.find(Autor.class, id),
				id -> manager.find(Categoria.class, id)
				);
		manager.persist(novoLivro);
		return novoLivro.toString();
	}

}
