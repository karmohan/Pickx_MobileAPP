package testrunner;


import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import config.Hooks;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features={"src/test/java/Android_features"},
		glue={"Android_stepdefinations", "config"},
		//tags= "(@Settings or @Search or @login or @Logout or @HomeLanguage or @liveTV or @MyVideos or @svodLanguage or @TVguideLanguage)",
							//tags= "(@MyVideos or @Logout)",
				plugin = {
						"summary",
						"pretty",
						"json:target/cucumber-reports/cucumber.json",
						"html:target/cucumber-reports/cucumber-html-report",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"timeline:test-output-thread/"
						}				
		)
public class Android_Testrunner_parallel extends AbstractTestNGCucumberTests {
	

@BeforeTest
@Parameters({"device","language"})
public void setup(String device,String language) throws IOException {
	
	Hooks.setDevice(device);
	Hooks.setLanguage(language);
	
	}
}