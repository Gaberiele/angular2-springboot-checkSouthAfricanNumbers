package com.checksanumber.enums;

public enum EnumTipoValidazione {

	NON_VALIDO ("NOT_VALID"),
	VALIDO ("VALID"),
	RIPARATO ("FIXED");
	
	private String codice;

	public String getCodice() {
		return codice;
	}

	private EnumTipoValidazione(String codice) {
		this.codice = codice;
	}
	
	
	
}
