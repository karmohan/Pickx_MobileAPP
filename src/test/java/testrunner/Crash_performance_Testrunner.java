package testrunner;


import java.io.IOException;

import org.testng.annotations.AfterClass;

import base.base;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.CommonUtils;


//@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features={"src/test/java/iOS_feature"},
		glue={"iOS_stepdefinations", "Crash_Test_config"},
				//tags= "(@login or @Home or @liveTV or @Settings or @chromecast or @TVguide or @Player or @recording or @HomeStream)",
				tags= "(@HomeScreenPerformanceTest or @liveTVPerformanceTest or @EPGPerformanceTest or @Settings or ~@PlayerPerformanceTest or @RecordingPerformanceTest)",
										//tags= "(@login or ~@CrashTestEPG)",
								
			plugin = {
						"summary",
						"pretty",
						"json:target/cucumber-reports/cucumber.json",
						"html:target/cucumber-reports/cucumber-html-report",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"timeline:test-output-thread/"
						}
		
						//tags = {"@Smoke","@Regression"}
		)
public class Crash_performance_Testrunner extends AbstractTestNGCucumberTests {
	public base base_object;
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	Scenario scenario;
	public Hooks hooks;
	
	public Crash_performance_Testrunner() throws IOException
	{
		 driver = Hooks.getDriver();
		 commonUtils = new CommonUtils(driver);
		 base_object = new base();
		 hooks = new Hooks();
			
	}
	
	@AfterClass(alwaysRun=true)
	public void after_suite() {
		System.out.println("@AfterClass executed in testrunner");
		String runningPlatform = base_object.getRunningPlatformName();
		if (runningPlatform.equalsIgnoreCase("browserstack")) {
			hooks.updateResultToBs(scenario);
		}
		if (driver != null) 
			driver.quit();
	}

}

