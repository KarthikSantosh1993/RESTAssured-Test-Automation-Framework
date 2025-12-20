package com.database;

import java.io.IOException;
import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException, IOException {
		DatabaseManager.createConnection();
		long startTime = System.currentTimeMillis();
		for (int i=0;i<100;i++) {
			DatabaseManager.createConnection();
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("Duration of test " + (endTime - startTime) + " ms");
	}

}
 