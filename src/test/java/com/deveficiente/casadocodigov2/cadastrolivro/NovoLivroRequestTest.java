package com.deveficiente.casadocodigov2.cadastrolivro;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.deveficiente.casadocodigov2.cadastrocategoria.Categoria;
import com.deveficiente.casadocodigov2.novoautor.Autor;

public class NovoLivroRequestTest {

	private NovoLivroRequest request = new NovoLivroRequest("", "", "",
			BigDecimal.TEN, 100, "", 1l, 1l);

	@Test
	@DisplayName("cria o livro categoria e autor estao cadastrados")
	void teste1() throws Exception {

		EntityManager manager = Mockito.mock(EntityManager.class);
		
		Mockito.when(manager.find(Categoria.class, 1l))
				.thenReturn(new Categoria(""));
		
		Mockito.when(manager.find(Autor.class, 1l))
		.thenReturn(new Autor("", "", ""));
		
		//assert meio fraco já que não checa o estado do livro
		//efeito colateral do teste em cima de condição em pre condicao
		Assertions.assertNotNull(request.toModel(manager));
		
				
	}
	
	@Test
	@DisplayName("nao cria livro caso o autor nao exista no banco")
	void teste2() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		
		Mockito.when(manager.find(Categoria.class, 1l))
		.thenReturn(new Categoria(""));
		
		Mockito.when(manager.find(Autor.class, 1l))
		.thenReturn(null);
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			request.toModel(manager);					
		});
		
	}
	
	@Test
	@DisplayName("nao cria livro caso a categoria nao exista")
	void teste3() throws Exception {
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		
		Mockito.when(manager.find(Categoria.class, 1l))
		.thenReturn(null);
		
		Mockito.when(manager.find(Autor.class, 1l))
		.thenReturn(new Autor("", "", ""));
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			request.toModel(manager);					
		});
		
	}
}
