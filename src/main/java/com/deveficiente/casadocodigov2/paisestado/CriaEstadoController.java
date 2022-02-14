package com.deveficiente.casadocodigov2.paisestado;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CriaEstadoController {

	private CadastraNovoEstado cadastraNovoEstado;

	public CriaEstadoController(CadastraNovoEstado cadastraNovoEstado) {
		super();
		this.cadastraNovoEstado = cadastraNovoEstado;
	}

	@PostMapping(value = "/estados")
	public String cria(@RequestBody @Valid NovoEstadoRequest request) {			
		Estado novoEstado = cadastraNovoEstado.executa(request);
		return novoEstado.toString();
	}

}
