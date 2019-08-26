package com.paypal.entities.enuns;

public enum StatusRegistroEnum {

	REG_OK(1, "Registro OK"), 
	COD_ORIGEM_NAO_ENCONTRADO(2,"Cod Origem não encontrado"),
	LAYOUT_NAO_ENCONTRADO(3,"Layout não encontrado"),
	COD_ORIGEM_INEXISTENTE(4, "Cod Origem inexiste para esse tipo");//tipo 4 alterado Fernando
	
	private Integer chave;
	private String descricao;
	
	private StatusRegistroEnum(Integer chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public static StatusRegistroEnum retrieveEnum(Integer code) {
		for (StatusRegistroEnum e : StatusRegistroEnum.values()) {
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
	
	public boolean equals(StatusRegistroEnum sttEnum) {
		return this.getChave().equals(sttEnum.getChave());
	}
	
}
