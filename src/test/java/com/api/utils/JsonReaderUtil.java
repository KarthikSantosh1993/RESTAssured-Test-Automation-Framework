package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	public static <T> Iterator<T> loadJSON(String path, Class<T[]> mapperClass) {
		InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		ObjectMapper objectMapper = new ObjectMapper();
		T[] mapperClassArray ;
		List<T> mapperClassList = null;
		try {
			mapperClassArray = objectMapper.readValue(jsonStream, mapperClass);
			mapperClassList =  Arrays.asList(mapperClassArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapperClassList.iterator();
	}
}