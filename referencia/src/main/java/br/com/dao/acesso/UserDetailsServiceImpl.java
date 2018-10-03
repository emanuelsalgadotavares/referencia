package br.com.dao.acesso;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.entity.acesso.Usuario;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;

	public UserDetails loadUserByUsername(String username) {
		return findByUsername(username);
	}

	private Usuario findByUsername(String username) {
		try {
			Usuario usuario = entityManager.createQuery("SELECT usu FROM Usuario usu WHERE UPPER(usu.username) = UPPER(:username)", Usuario.class)
      				.setParameter("username", username).getSingleResult();

			if (usuario == null)
				return null;

			GrantedAuthority result = new SimpleGrantedAuthority("ROLE_USER");
			usuario.getAuthorities().add(result);

			return usuario;
		} catch (NoResultException e) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}

	}

}
