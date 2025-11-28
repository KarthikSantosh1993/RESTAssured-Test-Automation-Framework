package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecificationWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;

public class CreateJobAPITest2 {
	private CreateJobPayload createJobPayload;
	private static final String COUNTRY= "India";
	
	@BeforeMethod(description= "Creating createjobapirequest payload")
	public void setup() {
		
		@SuppressWarnings("deprecation")
		Faker faker = new Faker(new Locale("en-IND"));
		String firstName= faker.name().firstName();
		String lasttName= faker.name().lastName();
		String mobileNumber= faker.numerify("738#######");
		String alternateMobileNumber= faker.numerify("738#######");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();
		
		Customer customer= new Customer(firstName, lasttName, mobileNumber, alternateMobileNumber, customerEmailAddress, altCustomerEmailAddress);
		
		
		String flatNumber= faker.numerify("###");
		String apartmentName= faker.address().streetName();
		String streetName= faker.address().streetName();
		String landmark= faker.address().streetName();
		String area = faker.address().streetName();
		String pincode= faker.numerify("#####");
		String state= faker.address().state();
		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pincode, COUNTRY, state);
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imei_serial_number = faker.numerify("###############"); 
		String popurl= faker.internet().url(); 

		CustomerProduct customerProduct = new CustomerProduct(dop, imei_serial_number, imei_serial_number, imei_serial_number, popurl, 1, 1);
		
		String remarks= faker.lorem().sentence(3);
		Random random = new Random();
		int problemId= random.nextInt(27)+1;
		
		Problems problems = new Problems(problemId, remarks);
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problems);
		
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);	
		System.out.println(createJobPayload);
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