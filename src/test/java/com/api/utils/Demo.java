package com.api.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class Demo {

	public static void main(String[] args) {
		Faker faker = new Faker(new Locale("en-IND"));
		String name= faker.name().fullName();
		System.out.println(name);
		
		System.out.println(faker.address().buildingNumber());
		System.out.println(faker.address().streetAddress());
		System.out.println(faker.address().city());
		
		
		System.out.println(faker.number().digits(5));
		System.out.println(faker.numerify("+91 890########"));
		System.out.println(faker.internet().emailAddress());
		System.out.println(faker.phoneNumber().phoneNumber());
	}
}
