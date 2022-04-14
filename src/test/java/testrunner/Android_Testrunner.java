package testrunner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features={"src/test/java/Android_features"},
		glue={"Android_stepdefinations", "config"},
			//	 tags= "(@TVguideLanguageNoPipeline or @chromecast or @TVguideStreamLanguage or @airingDetail or @Network or @MyVideosStream or @HomeStreamLanguage or @PlayerStreamLanguage or ~@Home)",
			//tags= "(@chromecast or @HomeStream or @PlayerStream or @svodStream or @TVguideStream or @MyVideosStream or @Network or ~@ParentalControlStream)",
						//tags= "(@PlayerReplayPlusStream)",
					//tags= "(@PlayerReplayPlusStreamLanguage)",
					//tags= "(@Settings or @Logout or @liveTV or @TVguideLanguage or @chromecast or @HomeLanguage or @HomeStreamLanguage or @PlayerStreamLanguage or @MyVideosStream or @MyVideos or @svodLanguage or @Network or @TVguideStreamLanguage or @airingDetail or @Search)",
							tags= "(@D2G or ~@Home1)",
				plugin = {
						"summary",
						"pretty",
						"json:target/cucumber-reports/cucumber.json",
						"html:target/cucumber-reports/cucumber-html-report",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						"timeline:test-output-thread/"
						}				
		)
public class Android_Testrunner extends AbstractTestNGCucumberTests {
	
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

}

