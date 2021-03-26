package com.deveficiente.casadocodigov2.cadastrocategoria;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assumptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.deveficiente.casadocodigov2.compartilhado.CustomMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.jqwik.api.ForAll;
import net.jqwik.api.Label;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.spring.JqwikSpringSupport;

@JqwikSpringSupport
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriasControllerTest {

	private Set<String> nomesGerados = new HashSet<>();
	@Autowired
	private CustomMockMvc mvc;

	@Property(tries = 10)
	@Label("fluxo de cadastro de novo autor")
	void teste(@ForAll @AlphaChars @StringLength(min = 1, max = 255) String nome,
			@ForAll @AlphaChars @StringLength(min = 1, max = 50) String email,
			@ForAll @AlphaChars @StringLength(min = 1, max = 255) String descricao) throws Exception {
		
		Assumptions.assumeTrue(nomesGerados.add(email));
		
		mvc.post("/categorias",Map.of("nome",nome))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		mvc.post("/categorias",Map.of("nome",nome))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}	
}
