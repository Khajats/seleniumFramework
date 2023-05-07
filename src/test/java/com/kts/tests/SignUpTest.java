package com.kts.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.kts.driver.DriverManager;

public final class SignUpTest extends BaseTest {

	private SignUpTest() {

	}

	@Test
	public void signUp() throws IOException {
		DriverManager.getDriver().findElement(By.linkText("Sign Up")).click();
		DriverManager.getDriver().findElement(By.id("FirstName")).sendKeys("abcdkts");
		DriverManager.getDriver().findElement(By.id("Surname")).sendKeys("abcdkts");
		DriverManager.getDriver().findElement(By.id("E_post")).sendKeys("abcdkts@gmail.com");
		DriverManager.getDriver().findElement(By.id("Mobile")).sendKeys("9535230088");
		DriverManager.getDriver().findElement(By.id("Username")).sendKeys("ancdeabcde313");
		DriverManager.getDriver().findElement(By.id("Password")).sendKeys("abcde12abcde");
		DriverManager.getDriver().findElement(By.id("ConfirmPassword")).sendKeys("abcde12abcde");
		DriverManager.getDriver().findElement(By.id("submit")).click();

		// for portion of page screenshot using element
		WebElement section = DriverManager.getDriver().findElement(By.xpath("//div[@class=\"form-horizontal\"]"));
		File src = section.getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshort/" + System.currentTimeMillis() + "signup.png");
		FileUtils.copyFile(src, destination);

//		// for particular element
//		WebElement element = driver.findElement(By.xpath("//label[text()='Registration Successful']"));
//		File source = element.getScreenshotAs(OutputType.FILE);
//		File trg = new File("./screenshort/" + System.currentTimeMillis() + "signupSuccesfull.png");
//		FileUtils.copyFile(source, trg);
	}

}
