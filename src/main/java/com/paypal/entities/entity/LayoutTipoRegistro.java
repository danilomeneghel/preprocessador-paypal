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
import javax.validation.constraints.NotNull;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_LAYOUT_TIPO_REGISTRO")
public class LayoutTipoRegistro extends AbstractEntity<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_LAYOUT_TIPO_REGISTRO")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ID_LAYOUT_ORIGEM_ARQUIVO")
	private LayoutOrigemArquivo layoutOrigemArquivo;
	
	@Column(name="TIPO_REGISTRO")
	private String tipoRegistro;
	
	@NotNull
	@Column(name = "IND_REGISTRO_ATIVO")
	private boolean ativo;
	
	@OneToMany(mappedBy = "layoutTipoRegistro", targetEntity = LayoutInformacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<LayoutInformacao> informacoes = new TreeSet<LayoutInformacao>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LayoutOrigemArquivo getLayoutOrigemArquivo() {
		return layoutOrigemArquivo;
	}

	public void setLayoutOrigemArquivo(LayoutOrigemArquivo layoutOrigemArquivo) {
		this.layoutOrigemArquivo = layoutOrigemArquivo;
	}

	public Set<LayoutInformacao> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(Set<LayoutInformacao> informacoes) {
		this.informacoes = informacoes;
	}

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
