package com.listener;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestListener implements ITestListener {
	private static Logger LOG = LogManager.getLogger(APITestListener.class);

	public void onTestStart(ITestResult result) {
		LOG.info("***********************************************************************************");
		LOG.info("========== Starting the test {} ==========", result.getName());
		LOG.info("========== Test class name: {} ==========", result.getMethod().getTestClass());
		LOG.info("========== Description of Test: {} ==========", result.getMethod().getDescription());
		LOG.info("========== Groups associalted with Test: {} ==========",
				Arrays.toString(result.getMethod().getGroups()));
	}

	public void onTestSuccess(ITestResult result) {
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();

		LOG.info("Total duration: {} ms ", (endTime - startTime));
		LOG.info("{} - Test passed!", result.getName());
	}

	public void onTestFailure(ITestResult result) {
		LOG.error("{} - Test Failed !", result.getName());
		LOG.error("Error Message {}", result.getThrowable().getMessage());
		LOG.error(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		LOG.info("Test skipped {}", result.getName());
		LOG.error(result.getThrowable());
	}

	public void onStart(ITestContext context) {
		LOG.info("************************* Starting the Framework*************************");
	}

	public void onFinish(ITestContext context) {
		LOG.info("************************* Ending the Framework*************************");
	}
}
