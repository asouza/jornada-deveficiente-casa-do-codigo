package com.deveficiente.casadocodigov2.detalhelivro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;
import com.deveficiente.casadocodigov2.cadastrolivro.Livro;

@RestController
public class DetalheLivroSiteController {

	private BuscadorDeEntidades buscadorDeEntidades;

	public DetalheLivroSiteController(BuscadorDeEntidades buscadorDeEntidades) {
		super();
		this.buscadorDeEntidades = buscadorDeEntidades;
	}

	@GetMapping(value = "/produtos/{id}")
	public DetalheSiteLivroResponse detalhe(@PathVariable("id") Long id) {

		// 1
		Livro livroBuscado = buscadorDeEntidades.buscaPorId(Livro.class, id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND));

		// 1
		DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(
				livroBuscado);
		return detalheSiteLivroResponse;
	}

}
