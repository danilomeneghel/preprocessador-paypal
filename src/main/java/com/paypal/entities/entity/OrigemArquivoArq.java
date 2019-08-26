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
import javax.validation.constraints.NotNull;

import com.paypal.util.AbstractEntity;

/**
 * @author diomar.rockenbach
 * 
 */
@Entity
@Table(name = "EDI_ORIGEM_ARQUIVO_ARQ")
public class OrigemArquivoArq extends AbstractEntity<Integer> {

	public OrigemArquivoArq() {
		this.setAtivo(true);
	}
	
	public OrigemArquivoArq(OrigemArquivo origemArquivo) {
		this.setAtivo(true);
		this.setOrigemArquivo(origemArquivo);
	}
	
	public OrigemArquivoArq(Integer id, Integer idOrigemArquivo, String nomeArquivo) {
		this.setId(id);
		
		this.setOrigemArquivo( new OrigemArquivo(idOrigemArquivo));
		
		this.setNomeArquivo(nomeArquivo);
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EDI_ORIGEM_ARQUIVO_ARQ_GEN")
	@SequenceGenerator(name = "SEQ_EDI_ORIGEM_ARQUIVO_ARQ_GEN", sequenceName = "EDI_ORIGEM_ARQUIVO_ARQ_SEQ")
	@Column(name="ID_ORIGEM_ARQUIVO_ARQ")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ID_ORIGEM_ARQUIVO")
	private OrigemArquivo origemArquivo;

	@NotNull
	@Column(name = "TXT_NOME_ARQUIVO",length = 256, nullable = false, columnDefinition="nvarchar2")
	private String nomeArquivo;

	@NotNull
	@Column(name = "IND_REGISTRO_ATIVO")
	private boolean ativo;
	
	@Column(name = "IND_EXCLUIDO")
	private boolean excluido;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrigemArquivo getOrigemArquivo() {
		return origemArquivo;
	}

	public void setOrigemArquivo(OrigemArquivo origemArquivo) {
		this.origemArquivo = origemArquivo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public boolean getAtivo() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeArquivo == null) ? 0 : nomeArquivo.hashCode());
		result = prime * result + ((origemArquivo == null) ? 0 : origemArquivo.hashCode());
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
		OrigemArquivoArq other = (OrigemArquivoArq) obj;
		if (nomeArquivo == null) {
			if (other.nomeArquivo != null)
				return false;
		} else if (!nomeArquivo.equals(other.nomeArquivo))
			return false;
		if (origemArquivo == null) {
			if (other.origemArquivo != null)
				return false;
		} else if (!origemArquivo.equals(other.origemArquivo))
			return false;
		return true;
	}

}

