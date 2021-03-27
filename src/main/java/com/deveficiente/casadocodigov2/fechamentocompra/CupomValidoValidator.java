package com.deveficiente.casadocodigov2.fechamentocompra;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;

@Component
public class CupomValidoValidator implements Validator {

	private CupomRepository cupomRepository;

	public CupomValidoValidator(CupomRepository cupomRepository) {
		super();
		this.cupomRepository = cupomRepository;
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

		NovaCompraRequest request = (NovaCompraRequest) target;
		Optional<String> possivelCodigo = request.getCodigoCupom();
		if (possivelCodigo.isPresent()) {
			Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
			Assert.state(Objects.nonNull(cupom),"O código do cupom precisa existir neste ponto da validacao");
			if (!cupom.valido()) {
				errors.rejectValue("codigoCupom", null,
						"Este cupom não é mais válido");
			}
		} 
	}

}
