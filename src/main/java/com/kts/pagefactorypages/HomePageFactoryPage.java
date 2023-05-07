package com.kts.pagefactorypages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactoryPage implements SearchContext {

	private WebDriver driver;

	public HomePageFactoryPage(WebDriver ldriver) {
		this.driver = ldriver;
		PageFactory.initElements(this, ldriver);
	}

	@CacheLookup
	@FindBy(xpath = "//h1")
	WebElement homePageTitile;

	@CacheLookup
	@FindBy(linkText = "Log out")
	WebElement lnkLogout;

	public String getHomePageTitile() {
		return homePageTitile.getText();
	}

	public LoginPageFactoryPage clickLnkLogout() {
		lnkLogout.click();
		return new LoginPageFactoryPage(driver);
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
