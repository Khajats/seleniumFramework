package com.kts.pages;

import org.openqa.selenium.By;

import com.kts.enums.WaitStrategy;

public final class HomePage extends BasePage {

	private final By homePageTitle = By.xpath("//h1");
	private final By logout = By.linkText("Log out");

	public String homePageTitle() {
		return getText(homePageTitle);
	}

	public LoginPage clickLogout() {
		click(logout, WaitStrategy.CLICKABLE);
		return new LoginPage();
	}
}
