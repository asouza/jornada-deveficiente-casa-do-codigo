package com.deveficiente.casadocodigov2.fechamentocompra;

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
public class FechaCompraParte1Controller {
	
	@Autowired
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private CupomRepository cupomRepository;
	@Autowired
	private CupomValidoValidator cupomValidoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidator,cupomValidoValidator);
	}

	@PostMapping(value = "/compras")
	@Transactional
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		
		Compra novaCompra = request.toModel(manager,cupomRepository);
		manager.persist(novaCompra);
		
		return novaCompra.toString();
	}
	
}
