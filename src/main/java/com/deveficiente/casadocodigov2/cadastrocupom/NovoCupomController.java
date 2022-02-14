package com.deveficiente.casadocodigov2.cadastrocupom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovoCupomController {

	@PersistenceContext
	private EntityManager manager;
	private CadastraNovoCupom cadastraNovoCupom;

	public NovoCupomController(CadastraNovoCupom cadastraNovoCupom) {
		super();
		this.cadastraNovoCupom = cadastraNovoCupom;
	}

	@PostMapping(value = "/cupons")
	@Transactional
	public String cria(@RequestBody @Valid NovoCupomRequest request) {
		
		Cupom novoCupom = cadastraNovoCupom.executa(request);
		return novoCupom.toString();
	}

}
