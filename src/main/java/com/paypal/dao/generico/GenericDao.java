package com.paypal.dao.generico;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.paypal.util.AbstractEntity;

public class GenericDao <T extends AbstractEntity<ID>, ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = -8796671076491941955L;

	private Class<T> entityClass;
	
	public GenericDao(Class<T> clazz) {
		this.entityClass = clazz;
	}
	
	public Session getSession() {
		return GerenciadorConexao.getEntityManager().unwrap(org.hibernate.Session.class);
	}
	
	public T findById(ID id) throws Exception {
		try {
			GerenciadorConexao.beginTransaction();
			T t = (T) GerenciadorConexao.getEntityManager().find(entityClass, id);
			GerenciadorConexao.commitTransaction();
//			GerenciadorConexao.close();
			return t;
		} catch (final HibernateException ex) {
			throw ex;
		}
	}
	
	/***
	 * Faz merge
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public T saveOrUpdate(T entity) throws Exception {
		try {
			entity = GerenciadorConexao.getEntityManager().merge(entity);
			GerenciadorConexao.getEntityManager().flush();
			GerenciadorConexao.getEntityManager().clear();
			return entity;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/***
	 * Só insere
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void save(T entity) throws Exception {
		try {
			GerenciadorConexao.getEntityManager().persist(entity);
			GerenciadorConexao.getEntityManager().flush();
			GerenciadorConexao.getEntityManager().clear();
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/***
	 * Exclui entidade
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void remove(T entity) throws Exception {
		try {
			GerenciadorConexao.getEntityManager().remove(entity);
			GerenciadorConexao.getEntityManager().flush();
			GerenciadorConexao.getEntityManager().clear();
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
