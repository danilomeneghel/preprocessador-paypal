package com.paypal.entities.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_REGISTRO_ARQUIVO")
public class RegistroArquivo extends AbstractEntity<Integer> {

	public RegistroArquivo() {
	
	}
	
	public RegistroArquivo(Integer id, String conteudoLinha, Integer numeroLinha, String codigoOrigem, String idTerminal, Integer idStatusRegistro) {
		this.setId(id);
		this.setConteudoLinha(conteudoLinha);
		this.setNumeroLinha(numeroLinha);
		this.setCodigoOrigem(codigoOrigem);
		this.setIdTerminal(idTerminal);
		this.setIdStatusRegistro(idStatusRegistro);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_REGISTRO_ARQUIVO_SEQ")
	@SequenceGenerator(name = "seq_REGISTRO_ARQUIVO_SEQ", sequenceName = "EDI_REGISTRO_ARQUIVO_SEQ")
	@Column(name="ID_REGISTRO_ARQUIVO")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_ARQUIVO_FISICO")
	private ArquivoFisico arquivoFisico;
	
	@NotNull
	@Column(name="TIPO_REGISTRO")
	private String tipoRegistro;
	
	@NotNull
	@Column(name="CONTEUDO_LINHA")
	private String conteudoLinha;
	
	@NotNull
	@Column(name="REPROCESSAR")
	private boolean reprocessar;
	
	@NotNull
	@Column(name="NRO_LINHA")
	private Integer numeroLinha;
	
	@NotNull
	@Column(name="ID_STATUS_REGISTRO")
	private Integer idStatusRegistro;
	
	@ManyToOne
	@JoinColumn(name = "ID_ARQUIVO_LOJA")
	private ArquivoLoja arquivoLoja;
	
	@Column(name="CODIGO_ORIGEM")
	private String codigoOrigem;
	
	@Column(name="ID_TERMINAL")
	private String idTerminal;
	
	@ManyToOne
	@JoinColumn(name = "ID_REGISTRO_ARQUIVO_CLOB")
	private RegistroArquivoClob registroArquivoClob;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public String getConteudoLinha() {
		return conteudoLinha;
	}

	public void setConteudoLinha(String conteudoLinha) {
		this.conteudoLinha = conteudoLinha;
	}

	public boolean isReprocessar() {
		return reprocessar;
	}

	public void setReprocessar(boolean reprocessar) {
		this.reprocessar = reprocessar;
	}

	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public Integer getIdStatusRegistro() {
		return idStatusRegistro;
	}

	public void setIdStatusRegistro(Integer idStatusRegistro) {
		this.idStatusRegistro = idStatusRegistro;
	}

	public ArquivoLoja getArquivoLoja() {
		return arquivoLoja;
	}

	public void setArquivoLoja(ArquivoLoja arquivoLoja) {
		this.arquivoLoja = arquivoLoja;
	}

	public ArquivoFisico getArquivoFisico() {
		return arquivoFisico;
	}

	public void setArquivoFisico(ArquivoFisico arquivoFisico) {
		this.arquivoFisico = arquivoFisico;
	}

	public String getCodigoOrigem() {
		return codigoOrigem;
	}

	public void setCodigoOrigem(String codigoOrigem) {
		this.codigoOrigem = codigoOrigem;
	}

	public String getIdTerminal() {
		return idTerminal;
	}

	public void setIdTerminal(String idTerminal) {
		this.idTerminal = idTerminal;
	}

	public RegistroArquivoClob getRegistroArquivoClob() {
		return registroArquivoClob;
	}

	public void setRegistroArquivoClob(RegistroArquivoClob registroArquivoClob) {
		this.registroArquivoClob = registroArquivoClob;
	}

}
