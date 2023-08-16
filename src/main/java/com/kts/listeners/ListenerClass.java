package com.kts.listeners;

import static com.kts.enums.LogType.FAIL;
import static com.kts.enums.LogType.PASS;
import static com.kts.enums.LogType.SKIP;
import static com.kts.reports.FrameworkLogger.log;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.kts.annotation.FrameworkAnnotation;
import com.kts.reports.ExtentReport;
import com.kts.utils.ELKUtils;

public class ListenerClass implements ITestListener, ISuiteListener {

	
	@Override
	public void onStart(ISuite suite) {
			ExtentReport.initReports();
	}

	
	@Override
	public void onFinish(ISuite suite) {
			ExtentReport.flushReports();
			
	}

	
	@Override
	public void onTestStart(ITestResult result) {
	
		ExtentReport.createTest(result.getMethod().getDescription());
		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
			.author());
		ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
			.category());
	}

	
	@Override
	public void onTestSuccess(ITestResult result) {
		//ExtentLogger.pass(result.getMethod().getMethodName() +" is passed");
		log(PASS, result.getMethod().getMethodName() +" is passed");
		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "pass");
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
			log(FAIL,result.getMethod().getMethodName() +" is failed");
			log(FAIL,result.getThrowable().toString());
			log(FAIL,Arrays.toString(result.getThrowable().getStackTrace()));
			ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "fail");
	}

	/**
	 * Marks the test as skip and logs it in the report
	 * @see com.tmb.reports.FrameworkLogger
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		//ExtentLogger.skip(result.getMethod().getMethodName() +" is skipped");
		log(SKIP,result.getMethod().getMethodName() +" is skipped");
		ELKUtils.sendDetailsToElk(result.getMethod().getDescription(), "skip");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		/*
		 * For now, we are not using this.
		 */
	}

	@Override
	public void onStart(ITestContext context) {
		/*
		 * We are having just one test in our suite. So we dont have any special implementation as of now
		 */
	}

	@Override
	public void onFinish(ITestContext context) {
		/*
		 * We are having just one test in our suite. So we dont have any special implementation as of now
		 */
		
	}
}
