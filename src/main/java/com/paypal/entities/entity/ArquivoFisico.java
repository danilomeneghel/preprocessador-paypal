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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_ARQUIVO_FISICO")
public class ArquivoFisico extends AbstractEntity<Integer> {

	public ArquivoFisico() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EDI_ARQUIVO_FISICO_SEQ_GEN")
	@SequenceGenerator(name = "EDI_ARQUIVO_FISICO_SEQ_GEN", sequenceName = "EDI_ARQUIVO_FISICO_SEQ")
	@Column(name="ID_ARQUIVO_FISICO")
	private Integer id;

	@NotNull
	@Column(name="nome_arquivo", length = 256, nullable = false, columnDefinition="nvarchar2")
	private String nomeArquivo;
	
	@Column(name = "DT_MOVIMENTO")
	private Date dataMovimento;
	
	@NotNull
	@Column(name = "DT_INCLUSAO")
	private Date dataInclusao;
	
	@Column(name="DT_TRANSACAO")
	private Date dataTransacao;
	
	@NotNull
	@Column(name = "DT_CHEGADA_ARQUIVO")
	private Date dataChegada;
	
	@Column(name="TAMANHO_ARQUIVO")
	private Long tamanho;
	
	@Column(name="ID_STATUS_ARQUIVO")
	private Integer statusArquivo;
	
	@Column(name="ID_ORIGEM_ARQUIVO")
	private Integer idOrigemArquivo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Integer getStatusArquivo() {
		return statusArquivo;
	}

	public void setStatusArquivo(Integer statusArquivo) {
		this.statusArquivo = statusArquivo;
	}

	public Integer getIdOrigemArquivo() {
		return idOrigemArquivo;
	}

	public void setIdOrigemArquivo(Integer idOrigemArquivo) {
		this.idOrigemArquivo = idOrigemArquivo;
	}
	
	
}

