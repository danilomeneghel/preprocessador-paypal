package com.paypal.entities.enuns;

public enum StatusProcessamentoEnum {

	AGUARDANDO_PROCESSAMENTO(1, "Aguardando processamento"), 
	PROCESSANDO(2,"Processando"),
	PROCESSADO_OK(3, "Processado OK"),
	ERRO_PROC(4,"Erro Processamento"),
	REGISTRO_ERRO(5,"Registros com erros"),
	REPROCESSAR(6,"Reprocessar"),
	PRE_PROCESSANDO(7,"Pre-Processamento"),
	ERRO_PRE_PROC(8,"Erro Pre-Processamento"),
	NAO_ACHOU_LOJAS(9,"Não achou Lojas"),
	RE_PRE_PROCESSANDO(10,"Re pré-processando"),
	AGUARDANDO_AUTORIZACAO(11,"Aguardando Autorização");
	
	private Integer chave;
	private String descricao;
	
	private StatusProcessamentoEnum(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public static StatusProcessamentoEnum retrieveEnum(Integer code) {
		for (StatusProcessamentoEnum e : StatusProcessamentoEnum.values()) {
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
	
	public boolean equals(StatusProcessamentoEnum procEnum) {
		return this.getChave().equals(procEnum.getChave());
	}
	
}
