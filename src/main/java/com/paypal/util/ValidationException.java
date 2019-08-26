package com.paypal.util;

import java.util.Map;
import java.util.Map.Entry;

public class ValidationException extends MetaException {

	private static final long serialVersionUID = 1L;

	private String codigoErro;
	
	private Map<String, String> mensagems;
	
	public ValidationException(String msg) {
		this(null, msg);
	}
	
	public ValidationException(Exception ex, String msg) {
		super(ex, msg);
	}
	
	public ValidationException(Exception ex, String msg, String codigoErro) {
		this(ex, msg);
		this.codigoErro = codigoErro;
	}
	
	public ValidationException(Exception ex, Map<String, String> mensagens ) {
		this(ex, "");
		this.mensagems = mensagens;
	}

	public String getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(String codigoErro) {
		this.codigoErro = codigoErro;
	}

	public Map<String, String> getMensagems() {
		return mensagems;
	}

	public void setMensagems(Map<String, String> mensagems) {
		this.mensagems = mensagems;
	}
	
	/**
	 * Método que concatena todas as mensagens em uma única string separando cada mensagem com quebra de linha com \n\r
	 * @return String mensagem formatada
	 */
	public String getMensagensFormatadas() {
		
		StringBuilder msg = new StringBuilder();
		
		for (Entry<String, String> mensagem : mensagems.entrySet()) {
			
			msg.append(mensagem.getValue());
			msg.append("\n\r");
			
		}
		
		return msg.toString();
	}
	
}
