package com.deveficiente.casadocodigov2.paisestado;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CadastraNovoPais {

	private PaisRepository paisRepository;

	public CadastraNovoPais(PaisRepository paisRepository) {
		super();
		this.paisRepository = paisRepository;
	}

	@Transactional
	public Pais executa(@Valid DadosNovoPais dados) {
		Pais novoPais = new Pais(dados.getNome());
		return paisRepository.save(novoPais);		
	}

}
