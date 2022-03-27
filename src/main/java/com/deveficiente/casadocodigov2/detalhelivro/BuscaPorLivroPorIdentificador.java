package com.deveficiente.casadocodigov2.detalhelivro;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;
import com.deveficiente.casadocodigov2.cadastrolivro.Livro;

@Service
public class BuscaPorLivroPorIdentificador {

	private BuscadorDeEntidades buscadorDeEntidades;

	public BuscaPorLivroPorIdentificador(
			BuscadorDeEntidades buscadorDeEntidades) {
		super();
		this.buscadorDeEntidades = buscadorDeEntidades;
	}
	
	public Optional<Livro> executa(Long id){
		return buscadorDeEntidades.buscaPorId(Livro.class, id);
	}

}
