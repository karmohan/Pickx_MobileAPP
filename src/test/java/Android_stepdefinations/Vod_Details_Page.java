package Android_stepdefinations;

import java.io.IOException;

import org.testng.Assert;

import Android_screens.Program_Details_Screen;
import Android_screens.Setting_Screen;
import Android_screens.Swimlane_Contents_Screen;
import Android_screens.Vod_Details_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Vod_Details_Page
		implements Vod_Details_Screen, Swimlane_Contents_Screen, Program_Details_Screen, Setting_Screen {

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

	@And("^Metadata properly displayed for playable vod item$")
	public void metadata_properly_displayed_for_playable_vod_item() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		if (commonUtils.displayed(player_screen_swimlane_Tab)) {
//			commonUtils.swipeUpOverScreen();
			Assert.assertTrue(commonUtils.displayed(player_screen_swimlane_Tab));
		} else {
			Assert.assertTrue(commonUtils.displayed(player_screen));
			Assert.assertTrue(commonUtils.displayed(play_pause_button));
			Assert.assertTrue(commonUtils.displayed(download_button));
			Assert.assertTrue(commonUtils.displayed(availability));
		}
		Assert.assertTrue(commonUtils.displayed(vod_program_title));
		Assert.assertTrue(commonUtils.displayed(vod_program_time));
		Assert.assertTrue(commonUtils.displayed(synopsis));

	}

	@And("^Metadata properly displayed for non-playable vod item$")
	public void metadata_properly_displayed_for_non_playable_vod_item() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		if (commonUtils.displayed(player_screen_swimlane_Tab)) {
//			commonUtils.swipeUpOverScreen();
			Assert.assertTrue(commonUtils.displayed(player_screen_swimlane_Tab));
		} else {
			Assert.assertTrue(commonUtils.displayed(player_screen));
			Assert.assertTrue(commonUtils.displayed(download_button));
			Assert.assertTrue(commonUtils.displayed(availability));
			Assert.assertTrue(commonUtils.displayed(not_playable_icon));
		}
		Assert.assertFalse(commonUtils.displayed(play_pause_button));
		Assert.assertTrue(commonUtils.displayed(vod_program_title));
		Assert.assertTrue(commonUtils.displayed(vod_program_time));
		Assert.assertTrue(commonUtils.displayed(synopsis));

	}

	@And("^Metadata properly displayed for programs$")
	public void metadata_properly_displayed_for_programs() {
		if (commonUtils.displayed(parental_pin_input))
			settingPage.enter_parental_pin_for_programs();
		if (!commonUtils.displayed(play_on_decoder)) {
			commonUtils.clickonElement(movie_container);
		} else {
			commonUtils.clickonElement(movie_container_next);
		}
		if (!commonUtils.displayed(synopsis))
			commonUtils.clickonback();
		// commonUtils.clickonElement(movie_container);
		//commonUtils.waitTillVisibility(download_button, 30);
		Assert.assertTrue(commonUtils.displayed(vod_program_title));
		Assert.assertTrue(commonUtils.displayed(vod_program_time));
		//Assert.assertTrue(commonUtils.displayed(download_button));
		Assert.assertTrue(commonUtils.displayed(synopsis));
	}

	@Then("^Validate movie details corresponding user selected language$")
	public void validate_movie_details_corresponding_user_selected_language() {
		metadata_properly_displayed_for_programs();
		//String downloadTitle = commonUtils.findElement(download_button).findElement(download_title).getText();
		String availability_text = commonUtils.findElement(availability).getText().split(":")[0].trim();
		//Assert.assertTrue(downloadTitle.equalsIgnoreCase(inputProperties.getElementString("download", commonUtils.getDeviceLanguage())));
		Assert.assertTrue(availability_text
				.equalsIgnoreCase(inputProperties.getElementString("availability", commonUtils.getDeviceLanguage())));

	}

}
