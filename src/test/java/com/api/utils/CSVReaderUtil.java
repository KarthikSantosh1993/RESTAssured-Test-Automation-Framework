package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	private CSVReaderUtil() {

	}

	@SuppressWarnings("unchecked")
	public static <T> Iterator<T> loadCSV(String pathOfCSvFile, Class<T> bean) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSvFile);
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(streamReader);
		
		// code to map CSV to POJO
		
		//Class<UserBean> bean = UserBean.class;
		@SuppressWarnings("rawtypes")
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();

		List<T> list = csvToBean.parse();
		return list.iterator();
	}
}
