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
@Table(name = "CAD_EMPRESA")
public class Empresa extends AbstractEntity<Integer> implements Comparable<Empresa> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genempresa")
//	@SequenceGenerator(name = "seq_genempresa", sequenceName = "CAD_EMPRESA_SEQ")
	@SequenceGenerator(name = "seq_genempresa", sequenceName = "CAD_EMPRESA_SEQ", initialValue = 1, allocationSize = 500)
	@Column(name="ID_EMPRESA")
	private Integer id;
	
	@Size(max = 80)
	@NotNull
	@Column(name = "NOME", length = 80, nullable = false, columnDefinition="nvarchar2")
	private String nome;
	
	@Column(name = "IND_REGISTRO_ATIVO")
	private Boolean ativo;
	
	@NotNull
	@Column(name = "CODIGO_EMPRESA", length = 5, nullable = false, columnDefinition="nvarchar2")
	private String codigoEmpresa;
	
	@ManyToOne
	@JoinColumn(name="ID_REDE")
	private Rede rede;
	
	@Column(name = "IND_EXCLUIDO")
	private Boolean excluido;
	
	@Column(name = "ID_ESQUEMA_COR")
	private Integer esquemaCor;
	
	//Usado no master detail Adquirente
	/*@OneToMany(mappedBy = "empresa", targetEntity = EmpresaAdquirente.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<EmpresaAdquirente> empresaAdquirente = new TreeSet<EmpresaAdquirente>();*/
	
	/*@OneToMany(mappedBy = "empresa", targetEntity = Loja.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Loja> lojas = new TreeSet<Loja>();*/
	
	
	public Empresa(){
		inicializaDefault();
	}

	private void inicializaDefault() {
		this.setAtivo(true);
		this.setExcluido(false);
	}
	
	public Empresa(Integer id, String codigoEmpresa){
		inicializaDefault();
		this.setId(id);
		this.setCodigoEmpresa(codigoEmpresa);
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

	public Rede getRede() {
		return rede;
	}

	public void setRede(Rede rede) {
		this.rede = rede;
	}
	
	public Boolean getExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}
	
	/*public Set<EmpresaAdquirente> getEmpresaAdquirente() {
		return empresaAdquirente;
	}

	public void setEmpresaAdquirente(Set<EmpresaAdquirente> empresaAdquirente) {
		this.empresaAdquirente = empresaAdquirente;
	}

	public Set<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(Set<Loja> lojas) {
		this.lojas = lojas;
	}
*/
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Integer getEsquemaCor() {
		return esquemaCor;
	}

	public void setEsquemaCor(Integer esquemaCor) {
		this.esquemaCor = esquemaCor;
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
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getCodigoEmpresa() + " - " + getNome();
	}
	
	public int compareTo(Empresa o) {
		return this != null ? o != null ? ((Empresa)this).getNome().compareTo(((Empresa)o).getNome()) : -1 : 1;
	}
	
}