package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPItest() throws IOException {
		
		given()
		.spec(SpecUtil.requestSpecificationWithAuth(FD))
		.when().get("userdetails/").
		
		then().spec(SpecUtil.responseSpec_OK()).body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}
