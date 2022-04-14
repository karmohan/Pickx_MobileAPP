package Android_stepdefinations;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;

import Android_screens.Setting_Screen;
import Android_screens.Swimlane_Contents_Screen;
import Android_screens.Vod_Details_Screen;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class SVOD_page implements Swimlane_Contents_Screen, Vod_Details_Screen, Setting_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;

	  public Home_Page home_page;
	  public Program_Details_Page progDetailsPage;
	  public Vod_Details_Page detailsPage;
	  public Setting_Page settingPage;
	  public String text = "text";
	  public By swimlane_view_all_button;
		public String swimlane_view_all_button_text;
	  
	public SVOD_page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		home_page = new Home_Page();
		progDetailsPage = new Program_Details_Page();
		detailsPage = new Vod_Details_Page();
		settingPage = new Setting_Page();
	}

    @When("^The user checks the assert available in the svod$")
    public void the_user_checks_the_assert_availablein_the_svod() {
    	Assert.assertTrue(commonUtils.displayed(new_this_week_page_title));
    	Assert.assertTrue(commonUtils.displayed(movies_page_title));
    	Assert.assertTrue(commonUtils.displayed(series_page_title));
    	Assert.assertTrue(commonUtils.displayed(entertainment_page_title));
    }
    
    @When("^The user sees the locked video content$")
    public void the_user_sees_the_locked_video_content() {
    	home_page.user_selects_recommended_in_movies_and_series();
		swimlane_view_all_button_text = commonUtils.getTextBasedonLanguage_Android("all_text");
		swimlane_view_all_button = commonUtils.findElementByXpathValueName(text,swimlane_view_all_button_text);
    	Assert.assertTrue(commonUtils.displayed(swimlane_view_all_button));
    	if(commonUtils.displayed(svod_icon_locked)) {		
    	}
    }

	@Then("^The user validates the metadata in locked content$")
	public void The_user_validates_the_metadata() {

		Assert.assertTrue(commonUtils.displayed(swimlane_content_page_program_title));
		Assert.assertTrue(commonUtils.displayed(episode_subtitle));
		if(commonUtils.displayed(svod_icon_locked)){
			Assert.assertTrue(commonUtils.displayed(svod_icon_locked));
			Assert.assertTrue(commonUtils.displayed(svod_icon_age));
		} else {
			System.out.println("Programs are not parental control asset");
			Assert.assertFalse(commonUtils.displayed(svod_icon_locked));
			Assert.assertFalse(commonUtils.displayed(svod_icon_age));
		}
	}

	@And("^User stream SVOD content$")
	public void user_stream_SVOD_content() throws Throwable {
		for (int counter = 0; counter < 5; counter++) {
			boolean svodFound = false;
			commonUtils.waitTillVisibility(programs_in_movies, 30);
			List<MobileElement> programList = commonUtils.findElements(programs_in_movies);
			for (int i = 0; i < programList.size(); i++) {
				Thread.sleep(10000);
				programList = commonUtils.findElements(programs_in_movies);
				if (!programList.get(i).findElement(image_view).isDisplayed())
					continue;
				if (programList.get(i).findElements(play_on_decoder).size() >0)
					continue;
				if (programList.get(i).findElements(play_on_decoder).size() < 1) {
					programList.get(i).click();
					if (commonUtils.displayed(parental_pin_input)) {
						System.out.println("Parental control");
						settingPage.enter_parental_pin_for_programs();
						commonUtils.waitTillInvisibility(svod_icon_locked, 20);
						continue;
					}
					progDetailsPage.program_starts_streaming();
					svodFound = true;
					break;
				}
			}

//			if (programList.get(0).findElements(play_on_decoder).size() < 1)
//				programList.get(0).click();
//			if (commonUtils.displayed(parental_pin_input)) {
//				settingPage.enter_parental_pin_for_programs();
//				commonUtils.waitTillInvisibility(svod_icon_locked, 10);
//				commonUtils.waitTillVisibility(programs_in_movies, 30);
//				programList = commonUtils.findElements(programs_in_movies);
//				for (int i = 0; i < programList.size(); i++) {
//					if (programList.get(i).findElements(swimlane_content_page_program_title).size() < 1) {
//						System.out.println("Continue");
//						continue;
//					}
//					if (programList.get(i).findElements(play_on_decoder).size() < 1) {
//						System.out.println("IF decoder");
//						programList.get(i).click();
//						progDetailsPage.program_starts_streaming();
//						svodFound = true;
//						break;
//					}
//				}
//			}
			if (svodFound)
				break;
			System.out.println("Swipe up");
			commonUtils.swipeUpOverScreen();
		}
		
	}

	@Then("^Validate movie details and Stream content$")
	public void Validate_movie_details_and_Stream_content() throws Throwable {
		detailsPage.metadata_properly_displayed_for_programs();
//    	if(!commonUtils.displayed(synopsis)) {
//    		settingPage.enter_parental_pin_for_programs();
//    	}
		progDetailsPage.program_starts_streaming();

	}

}
