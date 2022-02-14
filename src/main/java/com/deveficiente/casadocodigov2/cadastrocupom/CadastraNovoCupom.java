package com.deveficiente.casadocodigov2.cadastrocupom;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.deveficiente.casadocodigov2.fechamentocompra.CupomRepository;

@Service
@Validated
public class CadastraNovoCupom {

	private CupomRepository cupomRepository;

	public CadastraNovoCupom(CupomRepository cupomRepository) {
		super();
		this.cupomRepository = cupomRepository;
	}

	public Cupom executa(@Valid DadosNovoCupom dados) {
		Cupom novoCupom = dados.toModel();
		return cupomRepository.save(novoCupom);
	}

}
