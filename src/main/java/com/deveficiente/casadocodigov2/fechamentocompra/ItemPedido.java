package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigov2.cadastrolivro.Livro;
import com.deveficiente.casadocodigov2.compartilhado.Generated;

@Embeddable
public class ItemPedido {

	@ManyToOne
	private @NotNull Livro livro;
	private @Positive int quantidade;
	@Positive
	private BigDecimal precoMomento;
	
	@Deprecated
	public ItemPedido() {

	}

	public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoMomento = livro.getPreco();
	}

	@Override
	public String toString() {
		return "ItemPedido [livro=" + livro + ", quantidade=" + quantidade
				+ ", precoMomento=" + precoMomento + "]";
	}
	
	public BigDecimal total() {
		return precoMomento.multiply(new BigDecimal(quantidade));
	}

	@Generated(Generated.ECLIPSE)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		return result;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		return true;
	}

	
	

}
