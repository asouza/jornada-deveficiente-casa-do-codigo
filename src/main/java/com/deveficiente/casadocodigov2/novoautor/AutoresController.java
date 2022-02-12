package com.deveficiente.casadocodigov2.novoautor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//3
public class AutoresController {

	//1
	private CadastroNovoAutor cadastroNovoAutor;

	public AutoresController(CadastroNovoAutor cadastroNovoAutor) {
		super();
		this.cadastroNovoAutor = cadastroNovoAutor;
	}

	@PostMapping(value = "/autores")
	public String cria(@RequestBody @Valid NovoAutorRequest request) {
		// 1		
		Autor autor = cadastroNovoAutor.executa(request);
		return autor.toString();
	}

}
