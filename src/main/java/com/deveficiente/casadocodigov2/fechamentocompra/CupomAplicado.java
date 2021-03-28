package com.deveficiente.casadocodigov2.fechamentocompra;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;
import com.deveficiente.casadocodigov2.compartilhado.Generated;

@Embeddable
public class CupomAplicado {

	@ManyToOne
	private Cupom cupom;
	@Positive
	@NotNull
	private BigDecimal percentualDescontoMomento;
	@NotNull
	@Future
	private LocalDate validadeMomento;
	
	@Deprecated
	@Generated(Generated.ECLIPSE)
	public CupomAplicado() {

	}

	public CupomAplicado(Cupom cupom) {
		this.cupom = cupom;
		this.percentualDescontoMomento = cupom.getPercentualDesconto();
		this.validadeMomento = cupom.getValidade();
	}

	@Override
	@Generated(Generated.ECLIPSE)
	public String toString() {
		return "CupomAplicado [cupom=" + cupom + ", percentualDescontoMomento="
				+ percentualDescontoMomento + ", validadeMomento="
				+ validadeMomento + "]";
	}
	
	

	
}
