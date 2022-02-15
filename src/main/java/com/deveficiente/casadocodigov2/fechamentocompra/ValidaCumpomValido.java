package com.deveficiente.casadocodigov2.fechamentocompra;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;

@Component
public class ValidaCumpomValido {

	private CupomRepository cupomRepository;

	public ValidaCumpomValido(CupomRepository cupomRepository) {
		super();
		this.cupomRepository = cupomRepository;
	}

	public void valida(DadosNovaCompra dados, Runnable handlerErro) {
		Optional<String> possivelCodigo = dados.getCodigoCupom();
		if (possivelCodigo.isPresent()) {
			Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
			Assert.state(Objects.nonNull(cupom),
					"O código do cupom precisa existir neste ponto da validacao");
			if (!cupom.valido()) {
				handlerErro.run();
			}
		}
	}

}
