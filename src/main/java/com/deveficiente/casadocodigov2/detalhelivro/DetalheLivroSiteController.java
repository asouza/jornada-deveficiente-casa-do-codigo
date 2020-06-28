package com.deveficiente.casadocodigov2.detalhelivro;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.casadocodigov2.cadastrolivro.Livro;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@RestController
public class DetalheLivroSiteController {

	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/produtos/{id}")
	public ResponseEntity<?> detalhe(@PathVariable("id") Long id) {
		
		//1
		Livro livroBuscado = manager.find(Livro.class, id);
		// o find retorna nulo, aí eu não posso fazer nada. Tenho que verificar
		// nulo
		//1
		if (livroBuscado == null) {
			return ResponseEntity.notFound().build();
		}

		//1
		DetalheSiteLivroResponse detalheSiteLivroResponse = new DetalheSiteLivroResponse(
				livroBuscado);
		return ResponseEntity.ok(detalheSiteLivroResponse);
	}

}
