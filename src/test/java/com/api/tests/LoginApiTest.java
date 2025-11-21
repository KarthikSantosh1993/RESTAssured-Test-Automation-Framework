package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.api.pojo.UserCredentials;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {

	@Test
	public void loginAPITest() {

		UserCredentials userCredentials= new UserCredentials("iamfd", "password");
		
		given()
			.baseUri("http://64.227.160.186:9000/v1/").and()
			.contentType(ContentType.JSON)
			.body(userCredentials)
			.log().all()
		.when()
			.post("login")
		.then()
			.log().all()
			.statusCode(200)
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}
}
