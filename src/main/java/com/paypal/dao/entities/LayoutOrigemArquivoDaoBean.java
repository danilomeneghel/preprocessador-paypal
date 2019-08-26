package com.paypal.dao.entities;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.entity.LayoutOrigemArquivo;
import com.paypal.entities.entity.LayoutTipoRegistro;

public class LayoutOrigemArquivoDaoBean extends GenericDao<LayoutOrigemArquivo, Integer> {

	public LayoutOrigemArquivoDaoBean() {
		super(LayoutOrigemArquivo.class);
	}

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<LayoutOrigemArquivo> findLayoutsByIdAdquirente(String codigoOrigem) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select l ");
		sb.append("  from LayoutOrigemArquivo as l ");
		sb.append(" where l.origemArquivoArq.origemArquivo.codigoOrigem = :codigoOrigem ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("codigoOrigem", codigoOrigem);
		
		List<LayoutOrigemArquivo> layout = (List<LayoutOrigemArquivo>) query.getResultList();
			
		if (layout != null) {
			for (LayoutOrigemArquivo layoutOrigemArquivo : layout) {
				Hibernate.initialize(layoutOrigemArquivo.getTiposRegistros());
				
				if (layoutOrigemArquivo.getTiposRegistros() != null) {
					for (LayoutTipoRegistro tipo : layoutOrigemArquivo.getTiposRegistros()) {
						Hibernate.initialize(tipo.getInformacoes());
					}
				}
			}
		}
		
		return layout;
	}
	
}
	