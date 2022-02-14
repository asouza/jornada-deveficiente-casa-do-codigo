package com.deveficiente.casadocodigov2.cadastrolivro;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//3
public class LivrosController {

	//1
	private NovoLivro novoLivro;

	public LivrosController(NovoLivro novoLivro) {
		super();
		this.novoLivro = novoLivro;
	}

	@PostMapping(value = "/livros")
	@Transactional
	// 1
	public String cria(@RequestBody @Valid NovoLivroRequest request) {
		// 1		
		Livro livroCriado = novoLivro.executa(request);
		return livroCriado.toString();
	}

}
