package com.paypal.entities.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_LAYOUT_INFORMACAO")
public class LayoutInformacao extends AbstractEntity<Integer> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_LAYOUT_INFORMACAO")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ID_LAYOUT_TIPO_REGISTRO")
	private LayoutTipoRegistro layoutTipoRegistro;
	
	@Column(name="POSICAO_INI")
	private Integer posIni;
	
	@Column(name="POSICAO_FIM")
	private Integer posFim;
	
	@Column(name="POSICAO")
	private Integer posicao;
	
	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="ESCALA")
	private Integer escala;
	
	@Column(name="VALOR_FIXO")
	private String valorFixo;
	
	@Column(name="TAMANHO")
	private Integer tamanho;
	
	@Column(name="OBSERVACAO")
	private String obs;
	
	@ManyToOne
	@JoinColumn(name="ID_LAYOUT_CAMPO")
	private LayoutCampo layoutCampo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LayoutTipoRegistro getLayoutTipoRegistro() {
		return layoutTipoRegistro;
	}

	public void setLayoutTipoRegistro(LayoutTipoRegistro layoutTipoRegistro) {
		this.layoutTipoRegistro = layoutTipoRegistro;
	}

	public Integer getPosIni() {
		return posIni;
	}

	public void setPosIni(Integer posIni) {
		this.posIni = posIni;
	}

	public Integer getPosFim() {
		return posFim;
	}

	public void setPosFim(Integer posFim) {
		this.posFim = posFim;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getEscala() {
		return escala;
	}

	public void setEscala(Integer escala) {
		this.escala = escala;
	}

	public String getValorFixo() {
		return valorFixo;
	}

	public void setValorFixo(String valorFixo) {
		this.valorFixo = valorFixo;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public LayoutCampo getLayoutCampo() {
		return layoutCampo;
	}

	public void setLayoutCampo(LayoutCampo layoutCampo) {
		this.layoutCampo = layoutCampo;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

}


