package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role,String>();
	
	public static String getToken(Role role) throws IOException {
		if(tokenCache.containsKey(role)) {
			return tokenCache.get(role);
		}
		
		UserCredentials userCredentials = null;
		if (role == FD) {
			userCredentials = new UserCredentials("iamfd", "password");
		} else if (role == SUP) {
			userCredentials = new UserCredentials("iamsup", "password");
		} else if (role == ENG) {
			userCredentials = new UserCredentials("iameng", "password");
		} else if (role == QC) {
			userCredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(getProperty("BASEURI")).contentType(ContentType.JSON)
				.body(userCredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).body("message", equalTo("Success")).extract().body().jsonPath()
				.getString("data.token");
		
		tokenCache.put(role, token);
		
		return token;
	}

}
