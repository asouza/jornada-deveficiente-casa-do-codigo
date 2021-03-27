package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

@Component
public class EstadoPertenceAPaisValidator implements Validator {

	private EntityManager manager;

	public EstadoPertenceAPaisValidator(EntityManager manager) {
		super();
		this.manager = manager;
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

		if (request.temEstado()) {
			Pais pais = manager.find(Pais.class, request.getIdPais());
			Estado estado = manager.find(Estado.class, request.getIdEstado());
			if (!estado.pertenceAPais(pais)) {
				errors.rejectValue("idEstado", null, "este estado não é o do país selecionado");
			}
		}

	}

}
