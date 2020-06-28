package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class NovoPedidoRequest {

	@Positive
	@NotNull
	private BigDecimal total;
	@Size(min = 1)
	@Valid
	private List<NovoPedidoItemRequest> itens = new ArrayList<>();

	public NovoPedidoRequest(@Positive @NotNull BigDecimal total,
			@Size(min = 1) @Valid List<NovoPedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}
	
	public List<NovoPedidoItemRequest> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "NovoPedidoRequest [total=" + total + ", itens=" + itens + "]";
	}


}
