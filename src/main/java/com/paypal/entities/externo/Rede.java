package com.paypal.entities.externo;

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
import javax.persistence.Transient;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "CAD_REDE")
public class Rede extends AbstractEntity<Integer> implements Comparable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genrede")
	@SequenceGenerator(name = "seq_genrede", sequenceName = "CAD_REDE_SEQ")
	@Column(name="ID_REDE")
	private Integer id;
	
	@Column(name = "NOME", length = 50, nullable = false, columnDefinition="nvarchar2")
	private String nome;
	
	@Column(name = "IND_REGISTRO_ATIVO")
	private Boolean ativo;
	
	@Column(name = "ID_ESQUEMA_COR")
	private Integer esquemaCor;
	
	@Column(name = "IND_EXCLUIDO")
	private Boolean excluido;
	
	@Column(name = "CODIGO_REDE")
	private Integer codigo;
	
	@OneToMany(mappedBy = "rede", targetEntity = Empresa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Empresa> empresas = new TreeSet<Empresa>();
	
	@Transient
	private Boolean gerarAutomatico = true; 
	
	public Rede(){
		this.setAtivo(true);
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

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public Integer getEsquemaCor() {
		return esquemaCor;
	}

	public void setEsquemaCor(Integer esquemaCor) {
		this.esquemaCor = esquemaCor;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Boolean getGerarAutomatico() {
		return gerarAutomatico;
	}

	public void setGerarAutomatico(Boolean gerarAutomatico) {
		this.gerarAutomatico = gerarAutomatico;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rede other = (Rede) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	public int compareTo(Object o1) {
		return this != null ? o1 != null ? ((Rede)this).getNome().compareTo(((Rede)o1).getNome()) : -1 : 1;
	}
}
