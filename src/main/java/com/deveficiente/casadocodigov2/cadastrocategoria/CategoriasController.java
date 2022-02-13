package com.deveficiente.casadocodigov2.cadastrocategoria;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//2
public class CategoriasController {

	//1
	private CadastraNovaCategoria cadastraNovaCategoria;

	public CategoriasController(CadastraNovaCategoria cadastraNovaCategoria) {
		super();
		this.cadastraNovaCategoria = cadastraNovaCategoria;
	}

	@PostMapping(value = "/categorias")
	public String cria(@RequestBody @Valid NovaCategoriaRequest request) {

		//1
		Categoria novaCategoria = cadastraNovaCategoria.executa(request);

		return novaCategoria.toString();
	}

}
