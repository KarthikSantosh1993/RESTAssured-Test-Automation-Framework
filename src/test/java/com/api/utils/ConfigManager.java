package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	// WAP to read the properties file from config.properties
	// Special Class in Java: Properties class
	private static Properties properties = new Properties();
	private static String path = "config" + File.separator + "config.properties";
	public static String env;

	private ConfigManager() {
	}

	static {
		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		System.out.println("======================> " + env);
		switch (env) {
		case "dev" -> path = "config" + File.separator + "config.dev.properties";
		case "qa" -> path = "config" + File.separator + "config.qa.properties";
		case "uat" -> path = "config" + File.separator + "config.uat.properties";
		default -> path = "config" + File.separator + "config.qa.properties";
		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		if (input == null) {
			throw new RuntimeException("Cannot find the file at the path" + path);
		}
		try {
			properties.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String property) throws IOException {
		return properties.getProperty(property);
	}
}
