package com.kts.restUtils;

import java.util.Map;

import com.kts.reports.FrameworkLogger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String endPoint, Object requestPayload, Map<String,String>headers) {
        return RestAssured.given()
                .baseUri(endPoint)
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(requestPayload);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        FrameworkLogger.logInfoDetails("Endpoint is " + queryableRequestSpecification.getBaseUri());
        FrameworkLogger.logInfoDetails("Method is " + queryableRequestSpecification.getMethod());
        FrameworkLogger.logInfoDetails("Headers are ");
        FrameworkLogger.logHeaders(queryableRequestSpecification.getHeaders().asList());
        FrameworkLogger.logInfoDetails("Request body is ");
        FrameworkLogger.logJson(queryableRequestSpecification.getBody());
    }

    private static void printResponseLogInReport(Response response) {
    	 FrameworkLogger.logInfoDetails("Response status is " + response.getStatusCode());
    	 FrameworkLogger.logInfoDetails("Response Headers are ");
    	 FrameworkLogger.logHeaders(response.getHeaders().asList());
    	 FrameworkLogger.logInfoDetails("Response body is ");
    	 FrameworkLogger.logJson(response.getBody().prettyPrint());
    }

    public static Response performPost(String endPoint, String requestPayload, Map<String,String>headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response =  requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performPost(String endPoint, Map<String, Object> requestPayload, Map<String,String>headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayload, headers);
        Response response =  requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Response performPost(String endPoint, Object requestPayloadAsPojo, Map<String,String>headers) {
        RequestSpecification requestSpecification = getRequestSpecification(endPoint, requestPayloadAsPojo, headers);
        Response response =  requestSpecification.post();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }
}
