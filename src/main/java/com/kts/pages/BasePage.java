package com.kts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.kts.driver.DriverManager;
import com.kts.enums.WaitStrategy;
import com.kts.factories.ExplicityWaitFactory;

public class BasePage {
	protected BasePage() {

	}

	protected void sendKey(By by, String value, WaitStrategy waitingStrategy) {
		WebElement element = ExplicityWaitFactory.explicityWaitStrategy(waitingStrategy, by);
		element.sendKeys(value);
	}

	protected void click(By by, WaitStrategy waitingStrategy) {
		WebElement element = ExplicityWaitFactory.explicityWaitStrategy(waitingStrategy, by);
		element.click();
	}

	protected String getText(By by) {
		return DriverManager.getDriver().findElement(by).getText();
	}

}
