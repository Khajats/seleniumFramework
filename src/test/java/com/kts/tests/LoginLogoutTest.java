package com.kts.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import com.kts.pages.LoginPage;

public final class LoginLogoutTest extends BaseTest {

	private LoginLogoutTest() {
	}

	@Test
	public void loginLogoutTest() throws IOException {
		LoginPage login = new LoginPage();
		String title = login.username("abcdeabcde212").password("abcd1abcd").clickLogin().homePageTitle();
		assertEquals(title, "Dashboard");
	}

}
