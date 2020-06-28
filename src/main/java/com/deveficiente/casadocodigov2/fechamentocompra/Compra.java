package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

public class Compra {

	private @Email @NotBlank String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String documento;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	@ManyToOne
	private @NotNull Pais pais;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	@ManyToOne
	private Estado estado;

	public Compra(@Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String sobrenome, @NotBlank String documento,
			@NotBlank String endereco, @NotBlank String complemento,
			@NotNull Pais pais, @NotBlank String telefone,
			@NotBlank String cep) {
				this.email = email;
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.pais = pais;
				this.telefone = telefone;
				this.cep = cep;
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "Compra [email=" + email + ", nome=" + nome + ", sobrenome="
				+ sobrenome + ", documento=" + documento + ", endereco="
				+ endereco + ", complemento=" + complemento + ", pais=" + pais
				+ ", telefone=" + telefone + ", cep=" + cep + ", estado="
				+ estado + "]";
	}



	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.notNull(pais,"Não rola associar um estado enquanto o pais for nulo");
		Assert.isTrue(estado.pertenceAPais(pais),"Este estado não é do país associado a compra");
		this.estado = estado;
	}

}
