package iOS_stepdefinations;

import java.io.IOException;

import org.testng.Assert;

import base.Android_input_properties;
import config.Hooks;
import iOS_screens.Program_Details_Screen;
import iOS_screens.Swimlane_Contents_Screen;
import iOS_screens.Vod_Details_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Vod_Details_Page implements Vod_Details_Screen, Swimlane_Contents_Screen, Program_Details_Screen {

	public static AppiumDriver<MobileElement> driver;
    public CommonUtils commonUtils;
    public  Android_input_properties inputProperties;
    public Setting_Page settingPage;

	public Vod_Details_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		settingPage = new Setting_Page();
		inputProperties = new Android_input_properties();
	}

	@And("^Metadata properly displayed for programs$")
	public void metadata_properly_displayed_for_programs() {
		if (!commonUtils.displayed(vod_synopsis))
			settingPage.enter_parental_pin_for_programs();
//    	if(!commonUtils.displayed(play_on_decoder)) {
//        	commonUtils.clickonElement(movie_container);
//        	}
//        	else {
//        		commonUtils.clickonElement(movie_container_next);
//        	}
		// commonUtils.clickonElement(movie_container);
		Assert.assertTrue(commonUtils.displayed(vodView));
		Assert.assertTrue(commonUtils.displayed(vod_program_title));
		Assert.assertTrue(commonUtils.displayed(vod_program_time));
		Assert.assertTrue(commonUtils.displayed(vod_download_button));
		Assert.assertTrue(commonUtils.displayed(vod_synopsis));

		System.out.println("Displayed");
	}

	@Then("^Validate movie details corresponding user selected language$")
	public void validate_movie_details_corresponding_user_selected_language() {
		metadata_properly_displayed_for_programs();
		String downloadTitle = commonUtils.findElement(vod_download_title).getText().toLowerCase();
		Assert.assertTrue(downloadTitle.equalsIgnoreCase(commonUtils.getTextBasedonLanguage("download").toLowerCase()));
	}

	@And("^Metadata properly displayed for playable vod item$")
	public void metadata_properly_displayed_for_playable_vod_item() {
		Assert.assertTrue(commonUtils.displayed(close_button));
//		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertTrue(commonUtils.displayed(Play_pause_button_under_player_screen));
		Assert.assertTrue(commonUtils.displayed(vod_program_title));
		Assert.assertTrue(commonUtils.displayed(vod_program_time));
		Assert.assertTrue(commonUtils.displayed(vod_download_button));
		Assert.assertTrue(commonUtils.displayed(vod_synopsis));
		Assert.assertTrue(commonUtils.displayed(availability));
	}
	
	 @And("^Metadata properly displayed for non-playable vod item$")
	    public void metadata_properly_displayed_for_non_playable_vod_item() {
		Assert.assertTrue(commonUtils.displayed(close_button));
//		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertFalse(commonUtils.displayed(Play_pause_button_under_player_screen));
		Assert.assertTrue(commonUtils.displayed(vod_program_title));
		Assert.assertTrue(commonUtils.displayed(vod_program_time));
		Assert.assertTrue(commonUtils.displayed(vod_download_button));
		Assert.assertTrue(commonUtils.displayed(vod_synopsis));
		Assert.assertTrue(commonUtils.displayed(availability));
		Assert.assertTrue(commonUtils.displayed(play_on_decoder));
	    }

}
