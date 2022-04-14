package Android_stepdefinations;

import java.io.IOException;

import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;

import Android_screens.LiveTV_Screen;
import Android_screens.Program_Details_Screen;
import Android_screens.TVguide_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Player_page implements LiveTV_Screen, TVguide_Screen, Program_Details_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Android_input_properties inputProperties;
	public Program_Details_Page program_Details_Page;

	public Player_page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new Android_input_properties();
		program_Details_Page = new Program_Details_Page();
	}

    @Then("^The user see live airing portrait and horizontal$")
    public void the_user_see_live_airing_portrait_and_horizontal() throws InterruptedException {
    	ScreenOrientation orientation = driver.getOrientation();
    	System.out.println("Current orientation " + orientation);
    	commonUtils.clickonElement(live_fullscreen);
    	Thread.sleep(2000);
    	ScreenOrientation orientation_afterFullScreen = driver.getOrientation();
    	System.out.println("Orientation after FullScreen " + orientation_afterFullScreen);
    }
    
    @Then("^User able to FRW and able FFR the video$")
    public void user_able_to_frw_and_able_ffr_the_video() throws InterruptedException {
    	Assert.assertTrue(commonUtils.displayed(epg_live_rewind_button));
    	commonUtils.clickonElement(epg_live_rewind_button);
    	 Thread.sleep(10000);
    	   if(!commonUtils.displayed(epg_live_rewind_button)){
    	               commonUtils.clickonElement(live_vidoe_playing_container);
    	      }
    	Assert.assertTrue(commonUtils.enabled(epg_live_forward_button));
    	commonUtils.clickonElement(epg_live_forward_button);
    }
    
    @Then("^User able to FRW and not able FFR the video$")
    public void user_able_to_frw_and_not_able_ffr_the_video() {
    	Assert.assertTrue(commonUtils.displayed(epg_live_rewind_button));
    	commonUtils.clickonElement(epg_live_rewind_button);
 	   if(!commonUtils.displayed(epg_live_rewind_button)){
           commonUtils.clickonElement(live_vidoe_playing_container);
  }
    	Assert.assertTrue(!commonUtils.enabled(epg_live_forward_button));
    }
    
}
