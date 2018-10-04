package br.com.dao.acesso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.model.acesso.Role;
import br.com.model.acesso.User;
import br.com.service.RoleService;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private RoleService roleService;

	public UserDetails loadUserByUsername(String username) {
		return findByUsername(username);
	}

	private User findByUsername(String username) {
		try {
			User usuario = entityManager.createQuery("SELECT usu FROM Usuario usu WHERE UPPER(usu.username) = UPPER(:username)", User.class)
      				.setParameter("username", username).getSingleResult();

			if(usuario == null)
				return null;

			List<Role> roles = roleService.findRoleListByUser(usuario);

			for(Role role : roles) 
				usuario.getAuthorities().add(new SimpleGrantedAuthority(role.getNome()));

			GrantedAuthority result = new SimpleGrantedAuthority("ROLE_USER");
			usuario.getAuthorities().add(result);

			return usuario;
		} catch (NoResultException e) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
	}
}
