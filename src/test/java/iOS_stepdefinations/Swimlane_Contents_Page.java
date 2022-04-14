package iOS_stepdefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import config.Hooks;
import iOS_screens.Setting_Screen;
import iOS_screens.Swimlane_Contents_Screen;
import iOS_screens.TVguide_Screen;
import iOS_screens.Vod_Details_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Swimlane_Contents_Page
		implements Vod_Details_Screen, Swimlane_Contents_Screen, TVguide_Screen, Setting_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Setting_Page setting_page;

	public Swimlane_Contents_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		setting_page = new Setting_Page();
	}

	public void user_selects_movies_category() {
		commonUtils.waitTillVisibility(category, 30);
		List<MobileElement> categories = commonUtils.findElements(category);
		Assert.assertTrue(categories.size() > 0);
		// Since no texts are available for the categories, using index position
//		commonUtils.findElement(movies_page_title1).click();
		categories.get(1).click();
		commonUtils.waitTillVisibility(movies_page_title, 20);
		Assert.assertTrue(commonUtils.displayed(movies_page_title));
	}

	@Then("^User selects program from movie category$")
	public void user_selects_movie_category() throws Throwable {
		System.out.println("swimlane");
		user_selects_movies_category();
		boolean movie_found = false;
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(top_films)) {
				movie_found = select_vod(true, false);
				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(movie_found);
	}

	public void user_selects_series_category() {
		List<MobileElement> categories = commonUtils.findElements(category);
		Assert.assertTrue(categories.size() > 0);
		// Since no texts are available for the categories, using index position
		categories.get(2).click();
		commonUtils.waitTillVisibility(series_page_title, 30);
		Assert.assertTrue(commonUtils.displayed(series_page_title));
	}

	public void user_selects_new_this_week_category() {
		List<MobileElement> categories = commonUtils.findElements(category);
		Assert.assertTrue(categories.size() > 0);
		// Since no texts are available for the categories, using index position
		categories.get(0).click();
		commonUtils.waitTillVisibility(new_this_week_page_title, 30);
		Assert.assertTrue(commonUtils.displayed(new_this_week_page_title));
	}

	@Then("^User navigates back to swimlane content page$")
	public void navigates_back_to_swimlane_content_page() {
		boolean swimlaneContentPageDisplayed = false;
		// Since the categories have different sub-categories which are dynamic,
		// pressing the back button till the main category list is displayed
		for (int i = 0; i < 7; i++) {
			if (commonUtils.displayed(movies_and_series_page_title)) {
				swimlaneContentPageDisplayed = true;
				break;
			} else {
//				commonUtils.waitTillInvisibility(swimlane_content_page_close_button, 20);
//				commonUtils.clickonElement(swimlane_content_page_close_button);
				commonUtils.clickonback();
			}
		}
		Assert.assertTrue(swimlaneContentPageDisplayed);
	}

	@Then("^User selects program from series category$")
	public void user_selects_swimlane_series_category() throws Throwable {
		user_selects_series_category();
		boolean series_found = false;
		for (int i = 0; i < 5; i++) {
			Thread.sleep(10000);
			if (commonUtils.findElements(episode_subtitle).size() > 0) {
				if (commonUtils.findElements(episode_subtitle).size() > 0) {
					System.out.println("Found with accesibilty id");
				}
				series_found = select_vod(true, true);
				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(series_found);
	}

	@And("^User selects a non-playable vod item$")
	public void user_selects_non_playable_vod_item() throws Throwable {
		user_selects_new_this_week_category();
		boolean vodSelected = false;
		// Since the movie categories have different sub-categories which are dynamic,
		// selecting the first category in each page till the program list is displayed
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(new_this_week_page_title)) {
				vodSelected = select_vod(false, false);
				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(vodSelected);
	}

	public boolean select_vod(boolean playableVod, boolean isSeries) throws Throwable {
		boolean programSelected = false;
		String temp = "";
		int count = 0;
		By locator = programs_in_movies;
		if (isSeries)
			locator = programs_in_series;
		while (count < 8) {
			Thread.sleep(20000);
			List<MobileElement> programList = commonUtils.findElements(locator);
			for (int i = 0; i < programList.size(); i++) {
				programList = commonUtils.findElements(locator);
				System.out.println(programList.size());
				if (programList.size() < 10) {
					System.out.println("Phone");
					if (commonUtils.findElements(image_view).get(i).isDisplayed())
						continue;
				}

				if (commonUtils.findElements(play_on_decoder).size() == i + 1) {
					if (playableVod)
						continue;
				} else if (!playableVod) {
					continue;
				}
				System.out.println(i + "  i");
				programList.get(i).click();
				if (!commonUtils.displayed(vodView)) {
					setting_page.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					programList = commonUtils.findElements(locator);
					try {
						if (commonUtils.findElements(play_on_decoder).get(i).isDisplayed()) {
							if (playableVod)
								continue;
						} else if (!playableVod) {
							continue;
						}
					} catch (Exception e) {
						System.out.println("Catch");
						count++;
						continue;
					}
					programList.get(i).click();
				}
				Assert.assertTrue(commonUtils.displayed(vodView));
				programSelected = true;
				break;
			}
			if (programSelected)
				break;
			count++;
			commonUtils.swipeUpOverHomeScreen();
		}
		return programSelected;
	}

	@And("^User Validates the assets of SVOD movie content$")
	public void validates_the_assets_of_SVOD_movie_content() throws Throwable {
		user_selects_movies_category();
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(top_films)) {
				commonUtils.waitTillVisibility(programs_in_movies, 30);
				List<MobileElement> programList = commonUtils.findElements(programs_in_movies);
				for (MobileElement swimlanes : programList) {
					programList = commonUtils.findElements(programs_in_movies);
//					Assert.assertTrue(swimlanes.findElement(swimlane_content_page_program_title).isDisplayed());
//					System.out.println(swimlanes.findElement(swimlane_content_page_program_title).getText());
//					Assert.assertTrue(swimlanes.findElement(episode_subtitle).isDisplayed());
//					Assert.assertTrue(swimlanes.findElement(image_view).isDisplayed());
					break;
				}
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
	}
}
