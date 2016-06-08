package br.gov.ce.tce.domain.dao.impl;

import br.gov.ce.tce.domain.entity.Produto;
import br.gov.ce.tce.domain.enums.TipoProdutoEnum;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.ce.tce.domain.dao.ProdutoDAO;

@Repository
public class ProdutoDAOImpl extends GenericDAOImpl<Produto> implements ProdutoDAO {

	@Override
	public List<Produto> findByTipo(TipoProdutoEnum tipo) {
		// TODO Auto-generated method stub
		return null;
	}

}
