package com.deveficiente.casadocodigov2.fechamentocompra;

import org.springframework.stereotype.Component;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;
import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

@Component
public class ValidaEstadoPertenceAPais {

	private BuscadorDeEntidades buscadorDeEntidades;

	public ValidaEstadoPertenceAPais(BuscadorDeEntidades buscadorDeEntidades) {
		super();
		this.buscadorDeEntidades = buscadorDeEntidades;
	}

	public void valida(DadosNovaCompra dados, Runnable handlerErro) {
		if (dados.temEstado()) {
			Pais pais = buscadorDeEntidades.retornaPorId(Pais.class, dados.getIdPais());
			Estado estado = buscadorDeEntidades.retornaPorId(Estado.class, dados.getIdEstado());
			if (!estado.pertenceAPais(pais)) {
				handlerErro.run();
			}
		}		
	}

}
