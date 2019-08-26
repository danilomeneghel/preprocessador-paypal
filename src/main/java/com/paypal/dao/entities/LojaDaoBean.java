package com.paypal.dao.entities;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.externo.Loja;

//import br.com.auttar.preprocessador.entities.EmpresaRedeDTO;

public class LojaDaoBean extends GenericDao<Loja, Integer> {

	public LojaDaoBean() {
		super(Loja.class);
	}

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	/***
	 * Retorna apenas o ID da loja conforme o código de origem passado.
	 * @param codigo
	 * @return IdLoja
	 */
	/*public EmpresaRedeDTO findEmpresaRedeByIdLoja(Integer idLoja) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select new br.com.auttar.preprocessador.entities.EmpresaRedeDTO(l.empresa.id, l.empresa.rede.id) ");
		sb.append("  from Loja as l");
		sb.append(" where l.id = :idLoja ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("idLoja", idLoja);
		
		EmpresaRedeDTO empresaRede = null;
		try {
			empresaRede = (EmpresaRedeDTO) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
		return empresaRede;
	}*/

	/***
	 * Retorna apenas o ID da loja conforme o código de origem passado.
	 * @param codigo
	 * @return IdLoja
	 */
	public Integer findLojaByCnpj(String cnpj) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select l.id ");
		sb.append("  from Loja as l");
		sb.append(" where l.cnpj = :cnpj ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("cnpj", cnpj);
		
		
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
	
	public Integer findLojaByEmpresa(String loja, String empresa) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		//sb.append("select l.id ");
		//sb.append("  from Loja as l");
		//sb.append(" where l.cnpj = :cnpj ");
		
		sb.append("select l.id ");
		sb.append("  from Loja as l");
		sb.append(" where l.codigoLoja = :loja ");
		sb.append(" and l.empresa.codigoEmpresa = :empresa ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("loja", loja);
		query.setParameter("empresa", empresa);
		
		List<Integer> lojas = null;
		try {
			lojas = query.getResultList();
		} catch (NoResultException e) {
			return null;
		}

		if (lojas != null && lojas.size() > 0) {
			return lojas.get(0);
		} else {
			return null;
		}
		
	}
	
}
	