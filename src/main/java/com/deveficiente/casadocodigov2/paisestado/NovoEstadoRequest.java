package com.deveficiente.casadocodigov2.paisestado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.casadocodigov2.compartilhado.ExistsId;
import com.deveficiente.casadocodigov2.compartilhado.Generated;
import com.deveficiente.casadocodigov2.compartilhado.UniqueValue;

public class NovoEstadoRequest {

	@NotBlank
	@UniqueValue(domainClass = Estado.class,fieldName = "nome")
	private String nome;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "NovoEstadoRequest [nome=" + nome + ", idPais=" + idPais + "]";
	}

	public Estado toModel(EntityManager manager) {
		return new Estado(nome, manager.find(Pais.class, idPais));
	}

}
