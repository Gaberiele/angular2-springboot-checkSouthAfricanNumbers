package com.checksanumber.utils;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.checksanumber.model.PhoneNumber;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


public class OpenCsvUtil {

	public static List<PhoneNumber> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "id", "name", "address", "age" };
		Reader fileReader = null;
		CsvToBean<PhoneNumber> csvToBean = null;
	
		List<PhoneNumber> customers = new ArrayList<PhoneNumber>();
		
		try {
			fileReader = new InputStreamReader(is);
	
			ColumnPositionMappingStrategy<PhoneNumber> mappingStrategy = new ColumnPositionMappingStrategy<PhoneNumber>();
	
			mappingStrategy.setType(PhoneNumber.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<PhoneNumber>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
	
			customers = csvToBean.parse();
			
			return customers;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		
		return customers;
	}

	public static void customersToCsv(Writer writer, List<PhoneNumber> customers) {
		String[] CSV_HEADER = { "id", "name", "address", "age" };
	    
	    StatefulBeanToCsv<PhoneNumber> beanToCsv = null;
	 
	    try {
	      // write List of Objects
	      ColumnPositionMappingStrategy<PhoneNumber> mappingStrategy = 
	                new ColumnPositionMappingStrategy<PhoneNumber>();
	      
	      mappingStrategy.setType(PhoneNumber.class);
	      mappingStrategy.setColumnMapping(CSV_HEADER);
	      
	      beanToCsv = new StatefulBeanToCsvBuilder<PhoneNumber>(writer)
	          .withMappingStrategy(mappingStrategy)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
	 
	      beanToCsv.write(customers);
	    } catch (Exception e) {
	      System.out.println("Writing CSV error!");
	      e.printStackTrace();
	    }
	}
}
