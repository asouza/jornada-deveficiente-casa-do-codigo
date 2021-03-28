package com.deveficiente.casadocodigov2.fechamentocompra;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigov2.cadastrolivro.Livro;
import com.deveficiente.casadocodigov2.compartilhado.ExistsId;
import com.deveficiente.casadocodigov2.compartilhado.Generated;

public class NovoPedidoItemRequest {

	@NotNull
	@ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long idLivro;
	@Positive
	private int quantidade;

	public NovoPedidoItemRequest(@NotNull Long idLivro,
			@Positive int quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}
	
	public Long getIdLivro() {
		return idLivro;
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "NovoPedidoItemRequest [idLivro=" + idLivro + ", quantidade="
				+ quantidade + "]";
	}

	public ItemPedido toModel(EntityManager manager) {
		@NotNull Livro livro = manager.find(Livro.class, idLivro);
		return new ItemPedido(livro,quantidade);
	}
	
}
