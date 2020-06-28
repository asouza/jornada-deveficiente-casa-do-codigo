package com.deveficiente.casadocodigov2.fechamentocompra;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerificaDocumentoCpfCnpjValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		NovaCompraRequest request = (NovaCompraRequest) target;
		if(!request.documentoValido()) {
			errors.rejectValue("documento",null, "documento precisa ser cpf ou cnpj");
		}
	}

}
