package com.deveficiente.casadocodigov2.cadastrolivro;

import java.util.Optional;

public interface BuscadorDeEntidades {

	public <T> T retornaPorId(Class<T> klass, Long id);
	
	public <T> Optional<T> buscaPorId(Class<T> klass, Long id);
}
