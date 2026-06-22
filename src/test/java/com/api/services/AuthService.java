package com.api.services;

import static com.api.utils.SpecUtil.requestSpec;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;

import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPOINT = "/login";
	private static final Logger LOG = LogManager.getLogger(AuthService.class);

	public Response login(UserCredentials userCredentials) {
		LOG.info("Making Login request for the payload - {}", userCredentials.username());
		Response response = given().spec(requestSpec(userCredentials)).when().post(LOGIN_ENDPOINT);

		return response;
	}
}
