package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.api.utils.ConfigManager.*;

public class DatabaseManager {
	private static final String DB_URL = getProperty("DB_URL");
	private static final String DB_USERNAME = getProperty("DB_USERNAME");
	private static final String DB_PASSWORD = getProperty("DB_PASSWORD");
	private volatile static Connection connection; // any update that happens to connection variable, all threads are
													// aware of this

	private DatabaseManager() {
	}

	public static void createConnection() throws SQLException {
		if (connection == null) {
			synchronized (DatabaseManager.class) {
				if (connection == null) {
					connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
				}
				System.out.println(connection);
			}
		}
	}
}
