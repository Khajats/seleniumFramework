package com.kts.restUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.kts.reports.ExtentManager;
import com.kts.reports.FrameworkLogger;

import io.restassured.response.Response;

public class AssertionUtils {

    public static void assertExpectedValuesWithJsonPath(Response response, Map<String, Object> expectedValuesMap) {
        List<AssertionKeys> actualValuesMap = new ArrayList<>();
        // Table headers
        actualValuesMap.add(new AssertionKeys("JSON_PATH", "EXPECTED_VALUE", "ACTUAL_VALUE", "RESULT"));
        boolean allMatched = true;
        // Iterate to extract value from response using jsonpath
        Set<String> jsonPaths =  expectedValuesMap.keySet();
        for(String jsonPath : jsonPaths) {
            Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));
            if(actualValue.isPresent()) {
                Object value = actualValue.get();
                // Assert actual and expected values
                if(value.equals(expectedValuesMap.get(jsonPath)))
                    // if value is matched then add details
                    actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "MATCHED ✅"));
                else {
                    // if single assertion is failed then to update final result as failure
                    allMatched = false;
                    actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "NOT_MATCHED ❌"));
                }
            }
            // if jsonpath does not exist in the response
            else {
                allMatched = false;
                actualValuesMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), "VALUE_NOT_FOUND", "NOT_MATCHED ❌"));
            }
        }
        // To decide final result
        if(allMatched)
            FrameworkLogger.logPassDetails("All assertions are passed. 😊😊😊😊😊");
        else
        	FrameworkLogger.logFailureDetails("All assertions are not passed. 😒😒😒😒😒");

        // To log the details in a tabular format in extent report
        String[][] finalAssertionsMap = actualValuesMap.stream().map(assertions -> new String[] {assertions.getJsonPath(),
                String.valueOf(assertions.getExpectedValue()), String.valueOf(assertions.getActualValue()), assertions.getResult()})
                .toArray(String[][] :: new);
        ExtentManager.getExtentTest().info(MarkupHelper.createTable(finalAssertionsMap));
    }
}
