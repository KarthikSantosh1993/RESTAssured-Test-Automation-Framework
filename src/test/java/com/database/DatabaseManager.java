package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.api.utils.ConfigManager.*;

public class DatabaseManager {
	private static final String DB_URL = getProperty("DB_URL");
	private static final String DB_USERNAME = getProperty("DB_USERNAME");
	private static final String DB_PASSWORD = getProperty("DB_PASSWORD");
	private volatile static Connection connection; // volatile -- any update happens to connection variable all threads are aware
	
	private DatabaseManager() {
	}

	public static void createConnection() throws SQLException {
		if (connection == null) { //1st check which all parallel threads enter 
			synchronized (DatabaseManager.class) {
				if(connection == null) {
					connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					System.out.println(connection);
				}
			}		
		}
	}

	public static void main(String[] args) throws SQLException {
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();
	}
}