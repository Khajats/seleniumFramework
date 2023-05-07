package com.kts.pages;

import org.openqa.selenium.By;

import com.kts.enums.WaitStrategy;

public final class LoginPage extends BasePage {

	private final By txtUsername = By.id("Username");
	private final By txtPassword = By.id("Password");
	private final By btnlogin = By.name("login");

	public LoginPage username(String username) {
		sendKey(txtUsername, username, WaitStrategy.CLICKABLE);
		return this;
	}

	public LoginPage password(String pwd) {
		sendKey(txtPassword, pwd, WaitStrategy.PRESENCE);
		return this;
	}

	public HomePage clickLogin() {
		click(btnlogin, WaitStrategy.CLICKABLE);
		return new HomePage();
	}

}
