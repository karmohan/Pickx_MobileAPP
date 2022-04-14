package iOS_stepdefinations;

import java.io.IOException;

import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.Program_Details_Screen;
import iOS_screens.TVguide_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Player_page implements Program_Details_Screen,Home_Screen,TVguide_Screen{
	
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public iOS_input_properties inputProperties;

	public Player_page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new iOS_input_properties();
	}
	
    @Then("^The user see live airing portrait and horizontal$")
    public void the_user_see_live_airing_portrait_and_horizontal() throws InterruptedException {
    	ScreenOrientation orientation = driver.getOrientation();
    	System.out.println("Current orientation " + orientation);
    	Thread.sleep(2000);
    	commonUtils.clickonElement(player_fullscreen);
    	ScreenOrientation orientation_afterFullScreen = driver.getOrientation();
    	System.out.println("Orientation after FullScreen " + orientation_afterFullScreen);
    }
    
// Locators needed for  "epg_live_rewind_button" and "epg_live_forward_button"
    @Then("^User able to FRW and able FFR the video$")
    public void user_able_to_frw_and_able_ffr_the_video() {
    	Assert.assertTrue(commonUtils.displayed(epg_live_rewind_button));
    	commonUtils.clickonElement(epg_live_rewind_button);
    	Assert.assertTrue(commonUtils.enabled(epg_live_forward_button));
    	commonUtils.clickonElement(epg_live_forward_button);
    }
    
    @Then("^User able to FRW and not able FFR the video$")
    public void user_able_to_frw_and_not_able_ffr_the_video() {
    	Assert.assertTrue(commonUtils.displayed(epg_live_rewind_button));
    	commonUtils.clickonElement(epg_live_rewind_button);
    	Assert.assertTrue(!commonUtils.enabled(epg_live_forward_button));
    }
}
