package com.paypal.entities.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_LAYOUT_ORIGEM_ARQUIVO")
public class LayoutOrigemArquivo extends AbstractEntity<Integer> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_LAYOUT_ORIGEM_ARQUIVO")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ID_ORIGEM_ARQUIVO_ARQ")
	private OrigemArquivoArq origemArquivoArq;
	
	@Column(name = "TIPO_SEPARADOR", length = 20, columnDefinition = "nvarchar2")
	private String tipoSeparador;

	@Column(name = "SEPARADOR", length = 1)
	private String separador;
	
	@OneToMany(mappedBy = "layoutOrigemArquivo", targetEntity = LayoutTipoRegistro.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<LayoutTipoRegistro> tiposRegistros = new TreeSet<LayoutTipoRegistro>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrigemArquivoArq getOrigemArquivoArq() {
		return origemArquivoArq;
	}

	public void setOrigemArquivoArq(OrigemArquivoArq origemArquivoArq) {
		this.origemArquivoArq = origemArquivoArq;
	}

	public Set<LayoutTipoRegistro> getTiposRegistros() {
		return tiposRegistros;
	}

	public void setTiposRegistros(Set<LayoutTipoRegistro> tiposRegistros) {
		this.tiposRegistros = tiposRegistros;
	}

	public String getTipoSeparador() {
		return tipoSeparador;
	}

	public void setTipoSeparador(String tipoSeparador) {
		this.tipoSeparador = tipoSeparador;
	}

	public String getSeparador() {
		return separador;
	}

	public void setSeparador(String separador) {
		this.separador = separador;
	}
	
}
