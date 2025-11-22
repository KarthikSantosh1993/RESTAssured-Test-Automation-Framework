package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.utils.ConfigManager.*;

public class UserDetailsAPITest {

	@Test
	public void userDetailsAPItest() throws IOException {
		Header authHeader = new Header("Authorization", getToken(FD));
		given().baseUri(getProperty("BASEURI")).and().header(authHeader).and().accept(ContentType.ANY).log().uri().log()
				.method().log().body().and().when().get("userdetails/").then().log().all().statusCode(200)
				.time(lessThan(1500L)).and().body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
	}
}
