package com.api.utils;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constants.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	// Common request sections (method) for GET & DELETE
	public static RequestSpecification requestSpec() throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASEURI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD)
				.log(LogDetail.HEADERS).log(LogDetail.BODY).build();
		return request;

	}
	// Common request sections (method) for GET & DELETE for a Role
	public static RequestSpecification requestSpecificationWithAuth(Role role) throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASEURI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(Role.FD)).log(LogDetail.URI)
				.log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
		return request;
	}
	
	public static RequestSpecification requestSpecificationWithAuth(Role role, Object payload) throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASEURI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(Role.FD))
				.setBody(payload).log(LogDetail.URI)
				.log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();
		return request;
	}

	// Common request sections (method) for POST. PUT, PATCH
	public static RequestSpecification requestSpec(Object userCred) {
		RequestSpecification request = null;
		request = new RequestSpecBuilder().setBaseUri(ConfigManager.getProperty("BASEURI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(userCred).log(LogDetail.URI)
				.log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();

		return request;
	}

	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification response = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(Matchers.lessThan(10000L)).log(LogDetail.ALL).build();

		return response;
	}

	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification response = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(10000L)).log(LogDetail.ALL).build();

		return response;
	}

	public static ResponseSpecification responseSpec_HTML(int statusCode) {
		ResponseSpecification response = new ResponseSpecBuilder().expectContentType(ContentType.HTML)
				.expectStatusCode(statusCode).expectResponseTime(Matchers.lessThan(10000L)).log(LogDetail.ALL).build();

		return response;
	}

}
