/**
 * 
 */
package com.paypal.entities.entity;

import java.util.Date;
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
@Table(name = "EDI_ARQUIVOS_DIARIOS")
public class ArquivoDiario extends AbstractEntity<Integer> {

	public ArquivoDiario() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EDI_ARQUIVOS_DIARIOS_DTO_SEQ_GEN")
	@SequenceGenerator(name = "EDI_ARQUIVOS_DIARIOS_DTO_SEQ_GEN", sequenceName = "EDI_ARQUIVOS_DIARIOS_SEQ")
	@Column(name="ID_ARQUIVOS_DIARIOS")
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_ARQUIVO_FISICO")
	private ArquivoFisico arquivoFisico;
	
	@NotNull
	@Column(name = "ID_ORIGEM_ARQUIVO_ARQ")
	private Integer idOriArqArq;
	
	@Column(name = "ID_STATUS_PROCESSAMENTO")
	private Integer statusProc;
	
	@Column(name="DTH_INICIO_PRE_PROC")
	private Date dataInicioPreProc;
	
	@Column(name="DTH_FIM_PRE_PROC")
	private Date dataFimPreProc;
	
	@Column(name="NUM_SEQ_ARQUIVO")
	private Integer numSeqArquivo;
	
	@Column(name="QTDE_REGS_PROC")
	private Integer qtsRegPro;
	
	@Column(name="QTDE_REGS_DESCARTADOS")
	private Integer qtsRegDesc;
	
	@OneToMany(mappedBy="arquivoDiario", targetEntity = ArquivoDiarioLoja.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ArquivoDiarioLoja> arquivoDiarioLojas = new TreeSet<ArquivoDiarioLoja>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArquivoFisico getArquivoFisico() {
		return arquivoFisico;
	}

	public void setArquivoFisico(ArquivoFisico arquivoFisico) {
		this.arquivoFisico = arquivoFisico;
	}

	public Integer getIdOriArqArq() {
		return idOriArqArq;
	}

	public void setIdOriArqArq(Integer idOriArqArq) {
		this.idOriArqArq = idOriArqArq;
	}

	public Integer getStatusProc() {
		return statusProc;
	}

	public void setStatusProc(Integer statusProc) {
		this.statusProc = statusProc;
	}

	public Date getDataInicioPreProc() {
		return dataInicioPreProc;
	}

	public void setDataInicioPreProc(Date dataInicioPreProc) {
		this.dataInicioPreProc = dataInicioPreProc;
	}

	public Date getDataFimPreProc() {
		return dataFimPreProc;
	}

	public void setDataFimPreProc(Date dataFimPreProc) {
		this.dataFimPreProc = dataFimPreProc;
	}

	public Integer getNumSeqArquivo() {
		return numSeqArquivo;
	}

	public void setNumSeqArquivo(Integer numSeqArquivo) {
		this.numSeqArquivo = numSeqArquivo;
	}

	public Set<ArquivoDiarioLoja> getArquivoDiarioLojas() {
		return arquivoDiarioLojas;
	}

	public void setArquivoDiarioLojas(Set<ArquivoDiarioLoja> arquivoDiarioLojas) {
		this.arquivoDiarioLojas = arquivoDiarioLojas;
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

