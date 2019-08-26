package com.paypal.util;

import java.io.Serializable;

import com.paypal.entities.entity.ArquivoDiarioLoja;
import com.paypal.entities.entity.ArquivoDiarioLojaLog;

public abstract class AbstractEntity<ID extends Serializable> {

	private static final long serialVersionUID = 1L;

	public abstract ID getId();

	public int compareTo(ArquivoDiarioLoja o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int compareTo(ArquivoDiarioLojaLog o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
