package br.gov.ce.tce.domain.dao.sca.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.gov.ce.tce.domain.entity.sca.Usuario;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByUsername(username);
	}

	private Usuario findByUsername(String username) {

		try {
			Usuario usuario = (Usuario) entityManager.createNamedQuery("Usuario.findByUsername", Usuario.class)
      				.setParameter("username", username).getSingleResult();

			if (usuario == null || usuario.equals(""))
				return null;

			GrantedAuthority result = new SimpleGrantedAuthority("ROLE_USER");
			usuario.getAuthorities().add(result);
			
			return usuario;

		} catch (NoResultException e) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}

	}

}
