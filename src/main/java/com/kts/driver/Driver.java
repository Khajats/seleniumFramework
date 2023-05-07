package com.kts.driver;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class Driver {

	private Driver() {
	}

	public static void initDriver() {
		if (Objects.isNull(DriverManager.getDriver())) {
			WebDriver driver = new ChromeDriver();
			DriverManager.setDriver(driver);
		}
		DriverManager.getDriver().get("https://itera-qa.azurewebsites.net/Login");
		DriverManager.getDriver().manage().window().maximize();
	}

	public static void quitDriver() {
		if (Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
