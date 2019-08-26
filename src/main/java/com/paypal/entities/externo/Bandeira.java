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
@Table(name = "CAD_BANDEIRA")
public class Bandeira extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genbandeira")
	@SequenceGenerator(name = "seq_genbandeira", sequenceName = "CAD_BANDEIRA_SEQ")
	@Column(name="ID_BANDEIRA")
	private Integer id;
	
	@Size(max = 100)
	@NotNull
	@Column(name = "NOME", length = 100, nullable = false, columnDefinition="nvarchar2")
	private String nome;
	
	@Column(name = "IND_REGISTRO_ATIVO")
	private Boolean ativo;
	
	@Column(name = "IND_EXCLUIDO")
	private Boolean excluido;
	
	public Bandeira(){
		this.setAtivo(true);
		this.setExcluido(false);
	}
	
	public Bandeira(String nome){
		this.setNome(nome);
		this.setAtivo(true);
		this.setExcluido(false);
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
		Bandeira other = (Bandeira) obj;
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