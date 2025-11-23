package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() throws IOException {
		Customer customer = new Customer("karthik", "D", "0987654321", "", "krthiksantosh@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("2A", "TSH", "ABC Street", "near SS SWIMMING POOL", "LPI",
				"530002", "IN", "TG");
		CustomerProduct customerProduct = new CustomerProduct("2025-05-07T18:30:00.000Z", "87061348153530",
				"87061348153530", "87061348153530", "2025-05-07T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);

		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct,
				problemsList);

		given().spec(SpecUtil.requestSpecificationWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));
	}
}
