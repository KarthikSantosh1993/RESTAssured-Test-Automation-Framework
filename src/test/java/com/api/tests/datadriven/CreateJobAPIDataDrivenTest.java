package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecificationWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

public class CreateJobAPIDataDrivenTest {
	
	
	@Test(description="Verify Create job API response is shown correctly", groups = {"api", "datadriven", "regression", "csv"}, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider= "CreateJobDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) throws IOException {

		given().spec(requestSpecificationWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));
	}
}