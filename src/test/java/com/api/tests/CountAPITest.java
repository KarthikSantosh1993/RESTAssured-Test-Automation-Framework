package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	@Test
	public void verifyCountAPIRequest() throws IOException {
		given().spec(SpecUtil.requestSpecificationWithAuth(FD))
		.when().get("dashboard/count")
		.then().spec(SpecUtil.responseSpec_OK())
		.body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body("data.key",
						Matchers.containsInAnyOrder("pending_fst_assignment", "created_today", "pending_for_delivery"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema_FD.json"));
	}

	@Test
	public void countAPI_AuthToken_negative() throws IOException {
		given().spec(SpecUtil.requestSpec())
		.when().get("dashboard/count")
		.then().spec(SpecUtil.responseSpec_HTML(401));
	}
	
}
