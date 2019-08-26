package com.paypal.util;

public class MetaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public MetaException(Exception ex, String msg) {
		super(ex);
		this.setMsg(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
