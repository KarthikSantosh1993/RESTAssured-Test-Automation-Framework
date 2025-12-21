package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;

import static com.api.utils.SpecUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description= "Creating createjobapirequest payload")
	public void setup() {
		Customer customer = new Customer("karthik", "D", "0987654321", "", "krthiksantosh@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("2A", "TSH", "ABC Street", "near SS SWIMMING POOL", "LPI", "530002", "IN", "TG");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "87061348153532", "87061348153532", "87061348153532", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NEXUS2_BLUE.getCode());
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(), "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);

		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONTDESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemsList);
	}
	
	@Test(description="Verify Create job API response is shown correctly", groups = {"api", "smoke", "regression"})
	public void createJobAPITest() throws IOException {

		given().spec(requestSpecificationWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));
	}
}