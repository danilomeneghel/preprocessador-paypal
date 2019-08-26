/**
 * 
 */
package com.paypal.entities.enuns;

/**
 * @author fernando.buonocore
 *
 */
public enum TipoSeparadorEnum {

	POSICIONAL("Posicional"),
	SEPARADOR("Separador");
	
	private String nome;

	private TipoSeparadorEnum(String nome) {
		this.nome = nome;
	}
	
	public static TipoSeparadorEnum retrieveEnum(String code) {
		for (TipoSeparadorEnum e : TipoSeparadorEnum.values()) {
			if (e.getNome().equals(code))
				return e;
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
