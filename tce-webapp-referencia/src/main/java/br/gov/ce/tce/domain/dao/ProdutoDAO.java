package br.gov.ce.tce.domain.dao;

import br.gov.ce.tce.domain.entity.Produto;
import br.gov.ce.tce.domain.enums.TipoProdutoEnum;
import java.util.List;

public interface ProdutoDAO extends GenericDAO<Produto> {

	public List<Produto> findByTipo(TipoProdutoEnum tipo);
	
}
