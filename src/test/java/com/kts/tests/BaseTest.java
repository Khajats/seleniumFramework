package com.kts.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.kts.driver.Driver;

public class BaseTest {

	ExtentReports extent;

	protected BaseTest() {

	}

	@BeforeMethod
	protected void setUp() {
		Driver.initDriver();
	}

	@AfterMethod
	protected void tearDown() {
		Driver.quitDriver();
	}

}
