package com.paypal.entities.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_REGISTRO_ARQUIVO_CLOB")
public class RegistroArquivoClob extends AbstractEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_REGISTRO_ARQUIVO_CLOB_SEQ")
	@SequenceGenerator(name = "seq_REGISTRO_ARQUIVO_CLOB_SEQ", sequenceName = "EDI_REGISTRO_ARQUIVO_CLOB_SEQ")
	@Column(name="ID_REGISTRO_ARQUIVO_CLOB")
	private Integer id;
	
	@Column(name="CONTEUDO_LINHA_CLOB")
	private String conteudoLinhaClob;
	
	@Column(name="IND_TIPO_REGISTRO")
	private Integer tipoRegistro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConteudoLinhaClob() {
		return conteudoLinhaClob;
	}

	public void setConteudoLinhaClob(String conteudoLinhaClob) {
		this.conteudoLinhaClob = conteudoLinhaClob;
	}

	public Integer getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Integer tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	
}
