package com.checksanumber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.checksanumber.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long>{
	
	
	@Query( name = "select n FROM PhoneNumber where n.tipoNum = ?1 order by n.id")
	public List<PhoneNumber> findByTipoNum(String typeValidation);
	
	
	
}