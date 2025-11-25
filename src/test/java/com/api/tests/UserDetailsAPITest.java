package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {

	@Test(description="Verify userdetails API response is shown correctly", groups = {"api", "smoke", "regression"})
	public void userDetailsAPItest() throws IOException {
		
		given().spec(requestSpecificationWithAuth(FD))
		.when().get("userdetails/")
		.then().spec(responseSpec_OK()).body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}
