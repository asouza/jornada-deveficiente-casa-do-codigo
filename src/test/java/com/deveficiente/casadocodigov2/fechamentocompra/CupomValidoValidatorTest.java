package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;
import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

public class CupomValidoValidatorTest {

	private CupomRepository cupomRepository  = Mockito.mock(CupomRepository.class);
	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 10));
	private NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(BigDecimal.TEN, itens);
	private NovaCompraRequest request = new NovaCompraRequest("email@email.com", "nome",
			"sobrenome", "8967548654", "endereco", "complemento", "cidade",
			1l, "987454778", "54534534", pedidoRequest);
	
	@Test
	@DisplayName("deveria parar caso o cupom esteja invalido")
	void teste1() throws Exception {
		Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		//o cupom precisa ficar com uma validade para trás do dia de hoje
		ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().minusDays(1));
		
		request.setCodigoCupom("codigo");
		Mockito.when(cupomRepository.getByCodigo("codigo")).thenReturn(cupom);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		CupomValidoValidator validador = new CupomValidoValidator(cupomRepository);
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertEquals("Este cupom não é mais válido",errors.getFieldError("codigoCupom").getDefaultMessage());
	}
	
	@Test
	@DisplayName("deveria passar caso o cupom esteja válido")
	void teste2() throws Exception {
		Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		
		request.setCodigoCupom("codigo");
		Mockito.when(cupomRepository.getByCodigo("codigo")).thenReturn(cupom);
		
		
		Errors errors = new BeanPropertyBindingResult(request , "target");
		CupomValidoValidator validador = new CupomValidoValidator(cupomRepository);
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria passar caso nao tenha codigo de cupom")
	void teste3() throws Exception {
		Errors errors = new BeanPropertyBindingResult(request , "target");
		CupomValidoValidator validador = new CupomValidoValidator(cupomRepository);
		validador.validate(request, errors);
		
		Assertions.assertFalse(errors.hasErrors());
	}
	
	@Test
	@DisplayName("deveria parar caso já tenha erro")
	void teste4() throws Exception {
		Errors errors = new BeanPropertyBindingResult(request , "target");
		errors.reject("codigo");
		
		CupomValidoValidator validador = new CupomValidoValidator(cupomRepository);
		validador.validate(request, errors);
		
		Assertions.assertTrue(errors.getAllErrors().size() == 1);
		Assertions.assertEquals("codigo",errors.getGlobalErrors().get(0).getCode());
	}	
	
}
