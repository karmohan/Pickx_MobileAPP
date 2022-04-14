package iOS_stepdefinations;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.TestException;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.Login_Screen;
import iOS_screens.Search_screen;
import iOS_screens.Setting_Screen;
import iOS_screens.Swimlane_Contents_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.appmanagement.ApplicationState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Home_Page implements Login_Screen, Home_Screen, Setting_Screen, Swimlane_Contents_Screen, Search_screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Setting_Page settingPage;
	public Program_Details_Page programDetalsPage;
	public iOS_input_properties inputProperties;
	public Performance_login_Page performance_login_Page;
	public Login_Page login_Page;
	public String swimlane_view_all_button_text;
	public Swimlane_Contents_Page swimlanePage;
	public String text = "text";
//	public By swimlane_view_all_button;

	public Home_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		settingPage = new Setting_Page();
		inputProperties = new iOS_input_properties();
		programDetalsPage = new Program_Details_Page();
		performance_login_Page = new Performance_login_Page();
		login_Page = new Login_Page();
		swimlanePage = new Swimlane_Contents_Page();
	}
	
    @Given("^user check the language$")
    public void user_check_the_language() throws IOException {
    	System.out.println("system language....." + commonUtils.getDeviceLanguage());
    }
    
    @Given("Use get app activity")
    public void use_gets_app_activity() throws IOException {
		ApplicationState activity = driver.queryAppState("com.proximus.inhouse.tv.itp");
		System.out.println("activity when app is active   " + activity);
    }

	@Given("^Hero content is present on the home screen$")
	public void hero_content_is_present_on_the_home_screen() throws Throwable {
		if (commonUtils.displayed(hero_banner_container)) {
			System.out.println("Hero Banner is Present");
		} else {
			settingPage.the_user_is_on_settings_page();
			settingPage.user_login_to_parental_control();
			settingPage.user_turn_on_parental_control();
			settingPage.user_turn_off_parental_control();
			commonUtils.clickonback();
			commonUtils.clickonback();
			try {
				commonUtils.waitTillVisibility(hero_banner_container, 15);
				Assert.assertTrue(commonUtils.displayed(hero_banner_container));
			} catch (Exception e) {
				commonUtils.clickonback();
				try {
					commonUtils.waitTillVisibility(hero_banner_container, 15);
					Assert.assertTrue(commonUtils.displayed(hero_banner_container));
				} catch (Exception noHero) {
					throw new TestException(String.format("Hero banner not found"));
				}
			}
		}
	}

	@When("^User can see hero banner content are present$")
	public void user_can_see_hero_banner_content_are_present() throws Throwable {
// Assert.assertTrue(commonUtils.displayed(hero_banner_channel_logo));
		Assert.assertTrue(commonUtils.displayed(textview_hero_title));
		Assert.assertTrue(commonUtils.displayed(textview_hero_metadata));
		Assert.assertTrue(commonUtils.displayed(hero_banner_replay_icon));
		String HeroBanner_Watch = commonUtils.getTextBasedonLanguage("HeroBanner_watch");
		String HeroBanner_Info = commonUtils.getTextBasedonLanguage("HeroBanner_moreInfo");
		//Thread.sleep(2000);
		//Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(HeroBanner_watch)));
		if (!commonUtils.displayed(commonUtils.findElementByAccessibilityid(HeroBanner_Info))) {
			commonUtils.waitTillVisibility(commonUtils.findElementByAccessibilityid(HeroBanner_Watch), 5);
			Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(HeroBanner_Watch)));
		} else {
			commonUtils.waitTillVisibility(commonUtils.findElementByAccessibilityid(HeroBanner_Info), 5);
			Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(HeroBanner_Info)));
		}
	}

	@When("^User can see bottom navigation menu items$")
	public void user_can_see_bottom_navigation_menu_items() throws Throwable {
		Assert.assertTrue(commonUtils.displayed(home_button));
		Assert.assertTrue(commonUtils.displayed(liveTV_button));
		Assert.assertTrue(commonUtils.displayed(tvGuide_button));
		Assert.assertTrue(commonUtils.displayed(search_navigation_button1));
		//Assert.assertTrue(commonUtils.displayed(Recordings));
		Assert.assertTrue(commonUtils.displayed(settings_button));
		Assert.assertTrue(commonUtils.displayed(my_videos_button));
	}

	@When("^User can see bottom navigation menu \"([^\"]*)\" items$")
	public void user_can_see_bottom_navigation_menu_something_items(String menu) throws InterruptedException {
// performance_login_Page.the_user_see_the_home_page_and_validates_the_loaded_home_page();
		commonUtils.waitTillVisibility(liveTV_button, 5);
// Assert.assertTrue(commonUtils.displayed(Categories));
// commonUtils.swipeUpOverHomeScreen();
		Assert.assertTrue(commonUtils.displayed(liveTV_button));
		commonUtils.swipeUpOverHomeScreen();
		Assert.assertTrue(commonUtils.displayed(home_button));
		Assert.assertTrue(commonUtils.displayed(liveTV_button));
		Assert.assertTrue(commonUtils.displayed(tvGuide_button));
		//Assert.assertTrue(commonUtils.displayed(Recordings));
		Assert.assertTrue(commonUtils.displayed(settings_button));
		Assert.assertTrue(commonUtils.displayed(my_videos_button));
	}

	@Given("^Home screen categories are displayed$")
	public void home_screen_categories_are_displayed() throws Throwable {
		commonUtils.waitTillVisibility(home_category_container, 20);
		Assert.assertTrue(commonUtils.displayed(home_category_container));
	}
	@Given("^Validate Hero banner content in loop$")
	public void validate_hero_banner_content_in_loop() throws Throwable {
		int counter = 0;

		while (counter < 3) {
			hero_content_is_present_on_the_home_screen();
			user_can_see_hero_banner_content_are_present();
			navigate_to_home_page_and_relaunch_the_app();
			counter++;
		}
	}

	@When("^Navigate to home page and relaunch the app$")
	public void navigate_to_home_page_and_relaunch_the_app() throws Throwable {
		driver.closeApp();
		System.out.println("relaunching the app again");
		driver.launchApp();
		login_Page.the_user_selects_the_environment();
		login_Page.user_successfully_login_to_the_pickx_app();

	}

	@When("^The user validates the displayed categories on the homepage$")
	public void user_validates_the_displayed_categories_in_homepage() throws Throwable {
		/*
		 * Assert.assertTrue(commonUtils.displayed(Home_Movies));
		 * Assert.assertTrue(commonUtils.displayed(Home_Series));
		 * Assert.assertTrue(commonUtils.displayed(Home_Sports));
		 * Assert.assertTrue(commonUtils.displayed(Home_Entertainment));
		 * if(!commonUtils.displayed(Home_News)) commonUtils.scrollHorizantal();
		 * Assert.assertTrue(commonUtils.displayed(Home_News));
		 * Assert.assertTrue(commonUtils.displayed(Home_Discovery));
		 * Assert.assertTrue(commonUtils.displayed(Home_Kids));
		 * Assert.assertTrue(commonUtils.displayed(Home_Kids));
		 *
		 * if (categoryName.contains("(")) categoryName =
		 * categoryName.split("\\(")[0].trim();
		 * categoriesDisplayed.add(categoryName.toLowerCase()); }
		 */

		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String lastCategory = null;
		while (counter < 3) {
			Thread.sleep(5000);
			List<MobileElement> categoryElements = commonUtils.findElements(home_categories);
			System.out.println("categoryElements.size " + categoryElements.size());
			if (categoryElements.size() < 1)
				break;
			try {
				lastCategory = categoryElements.get(categoryElements.size() - 2).getText();
			} catch (Exception e) {
				lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			}
			System.out.println("last " + categoryElements.get(categoryElements.size() - 1).getText());
			System.out.println("lastCategory  " + lastCategory);
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
// System.out.println("categoryName "+ categoryName);
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				if (!categoriesDisplayed.contains(categoryName.toLowerCase()))
					categoriesDisplayed.add(categoryName.toLowerCase());
			}
			if (temp.equals(lastCategory))
				counter++;
			else
				counter = 0;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
//			commonUtils.swipeLeftOverElement(programList.get(programList.size() - 1));
		}

		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("movies")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("series")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("entertainment")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("sports")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("discovery")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("kids")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage("music")));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getNews()));
	}

	@Given("^Validate homescreen loading skeleton$")
	public void validate_homescreen_loading_skeleton() throws Throwable {
		commonUtils.waitTillVisibility(HomeScreen_layout, 20);
		Assert.assertTrue(commonUtils.displayed(HomeScreen_layout));
		Assert.assertFalse(commonUtils.findElements(home_categories).isEmpty());
		user_can_see_bottom_navigation_menu_items();
	}

	@Given("^User see locked Hero banner$")
	public void user_see_locked_hero_banner() {
		Assert.assertTrue(commonUtils.displayed(hero_banner_container));
		commonUtils.displayed(svod_icon_locked);
	}

	@When("^Tiles are properly locked $")
	public void tiles_are_properly_locked() {
		Assert.assertTrue(commonUtils.displayed(swimlane_content_page_program_title));
		Assert.assertTrue(commonUtils.displayed(episode_subtitle));
//     Assert.assertTrue(commonUtils.displayed(svod_icon_age));
	}

	@Then("^User unlocks the locked hero banner$")
	public void user_unlocks_the_locked_hero_banner() {
		commonUtils.clickonElement(hero_banner_container);
		settingPage.enter_parental_pin_for_programs();
		Assert.assertTrue(commonUtils.displayed(hero_banner_watch_button));
	}

	@When("^User can see metadata for programs under Now on TV swimlane$")
	public void user_can_see_metadata_for_programs_under_now_on_tv() {
		int counter = 0;
		String temp = null;
		boolean nowOnTvFound = false;
		commonUtils.waitTillVisibility(home_category_container, 20);
		commonUtils.waitTillVisibility(swimlane_title, 20);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			String lastSwimlaneProgramTitle = null;
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane);
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlanes : swimlaneList) {
				if (swimlanes.findElements(live_icon_in_swimlane).size() > 0) {
					try {
						swimlanes.findElement(program_subtitle_under_swimlane);
					} catch (Exception e) {
						throw new TestException(String.format("Programs not found under 'Now on TV' swimlane"));
					}
					metadata_provided_for_channel_programs(swimlanes.findElement(swimlane), true);
					nowOnTvFound = true;
					break;
				}
			}
			if (nowOnTvFound)
				break;
			if (lastSwimlaneProgramTitle.equals(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
	}

	@When("^User can see metadata for programs under Recommended swimlane$")
	public void user_can_see_metadata_for_programs_under_recommended_swimlane() throws Throwable {
		int counter = 0;
		String temp = null;
		boolean swimlaneFound = false;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			String lastSwimlaneProgramTitle = null;
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			System.out.println("lastSwimlaneProgramTitle  " + lastSwimlaneProgramTitle);
			for (MobileElement swimlanes : swimlaneList) {
				String swimlaneTitle;
				try {
					swimlaneTitle = swimlanes.findElement(swimlane_title).getText();
					swimlanes.findElement(program_subtitle_under_swimlane).getText();
				} catch (Exception e) {
					continue;
				}
				System.out.println("swimlaneTitle " + swimlaneTitle);
				if (swimlaneTitle.equalsIgnoreCase(inputProperties.getRecommendedForYou())) {
					System.out.println(" Recommended found");
					try {
						swimlanes.findElement(program_subtitle_under_swimlane);
					} catch (Exception e) {
						throw new TestException(
								String.format("Programs not found under 'Recommended for you' swimlane"));
					}
					metadata_provided_for_channel_programs(swimlanes.findElement(swimlane), false);
					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastSwimlaneProgramTitle.equals(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverHomeScreen();
			commonUtils.swipeUpOverHomeScreen();
			Thread.sleep(20000);
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Recommended for you' swimlane not found"));
	}

	@And("^User can see metadata for programs under VOD swimlane$")
	public void user_can_see_metadata_for_programs_under_VOD_swimlane() {
		int counter = 0;
		String temp = null;
		boolean swimlaneFound = false;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			String lastSwimlaneProgramTitle = null;
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			if (swimlaneList.size() == 0)
				continue;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			System.out.println("lastSwimlaneProgramTitle  " + lastSwimlaneProgramTitle);
			for (MobileElement swimlanes : swimlaneList) {
				String swimlaneTitle;
				try {
					swimlaneTitle = swimlanes.findElement(swimlane_title).getText();
					swimlanes.findElement(program_subtitle_under_swimlane);
				} catch (Exception e) {
					continue;
				}
				System.out.println("swimlaneTitle " + swimlaneTitle);
				if (swimlaneTitle.equalsIgnoreCase(
						commonUtils.getTextBasedonLanguage("recommended_movies_and_series_swimlane").toLowerCase())) {
					System.out.println("Recommended movies");
					try {
						swimlanes.findElement(program_subtitle_under_swimlane);
					} catch (Exception e) {
						throw new TestException(
								String.format("Programs not found under 'Recommended in Movies & Series' swimlane"));
					}
					metadata_provided_for_vod(swimlanes.findElement(swimlane));
					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastSwimlaneProgramTitle.equals(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Recommended in Movies & Series' swimlane not found"));
	}

	@Then("^User can see metadata for programs under future swimlane$")
	public void user_can_see_metadata_for_programs_under_future_swimlane() throws Throwable {
		int counter = 0;
		String temp = null;
		boolean swimlaneFound = false;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			String lastSwimlaneProgramTitle = null;
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			System.out.println("lastSwimlaneProgramTitle " + lastSwimlaneProgramTitle);
			for (MobileElement swimlanes : swimlaneList) {
				String swimlaneTitle;
				try {
					swimlaneTitle = swimlanes.findElement(swimlane_title).getText();
					swimlanes.findElement(program_title_under_swimlane);
				} catch (Exception e) {
					continue;
				}
				System.out.println("swimlaneTitle " + swimlaneTitle);
				if (swimlaneTitle.equalsIgnoreCase(inputProperties.getComingUpOnTv())) {
					System.out.println("Coming up on tv");
					try {
						swimlanes.findElement(program_subtitle_under_swimlane);
					} catch (Exception e) {
						throw new TestException(String.format("Programs not found under 'Coming up on TV' swimlane"));
					}
					metadata_provided_for_channel_programs(swimlanes.findElement(swimlane), false);
					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastSwimlaneProgramTitle.equals(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
			commonUtils.swipeUpOverScreen();
			Thread.sleep(20000);
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Coming up on TV' swimlane not found"));
	}

	@Given("^User selects ongoing program from home screen$")
	public void user_selects_ongoing_program_from_home_screen() throws Throwable {
		//Thread.sleep(30000);
		commonUtils.waitTillVisibility(swimlane, 30);
// Swipe over the home screen to find swimlane with live icons
		int counter = 0;
		String temp = null;
		boolean ongoingProgramFound = false;
// commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		// To verify if the user has swiped till the last swimlane the counter value is
		// provided. For verifying if the last swimlane has reached, after each swipe
		// the program title of the last swimlane is compared with that of the previous
		// swipe. And if the titles are same the counter value is incremented. Since
		// there are swimlanes with same title, this comparison is repeated till counter
		// value reaches 3
		while (counter < 3) {
			Thread.sleep(30000);
			String lastSwimlaneProgramTitle = null;
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					swimlane.findElement(live_icon_in_swimlane).click();
					if (commonUtils.enabled(parental_pin_input)) {
						settingPage.enter_parental_pin_for_programs();
						commonUtils.waitTillInvisibility(lock_icon, 30);
						swimlane.findElement(live_icon_in_swimlane).click();
//						continue;
					}
					programDetalsPage.check_streaming_Error();
					ongoingProgramFound = true;
					break;
				}
			}
			if (ongoingProgramFound)
				break;
			if (lastSwimlaneProgramTitle.equalsIgnoreCase(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
		if (!ongoingProgramFound)
			throw new TestException(String.format("Ongoing program not found in the home screen"));
		Assert.assertTrue(programDetalsPage.user_on_program_details_page());
	}

	@Given("^User selects completed replay program from home screen$")
	public void user_selects_completed_replay_program_from_home() throws ParseException, Throwable {
		commonUtils.waitTillVisibility(swimlane, 30);
		int counter = 0;
		String temp = null;
		boolean replayProgramFound = false;
		String lastProgram = null;
		if (!commonUtils.displayed(hero_banner_container)) {
			hero_content_is_present_on_the_home_screen();
			commonUtils.swipeUpOverHomeScreen();
		} else {
			commonUtils.swipeUpOverHomeScreen();
		}
		// To verify if the user has swiped till the last swimlane the counter value is
		// provided. For verifying if the last swimlane has reached, after each swipe
		// the program title of the last swimlane is compared with that of the previous
		// swipe. And if the titles are same the counter value is incremented. Since
		// there are
		// swimlanes with same title, this comparison is repeated till counter value
		// reaches 3
		while (counter < 3) {
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0)
					continue;
				if (swimlane.findElements(program_title_under_swimlane).size() < 1)
					break;
				int counter2 = 0;
				String temp2 = null;
				String lastProgramTitle = null;
				boolean switchToNextSwimlane = false;
				// Applying same logic of swiping vertically over the screen for swiping
				// horizontally over the swimlane
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_title_under_swimlane);
					System.out.println("programList.size()   " + programList.size());
					for (int k = 0; k < programList.size(); k++) {
						System.out.println(swimlane.findElement(program_subtitle_under_swimlane).getText());
						if (programList.get(k).isDisplayed()) {
							System.out.println(programList.get(k).getText());
						}

					}
					if (programList.size() == 0) {
						break;
					}
					try {

						if (programList.get(programList.size() - 1).isDisplayed()) {
							lastProgramTitle = programList.get(programList.size() - 1).getText();
							System.out.println("lastProgramTitle " + lastProgramTitle);
						} else
							continue;

					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2).getText();
					}
					for (int j = 0; j < programList.size(); j++) {
//					for (MobileElement program : programList) {
						String subtitle = null;
						try {
							if (swimlane.findElements(program_subtitle_under_swimlane).get(j).isDisplayed()) {
								subtitle = swimlane.findElements(program_subtitle_under_swimlane).get(j).getText();
							} else
								continue;
						} catch (Exception e) {
							System.out.println("Catch subtitle");
							continue;
						}
						System.out.println("Subtitle   " + subtitle);
//					 Checking if program is a non VOD asset
						if (!subtitle.contains("|")) {
							switchToNextSwimlane = true;
							continue;
						}
						// Verifying if the airing is already completed
						if (subtitle.contains(commonUtils.getTextBasedonLanguage("Tomorrow"))) {
							continue;
						} else if (subtitle.contains(commonUtils.getTextBasedonLanguage("Yesterday"))) {
							replayProgramFound = true;
						} else if (subtitle.contains(commonUtils.getTextBasedonLanguage("Today"))) {
							if (commonUtils.if_time_is_past(subtitle.split("\\|")[1].split("-")[1].trim())) {
								replayProgramFound = true;
							}
						} else {
							if (commonUtils.verify_if_date_is_past_date(subtitle.split("\\|")[0].trim())) {
								replayProgramFound = true;
							}
						}
						if (replayProgramFound) {
							if (commonUtils
									.checkBounds(swimlane.findElements(program_subtitle_under_swimlane).get(j)) < 210) {
								replayProgramFound = false;
								continue;
							}
							swimlane.findElements(program_title_under_swimlane).get(j).click();
							if (commonUtils.displayed(parental_pin_input)) {
								settingPage.enter_parental_pin_for_programs();
								commonUtils.waitTillInvisibility(lock_icon, 30);
								swimlane.findElements(program_title_under_swimlane).get(j).click();
							}
							break;
						}
					}
					if (switchToNextSwimlane || replayProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(programList.size() - 1));
				}
				if (replayProgramFound)
					break;
			}
			if (replayProgramFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverScreen();
		}
		Assert.assertTrue(programDetalsPage.user_on_program_details_page());
	}

	@Given("^User selects VOD asset from home screen$")
	public void user_selects_VOD_asset_from_home_screen() {
		commonUtils.waitTillVisibility(swimlane, 20);
		int counter = 0;
		String temp = null;
		boolean vodItemFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane, 20);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
// To verify if the user has swiped till the last swimlane the counter value is
// provided. For verifying if the last swimlane has reached, after each swipe
// the program title of the last swimlane is compared with that of the previous
// swipe. And if the titles are same the counter value is incremented. Since
// there are
// swimlanes with same title, this comparison is repeated till counter value
// reaches 3
		while (counter < 3) {
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				if (swimlaneList.get(swimlaneList.size() - 2).findElements(program_title_under_swimlane).isEmpty()) {
					System.out.println("Swipe up");
					commonUtils.swipeUpOverScreen();
					continue;
				}
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					continue;
				}
				String subtitle = null;
				try {
					if (swimlane.findElement(program_subtitle_under_swimlane).isDisplayed()) {
						subtitle = swimlane.findElement(program_subtitle_under_swimlane).getText();
					} else
						continue;
				} catch (Exception e) {
					continue;
				}
				if (subtitle.contains("-"))
					continue;
				vodItemFound = true;
				swimlane.findElement(program_title_under_swimlane).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					vodItemFound = false;
//					commonUtils.waitTillInvisibility(lock_icon, 30);
					continue;
				}
				break;
			}

			if (vodItemFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverScreen();
		}
		Assert.assertTrue(programDetalsPage.user_on_VOD_program_details_page());
	}

	@Given("^User searches for recommended in movies and series$")
	public void user_searches_for_recommended_in_movies_and_series() throws Throwable {
		commonUtils.waitTillVisibility(HomeView, 20);
		// To verify if the last swimlane has reached, using counter value in while loop
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		commonUtils.waitTillVisibility(swimlane, 20);
		commonUtils.waitTillVisibility(swimlane_title, 20);
		while (counter < 6) {
			Thread.sleep(20000);
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
				System.out.println("lastswimlane   " + lastswimlane);
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception e1) {
					System.out.println("Catch   ");
					counter++;
//					throw new TestException(
//							String.format("Swimlane title not found"));
					continue;
				}
			}
			for (int j = 0; j < swimlaneList.size(); j++) {
				System.out.println("swimlane.findElements(swimlane_title).size()  "
						+ swimlaneList.get(j).findElements(swimlane_title).size());
				System.out.println(j);
				if (swimlaneList.get(j).findElements(swimlane_title).size() < 1)
					continue;
				System.out.println(swimlaneList.get(j).findElement(swimlane_title).getText());
				System.out.println(swimlaneList.get(j).findElements(program_subtitle_under_swimlane).size());
				if (swimlaneList.get(j).findElements(program_subtitle_under_swimlane).size() < 1)
					break;
				if (!swimlaneList.get(j).findElement(program_subtitle_under_swimlane).isDisplayed()) {
					System.out.println("Not displayed program title in IOS");
					break;
				}

				if (swimlaneList.get(j).findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(
						commonUtils.getTextBasedonLanguage("recommended_movies_and_series_swimlane").toLowerCase())) {
					System.out.println("Found");
					
				/*	try {
						swimlaneList.get(j).findElement(Recommended_movies_series_viewAll);
					} catch (Exception e) {
						System.out.println("No such element found");
						break;
					} */
					swimlaneFound = true;
					break;

				}
			} 
			if (swimlaneFound)
				break;
			if (!lastswimlane.equals(temp))
				counter = 0;
			if (lastswimlane.equals(temp))
				counter++;
			temp = lastswimlane;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Recommended in movies and series' swimlane not found"));
	}
	
    @Given("User selects SVOD swimlane")
    public void user_selects_svod_swimlane() {
    	commonUtils.waitTillVisibility(HomeView, 20);
		// To verify if the last swimlane has reached, using counter value in while loop
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		commonUtils.waitTillVisibility(swimlane, 20);
		commonUtils.waitTillVisibility(swimlane_title, 20);
		while (counter < 6) {
			//Thread.sleep(20000);
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
				System.out.println("lastswimlane   " + lastswimlane);
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception e1) {
					System.out.println("Catch   ");
					counter++;
//					throw new TestException(
//							String.format("Swimlane title not found"));
					continue;
				}
			}
			for (int j = 0; j < swimlaneList.size(); j++) {
				System.out.println("swimlane.findElements(swimlane_title).size()  "
						+ swimlaneList.get(j).findElements(swimlane_title).size());
				if (swimlaneList.get(j).findElements(swimlane_title).size() < 1)
					continue;
				if (swimlaneList.get(j).findElements(program_title_under_swimlane).size() < 1) {
					System.out.println("Program title not specified");
					break;
				}
				if ((swimlaneList.get(j).findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(
						commonUtils.getTextBasedonLanguage("recommended_movies_and_series_swimlane").toLowerCase())) || (swimlaneList.get(j).findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(
								commonUtils.getTextBasedonLanguage("recommended_pickx_mix").toLowerCase()))) {
					System.out.println("Found");

					if (!swimlaneList.get(j).findElement(commonUtils.findElementByXpathContains("value",
							commonUtils.getTextBasedonLanguage("view_all"))).isDisplayed()) {
						System.out.println("Not displayed in IOS");
						continue;
					}
					System.out.println("View all displayed in IOS");
					swimlaneList.get(j).findElement(commonUtils.findElementByXpathContains("value",
							commonUtils.getTextBasedonLanguage("view_all"))).click();

					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastswimlane.equals(temp))
				counter++;
			temp = lastswimlane;
			commonUtils.swipeUpOverScreen();
		}
		if(lastswimlane.equalsIgnoreCase("recommended_movies_and_series_swimlane")) {
			commonUtils.waitTillVisibility(movies_and_series_page_title, 30);
			Assert.assertTrue(commonUtils.displayed(movies_and_series_page_title));
		}
		else if(lastswimlane.equalsIgnoreCase("recommended_pickx_mix")) {
			commonUtils.waitTillVisibility(pickx_mix_page_title, 30);
			Assert.assertTrue(commonUtils.displayed(pickx_mix_page_title));
		}
    }
	
	@Given("^User selects recommended in movies and series swimlane$")
	public void user_selects_recommended_in_movies_and_series_swimlane() throws Throwable {
		commonUtils.waitTillVisibility(HomeView, 20);
		// To verify if the last swimlane has reached, using counter value in while loop
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		commonUtils.waitTillVisibility(swimlane, 20);
		commonUtils.waitTillVisibility(swimlane_title, 20);
		while (counter < 6) {
			Thread.sleep(20000);
			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
				System.out.println("lastswimlane   " + lastswimlane);
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception e1) {
					System.out.println("Catch   ");
					counter++;
//					throw new TestException(
//							String.format("Swimlane title not found"));
					continue;
				}
			}
			for (int j = 0; j < swimlaneList.size(); j++) {
				System.out.println("swimlane.findElements(swimlane_title).size()  "
						+ swimlaneList.get(j).findElements(swimlane_title).size());
				if (swimlaneList.get(j).findElements(swimlane_title).size() < 1)
					continue;
				if (swimlaneList.get(j).findElements(program_title_under_swimlane).size() < 1) {
					System.out.println("Program title not specified");
					break;
				}
				if (swimlaneList.get(j).findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(
						commonUtils.getTextBasedonLanguage("recommended_movies_and_series_swimlane").toLowerCase())) {
					System.out.println("Found");

					if (!swimlaneList.get(j).findElement(commonUtils.findElementByXpathContains("value",
							commonUtils.getTextBasedonLanguage("view_all"))).isDisplayed()) {
						System.out.println("Not displayed in IOS");
						continue;
					}
					System.out.println("View all displayed in IOS");
					swimlaneList.get(j).findElement(commonUtils.findElementByXpathContains("value",
							commonUtils.getTextBasedonLanguage("view_all"))).click();

					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastswimlane.equals(temp))
				counter++;
			temp = lastswimlane;
			commonUtils.swipeUpOverScreen();
		}
		commonUtils.waitTillVisibility(movies_and_series_page_title, 30);
		Assert.assertTrue(commonUtils.displayed(movies_and_series_page_title));
	}

	public void metadata_provided_for_channel_programs(MobileElement program, boolean isLive) {
// try {
// program.findElement(swimlane_program_poster);
// } catch (Exception e) {
// throw new TestException(
// String.format("Program poster not displayed for program under 'Now on TV' swimlane"));
// }
		if (isLive)
			try {
				program.findElement(live_icon_in_swimlane);
			} catch (Exception e) {
				throw new TestException(
						String.format("Live label not displayed for program under 'Now on TV' swimlane"));
			}
// try {
// program.findElement(channel_icon);
// } catch (Exception e) {
// throw new TestException(String.format("Channel icon not displayed for program under 'Now on TV' swimlane"));
// }
		try {
			program.findElement(program_title_under_swimlane);
		} catch (Exception e) {
			throw new TestException(
					String.format("Program title not displayed for program under 'Now on TV' swimlane"));
		}
		try {
			program.findElement(program_subtitle_under_swimlane);
		} catch (Exception e) {
			throw new TestException(
					String.format("Program subtitle not displayed for program under 'Now on TV' swimlane"));
		}
	}

	@And("^Verify if metadata provided for VOD items$")
	public void metadata_provided_for_vod(MobileElement program) {
//		try {
//			program.findElement(swimlane_program_poster);
//		} catch (Exception e) {
//			throw new TestException(
//					String.format("Program poster not displayed for program under 'Now on TV' swimlane"));
//		}
		try {
			program.findElement(program_title_under_swimlane);
		} catch (Exception e) {
			throw new TestException(
					String.format("Program title not displayed for program under 'Now on TV' swimlane"));
		}
		try {
			program.findElement(program_subtitle_under_swimlane);
		} catch (Exception e) {
			throw new TestException(
					String.format("Program subtitle not displayed for program under 'Now on TV' swimlane"));
		}
	}

	@Given("^The user selects program from home screen$")
	public void user_selects_program_from_home_screen() throws Throwable {
		commonUtils.waitTillVisibility(HomeView, 20);
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		commonUtils.waitTillVisibility(swimlane, 20);
		commonUtils.waitTillVisibility(swimlane_title, 20);
		while (counter < 3) {

			List<MobileElement> swimlaneFullList = commonUtils.findElements(swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
				System.out.println("lastswimlane   " + lastswimlane);
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception e1) {
					System.out.println("Catch   ");
					throw new TestException(String.format("Swimlane title not found"));
				}
			}
			for (MobileElement swimlanes : swimlaneList) {
				if (swimlanes.findElements(swimlane_title).size() < 1)
					continue;
				if (swimlanes.findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(
						commonUtils.getTextBasedonLanguage("recommended_movies_and_series_swimlane").toLowerCase())) {
					Assert.assertTrue(swimlanes.findElement(Recommended_movies_series_viewAll).isDisplayed());
					if (swimlanes.findElements(program_title_under_swimlane).isEmpty()) {
						continue;
					}
					swimlanes.findElements(program_title_under_swimlane).get(0).click();
					if (commonUtils.enabled(parental_pin_input)) {
						settingPage.enter_parental_pin_for_programs();
//						commonUtils.waitTillInvisibility(, counter);
						continue;
					}
					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastswimlane.equals(temp))
				counter++;
			temp = lastswimlane;
			commonUtils.swipeUpOverScreen();
			Thread.sleep(30000);
		}
		Assert.assertTrue(swimlaneFound);
	}

	@Given("^The user selects movie genre from home page$")
	public void user_selects_movie_genre() throws Throwable {
		home_screen_categories_are_displayed();
		List<MobileElement> categoryElements = commonUtils.findElements(home_categories);
		categoryElements.get(3).click();
		commonUtils.waitTillVisibility(movies_categoryPage_title, 20);
		select_recommended_movies(swimlane);
		commonUtils.waitTillVisibility(movies_and_series_page_title, 20);
		Assert.assertTrue(commonUtils.displayed(movies_and_series_page_title));
		swimlanePage.user_selects_movie_category();
	}

	public void select_recommended_movies(By container_swimlane) throws InterruptedException {
		int counter = 0;
		String temp = null;
		boolean svodItemFound = false;
		String lastProgram = null;
//		swimlane_view_all_button_text = commonUtils.getTextBasedonLanguage("all_text");
//		swimlane_view_all_button = commonUtils.findElementByXpathValueName(text, swimlane_view_all_button_text);
		while (counter < 3) {
			Thread.sleep(10000);
			List<MobileElement> swimlaneFullList = commonUtils.findElements(container_swimlane);
			List<MobileElement> swimlaneList = new ArrayList<MobileElement>();
			for (int i = 0; i < swimlaneFullList.size(); i++) {
				if (swimlaneFullList.get(i).isDisplayed()) {
					swimlaneList.add(swimlaneFullList.get(i));
				}
			}
			// commonUtils.waitTillVisibility(swimlane_title, 30);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
			}
			String title = "";
			for (MobileElement swimlanes : swimlaneList) {
				try {
					title = swimlanes.findElement(swimlane_title).getText();
				} catch (Exception e) {
					continue;
				}
				if (swimlanes.findElements(swimlane_title).size() < 1)
					continue;
				if (title.equalsIgnoreCase(
						commonUtils.getTextBasedonLanguage("recommended_movies_and_series_swimlane").toLowerCase())) {
					swimlanes.findElement(swimlane_view_all_button).click();
					svodItemFound = true;
					break;
				}
			}
			if (svodItemFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverHomeScreen();
		}
		Assert.assertTrue(svodItemFound);
	}
}
