package com.paypal.dao.entities;

import com.paypal.dao.generico.GenericDao;
import com.paypal.entities.entity.ArquivoDiarioLojaLog;
import com.paypal.util.MetaException;

public class ArquivoDiarioLojaLogDaoBean extends GenericDao<ArquivoDiarioLojaLog, Integer> {

	public ArquivoDiarioLojaLogDaoBean() {
		super(ArquivoDiarioLojaLog.class);
	}

	private static final long serialVersionUID = 1L;

	
	@Override
	public ArquivoDiarioLojaLog saveOrUpdate(ArquivoDiarioLojaLog entity) throws MetaException {
		
		
		try {
			entity = super.saveOrUpdate(entity);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
		
		return entity;
	}
	
	@Override
	public void save(ArquivoDiarioLojaLog entity) throws MetaException {
		
		try {
			super.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
		
	}
	
	
}
	