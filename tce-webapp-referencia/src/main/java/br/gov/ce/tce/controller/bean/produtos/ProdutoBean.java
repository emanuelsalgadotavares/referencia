package br.gov.ce.tce.controller.bean.produtos;


import static br.gov.ce.tce.controller.util.FacesUtil.addInfoMessage;
import static br.gov.ce.tce.controller.util.FacesUtil.getFlashParameter;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ce.tce.controller.bean.GenericBean;
import br.gov.ce.tce.domain.entity.Produto;
import br.gov.ce.tce.domain.enums.TipoProdutoEnum;
import br.gov.ce.tce.domain.infra.exception.BusinessException;
import br.gov.ce.tce.domain.service.ProdutoService;

@Controller
@Scope("view")
public class ProdutoBean extends GenericBean {

	@Autowired
	public ProdutoService produtoService;
	
	public Produto produto;
	
	private List<TipoProdutoEnum> tiposProduto;
	
	private Boolean paginaDeCadastro;
	
	@PostConstruct
	public void init() {
		getLogger().info("Executando Init");
		setTiposProduto(TipoProdutoEnum.validValues());
		carregarProduto();
	}

	private void carregarProduto() {
		if (getFlashParameter("produto") != null) {
			setProduto( (Produto) getFlashParameter("produto") );
			setPaginaDeCadastro(false);
		} else {
			setProduto(new Produto());
			setPaginaDeCadastro(true);
		}
	}
	
	public String salvarCadastrarProduto() throws BusinessException {
		getLogger().info("Executando cadastro do produto: " + produto);
		produtoService.cadastrar(produto);
		addInfoMessage("Produto cadastrado com sucesso.");
		return "/paginas/produtos/listarProdutos";
	}
	
	public String salvarAlterarProduto() {
		produtoService.alterarProduto(produto);
		addInfoMessage("Produto alterado com sucesso.");
		return "/paginas/produtos/listarProdutos";
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public List<TipoProdutoEnum> getTiposProduto() {
		return tiposProduto;
	}

	public void setTiposProduto(List<TipoProdutoEnum> tiposProduto) {
		this.tiposProduto = tiposProduto;
	}

	public Boolean getPaginaDeCadastro() {
		return paginaDeCadastro;
	}

	public void setPaginaDeCadastro(Boolean paginaDeCadastro) {
		this.paginaDeCadastro = paginaDeCadastro;
	}
	
}
