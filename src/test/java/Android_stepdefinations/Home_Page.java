package Android_stepdefinations;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.TestException;

import Android_screens.Home_Screen;
import Android_screens.LiveTV_Screen;
import Android_screens.Program_Details_Screen;
import Android_screens.Search_screen;
import Android_screens.Setting_Screen;
import Android_screens.Swimlane_Contents_Screen;
import Android_screens.TVguide_Screen;
import Android_screens.Vod_Details_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Home_Page implements Home_Screen, Setting_Screen, Swimlane_Contents_Screen, LiveTV_Screen,
		Program_Details_Screen, TVguide_Screen,Vod_Details_Screen,Search_screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Program_Details_Page programDetalsPage;
	public Android_input_properties inputProperties;
	public Setting_Page settingPage;
	public Swimlane_Contents_Page swimlanePage;
	public TVguide_Page tvGuidePage;
	public Live_Tv_Page liveTvPage;
	public Login_Page loginPage;	
	public String Bottom_navigation_LiveTv_Text;
	public String Bottom_navigation_TVguide_Text;
	public String Bottom_navigation_MyVideos_Text;
	public String swimlane_view_all_button_text;
	public String Bottom_navigation_Recodrings;
	  public String text = "text";
	  public By swimlane_view_all_button;
	
	public Home_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new Android_input_properties();
		settingPage = new Setting_Page();
		programDetalsPage = new Program_Details_Page();
		swimlanePage = new Swimlane_Contents_Page();
		tvGuidePage = new TVguide_Page();
		liveTvPage = new Live_Tv_Page();
		loginPage = new Login_Page();
	}

	@Given("^User selects ongoing program from home screen$")
	public void user_selects_ongoing_program_from_home_screen() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		// Swipe over the home screen to find swimlane with live icons
		int counter = 0;
		String temp = null;
		boolean ongoingProgramFound = false;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		// To verify if the user has swiped till the last swimlane the counter value is
		// provided. For verifying if the last swimlane has reached, after each swipe
		// the program title of the last swimlane is compared with that of the previous
		// swipe. And if the titles are same the counter value is incremented. Since
		// there are swimlanes with same title, this comparison is repeated till counter
		// value reaches 3
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			String lastSwimlaneProgramTitle;
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
					if (!commonUtils.displayed(player_screen))
						settingPage.enter_parental_pin_for_programs();
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
	public void user_selects_completed_replay_program_from_home() throws Throwable {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean replayProgramFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (!commonUtils.displayed(hero_banner_container)) {
			hero_content_is_present_on_the_home_screen();
			commonUtils.swipeUpOverHomeScreen();
		}
		else {
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
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
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
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					try {
						lastProgramTitle = programList.get(programList.size() - 1)
								.findElement(program_title_under_swimlane).getText();
					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2)
								.findElement(program_title_under_swimlane).getText();
					}
					for (MobileElement program : programList) {
						try {
							program.findElement(replay_icon);
						} catch (Exception e) {
							continue;
						}
						String subtitle = null;
						try {
							subtitle = program.findElement(program_subtitle_under_swimlane).getText();
						} catch (Exception e) {
							continue;
						}
						// Checking if program is a non VOD asset
						if (!subtitle.contains("|")) {
							switchToNextSwimlane = true;
							continue;
						}
						// Verifying if the airing is already completed
						if (subtitle.contains(commonUtils.getTextBasedonLanguage_Android("Tomorrow"))) {
							continue;
						} else if (subtitle.contains(commonUtils.getTextBasedonLanguage_Android("Yesterday"))) {
							replayProgramFound = true;
						} else if (subtitle.contains(commonUtils.getTextBasedonLanguage_Android("Today"))) {
							if (commonUtils.if_time_is_past(subtitle.split("\\|")[1].split("-")[1].trim())) {
								replayProgramFound = true;
							}
						} else {
							if (commonUtils.verify_if_date_is_past_date(subtitle.split("\\|")[0].trim())) {
								replayProgramFound = true;
							}
						}
						if (replayProgramFound) {
							if (commonUtils.checkBounds(program.findElement(program_title_under_swimlane)) < 210) {
								replayProgramFound = false;
								continue;
							}

							program.findElement(program_title_under_swimlane).click();

							if (commonUtils.displayed(parental_pin_input)) {
								settingPage.enter_parental_pin_for_programs();
								program.findElement(program_title_under_swimlane).click();

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
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean vodItemFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
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
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane).getText();
				System.out.println("lastProgram in try  " +lastProgram);
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane).getText();
				System.out.println("lastProgram in Catch  " +lastProgram);
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0)
					continue;
				String subtitle = null;
				try {
					subtitle = swimlane.findElement(program_subtitle_under_swimlane).getText();
				} catch (Exception e) {
					continue;
				}
				if (subtitle.contains("|"))
					continue;
				vodItemFound = true;
				swimlane.findElement(program_subtitle_under_swimlane).click();
				if (!commonUtils.displayed(player_screen)) {
					settingPage.enter_parental_pin_for_programs();
					swimlane.findElement(program_subtitle_under_swimlane).click();
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
		Assert.assertTrue(programDetalsPage.user_on_program_details_page());
	}

	@Given("^Hero content is present on the home screen$")
	public void hero_content_is_present_on_the_home_screen() throws Throwable {
		//commonUtils.waitTillVisibility(hero_banner_channel_logo, 20);
		//Thread.sleep(10000);
		if(commonUtils.displayed(hero_banner_container)) {
			System.out.println("Hero Banner is Present");
		}
		
		else {
			settingPage.the_user_is_on_settings_page();
			settingPage.user_login_to_parental_control();
			settingPage.user_turn_on_parental_control();
			settingPage.user_turn_off_parental_control();
			commonUtils.clickonback();
			commonUtils.clickonback();
			try {
				if(!commonUtils.displayed(hero_banner_container)) {
					System.out.println("Hero Banner is not Present even after disabiling Parental control");
					driver.closeApp();
					driver.launchApp();
					loginPage.the_user_selects_the_environment();
					loginPage.the_user_accepts_the_terms_and_condition();
					Thread.sleep(10000);
				}
				Assert.assertTrue(commonUtils.displayed(hero_banner_container));
			} catch (Exception e) {
				throw new TestException(String.format("Hero banner not found"));
			}
		}
		
	}

	@When("^User can see hero banner content are present$")
	public void user_can_see_hero_banner_content_are_present() {
		Assert.assertTrue(commonUtils.displayed(hero_banner_channel_logo));
		Assert.assertTrue(commonUtils.displayed(textview_hero_title));
		Assert.assertTrue(commonUtils.displayed(textview_hero_metadata));
		Assert.assertTrue(commonUtils.displayed(hero_banner_watch_button));
		// Assert.assertTrue(commonUtils.displayed(hero_banner_icon_container));
	}

	@When("^User can see bottom navigation menu items$")
	public void user_can_see_bottom_navigation_menu_items() {
		Assert.assertTrue(commonUtils.displayed(home_button));
		Bottom_navigation_LiveTv_Text = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_LiveTv");
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(Bottom_navigation_LiveTv_Text)));
		Bottom_navigation_TVguide_Text = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_TVguide");
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(Bottom_navigation_TVguide_Text)));
		Assert.assertTrue(commonUtils.displayed(settings_button));
		Assert.assertTrue(commonUtils.displayed(search_navigation_button));
		Bottom_navigation_Recodrings = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_Recordings");
		if(!commonUtils.displayed(commonUtils.findElementByAccessibilityid(Bottom_navigation_Recodrings))) {
			Bottom_navigation_MyVideos_Text = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_MyVideos");
			Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(Bottom_navigation_MyVideos_Text)));
		}
		else {
			Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(Bottom_navigation_Recodrings)));
		}

	}

	@When("^User can see metadata for programs under Now on TV swimlane$")
	public void user_can_see_metadata_for_programs_under_now_on_tv() {
		int counter = 0;
		String temp = null;
		boolean nowOnTvFound = false;
		try {
			commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		} catch (Exception e) {
			System.out.println("Tablet no poster found in login");
		}
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				try {
					lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
							.findElement(program_title_under_swimlane).getText();
				} catch (Exception err) {
					System.out.println("No program title displayed");
					commonUtils.swipeUpOverHomeScreen();
					continue;
				}
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					try {
						swimlane.findElement(program_in_swimlane);
					} catch (Exception e) {
						throw new TestException(String.format("Programs not found under 'Now on TV' swimlane"));
					}
					metadata_provided_for_channel_programs(swimlane.findElement(program_in_swimlane), true);
					nowOnTvFound = true;
					break;
				}
			}
			if (nowOnTvFound)
				break;
			if (lastSwimlaneProgramTitle.equals(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverHomeScreen();
		}
	}

	public void metadata_provided_for_channel_programs(MobileElement program, boolean isLive) {
		try {
			program.findElement(swimlane_program_poster);
		} catch (Exception e) {
			throw new TestException(
					String.format("Program poster not displayed for program under 'Now on TV' swimlane"));
		}
		if (isLive)
			try {
				program.findElement(live_icon_in_swimlane);
			} catch (Exception e) {
				throw new TestException(
						String.format("Live label not displayed for program under 'Now on TV' swimlane"));
			}
		try {
			program.findElement(channel_icon);
		} catch (Exception e) {
			throw new TestException(String.format("Channel icon not displayed for program under 'Now on TV' swimlane"));
		}
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

	@Given("^Home screen categories are displayed$")
	public void home_screen_categories_are_displayed() {
		Assert.assertTrue(commonUtils.displayed(home_category_container));
	}

	@When("^The user validates the displayed categories on the homepage$")
	public void user_validates_the_displayed_categories_in_homepage() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		while (counter < 3) {
			List<MobileElement> categoryElements = commonUtils.findElements(home_categories);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				categoriesDisplayed.add(categoryName.toLowerCase());
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
		System.out.println("categoriesDisplayed     " + categoriesDisplayed);
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("movies")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("series")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("entertainment")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("sports")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("discovery")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("kids")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("music")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("news")));
		
	/*	Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getMovies()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getSeries()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getEntertainment()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getSports()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getDiscovery()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getKids()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getMusic()));
		Assert.assertTrue(categoriesDisplayed.contains(inputProperties.getNews())); */
	}

	@Given("^User selects recommended in movies and series$")
	public void user_selects_recommended_in_movies_and_series() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		// To verify if the last swimlane has reached, using counter value in while loop
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		swimlane_view_all_button_text = commonUtils.getTextBasedonLanguage_Android("all_text");
		swimlane_view_all_button = commonUtils.findElementByXpathValueName(text,swimlane_view_all_button_text);
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception eTab) {
					commonUtils.swipeUpOverHomeScreen();
					System.out.println("Tab : program tile not displayed - Swipe up");
					continue;
				}
			}
			for (MobileElement swimlane : swimlaneList) {
				try {
					if (swimlane.findElements(swimlane_title).size() < 1)
						continue;
					if (swimlane.findElements(program_subtitle_under_swimlane).size() < 1)
						break;
				} catch (Exception e) {
					System.out.println("Program title not displayed");
					continue;
				}
				if (swimlane.findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(inputProperties
						.getElementString("recommended_movies_and_series_swimlane", commonUtils.getDeviceLanguage()))) {
					swimlane.findElement(swimlane_view_all_button);
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
			throw new TestException(String.format("Recommended movies and series not found"));
	}

	@Given("^User selects recommended in movies and series swimlane$")
	public void user_selects_recommended_in_movies_and_series_swimlane() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		// To verify if the last swimlane has reached, using counter value in while loop
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		swimlane_view_all_button_text = commonUtils.getTextBasedonLanguage_Android("all_text");
		swimlane_view_all_button = commonUtils.findElementByXpathValueName(text,swimlane_view_all_button_text);
		while (counter < 4) {
			commonUtils.waitTillVisibility(swimlane_title, 20);
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			System.out.println("swimlaneList.size() " + swimlaneList.size());
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception eTab) {
					commonUtils.swipeUpOverHomeScreen();
					System.out.println("Tab : program tile not displayed - Swipe up");
					continue;
				}
			}
			for (MobileElement swimlane : swimlaneList) {
				try {
					if (swimlane.findElements(swimlane_title).size() < 1)
						continue;
				} catch (Exception e) {
					continue;
				}
				System.out.println("swimlane.findElement(swimlane_title).getText()   "
						+ swimlane.findElement(swimlane_title).getText());
				try {
					String recommended_movies_and_series_swimlane = inputProperties.getElementString("recommended_movies_and_series_swimlane",
							commonUtils.getDeviceLanguage());
					if (swimlane.findElement(swimlane_title).getText()
							.equalsIgnoreCase(recommended_movies_and_series_swimlane)) {
					swimlane.findElement(swimlane_view_all_button).click();
						commonUtils.waitTillVisibility(movies_and_series_page_title, 30);
						swimlaneFound = true;
						break;
					}
				} catch (Exception e) {
					continue;
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
			throw new TestException(String.format("Recommended for you not found"));
		Assert.assertTrue(commonUtils.displayed(movies_and_series_page_title));
	}

	@Given("^User records and validate episode of live airing from homescreen$")
	public void user_records_and_validate_episode_of_live_airing_from_homescreen() {
		AndroidElement nowOnTv = returnNowOnTvSwimlane();
		if (!select_program_from_homescreen_for_recording(nowOnTv, true, true))
			throw new TestException(String
					.format("Live airing which is part of series and can be recorded not found from home screen"));
		Map<String, String> programDetails = programDetalsPage
				.records_series_program_and_validate_updated_details(true);
		programDetalsPage.close_program_details_page_to_reach_homescreen();
		MobileElement program = verify_recording_icon_present_over_program_in_homescreen(programDetails);
		program.click();
		programDetalsPage.stop_recording_episode();
		programDetalsPage.delete_recording();
	}

	public AndroidElement returnNowOnTvSwimlane() {
		int counter = 0;
		String temp = null;
		boolean nowOnTvSwimlaneFound = false;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		AndroidElement nowOnTvSwimlane = null;
		// To verify if the user has swiped till the last swimlane the counter value is
		// provided. For verifying if the last swimlane has reached, after each swipe
		// the program title of the last swimlane is compared with that of the previous
		// swipe. And if the titles are same the counter value is incremented. Since
		// there are swimlanes with same title, this comparison is repeated till counter
		// value reaches 3
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					nowOnTvSwimlane = (AndroidElement) swimlane;
					nowOnTvSwimlaneFound = true;
					break;
				}
			}
			if (nowOnTvSwimlaneFound)
				break;
			if (lastSwimlaneProgramTitle.equalsIgnoreCase(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
		if (!nowOnTvSwimlaneFound)
			throw new TestException(String.format("Ongoing program not found in the home screen"));
		return nowOnTvSwimlane;
	}

	public boolean select_program_from_homescreen_for_recording(AndroidElement swimlaneElement, boolean isSeries,
			boolean isOngoing) {
		int counter = 0;
		String temp = null;
		boolean programFound = false;
		while (counter < 3) {
			// Applying same logic of swiping vertically over the screen for swiping
			// horizontally over the swimlane
			List<MobileElement> programList = swimlaneElement.findElements(program_in_swimlane);
			String lastProgramTitle;
			try {
				lastProgramTitle = programList.get(programList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				commonUtils.swipeUpOverHomeScreen();
				lastProgramTitle = programList.get(programList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			}
			// Swipe so that the program tile is completely visible
			programList.get(0).click();
			if (programDetalsPage.is_program_to_be_recorded()) {
				if (isOngoing) {
					if (programDetalsPage.verify_if_ongoing_program_is_part_of_series() == isSeries) {
						programFound = true;
						break;
					}
				} else {
					if (programDetalsPage.verify_if_upcoming_program_is_part_of_series() == isSeries) {
						programFound = true;
						break;
					}
				}
			}
			programDetalsPage.close_program_details_page_to_reach_homescreen();
			if (lastProgramTitle.equals(temp))
				counter++;
			temp = lastProgramTitle;
			commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(0));
		}
		return programFound;
	}

	public MobileElement verify_recording_icon_present_over_program_in_homescreen(Map<String, String> programDetails) {
		boolean programFound = false;
		List<MobileElement> programsList = commonUtils.findElements(program_in_swimlane);
		for (MobileElement program : programsList) {
			if (program.findElement(program_title_under_swimlane).getText().contains(programDetails.get("title"))) {
				programFound = true;
				if (program.findElements(recording_icon_in_homescreen).size() < 1)
					throw new TestException(
							String.format("Recording icon not displayed in the homescreen poster of the program"));
				return program.findElement(program_title_under_swimlane);
			}
		}
		if (!programFound)
			throw new TestException(
					String.format("Couldn't find " + programDetails.get("title") + " in the home screen"));
		return null;
	}

	@Given("^User records and validate live airing not part of series from homescreen$")
	public void record_and_validate_live_airing_not_part_of_series_from_homescreen() {
		AndroidElement nowOnTv = returnNowOnTvSwimlane();
		if (!select_program_from_homescreen_for_recording(nowOnTv, false, true))
			throw new TestException(String
					.format("Live airing which is not part of series and can be recorded not found from home screen"));
		Map<String, String> programDetails = programDetalsPage
				.records_non_series_program_and_validate_updated_details(true);
		programDetalsPage.close_program_details_page_to_reach_homescreen();
		MobileElement program = verify_recording_icon_present_over_program_in_homescreen(programDetails);
		program.click();
		programDetalsPage.stop_recording_non_series_item();
		programDetalsPage.delete_recording();
	}

	@Given("^User validates series recording of replay airing from homescreen$")
	public void validate_series_recording_of_replay_airing_from_homescreen() throws ParseException {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean replayProgramFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
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
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
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
				int counter2 = 0;
				String temp2 = null;
				boolean switchToNextSwimlane = false;
				// Applying same logic of swiping vertically over the screen for swiping
				// horizontally over the swimlane
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					String lastProgramTitle = programList.get(programList.size() - 1)
							.findElement(program_title_under_swimlane).getText();
					for (MobileElement program : programList) {
						try {
							program.findElement(replay_icon);
						} catch (Exception e) {
							continue;
						}
						String subtitle = null;
						try {
							subtitle = program.findElement(program_subtitle_under_swimlane).getText();
						} catch (Exception e) {
							continue;
						}
						// Checking if program is a non VOD asset
						if (!subtitle.contains("|")) {
							switchToNextSwimlane = true;
							break;
						}
						// Verifying if the airing is already completed
						if (subtitle.contains("Tomorrow")) {
							continue;
						} else if (subtitle.contains("Today")) {
							if (!commonUtils.if_time_is_past(subtitle.split("\\|")[1].split("-")[1].trim()))
								continue;
						} else if (!subtitle.contains("Yesterday")) {
							if (!commonUtils.verify_if_date_is_past_date(subtitle.split("\\|")[0].trim()))
								continue;
						}
						program.click();
						if (programDetalsPage.is_program_to_be_recorded()) {
							if (programDetalsPage.verify_if_upcoming_program_is_part_of_series()) {
								replayProgramFound = true;
								break;
							}
						}
						programDetalsPage.close_program_details_page_to_reach_homescreen();
					}
					if (switchToNextSwimlane || replayProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftOverElement(programList.get(programList.size() - 1));
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
		Map<String, String> programDetails = programDetalsPage
				.records_completed_series_program_and_validate_updated_details();
		programDetalsPage.close_program_details_page_to_reach_homescreen();
		MobileElement program = verify_recording_icon_present_over_program_in_homescreen(programDetails);
		program.click();
		programDetalsPage.stop_series_recording_of_completed_program_and_validate_updated_details();
	}

	@Given("^User validates recording of replay airing not part of series from homescreen$")
	public void validate_replay_airing_recording_not_part_of_series_from_homescreen() throws ParseException {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean replayProgramFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
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
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
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
				int counter2 = 0;
				String temp2 = null;
				boolean switchToNextSwimlane = false;
				// Applying same logic of swiping vertically over the screen for swiping
				// horizontally over the swimlane
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					String lastProgramTitle = programList.get(programList.size() - 1)
							.findElement(program_title_under_swimlane).getText();
					for (MobileElement program : programList) {
						try {
							program.findElement(replay_icon);
						} catch (Exception e) {
							continue;
						}
						String subtitle = null;
						try {
							subtitle = program.findElement(program_subtitle_under_swimlane).getText();
						} catch (Exception e) {
							continue;
						}
						// Checking if program is a non VOD asset
						if (!subtitle.contains("|")) {
							switchToNextSwimlane = true;
							break;
						}
						// Verifying if the airing is already completed
						if (subtitle.contains("Tomorrow")) {
							continue;
						} else if (subtitle.contains("Today")) {
							if (!commonUtils.if_time_is_past(subtitle.split("\\|")[1].split("-")[1].trim()))
								continue;
						} else if (!subtitle.contains("Yesterday")) {
							if (!commonUtils.verify_if_date_is_past_date(subtitle.split("\\|")[0].trim()))
								continue;
						}
						program.click();
						if (programDetalsPage.is_completed_program_non_series()) {
							replayProgramFound = true;
							break;
						}
						programDetalsPage.close_program_details_page_to_reach_homescreen();
					}
					if (switchToNextSwimlane || replayProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftOverElement(programList.get(programList.size() - 1));
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
		if (!replayProgramFound)
			throw new TestException(
					String.format("Couldn't find replayable completed non-series program from home screen"));
	}

	@When("^User can see metadata for programs under Recommended swimlane$")
	public void user_can_see_metadata_for_programs_under_recommended_swimlane() {
		int counter = 0;
		String temp = null;
		boolean swimlaneFound = false;
		try {
			commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		} catch (Exception e) {
			System.out.println("Tablet no poster found in login");
		}
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				String swimlaneTitle;
				try {
					swimlaneTitle = swimlane.findElement(swimlane_title).getText();
					swimlane.findElement(program_subtitle_under_swimlane).getText();
				} catch (Exception e) {
					continue;
				}
				if (swimlaneTitle.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("recommended_for_you"))) {
					try {
						swimlane.findElement(program_in_swimlane);
					} catch (Exception e) {
						throw new TestException(
								String.format("Programs not found under 'Recommended for you' swimlane"));
					}
					metadata_provided_for_channel_programs(swimlane.findElement(program_in_swimlane), false);
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
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Recommended for you' swimlane not found"));
	}

	@And("^User can see metadata for programs under VOD swimlane$")
	public void user_can_see_metadata_for_programs_under_VOD_swimlane() {
		int counter = 0;
		String temp = null;
		boolean swimlaneFound = false;
		try {
			commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		} catch (Exception e) {
			System.out.println("Tablet no poster found in login");
		}
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 6) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				String swimlaneTitle;
				try {
					swimlaneTitle = swimlane.findElement(swimlane_title).getText();
					swimlane.findElement(program_subtitle_under_swimlane);
				} catch (Exception e) {
					continue;
				}
				if (swimlaneTitle.equalsIgnoreCase(inputProperties
						.getElementString("recommended_movies_and_series_swimlane", commonUtils.getDeviceLanguage()))) {
					try {
						swimlane.findElement(program_in_swimlane);
					} catch (Exception e) {
						throw new TestException(
								String.format("Programs not found under 'Recommended in Movies & Series' swimlane"));
					}
					metadata_provided_for_vod(swimlane.findElement(program_in_swimlane));
					swimlaneFound = true;
					break;
				}
			}
			if (swimlaneFound)
				break;
			if (!lastSwimlaneProgramTitle.equals(temp))
				counter = 0;
			if (lastSwimlaneProgramTitle.equals(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Recommended in Movies & Series' swimlane not found"));
	}

	@And("^Verify if metadata provided for VOD items$")
	public void metadata_provided_for_vod(MobileElement program) {
		try {
			program.findElement(swimlane_program_poster);
		} catch (Exception e) {
			throw new TestException(String.format(
					"Program poster not displayed for program under 'Recommended for Movies and Series' swimlane"));
		}
		try {
			program.findElement(program_title_under_swimlane);
		} catch (Exception e) {
			throw new TestException(String.format(
					"Program title not displayed for program under 'Recommended for Movies and Series' swimlane"));
		}
		try {
			program.findElement(program_subtitle_under_swimlane);
		} catch (Exception e) {
			throw new TestException(String.format(
					"Program subtitle not displayed for program under 'Recommended for Movies and Series' swimlane"));
		}
	}

	@Then("^User can see metadata for programs under future swimlane$")
	public void user_can_see_metadata_for_programs_under_future_swimlane() {
		int counter = 0;
		String temp = null;
		boolean swimlaneFound = false;
		try {
			commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		} catch (Exception e) {
			System.out.println("Tablet no poster found in login");
		}
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			String lastSwimlaneProgramTitle = null;
			System.out.println("swimlaneList  " + swimlaneList.size());
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				if (swimlaneList.get(swimlaneList.size() - 2).findElements(program_title_under_swimlane).isEmpty()) {
					System.out.println("No swimlane program");
					continue;
				}
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				String swimlaneTitle;
				try {
					swimlaneTitle = swimlane.findElement(swimlane_title).getText();
					swimlane.findElement(program_subtitle_under_swimlane);
				} catch (Exception e) {
					continue;
				}
				if (swimlaneTitle.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("coming_up_on_tv"))) {
					try {
						swimlane.findElement(program_in_swimlane);
					} catch (Exception e) {
						throw new TestException(String.format("Programs not found under 'Coming up on TV' swimlane"));
					}
					metadata_provided_for_channel_programs(swimlane.findElement(program_in_swimlane), false);
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
		}
		if (!swimlaneFound)
			throw new TestException(String.format("'Coming up on TV' swimlane not found"));
	}

	@Given("^User validates recording of future airing not part of series from homescreen$")
	public void validate_recording_of_future_airing_not_part_of_series_from_homescreen() throws ParseException {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		AndroidElement swimlane = user_selects_recommended_in_sports_swimlane();
		if (!select_program_from_homescreen_for_recording(swimlane, false, false))
			throw new TestException(String.format(
					"Future airing which is not part of series and can be recorded not found from home screen"));
		Map<String, String> programDetails = programDetalsPage
				.records_non_series_program_and_validate_updated_details(false);
		programDetalsPage.close_program_details_page_to_reach_homescreen();
		MobileElement program = verify_recording_icon_present_over_program_in_homescreen(programDetails);
		program.click();
		programDetalsPage.cancel_recording_non_series_item();
	}

	public boolean if_program_is_upcoming_non_series_recordable_program(MobileElement program) {
		boolean returnValue = false;
		program.click();
		if (!programDetalsPage.verify_if_upcoming_program_is_part_of_series()) {
			if (programDetalsPage.is_program_to_be_recorded()) {
				return true;
			}
		}
		programDetalsPage.close_program_details_page_to_reach_homescreen();
		return returnValue;
	}

	@Given("^User selects recommended in sports swimlane$")
	public AndroidElement user_selects_recommended_in_sports_swimlane() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		// To verify if the last swimlane has reached, using counter value in while loop
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		while (counter < 4) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
			} catch (Exception e) {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(swimlane_title).size() < 1)
					continue;
				if (swimlane.findElement(swimlane_title).getText().toLowerCase()
						.equalsIgnoreCase(inputProperties.getRecommendedInSports())) {
					swimlaneFound = true;
					return (AndroidElement) swimlane;
				}
			}
			if (swimlaneFound)
				break;
			if (lastswimlane.equals(temp))
				counter++;
			temp = lastswimlane;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!swimlaneFound)
			throw new TestException(
					String.format("Couldn't find 'Recommended in All Sports' swimlane from home screen"));
		return null;
	}

	@Given("^Validate homescreen loading skeleton$")
	public void validate_homescreen_loading_skeleton() {
		Assert.assertTrue(commonUtils.displayed(HomeScreen_layout));
		Assert.assertTrue(commonUtils.displayed(HomeScreen_layout_full));
		Assert.assertTrue(commonUtils.displayed(HomeScreen_horizandal_scrollview));
	}

	@Given("^User see locked Hero banner$")
	public void user_see_locked_hero_banner() {
		Assert.assertTrue(commonUtils.displayed(hero_banner_container));
		commonUtils.displayed(svod_icon_locked);
	}

	@When("^Tiles are properly locked $")
	public void tiles_are_properly_locked() {
		Assert.assertTrue(commonUtils.displayed(swimlane_content_page_program_title));
		Assert.assertTrue(commonUtils.displayed(episode_subtitle));
		Assert.assertTrue(commonUtils.displayed(svod_icon_age));
	}

	@Then("^User unlocks the locked hero banner$")
	public void user_unlocks_the_locked_hero_banner() {
		commonUtils.clickonElement(hero_banner_container);
		settingPage.enter_parental_pin_for_programs();
		Assert.assertTrue(commonUtils.displayed(hero_banner_watch_button));
	}

	public void select_recommended_movies(By container_swimlane) throws InterruptedException {
		int counter = 0;
		String temp = null;
		boolean svodItemFound = false;
		String lastProgram = null;
		swimlane_view_all_button_text = commonUtils.getTextBasedonLanguage_Android("all_text");
		swimlane_view_all_button = commonUtils.findElementByXpathValueName(text,swimlane_view_all_button_text);
		while (counter < 3) {
			Thread.sleep(10000);
			List<MobileElement> swimlaneList = commonUtils.findElements(container_swimlane);
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
				if (title.equalsIgnoreCase(inputProperties.getElementString("recommended_movies_and_series_swimlane",
						commonUtils.getDeviceLanguage()))) {
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
	}

	@Given("^The user selects movie genre from home page$")
	public void user_selects_movie_genre() throws Throwable  {
		home_screen_categories_are_displayed();
		List<MobileElement> categoryElements = commonUtils.findElements(home_categories);
		categoryElements.get(0).click();
		select_recommended_movies(movie_swimlane);
		commonUtils.waitTillVisibility(movies_and_series_page_title, 20);
		Assert.assertTrue(commonUtils.displayed(movies_and_series_page_title));
		swimlanePage.user_selects_movie_category();
	}

	@Given("^The user selets program from home screen$")
	public void user_selets_program_from_home_screen() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		String lastswimlane = null;
		boolean swimlaneFound = false;
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			if (swimlaneList.isEmpty()) {
				commonUtils.swipeUpOverHomeScreen();
				continue;
			}
			try {
				lastswimlane = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title).getText();
			} catch (Exception e) {
				try {
					lastswimlane = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title).getText();
				} catch (Exception eTab) {
					commonUtils.swipeUpOverHomeScreen();
					System.out.println("Tab : program tile not displayed - Swipe up");
					continue;
				}
			}
			for (int i = 0; i < swimlaneList.size(); i++) {
//    	    for (MobileElement swimlane : swimlaneList) {
				try {
					if (swimlaneList.get(i).findElements(swimlane_title).size() < 1)
						continue;
				} catch (Exception e) {
					continue;
				}
				if (swimlaneList.get(i).findElement(swimlane_title).getText().toLowerCase()
						.equalsIgnoreCase(inputProperties.getElementString("recommended_movies_and_series_swimlane",
								commonUtils.getDeviceLanguage()))) {
					if (swimlaneList.get(i).findElements(program_in_swimlane).isEmpty()) {
						continue;
					}
					if (swimlaneList.get(i).findElement(program_in_swimlane)
							.findElements(program_subtitle_under_swimlane).isEmpty()) {
						continue;
					}
					System.out.println(swimlaneList.get(i).findElement(program_in_swimlane)
							.findElement(program_subtitle_under_swimlane).getText());
					swimlaneList.get(i).findElements(program_in_swimlane).get(0).click();
					if (commonUtils.displayed(parental_pin_input)) {
						settingPage.enter_parental_pin_for_programs();
						swimlaneList = commonUtils.findElements(swimlane_tiles);
//						swimlaneList.get(i).findElements(program_in_swimlane).get(0).click();
						if (swimlaneList.get(i).findElement(swimlane_title).getText().toLowerCase().equalsIgnoreCase(
								inputProperties.getElementString("recommended_movies_and_series_swimlane",
										commonUtils.getDeviceLanguage()))) {
							if (swimlaneList.get(i).findElements(program_in_swimlane).isEmpty()) {
								continue;
							}
							swimlaneList.get(i).findElements(program_in_swimlane).get(0).click();
						} else {
							continue;
						}
					}
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
	}

	public boolean next_live_program_in_swimlane(MobileElement swimlaneItem, Boolean replayable) throws Throwable {
		int counter2 = 0;
		String lastswimlaneProgram = null;
		Boolean swimlaneFound = false;
		String temp2 = null;
		while (counter2 < 3) {
			Thread.sleep(5000);
			List<MobileElement> swimlaneProgramList = swimlaneItem.findElements(program_in_swimlane);
			if (swimlaneItem.findElements(program_title_under_swimlane).size() < 1) {
				commonUtils.swipeUpOverHomeScreen();
				break;
			}
			if (swimlaneItem.findElements(program_title_under_swimlane).size() > 0) {
				try {
					lastswimlaneProgram = swimlaneProgramList.get(swimlaneProgramList.size() - 1)
							.findElement(program_title_under_swimlane).getText();
				} catch (Exception e) {
					lastswimlaneProgram = swimlaneProgramList.get(swimlaneProgramList.size() - 2)
							.findElement(program_title_under_swimlane).getText();
				}
//				for (MobileElement swimlaneProgram : swimlaneProgramList) {
				for(int i=0;i<swimlaneProgramList.size();i++) {
					swimlaneProgramList = swimlaneItem.findElements(program_in_swimlane);
					try {
						if (swimlaneProgramList.get(i).findElements(live_icon_in_swimlane).size() < 1)
							continue;
					} catch (Exception e) {
						continue;
					}
					if (swimlaneProgramList.get(i).findElements(live_icon_in_swimlane).size() > 0) {
						if (!swimlaneProgramList.get(i).findElement(live_icon_in_swimlane).getText().equalsIgnoreCase("now"))
							continue;
						if (replayable) {
							swimlaneFound = replayable_program_from_home(swimlaneProgramList.get(i));
							if (swimlaneFound) {
								break;
							}
						} else {
							swimlaneFound = not_replayable_program_from_home(swimlaneProgramList.get(i));
							if (swimlaneFound) {
								break;
							}
						}
					}
				}
				if (swimlaneFound)
					break;
			}
			if (swimlaneFound)
				break;
			if (lastswimlaneProgram.equals(temp2))
				counter2++;
			temp2 = lastswimlaneProgram;
			commonUtils.swipeLeftToSeeNextProgramInSwimlane(swimlaneProgramList.get(swimlaneProgramList.size() - 1));
		}
		return swimlaneFound;
	}

	public boolean not_replayable_program_from_home(MobileElement swimlaneProgram) {
//		if (swimlaneProgram.findElements(replay_icon).size() < 1) {
		swimlaneProgram.findElement(program_title_under_swimlane).click();
		try {
			commonUtils.waitTillVisibility(player_screen_swimlane, 20);
		} catch (Exception e) {
			if (commonUtils.displayed(parental_pin_input)) {
				commonUtils.clickonElement(parental_pin_cancel_button);
				return false;
//				settingPage.enter_parental_pin_for_programs();
//				commonUtils.waitTillInvisibility(lock_icon, 20);
//				swimlaneProgram.findElement(program_title_under_swimlane).click();
			}
		}
		if (commonUtils.displayed(player_screen_swimlane_Tab))
			commonUtils.clickonback();
		try {
			if (commonUtils.findElements(replay_icon_details).size() < 1) {
				Assert.assertTrue(commonUtils.findElements(replay_icon_details).isEmpty());
				programDetalsPage.close_program_details_page_to_reach_homescreen();
				return true;
			}
		} catch (Exception e) {
			System.out.println("Replay icon not found catch");
		}
		programDetalsPage.close_program_details_page_to_reach_homescreen();
//		}
		return false;
	}

	public boolean replayable_program_from_home(MobileElement swimlaneProgram) {
//		if (swimlaneProgram.findElements(replay_icon).size() > 0) {
		swimlaneProgram.findElement(program_title_under_swimlane).click();
//		swimlaneProgram.click();
		try {
			commonUtils.waitTillVisibility(player_screen_swimlane, 20);
		} catch (Exception e) {
			if (commonUtils.displayed(parental_pin_input)) {
				commonUtils.clickonElement(parental_pin_cancel_button);
				return false;
//				settingPage.enter_parental_pin_for_programs();
//				commonUtils.waitTillInvisibility(lock_icon, 20);
//				swimlaneProgram.findElement(program_title_under_swimlane).click();
			}
//			commonUtils.waitTillVisibility(synopsis, 20);
		}
		if (commonUtils.displayed(player_screen_swimlane_Tab))
			commonUtils.clickonback();
		if (commonUtils.findElements(replay_icon_details).size() > 0) {
			Assert.assertTrue(commonUtils.findElement(replay_icon_details).isDisplayed());
			programDetalsPage.close_program_details_page_to_reach_homescreen();
			return true;
		}
		programDetalsPage.close_program_details_page_to_reach_homescreen();
//		}
		return false;
	}

	@Given("^User validates live replayable program from Swimlane$")
	public void user_validates_live_replayable_program_from_swimlane() throws Throwable {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		// Swipe over the home screen to find swimlane with live icons
		int counter = 0;
		String temp = null;
		boolean replayable = true;
		boolean ongoingProgramFound = false;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		try {
			commonUtils.waitTillVisibility(swimlane_container, 20);
		} catch (Exception e) {
			System.out.println("Swimlane is not fully visible");
			System.out.println("Check if it is loading issue or is device a Tablet");
			commonUtils.swipeUpOverHomeScreen();
			commonUtils.waitTillVisibility(swimlane_container, 20);
		}
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				if (swimlaneList.size() < 2) {
					System.out.println("Swimlane size : " + swimlaneList.size());
					commonUtils.swipeUpOverScreen();
					continue;
				}
				if (swimlaneList.get(swimlaneList.size() - 2).findElements(program_title_under_swimlane).isEmpty()) {
					System.out.println("Swimlane program title not found, Continue Swipe up");
					commonUtils.swipeUpOverScreen();
					continue;
				}
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					ongoingProgramFound = next_live_program_in_swimlane(swimlane, replayable);
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
			throw new TestException(String.format("Ongoing replayable program not found in the home swimlane"));
	}

	@Then("^User validates non-replayable live program from Swimlane$")
	public void user_validates_non_replayable_program_from_swimlane() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		commonUtils.waitTillVisibility(swimlane_container, 20);
		// Swipe over the home screen to find swimlane with live icons
		int counter = 0;
		String temp = null;
		boolean ongoingProgramFound = false;
		boolean replayable = false;
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1)
						.findElement(program_title_under_swimlane).getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}

			for (MobileElement swimlane : swimlaneList) {
				try {
					if (swimlane.findElement(live_icon_in_swimlane).isDisplayed()) {
						System.out.println("Nullll");
					}
				} catch (Exception e) {
					System.out.println("Stale element  : Swimlane not available");
					continue;
				}

				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					ongoingProgramFound = next_live_program_in_swimlane(swimlane, replayable);
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
			System.out.println("Non-replayable ongoing program not found in the home screen swimlane");
//			throw new TestException(
//					String.format("Non-replayable ongoing program not found in the home screen swimlane"));
	}

	public boolean program_in_future(MobileElement swimlaneProgram) throws ParseException {
		String subtitle = null;
		try {
			subtitle = swimlaneProgram.findElement(program_subtitle_under_swimlane).getText();
		} catch (Exception e) {
			return false;
		}
		// Verifying if the airing is upcoming
		if (!subtitle.contains("|")) {
			return false;
		}
		if (subtitle.contains("Yesterday")) {
			return false;
		} else if (subtitle.contains("Tomorrow")) {
			return true;
		} else if (subtitle.contains("Today")) {
			if (commonUtils.if_time_is_future(subtitle.split("\\|")[1].split("-")[1].trim())) {
				return true;
			}
		} else {
			if (commonUtils.verify_if_date_is_upcoming_date(subtitle.split("\\|")[0].trim())) {
				return true;
			}
		}
		return false;

	}

	public boolean program_completed(MobileElement swimlaneProgram) throws ParseException {
		String subtitle = null;
		try {
			subtitle = swimlaneProgram.findElement(program_subtitle_under_swimlane).getText();
		} catch (Exception e) {
			return false;
		}
		// Verifying if the airing is already completed
		if (!subtitle.contains("|")) {
			return false;
		}
		if (subtitle.contains("Tomorrow")) {
			return false;
		} else if (subtitle.contains("Yesterday")) {
			return true;
		} else if (subtitle.contains("Today")) {
			if (commonUtils.if_time_is_past(subtitle.split("\\|")[1].split("-")[1].trim())) {
				return true;
			}
		} else {
			if (commonUtils.verify_if_date_is_past_date(subtitle.split("\\|")[0].trim())) {
				return true;
			}
		}
		return false;

	}

	public boolean program_in_swimlane(MobileElement swimlaneItem, boolean past) throws ParseException, Throwable {
		int counter2 = 0;
		String lastswimlaneProgram = null;
		boolean swimlaneFound = false;
		boolean replayProgramFound = false;
		String temp2 = null;
		while (counter2 < 3) {
			Thread.sleep(20000);
			List<MobileElement> swimlaneProgramList = swimlaneItem.findElements(program_in_swimlane);

			if (swimlaneItem.findElements(program_subtitle_under_swimlane).size() < 1)
				break;
			if (swimlaneItem.findElements(program_title_under_swimlane).size() > 0) {
				try {
					lastswimlaneProgram = swimlaneProgramList.get(swimlaneProgramList.size() - 1)
							.findElement(program_title_under_swimlane).getText();
				} catch (Exception e) {
					lastswimlaneProgram = swimlaneProgramList.get(swimlaneProgramList.size() - 2)
							.findElement(program_title_under_swimlane).getText();
				}
				for (MobileElement swimlaneProgram : swimlaneProgramList) {
					swimlaneProgramList = swimlaneItem.findElements(program_in_swimlane);
					try {
						if (swimlaneProgram.findElements(program_subtitle_under_swimlane).size() > 0) {
							if (past) {
								replayProgramFound = program_completed(swimlaneProgram);
							} else {
								replayProgramFound = program_in_future(swimlaneProgram);
							}
							if (replayProgramFound) {
								swimlaneFound = replayable_program_from_home(swimlaneProgram);
							}
						}
					} catch (Exception e) {
						System.out.println("Catch stale elemnt");
						continue;
					}
					if (swimlaneFound)
						break;
				}
			}
			if (swimlaneFound)
				break;
			if (lastswimlaneProgram.equals(temp2))
				counter2++;
			temp2 = lastswimlaneProgram;
			commonUtils.swipeLeftToSeeNextProgramInSwimlane(swimlaneProgramList.get(swimlaneProgramList.size() - 1));
		}
		return swimlaneFound;
	}

	@Then("^User validates replayable program from past on Swimlane$")
	public void user_validates_replayable_program_from_past_on_swimlane() throws Throwable {
		int counter = 0;
		int counterdown = 0;
		String temp = null;
		boolean pastProgramFound = false;
		String lastSwimlaneProgramTitle = null;
		while (counterdown < 5) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			try {
				if (swimlaneList.get(1).findElement(swimlane_title).getText().equalsIgnoreCase("Now on TV"))
					break;
			} catch (Exception e) {
				System.out.println("catch ");
				if (swimlaneList.get(0).findElement(swimlane_title).getText().equalsIgnoreCase("Now on TV"))
					break;
			}
			commonUtils.swipeDownScreen();
			counterdown++;
		}
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title)
						.getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				swimlaneList = commonUtils.findElements(swimlane_tiles);
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					continue;
				}
				if (swimlane.findElements(swimlane_title).size() < 1) {
					continue;

				}
				if (swimlane.findElement(swimlane_title).getText().equalsIgnoreCase("Now on TV")) {
					continue;
				}
				pastProgramFound = program_in_swimlane(swimlane, true);
				if (pastProgramFound)
					break;
			}
			if (pastProgramFound)
				break;
			if (lastSwimlaneProgramTitle.equalsIgnoreCase(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
		if (!pastProgramFound)
			throw new TestException(String.format("Past replayable program not found in the home swimlane"));

	}

	@Then("^User validates upcoming replayable program on Swimlane$")
	public void user_validates_upcoming_replayable_program_from_swimlane() throws Throwable {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean upcomingProgramFound = false;
		String lastSwimlaneProgramTitle = null;
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_tiles);

			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1).findElement(swimlane_title)
						.getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2).findElement(swimlane_title)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0) {
					continue;
				}
				if (swimlane.findElements(swimlane_title).size() < 1) {
					continue;

				}
				if (swimlane.findElement(swimlane_title).getText().equalsIgnoreCase("Now on TV")) {
					continue;
				}
				upcomingProgramFound = program_in_swimlane(swimlane, false);
				if (upcomingProgramFound)
					break;
			}
			if (upcomingProgramFound)
				break;
			if (lastSwimlaneProgramTitle.equalsIgnoreCase(temp))
				counter++;
			temp = lastSwimlaneProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
		if (!upcomingProgramFound)
			throw new TestException(String.format("Upcoming replayable program not found in the home swimlane"));
	}

	@Given("^User selects unlocked program from home screen$")
	public void user_selects_unlocked_program_from_home() {
		int counter = 0;
		String temp = null;
		boolean unlockedProgramFound = false;
		String lastProgram = null;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		try {
			commonUtils.waitTillVisibility(swimlane_container, 20);
		} catch (Exception e) {
			System.out.println("Swimlane is not fully visible");
			System.out.println("Check if it is loading issue or is device a Tablet");
			commonUtils.swipeUpOverHomeScreen();
			commonUtils.waitTillVisibility(swimlane_container, 20);
		}
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			System.out.println("lastProgram " + lastProgram);
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(program_title_under_swimlane).size() < 1)
					break;
				int counter2 = 0;
				String temp2 = null;
				String lastProgramTitle = null;
				boolean switchToNextSwimlane = false;
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					try {
						lastProgramTitle = programList.get(programList.size() - 1)
								.findElement(program_title_under_swimlane).getText();
					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2)
								.findElement(program_title_under_swimlane).getText();
					}
					for (MobileElement program : programList) {
						try {
							program.findElement(svod_icon_age);
						} catch (Exception e) {
							continue;
						}
						program.findElement(program_title_under_swimlane).click();
						if (commonUtils.displayed(parental_pin_input))
							throw new TestException(
									String.format("Program locked even after disabling parental control."));
						Assert.assertTrue(programDetalsPage.user_on_program_details_page());
						unlockedProgramFound = true;
						commonUtils.clickonback();
						System.out.println("Program found in Home screen");
						break;
					}

					if (switchToNextSwimlane || unlockedProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(programList.size() - 1));

				}
				if (unlockedProgramFound)
					break;
			}
			if (unlockedProgramFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverScreen();
		}
	}

	@Given("^User selects program without age rating from home screen$")
	public void user_selects_program_without_age_rating_from_home() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean unlockedProgramFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(program_title_under_swimlane).size() < 1)
					break;
				int counter2 = 0;
				String temp2 = null;
				String lastProgramTitle = null;
				boolean switchToNextSwimlane = false;
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					try {
						lastProgramTitle = programList.get(programList.size() - 1)
								.findElement(program_title_under_swimlane).getText();
					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2)
								.findElement(program_title_under_swimlane).getText();
					}
					for (MobileElement program : programList) {
						if ((program.findElements(svod_icon_age).size() > 0)
								|| (program.findElements(svod_icon_locked).size() > 0))
							continue;
						program.findElement(program_title_under_swimlane).click();
						if (commonUtils.displayed(parental_pin_input))
							throw new TestException(
									String.format("Program without age rating is locked in the home screen"));
						Assert.assertTrue(programDetalsPage.user_on_program_details_page());
						unlockedProgramFound = true;
						commonUtils.clickonback();
						break;
					}

					if (switchToNextSwimlane || unlockedProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(programList.size() - 1));

				}
				if (unlockedProgramFound)
					break;
			}
			if (unlockedProgramFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverScreen();
		}
	}

	@Given("^User selects locked program from home screen$")
	public void user_selects_locked_program_from_home() {
		int counter = 0;
		String temp = null;
		boolean lockedProgramFound = false;
		String lastProgram = null;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		try {
			commonUtils.waitTillVisibility(swimlane_container, 20);
		} catch (Exception e) {
			System.out.println("Swimlane is not fully visible");
			System.out.println("Check if it is loading issue or is device a Tablet");
			commonUtils.swipeUpOverHomeScreen();
			commonUtils.waitTillVisibility(swimlane_container, 20);
		}
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(program_title_under_swimlane).size() < 1)
					break;
				int counter2 = 0;
				String temp2 = null;
				String lastProgramTitle = null;
				boolean switchToNextSwimlane = false;
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					try {
						lastProgramTitle = programList.get(programList.size() - 1)
								.findElement(program_title_under_swimlane).getText();
					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2)
								.findElement(program_title_under_swimlane).getText();
					}
					for (MobileElement program : programList) {
						try {
							program.findElement(svod_icon_age);

							System.out.println(program.findElement(program_title_under_swimlane).getText());
							program.findElement(program_title_under_swimlane).click();
						} catch (Exception e) {
							continue;
						}
						if (!commonUtils.displayed(parental_pin_cancel_button))
							throw new TestException(String.format(
									"Program is not locked even after enabling parental control - Home screen"));
						commonUtils.clickonElement(parental_pin_cancel_button);
						commonUtils.waitTillVisibility(program_in_swimlane,30);
						programList = swimlane.findElements(program_in_swimlane);
						program.findElement(program_title_under_swimlane).click();
						if (!commonUtils.displayed(parental_pin_input))
							throw new TestException(
									String.format("Program is unlocked without entering pin-Home screen"));
//						commonUtils.clickonElement(parental_pin_cancel_button);
						lockedProgramFound = true;
						System.out.println("Program found in Home screen");
						break;
					}

					if (switchToNextSwimlane || lockedProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(programList.size() - 1));
				}
				if (lockedProgramFound)
					break;
			}
			if (lockedProgramFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverScreen();
		}
	}

	@And("^User unlocks program temporarily from home screen$")
	public void user_unlocks_program_from_home() {
		user_selects_locked_program_from_home();
		settingPage.enter_parental_pin_for_programs();
		try {
			commonUtils.waitTillInvisibility(lock_icon, 20);
		} catch (Exception e) {
			System.out.println("Program is locked even after entering parental pin");
		}
		commonUtils.clickonElement(liveTV_button);
		commonUtils.clickonElement(home_button);
		try {
			commonUtils.waitTillInvisibility(lock_icon, 20);
		} catch (Exception e) {
			throw new TestException(String.format("Program is locked even after entering parental pin"));
		}
	}

	@Given("^User validates unlocked program from home screen,TV Guide and Live TV$")
	public void user_selects_unlocked_program_from_home_EPG_Live() throws Throwable {
		user_selects_unlocked_program_from_home();
		tvGuidePage.user_is_on_tv_guide_page();
		tvGuidePage.user_selects_unlocked_program_from_TVGuide();
		liveTvPage.user_navigates_to_live_tv();
		liveTvPage.user_selects_unlocked_program_from_LiveTV();
	}

	@Given("^User validates locked program from home screen,TV Guide and Live TV$")
	public void user_selects_locked_program_from_home_EPG_Live() throws Throwable {
		user_selects_locked_program_from_home();
		commonUtils.clickonElement(parental_pin_cancel_button);
		tvGuidePage.user_is_on_tv_guide_page();
		tvGuidePage.user_selects_locked_program_from_TVGuide();
		commonUtils.clickonElement(liveTV_button);
		commonUtils.waitTillVisibility(live_tv_channel_icon, 60);
		liveTvPage.user_selects_locked_program_and_rejects_parental_pin();
	}

	@And("^User waits ten_minutes for temporary unlock$")
	public void user_waits_for_ten_minutes() throws Throwable {
		commonUtils.clickonElement(parental_pin_cancel_button);
		for (int i = 0; i < 5; i++) {
			liveTvPage.user_navigates_to_live_tv();
			Thread.sleep(60000);
			tvGuidePage.user_is_on_tv_guide_page();
			Thread.sleep(60000);
		}
//		commonUtils.clickonElement(liveTV_button);
		commonUtils.clickonElement(home_button);
	}

	@And("^User waits for programs to lock after temporary unlock$")
	public void user_waits_for_programs_to_lock_after_temporary_unlock() throws Throwable {
		for (int i = 0; i <= 6; i++) {
			try {
				commonUtils.waitTillVisibilityByMin(lock_icon, 1);
			} catch (Exception e) {
				System.out.println("Catch");
				Thread.sleep(60000);
			}
		}
//		commonUtils.clickonElement(liveTV_button);
//		commonUtils.clickonElement(tvGuide_button);
	}

	@And("^User verify if the program is unlocked$")
	public void user_verify_if_program_is_locked() {
		user_unlocks_program_from_home();
		if (!commonUtils.findElements(svod_icon_age).isEmpty())
			commonUtils.findElement(svod_icon_age).click();
		else {
			user_selects_unlocked_program_from_home();
			commonUtils.findElement(svod_icon_age).click();
		}
		Assert.assertTrue(programDetalsPage.user_on_program_details_page());
	}

	@And("^User locks age restricted programs from settings$")
	public void user_locks_program_from_settings() {
		settingPage.the_user_is_on_settings_page();
		settingPage.user_login_to_parental_control();
		settingPage.user_turn_off_parental_control();
		settingPage.user_turn_on_parental_control();
		settingPage.user_open_age_restriction_option();
		settingPage.user_select_the_age_restriction_as_ten();
	}

	@Then("^User validate age rated VOD asset from home screen$")
	public void user_validate_age_rated_VOD() throws Throwable {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean lockedVODFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(program_title_under_swimlane).size() < 1)
					break;
				if (swimlane.findElements(program_subtitle_under_swimlane).size() < 1)
					break;
				if (swimlane.findElements(live_icon_in_swimlane).size() > 0)
					continue;
				if (swimlane.findElement(program_subtitle_under_swimlane).getText().contains("|"))
					continue;
				int counter2 = 0;
				String temp2 = null;
				String lastProgramTitle = null;
				boolean switchToNextSwimlane = false;
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					try {
						lastProgramTitle = programList.get(programList.size() - 1)
								.findElement(program_title_under_swimlane).getText();
					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2)
								.findElement(program_title_under_swimlane).getText();
					}
					System.out.println("lastProgramTitle  " + lastProgramTitle);
					for (MobileElement program : programList) {
						try {
							program.findElement(svod_icon_age);
						} catch (Exception e) {
							continue;
						}
						System.out.println(program.findElement(program_title_under_swimlane).getText());
						if (swimlane.findElement(program_subtitle_under_swimlane).getText().contains("|"))
							continue;
						program.findElement(program_title_under_swimlane).click();
//							settingPage.enter_parental_pin_for_programs();
//							try {
//								commonUtils.waitTillInvisibility(lock_icon, 20);
//								program.findElement(program_title_under_swimlane).click();
//							} catch (Exception e) {
//								System.out.println("Program is locked even after entering parental pin");
//								continue;
//							}
						lockedVODFound = true;
						System.out.println("Program found in Home screen");
						break;
					}
					if (switchToNextSwimlane || lockedVODFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(programList.size() - 1));
				}
				if (lockedVODFound)
					break;
			}
			if (lockedVODFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!lockedVODFound)
			throw new TestException(String.format("Age rated VOD not found in Home screen"));
	}

	@And("^User validate VOD asset in CW swimlane$")
	public void user_validate_VOD_asset_in_CW() throws Throwable {
		String title = commonUtils.findElement(vod_program_title).getText();
		System.out.println("Title " + title);
		Thread.sleep(30000);
		programDetalsPage.close_program_details_page_to_reach_homescreen();
		int counter = 0;
		boolean found = false;
		List<MobileElement> swimlaneList = null;
		while (counter < 5) {
			swimlaneList = commonUtils.findElements(swimlane_tiles);
			try {
				System.out.println(swimlaneList.get(0).findElement(swimlane_title).getText());
				if (swimlaneList.get(0).findElement(swimlane_title).getText().equalsIgnoreCase("Continue watching"))
					if (swimlaneList.get(0).findElement(program_title_under_swimlane).getText()
							.equalsIgnoreCase(title)) {
						found = true;
						break;
					}
			} catch (Exception e) {
				System.out.println("catch ");
				if (swimlaneList.get(1).findElement(swimlane_title).getText().equalsIgnoreCase("Continue watching"))
					if (swimlaneList.get(1).findElement(program_title_under_swimlane).getText()
							.equalsIgnoreCase(title)) {
						found = true;
						break;
					}
			}
			commonUtils.swipeDownScreen();
			counter++;

		}
		if (!found) {
			throw new TestException(String.format("Age rated VOD not found in CW swimlane"));
		}
	}

	@And("^User validates locked VOD asset in CW swimlane$")
	public void user_validates_locked_VOD_asset_in_CW() throws Throwable {
		Thread.sleep(20000);
		List<MobileElement> swimlaneList = null;
		boolean vodFound = false;
		int counter = 0;
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		swimlaneList = commonUtils.findElements(swimlane_tiles);
		while (counter < 3) {
			swimlaneList = commonUtils.findElements(swimlane_tiles);
			for (MobileElement swimlane : swimlaneList) {
				try {
					System.out.println(swimlane.findElement(swimlane_title).getText());
					swimlane.findElement(swimlane_title).getText().equalsIgnoreCase("Continue watching");
					swimlane.findElement(svod_icon_age).click();
				}
				catch(Exception e) {
					System.out.println("Catch");
					continue;
				}
				if (swimlane.findElement(swimlane_title).getText().equalsIgnoreCase("Continue watching")) {
					swimlane.findElement(svod_icon_age).click();
					vodFound = true;
					break;
				}
			}
			counter++;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!vodFound)
			throw new TestException(String.format("Program is not found in continue watching swimlane"));
	}
	
	@And("^User validates and starts recording age rated program from home screen$")
	public void user_starts_recording_age_rates_program_from_home() {
		commonUtils.waitTillVisibility(swimlane_container, 20);
		int counter = 0;
		String temp = null;
		boolean lockedProgramFound = false;
		String lastProgram = null;
		commonUtils.waitTillVisibility(swimlane_program_poster, 30);
		if (commonUtils.displayed(hero_banner_container))
			commonUtils.swipeUpOverHomeScreen();
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(swimlane_container_without_title);
			try {
				lastProgram = swimlaneList.get(swimlaneList.size() - 1).findElement(program_title_under_swimlane)
						.getText();
			} catch (Exception e) {
				lastProgram = swimlaneList.get(swimlaneList.size() - 2).findElement(program_title_under_swimlane)
						.getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(program_title_under_swimlane).size() < 1)
					break;
				int counter2 = 0;
				String temp2 = null;
				String lastProgramTitle = null;
				boolean switchToNextSwimlane = false;
				while (counter2 < 3) {
					List<MobileElement> programList = swimlane.findElements(program_in_swimlane);
					try {
						lastProgramTitle = programList.get(programList.size() - 1)
								.findElement(program_title_under_swimlane).getText();
					} catch (Exception e) {
						lastProgramTitle = programList.get(programList.size() - 2)
								.findElement(program_title_under_swimlane).getText();
					}
					for (MobileElement program : programList) {
						try {
							program.findElement(svod_icon_age);
						} catch (Exception e) {
							continue;
						}
						if (!swimlane.findElement(program_subtitle_under_swimlane).getText().contains("|"))
							continue;
						System.out.println(program.findElement(program_title_under_swimlane).getText());
						program.findElement(program_title_under_swimlane).click();
						if (!commonUtils.displayed(parental_pin_cancel_button))
							throw new TestException(String.format(
									"Program is not locked even after enabling parental control - Home screen"));
						settingPage.enter_parental_pin_for_programs();
						try {
							commonUtils.waitTillInvisibility(lock_icon, 20);
						} catch (Exception e) {
							System.out.println("Program is locked even after entering parental pin");
						}
						program.findElement(program_title_under_swimlane).click();
						commonUtils.findElement(record_button).click();
						programDetalsPage.close_program_details_page_to_reach_homescreen();
						lockedProgramFound = true;
						System.out.println("Program found in Home screen");
						break;
					}

					if (switchToNextSwimlane || lockedProgramFound)
						break;
					if (lastProgramTitle.equals(temp2))
						counter2++;
					temp2 = lastProgramTitle;
					commonUtils.swipeLeftToSeeNextProgramInSwimlane(programList.get(programList.size() - 1));
				}
				if (lockedProgramFound)
					break;
			}
			if (lockedProgramFound)
				break;
			if (lastProgram.equals(temp))
				counter++;
			temp = lastProgram;
			commonUtils.swipeUpOverScreen();
		}
	}
	
//	@And("^User stream recorded program$")
//	public void user_unlocks_program_from_home() {
//		user_selects_locked_program_from_home();
//		settingPage.enter_parental_pin_for_programs();
//		try {
//			commonUtils.waitTillInvisibility(lock_icon, 20);
//		} catch (Exception e) {
//			System.out.println("Program is locked even after entering parental pin");
//		}
//		commonUtils.clickonElement(liveTV_button);
//		commonUtils.clickonElement(home_button);
//	}
}
