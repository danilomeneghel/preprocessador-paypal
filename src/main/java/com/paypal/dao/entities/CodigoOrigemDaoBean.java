package com.paypal.dao.entities;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.externo.CodigoOrigem;
import com.paypal.dao.entities.CodigosDTO;

public class CodigoOrigemDaoBean extends GenericDao<CodigoOrigem, Integer> {

	public CodigoOrigemDaoBean() {
		super(CodigoOrigem.class);
	}

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	/***
	 * Retorna apenas o ID da loja conforme o código de origem passado.
	 * Teoricamente, é para ter sempre apenas uma loja por código de origem para
	 * o adquirente. Porém, GetNet, pode ter mais de um, então tem que passar
	 * também o ID do Terminal. Em casos como o Header do arquivo, não tem como
	 * saber qual é o terminal, então caso retorne mais de uma loja, seja
	 * considerada a primeira retornada.
	 * 
	 * @param codigo
	 * @return IdLoja
	 */
	public Integer findLojaByCodigoOrigem(String codigo, Integer idAdquirente, String idTerminal) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select c.loja.id");
		sb.append(" from CodigoOrigem as c");
		sb.append(" where c.codigoOrigem = :codigo ");
		sb.append(" and c.adquirente.id = :idAdquirente ");
		
		if (idTerminal != null) {
			sb.append(" and c.idTerminal = :idTerminal ");
		}
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("codigo", codigo);
		query.setParameter("idAdquirente", idAdquirente);
		
		if (idTerminal != null) { 
			query.setParameter("idTerminal", idTerminal);
		}
		
		
		List<Integer> loja = null;
		try {
			loja = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}

		if (loja != null && loja.size() > 0) {
			return loja.get(0);
		} else {
			return null;
		}
	}
	
	public List<CodigosDTO> findCodigosOrigem() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select new com.paypal.dao.entities.CodigosDTO(c.loja.id, c.codigoOrigem, l.empresa.id, c.termoAceite) ");
		sb.append("from CodigoOrigem as c , Loja l ");
		sb.append("where c.loja.id = l.id ");
		sb.append("and c.adquirente.codigoAdquirente = 'PP'");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		List<CodigosDTO> codigos = null;
		try {
			codigos = (List<CodigosDTO>)query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return codigos;
	}
	
	public CodigosDTO findEstabelecimento(String codigoOrigem) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select new com.paypal.dao.entities.CodigosDTO(c.loja.id, c.codigoOrigem, c.loja.empresa.id, c.termoAceite)");
		sb.append(" from CodigoOrigem as c");
		sb.append(" where c.adquirente.codigoAdquirente = 'PP' and c.codigoOrigem = :codigoOrigem");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());		
		query.setParameter("codigoOrigem", codigoOrigem);
		
		CodigosDTO codigo = null;
		try {
			codigo = ((CodigosDTO) query.getSingleResult());
		} catch(Exception e){
			e.printStackTrace();
		}
		return codigo;
	}
	
	public Integer findIdLoja(String codigoOrigem) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("select new com.paypal.dao.entities.CodigosDTO(c.loja.id, c.codigoOrigem)");
		sb.append(" from CodigoOrigem as c");
		sb.append(" where c.adquirente.codigoAdquirente = 'PP' and c.codigoOrigem = :codigoOrigem");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());		
		query.setParameter("codigoOrigem", codigoOrigem);

		Integer codigo = 0;
		try {
			codigo = ((CodigosDTO) query.getSingleResult()).getIdLoja();
		} catch (NoResultException e) {
			return 0;
		} catch(Exception e){
			e.printStackTrace();
		}
		return codigo;
	}

	public Integer findIdEmpresa(Integer idLoja) {

		StringBuilder sb = new StringBuilder();
		
		sb.append("select new com.paypal.dao.entities.CodigosDTO(c.codigoOrigem, c.loja.empresa.id)");
		sb.append(" from CodigoOrigem as c");
		sb.append(" where c.adquirente.codigoAdquirente = 'PP' and c.loja.id = :idLoja");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());		
		query.setParameter("idLoja", idLoja);

		Integer codigo = 0;
		try {
			codigo = ((CodigosDTO) query.getSingleResult()).getEmpresa();
		} catch (NoResultException e) {
			return 0;
		} catch(Exception e){
			e.printStackTrace();
		}
		return codigo;
	}

}
	