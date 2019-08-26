package com.paypal.entities.enuns;

public enum StatusArquivoEnum {

	ARQ_ERRO(1, "Arquivo com erro"), 
	ARQ_VAZIO(2,"Arquivo vazio"),
	SEM_HE_TR(3, "Sem header ou trailler"),
	DIVERGENCIA_LINHAS(4,"Divergência de linhas no trailler"),
	ARQUIVO_OK(5,"Arquivo OK"),
	REPROCESSAR(6,"Reprocessar");
	
	private Integer chave;
	private String descricao;
	
	private StatusArquivoEnum(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public static StatusArquivoEnum retrieveEnum(Integer code) {
		for (StatusArquivoEnum e : StatusArquivoEnum.values()) {
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
	
	public boolean equals(StatusArquivoEnum sttEnum) {
		return this.getChave().equals(sttEnum.getChave());
	}
	
}
