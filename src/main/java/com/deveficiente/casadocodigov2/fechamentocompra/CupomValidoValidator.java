package com.deveficiente.casadocodigov2.fechamentocompra;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;

@Component
public class CupomValidoValidator implements Validator{
	
	@Autowired
	private CupomRepository cupomRepository;
	

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
		Optional<String> possivelCodigo = request.getCodigoCupom();
		if(possivelCodigo.isPresent()) {
			Cupom cupom = cupomRepository.getByCodigo(possivelCodigo.get());
			if(!cupom.valido()) {
				errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
			}
		}
	}

}
