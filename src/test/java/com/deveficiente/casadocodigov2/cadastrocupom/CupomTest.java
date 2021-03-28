package com.deveficiente.casadocodigov2.cadastrocupom;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

public class CupomTest {

	@ParameterizedTest
	@CsvSource({
		"0,true",
		"1,true"				
	})
	void teste1(long valor,boolean resultado) throws Exception {
		Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(valor));
		Assertions.assertEquals(resultado, cupom.valido());
	}
	
	@Test
	@DisplayName("cupom com data passada nao eh mais valido")
	void teste2() throws Exception {
		Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1));
		ReflectionTestUtils.setField(cupom, "validade", LocalDate.now().minusDays(1));
		Assertions.assertFalse(cupom.valido());
	}
	
	@Test
	@DisplayName("não pode criar cupom com data no passado")
	void teste3() throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Cupom("codigo", BigDecimal.TEN, LocalDate.now().minusDays(1));			
		});
		
	}
}
