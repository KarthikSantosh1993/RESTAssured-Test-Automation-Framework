package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	static final String COUNTRY= "India";
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
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
		
		String remarks= faker.lorem().sentence(10);
		Random random = new Random();
		int problemId= random.nextInt(27)+1;
		
		Problems problems = new Problems(problemId, remarks);
		List<Problems> problemList= new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		System.out.println(createJobPayload);
	}
}
