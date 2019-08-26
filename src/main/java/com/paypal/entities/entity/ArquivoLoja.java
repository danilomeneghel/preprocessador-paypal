/**
 * 
 */
package com.paypal.entities.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_ARQUIVO_LOJA")
public class ArquivoLoja extends AbstractEntity<Integer>  {

	public ArquivoLoja() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EDI_ARQUIVO_LOJA_DTO_GEN")
	@SequenceGenerator(name = "SEQ_EDI_ARQUIVO_LOJA_DTO_GEN", sequenceName = "EDI_ARQUIVO_LOJA_SEQ")
	@Column(name="ID_ARQUIVO_LOJA")
	private Integer id;

	@Column(name = "ID_LOJA")
	private Integer idLoja;
	
	@Column(name = "ID_ADQUIRENTE")
	private Integer idAdquirente;
	
	@NotNull
	@Column(name = "ID_ORIGEM_ARQUIVO")
	private Integer idOriArq;
	
	@Column(name = "AGRUPADO")
	private boolean agrupado;
	
	@Column(name = "ID_LOJA_AGRUPADORA")
	private Integer idLojaAgrupadora;
	
	@OneToMany(mappedBy = "arquivoLoja", targetEntity = ArquivoLojaArq.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ArquivoLojaArq> arquivoLojaArq = new TreeSet<ArquivoLojaArq>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}

	public Integer getIdAdquirente() {
		return idAdquirente;
	}

	public void setIdAdquirente(Integer idAdquirente) {
		this.idAdquirente = idAdquirente;
	}

	public Set<ArquivoLojaArq> getArquivoLojaArq() {
		return arquivoLojaArq;
	}

	public void setArquivoLojaArq(Set<ArquivoLojaArq> arquivoLojaArq) {
		this.arquivoLojaArq = arquivoLojaArq;
	}

	public boolean isAgrupado() {
		return agrupado;
	}

	public void setAgrupado(boolean agrupado) {
		this.agrupado = agrupado;
	}

	public Integer getIdLojaAgrupadora() {
		return idLojaAgrupadora;
	}

	public void setIdLojaAgrupadora(Integer idLojaAgrupadora) {
		this.idLojaAgrupadora = idLojaAgrupadora;
	}
	
	public Integer getIdOriArq() {
		return idOriArq;
	}

	public void setIdOriArq(Integer idOriArq) {
		this.idOriArq = idOriArq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAdquirente == null) ? 0 : idAdquirente.hashCode());
		result = prime * result + ((idLoja == 0) ? 0 : Integer.hashCode(idLoja));
		result = prime * result + ((idOriArq == null) ? 0 : idOriArq.hashCode());
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
		ArquivoLoja other = (ArquivoLoja) obj;
		if (idAdquirente == null) {
			if (other.idAdquirente != null)
				return false;
		} else if (!idAdquirente.equals(other.idAdquirente))
			return false;
		if (idLoja == 0) {
			if (other.idLoja != 0)
				return false;
		} else if (!(idLoja == other.idLoja))
			return false;
		if (idOriArq == null) {
			if (other.idOriArq != null)
				return false;
		} else if (!idOriArq.equals(other.idOriArq))
			return false;
		return true;
	}
	
}

