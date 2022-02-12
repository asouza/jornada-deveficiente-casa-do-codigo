package com.deveficiente.casadocodigov2.novoautor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
//3
public class CadastroNovoAutor {

	//1
	private AutorRepository autorRepository;

	public CadastroNovoAutor(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}

	@Transactional
	//2
	public Autor executa(@Valid DadosNovoAutor dados) {
		Autor autor = dados.toModel();
		autorRepository.save(autor);
		return autor;
	}

}
