package com.checksanumber.utils;

import com.checksanumber.enums.EnumTipoValidazione;
import com.checksanumber.model.PhoneNumber;

public class UtilsNumber {

	public static PhoneNumber checkNumber(PhoneNumber numberPhone) {
		PhoneNumber newNum = new PhoneNumber();
		newNum.setId(numberPhone.getId());
		newNum.setNumeroTel(numberPhone.getNumeroTel());
		
		String num = numberPhone.getNumeroTel();
		if (num.matches(".*[a-zA-Z].*")) { 
			newNum.setTipoNum(EnumTipoValidazione.NON_VALIDO.getCodice());
			newNum.setNote("STRING");
			return newNum;
		}
		num = num.replaceAll("\\s","");
		int len = num.length();
		
		if (len == 11 || len == 9) {
			if (len == 9) {
				newNum.setNumeroTel("27"+num);
				newNum.setTipoNum(EnumTipoValidazione.RIPARATO.getCodice());
				newNum.setNote("ADDED PREFIX");
			}else if (!num.startsWith("27")){
				newNum.setTipoNum(EnumTipoValidazione.NON_VALIDO.getCodice());
				newNum.setNote("PREFIX WRONG");
			}else {
				newNum.setTipoNum(EnumTipoValidazione.VALIDO.getCodice());
			}	
		}else {
			newNum.setTipoNum(EnumTipoValidazione.NON_VALIDO.getCodice());
			newNum.setNote("LENGTH");
		}
		
		return newNum;
	}
	

}
