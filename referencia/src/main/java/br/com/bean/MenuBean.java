package br.com.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("session")
public class MenuBean extends GenericBean implements Serializable {
	
	private MenuModel model;
	
	@PostConstruct
	public void init() {
		getLogger().info("Executando Init");
		criaMenuModel();
	}

	private void criaMenuModel() {
		getLogger().info("Criando MenuModel");
		model= new DefaultMenuModel();

		DefaultMenuItem item = new DefaultMenuItem("Início", "ui-icon-home", "/paginas/home.xhtml");
		model.addElement(item);
		
		DefaultSubMenu submenu = new DefaultSubMenu("Produtos");
		item = new DefaultMenuItem("Listar");
		item.setUrl("/paginas/produtos/listarProdutos.xhtml");
		submenu.addElement(item);

		item = new DefaultMenuItem("Cadastrar");
		item.setUrl("/paginas/produtos/cadastrarAlterarProduto.xhtml");
		submenu.addElement(item);
		
		
		model.addElement(submenu);
	}

	public MenuModel getModel() {
		return model;
	}
}
