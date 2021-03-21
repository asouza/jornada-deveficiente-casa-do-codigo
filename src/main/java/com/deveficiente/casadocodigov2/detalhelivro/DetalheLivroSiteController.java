package com.deveficiente.casadocodigov2.detalhelivro;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deveficiente.casadocodigov2.cadastrolivro.Livro;

@RestController
public class DetalheLivroSiteController {

	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/produtos/{id}")
	public DetalheSiteLivroResponse detalhe(@PathVariable("id") Long id) {

		// 1
		Livro livroBuscado = Optional.ofNullable(manager.find(Livro.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

		// 1
		DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(
				livroBuscado);
		return detalheSiteLivroResponse;
	}

}
