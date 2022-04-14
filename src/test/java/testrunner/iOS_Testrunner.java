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
		glue={"iOS_stepdefinations", "config"},
				//tags= "(@HomeStreamLanguage or @chromecast or @MyVideosStream or @Network or @liveTVLanguageNoPipeline or @TVguideStreamLanguage or @TVguideLanguageNoPipeline or @PlayerStreamLanguage or @SearchNoPipeline)",
				//tags= "(@chromecast or @MyVideosStream or @Network or @PlayerStream or ~@TVguideStream or ~@loginepicuser or ~@Home)",
						//tags= "(@TVguideReplayPLus or @PlayerReplayPLus or ~@Home)",
		               //tags="(@PlayerReplayPLusLanguage or @LiveTVReplayLanguage or @ReplayplusLanguage)",
				//tags= "(@Logout or @chromecast or @HomeLanguage or @HomeStreamLanguage or @Settings or @liveTVLanguage or @MyVideos or @MyVideosStream or @PlayerLanguage or @TVguideLanguage or @Network or @svodLanguage or @TVguideStreamLanguage or @Search)",
										tags= "(@D2Gtest or ~@liveTVLanguage or ~@HomeStream)",
								
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
public class iOS_Testrunner extends AbstractTestNGCucumberTests {
	public base base_object;
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	Scenario scenario;
	public Hooks hooks;
	
	public iOS_Testrunner() throws IOException
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

