package br.gov.ce.tce.controller.bean;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.ce.tce.domain.entity.sca.Usuario;
import br.gov.ce.tce.domain.infra.exception.BusinessException;
import br.gov.ce.tce.domain.service.sca.AuthenticationService;

@SuppressWarnings("serial")
@Component("loginBean")
@Scope("request")
public class LoginBean extends GenericBean implements Serializable {

	@Autowired
	AuthenticationService authenticationService;

	private String login;
	private String senha;
	
	public String autenticar() throws BusinessException {

		getLogger().info("Iniciando autenticacao...");
		
		authenticationService.login(login, senha);	
		
		getLogger().info("Autenticacao feita com sucesso. redirecionando para home...");	
		
		return "/paginas/home.xhtml?faces-redirect=true";
	}


	public String logout() {
		getLogger().info("logout");
		
		authenticationService.logout();
		
		return "/login.xhtml?faces-redirect=true";
	}

	/**
	 * Retorna a URL Home da aplicação
	 * @return
	 */
	public String home() {
		return "/paginas/home.xhtml?faces-redirect=true";
	}

	public Usuario getUsuarioLogado() throws BusinessException {
		return authenticationService.getUsuarioLogado();
	}
 
	public Boolean anyGranted(String value) throws BusinessException {
		return authenticationService.getUsuarioLogado().anyGranted(value);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
