package com.paypal.dao.entities;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.entity.ArquivoFisico;
import com.paypal.entities.enuns.StatusArquivoEnum;

public class ArquivoFisicoDaoBean extends GenericDao<ArquivoFisico, Integer> {

	public ArquivoFisicoDaoBean() {
		super(ArquivoFisico.class);
	}

	private static final long serialVersionUID = 1L;

	public ArquivoFisico existeArquivoFisico(String nomeArquivo) {

		StringBuilder sb = new StringBuilder();

		sb.append("select af ");
		sb.append("from ArquivoFisico af ");
		sb.append("where af.nomeArquivo = :nomeArquivo");

		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("nomeArquivo", nomeArquivo);

		ArquivoFisico arq = null;

		try {
			arq = (ArquivoFisico) query.getSingleResult();
		} catch (NoResultException ne) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arq;
	}

	@Override
	public ArquivoFisico saveOrUpdate(ArquivoFisico entity) {

		try {
			entity = super.saveOrUpdate(entity);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return entity;
	}

	public List<ArquivoFisico> buscaArquivos() {

		StringBuilder sb = new StringBuilder();

		sb.append("select af ");
		sb.append("from ArquivoFisico af ");
		sb.append("where af.statusArquivo = :statusArquivo");

		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("statusArquivo", StatusArquivoEnum.REPROCESSAR.getChave());

		List<ArquivoFisico> arquivos = null;

		try {
			arquivos = (List<ArquivoFisico>) query.getResultList();
		} catch (NoResultException ne) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arquivos;
	}
}
