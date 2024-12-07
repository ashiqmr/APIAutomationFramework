package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtils {
	
	ExtentHtmlReporter htmlReporter;
	
	ExtentReports extentReport;
	
	ExtentTest extentTest;
	
	public ExtentReportUtils(String fileName) {
		
		htmlReporter = new ExtentHtmlReporter(fileName);
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
		
		
	}
	
	public void createTestCase(String testCaseName) {
		extentTest = extentReport.createTest(testCaseName);
	}
	
	public void addLog(Status status, String comments) {
		extentTest.log(status, comments);
	}
	
	public void closeReport() {
		extentReport.flush();
	}

}
