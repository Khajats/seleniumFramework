package com.kts.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.testng.annotations.DataProvider;

import com.kts.constants.FrameworkConstants;


public final class DataProviderUtils {

	
	private DataProviderUtils() {
	}

	private static List<Map<String, String>> list = new ArrayList<>();

	
	@DataProvider(parallel = false)
	public static Object[] getData(Method m) {
		String testname = m.getName();

		if (list.isEmpty()) {
			list = ExcelUtils.getTestDetails(FrameworkConstants.getIterationDatasheet());
			System.out.println(list);
		}
		List<Map<String, String>> smalllist = new ArrayList<>(list);
		/*
		 * for (int i = 0; i < list.size(); i++) { if
		 * (list.get(i).get("testname").equalsIgnoreCase(testname)) { if
		 * (list.get(i).get("execute").equalsIgnoreCase("yes")) {
		 * smalllist.add(list.get(i)); } } }
		 */

		Predicate<Map<String, String>> isTestNameNotMatching = map -> !map.get("testname").equalsIgnoreCase(testname);
		Predicate<Map<String, String>> isExecuteColumnNo = map -> map.get("execute").equalsIgnoreCase("no");

		smalllist.removeIf(isTestNameNotMatching.or(isExecuteColumnNo));
		return smalllist.toArray();

	}
}
