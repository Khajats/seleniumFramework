package com.kts.reports;

public final class FrameLogger {
	private FrameLogger() {

	}

	public void pass(String message) {

		ExtentManager.getExtentTest().pass(message);
	}
}
