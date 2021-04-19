package com.checksanumber.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.checksanumber.model.PhoneNumber;
import com.checksanumber.repository.PhoneNumberRepository;
import com.checksanumber.utils.ApacheCommonsCsvUtil;
import com.checksanumber.utils.UtilsNumber;


@Service
public class CsvFileServices {

	@Autowired PhoneNumberRepository phoneNumberRepository;

	@Transactional
	public List<PhoneNumber> loadFile(MultipartFile csvFile) throws IOException {
		try {
			phoneNumberRepository.deleteAll();
			List<PhoneNumber> listaNumber = ApacheCommonsCsvUtil.parseCsvFile(csvFile.getInputStream());
			try {
				//replace every number of list with numbers checked
				listaNumber = listaNumber.stream().map(number -> {
					PhoneNumber newNumber = UtilsNumber.checkNumber(number);
					phoneNumberRepository.saveAndFlush(newNumber);

					return newNumber;
				}).collect(Collectors.toList());
				//   phoneNumberRepository.save(new PhoneNumber(13, "34534534533", EnumTipoValidazione.VALIDO.getCodice()));
			}catch(Exception e) {
				System.out.print(e.getMessage());
			}
			return listaNumber;

			// Using Open CSV Utils to write Customer List objects to a Writer
			// OpenCsvUtil.customersToCsv(writer, customers);    		
		} catch(Exception e) {
			throw new RuntimeException("Fail! -> Message = " + e.getMessage());
		}
	}
}