package com.paypal.entities.enuns;

public enum TipoRegistroEnum {

	HEADER("HA", "Header do Arquivo"), 
	REGISTRO("RE","Registro normal"),
	TRAILLER("TA","Trailler do Arquivo");
	
	private String chave;
	private String descricao;
	
	private TipoRegistroEnum(String chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}

	public static TipoRegistroEnum retrieveEnum(String code) {
		for (TipoRegistroEnum e : TipoRegistroEnum.values()) {
			if (e.getChave().equals(code))
				return e;
		}
		return null;
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean equals(TipoRegistroEnum tipoRegistroEnum) {
		return this.getChave().equals(tipoRegistroEnum.getChave());
	}
	
}
