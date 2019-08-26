package com.paypal.util;

public class LayoutException extends MetaException {

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public LayoutException(Exception ex, String msg) {
		super(ex, msg);
		this.setMsg(msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
