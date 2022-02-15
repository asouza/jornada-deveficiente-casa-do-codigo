package com.deveficiente.casadocodigov2.compartilhado;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;

/**
 * Implementacao de um buscador de entidades em cima da JPA
 * 
 * @author albertoluizsouza
 *
 */
@Component
public class BuscadorDeEntidadesJPA implements BuscadorDeEntidades {

	private EntityManager manager;

	public BuscadorDeEntidadesJPA(EntityManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public <T> T retornaPorId(Class<T> klass, Long id) {
		T entidade = manager.find(klass, id);
		Assert.state(Objects.nonNull(entidade),
				"A busca pela pela entidade do tipo " + klass
						+ " retornou nulo para o id " + id
						+ ". Isso não deveria acontecer");
		return entidade;
	}

	@Override
	public <T> Optional<T> buscaPorId(Class<T> klass, Long id) {
		return Optional.ofNullable(manager.find(klass, id));
	}

}
