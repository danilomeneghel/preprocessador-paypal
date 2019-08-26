package com.paypal.dao.entities;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.paypal.dao.generico.GerenciadorConexao;

public class ConfiguracaoPaypalDaoBean {
	
	public String findClientID() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("select valor from cad_configuracao ");
		sb.append("where codigo = 'PAYPAL_ID'");
		
		Query query = GerenciadorConexao.getEntityManager().createNativeQuery(sb.toString());
			
		String valor = null;
		try {
			valor = (String) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return valor;
	}
	
	public String findSecretID() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select valor from cad_configuracao ");
		sb.append("where codigo = 'PAYPAL_SECRET'");
		
		Query query = GerenciadorConexao.getEntityManager().createNativeQuery(sb.toString());
		
		String valor = null;
		try {
			valor = (String) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return valor;
	}

}
