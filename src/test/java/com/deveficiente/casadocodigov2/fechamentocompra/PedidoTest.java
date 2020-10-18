package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import com.deveficiente.casadocodigov2.cadastrocategoria.Categoria;
import com.deveficiente.casadocodigov2.cadastrolivro.Livro;
import com.deveficiente.casadocodigov2.novoautor.Autor;

public class PedidoTest {

	@DisplayName("verifica se o total do pedido é igual ao passado como argumento")
	@ParameterizedTest
	@CsvSource({
		"10,true",
		"9.99,false",
		"10.01,false"
	})
	void teste1(BigDecimal valor,boolean resultadoEsperado) throws Exception {
		Autor autor = new Autor("nome", "email@email.com", "descricao");
		Categoria categoria = new Categoria("categoria");
		Livro livro = new Livro("titulo", "resumo", "sumario", BigDecimal.TEN, 100, "98754985743", LocalDate.of(2000, 10, 10), autor, categoria);
		Set<ItemPedido> itens = Set.of(new ItemPedido(livro, 1));
		Pedido pedido = new Pedido(Mockito.mock(Compra.class), itens);

		Assertions.assertEquals(resultadoEsperado, pedido.totalIgual(valor));
	}
}
