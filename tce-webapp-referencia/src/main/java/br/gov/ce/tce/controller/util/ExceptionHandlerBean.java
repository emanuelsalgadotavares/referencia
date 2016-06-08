package br.gov.ce.tce.controller.util;

import static br.gov.ce.tce.controller.util.FacesUtil.addErrorMessage;
import static br.gov.ce.tce.controller.util.FacesUtil.getFacesContext;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.gov.ce.tce.controller.bean.GenericBean;
import br.gov.ce.tce.domain.infra.exception.BusinessException;

/**
 * Exception Handler a nível de controller JSF
 */
@Component(value = "exceptionHandler")
public class ExceptionHandlerBean extends GenericBean {

	public Object handle(ProceedingJoinPoint pjp) throws DataIntegrityViolationException, ConstraintViolationException {
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			Throwable excecaoRaiz = getExcecaoRaiz(e);
			
			String idErro = gerarNumeroErro();
			getLogger().error("Erro " + idErro + ": " + excecaoRaiz.getMessage() , e);

			if (e instanceof BusinessException) {
				BusinessException businessException = (BusinessException) e;
				for (String message : businessException.getMessages()) {
					addErrorMessage(message);
				}
			} else if (e instanceof HibernateException || e instanceof PersistenceException) {
				if (e instanceof OptimisticLockException) {
					addErrorMessage("Outro usuário alterou esse registro, por favor recarregue-o novamente. Erro: "+ idErro);
				} else {
					addErrorMessage("Erro de conexão com a base de dados. Operação cancelada. Erro: " + idErro);
				} 
			} else {
				addErrorMessage("Ocorreu um erro inesperado. Por favor, tente novamente. "
						+ "Se o problema persistir, entre em contato com o suporte técnico informando o número de erro: "
						+ idErro);
			}
			getFacesContext().renderResponse();
		}
		return null;
	}

	private Throwable getExcecaoRaiz(Throwable e) {
		Throwable excecaoRaiz = e;
		while (excecaoRaiz.getCause() != null) {
			excecaoRaiz = excecaoRaiz.getCause();
		}
		return excecaoRaiz;
	}

	private String gerarNumeroErro() {
		String idErro = String.valueOf(System.currentTimeMillis());
		if (idErro.length() > 6) {
			idErro = idErro.substring(idErro.length() - 6, idErro.length());
		}
		return idErro;
	}
}
