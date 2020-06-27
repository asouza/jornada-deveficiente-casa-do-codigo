package com.deveficiente.casadocodigov2.novoautor;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long>{

	Optional<Autor> findByEmail(String email);

}
