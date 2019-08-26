package com.paypal.dao.entities;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.entity.OrigemArquivo;
import com.paypal.entities.entity.OrigemArquivoPath;
import com.paypal.util.MetaException;

public class OrigemArquivoDaoBean extends GenericDao<OrigemArquivo, Integer> {

	public OrigemArquivoDaoBean() {
		super(OrigemArquivo.class);
	}

	private static final long serialVersionUID = 1L;

	public List<OrigemArquivoPath> carregaPaths(String codigoOrigem) throws MetaException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select new com.paypal.entities.entity.OrigemArquivoPath(op.localOrigem, op.localDestino, op.localPreProcessamento, op.localPreProcessamentoErro ) ");
		sb.append("  from OrigemArquivoPath op ");
		sb.append(" where op.origemArquivo.codigoOrigem = :codigoOrigem ");
		sb.append("   and op.ativo = true ");
		sb.append(" order by op.prioridade ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());
		
		query.setParameter("codigoOrigem", codigoOrigem);
		
		List<OrigemArquivoPath> paths = null;
		try {
			paths = (List<OrigemArquivoPath>) query.getResultList();
			
		} catch (NoResultException ne) {
			return null;
		} catch (Exception e) {
			throw new MetaException(e, e.getMessage());
		}
		
		return paths;
	}
	
	public String carregaCodigoOrigemArquivo(Integer idOrigemArquivo) throws MetaException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select o.codigoOrigem  ");
		sb.append("  from OrigemArquivo o ");
		sb.append(" where o.id = :idOrigemArquivo ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());
		
		query.setParameter("idOrigemArquivo", idOrigemArquivo);
		
		String codigo = null;
		try {
			codigo = (String) query.getSingleResult();
		} catch (NoResultException ne) {
			return null;
		} catch (Exception e) {
			throw new MetaException(e, e.getMessage());
		}
		
		return codigo;
	}
	
}
	