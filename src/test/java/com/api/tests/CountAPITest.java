package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	@Test
	public void verifyCountAPIRequest() throws IOException {
		given().baseUri(getProperty("BASEURI")).header("Authorization", getToken(FD)).log().uri().log().method().when()
				.get("dashboard/count").then().log().all().statusCode(200).body("message", equalTo("Success"))
				.time(lessThan(1000L)).body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body("data.key",
						Matchers.containsInAnyOrder("pending_fst_assignment", "created_today", "pending_for_delivery"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema_FD.json"));
	}

	@Test
	public void countAPI_AuthToken_negative() throws IOException {
		given().baseUri(getProperty("BASEURI")).when().get("dashboard/count").then().log().all().statusCode(401);
	}
}
