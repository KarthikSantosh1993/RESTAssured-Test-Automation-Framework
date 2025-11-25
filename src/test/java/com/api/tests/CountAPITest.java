package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {
	
	@Test(description="Verify count API response is shown correctly", groups = {"api", "smoke", "regression"})
	public void verifyCountAPIRequest() throws IOException {
		given().spec(requestSpecificationWithAuth(FD))
		.when().get("dashboard/count")
		.then().spec(responseSpec_OK())
		.body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body("data.key",
						Matchers.containsInAnyOrder("pending_fst_assignment", "created_today", "pending_for_delivery"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema_FD.json"));
	}

	@Test(description="Verify count API is giving correct status code for invalid token", groups = {"api", "negative", "smoke", "regression"})
	public void countAPI_AuthToken_negative() throws IOException {
		given().spec(requestSpec())
		.when().get("dashboard/count")
		.then().spec(responseSpec_HTML(401));
	}
	
}
