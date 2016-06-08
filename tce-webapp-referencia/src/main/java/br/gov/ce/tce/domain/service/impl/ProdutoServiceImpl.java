package br.gov.ce.tce.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.ce.tce.domain.dao.ProdutoDAO;
import br.gov.ce.tce.domain.entity.Produto;
import br.gov.ce.tce.domain.enums.TipoProdutoEnum;
import br.gov.ce.tce.domain.infra.exception.BusinessException;
import br.gov.ce.tce.domain.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDAO produtoDao;

	@Override
	public List<Produto> findAll() {
		return produtoDao.findAll();
	}

	@Override
	public void cadastrar(Produto produto) throws BusinessException {

		validarProduto(produto);
		
		produto.setIdTipoProduto(produto.getTipo().getCodigo());
		produtoDao.save(produto);
	}

	private void validarProduto(Produto produto) throws BusinessException {
		//TODO - yuri - refatorar
		if (produto.getTipo().equals(TipoProdutoEnum.ALIMENTO) && produto.getPreco() > 1000D) {
			throw new BusinessException("Alimentos não podem custar mais que R$ 1.000,00");
		}
	}

	@Override
	public void alterarProduto(Produto produto) {
		produtoDao.update(produto);
	}

	@Override
	public void excluirProduto(Produto produto) {
		produtoDao.delete(produto);
	}
}
