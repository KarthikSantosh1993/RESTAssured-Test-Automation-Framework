package com.api.tests;

import static com.api.constants.Role.*;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPIRequestTest {

	@Test
	public void masterAPIRequest() throws IOException {
		given().spec(SpecUtil.requestSpecificationWithAuth(FD)) 	// application/url-formencoded	
		.when().post("master").then().spec(SpecUtil.responseSpec_OK())
				.body("message", Matchers.equalTo("Success")).body("data", Matchers.notNullValue())
				.body("data", Matchers.hasKey("mst_oem")).body("$", Matchers.hasKey("message"))
				.body("data.mst_oem.size()", Matchers.equalTo(2)) // check size of JSON Array
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue()))
				.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
	}
	
	@Test
	public void invalidTokenMasterAPirequest() throws IOException {
		given().spec(SpecUtil.requestSpec()).when().post("master").then().spec(SpecUtil.responseSpec_HTML(401));
	}
}
