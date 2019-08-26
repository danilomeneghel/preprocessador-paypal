package com.paypal.dao.entities;

import javax.persistence.NoResultException;

import org.hibernate.Hibernate;

import com.paypal.dao.generico.GenericDao;
import com.paypal.entities.entity.ArquivoLoja;

public class ArquivoLojaDaoBean extends GenericDao<ArquivoLoja, Integer> {

	public ArquivoLojaDaoBean() {
		super(ArquivoLoja.class);
	}

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	/***
	 * Pesquisa ArquivoLoja a partir do Id da loja e idOrigemArquivo.
	 * @param idLoja
	 * @param idOrigemArquivo
	 * @return ArquivoLoja
	 */
	public ArquivoLoja findArquivoLoja(Integer idLoja, Integer idOrigemArquivo) throws Exception {
				
		StringBuilder sb = new StringBuilder();
		
		sb.append("select ar ");
		sb.append("from ArquivoLoja as ar ");
		sb.append("where ar.idLoja = :idLoja ");
		sb.append("and ar.idOriArq = :idOriArq ");
				
		org.hibernate.Query query = getSession().createQuery(sb.toString());

		query.setParameter("idLoja", idLoja);
		query.setParameter("idOriArq", idOrigemArquivo);
				
		ArquivoLoja arquivoLojaDTO = null;
		try {
			arquivoLojaDTO = (ArquivoLoja) query.uniqueResult();
			
			if (arquivoLojaDTO != null) {
				Hibernate.initialize(arquivoLojaDTO.getArquivoLojaArq());
			}
			
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
		return arquivoLojaDTO;
	}

	@Override
	public ArquivoLoja saveOrUpdate(ArquivoLoja entity) throws Exception {
	
		ArquivoLoja arq = null;
		try {
			arq = super.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return arq;
	}
	
	public ArquivoLoja saveDados(ArquivoLoja entity) throws Exception {
		
		try {
			entity = this.saveOrUpdate(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return entity;
	}
	
	
}
	