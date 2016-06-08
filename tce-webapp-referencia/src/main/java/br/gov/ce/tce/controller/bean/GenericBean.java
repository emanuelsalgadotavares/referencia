package br.gov.ce.tce.controller.bean;

import org.apache.log4j.Logger;

public class GenericBean {

	private Logger LOG = null;
	
	public Logger getLogger() {
		if (LOG == null) {
			return Logger.getLogger(this.getClass());
		}
		return LOG;
	}
}
