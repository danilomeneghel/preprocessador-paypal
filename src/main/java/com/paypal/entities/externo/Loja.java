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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.paypal.util.AbstractEntity;

@Entity
@Table(name = "CAD_LOJA")
public class Loja extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genloja")
	@SequenceGenerator(name = "seq_genloja", sequenceName = "CAD_LOJA_SEQ")
	@Column(name="ID_LOJA")
	private Integer id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_EMPRESA")
	private Empresa empresa;
	
	@Max(value = 9999) 
	@Column(name = "CODIGO_LOJA", length = 4, nullable = false, columnDefinition = "nvarchar2")
	private String codigoLoja;
	
	@Size(max = 100)
	@Column(name = "NOME", length = 100, nullable = false, columnDefinition="nvarchar2")
	private String nome;
	
	@Size(max = 50)
	@Column(name = "DESCRICAO", length = 50, nullable = true, columnDefinition="nvarchar2")
	private String descricao;
	
	@Column(name = "CNPJ", length = 14, nullable = true, columnDefinition="nvarchar2")
	private String cnpj;
	
	@Column(name = "IND_HORARIO_VERAO")
	private Boolean horarioVerao;
	
	@Column(name = "IND_REGISTRO_ATIVO")
	private Boolean ativo;
	
	@Column(name = "IND_EXCLUIDO")
	private Boolean excluido;
	
	@Column(name = "IND_GESTAO_CARGA_TABELAS")
	private Boolean gestorTabela;
	
	//Endereco
	@Size(max = 50)
	@Column(name = "ENDERECO", length = 50, nullable = true, columnDefinition="nvarchar2")
	private String endereco;
	
	@Size(max = 14)
	@Column(name = "COMPLEMENTO", length = 14, nullable = true, columnDefinition="nvarchar2")
	private String complemento;
	
	@Size(max = 50)
	@Column(name = "BAIRRO", length = 50, nullable = true, columnDefinition="nvarchar2")
	private String bairro;
	
	@Size(max = 30)
	@Column(name = "CIDADE", length = 30, nullable = true, columnDefinition="nvarchar2")
	private String cidade;
	
	@Column(name = "TELEFONE_PREFIXO1", length = 2, nullable = true, columnDefinition="nvarchar2")
	private String prefixoTelefone1;
	
	@Column(name = "TELEFONE_NUMERO1", length = 9, nullable = true, columnDefinition="nvarchar2")
	private String telefone1;
	
	@Column(name = "TELEFONE_PREFIXO2", length = 2, nullable = true, columnDefinition="nvarchar2")
	private String prefixoTelefone2;
	
	@Column(name = "TELEFONE_NUMERO2", length = 9, nullable = true, columnDefinition="nvarchar2")
	private String telefone2;
	
	@Column(name = "CEP", length = 8, nullable = true, columnDefinition="nvarchar2")
	private String cep;
	
	@Size(max = 14)
	@Column(name = "NUMERO", length = 14, nullable = true, columnDefinition="nvarchar2")
	private String numero;

	@javax.persistence.Transient
	private Rede rede;
	
	@Column(name = "PERMITE_AGRUPAR")
	private Boolean permiteAgrupamento;
	
	@OneToMany(mappedBy = "loja", targetEntity = CodigoOrigem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<CodigoOrigem> codigosOrigens = new TreeSet<CodigoOrigem>();
	
	public Loja(){
		inicializaDefaults();
	}
	
	public Loja(Integer id, String codigoLoja){
		inicializaDefaults();
		this.setId(id);
		this.setCodigoLoja(codigoLoja);
	}
	
	public Loja(Integer id, String codigoLoja, String nome){
		inicializaDefaults();
		this.setId(id);
		this.setCodigoLoja(codigoLoja);
		this.setNome(nome);
	}
	
	public Loja(Integer id, String codigoLoja, Integer idEmpresa, String codigoEmpresa){
		inicializaDefaults();
		this.setId(id);
		this.setCodigoLoja(codigoLoja);
		Empresa e = new Empresa(idEmpresa, codigoEmpresa);
		this.setEmpresa(e);
	}

	private void inicializaDefaults() {
		this.setExcluido(false);
		this.setAtivo(true);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(String codigoLoja) {
		this.codigoLoja = codigoLoja;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Boolean getHorarioVerao() {
		return horarioVerao;
	}

	public void setHorarioVerao(Boolean horarioVerao) {
		this.horarioVerao = horarioVerao;
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

	public Rede getRede() {
		return rede;
	}

	public void setRede(Rede rede) {
		this.rede = rede;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getPrefixoTelefone1() {
		return prefixoTelefone1;
	}

	public void setPrefixoTelefone1(String prefixoTelefone1) {
		this.prefixoTelefone1 = prefixoTelefone1;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getPrefixoTelefone2() {
		return prefixoTelefone2;
	}

	public void setPrefixoTelefone2(String prefixoTelefone2) {
		this.prefixoTelefone2 = prefixoTelefone2;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result
				+ ((codigoLoja == null) ? 0 : codigoLoja.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		return result;
	}

	public String getCnpjMasked() {
		if (this.getCnpj() != null) {
			String cnpj = this.getCnpj().replaceAll("[^0-9]", "");
		
			if (cnpj.length() == 11) {
				return cnpj.substring(0, 3) + "." + cnpj.substring(3, 6) + "." + cnpj.substring(6, 9) + "-"
						+ cnpj.substring(9, 11);
			}
			if (cnpj.length() == 14) {
				return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/"
						+ cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
			}
		}
		return "";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (codigoLoja == null) {
			if (other.codigoLoja != null)
				return false;
		} else if (!codigoLoja.equals(other.codigoLoja))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getCodigoLoja() + " - " + getNome();
	}

	public Boolean getGestorTabela() {
		return gestorTabela;
	}

	public void setGestorTabela(Boolean gestorTabela) {
		this.gestorTabela = gestorTabela;
	}

	public Set<CodigoOrigem> getCodigosOrigens() {
		return codigosOrigens;
	}

	public void setCodigosOrigens(Set<CodigoOrigem> codigosOrigens) {
		this.codigosOrigens = codigosOrigens;
	}

	public Boolean getPermiteAgrupamento() {
		return permiteAgrupamento;
	}

	public void setPermiteAgrupamento(Boolean permiteAgrupamento) {
		this.permiteAgrupamento = permiteAgrupamento;
	}

}
