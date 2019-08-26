package com.paypal.entities.externo;

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
import javax.validation.constraints.Size;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "CAD_CODIGO_ORIGEM")
public class CodigoOrigem extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
	public CodigoOrigem() {
	}
	
	public CodigoOrigem(Loja loja) {
		this.loja = loja;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_gencodigo_origem")
	@SequenceGenerator(name = "seq_gencodigo_origem", sequenceName = "CAD_CODIGO_ORIGEM_SEQ")
	@Column(name="ID_CODIGO_ORIGEM")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_ADQUIRENTE")
	private Adquirente adquirente;
	
	@ManyToOne
	@JoinColumn(name="ID_LOJA")
	private Loja loja;
	
	@Size(max = 15)
	@Column(name = "CODIGO_ORIGEM", length = 15, nullable = false, columnDefinition="nvarchar2")
	private String codigoOrigem;
	
	@Size(max = 1)
	@Column(name = "IND_TEM_TERMO_ACEITE", length = 1, nullable = false)
	private Integer termoAceite;
	
	@Size(max = 15)
	@Column(name = "ID_TERMINAL", length = 15, columnDefinition="nvarchar2")
	private String idTerminal;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getTermoAceite() {
		return termoAceite;
	}

	public void setTermoAceite(Integer termoAceite) {
		this.termoAceite = termoAceite;
	}

	public Adquirente getAdquirente() {
		return adquirente;
	}

	public void setAdquirente(Adquirente adquirente) {
		this.adquirente = adquirente;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adquirente == null) ? 0 : adquirente.hashCode());
		result = prime * result + ((codigoOrigem == null) ? 0 : codigoOrigem.hashCode());
		result = prime * result + ((idTerminal == null) ? 0 : idTerminal.hashCode());
		result = prime * result + ((loja == null) ? 0 : loja.hashCode());
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
		CodigoOrigem other = (CodigoOrigem) obj;
		if (adquirente == null) {
			if (other.adquirente != null)
				return false;
		} else if (!adquirente.equals(other.adquirente))
			return false;
		if (codigoOrigem == null) {
			if (other.codigoOrigem != null)
				return false;
		} else if (!codigoOrigem.equals(other.codigoOrigem))
			return false;
		if (idTerminal == null) {
			if (other.idTerminal != null)
				return false;
		} else if (!idTerminal.equals(other.idTerminal))
			return false;
		if (loja == null) {
			if (other.loja != null)
				return false;
		} else if (!loja.equals(other.loja))
			return false;
		return true;
	}

	
}
