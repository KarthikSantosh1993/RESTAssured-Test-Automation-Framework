package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.services.AuthService;

@Listeners(com.listener.APITestListener.class)
public class LoginAPITest {
	private UserCredentials userCredentials;
	private AuthService authService;
	@BeforeMethod(description="create payload for Login API")
	public void setup() {
		 userCredentials = new UserCredentials("iamfd", "password");
		 authService = new AuthService();
	}
	
	@Test(description = "Verify if login api is working for user iamfd", groups = {"api","regression", "smoke"})
	public void loginAPITest() throws IOException {
		
		authService.login(userCredentials)
		.then().spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.and().body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}	
}
