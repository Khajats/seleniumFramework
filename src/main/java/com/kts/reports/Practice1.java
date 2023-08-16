package com.kts.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.kts.utils.ScreenshortUtils;

public class Practice1 {

	ExtentReports extent;
	WebDriver driver;

	@BeforeSuite
	public void initReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("report.html");
		spark.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY })
				.apply();
		ExtentSparkReporter failed = new ExtentSparkReporter("failedreport.html").filter().statusFilter()
				.as(new Status[] { Status.FAIL }).apply();
		extent.attachReporter(spark);
		extent.attachReporter(failed);
		spark.config().setDocumentTitle("selenium");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("report");

		extent.setSystemInfo("username", "khaja ts");
		extent.setSystemInfo("browser", "chrome");
		extent.setSystemInfo("java version", "java 18");
	}

	@AfterSuite
	public void flushReport() throws IOException {
		extent.flush();
		driver.quit();
		Desktop.getDesktop().browse(new File("report.html").toURI());
		Desktop.getDesktop().browse(new File("failedreport.html").toURI());

	}

	@Test
	public void googleTest1() throws IOException {
		ExtentTest test1 = extent.createTest("google search1");
		test1.assignAuthor("khaja").assignCategory("sanity").assignDevice("chrome");
		driver = new ChromeDriver();
		driver.get("https:/google.com");
		test1.info("browser open");
		driver.manage().timeouts().implicitlyWait(0, null);
		driver.findElement(By.name("q")).sendKeys("automation", Keys.ENTER);
		test1.info("detailes enter");
		// test.pass("test is passed",
		// MediaEntityBuilder.createScreenCaptureFromPath(getScreenshortpath()).build());
		/*
		 * test.pass("test is pass",
		 * MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshortBase64()
		 * ).build());
		 */
		test1.pass("test is passed",
				MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshortUtils.getScreenBase64()).build());
	}

	@Test
	public void googletest2() {
		ExtentTest test2 = extent.createTest("google search2");
		test2.assignAuthor("sameer").assignCategory("sanity").assignDevice("chrome");
		driver = new ChromeDriver();
		driver.get("https:/google.com");
		test2.info("browser open");
		driver.manage().timeouts().implicitlyWait(0, null);
		driver.findElement(By.name("q")).sendKeys("postman", Keys.ENTER);
		test2.info("detailes enter");
		test2.fail("test is failed",
				MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshortUtils.getScreenBase64()).build());
	}

}
