package com.paypal.dao.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Hibernate;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.entity.ArquivoDiario;
import com.paypal.entities.entity.ArquivoFisico;
import com.paypal.entities.entity.ArquivoLojaArq;
import com.paypal.util.DateUtil;
import com.paypal.util.MetaException;

public class ArquivoDiarioDaoBean extends GenericDao<ArquivoDiario, Integer> {

	public ArquivoDiarioDaoBean() {
		super(ArquivoDiario.class);
	}

	private static final long serialVersionUID = 1L;

	public ArquivoDiario existeArquivoDiario(ArquivoFisico arquivoFisico) throws MetaException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select ad ");
		sb.append("  from ArquivoDiario ad ");
		sb.append(" where ad.arquivoFisico = :arquivoFisico ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());
		
		query.setParameter("arquivoFisico", arquivoFisico);
		
		ArquivoDiario arq = null;
		try {
			arq = (ArquivoDiario) query.getSingleResult();
			
			Hibernate.initialize(arq.getArquivoDiarioLojas());
			
		} catch (NoResultException ne) {
			return null;
		} catch (Exception e) {
			throw new MetaException(e, e.getMessage());
		}
		
		return arq;
	}
	
	public List<Integer> buscaArquivoDiarioId(ArquivoLojaArq arqLojaArq, Date data, String nomeArquivo) throws MetaException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select ad.id ");
		sb.append("  from ArquivoDiarioDTO ad ");
		sb.append(" where ad.dataMovimento = :data ");
		if (arqLojaArq != null) {
			sb.append("   and ad.arquivoLojaArq = :arqLojaArq ");
		}
		sb.append("   and ad.nomeArquivo = :nomeArquivo ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());
		
		Calendar hoje = Calendar.getInstance();
		
		hoje.setTime(data);
		
		query.setParameter("data", DateUtil.lowDateTime(hoje.getTime()));
		query.setParameter("nomeArquivo", nomeArquivo);
		if (arqLojaArq != null) {
			query.setParameter("arqLojaArq", arqLojaArq);
		}
		
		List<Integer> arq = null;
		try {
			arq = (List<Integer>) query.getResultList();
		} catch (NoResultException ne) {
			return null;
		} catch (Exception e) {
			throw new MetaException(e, e.getMessage());
		}
		
		return arq;
	}
	
	@Override
	public ArquivoDiario saveOrUpdate(ArquivoDiario entity) throws MetaException {
		
		
		try {
			entity = super.saveOrUpdate(entity);
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
		
		return entity;
	}
	
	public ArquivoDiario saveOrUpdateNoTransaction(ArquivoDiario entity) throws MetaException {
		try {
			return super.saveOrUpdate(entity);
		} catch (Exception e) {
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
	}
	
	public void saveNoTransaction(ArquivoDiario entity) throws MetaException {
		try {
			super.save(entity);
		} catch (Exception e) {
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
	}
	
	@Override
	public void save(ArquivoDiario entity) throws MetaException {
		
		try {
			super.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao salvar arquivo diario");
		}
		
	}
	
	
}
	