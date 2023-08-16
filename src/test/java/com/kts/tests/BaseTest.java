package com.kts.tests;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.kts.driver.Driver;

public class BaseTest {

	ExtentReports extent;

	protected BaseTest() {

	}
	@SuppressWarnings("unchecked")
	@BeforeMethod
	protected void setUp(Object[] data) { //Map<String,String>
		Map<String,String> map = (Map<String,String>)data[0];
		Driver.initDriver(map.get("browser"),map.get("version"));
	}

	@AfterMethod
	protected void tearDown() {
		Driver.quitDriver();
	}

}
