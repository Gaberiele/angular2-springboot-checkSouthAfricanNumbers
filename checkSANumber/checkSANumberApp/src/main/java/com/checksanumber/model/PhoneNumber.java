package com.checksanumber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

	@Id
	private Long id;

	@Column(name = "numero_tel")
	private String numeroTel;

	@Column(name = "tipo_num")
	private String tipoNum;

	@Column(name = "note")
	private String note;

	public PhoneNumber() {
	}

	public PhoneNumber(String numeroTel) {
		super();
		this.numeroTel = numeroTel;
	}

	public PhoneNumber(Long id, String numeroTel) {
		this.id = id;
		this.numeroTel = numeroTel;
	}
	
	public PhoneNumber(Long id, String numeroTel, String tipoNum) {
		this.id = id;
		this.numeroTel = numeroTel;
		this.tipoNum = tipoNum;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public String getTipoNum() {
		return tipoNum;
	}

	public void setTipoNum(String tipoNum) {
		this.tipoNum = tipoNum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	

}