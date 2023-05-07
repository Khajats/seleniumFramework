package com.kts.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.kts.driver.DriverManager;

public final class Screenshort {

	private Screenshort() {

	}

	public static void screenshortOfPage() {
		TakesScreenshot screenshort = (TakesScreenshot) DriverManager.getDriver();
		File src = screenshort.getScreenshotAs(OutputType.FILE);
		File trg = new File("./screenshort/" + System.currentTimeMillis() + "login.png");
		try {
			FileUtils.copyFile(src, trg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getScreenshortpath() throws IOException {
		TakesScreenshot screenshort = (TakesScreenshot) DriverManager.getDriver();
		File src = screenshort.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/software/image.png";
		FileUtils.copyDirectory(src, new File(path));
		return path;

	}

	public static String getScreenshortBase64() throws IOException {
		TakesScreenshot screenshort = (TakesScreenshot) DriverManager.getDriver();
		File src = screenshort.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/software/image.png";
		FileUtils.copyDirectory(src, new File(path));
		byte[] imageByte = IOUtils.toByteArray(new FileInputStream(path));
		return Base64.getEncoder().encodeToString(imageByte);
	}

	public static String getScreenBase64() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
