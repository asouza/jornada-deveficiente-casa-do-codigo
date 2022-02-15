package com.deveficiente.casadocodigov2.fechamentocompra;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EstadoPertenceAPaisWebValidator implements Validator {

	private ValidaEstadoPertenceAPais validaEstadoPertenceAPais;

	public EstadoPertenceAPaisWebValidator(
			ValidaEstadoPertenceAPais validaEstadoPertenceAPais) {
		super();
		this.validaEstadoPertenceAPais = validaEstadoPertenceAPais;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		DadosNovaCompra request = (DadosNovaCompra) target;

		validaEstadoPertenceAPais.valida(request, () -> {
			//aqu sobrou acoplamento... Como eu sei que é o idEstado?
			errors.reject(null,
					"este estado não é o do país selecionado");
		});

	}

}
