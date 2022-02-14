package com.deveficiente.casadocodigov2.paisestado;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;

@Service
@Validated
public class CadastraNovoEstado {

	private EstadoRepository estadoRepository;
	private BuscadorDeEntidades buscadorDeEntidades;

	public CadastraNovoEstado(EstadoRepository estadoRepository,
			BuscadorDeEntidades buscadorDeEntidades) {
		super();
		this.estadoRepository = estadoRepository;
		this.buscadorDeEntidades = buscadorDeEntidades;
	}

	@Transactional
	public Estado executa(@Valid DadosNovoEstado dados) {
		Estado novoEstado = dados.toModel(buscadorDeEntidades);
		return estadoRepository.save(novoEstado);
	}

}
