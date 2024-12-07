package tests;

import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import request.RequestFactory;
import utils.ConfigRead;
import utils.ExtentReportUtils;

public class BaseTest {
	
	RequestFactory requestFactory;
	String configFileName, currentWorkingDirectory, htmlReportFileName;
	Properties configProperties;
	ExtentReportUtils extentReports;

	@BeforeSuite
	public void preSetup() throws Exception {
		currentWorkingDirectory = System.getProperty("user.dir");
		configFileName = currentWorkingDirectory + "/src/test/resources/config/config.properties";
		configProperties = ConfigRead.configProperties(configFileName);
		
		htmlReportFileName = currentWorkingDirectory +"/src/test/resources/reports/htmlreport.html";
		
		extentReports = new ExtentReportUtils(htmlReportFileName);
	}

	@BeforeClass
	public void setup() {		
		
		extentReports.createTestCase("Setup: Update all Configs");
		
		RestAssured.baseURI = configProperties.getProperty("baseUrl");
		RestAssured.port = Integer.parseInt(configProperties.getProperty("port"));
	
		extentReports.addLog(Status.INFO, "Base Url - " + configProperties.getProperty("baseUrl"));
		extentReports.addLog(Status.INFO, "Base Port - " + configProperties.getProperty("port"));
		requestFactory = new RequestFactory();
	}
	
	@AfterMethod
	public void postTestCheck(ITestResult result) {
//		String testName = result.getName();
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentReports.addLog(Status.PASS, "All test are passed");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentReports.addLog(Status.FAIL, "Some tests failed");
		}else {
			extentReports.addLog(Status.SKIP, "Skipped some");
		}
	}
	
	@AfterClass
	public void cleanUp() {
		RestAssured.reset();
		extentReports.closeReport();
	}

}
