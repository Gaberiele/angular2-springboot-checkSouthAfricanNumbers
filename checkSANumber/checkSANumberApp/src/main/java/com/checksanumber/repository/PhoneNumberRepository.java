package com.checksanumber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.checksanumber.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long>{
	
	public List<PhoneNumber> findByTipoNum(String typeValidation);
	
}