package br.gov.ce.tce.domain.service.sca;

import br.gov.ce.tce.domain.entity.sca.Usuario;
import br.gov.ce.tce.domain.infra.exception.BusinessException;

public interface AuthenticationService {

	public abstract void login(String username, String password) throws BusinessException;

	public abstract void logout();

	public abstract Usuario getUsuarioLogado() throws BusinessException;

}