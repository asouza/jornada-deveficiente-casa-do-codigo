package com.deveficiente.casadocodigov2.fechamentocompra;

import org.springframework.stereotype.Repository;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;

@Repository
public interface CupomRepository
		extends org.springframework.data.repository.Repository<Cupom, Long> {

	/**
	 * Busca por um cupom que existe no sistema
	 * @param codigo
	 * @return
	 */
	public Cupom getByCodigo(String codigo);
}
