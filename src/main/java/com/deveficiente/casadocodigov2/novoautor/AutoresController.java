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

	private AutorRepository autorRepository;

	public AutoresController(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}

	@PostMapping(value = "/autores")
	@Transactional
	// 1
	// 2
	public String cria(@RequestBody @Valid NovoAutorRequest request) {
		// 1
		Autor autor = request.toModel();
		autorRepository.save(autor);
		return autor.toString();
	}

}
