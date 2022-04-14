package Android_stepdefinations;

import java.io.IOException;

import org.testng.Assert;

import Performance_Android_screens.Login_Screen;
import Performance_config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Performance_login_page implements Login_Screen{
	
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;

	public Performance_login_page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
	}

	@Given("^User opens the PickxTV app$")
	public void user_opens_the_pickxtv_app() {
		commonUtils.waitTillVisibility(home_button, 5);
		//Assert.assertTrue(commonUtils.displayed(home_button));
	}

	@When("^The user see the home page and validates the loaded home page android$")
	public void the_user_see_the_home_page_and_validates_the_loaded_home_page_android() throws InterruptedException {
		commonUtils.swipeUpOverHomeScreen();
	//	Assert.assertTrue(commonUtils.displayed(swimlane_title));
	//	Assert.assertTrue(commonUtils.displayed(swimlane_container));
//		Assert.assertTrue(commonUtils.displayed(liveTV_button));
//		Assert.assertTrue(commonUtils.displayed(tvGuide_button));
//		Assert.assertTrue(commonUtils.displayed(Recordings));
		commonUtils.waitTillVisibility(live_icon, 5);
		Assert.assertTrue(commonUtils.displayed(live_icon));
	}

}
