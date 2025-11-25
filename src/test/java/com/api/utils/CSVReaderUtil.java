package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	private CSVReaderUtil() {

	}

	public static void loadCSV(String pathOfCSvFile) {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSvFile);
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(streamReader);

		// code to map CSV to POJO
		CsvToBean<com.dataproviders.api.bean.UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();

		List<UserBean> userList = csvToBean.parse();

		System.out.println(userList);
		System.out.println(userList.get(0).getUsername());
	}
}
