package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPIRequestTest {

	@Test(description="Verify master API response is shown correctly", groups = {"api", "smoke", "regression"})
	public void masterAPIRequest() throws IOException {
		given().spec(requestSpecificationWithAuth(FD)) 	// application/url-formencoded	
		.when().post("master").then().spec(responseSpec_OK())
				.body("message", Matchers.equalTo("Success")).body("data", Matchers.notNullValue())
				.body("data", Matchers.hasKey("mst_oem")).body("$", Matchers.hasKey("message"))
				.body("data.mst_oem.size()", Matchers.equalTo(2)) // check size of JSON Array
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
				.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test(description="Verify master API is giving correct status code for invalid token", groups = {"api", "negative", "smoke", "regression"})
	public void invalidTokenMasterAPirequest() throws IOException {
		given().spec(requestSpec()).when().post("master").then().spec(responseSpec_HTML(401));
	}
}
