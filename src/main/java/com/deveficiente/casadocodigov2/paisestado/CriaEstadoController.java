package com.deveficiente.casadocodigov2.paisestado;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriaEstadoController {
	
	@Autowired
	private EntityManager manager;

	@PostMapping(value = "/estados")
	@Transactional
	public String cria(@RequestBody @Valid NovoEstadoRequest request) {		
		Estado novoEstado = request.toModel(manager);		
		manager.persist(novoEstado);
		
		return novoEstado.toString();
	}

}
