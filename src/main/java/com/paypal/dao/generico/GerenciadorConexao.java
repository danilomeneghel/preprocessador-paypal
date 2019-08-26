package com.paypal.dao.generico;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GerenciadorConexao {
	
	private static Map<String, String> persistenceMap = null;
	
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
			
		try {
			return pegarConexao();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static EntityManager pegarConexao() throws IOException {
		
		if (entityManager != null) {
			return entityManager;
		}
		
		EntityManagerFactory managerFactory = null;
		
		try {			
			
			if(persistenceMap == null) {
				persistenceMap = new HashMap<String, String>();
			}
				
			if(persistenceMap.isEmpty()) {
				String dirConf = System.getProperty("pre_processador_path");    		    
			    Properties props = new Properties();
				InputStream arquivoConfig = new FileInputStream(dirConf + "/db.properties");		
	        	props.load(arquivoConfig);
			    if(props.containsKey("persistence.url") &&  props.containsKey("persistence.user") && props.containsKey("persistence.password")) {
			    	persistenceMap.put("javax.persistence.jdbc.url", props.getProperty("persistence.url"));
				    persistenceMap.put("javax.persistence.jdbc.user", props.getProperty("persistence.user"));
				    persistenceMap.put("javax.persistence.jdbc.password", props.getProperty("persistence.password"));
			    }
			}
		    						
			managerFactory = Persistence.createEntityManagerFactory("paypal", persistenceMap);
		} catch (IOException e) {
			throw e;
		}
		entityManager =  managerFactory.createEntityManager();
		
		return entityManager;
    }
	
	public static void beginTransaction() {
		if (getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().rollback();
		}
		getEntityManager().getTransaction().begin();  
	}
	public static void commitTransaction() {
		if (getEntityManager().getTransaction().isActive()) {
			getEntityManager().flush();
			getEntityManager().getTransaction().commit();
		}
	}
	public static void rollbackTransaction() {
		if (getEntityManager().getTransaction().isActive()) {
			getEntityManager().getTransaction().rollback();
		}
	}
	public static void close() {
		getEntityManager().close();
	}
	
}

