package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features={"src/test/java/Performance_iOS_features"},
		glue={"iOS_stepdefinations", "Performance_config"},
				tags= "(@PerformanceTest)",
				
						//tags= "(@CrashTest or @CrashTestEPG)",
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
public class Performance_iOS_Testrunner extends AbstractTestNGCucumberTests{

}
