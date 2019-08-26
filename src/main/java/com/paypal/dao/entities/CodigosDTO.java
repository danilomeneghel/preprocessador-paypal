/**
 * 
 */
package com.paypal.dao.entities;

import java.io.Serializable;

import com.paypal.entities.entity.OrigemArquivo;

public class CodigosDTO implements Serializable {

	private static final long serialVersionUID = 457652558583820858L;

	public CodigosDTO() {
	}
	
	public CodigosDTO(String codigoOrigem, Integer empresa) {
		this.setCodigoOrigem(codigoOrigem);
		this.setEmpresa(empresa);
	}
	
	public CodigosDTO(Integer idLoja, String codigoOrigem) {
		this.setIdLoja(idLoja);
		this.setCodigoOrigem(codigoOrigem);
	}
	
	public CodigosDTO(Integer idLoja, String codigoOrigem, Integer empresa, Integer termoAceite) {
		this.setIdLoja(idLoja);
		this.setCodigoOrigem(codigoOrigem);
		this.setEmpresa(empresa);
		this.setTermoAceite(termoAceite);
	}
	
	private Integer idLoja;
	
	private String codigoOrigem;
	
	private Integer empresa;
	
	private Integer termoAceite;
	
	private OrigemArquivo origemArquivo;

	public Integer getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}
	
	public Integer getTermoAceite() {
		return termoAceite;
	}

	public void setTermoAceite(Integer termoAceite) {
		this.termoAceite = termoAceite;
	}
	
	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public String getCodigoOrigem() {
		return codigoOrigem;
	}

	public void setCodigoOrigem(String codigoOrigem) {
		this.codigoOrigem = codigoOrigem;
	}

}

