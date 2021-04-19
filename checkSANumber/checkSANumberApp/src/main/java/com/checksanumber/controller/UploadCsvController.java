package com.checksanumber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.checksanumber.message.Message;
import com.checksanumber.message.Response;
import com.checksanumber.model.PhoneNumber;
import com.checksanumber.service.CsvFileServices;
import com.checksanumber.utils.ApacheCommonsCsvUtil;


@RestController
@RequestMapping("/csv")
public class UploadCsvController {
	
	@Autowired
	private CsvFileServices csvFileServices;


	@PostMapping("/upload")
	public Response uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {
	
		Response response = new Response();
	
		// Checking the upload-file's name before processing
		if (csvfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(csvfile.getOriginalFilename(),
					"No selected file", "fail"));
	
			return response;
		}
	
		// checking the upload file's type is CSV or NOT
		
		if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) { 
		    response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: not a CSV file!", "fail")); 
	        return response; 
		}
		  
		try {
			// save file data to database
			List<PhoneNumber> listaNumbers = csvFileServices.loadFile(csvfile);
			
			response.setPayload(listaNumbers);
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
	}
}