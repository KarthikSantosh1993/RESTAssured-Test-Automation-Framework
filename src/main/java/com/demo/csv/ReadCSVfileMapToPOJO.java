package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVfileMapToPOJO {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, CsvException {
		//Read CSV File  csvReader=> input stream reader=> input stream
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testdata/logincreds.csv");
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(streamReader);
		
		
		List<String[]> dataList = csvReader.readAll();

		for (String[] dataArray : dataList) {
			System.out.println(dataArray[0]);
			System.out.println(dataArray[1]);
		}
	}
}
