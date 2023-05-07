package com.kts.reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	static ExtentTest getExtentTest() {
		return test.get();
	}

	static void setExtentTest(ExtentTest extentTest) {
		if (Objects.isNull(extentTest)) {
			test.set(extentTest);
		}
	}

	static void unload() {
		test.remove();
	}

}
