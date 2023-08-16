package com.kts.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.testng.annotations.DataProvider;

import com.kts.constants.FrameworkConstants;
import com.kts.pojos.Airline;

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

	@DataProvider(name = "airlineData")
	public Iterator<Airline> getCreateAirlineData() throws IOException {
		List<Map<String, String>> excelDataAsListOfMap = ExcelUtils.getTestDetails("Sheet1");
		System.out.println(excelDataAsListOfMap);
		List<Airline> airlineData = new ArrayList<>();
		for (Map<String, String> data : excelDataAsListOfMap) {
			Airline airline = Airline.builder().id(Integer.parseInt(data.get("Id"))).name(data.get("Name"))
					.country(data.get("Country")).logo(data.get("Logo")).established(data.get("Established"))
					.website(data.get("Website")).slogan(data.get("Slogan")).head_quaters(data.get("HeadQuarter"))
					.build();
			airlineData.add(airline);
		}
		return airlineData.iterator();
	}
}
