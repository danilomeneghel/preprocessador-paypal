package com.paypal.dao.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.exception.ConstraintViolationException;

import com.paypal.dao.generico.GenericDao;
import com.paypal.dao.generico.GerenciadorConexao;
import com.paypal.entities.entity.ArquivoDiario;
import com.paypal.entities.entity.ArquivoFisico;
import com.paypal.entities.entity.ArquivoLoja;
import com.paypal.entities.entity.RegistroArquivo;
import com.paypal.entities.enuns.StatusRegistroEnum;
import com.paypal.util.MetaException;

public class RegistroArquivoDaoBean extends GenericDao<RegistroArquivo, Integer> {

	public RegistroArquivoDaoBean() {
		super(RegistroArquivo.class);
	}

	private static final long serialVersionUID = 1L;

	public RegistroArquivo find(Integer numeroLinha, ArquivoDiario arquivosDiarios) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select r ");
		sb.append("  from RegistroArquivo as r ");
		sb.append(" where r.numeroLinha = :numeroLinha ");
		sb.append("   and r.arquivosDiarios = :arquivosDiarios ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("numeroLinha", numeroLinha);
		query.setParameter("arquivosDiarios", arquivosDiarios);
		
		RegistroArquivo reg = null;
		try{
			reg = (RegistroArquivo) query.getSingleResult();
		} catch (NoResultException e) {
			// Não é erro.
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
		return reg;
	}

	public void validaEInsere(Integer nroLinhas, ArquivoFisico arquivoFisico, String linha,
			boolean reprocessar, String tipoRegistro, String codigoOrigem, String idTerminal, ArquivoLoja arquivoLoja, StatusRegistroEnum statusReg) throws Exception {
		
		RegistroArquivo regArq = new RegistroArquivo();
		
		regArq.setConteudoLinha(linha);
		regArq.setArquivoLoja(arquivoLoja);
		regArq.setArquivoFisico(arquivoFisico);
		regArq.setNumeroLinha(nroLinhas);
		regArq.setReprocessar(false);
		regArq.setTipoRegistro(tipoRegistro);
		
		if (codigoOrigem != null) {
			regArq.setCodigoOrigem(codigoOrigem.trim());
		}
		if (idTerminal != null) {
			regArq.setIdTerminal(idTerminal.trim());
		}
		
		regArq.setIdStatusRegistro(statusReg.getChave());

		try {
			super.save(regArq);
		} catch (PersistenceException ex) {
			if (ex.getCause() instanceof ConstraintViolationException) {
				System.out.println("Registro já existente");
			} else {
				ex.printStackTrace();

				throw new MetaException(ex, "Erro ao salvar dados na tabela EDI_REGISTRO_ARQUIVO");
			}

		}catch (Exception e) {
			e.printStackTrace();

			throw new MetaException(e, "Erro ao salvar dados na tabela EDI_REGISTRO_ARQUIVO");
		}
		
	}
	
	public void atualizaArquivoLoja(ArquivoLoja arquivoLoja, ArquivoFisico arquivoFisico, Integer nroLinhas){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" update RegistroArquivo as r ");
		sb.append("    set r.arquivoLoja = :arquivoLoja ");
		sb.append(" where  r.arquivoFisico = :arquivoFisico ");
		sb.append(" and  r.numeroLinha = :numeroLinha ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("arquivoLoja", arquivoLoja);
		query.setParameter("arquivoFisico", arquivoFisico);
		query.setParameter("numeroLinha", nroLinhas);
		
		try{
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void removeLinhasArquivo(ArquivoFisico arquivoFisico) throws MetaException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("delete from RegistroArquivo as r ");
		sb.append(" where r.arquivoFisico = :arquivoFisico ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("arquivoFisico", arquivoFisico);
		try{
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao remover dados na tabela EDI_REGISTRO_ARQUIVO");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistroArquivo> buscarLinhas(Integer idArquivoFisico) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select new br.com.auttar.preprocessador.entities.RegistroArquivo(r.id, r.conteudoLinha, r.numeroLinha, r.codigoOrigem, r.idTerminal, r.idStatusRegistro) ");
		
		sb.append("  from RegistroArquivo as r ");
		sb.append(" where r.arquivoFisico.id = :idArquivoFisico ");
		sb.append("   and ((r.idStatusRegistro in (:idStatusRegistro) ");
		sb.append("   and r.tipoRegistro <> 'TA')  ");
		sb.append("    or r.tipoRegistro = 'HA')  ");
		sb.append(" order by r.numeroLinha ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		List<Integer> idsStatus = new ArrayList<Integer>();
		
		idsStatus.add(StatusRegistroEnum.COD_ORIGEM_NAO_ENCONTRADO.getChave());
		
		query.setParameter("idArquivoFisico", idArquivoFisico);
		query.setParameter("idStatusRegistro", idsStatus);
		
		List<RegistroArquivo> linhas = null;
		try{
			linhas = (List<RegistroArquivo>) query.getResultList();
		} catch (NoResultException e) {
			// Não é erro.
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
			
		return linhas;
	}
	
	public void atualizaLinhaRePreProcessada(RegistroArquivo registroArquivo) throws MetaException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" update RegistroArquivo as r ");
		sb.append("    set r.idStatusRegistro = :idStatusRegistro ");
		sb.append("      , r.arquivoLoja = :arquivoLoja ");
		sb.append(" where r.id = :id ");
		
		Query query = GerenciadorConexao.getEntityManager().createQuery(sb.toString());

		query.setParameter("id", registroArquivo.getId());
		query.setParameter("idStatusRegistro", registroArquivo.getIdStatusRegistro());
		query.setParameter("arquivoLoja", registroArquivo.getArquivoLoja());
		
		try{
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MetaException(e, "Erro ao atualizar dados na tabela EDI_REGISTRO_ARQUIVO");
		}
		
	}
	
	
}
	