package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;

@Service
@Validated
public class RegistraNovaCompra {

	private CompraRepository compraRepository;
	private CupomRepository cupomRepository;
	private BuscadorDeEntidades buscadorDeEntidades;
	private ValidaEstadoPertenceAPais validaEstadoPertenceAPais;
	private ValidaCumpomValido validaCumpomValido;

	public RegistraNovaCompra(CompraRepository compraRepository,
			CupomRepository cupomRepository,
			BuscadorDeEntidades buscadorDeEntidades,
			ValidaEstadoPertenceAPais validaEstadoPertenceAPais,
			ValidaCumpomValido validaCumpomValido) {
		super();
		this.compraRepository = compraRepository;
		this.cupomRepository = cupomRepository;
		this.buscadorDeEntidades = buscadorDeEntidades;
		this.validaEstadoPertenceAPais = validaEstadoPertenceAPais;
		this.validaCumpomValido = validaCumpomValido;
	}

	@Transactional
	public Compra executa(@Valid DadosNovaCompra dados) {
		
		validaCumpomValido.valida(dados, () -> {
			throw new IllegalArgumentException("Neste ponto do fluxo o cupom devia estar valido");
		});
		
		validaEstadoPertenceAPais.valida(dados, () -> {
			throw new IllegalArgumentException("Neste ponto do fluxo a validacao do estado pertencente ao pais deveria estar feita");
		});

		Compra novaCompra = dados.toModel(buscadorDeEntidades, cupomRepository);
		return compraRepository.save(novaCompra);
	}
}
