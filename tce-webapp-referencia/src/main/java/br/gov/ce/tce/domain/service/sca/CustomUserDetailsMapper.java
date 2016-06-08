package br.gov.ce.tce.domain.service.sca;

import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsMapper {

	@SuppressWarnings("rawtypes")
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authority);
}
