package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HicariCPDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
		hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setMaxLifetime(1800000); //30 m =30*60*1000
		hikariConfig.setPoolName("RESTASSURED_Test Automation Framework pool");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT first_name, last_name, mobile_number  FROM tr_customer;");

		long startTime = System.currentTimeMillis();
		while (rs.next()) {
			System.out.println(rs.getString("first_name") + "| " + rs.getString("last_name") + "| "
					+ rs.getString("mobile_number") + "|");
		}

		long endTime = System.currentTimeMillis();
		System.out.println("Duration " + (endTime - startTime));
		dataSource.close();
	}

}
