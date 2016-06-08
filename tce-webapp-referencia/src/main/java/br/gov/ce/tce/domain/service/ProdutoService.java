package br.gov.ce.tce.domain.service;

import java.util.List;

import br.gov.ce.tce.domain.entity.Produto;
import br.gov.ce.tce.domain.infra.exception.BusinessException;

public interface ProdutoService {
	
	public List<Produto> findAll();

	public void cadastrar(Produto produto) throws BusinessException;

	public void alterarProduto(Produto produto);

	public void excluirProduto(Produto produto);
}
