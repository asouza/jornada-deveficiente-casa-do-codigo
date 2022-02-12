package com.deveficiente.casadocodigov2.novoautor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service
public class CadastroNovoAutor {

	private AutorRepository autorRepository;

	public CadastroNovoAutor(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}

	@Transactional
	public Autor executa(@Valid NovoAutorRequest request) {
		Autor autor = request.toModel();
		autorRepository.save(autor);
		return autor;
	}

}
