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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.paypal.entities.externo.Adquirente;
import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "EDI_ORIGEM_ARQUIVO")
public class OrigemArquivo extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 457652558583820858L;

	public OrigemArquivo() {
		this.setAtivo(true);
	}
	
	public OrigemArquivo(Integer id) {
		this.setId(id);
		this.setAtivo(true);
	}
	
	@Id
	@Column(name="ID_ORIGEM_ARQUIVO")
	private Integer id;

	@NotNull
	@Column(name = "COD_ORIGEM_ARQUIVO",length = 10, nullable = false, columnDefinition="nvarchar2")
	private String codigoOrigem;
	
	@NotNull
	@Column(name = "TXT_DESCRICAO",length = 100, nullable = false, columnDefinition="nvarchar2")
	private String descricao;
	
	@Column(name = "ID_ADQUIRENTE")
	private Integer idAdquirente;
	
	@NotNull
	@Column(name = "IND_REGISTRO_ATIVO")
	private boolean ativo;
	
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido;
	
//	@Transient
	@JoinColumn(name = "ID_ADQUIRENTE", insertable=false, updatable=false)
	@ManyToOne
	private Adquirente adquirente;
	
	@OneToMany(mappedBy = "origemArquivo", targetEntity = OrigemArquivoArq.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<OrigemArquivoArq> origemArquivosArq = new TreeSet<OrigemArquivoArq>();
	
	@OneToMany(mappedBy = "origemArquivo", targetEntity = OrigemArquivoPath.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<OrigemArquivoPath> origemArquivosPath = new TreeSet<OrigemArquivoPath>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoOrigem() {
		return codigoOrigem;
	}

	public void setCodigoOrigem(String codigoOrigem) {
		this.codigoOrigem = codigoOrigem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdAdquirente() {
		return idAdquirente;
	}

	public void setIdAdquirente(Integer idAdquirente) {
		this.idAdquirente = idAdquirente;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Adquirente getAdquirente() {
		return adquirente;
	}

	public void setAdquirente(Adquirente adquirente) {
		if (adquirente != null) {
			this.setIdAdquirente(adquirente.getId());
		}
		this.adquirente = adquirente;
	}

	public Set<OrigemArquivoArq> getOrigemArquivosArq() {
		return origemArquivosArq;
	}

	public void setOrigemArquivosArq(Set<OrigemArquivoArq> origemArquivosArq) {
		this.origemArquivosArq = origemArquivosArq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrigemArquivo other = (OrigemArquivo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

