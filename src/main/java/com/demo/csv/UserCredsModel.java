package com.demo.csv;

import com.opencsv.bean.CsvBindByName;

public class UserCredsModel {
	@CsvBindByName(column = "username")
	private String username;
	@CsvBindByName(column = "password")
	private String password;
	
	public UserCredsModel() {
		
	}
	public UserCredsModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserPOJO [username=" + username + ", password=" + password + "]";
	}
	
	

}
