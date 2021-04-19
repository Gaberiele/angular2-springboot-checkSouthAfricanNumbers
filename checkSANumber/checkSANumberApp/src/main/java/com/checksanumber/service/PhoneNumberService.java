package com.checksanumber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.checksanumber.model.PhoneNumber;
import com.checksanumber.repository.PhoneNumberRepository;

@Service
public class PhoneNumberService {

	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
	
	public List<PhoneNumber> getPhoneNumbersValidator(String typeValidation){	
		return phoneNumberRepository.findByTipoNum(typeValidation);
		
	}
	
}
