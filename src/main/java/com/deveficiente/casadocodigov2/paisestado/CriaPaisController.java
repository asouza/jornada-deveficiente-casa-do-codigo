package com.deveficiente.casadocodigov2.paisestado;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//3
public class CriaPaisController {

	//1
	private CadastraNovoPais novoPais;

	public CriaPaisController(CadastraNovoPais novoPais) {
		super();
		this.novoPais = novoPais;
	}

	@PostMapping(value = "/paises")
	@Transactional
	//1
	public String criaPais(@RequestBody @Valid NovoPaisRequest request) {
		//1
		Pais paisCriado = novoPais.executa(request);		
		return paisCriado.toString();
	}

}
