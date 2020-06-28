package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import com.deveficiente.casadocodigov2.compartilhado.ExistsId;
import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

public class NovaCompraRequest {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String sobrenome;
	@NotBlank
	private String documento;
	@NotBlank
	private String endereco;
	@NotBlank
	private String complemento;
	@NotBlank
	private String cidade;
	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;
	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long idEstado;
	@NotBlank
	private String telefone;
	@NotBlank
	private String cep;

	public NovaCompraRequest(@Email @NotBlank String email,
			@NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco,
			@NotBlank String complemento, @NotBlank String cidade,
			@NotNull Long idPais, Long idEstado, @NotBlank String telefone,
			@NotBlank String cep) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
	}

	public String getDocumento() {
		return documento;
	}

	@Override
	public String toString() {
		return "NovaCompraRequest [email=" + email + ", nome=" + nome
				+ ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento
				+ ", cidade=" + cidade + ", idPais=" + idPais + ", idEstado="
				+ idEstado + ", telefone=" + telefone + ", cep=" + cep + "]";
	}



	public boolean documentoValido() {
		Assert.hasLength(documento,
				"você nao deveria validar o documento se ele não tiver sido preenchido");

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null)
				|| cnpjValidator.isValid(documento, null);
	}

	public Long getIdPais() {
		return idPais;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	/*
	 * 
	 * * email obrigatório e com formato adequado nome obrigatório sobrenome
	 * obrigatório documento(cpf/cnpj) obrigatório e só precisa ser um cpf ou
	 * cnpj endereco obrigatório complemento obrigatório cidade obrigatório pais
	 * obrigatório estado(caso aquele pais tenha estado) - apenas se o país
	 * tiver cadastro de estados telefone obrigatório cep é obrigatório
	 * 
	 */

}
