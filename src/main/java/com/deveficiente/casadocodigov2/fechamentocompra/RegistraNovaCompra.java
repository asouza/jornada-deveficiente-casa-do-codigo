package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.deveficiente.casadocodigov2.cadastrolivro.BuscadorDeEntidades;

@Service
@Validated
public class RegistraNovaCompra {

	private CompraRepository compraRepository;
	private CupomRepository cupomRepository;
	private BuscadorDeEntidades buscadorDeEntidades;

	public RegistraNovaCompra(CompraRepository compraRepository,
			CupomRepository cupomRepository,
			BuscadorDeEntidades buscadorDeEntidades) {
		super();
		this.compraRepository = compraRepository;
		this.cupomRepository = cupomRepository;
		this.buscadorDeEntidades = buscadorDeEntidades;
	}

	@Transactional
	public Compra executa(@RequestBody @Valid DadosNovaCompra dados) {

		Compra novaCompra = dados.toModel(buscadorDeEntidades, cupomRepository);
		return compraRepository.save(novaCompra);
	}
}
