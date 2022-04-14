package iOS_stepdefinations;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.Setting_Screen;
import iOS_screens.Swimlane_Contents_Screen;
import iOS_screens.Vod_Details_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class SVOD_Page implements Swimlane_Contents_Screen, Home_Screen, Vod_Details_Screen, Setting_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;

	public Home_Page home_page;
	public Program_Details_Page progDetailsPage;
	public Vod_Details_Page detailsPage;
	public Setting_Page settingPage;

	public SVOD_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		home_page = new Home_Page();
		progDetailsPage = new Program_Details_Page();
		settingPage = new Setting_Page();
		detailsPage = new Vod_Details_Page();
	}

	@When("^The user checks the assert available in the svod$")
	public void the_user_checks_the_assert_availablein_the_svod() {
		try {
			commonUtils.waitTillVisibility(new_this_week_page_titleList, 30);
			Assert.assertTrue(commonUtils.displayed(new_this_week_page_titleList));
			Assert.assertTrue(commonUtils.displayed(movies_page_titleList));
			Assert.assertTrue(commonUtils.displayed(series_page_titleList));
			Assert.assertTrue(commonUtils.displayed(entertainment_page_title));
		} catch (Exception e) {
			System.out.println("Tablet check");
			commonUtils.waitTillVisibility(new_this_week_page_titleList_tab, 30);
			Assert.assertTrue(commonUtils.displayed(new_this_week_page_titleList_tab));
			Assert.assertTrue(commonUtils.displayed(movies_page_titleList_tab));
			Assert.assertTrue(commonUtils.displayed(series_page_titleList_tab));
			Assert.assertTrue(commonUtils.displayed(entertainment_page_title_tab));
		}
	}

	@When("^The user sees the locked video content$")
	public void the_user_sees_the_locked_video_content() throws Throwable {
		home_page.user_searches_for_recommended_in_movies_and_series();
		boolean found = false;
		for (int i = 0; i < 3; i++) {
			if (commonUtils.findElements(Recommended_movies_series_viewAll).get(i).isDisplayed()) {
				found = true;
				break;
			}
		}
		Assert.assertTrue(found);

//		Assert.assertTrue(commonUtils.displayed(Recommended_movies_series_viewAll));
		// if(commonUtils.displayed(svod_icon_locked)) {
		// }
	}

	@Then("^The user validates the metadata in locked content$")
	public void The_user_validates_the_metadata() {
		List<MobileElement> swimlaneList = commonUtils.findElements(swimlane);
		boolean lockedFound = false;
		System.out.println("lockedProgramList " + swimlaneList.size());
		for (int i = 0; i < swimlaneList.size(); i++) {
			if (!swimlaneList.get(i).isDisplayed()) {
				continue;
			}
			if (!swimlaneList.get(i).findElement(swimlane_title).isDisplayed())
				continue;
			if (!swimlaneList.get(i).findElement(program_title_under_swimlane).isDisplayed())
				continue;
			if (swimlaneList.get(i).findElement(swimlane_title).getText()
					.equalsIgnoreCase("Recommended in Movies & Series")) {
				Assert.assertTrue(swimlaneList.get(i).findElement(program_title_under_swimlane).isDisplayed());
				lockedFound = true;
			}

		}
		Assert.assertTrue(lockedFound);
//		Assert.assertTrue(commonUtils.displayed(lock_icon));
//		Assert.assertTrue(commonUtils.displayed(swimlane_content_page_program_title));
//		Assert.assertTrue(commonUtils.displayed(episode_subtitle));
//    	Assert.assertTrue(commonUtils.displayed(svod_icon_age));
	}

	@Then("^Validate movie details and Stream content$")
	public void Validate_movie_details_and_Stream_content() throws Throwable {
		detailsPage.metadata_properly_displayed_for_programs();
		progDetailsPage.program_starts_streaming();

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
//				if (!programList.get(i).findElement(image_view).isDisplayed())
//					continue;
				if (programList.get(i).findElements(play_on_decoder).size() > 0)
					continue;
				if (programList.get(i).findElements(play_on_decoder).size() < 1) {
					programList.get(i).click();
					if (commonUtils.displayed(parental_pin_input)) {
						System.out.println("Parental control");
						commonUtils.clickonElement(parental_pin_cancel_button);
						continue;
					}
					progDetailsPage.program_starts_streaming();
					svodFound = true;
					break;
				}
			}
			if (svodFound)
				break;
			System.out.println("Swipe up");
			commonUtils.swipeUpOverScreen();
		}
	}
}
