package com.deveficiente.casadocodigov2.cadastrolivro;

public interface BuscadorDeEntidades {

	public <T> T buscaPorId(Class<T> klass, Long id);
}
