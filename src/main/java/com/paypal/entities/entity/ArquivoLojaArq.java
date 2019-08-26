/**
 * 
 */
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

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_ARQUIVO_LOJA_ARQ")
public class ArquivoLojaArq extends AbstractEntity<Integer> implements Comparable<ArquivoLojaArq>{

	public ArquivoLojaArq() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EDI_ARQUIVO_LOJA_ARQ_DTO_GEN")
	@SequenceGenerator(name = "SEQ_EDI_ARQUIVO_LOJA_ARQ_DTO_GEN", sequenceName = "EDI_ARQUIVO_LOJA_ARQ_SEQ")
	@Column(name="ID_ARQUIVO_LOJA_ARQ")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_ARQUIVO_LOJA")
	private ArquivoLoja arquivoLoja;
	
	@Column(name = "ID_ORIGEM_ARQUIVO_ARQ")
	private Integer idOrigemArquivoArq;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORIGEM_ARQUIVO_ARQ", insertable=false, updatable=false)
	private OrigemArquivoArq origemArquivoArq;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArquivoLoja getArquivoLoja() {
		return arquivoLoja;
	}

	public void setArquivoLoja(ArquivoLoja arquivoLoja) {
		this.arquivoLoja = arquivoLoja;
	}

	public int compareTo(ArquivoLojaArq o) {
		return this.getIdOrigemArquivoArq().compareTo(o.getIdOrigemArquivoArq());
	}

	public Integer getIdOrigemArquivoArq() {
		return idOrigemArquivoArq;
	}

	public void setIdOrigemArquivoArq(Integer idOrigemArquivoArq) {
		this.idOrigemArquivoArq = idOrigemArquivoArq;
	}

	public OrigemArquivoArq getOrigemArquivoArq() {
		return origemArquivoArq;
	}

	public void setOrigemArquivoArq(OrigemArquivoArq origemArquivoArq) {
		this.origemArquivoArq = origemArquivoArq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivoLoja == null) ? 0 : arquivoLoja.hashCode());
		result = prime * result + ((idOrigemArquivoArq == null) ? 0 : idOrigemArquivoArq.hashCode());
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
		ArquivoLojaArq other = (ArquivoLojaArq) obj;
		if (arquivoLoja == null) {
			if (other.arquivoLoja != null)
				return false;
		} else if (!arquivoLoja.equals(other.arquivoLoja))
			return false;
		if (idOrigemArquivoArq == null) {
			if (other.idOrigemArquivoArq != null)
				return false;
		} else if (!idOrigemArquivoArq.equals(other.idOrigemArquivoArq))
			return false;
		return true;
	}
	
}

