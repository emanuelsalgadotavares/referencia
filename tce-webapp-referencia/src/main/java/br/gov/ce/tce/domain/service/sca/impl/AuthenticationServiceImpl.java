package br.gov.ce.tce.domain.service.sca.impl;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.gov.ce.tce.domain.entity.sca.Usuario;
import br.gov.ce.tce.domain.infra.exception.BusinessException;
import br.gov.ce.tce.domain.service.sca.AuthenticationService;

@Component
public class AuthenticationServiceImpl implements Serializable, AuthenticationService {

	private static final long serialVersionUID = 1L;
	
	private Logger LOG = null;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	public void login(String username, String password) throws BusinessException {
	
		// autenticando spring security
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authenticate = null;
		
		try {
			 authenticate = authenticationManager.authenticate(token);
		} catch (DisabledException e) {
			getLogger().error("Usuário desabilitado.");
			throw new BusinessException("Usuário desabilitado.");
		} catch (LockedException e) {
			getLogger().error("Usuário bloqueado." );
			throw new BusinessException("Usuário bloqueado.");
		} catch (CredentialsExpiredException e) {
			getLogger().error("Conta expirada.");
			throw new BusinessException("Conta expirada.");
		} catch (UsernameNotFoundException e) {
			getLogger().error("Nenhum usuário com esse login foi encontrado.");
			throw new BusinessException("Nenhum usuário com esse login foi encontrado.");
		} catch (BadCredentialsException e) {
			getLogger().error("Login ou senha inválidos.");
			throw new BusinessException("Login ou senha inválidos.");
		} catch (Exception e) {
			getLogger().error("Erro ao logar.");
			throw new BusinessException("Erro ao logar.");
		}
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		// pergando o usuario
		Usuario usuario = (Usuario) getUsuarioLogado();
		
		// verificando se o usuario esta ativo
		if (!usuario.isEnabled()) {
			throw new BusinessException("Usuário não está ativo.");
		}

		// Verificando se a senha esta expirada
		if (!usuario.isAccountNonExpired()) {
			throw new BusinessException("Conta expirada.");
		}

		// verificando se o usuario tem alguma permissao
		if (usuario.getAuthorities().isEmpty()) {
			throw new BusinessException("Usuário não tem permissão de acesso.");
		}

		// verificando se o usuario esta bloqueado
		if (!usuario.isAccountNonLocked()) {
			throw new BusinessException("Usuário bloqueado.");
		}
		
		// verificando se a senha expirou
		if (!usuario.isCredentialsNonExpired()) {
			throw new BusinessException("Senha expirada.");
		}
		
		// verificando autenticacao
		if (!authenticate.isAuthenticated()) {
			throw new BusinessException("Login ou senha inválidos");
		}
	}

	public void logout() {
		getLogger().info("** LOGOUT **");

		SecurityContextHolder.getContext().setAuthentication(null);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
	}

	public Usuario getUsuarioLogado() throws BusinessException {
		try {
			Authentication context = SecurityContextHolder.getContext().getAuthentication();
			
			if(context != null && context.isAuthenticated()){
				return (Usuario) context.getPrincipal();
			}
		} catch (Exception e) {
			throw new BusinessException("Erro na identificação do usuário logado. Favor efetuar novo login.");
		}
		
		return null;
	}
	
	public Logger getLogger() {
		if (LOG == null) {
			return Logger.getLogger(this.getClass());
		}
		return LOG;
	}
}
