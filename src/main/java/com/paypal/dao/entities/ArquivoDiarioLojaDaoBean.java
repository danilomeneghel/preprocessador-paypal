package com.paypal.dao.entities;

import com.paypal.dao.generico.GenericDao;
import com.paypal.entities.entity.ArquivoDiarioLoja;
import com.paypal.util.MetaException;

public class ArquivoDiarioLojaDaoBean extends GenericDao<ArquivoDiarioLoja, Integer> {

	public ArquivoDiarioLojaDaoBean() {
		super(ArquivoDiarioLoja.class);
	}

	private static final long serialVersionUID = 1L;

	
	@Override
	public ArquivoDiarioLoja saveOrUpdate(ArquivoDiarioLoja entity) throws MetaException {
		
		
		try {
			entity = super.saveOrUpdate(entity);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
		
		return entity;
	}
	
	@Override
	public void save(ArquivoDiarioLoja entity) throws MetaException {
		
		try {
			super.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
		
	}
	
	
}
	