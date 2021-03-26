package com.deveficiente.casadocodigov2.novoautor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.Email;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class AutoresControllerTest {
	
	@Autowired
	private MockMvc mvc;
	private static Set<String> emailsGerados = new HashSet<>();

	@Property(tries = 10)
	@Label("fluxo de cadastro de novo autor")
	void teste(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String nome,
			@ForAll @AlphaChars @StringLength(min = 1, max = 50) String email,
			@ForAll @AlphaChars @StringLength(min = 1, max = 255) String descricao) throws Exception {
		
		Assumptions.assumeTrue(emailsGerados.add(email));
		
		String payload = new ObjectMapper()
				.writeValueAsString(
						Map.of("nome",nome,
							   "email",email+"@gmail.com",
							   "descricao",descricao));
				
		
		MockHttpServletRequestBuilder conteudo = MockMvcRequestBuilders.post("/autores")
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(payload);
		mvc.perform(
				conteudo)
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		
		mvc.perform(
				conteudo)
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
}
