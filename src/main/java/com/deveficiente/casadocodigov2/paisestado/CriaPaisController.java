package com.deveficiente.casadocodigov2.paisestado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriaPaisController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/paises")
	@Transactional
	public String criaPais(@RequestBody @Valid NovoPaisRequest request) {
		Pais novoPais = new Pais(request.getNome());
		manager.persist(novoPais);
		return novoPais.toString();
	}

}
