package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import java.io.IOException;

import com.api.pojo.UserCredentials;
import static com.api.utils.ConfigManager.*;

public class LoginApiTest {

	@Test
	public void loginAPITest() throws IOException {
		UserCredentials userCredentials= new UserCredentials("iamfd", "password");
		
		given()
			.baseUri(getProperty("BASEURI"))
			.and()
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
