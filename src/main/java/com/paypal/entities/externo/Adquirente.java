package com.paypal.entities.externo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "CAD_ADQUIRENTE")
public class Adquirente extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genadquirente")
	@SequenceGenerator(name = "seq_genadquirente", sequenceName = "CAD_ADQUIRENTE_SEQ")
	@Column(name="ID_ADQUIRENTE")
	private Integer id;
	
	@Size(max = 100)
	@NotNull
	@Column(name = "NOME", length = 100, nullable = false, columnDefinition="nvarchar2")
	private String nome;
	
	@NotNull
	@Column(name = "COD_ADQUIRENTE", nullable = false)
	private String codigoAdquirente;
	
	@Column(name = "IND_HORARIO_VERAO", nullable = false)
	private boolean horarioVerao;
	
	@Column(name = "IND_MASCARA_CARTAO", nullable = false)
	private boolean mascaraCartao;
	
	@Column(name = "IND_REGISTRO_ATIVO")
	private Boolean ativo;
	
	@Column(name = "IND_EXCLUIDO")
	private Boolean excluido;
	
	@Column(name = "TAM_COD_ORIGEM")
	private Integer tamCodigoOrigem;
	
	@Column(name = "COD_VAN", length = 2, columnDefinition="nvarchar2")
	private String codVan;
	
	public Adquirente(){
		inicializaValoresPadroes();
	}

	private void inicializaValoresPadroes() {
		this.setAtivo(true);
		this.setExcluido(false);
	
	}
	
	public Adquirente(String nome){
		this.setNome(nome);
		inicializaValoresPadroes();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isHorarioVerao() {
		return horarioVerao;
	}

	public void setHorarioVerao(boolean horarioVerao) {
		this.horarioVerao = horarioVerao;
	}

	public boolean isMascaraCartao() {
		return mascaraCartao;
	}

	public void setMascaraCartao(boolean mascaraCartao) {
		this.mascaraCartao = mascaraCartao;
	}

	public String getCodigoAdquirente() {
		return codigoAdquirente;
	}

	public void setCodigoAdquirente(String codigoAdquirente) {
		this.codigoAdquirente = codigoAdquirente;
	}

	public Integer getTamCodigoOrigem() {
		return tamCodigoOrigem;
	}

	public void setTamCodigoOrigem(Integer tamCodigoOrigem) {
		this.tamCodigoOrigem = tamCodigoOrigem;
	}
	
	public String getCodVan() {
		return codVan;
	}

	public void setCodVan(String codVan) {
		this.codVan = codVan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Adquirente other = (Adquirente) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}