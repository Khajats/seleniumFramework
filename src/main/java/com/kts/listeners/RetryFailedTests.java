package com.kts.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.kts.enums.ConfigProperties;
import com.kts.utils.PropertyUtils;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count=0;
	private int retries = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		boolean value =false;

		if(PropertyUtils.get(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")) {
			value = count<retries ;
			count++;
		} 
		return value;
	}
}
