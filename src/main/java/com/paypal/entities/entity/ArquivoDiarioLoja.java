/**
 * 
 */
package com.paypal.entities.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_ARQ_DIARIOS_ARQ_LOJA")
public class ArquivoDiarioLoja extends AbstractEntity<Integer> implements Comparable<ArquivoDiarioLoja> {

	public ArquivoDiarioLoja() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EDI_ARQ_DIARIOS_ARQ_LOJA_SEQ_GEN")
	@SequenceGenerator(name = "EDI_ARQ_DIARIOS_ARQ_LOJA_SEQ_GEN", sequenceName = "EDI_ARQ_DIARIOS_ARQ_LOJA_SEQ")
	@Column(name="ID_ARQ_DIARIOS_ARQ_LOJA")
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_ARQUIVOS_DIARIOS")
	private ArquivoDiario arquivoDiario;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_ARQUIVO_LOJA")
	private ArquivoLoja arquivoLoja;
	
	@Column(name = "ID_STATUS_PROCESSAMENTO")
	private Integer statusProc;
	
	@Column(name="QTD_REGS_PROC")
	private Integer qtsRegPro;
	
	@Column(name="QTD_REGS_DESCARTADOS")
	private Integer qtsRegDesc;
	
	@OneToMany(mappedBy="arquivoDiarioLoja", targetEntity = ArquivoDiarioLojaLog.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ArquivoDiarioLojaLog> arquivoDiarioLojaLogs = new TreeSet<ArquivoDiarioLojaLog>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArquivoLoja getArquivoLoja() {
		return arquivoLoja;
	}

	public void setArquivoLoja(ArquivoLoja arquivoLoja) {
		this.arquivoLoja = arquivoLoja;
	}

	public ArquivoDiario getArquivoDiario() {
		return arquivoDiario;
	}

	public void setArquivoDiario(ArquivoDiario arquivoDiario) {
		this.arquivoDiario = arquivoDiario;
	}

	public Integer getStatusProc() {
		return statusProc;
	}

	public void setStatusProc(Integer statusProc) {
		this.statusProc = statusProc;
	}

	public Set<ArquivoDiarioLojaLog> getArquivoDiarioLojaLogs() {
		return arquivoDiarioLojaLogs;
	}

	public void setArquivoDiarioLojaLogs(Set<ArquivoDiarioLojaLog> arquivoDiarioLojaLogs) {
		this.arquivoDiarioLojaLogs = arquivoDiarioLojaLogs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoDiario == null) ? 0 : arquivoDiario.hashCode());
		result = prime * result + ((arquivoLoja == null) ? 0 : arquivoLoja.hashCode());
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
		ArquivoDiarioLoja other = (ArquivoDiarioLoja) obj;
		if (arquivoDiario == null) {
			if (other.arquivoDiario != null)
				return false;
		} else if (!arquivoDiario.equals(other.arquivoDiario))
			return false;
		if (arquivoLoja == null) {
			if (other.arquivoLoja != null)
				return false;
		} else if (!arquivoLoja.equals(other.arquivoLoja))
			return false;
		return true;
	}

	@Override
	public int compareTo(ArquivoDiarioLoja o) {
		Integer hashT = this.hashCode();
		Integer hasho = o.hashCode();
		return hashT.compareTo(hasho);
	}

	public Integer getQtsRegPro() {
		return qtsRegPro;
	}

	public void setQtsRegPro(Integer qtsRegPro) {
		this.qtsRegPro = qtsRegPro;
	}

	public Integer getQtsRegDesc() {
		return qtsRegDesc;
	}

	public void setQtsRegDesc(Integer qtsRegDesc) {
		this.qtsRegDesc = qtsRegDesc;
	}

}

