package com.deveficiente.casadocodigov2.cadastrocupom;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigov2.compartilhado.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovoCupomRequest {

	@NotBlank
	@UniqueValue(domainClass = Cupom.class,fieldName = "codigo")
	private String codigo;
	@Positive
	@NotNull
	private BigDecimal percentualDesconto;
	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	public NovoCupomRequest(@NotBlank String codigo,
			@Positive @NotNull BigDecimal percentualDesconto) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
	}
	
	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Cupom toModel() {
		return new Cupom(codigo,percentualDesconto,validade);
	}

}
