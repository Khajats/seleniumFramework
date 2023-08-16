package com.kts.reports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.kts.enums.ConfigProperties;
import com.kts.enums.LogType;
import com.kts.utils.PropertyUtils;
import com.kts.utils.ScreenshortUtils;

import io.restassured.http.Header;


public final class FrameworkLogger {

    private FrameworkLogger(){}

    private static final Consumer<String> PASS = (message)->ExtentManager.getExtentTest().pass(message);
    private static final Consumer<String> FAIL = (message)->ExtentManager.getExtentTest().fail(message);
    private static final Consumer<String> SKIP = (message)->ExtentManager.getExtentTest().skip(message);
    private static final Consumer<String> INFO = (message)->ExtentManager.getExtentTest().info(message);
    private static final Consumer<String> CONSOLE = (message) -> System.out.println("INFO---->"+message);
    private static final Consumer<String> EXTENTANDCONSOLE = PASS.andThen(CONSOLE);
    private static final Consumer<String> TAKESCREENSHOT = (message)-> ExtentManager.getExtentTest().info("",
            MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshortUtils.getScreenBase64()).build());

    private static final Map<LogType,Consumer<String>> MAP = new EnumMap<>(LogType.class);
    private static final Map<LogType,Consumer<String>> SCREENSHOTMAP = new EnumMap<>(LogType.class);

    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);
        SCREENSHOTMAP.put(LogType.PASS, PASS.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.SKIP, SKIP.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.INFO, INFO);
        SCREENSHOTMAP.put(LogType.CONSOLE, CONSOLE);
        SCREENSHOTMAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE.andThen(TAKESCREENSHOT));
    }

    public static void log(LogType status, String message) {
        if(!PropertyUtils.get(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")) {
            MAP.getOrDefault(status,EXTENTANDCONSOLE).accept(message);
        }
        else{
            SCREENSHOTMAP.getOrDefault(status,EXTENTANDCONSOLE).accept(message);
        }
    }
    
    public static String getReportNameWithTimeStamp() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDateTime);
        String reportName = "TestReport" + formattedTime + ".html";
        return reportName;
    }

    public static void logPassDetails(String log) {
    	ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }
    public static void logFailureDetails(String log) {
    	ExtentManager.getExtentTest().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }
    public static void logExceptionDetails(String log) {
    	ExtentManager.getExtentTest().fail(log);
    }
    public static void logInfoDetails(String log) {
    	ExtentManager.getExtentTest().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }
    public static void logWarningDetails(String log) {
    	ExtentManager.getExtentTest().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }
    public static void logJson(String json) {
    	ExtentManager.getExtentTest().info(MarkupHelper.createCodeBlock(json, CodeLanguage.JSON));
    }
    public static void logHeaders(List<Header> headersList) {

        String[][] arrayHeaders = headersList.stream().map(header -> new String[] {header.getName(), header.getValue()})
                        .toArray(String[][] :: new);
        ExtentManager.getExtentTest().info(MarkupHelper.createTable(arrayHeaders));
    }
}
