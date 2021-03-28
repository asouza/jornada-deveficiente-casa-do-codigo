package com.deveficiente.casadocodigov2.detalhelivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.deveficiente.casadocodigov2.compartilhado.CustomMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DetalheLivroSiteControllerTest {

	@Autowired
	private CustomMockMvc mvc;

	@Test
	@DisplayName("exibe detalhes do livro corretamente")	
	void teste1() throws Exception {
		mvc.post("/autores", Map.of("nome", "alberto", "email",
				"teste@gmail.com", "descricao", "descricao"));

		mvc.post("/categorias", Map.of("nome", "categoria"));

		BigDecimal valorLivro = new BigDecimal("20");
		String titulo = "titulo";
		String resumo = "resumo";
		String sumario = "sumario";
		int numeroPaginas = 100;
		String isbn = "1234567890";
		String dataPublicacao = LocalDate.now().plusDays(1)
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		mvc.post("/livros",
				Map.of("titulo", titulo, "resumo", resumo, "sumario", sumario,
						"preco", valorLivro.toString(), "numeroPaginas",
						numeroPaginas, "isbn", isbn, "dataPublicacao",
						dataPublicacao, "idCategoria", "1", "idAutor", "1"));

		ResultActions resultado = mvc.get("/produtos/1");

		Map<String, Object> autor = Map.of("nome","alberto","descricao","descricao");
		Map<String, Object> detalheLivro = Map.of(
				"titulo", titulo,
				"isbn",isbn, 
				"numeroPaginas",numeroPaginas,
				"preco", valorLivro.setScale(2),
				"resumo",resumo,
				"sumario", sumario,
				"dataPublicacao", dataPublicacao,
				"autor",autor );
		
		String jsonEsperado = new ObjectMapper()
				.writeValueAsString(detalheLivro);
		resultado.andExpect(MockMvcResultMatchers.content().json(jsonEsperado));

	}
}
