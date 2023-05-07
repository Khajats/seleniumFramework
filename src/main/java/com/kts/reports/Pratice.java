package com.kts.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class Pratice {

	public static void main(String[] args) throws IOException {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("report.html");
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY })
				.apply();
		ExtentSparkReporter failed = new ExtentSparkReporter("failedreport.htm").filter().statusFilter()
				.as(new Status[] { Status.FAIL }).apply();
		extent.attachReporter(spark);
		extent.attachReporter(failed);

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("practice report");
		spark.config().setReportName("report");

		ExtentTest test = extent.createTest("login test").assignAuthor("khaja").assignCategory("smoke")
				.assignDevice("chrome");
		test.info("details enter succusfully");
		Map<String, String> map = new HashMap<>();
		map.put("selenium", "SDET");
		map.put("java", "tmb");
		map.put("testng", "qafox");
		test.info(MarkupHelper.createUnorderedList(map));
		test.pass(MarkupHelper.createLabel("login test is pass", ExtentColor.GREEN));
		test.pass("login test is pass");

		ExtentTest test1 = extent.createTest("signup test").assignAuthor("preetham").assignCategory("regression")
				.assignDevice("firefox");
		test1.info("sign -test");
		test1.info("details enter");
		test1.fail("signup test failed");

		extent.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		// Desktop.getDesktop().browse(new File("failedreport.html").toURI());

	}

}
