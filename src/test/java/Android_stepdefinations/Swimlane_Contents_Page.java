package Android_stepdefinations;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Android_screens.Setting_Screen;
import Android_screens.Swimlane_Contents_Screen;
import Android_screens.Vod_Details_Screen;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Swimlane_Contents_Page
		implements Swimlane_Contents_Screen, Vod_Details_Screen, Android_screens.LiveTV_Screen,Setting_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Setting_Page setting_page;

	public Swimlane_Contents_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		setting_page = new Setting_Page();
	}

	public void user_selects_new_this_week_category() {
		List<MobileElement> categories = commonUtils.findElements(category);
		Assert.assertTrue(categories.size() > 0);
		// Since no texts are available for the categories, using index position
		categories.get(0).click();
		commonUtils.waitTillVisibility(new_this_week_page_title, 20);
		Assert.assertTrue(commonUtils.displayed(new_this_week_page_title));
	}

	public void user_selects_movies_category() {
		List<MobileElement> categories = commonUtils.findElements(category);
		Assert.assertTrue(categories.size() > 0);
		// Since no texts are available for the categories, using index position
		//categories.get(1).click();
		commonUtils.clickonElement(movies_page_title);
		commonUtils.waitTillVisibility(movies_page_title, 30);
		Assert.assertTrue(commonUtils.displayed(movies_page_title));
	}

	@And("^User selects a playable vod movie$")
	public void user_selects_playable_vod_movie() throws Throwable {
		user_selects_movies_category();
		boolean movieSelected = false;
		// Since the movie categories have different sub-categories which are dynamic,
		// selecting the first category in each page till the program list is displayed
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(swimlane_content_page_program_title)) {
				movieSelected = select_vod(true, false);
				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(movieSelected);
	}

	public boolean select_vod(boolean playableVod, boolean isSeries) throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean programSelected = false;
		String temp = "";
		int count = 0;
		By locator = programs_in_movies;
		if (isSeries)
			locator = programs_in_series;
		while (count < 3) {
			Thread.sleep(20000);
			List<MobileElement> programList = commonUtils.findElements(locator);
			String lastProgram = "";
			try {
				lastProgram = programList.get(programList.size() - 1).findElement(swimlane_content_page_program_title)
						.getText();
			} catch (Exception e) {
				programList = commonUtils.findElements(locator);
				if (programList.get(programList.size() - 3).findElements(swimlane_content_page_program_title).isEmpty())
					lastProgram = programList.get(programList.size() - 6)
							.findElement(swimlane_content_page_program_title).getText();
				else
					lastProgram = programList.get(programList.size() - 3)
							.findElement(swimlane_content_page_program_title).getText();
			}
			if (commonUtils.displayed(lock_icon)) {
				System.out.println("Locked item found");
				commonUtils.clickonElement(lock_icon);
				setting_page.enter_parental_pin_for_programs();
				commonUtils.waitTillInvisibility(lock_icon, 20);
			}
			for (int i = 0; i < programList.size(); i++) {
				programList = commonUtils.findElements(locator);
				try {
					if (programList.get(i).findElements(image_view).size() < 1)
						continue;

					if (programList.get(i).findElements(non_playable_icon).size() > 0) {
						if (playableVod)
							continue;
					} else if (!playableVod) {
						continue;
					}
				} catch (Exception overlayErr) {
					System.out.println("Overlay not found error");
					continue;
				}
				programList.get(i).click();
//				For tablet ,program details are not dispalyed on clicking , So clicking back	
				try {
					commonUtils.waitTillVisibility(vod_program_title, 30);
				} catch (Exception e) {

					System.out.println("Cehcking Tablet");
					commonUtils.clickonback();
					commonUtils.waitTillVisibility(vod_program_title, 30);
				}
				Assert.assertTrue(commonUtils.displayed(vod_program_title));
				programSelected = true;
				break;
			}
			if (programSelected)
				break;
			if (temp.equals(lastProgram))
				count++;
			temp = lastProgram;
			commonUtils.swipeUpOverHomeScreen();
		}
		return programSelected;
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
				commonUtils.clickonElement(swimlane_content_page_close_button);
			}
		}
		Assert.assertTrue(swimlaneContentPageDisplayed);
	}

	public void user_selects_series_category() {
		List<MobileElement> categories = commonUtils.findElements(category);
		Assert.assertTrue(categories.size() > 0);
		// Since no texts are available for the categories, using index position
		categories.get(2).click();
		Assert.assertTrue(commonUtils.displayed(series_page_title));
	}

	@And("^User selects a non-playable vod item$")
	public void user_selects_non_playable_vod_item() throws Throwable {
		user_selects_new_this_week_category();
		boolean vodSelected = false;
		boolean playableVod = false;
		boolean isSeries = false;
		// Since the movie categories have different sub-categories which are dynamic,
		// selecting the first category in each page till the program list is displayed
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(swimlane_content_page_program_title)) {
				vodSelected = select_vod(playableVod, isSeries);
				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(vodSelected);
	}

//	added throw sree 11/08
	@And("^User selects a playable vod series$")
	public void user_selects_playable_vod_series() throws Throwable {
		user_selects_series_category();
		boolean serieSelected = false;
		// Since the series categories have different sub-categories which are dynamic,
		// selecting the first category in each page till the program list is displayed
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(episode_subtitle)) {
				serieSelected = select_vod(true, true);
				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(serieSelected);
	}

	@Then("^User selects program from movie category$")
	public void user_selects_movie_category() throws Throwable {
		user_selects_movies_category();
		boolean movie_found = false;
		for (int i = 0; i < 6; i++) {
			Thread.sleep(5000);
			if (commonUtils.displayed(swimlane_content_page_program_title)) {
				movie_found = select_vod(true, false);
				break;
//				List<MobileElement> programList = commonUtils.findElements(programs_in_movies);
//				for (MobileElement program : programList) {
//					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//					if (program.findElements(play_on_decoder).size() < 1) {
//						program.click();
//						if (!commonUtils.displayed(synopsis)) {
//							setting_page.enter_parental_pin_for_programs();
//							commonUtils.waitTillInvisibility(lock_icon, 20);
//							break;
//						}
//						movie_found = true;
//						break;
//					}
//				}
//				if (movie_found)
//					break;
//				
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(movie_found);
	}

	@Then("^User selects program from series category$")
	public void user_selects_swimlane_series_category() throws Throwable {
		user_selects_series_category();
		boolean series_found = false;
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(episode_subtitle)) {
				series_found = select_vod(true, true);
				break;
				
//				Thread.sleep(10000);
//				List<MobileElement> programList = commonUtils.findElements(programs_in_series);
//				try {
//					programList.get(0).click();
//				} catch (Exception e) {
//					commonUtils.waitTillVisibility(programs_in_series, 30);
//					programList = commonUtils.findElements(programs_in_series);
//					programList.get(0).click();
//				}
//				series_found = true;
//				break;
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
		Assert.assertTrue(series_found);
	}

	@And("^User Validates the assets of SVOD movie content$")
	public void validates_the_assets_of_SVOD_movie_content() throws Throwable {
		user_selects_movies_category();
		for (int i = 0; i < 5; i++) {
			if (commonUtils.displayed(swimlane_content_page_program_title)) {
				commonUtils.waitTillVisibility(programs_in_movies, 30);
				Thread.sleep(20000);
//				List<MobileElement> programList = commonUtils.findElements(programs_in_movies);
//				for (int j = 0; j < programList.size(); j++) {
//					programList = commonUtils.findElements(programs_in_movies);
//					System.out.println(programList.get(j).findElement(swimlane_content_page_program_title).getText());
//					if (programList.get(j).findElements(svod_icon_age).size() > 0) {
//						Assert.assertTrue(programList.get(i).findElement(svod_icon_age).isDisplayed());
//					}
//					Assert.assertTrue(
//							programList.get(j).findElement(swimlane_content_page_program_title).isDisplayed());
//					System.out.println(programList.get(j).findElement(swimlane_content_page_program_title).getText());
//					Assert.assertTrue(programList.get(j).findElement(episode_subtitle).isDisplayed());
//					Assert.assertTrue(programList.get(j).findElement(image_view).isDisplayed());
//					break;
//				}

				commonUtils.waitTillVisibility(swimlane_content_page_program_title, 30);
				List<MobileElement> programList = commonUtils.findElements(programs_in_movies);
				for (MobileElement swimlanes : programList) {
					programList = commonUtils.findElements(programs_in_movies);
					if (swimlanes.findElements(svod_icon_age).size()>0) {
						Assert.assertTrue(swimlanes.findElement(svod_icon_age).isDisplayed());
					}
					Assert.assertTrue(swimlanes.findElement(swimlane_content_page_program_title).isDisplayed());
					System.out.println(swimlanes.findElement(swimlane_content_page_program_title).getText());
					Assert.assertTrue(swimlanes.findElement(episode_subtitle).isDisplayed());
					Assert.assertTrue(swimlanes.findElement(image_view).isDisplayed());
					break;
				}
			} else {
				commonUtils.findElements(category).get(0).click();
			}
		}
	}
}
