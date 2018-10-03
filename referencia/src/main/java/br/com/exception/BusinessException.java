package br.com.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<String> messages = new ArrayList<String>();

	public BusinessException(String message) {
		super(message);
		if (message == null) {
			throw new IllegalArgumentException("message == null");
		}
		this.messages.add(message);
	}

	public BusinessException(List<String> messages) {
		this.messages = messages;
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		if (message == null || cause == null) {
			throw new IllegalArgumentException("message == null || cause == null");
		}
		this.messages.add(message);
	}

	public List<String> getMessages() {
		return this.messages;
	}

	public void addMessage(String message) {
		if (message != null && !message.trim().isEmpty()) {
			this.messages.add(message);
		}
	}

	@Override
	public String getMessage() {
		if (!this.messages.isEmpty()) {
			String menssagesString = null;
			for (String message : this.messages) {
				if (menssagesString == null) {
					menssagesString = message;
				} else {
					menssagesString += ", " + message;
				}
			}
			return menssagesString;
		} else if (getCause() != null) {
			return getCause().getLocalizedMessage();
		}
		return null;
	}

}
