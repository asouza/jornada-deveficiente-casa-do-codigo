package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

public class EstadoPertenceAPaisValidatorTest {
	private EntityManager manager = Mockito.mock(EntityManager.class);	
	private Pais paisPadrao = new Pais("teste");
	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 10));
	private NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(BigDecimal.TEN, itens);
	private NovaCompraRequest request = new NovaCompraRequest("email@email.com", "nome",
			"sobrenome", "8967548654", "endereco", "complemento", "cidade",
			1l, "987454778", "54534534", pedidoRequest);

	@Test
	@DisplayName("deveria validar uma compra com pais e estado pertencente ao pais")
	void teste1() throws Exception {		
		Estado estadoDoPais = new Estado("estado", paisPadrao);
		Mockito.when(manager.find(Pais.class, 1l)).thenReturn(paisPadrao);
		Mockito.when(manager.find(Estado.class, 1l)).thenReturn(estadoDoPais);		
		request.setIdEstado(1l);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria bloquear compra com pais e estado não pertecente a compra")
	void teste2() throws Exception {				
		Pais pais2 = new Pais("diferente");
		Estado estadoDeOutroPais = new Estado("estado", pais2);
		Mockito.when(manager.find(Estado.class, 2l)).thenReturn(estadoDeOutroPais);
		
		Mockito.when(manager.find(Pais.class, 1l)).thenReturn(paisPadrao);
		request.setIdEstado(2l);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertTrue(errors.hasFieldErrors("idEstado"));
	}
	
	@Test
	@DisplayName("deveria deixar passar se a compra ta sem estado")
	void teste3() throws Exception {				
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria parar se já tem erro de validacao no fluxo")
	void teste4() throws Exception {				
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		errors.reject("codigoQualquer");
		
		EstadoPertenceAPaisValidator validador = new EstadoPertenceAPaisValidator(
				manager);		
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertEquals("codigoQualquer",errors.getGlobalErrors().get(0).getCode());
	}
	
}
