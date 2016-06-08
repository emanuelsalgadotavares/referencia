package br.gov.ce.tce.controller.util;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

public class FacesUtil {
	
	private static Logger LOG = Logger.getLogger(FacesUtil.class);

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static void addInfoMessage(String message) {
		LOG.info("Adicionando mensagem de INFO no context do JSF. Mensagem: " + message);
		getFacesContext().addMessage(null, new FacesMessage(SEVERITY_INFO, message, message));
	}

	public static void addErrorMessage(String message) {
		LOG.info("Adicionando mensagem de ERROR no context do JSF. Mensagem: " + message);
		getFacesContext().addMessage(null, new FacesMessage(SEVERITY_ERROR, message, message));
	}

	public static void addWarnMessage(String message) {
		LOG.info("Adicionando mensagem de WARN no context do JSF. Mensagem: " + message);
		getFacesContext().addMessage(null, new FacesMessage(SEVERITY_WARN, message, message));
	}

	public static Object getSessionParameter(String name) {
		LOG.info("Obtendo parâmetro de sessão de nome: " + name);
		return getExternalContext().getSessionMap().get(name);
	}

	public static void setSessionParameter(String name, Object value) {
		LOG.info("Setando parâmetro de sessão de [nome, valor] -> [" + name + ", " + value + "]");
		getExternalContext().getSessionMap().put(name, value);
	}

	public static Object getApplicationAttribute(String attribute) {
		LOG.info("Obtendo atributo de aplicação de nome: " + attribute);
		return getExternalContext().getApplicationMap().get(attribute);
	}
	
	public static Object getFlashParameter(String name) {
		LOG.info("Obtendo parâmetro de Flash Scope de nome: " + name);
		return getExternalContext().getFlash().get(name);
	}

	public static void setFlashParameter(String name, Object value) {
		LOG.info("Setando parâmetro de Flash Scope de [nome, valor] -> [" + name + ", " + value + "]");
		getExternalContext().getFlash().put(name, value);
	}
}
