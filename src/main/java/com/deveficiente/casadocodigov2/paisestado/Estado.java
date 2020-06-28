package com.deveficiente.casadocodigov2.paisestado;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	@ManyToOne
	private @NotNull @Valid Pais pais;
	
	@Deprecated
	public Estado() {

	}

	public Estado(@NotBlank String nome, @NotNull @Valid Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}

	public boolean pertenceAPais(Pais pais) {
		return this.pais.equals(pais);
	}
	
	

}
