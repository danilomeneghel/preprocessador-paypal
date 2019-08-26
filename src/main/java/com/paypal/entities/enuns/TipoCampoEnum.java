package com.paypal.entities.enuns;

public enum TipoCampoEnum {

	TIPO_REGISTRO(1, "Tipo de Registro"), 
	CODIGO_ESTABELECIMENTO(2,"C�digo do Estabelecimento"),
	CODIGO_ESTAB_CENTRALIZADOR(3, "C�digo do Estabelecimento Centralizador dos Pagamentos"),
	ARQUIVO_VERSAO(4,"Arquivo e Vers�o"),
	NUM_SEQ_HEADER(6,"N�mero sequencial de remessa no Header"),
	QUANTIDADE_REGISTROS(7,"Quantidade de Registros"),
	DATA_MOVIMENTO_HEADER(8, "Data do movimento. Campo do Header"),
	NUMERO_RV(50, "N�mero do RV"),
	NUMERO_TERMINAL(51, "N�mero do Terminal"),
	REDE_ESTABELECIMENTO(52, "Rede Estabelecimento"),
	REDE(53, "Rede"),
	ESTABELECIMENTO(54, "Estabelecimento"),
	CNPJ_ESTABELECIMENTO(55, "CNPJ Estabelecimento"),
	CODIGO_EMPRESA(56,"C�digo Empresa"),
	CODIGO_LOJA(57, "C�digo Loja"),
	VALIDA_QUANTIDADE_REGISTRO(59, "Valida Quantidade Registro");//oraldo
	
	private Integer chave;
	private String descricao;
	
	private TipoCampoEnum(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public static TipoCampoEnum retrieveEnum(Integer code) {
		for (TipoCampoEnum e : TipoCampoEnum.values()) {
			if (e.getChave().equals(code))
				return e;
		}
		return null;
	}
	
	public Integer getChave() {
		return chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean equals(TipoCampoEnum tipoCampoEnum) {
		return this.getChave().equals(tipoCampoEnum.getChave());
	}
	
}
