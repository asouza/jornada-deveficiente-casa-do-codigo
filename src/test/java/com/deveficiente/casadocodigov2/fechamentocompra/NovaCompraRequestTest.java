package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import com.deveficiente.casadocodigov2.cadastrocategoria.Categoria;
import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;
import com.deveficiente.casadocodigov2.cadastrolivro.Livro;
import com.deveficiente.casadocodigov2.novoautor.Autor;
import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

public class NovaCompraRequestTest {

	private EntityManager manager = Mockito.mock(EntityManager.class);
	private CupomRepository cupomRepository = Mockito
			.mock(CupomRepository.class);

	private List<NovoPedidoItemRequest> itens = List
			.of(new NovoPedidoItemRequest(1l, 5));
	private NovoPedidoRequest pedido = new NovoPedidoRequest(new BigDecimal("50"),
			itens);
	
	private Pais pais = new Pais("pais");	
	private Autor autor = new Autor("nome", "email@email.com", "descricao");
	private Categoria categoria = new Categoria("categoria");
	private Livro livro = new Livro("titulo", "resunmo", "sumario", BigDecimal.TEN,
			100, "97834985782", LocalDate.of(2000, 10, 10), autor,
			categoria);
	
	{
		Mockito.when(manager.find(Pais.class, 1l)).thenReturn(pais);
		
		Mockito.when(manager.find(Livro.class, 1l)).thenReturn(livro);
		
		Mockito.when(manager.find(Estado.class, 1l))
		.thenReturn(new Estado("estado", pais));
		
		Mockito.when(cupomRepository.getByCodigo("codigo-cupom"))
		.thenReturn(new Cupom("codigo-cupom", BigDecimal.TEN,
				LocalDate.now().plusDays(1l)));
	}
	
	private NovaCompraRequest request = new NovaCompraRequest("email@email.com",
			"Alberto", "Souza", "11111111", "endereco", "complemento",
			"salvador", 1l, "999999999", "400000", pedido);
	
	@Test
	@DisplayName("cria compra com estado e cupom")
	void teste1() throws Exception {

		request.setCodigoCupom("codigo-cupom");
		request.setIdEstado(1l);
		Compra novaCompra = request.toModel(manager, cupomRepository);

		Assertions.assertNotNull(novaCompra);
		Mockito.verify(manager).find(Estado.class, 1l);
		Mockito.verify(cupomRepository).getByCodigo("codigo-cupom");

	}
	
	@Test
	@DisplayName("cria compra sem estado e com cupom")
	void teste2() throws Exception {
		request.setCodigoCupom("codigo-cupom");
		Compra novaCompra = request.toModel(manager, cupomRepository);
		
		Assertions.assertNotNull(novaCompra);
		//abre o find para garantir que tal find nao eh para ser chamado nunca
		Mockito.verify(manager,Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
		Mockito.verify(cupomRepository).getByCodigo("codigo-cupom");
		
	}
	
	@Test
	@DisplayName("cria compra sem estado e sem cupom")
	void teste3() throws Exception {
		Compra novaCompra = request.toModel(manager, cupomRepository);
		
		Assertions.assertNotNull(novaCompra);
		
		//usa o matcher para facilitar achar o bug, já que as buscas nunca devem ser chamadas com nada
		Mockito.verify(manager,Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
		Mockito.verify(cupomRepository,Mockito.never()).getByCodigo(Mockito.anyString());
		
	}
	
	/*
	 * cpf true cnpj false
	 * cpf false cnpj true
	 * cpf false e cnpj false
	 */
	@ParameterizedTest
	@DisplayName("verifica documento válido")
	@CsvSource({
		"79744157038,true","43134638000128,true","987453895349,false"
	})
	void teste4(String documento,boolean resultadoEsperado) throws Exception {
		NovaCompraRequest request = new NovaCompraRequest("email@email.com",
				"Alberto", "Souza", documento, "endereco", "complemento",
				"salvador", 1l, "999999999", "400000", pedido);
		
		Assertions.assertEquals(resultadoEsperado, request.documentoValido());
	}
}
