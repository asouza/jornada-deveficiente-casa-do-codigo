package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new VerificaDocumentoCpfCnpjValidator(),estadoPertenceAPaisValidator);
	}

	@PostMapping(value = "/compras")
	public String cria(@RequestBody @Valid NovaCompraRequest request) {
		
		Compra novaCompra = request.toModel(manager);
		System.out.println("cria");
		return novaCompra.toString();
	}
	
}
