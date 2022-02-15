package com.deveficiente.casadocodigov2.fechamentocompra;

import java.util.Optional;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;

public interface DadosNovaCompra {

	Compra toModel(BuscadorDeEntidades buscadorDeEntidades,
			CupomRepository cupomRepository);

	boolean temEstado();

	Long getIdPais();

	Long getIdEstado();

	Optional<String> getCodigoCupom();

}
