package com.kts.pagefactorypages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactoryPage implements SearchContext {

	private WebDriver driver;

	public LoginPageFactoryPage(WebDriver ldriver) {
		this.driver = ldriver;
		PageFactory.initElements(this, ldriver);
	}

	@CacheLookup
	@FindBy(id = "Username")
	WebElement txtUsername;

	@CacheLookup
	@FindBy(id = "Password")
	WebElement txtPassword;

	@CacheLookup
	@FindBy(id = "login")
	WebElement btnlogin;

	public LoginPageFactoryPage setTxtUsername(String Username) {
		txtUsername.sendKeys(Username);
		return this;
	}

	public LoginPageFactoryPage setTxtPassword(String Password) {
		txtPassword.sendKeys(Password);
		return this;
	}

	public HomePageFactoryPage clickBtnlogin() {
		btnlogin.click();
		return new HomePageFactoryPage(driver);
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

}
