package br.gov.ce.tce.controller.bean.produtos;


import static br.gov.ce.tce.controller.util.FacesUtil.addInfoMessage;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.ce.tce.controller.bean.GenericBean;
import br.gov.ce.tce.controller.util.FacesUtil;
import br.gov.ce.tce.domain.entity.Produto;
import br.gov.ce.tce.domain.enums.TipoProdutoEnum;
import br.gov.ce.tce.domain.service.ProdutoService;

@Controller
@Scope("view")
public class ProdutoListBean extends GenericBean {

	@Autowired
	public ProdutoService produtoService;
	
	private List<Produto> produtos;
	
	public Produto produto;
	
	private List<TipoProdutoEnum> tiposProduto;
	
	@PostConstruct
	public void init() {
		getLogger().info("Executando Init");
		setTiposProduto(TipoProdutoEnum.validValues());
	}
	
	public List<Produto> getProdutos() {
		getLogger().info("Retornando todos os produtos");
		if (produtos == null) {
			setProdutos(produtoService.findAll());
		}
		return produtos;
	}
	
	public String salvarExcluirProduto(Produto produto) {
		getLogger().info("Excluindo produto: " + produto);
		produtoService.excluirProduto(produto);
		getProdutos().remove(produto);
		addInfoMessage("Produto excluído com sucesso.");
		return "/paginas/produtos/listarProdutos";
	}
	
	public String alterarProduto(Produto produto) {
		getLogger().info("Encaminhando para página de alteração do produto: " + produto);
		FacesUtil.setFlashParameter("produto", produto);
		return "/paginas/produtos/cadastrarAlterarProduto";
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

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
