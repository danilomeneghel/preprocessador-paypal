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

@Entity
@Table(name = "EDI_ORIGEM_ARQUIVO_PATH")
public class OrigemArquivoPath extends AbstractEntity<Integer> {

	public OrigemArquivoPath() {
		this.setAtivo(true);
	}
	
	public OrigemArquivoPath(String localOrigem, String localDestino, String localPreProcessamento, String localPreProcessamentoErro) {
		this.setLocalOrigem(localOrigem);
		this.setLocalDestino(localDestino);
		this.setLocalPreProcessamento(localPreProcessamento);
		this.setLocalPreProcessamentoErro(localPreProcessamentoErro);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EDI_ORIGEM_ARQUIVO_PATH_GEN")
	@SequenceGenerator(name = "SEQ_EDI_ORIGEM_ARQUIVO_PATH_GEN", sequenceName = "EDI_ORIGEM_ARQUIVO_PATH_SEQ")
	@Column(name="ID_ORIGEM_ARQUIVO_PATH")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ID_ORIGEM_ARQUIVO")
	private OrigemArquivo origemArquivo;

	@NotNull
	@Column(name = "TXT_LOCAL_ORIGEM",length = 256, nullable = false, columnDefinition="nvarchar2")
	private String localOrigem;
	
	@NotNull
	@Column(name = "TXT_LOCAL_DESTINO",length = 256, nullable = false, columnDefinition="nvarchar2")
	private String localDestino;
	
	@NotNull
	@Column(name = "TXT_PROGRAMA_EXECUTAVEL",length = 256, nullable = false, columnDefinition="nvarchar2")
	private String executavel;
	
	@NotNull
	@Column(name = "TXT_LOCAL_PRE_PROCESSAMENTO",length = 256, nullable = false, columnDefinition="nvarchar2")
	private String localPreProcessamento;
	
	@NotNull
	@Column(name = "TXT_ERROS_PRE_PROCESSAMENTO",length = 256, nullable = false, columnDefinition="nvarchar2")
	private String localPreProcessamentoErro;
	
	@NotNull
	@Column(name = "IND_REGISTRO_ATIVO")
	private boolean ativo;
	
	@NotNull
	@Column(name = "PRIORIDADE")
	private Integer prioridade;
	
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

	public String getLocalOrigem() {
		return localOrigem;
	}

	public void setLocalOrigem(String localOrigem) {
		this.localOrigem = localOrigem;
	}

	public String getLocalDestino() {
		return localDestino;
	}

	public void setLocalDestino(String localDestino) {
		this.localDestino = localDestino;
	}

	public String getExecutavel() {
		return executavel;
	}

	public void setExecutavel(String executavel) {
		this.executavel = executavel;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public String getLocalPreProcessamento() {
		return localPreProcessamento;
	}

	public void setLocalPreProcessamento(String localPreProcessamento) {
		this.localPreProcessamento = localPreProcessamento;
	}
	
	public String getLocalPreProcessamentoErro() {
		return localPreProcessamentoErro;
	}

	public void setLocalPreProcessamentoErro(String localPreProcessamentoErro) {
		this.localPreProcessamentoErro = localPreProcessamentoErro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localOrigem == null) ? 0 : localOrigem.hashCode());
		result = prime * result + ((origemArquivo == null) ? 0 : origemArquivo.hashCode());
		result = prime * result + ((prioridade == null) ? 0 : prioridade.hashCode());
		return result;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrigemArquivoPath other = (OrigemArquivoPath) obj;
		if (localOrigem == null) {
			if (other.localOrigem != null)
				return false;
		} else if (!localOrigem.equals(other.localOrigem))
			return false;
		if (origemArquivo == null) {
			if (other.origemArquivo != null)
				return false;
		} else if (!origemArquivo.equals(other.origemArquivo))
			return false;
		if (prioridade == null) {
			if (other.prioridade != null)
				return false;
		} else if (!prioridade.equals(other.prioridade))
			return false;
		return true;
	}

}

