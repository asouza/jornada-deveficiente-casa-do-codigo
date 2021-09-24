package com.deveficiente.casadocodigov2.fechamentocompra;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import com.deveficiente.casadocodigov2.cadastrocupom.Cupom;
import com.deveficiente.casadocodigov2.paisestado.Estado;
import com.deveficiente.casadocodigov2.paisestado.Pais;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@OneToOne(mappedBy = "compra",cascade = CascadeType.PERSIST)
	private Pedido pedido;
	@Embedded
	private CupomAplicado cupomAplicado;

	public Compra(@Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String sobrenome, @NotBlank String documento,
			@NotBlank String endereco, @NotBlank String complemento,
			@NotNull Pais pais, @NotBlank String telefone,
			@NotBlank String cep, Function<Compra, Pedido> funcaoCriacaoPedido) {
				this.email = email;
				this.nome = nome;
				this.sobrenome = sobrenome;
				this.documento = documento;
				this.endereco = endereco;
				this.complemento = complemento;
				this.pais = pais;
				this.telefone = telefone;
				this.cep = cep;
				this.pedido = funcaoCriacaoPedido.apply(this);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "Compra [id=" + id + ", email=" + email + ", nome=" + nome
				+ ", sobrenome=" + sobrenome + ", documento=" + documento
				+ ", endereco=" + endereco + ", complemento=" + complemento
				+ ", pais=" + pais + ", telefone=" + telefone + ", cep=" + cep
				+ ", estado=" + estado + ", pedido=" + pedido
				+ ", cupomAplicado=" + cupomAplicado + "]";
	}



	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.notNull(pais,"Não rola associar um estado enquanto o pais for nulo");
		Assert.isTrue(estado.pertenceAPais(pais),"Este estado não é do país associado a compra");
		this.estado = estado;
	}


	public void aplicaCupom(Cupom cupom) {
		Assert.isTrue(cupom.valido(),"Olha o cupom que está sendo aplicado não está mais válido");
		Assert.isNull(cupomAplicado,"Olha você não pode trocar um cupom de uma compra");
		this.cupomAplicado = new CupomAplicado(cupom);
	}

}
