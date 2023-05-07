package com.kts.factories;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kts.driver.DriverManager;
import com.kts.enums.WaitStrategy;

public final class ExplicityWaitFactory {

	private ExplicityWaitFactory() {

	}

	public static WebElement explicityWaitStrategy(WaitStrategy waitingStrategy, By by) {
		WebElement element = null;
		switch (waitingStrategy) {
		case CLICKABLE:
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
					.until(ExpectedConditions.elementToBeClickable(by));
			break;
		case PRESENCE:
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfElementLocated(by));
			break;
		case VISIBLE:
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
			break;
		case NONE:
			element = DriverManager.getDriver().findElement(by);
			break;
		}
		return element;
	}

}
