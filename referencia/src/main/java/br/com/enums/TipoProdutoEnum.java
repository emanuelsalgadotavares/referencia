package br.com.enums;

import java.util.Arrays;
import java.util.List;

public enum TipoProdutoEnum {

	ROUPA(1, "Roupa"),
	ALIMENTO(2, "Alimento"),
	ACESSORIO(3, "Acessório"),
	NAO_IDENTIFICADO(99, "Não identificado");
	
	private Integer codigo;
	private String descricao;
	
	private TipoProdutoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoProdutoEnum valueOf(Integer codigo) {
		for (TipoProdutoEnum tipoProduto : TipoProdutoEnum.values()) {
			if (tipoProduto.getCodigo().equals(codigo)) {
				return tipoProduto;
			}
		}
		return TipoProdutoEnum.NAO_IDENTIFICADO;
	}
	
	public static List<TipoProdutoEnum> validValues() {
		return Arrays.asList(ROUPA, ALIMENTO, ACESSORIO);
	}
}
