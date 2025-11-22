package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerArchived {
	//WAP to read the properties file from config.properties
	// Special Class in Java: Properties class
	private static Properties properties = new Properties();
	
	private ConfigManagerArchived() {
		
	}

	static {
		File configFile = new File(System.getProperty("user.dir")
				+File.separator+"src"+File.separator+"test"+ File.separator+ "resources"
				+File.separator+ "config"+File.separator + "config.properties");
		FileReader fileReader;
		try {
			fileReader = new FileReader(configFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String property) throws IOException {
		return properties.getProperty(property);
	}
}
  