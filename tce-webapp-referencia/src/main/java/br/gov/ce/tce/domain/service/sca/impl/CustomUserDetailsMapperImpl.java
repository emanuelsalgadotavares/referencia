package br.gov.ce.tce.domain.service.sca.impl;

import java.util.Collection;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Service;

import br.gov.ce.tce.domain.entity.sca.Usuario;
import br.gov.ce.tce.domain.service.sca.CustomUserDetailsMapper;
import br.gov.ce.tce.domain.service.sca.UsuarioService;

@Service("customUserDetailsMapper")
public class CustomUserDetailsMapperImpl extends LdapUserDetailsMapper implements CustomUserDetailsMapper{

	@Autowired
	private UsuarioService usuarioService;

	@SuppressWarnings({ "rawtypes" })
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authority) {
		
		try {
			
			Usuario usuario = usuarioService.findByUsername(username);

			// verificando a lista de usuarios
			if (usuario == null || usuario.equals(""))
				return null;

			GrantedAuthority result = new SimpleGrantedAuthority("ROLE_USER");
			usuario.getAuthorities().add(result);
						
			return usuario;

		} catch (NoResultException e) {
			throw new UsernameNotFoundException("Usuario nao encontrado.");
		}

	}

}
