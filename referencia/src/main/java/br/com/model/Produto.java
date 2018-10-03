package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.enums.TipoProdutoEnum;

@Entity
@Table(name="PRODUTO", schema="ARCHETYPE")
@SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "SEQ_PRODUTO", schema = "ARCHETYPE", 
allocationSize = 1, initialValue = 1)
public class Produto extends BasicEntity<Long> {
	
	private static final long serialVersionUID = -5482714943328209774L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_PRODUTO")
	@Column(name="id")
	private Long id;
	
	@Column(name="descricao")
	private String descricao;

	@Column(name="preco")
	private Double preco;

	@Column(name="id_tipo_produto")
	private Integer idTipoProduto;
	
	@Transient
	private TipoProdutoEnum tipo;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getIdTipoProduto() {
		if (idTipoProduto == null && tipo != null) {
			idTipoProduto = tipo.getCodigo();
		}
		return idTipoProduto;
	}
	
	public void setIdTipoProduto(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}
	
	public TipoProdutoEnum getTipo() {
		if (tipo == null) {
			tipo = TipoProdutoEnum.valueOf(this.idTipoProduto);
		}
		return tipo;
	}
	
	public void setTipo(TipoProdutoEnum tipo) {
		this.tipo = tipo;
	}
}
