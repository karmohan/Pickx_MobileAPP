package testrunner;


import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

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
		glue={"iOS_stepdefinations", "config"},
				//tags= "(@Settings or @Search or @login or @Logout or @HomeLanguage or @liveTVLanguage or @MyVideos or @svodLanguage or @TVguideLanguage)",
										//tags= "(@Search12)",
								
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
public class iOS_Testrunner_parallel extends AbstractTestNGCucumberTests {
	public base base_object;
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	Scenario scenario;
	public Hooks hooks;
	
	public iOS_Testrunner_parallel() throws IOException
	{
		 driver = Hooks.getDriver();
		 commonUtils = new CommonUtils(driver);
		 base_object = new base();
		 hooks = new Hooks();
			
	}

@BeforeTest
@Parameters({"device","language"})
public void setup(String device,String language) throws IOException {
	
	Hooks.setDevice(device);
	Hooks.setLanguage(language);
	
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

