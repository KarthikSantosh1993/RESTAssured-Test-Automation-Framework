package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	public static <T> Iterator<T> loadJSON(String path, Class<T[]> mapperClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		T[] mapperClassArray ;
		List<T> mapperClassList = null;
		
		try {
			mapperClassArray = objectMapper.readValue(jsonStream, mapperClass);
			mapperClassList= (List<T>) Arrays.asList(mapperClassArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapperClassList.iterator();
	}
}