package com.kts.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public final class ExtentReport {
	private static ExtentReports extent;
	private static ExtentTest extentTest;

	private ExtentReport() {

	}

	public static void initReport() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("./report.html");
			spark.viewConfigurer().viewOrder()
					.as(new ViewName[] { ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST }).apply();
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("kts report");
			spark.config().setReportName("Framework");
		}
	}

	public static void flushReport() throws IOException {
		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		Desktop.getDesktop().browse(new File("./report.html").toURI());

	}

	public static void createTest(String testname) {
		extentTest = extent.createTest(testname);
	}

}
