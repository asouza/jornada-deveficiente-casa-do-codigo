package com.deveficiente.casadocodigov2.fechamentocompra;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CupomValidoWebValidator implements Validator {

	private ValidaCumpomValido validaCupomValido;

	public CupomValidoWebValidator(ValidaCumpomValido validaCupomValido) {
		super();
		this.validaCupomValido = validaCupomValido;
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
		validaCupomValido.valida(request, () -> {
			errors.rejectValue("codigoCupom", null,
					"Este cupom não é mais válido");
		});
	}

}
