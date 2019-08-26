/**
 * 
 */
package com.paypal.entities.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_ARQ_DIARIOS_ARQ_LOJA_LOG")
public class ArquivoDiarioLojaLog extends AbstractEntity<Integer> implements Comparable<ArquivoDiarioLojaLog> {

	public ArquivoDiarioLojaLog() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EDI_ARQ_DIA_ARQ_LOJA_LOG_SEQ_GEN")
	@SequenceGenerator(name = "EDI_ARQ_DIA_ARQ_LOJA_LOG_SEQ_GEN", sequenceName = "EDI_ARQ_DIA_ARQ_LOJA_LOG_SEQ")
	@Column(name="ID_ARQ_DIARIOS_ARQ_LOJA_LOG")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_ARQ_DIARIOS_ARQ_LOJA")
	private ArquivoDiarioLoja arquivoDiarioLoja;
	
	@ManyToOne
	@JoinColumn(name = "ID_ARQUIVOS_DIARIOS")
	private ArquivoDiario arquivoDiario;
	
	@Column(name = "DTH_LOG")
	private Date dataLog;
	
	@Column(name = "TXT_DESCR_LOG")
	private String txtDescricaoLog;
	
	@Column(name="ENVIOU_EMAIL")
	private boolean enviouEmail;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArquivoDiarioLoja getArquivoDiarioLoja() {
		return arquivoDiarioLoja;
	}

	public void setArquivoDiarioLoja(ArquivoDiarioLoja arquivoDiarioLoja) {
		this.arquivoDiarioLoja = arquivoDiarioLoja;
	}

	public ArquivoDiario getArquivoDiario() {
		return arquivoDiario;
	}

	public void setArquivoDiario(ArquivoDiario arquivoDiario) {
		this.arquivoDiario = arquivoDiario;
	}

	public Date getDataLog() {
		return dataLog;
	}

	public void setDataLog(Date dataLog) {
		this.dataLog = dataLog;
	}

	public String getTxtDescricaoLog() {
		return txtDescricaoLog;
	}

	public void setTxtDescricaoLog(String txtDescricaoLog) {
		this.txtDescricaoLog = txtDescricaoLog;
	}

	public boolean isEnviouEmail() {
		return enviouEmail;
	}

	public void setEnviouEmail(boolean enviouEmail) {
		this.enviouEmail = enviouEmail;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoDiario == null) ? 0 : arquivoDiario.hashCode());
		result = prime * result + ((arquivoDiarioLoja == null) ? 0 : arquivoDiarioLoja.hashCode());
		result = prime * result + ((dataLog == null) ? 0 : dataLog.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoDiarioLojaLog other = (ArquivoDiarioLojaLog) obj;
		if (arquivoDiario == null) {
			if (other.arquivoDiario != null)
				return false;
		} else if (!arquivoDiario.equals(other.arquivoDiario))
			return false;
		if (arquivoDiarioLoja == null) {
			if (other.arquivoDiarioLoja != null)
				return false;
		} else if (!arquivoDiarioLoja.equals(other.arquivoDiarioLoja))
			return false;
		if (dataLog == null) {
			if (other.dataLog != null)
				return false;
		} else if (!dataLog.equals(other.dataLog))
			return false;
		return true;
	}

	@Override
	public int compareTo(ArquivoDiarioLojaLog o) {
		
		Integer hashT = this.hashCode();
		Integer hashO = o.hashCode();
		
		return hashT.compareTo(hashO);
	}

}

