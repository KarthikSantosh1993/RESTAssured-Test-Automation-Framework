package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnvironmentWriterUtil {
	private static final Logger LOG = LogManager.getLogger();
	
	public static void createEnvironmentPropertiesFile() {
		//properties file --  environment.properties 
		//create the properties file 
		
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdir();
		Properties properties = new Properties();
		properties.setProperty("Author", "karthik");
		properties.setProperty("ProjectName", "Phoenix test Automation Framework");
		properties.setProperty("Env", ConfigManager.env);
		properties.setProperty("BASEURI",ConfigManager.getProperty("BASEURI"));
		
		properties.setProperty("OperatingSystem", System.getProperty("os.name"));
		properties.setProperty("JavaVersion", System.getProperty("java.version"));
		FileWriter writer;
		try {
			writer = new FileWriter(folderPath+"/environment.properties");
			properties.store(writer, "My properties file");
			LOG.info("create environment.properties at {}",folderPath);
		} catch (IOException e) {
			LOG.error("unable to create environment.properties file", e);
			e.printStackTrace();
		}
	}

}
