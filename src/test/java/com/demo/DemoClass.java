package com.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoClass {
	private static Logger log = LogManager.getLogger(DemoClass.class);

	public static void main(String[] args) {
		log.info("inside main method");
		int a = 10;
		log.info("value of a is {}", a);
		int b = 0;
		if (b == 0) {
			log.warn("value of b is {}", b);
		} else {
			log.info("value of b is {}", b);
		}
		try {
			int result = a / b;
			log.info("Final Result is {}", result);
		} catch (Exception e) {
			log.error("Operaion cannot happen ", e);
		}
		log.info("program Ended");
	}
}
