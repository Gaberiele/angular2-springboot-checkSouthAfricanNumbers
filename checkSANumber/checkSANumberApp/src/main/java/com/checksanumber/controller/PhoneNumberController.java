package com.checksanumber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checksanumber.enums.EnumTipoValidazione;
import com.checksanumber.message.Error;
import com.checksanumber.message.Response;
import com.checksanumber.model.PhoneNumber;
import com.checksanumber.service.PhoneNumberService;
import com.checksanumber.utils.UtilsNumber;

@RestController
@RequestMapping("/phone-number")
public class PhoneNumberController {

	@Autowired
	private PhoneNumberService phoneNumberService;

	@GetMapping("/get-correct")
	public Response getNumberCorrect() {
		Response response = new Response();
		try {
			List<PhoneNumber> lista = phoneNumberService.getPhoneNumbersValidator(EnumTipoValidazione.VALIDO.getCodice());
			response.setPayload(lista);
			return response;
		} catch (Exception e) {
			response.setError(new Error("200", "errore"));
			return response;
		}
	}
	
	@GetMapping("/get-wrong")
	public Response getNumberWrong() {
		Response response = new Response();
		try {
			List<PhoneNumber> lista = phoneNumberService.getPhoneNumbersValidator(EnumTipoValidazione.NON_VALIDO.getCodice());
			response.setPayload(lista);
			return response;
		} catch (Exception e) {
			response.setError(new Error("200", "errore"));
			return response;
		}
	}
	
	@GetMapping("/get-fixed")
	public Response getNumberFixed() {
		Response response = new Response();
		try {
			List<PhoneNumber> lista = phoneNumberService.getPhoneNumbersValidator(EnumTipoValidazione.RIPARATO.getCodice());
			response.setPayload(lista);
			return response;
		} catch (Exception e) {
			response.setError(new Error("200", "errore"));
			return response;
		}
	}
	
	@GetMapping("/check/{numberPhone}")
	public Response checkNumberPhone(@PathVariable(name = "numberPhone") String numberPhone) {
		Response response = new Response();
		try {
			PhoneNumber numberCheck = UtilsNumber.checkNumber(new PhoneNumber(numberPhone));
			response.setPayload(numberCheck);
			return response;
		} catch (Exception e) {
			response.setError(new Error("200", "errore"));
			return response;
		}
	}
	
}
