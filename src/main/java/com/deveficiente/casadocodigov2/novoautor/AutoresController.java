package com.deveficiente.casadocodigov2.novoautor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//3
public class AutoresController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		//1
		binder.addValidators(proibeEmailDuplicadoAutorValidator);
	}

	@PostMapping(value = "/autores")
	@Transactional
	//1
	//2
	public String cria(@RequestBody @Valid NovoAutorRequest request) {
		//1
		Autor autor = request.toModel();
		manager.persist(autor);
		return autor.toString();
	}

	
}
