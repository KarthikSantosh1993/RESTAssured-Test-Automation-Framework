package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPIRequestTest {

	@Test
	public void masterAPIRequest() throws IOException {
		RestAssured.given().baseUri(getProperty("BASEURI")).header("Authorization", getToken(FD)).contentType("") // default
																													// content-type
																													// application/url-formencoded
				.log().all().when().post("master").then().log().all().statusCode(200).time(Matchers.lessThan(1000L))
				.body("message", Matchers.equalTo("Success")).body("data", Matchers.notNullValue())
				.body("data", Matchers.hasKey("mst_oem")).body("$", Matchers.hasKey("message"))
				.body("data.mst_oem.size()", Matchers.equalTo(2)) // check size of JSON Array
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidTokenMasterAPirequest() throws IOException {
		RestAssured.given().baseUri(getProperty("BASEURI")).header("Authorization", "").contentType("") // default
				.log().all().when().post("master").then().log().all().statusCode(401);
	}
}
