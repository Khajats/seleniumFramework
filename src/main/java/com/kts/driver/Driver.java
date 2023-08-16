package com.kts.driver;

import java.net.MalformedURLException;
import java.util.Objects;

import com.kts.enums.ConfigProperties;
import com.kts.factories.DriverFactory;
import com.kts.utils.PropertyUtils;

public final class Driver {

	private Driver() {}


	public static void initDriver(String browser,String version)  {

		
		if(Objects.isNull(DriverManager.getDriver())) {
			try {
				DriverManager.setDriver(DriverFactory.getDriver(browser,version));
			
			} catch (MalformedURLException e) {
				throw new RuntimeException("Please check the capabilities of browser");
			}
			DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
		}
	}

	
	public static void quitDriver() {
		if(Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}

}
