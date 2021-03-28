package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private @NotNull @Valid Compra compra;

	@ElementCollection
	private @Size(min = 1) Set<ItemPedido> itens = new HashSet<>();

	public Pedido(@NotNull @Valid Compra compra,
			@Size(min = 1) Set<ItemPedido> itens) {
		Assert.isTrue(itens.iterator().hasNext(),
				"todo pedido deve ter pelo menos um item");
		this.compra = compra;
		this.itens.addAll(itens);
	}

	public boolean totalIgual(@Positive @NotNull BigDecimal valor) {
		return total().setScale(2).equals(valor.setScale(2));
	}

	@Override
	public String toString() {
		return "Pedido [itens=" + itens + "]";
	}

	public BigDecimal total() {
		return itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO,
				(atual, proximo) -> atual.add(proximo));
	}

}
