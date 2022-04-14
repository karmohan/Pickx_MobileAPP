package Android_stepdefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.TestException;

import Android_screens.LiveTV_Screen;
import Android_screens.Setting_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class TVguide_Page implements Android_screens.Home_Screen, Android_screens.TVguide_Screen,
		Android_screens.Program_Details_Screen, LiveTV_Screen, Setting_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Android_input_properties inputProperties;
	public List<String> displayed_dates;
	public Setting_Page settingPage;
	public Program_Details_Page detailsPage;
	AndroidDriver<MobileElement> androidDriver;
	public String Bottom_navigation_TVguide_text;

	public TVguide_Page() throws IOException {
		driver = Hooks.getDriver();
		this.androidDriver=(AndroidDriver<MobileElement>) driver;
		commonUtils = new CommonUtils(driver);
		inputProperties = new Android_input_properties();
		settingPage = new Setting_Page();
		detailsPage = new Program_Details_Page();
	}

	@Given("^User is on the TV guide page$")
	public void user_is_on_tv_guide_page() {
		if(commonUtils.displayed(parental_pin_cancel_button)) {
			commonUtils.clickonElement(parental_pin_cancel_button);
		}
		Bottom_navigation_TVguide_text = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_TVguide");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(Bottom_navigation_TVguide_text));
		//commonUtils.clickonElement(tvGuide_button);
		commonUtils.waitTillVisibility(tv_guide_program_poster, 30);
		try {
			commonUtils.displayed(tv_guide_program_poster);
			Assert.assertTrue(commonUtils.displayed(tv_guide_program_poster));
		} catch (Exception e) {
			System.out.println("epg_video_title catch");
			commonUtils.waitTillVisibility(epg_video_title, 30);
			Assert.assertTrue(commonUtils.displayed(epg_video_title));
		}
	}

	@When("^The user scroll body horizontally$")
	public void user_scroll_boday_horizontally() {
		String beforeScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("Before horizontal scroll the vidoe title is :" + beforeScroll_titile);
		commonUtils.scrollHorizantal();
		String afterScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("After horizontal scroll the vidoe title is :" + afterScroll_titile);
		Assert.assertNotEquals(beforeScroll_titile, afterScroll_titile);
	}

	@Then("^User scroll the channel vertically$")
	public void user_scroll_channel_vertically() {
		String beforeScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("Before vertical scroll the vidoe title is :" + beforeScroll_titile);
		commonUtils.swipeUpOverScreen();
		String afterScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("After vertical scroll the vidoe title is :" + afterScroll_titile);
		Assert.assertNotEquals(beforeScroll_titile, afterScroll_titile);
	}

	@Given("^The user clicks on the date picker$")
	public void the_user_clicks_on_the_date_picker() {
		commonUtils.clickonElement(date_picker);
		commonUtils.waitTillVisibility(date_picker_textview_date, 20);
		Assert.assertTrue(commonUtils.displayed(date_picker_textview_date));
	}

	@Given("^The user see locked tiles$")
	public void the_user_see_locked_tiles() {
		commonUtils.waitTillVisibility(epg_video_container, 20);
		// Swipe over the home screen to find swimlane with live icons
		int counter = 0;
		String temp = null;
		boolean ongoingProgramFound = false;
		commonUtils.waitTillVisibility(epg_video_imageview_airing, 30);
		if (commonUtils.displayed(epg_live_now_icon))
			commonUtils.swipeUpOverHomeScreen();
		// To verify if the user has swiped till the last swimlane the counter value is
		// provided. For verifying if the last swimlane has reached, after each swipe
		// the program title of the last swimlane is compared with that of the previous
		// swipe. And if the titles are same the counter value is incremented. Since
		// there are swimlanes with same title, this comparison is repeated till counter
		// value reaches 3
		while (counter < 3) {
			List<MobileElement> swimlaneList = commonUtils.findElements(epg_video_imageview_airing);
			String lastSwimlaneProgramTitle;
			try {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 1).findElement(epg_video_title)
						.getText();
			} catch (Exception e) {
				lastSwimlaneProgramTitle = swimlaneList.get(swimlaneList.size() - 2)
						.findElement(program_title_under_swimlane).getText();
			}
			for (MobileElement swimlane : swimlaneList) {
				if (swimlane.findElements(age_icon).size() > 0) {
					swimlane.findElement(age_icon).click();
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
		Assert.assertTrue(detailsPage.user_on_program_details_page());

	}

	@When("^The user unlocks the tiles by entering a pin$")
	public void the_user_unlocks_the_tiles_by_entering_a_pin() {
	}

	@When("^The user validates the date shown in the date picker$")
	public void the_user_validates_the_date_shown_in_the_date_picker() {
		displayed_dates = new ArrayList<String>();
		int counter = 0;
		while (counter < 2) {
			List<MobileElement> previous_day = commonUtils.findElements(date_picker_textview_date);
			for (MobileElement day_value : previous_day) {
				String value = day_value.getText();
				if (value.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Today"))) {
					commonUtils.swipeUpOverScreen();
				}
				displayed_dates.add(value);
			}
			counter++;
		}
		System.out.println("List of previous week value :" + displayed_dates);
	}

	@Then("^The user check previous and the future day is shown in the date picker$")
	public void the_user_check_previous_and_the_future_day_is_shown_in_the_date_picker() {
		Assert.assertTrue(displayed_dates.contains(commonUtils.previousDay_picker("sixthday")));
		Assert.assertTrue(displayed_dates.contains(commonUtils.futureDay_picker("sixthday")));
	/*	if (displayed_dates.contains(commonUtils.previousDay_picker("sixthday"))) {
			System.out.println(commonUtils.previousDay_picker("sixthday"));
		}

		if (displayed_dates.contains(commonUtils.futureDay_picker("seventhday"))) {
			System.out.println(commonUtils.futureDay_picker("seventhday"));
		}*/
	}

	@When("^The user validates the program for different dates$")
	public void the_user_validates_the_program_for_different_dates() {
		user_select_the_previous_day_program();
		user_select_today_program();
		user_select_future_day_program();
	}

	@And("^User select the previous day program$")
	public void user_select_the_previous_day_program() {
		String text = commonUtils.previousDay_picker("thirdday");
		driver.findElement(By.xpath("//*[contains(@text,'" + text + "')]")).click();
		Assert.assertTrue(commonUtils.displayed(epg_video_container));
		user_check_for_displayed_program_details();
		commonUtils.waitTillVisibility(epg_video_airing_details, 20);
		Assert.assertTrue(commonUtils.displayed(epg_video_airing_details));
		// Code to check replay icon//
//    	if(!commonUtils.displayed(epg_video_replay_icon)) {
//    		commonUtils.clickonElement(epg_video_imageview_airing);
//    		commonUtils.clickonElement(navigate_back);
//    		commonUtils.displayed(epg_video_replay_icon);
//    	}
//    	else {
//    		commonUtils.displayed(epg_video_replay_icon);
//    	}
	}

	@And("^User check for displayed program details$")
	public void user_check_for_displayed_program_details() {
		try {
		commonUtils.waitTillVisibility(epg_program_tiles, 30);
		}
		catch(Exception e){
			System.out.println("Stale element wait");
		}
		List<MobileElement> programFullList = commonUtils.findElements(epg_program_tiles);
//		ArrayList<MobileElement> programList= new ArrayList<MobileElement>();
		if(programFullList.size()<0) {
			System.out.println("Size 0");
			commonUtils.waitTillVisibility(epg_program_tiles, 30);
			programFullList = commonUtils.findElements(epg_program_tiles);
		}
		for (int i = 0; i < programFullList.size(); i++) {
			programFullList = commonUtils.findElements(epg_program_tiles);
			try {
				if (programFullList.get(i).isDisplayed()) {
					if (!commonUtils.findElements(epg_video_broadcast_time).get(i).isDisplayed()) {
						System.out.println("Not displayed");
						continue;
					}
					if (!commonUtils.findElements(epg_video_title).get(i).getText()
							.equalsIgnoreCase("Locked content")) {
						Assert.assertTrue(commonUtils.findElements(epg_video_imageview_airing).get(i).isDisplayed());
						System.out.println("Not lcoked ");
					}
					System.out.println("EPG airing");
					Assert.assertTrue(commonUtils.findElements(epg_video_title).get(i).isDisplayed());
					Assert.assertTrue(commonUtils.findElements(epg_video_broadcast_time).get(i).isDisplayed());
					break;
				}
			} catch (Exception e) {
				System.out.println("Catch");
				continue;
			}
		}

//		Assert.assertTrue(commonUtils.displayed(epg_video_imageview_airing));
//		System.out.println("epg_video_title ");
//		Assert.assertTrue(commonUtils.displayed(epg_video_title));
//		Assert.assertTrue(commonUtils.displayed(epg_video_broadcast_time));
//    	if(commonUtils.displayed(epg_date_picker_today)) {
//    		Assert.assertTrue(commonUtils.displayed(epg_live_now_icon));
//    	}
//    	else {
//    		Assert.assertTrue(commonUtils.displayed(epg_video_airing_details));
//    	}

	}

	@And("^User select today program$")
	public void user_select_today_program() {
		the_user_clicks_on_the_date_picker();
		commonUtils.clickonElement(commonUtils.findElementByXpathContains("text", commonUtils.getTextBasedonLanguage_Android("today_text_for_Xpath")));
		//commonUtils.clickonElement(epg_date_picker_today);
		user_check_for_displayed_program_details();
		Assert.assertTrue(commonUtils.displayed(epg_live_now_icon));
		commonUtils.clickonElement(epg_live_now_icon);
		if (commonUtils.displayed(parental_pin_input)) {
			settingPage.enter_parental_pin_for_programs();
			commonUtils.waitTillInvisibility(lock_icon, 20);
			commonUtils.clickonElement(epg_live_now_icon);
		}
		Assert.assertTrue(commonUtils.displayed(epg_live_video_container));
		commonUtils.clickonElement(epg_live_play_pause_icon);
		Assert.assertTrue(commonUtils.displayed(epg_live_mute_button));
		Assert.assertTrue(commonUtils.displayed(epg_live_rewind_button));
		commonUtils.clickonback();
//		commonUtils.clickonElement(navigate_back);
	}

	@And("^User select future day program$")
	public void user_select_future_day_program() {
		the_user_clicks_on_the_date_picker();
		commonUtils.swipeUpOverScreen();
		String text = commonUtils.futureDay_picker("thirdday");
		driver.findElement(By.xpath("//*[contains(@text,'" + text + "')]")).click();
		Assert.assertTrue(commonUtils.displayed(epg_video_container));
		user_check_for_displayed_program_details();
		Assert.assertTrue(commonUtils.displayed(epg_video_airing_details));
	}

	@Given("^User selects non-series live program from TV guide$")
	public void user_selects_non_series_live_program_from_tv_guide() throws Throwable {
		boolean nonSeriesProgram = false;
		commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
		int channelsTab = commonUtils.findElements(epg_video_container).size();
		if (channelsTab < 1) {
			throw new TestException(String.format("Ongoing program not found in the TV Guide"));
		}
		// Tablet live programs are selected
		if (channelsTab > 1) {
			for (int i = 0; i < 20; i++) {
				System.out.println("channelsTab   " + channelsTab);
				for (int j = 0; j < channelsTab; j++) {
					Thread.sleep(10000);
					commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
					channelsTab = commonUtils.findElements(epg_video_container).size();
					select_ongoing_program_from_tv_guide(j);
					if (commonUtils.displayed(ongoing_program_record_button)) {
						if (!detailsPage.verify_if_ongoing_program_is_part_of_series()) {
							nonSeriesProgram = true;
							break;
						}
					}
					detailsPage.close_program_details_page_to_reach_tvguide();
				}
				if (nonSeriesProgram) {
					break;
				}
				commonUtils.swipeRightChannel();
			}
			// Android phone live program is selected
		} else {
			for (int i = 0; i < 50; i++) {
				select_ongoing_program_from_tv_guide(0);
				if (commonUtils.displayed(ongoing_program_record_button)) {
					if (!detailsPage.verify_if_ongoing_program_is_part_of_series()) {
						nonSeriesProgram = true;
						break;
					}
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
				select_next_channel_from_tv_guide();
			}
		}
		if (!nonSeriesProgram)
			throw new TestException(String.format("Live non-series program not found in the EPG"));
		}

	@And("^User selects ongoing program from TV guide$")
	public boolean select_ongoing_program_from_tv_guide(int channelLiveProgram) {
		commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
		if (!commonUtils.displayed(epg_live_now_icon)) {
			throw new TestException(String.format("Live program not found in the EPG"));
		}
		commonUtils.findElements(epg_live_now_icon).get(channelLiveProgram).click();
		if (commonUtils.displayed(parental_pin_input)) {
			settingPage.enter_parental_pin_for_programs();
			commonUtils.waitTillInvisibility(lock_icon, 20);
//			commonUtils.clickonElement(parental_pin_cancel_button);
			System.out.println("Locked");
			return false;
		}
		try {
			commonUtils.waitTillVisibility(player_screen, 30);
			Assert.assertTrue(commonUtils.displayed(player_screen));
		} catch (Exception e) {
			System.out.println("Tablet");
			if (commonUtils.displayed(player_screen_swimlane_Tab))
				commonUtils.clickonback();
			Assert.assertTrue(commonUtils.displayed(player_screen_swimlane_Tab));
		}
		return true;
	}

	public void select_next_channel_from_tv_guide() {
		List<MobileElement> channelList = commonUtils.findElements(channel_cell);
		/*
		 * For Android selecting each channel by clicking on the channel tab And for
		 * Tablet swiping right since channel tab is not specified
		 */
		if (channelList.isEmpty()) {
			System.out.println("Tablet ");
			commonUtils.swipeRightChannel();
		} else {
			Assert.assertFalse(channelList.isEmpty());
			for (int i = 1; i < channelList.size() - 1; i++) {
				if (channelList.get(i).findElements(channel_selected_bar).size() > 0) {
					channelList.get(i + 1).findElement(tv_guide_channel_icon).click();
					break;
				}
			}
		}
	}

	@Then("^User selects series live program from TV guide$")
	public void user_selects_series_live_program_from_tvguide() throws Throwable {
		boolean seriesProgram = false;
		commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
		int channelsTab = commonUtils.findElements(epg_video_container).size();
		if (channelsTab < 1) {
			throw new TestException(String.format("Ongoing program not found in the TV Guide"));
		}
		// Tablet live programs are selected
		if (channelsTab > 1) {
			for (int i = 0; i < 20; i++) {
				System.out.println("channelsTab   " + channelsTab);
				for (int j = 0; j < channelsTab; j++) {
					Thread.sleep(5000);
					commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
					channelsTab = commonUtils.findElements(epg_video_container).size();
					select_ongoing_program_from_tv_guide(j);
					if (commonUtils.displayed(ongoing_program_record_button)) {
						if (detailsPage.verify_if_ongoing_program_is_part_of_series()) {
							seriesProgram = true;
							break;
						}
					}
					detailsPage.close_program_details_page_to_reach_tvguide();
				}
				if (seriesProgram) {
					break;
				}
				commonUtils.swipeRightChannel();
			}
			// Android phone live program is selected
		} else {
			for (int i = 0; i < 50; i++) {
				select_ongoing_program_from_tv_guide(0);
				if (commonUtils.displayed(ongoing_program_record_button)) {
					if (detailsPage.verify_if_ongoing_program_is_part_of_series()) {
						seriesProgram = true;
						break;
					}
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
				select_next_channel_from_tv_guide();
			}
		}
		if (!seriesProgram)
			throw new TestException(String.format("Live series program not found in the EPG"));

	}

	@Then("^User selects non-playable live program$")
	public void user_selects_non_playable_live_program() throws Throwable {
		boolean nonPlayableItemFound = false;
		search_channel_with_non_playable_programs();
		for (int i = 0; i < 6; i++) {
			if (!commonUtils.displayed(not_playable_icon_in_tvguide)) {
				commonUtils.swipeRightOverElement(commonUtils.findElements(epg_program_tiles).get(i));
				commonUtils.waitTillVisibility(epg_video_title, 10);
				continue;
			}
			select_ongoing_program_from_tv_guide(0);
			nonPlayableItemFound = true;
			break;
		}
		Assert.assertTrue(nonPlayableItemFound);
	}

	@When("^User selects yesterday in TV guide$")
	public void user_selects_yesterday_in_tvguide() {
		Assert.assertTrue(commonUtils.displayed(date_field));
		boolean dateSelected = false;
		if (!commonUtils.getText(date_field).equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Yesterday"))) {
			commonUtils.clickonElement(toggle_date_button);
			List<MobileElement> dateLIst = commonUtils.findElements(date_list);
			for (MobileElement date : dateLIst) {
				if (date.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Yesterday"))) {
					date.click();
					Assert.assertTrue(commonUtils.getText(date_field).equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Yesterday")));
					dateSelected = true;
					break;
				}
			}
		}
		if (!dateSelected)
			throw new TestException(String.format("Failed to select yesterday from day selector"));
	}

	// added throw sree 11/08/21
	@And("^User selects non-series replay program from TV guide$")
	public void user_selects_non_series_replay_program_from_tvguide() throws Throwable {
		boolean isLive = false;
		boolean programFound = false;
		for (int i = 0; i < 30; i++) {
			try {
				commonUtils.waitTillVisibility(epg_video_title, 30);
			} catch (Exception notDispalyed) {
				System.out.println("epg_video_title not displayed");
				commonUtils.waitTillVisibility(tv_guide_program_poster, 30);
			}
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			System.out.println("programList.size()  " + programList.size());
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
				programList = commonUtils.findElements(epg_program_tiles);
				try {
					if (!programList.get(j).isDisplayed()) {
						continue;
					}
					programList.get(j).click();
				} catch (Exception e) {
					System.out.println("Catch");
					continue;
				}
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
//					commonUtils.clickonElement(parental_pin_cancel_button);
//					continue;
				}
				if (detailsPage.is_program_replayable(isLive)) {
					if (!detailsPage.verify_if_completed_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(programFound);
	}
	
	@And("^User selects series replay program from TV guide$")
	public void user_selects_series_replay_program_from_tvguide() {
		boolean programFound = false;
		boolean isLive = false;
		for (int i = 0; i < 30; i++) {
			try {
				commonUtils.waitTillVisibility(epg_video_title, 30);
			} catch (Exception notDispalyed) {
				System.out.println("epg_video_title not displayed");
			}
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			System.out.println("programList.size()  " + programList.size());
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(tv_guide_program_poster, 30);
				programList = commonUtils.findElements(epg_program_tiles);
				try {
					if (!programList.get(j).isDisplayed()) {
						continue;
					}
					programList.get(j).click();
				} catch (Exception e) {
					System.out.println("Catch");
					continue;
				}
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
				}
				if (detailsPage.is_program_replayable(isLive)) {
					if (detailsPage.verify_if_completed_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(programFound);
	}
	
	@When("^User selects tomorrow in TV guide$")
        public void user_selects_tomorrow_in_tvguide() {
    		Assert.assertTrue(commonUtils.displayed(date_field));
    		boolean dateSelected = false;
    		if (!commonUtils.getText(date_field).equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Tomorrow"))) {
    		    commonUtils.clickonElement(toggle_date_button);
    		    List<MobileElement> dateLIst = commonUtils.findElements(date_list);
    		    for (MobileElement date : dateLIst) {
    			if (date.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Tomorrow"))) {
    			    date.click();
    			    Assert.assertTrue(commonUtils.getText(date_field).equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("Tomorrow")));
    			    dateSelected = true;
    			    break;
    			}
    		    }
    		}
    		if (!dateSelected)
    		    throw new TestException(String.format("Failed to select tomorrow from day selector"));
        }
        
	@And("^User selects non-series upcoming program from TV guide$")
	public void user_selects_non_series_upcoming_program_from_tvguide() {
		boolean programFound = false;
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			for (int j = 0; j < programList.size(); j++) {
				programList = commonUtils.findElements(epg_program_tiles);
				try {
					if (!programList.get(j).isDisplayed()) {
						continue;
					}
					programList.get(j).click();
				} catch (Exception e) {
					System.out.println("Catch");
					continue;
				}
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
//					commonUtils.clickonElement(parental_pin_cancel_button);
//					continue;
				}
				if( commonUtils.findElements(record_button_text).size()<1) {
					detailsPage.close_program_details_page_to_reach_tvguide();
					continue;
				}
				
				if (!detailsPage.verify_if_upcoming_program_is_part_of_series()) {
					programFound = true;
					break;
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(programFound);
	}

//	added throw sree 11/08
	@And("^User selects series upcoming program from TV guide$")
	public void user_selects_series_upcoming_program_from_tvguide() {
		boolean programFound = false;
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			for (int j = 0; j < programList.size(); j++) {
				programList = commonUtils.findElements(epg_program_tiles);
				try {
					if (!programList.get(j).isDisplayed()) {
						continue;
					}
					programList.get(j).click();
				} catch (Exception e) {
					System.out.println("Catch");
					continue;
				}
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
//					commonUtils.clickonElement(parental_pin_cancel_button);
//					continue;
				}
    			if (detailsPage.verify_if_upcoming_program_is_part_of_series()) {
    			    programFound = true;
    			    break;
    			}
    			detailsPage.close_program_details_page_to_reach_tvguide();
    		    }
    		    if (programFound)
    			break;
    		    select_next_channel_from_tv_guide();
    		}
    		Assert.assertTrue(programFound);
        }
        
        @And("^User selects non-playable upcoming program$")
	public void user_selects_non_playable_upcoming_program() throws Throwable {
    		boolean nonPlayableItemFound = false;
    		search_channel_with_non_playable_programs();
		Thread.sleep(30000);
		for (int count = 0; count < 8; count++) {

			if (!commonUtils.displayed(not_playable_icon_in_tvguide)) {
				commonUtils.swipeRightOverElement(commonUtils.findElements(epg_program_tiles).get(count));
				commonUtils.waitTillVisibility(epg_video_title, 30);
				continue;
			}
			commonUtils.clickonElement(not_playable_icon_in_tvguide);
			nonPlayableItemFound = true;
			break;
		}
		Assert.assertTrue(nonPlayableItemFound);
	}

	@And("^User searches for channel with non playable programs$")
	public void search_channel_with_non_playable_programs() throws Throwable {
		commonUtils.clickonElement(search_button);
		commonUtils.sendKey(search_text_field, inputProperties.getChannelWithNonPlayableItem());
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
		Thread.sleep(30000);
		commonUtils.waitTillVisibility(epg_video_title, 15);
		Assert.assertTrue(commonUtils.displayed(epg_video_title));
	}

	@Then("^User records and validate episode of live airing from EPG$")
	public void user_records_and_validate_live_episode_airing_from_EPG() throws Throwable {
		select_recordable_live_program_from_epg(true);
		Map<String, String> programDetails = detailsPage.records_series_program_and_validate_updated_details(true);
		detailsPage.close_program_details_page_to_reach_tvguide();
		MobileElement program = verify_recording_icon_present_over_program_in_epg(programDetails);
		program.click();
		if (commonUtils.displayed(parental_pin_input)) {
			settingPage.enter_parental_pin_for_programs();
			commonUtils.waitTillVisibility(epg_video_title, 10);
			program.click();
		}
		detailsPage.stop_recording_episode();
		detailsPage.delete_recording();
	}

	public void select_recordable_live_program_from_epg(boolean isSeries) throws Throwable {
		boolean programFound = false;
		for (int i = 0; i < 15; i++) {
			if (isSeries)
				user_selects_series_live_program_from_tvguide();
			else
				user_selects_non_series_live_program_from_tv_guide();
			if (detailsPage.is_program_to_be_recorded()) {
				programFound = true;
				break;
			}
			detailsPage.close_program_details_page_to_reach_tvguide();
			select_next_channel_from_tv_guide();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find a series program from live TV for recording"));
	}

	public MobileElement verify_recording_icon_present_over_program_in_epg(Map<String, String> programDetails) {
		boolean programFound = false;
		List<MobileElement> programCellList = commonUtils.findElements(epg_program_tiles);
		for (MobileElement programCell : programCellList) {
			programCellList = commonUtils.findElements(epg_program_tiles);
			String title;
			try {
				title = programCell.findElement(epg_video_title).getText();
			} catch (Exception e) {
				continue;
			}
			if (title.contains(programDetails.get("title"))) {
				programFound = true;
				if (programCell.findElements(epg_recording_icon).size() < 1)
					throw new TestException(
							String.format("Recording icon not displayed under the program tile in EPG"));
				return programCell.findElement(epg_video_title);
			}
		}
		if (!programFound)
			throw new TestException(String.format("Couldn't find " + programDetails.get("title") + " in the live TV"));
		return null;
	}

	@Then("^User validates series recording of live airing from EPG$")
	public void validate_series_recording_of_live_airing_from_EPG() throws Throwable {
		select_recordable_live_program_from_epg(true);
		Map<String, String> programDetails = detailsPage.records_series_program_and_validate_updated_details(false);
		detailsPage.close_program_details_page_to_reach_tvguide();
		MobileElement program = verify_recording_icon_present_over_program_in_epg(programDetails);
		program.click();
		detailsPage.stop_recording_series(false);
		detailsPage.delete_recording();
	}

	@Then("^User validates recording_of_live airing not part of series from EPG$")
	public void user_validates_recording_of_live_airing_not_part_of_series_from_EPG() throws Throwable {
		select_recordable_live_program_from_epg(false);
		Map<String, String> programDetails = detailsPage.records_non_series_program_and_validate_updated_details(true);
		detailsPage.close_program_details_page_to_reach_tvguide();
		MobileElement program = verify_recording_icon_present_over_program_in_epg(programDetails);
		program.click();
		detailsPage.stop_recording_non_series_item();
		detailsPage.delete_recording();
	}

	@Given("^User validates recording of replay airing not part of series from EPG$")
	public void validate_recording_of_replay_airing_not_part_of_series_from_EPG() {
		user_selects_yesterday_in_tvguide();
		boolean programFound = false;
		boolean isLive=false;
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programList = commonUtils.findElements(epg_video_title);
			for (int j = 0; j < programList.size(); j++) {
				programList = commonUtils.findElements(epg_video_title);
				programList.get(j).click();
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
				}
				if (detailsPage.is_program_replayable(isLive)) {
					if (!detailsPage.is_completed_program_non_series()) {
						programFound = true;
						break;
					}
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
		}
		if (!programFound)
			throw new TestException(String.format("Couldn't find replayable completed non-series program from EPG"));
	}

	@Then("^User validates recording of future airing part of series from EPG$")
	public void validate_recording_of_future_airing_part_of_series_from_EPG() {
		user_selects_tomorrow_in_tvguide();
		select_upcoming_recordable_series_program_from_epg();
		Map<String, String> programDetails = detailsPage.records_series_program_and_validate_updated_details(true);
		detailsPage.close_program_details_page_to_reach_tvguide();
		MobileElement program = verify_recording_icon_present_over_program_in_epg(programDetails);
		program.click();
		detailsPage.cancel_recording_episode();
		detailsPage.records_series_program_and_validate_updated_details(false);
		detailsPage.close_program_details_page_to_reach_tvguide();
		verify_recording_icon_present_over_program_in_epg(programDetails);
		program.click();
		detailsPage.stop_recording_series(false);
	}

	@And("^User selects upcoming recordable series program from EPG$")
	public void select_upcoming_recordable_series_program_from_epg() {
		boolean programFound = false;
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programList = commonUtils.findElements(epg_video_title);
			for (int j = 0; j < programList.size(); j++) {
				programList = commonUtils.findElements(epg_video_title);
				programList.get(j).click();
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);

					continue;
				}
				if (detailsPage.is_program_to_be_recorded()) {
					if (detailsPage.verify_if_upcoming_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
		}
		if (!programFound)
			throw new TestException(String.format("Couldn't find recordable upcoming series program from EPG"));
	}

	@Then("^Verify if the EPG program is unlocked$")
	public void verify_if_the_program_is_unlocked() {
		String temp = "";
		boolean lockedProgramFound = false;
		int count = 0;
		for (int i = 0; i < 30; i++) {
			commonUtils.waitTillVisibility(epg_video_title, 30);
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			System.out.println("programList.size()  " + programList.size());
			for (int j = 0; j < programList.size(); j++) {
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(j).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(epg_program_tiles);
					icon = programList.get(j).findElements(age_icon);
				}
				if (icon.isEmpty() || (programList.get(j).findElements(epg_video_title).size() < 1))
					continue;
				if (programList.get(j).findElements(lock_icon).size() < 1)
					throw new TestException(String.format("Program not locked even after enabling parental control"));
				programList.get(j).click();
				settingPage.enter_parental_pin_for_programs();
				try {
					commonUtils.waitTillInvisibility(lock_icon, 30);
				} catch (Exception e) {
					throw new TestException(
							String.format("Program didn't unlock even after entering parental control pin(Lock icon)"));
				}
				programList = commonUtils.findElements(epg_program_tiles);
				String title;
				title = programList.get(j).findElement(epg_video_title).getText();
				if (title.equalsIgnoreCase("Locked content"))
					throw new TestException(
							String.format("Program didn't unlock even after entering parental control pin(Title)"));
				lockedProgramFound = true;
				break;
			}
			if (lockedProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(lockedProgramFound);

	}

//	@Then("^Verify if the EPG program is unlocked$")
//	public void verify_if_the_program_is_unlocked() {
//		String temp = "";
//		boolean lockedProgramFound = false;
////		List<MobileElement> channelList = commonUtils.findElements(channel_cell);
//		int count = 0;
//		while (count < 30) {
//			// counter instead count
////			chnlnum = Integer.parseInt(channelList.get(2).findElement(channel_number).getText());
//			int counter = 0;
//			while (counter < 3) {
//				List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
//				String lastProgramTitle = programList.get(programList.size() - 1).findElement(epg_video_title)
//						.getText();
//				for (int i = 0; i < programList.size(); i++) {
//					List<MobileElement> icon = new ArrayList<MobileElement>();
//					try {
//						icon = programList.get(i).findElements(age_icon);
//					} catch (Exception e) {
//						programList = commonUtils.findElements(epg_program_tiles);
//						icon = programList.get(i).findElements(age_icon);
//					}
//					if (icon.isEmpty() || (programList.get(i).findElements(epg_video_title).size() < 1))
//						continue;
////					(!programList.get(i).findElement(epg_video_title).getText().equalsIgnoreCase("Locked content")
//					if (programList.get(i).findElements(lock_icon).size()<1)
//						throw new TestException(
//								String.format("Program not locked even after enabling parental control"));
//					programList.get(i).click();
//					settingPage.enter_parental_pin_for_programs();
//					try {
//						commonUtils.waitTillInvisibility(lock_icon, 30);
//					} catch (Exception e) {
//						throw new TestException(
//								String.format("Program didn't unlock even after entering parental control pin(Lock icon)"));
//					}
//					programList = commonUtils.findElements(epg_program_tiles);
//					String title;
//					title = programList.get(i).findElement(epg_video_title).getText();
//					if (title.equalsIgnoreCase("Locked content"))
//						throw new TestException(
//								String.format("Program didn't unlock even after entering parental control pin(Title)"));
//					lockedProgramFound = true;
//					break;
//				}
//				if (lockedProgramFound)
//					break;
//				if (lastProgramTitle.equals(temp))
//					counter++;
//				temp = lastProgramTitle;
//				commonUtils.swipeUpOverScreen();
//			}
//			if (lockedProgramFound)
//				break;
//			count++;
//			commonUtils.swipeRightChannel();
////			channelList = commonUtils.findElements(channel_cell);
//		}
//	}

	@Then("^User select and stream past replayable program$")
	public void User_select_and_stream_replay_of_program() throws Throwable {
		boolean replayProgramFound = false;
		boolean isLive=false;
		String text = commonUtils.previousDay_picker("sixthday");
		driver.findElement(By.xpath("//*[contains(@text,'" + text + "')]")).click();
		for (int i = 0; i < 30; i++) {
			commonUtils.waitTillVisibility(epg_program_tiles, 30);
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(epg_program_tiles, 30);
				programList = commonUtils.findElements(epg_program_tiles);
				if (!programList.get(j).isDisplayed()) {
					continue;
				}
				programList.get(j).click();
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
				}
				if (detailsPage.is_program_replayable(isLive)) {
					Assert.assertTrue(detailsPage.user_on_program_details_page());
					detailsPage.program_starts_streaming();
					replayProgramFound = true;
					break;
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (replayProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(replayProgramFound);
	}

	@Given("^User validates live replayable program from TV guide$")
	public void user_validates_live_replayable_program_from_TV_guide() {
		boolean liveReplayProgramFound = false;
		boolean isLive = true;
		commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
		int channelsTab = commonUtils.findElements(epg_video_container).size();
		System.out.println("channelsTab   " + channelsTab);
//		Tablet
		if (channelsTab > 1) {
			for (int i = 0; i < 30; i++) {
				System.out.println("channelsTab   " + channelsTab);
				for (int j = 0; j < channelsTab; j++) {
					commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
					channelsTab = commonUtils.findElements(epg_video_container).size();
					select_ongoing_program_from_tv_guide(j);
					if (detailsPage.is_program_replayable(isLive)) {
						System.out.println("detailsPage.is_program_replayable(isLive)  "
								+ detailsPage.is_program_replayable(isLive));
						Assert.assertTrue(detailsPage.is_program_replayable(isLive));
						liveReplayProgramFound = true;
						break;

					}
					detailsPage.close_program_details_page_to_reach_tvguide();
				}
				if (liveReplayProgramFound) {
					break;
				}
				commonUtils.swipeRightChannel();
			}
		} else {
//			Android phone
			for (int i = 0; i < 30; i++) {
				select_ongoing_program_from_tv_guide(0);
				if (detailsPage.is_program_replayable(isLive)) {
					Assert.assertTrue(detailsPage.is_program_replayable(isLive));
					liveReplayProgramFound = true;
					break;
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
				select_next_channel_from_tv_guide();
			}
		}
		if (!liveReplayProgramFound) {
			throw new TestException(String.format("Live replayable program not found"));
		}
	}

	@Then("^User validates non-replayable live program from TV guide$")
	public void user_validates_non_replayable_live_program_from_TV_guide() throws Throwable {
		boolean liveProgramFound = false;
		boolean liveNonReplayProgramFound = false;
		boolean isLive = true;
		commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
		int channelsTab = commonUtils.findElements(epg_video_container).size();
		System.out.println("channelsTab   " + channelsTab);
		if (channelsTab > 1) {
			for (int i = 0; i < 30; i++) {
				System.out.println("channelsTab   " + channelsTab);
				for (int j = 0; j < channelsTab; j++) {
					Thread.sleep(5000);
					commonUtils.waitTillVisibility(tv_guide_program_poster, 20);
					channelsTab = commonUtils.findElements(epg_video_container).size();
					System.out.println("channelsTab   " + channelsTab);

					liveProgramFound = select_ongoing_program_from_tv_guide(j);
					System.out.println("liveProgramFound   " + liveProgramFound);
					if (liveProgramFound) {
						if (!detailsPage.is_program_replayable(isLive)) {
							System.out.println("is_program_replayable " + detailsPage.is_program_replayable(isLive));
							Assert.assertFalse(detailsPage.is_program_replayable(isLive));
							liveNonReplayProgramFound = true;
							break;
						}
						detailsPage.close_program_details_page_to_reach_tvguide();
					}
				}
				if (liveNonReplayProgramFound) {
					break;
				}
				commonUtils.swipeRightChannel();
			}
		} else {
//			Android phone
			for (int i = 0; i < 30; i++) {
				liveProgramFound = select_ongoing_program_from_tv_guide(0);
				if (liveProgramFound) {
					if (!detailsPage.is_program_replayable(isLive)) {
						Assert.assertFalse(detailsPage.is_program_replayable(isLive));
						liveNonReplayProgramFound = true;
						break;
					}
					detailsPage.close_program_details_page_to_reach_tvguide();
				}
				select_next_channel_from_tv_guide();
			}
		}
		if (!liveNonReplayProgramFound) {
			throw new TestException(String.format("Live replayable program not found"));
		}

//		boolean liveProgramFound = false;
//		boolean isLive = true;
//		for (int i = 0; i < 30; i++) {
//			liveProgramFound = select_ongoing_program_from_tv_guide(0);
//			if (liveProgramFound) {
//				commonUtils.waitTillVisibility(ongoing_program_title, 20);
//				Assert.assertTrue(commonUtils.displayed(ongoing_program_title));
//				if (!detailsPage.is_program_replayable(isLive)) {
//					Assert.assertFalse(detailsPage.is_program_replayable(isLive));
//					break;
//				}
//				detailsPage.close_program_details_page_to_reach_tvguide();
//			}
//			select_next_channel_from_tv_guide();
//		}
	}

	@Then("^User validates replayable program from past on TV guide$")
	public void user_validates_replayable_program_from_past_on_TV_guide() throws Throwable {
		boolean isLive=false;
		boolean pastProgramFound = false;
		the_user_clicks_on_the_date_picker();
		user_select_the_previous_day_program();
		Thread.sleep(5000);
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(epg_program_tiles, 30);
				programList = commonUtils.findElements(epg_program_tiles);
				try {
					if (!programList.get(j).isDisplayed()) {
						continue;
					}
				} catch (Exception e) {
					System.out.println("Stale element");
					continue;
				}
				programList.get(j).click();
				if (commonUtils.displayed(parental_pin_input)) {
					commonUtils.clickonElement(parental_pin_cancel_button);
					continue;
				}
				if (detailsPage.is_program_replayable(isLive)) {
					Assert.assertTrue(detailsPage.user_on_program_details_page());
					pastProgramFound = true;
					break;
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (pastProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
	}

	@Then("^User validates upcoming replayable program on TV guide$")
	public void user_validates_upcoming_replayable_program_on_TV_guide() {
		boolean upcomingProgramFound = false;
		boolean isLive=false;
		user_select_future_day_program();
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(epg_program_tiles, 30);
				programList = commonUtils.findElements(epg_program_tiles);
				try {
					if (!programList.get(j).isDisplayed()) {
						continue;
					}
				} catch (Exception e) {
					System.out.println("Stale element");
					continue;
				}
				programList.get(j).click();
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					continue;
				}
				if (detailsPage.is_program_replayable(isLive)) {
					Assert.assertTrue(detailsPage.user_on_program_details_page());
					upcomingProgramFound = true;
					break;
				}
				detailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (upcomingProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
	}

	@Given("^Validate TV guide loading skeleton$")
	public void validate_tv_guide_loading_skeleton() {
		Assert.assertTrue(commonUtils.displayed(dateselector));
		Assert.assertTrue(commonUtils.displayed(channel_icons));
		Assert.assertTrue(commonUtils.displayed(epg_video_imageview_airing));
		Assert.assertTrue(commonUtils.displayed(channel_layout));
	}

	@Then("^User selects unlocked program from TV Guide$")
	public void user_selects_unlocked_program_from_TVGuide() {
		boolean unlockedProgramFound = false;
		for (int i = 0; i < 30; i++) {
			commonUtils.waitTillVisibility(epg_video_title, 30);
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			System.out.println("programList.size()  " + programList.size());
			if (commonUtils.findElements(age_icon).size() > 0) {
				commonUtils.findElement(age_icon).click();
				if (commonUtils.displayed(parental_pin_input))
					throw new TestException(String.format("Program is locked even after disabling parental control"));
				unlockedProgramFound = true;
				detailsPage.close_program_details_page_to_reach_tvguide();
				System.out.println("Program found in TV Guide");
				break;
			} else {
				select_next_channel_from_tv_guide();
			}

//			for (int j = 0; j < programList.size(); j++) {
//				List<MobileElement> icon = new ArrayList<MobileElement>();
//				try {
//					icon = programList.get(j).findElements(age_icon);
//				} catch (Exception e) {
//					programList = commonUtils.findElements(epg_program_tiles);
//					icon = programList.get(j).findElements(age_icon);
//				}
//				if (icon.isEmpty() || (programList.get(j).findElements(epg_video_title).size() < 1))
//					continue;
//				programList.get(j).click();
//				if (commonUtils.displayed(parental_pin_input))
//					throw new TestException(String.format("Program is locked even after disabling parental control"));
//				unlockedProgramFound = true;
//				detailsPage.close_program_details_page_to_reach_tvguide();
//				System.out.println("Program found in TV Guide");
//				break;
//			}
//			if (unlockedProgramFound)
//				break;
//			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(unlockedProgramFound);
	}
	
	@Then("^User selects program without age rating from TV Guide$")
	public void user_selects_program_without_age_rating_from_TVGuide() {
		String temp = "";
		boolean unlockedProgramFound = false;
		int count = 0;
		for (int i = 0; i < 30; i++) {
			commonUtils.waitTillVisibility(epg_video_title, 30);
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			System.out.println("programList.size()  " + programList.size());
			for (int j = 0; j < programList.size(); j++) {
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(j).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(epg_program_tiles);
					icon = programList.get(j).findElements(age_icon);
				}
				if (!icon.isEmpty() || (programList.get(j).findElements(epg_video_title).size() > 0))
					continue;
				programList.get(j).click();
				if (commonUtils.displayed(parental_pin_input))
					throw new TestException(String.format("Program is locked even after disabling parental control"));
				unlockedProgramFound = true;
				detailsPage.close_program_details_page_to_reach_tvguide();
				break;
			}
			if (unlockedProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(unlockedProgramFound);
	}
	
	@Then("^User selects locked program from TV Guide$")
	public void user_selects_locked_program_from_TVGuide() throws Throwable {
		boolean lockedProgramFound = false;
		for (int i = 0; i < 30; i++) {
			commonUtils.waitTillVisibility(epg_video_title, 30);
			List<MobileElement> programList = commonUtils.findElements(epg_program_tiles);
			System.out.println("programList.size()  " + programList.size());
			if (commonUtils.findElements(age_icon).size() > 0) {
				commonUtils.findElement(age_icon).click();
				if (!commonUtils.displayed(parental_pin_cancel_button))
					throw new TestException(
							String.format("Program is not locked even after enabling parental control-TV Guide"));
				commonUtils.clickonElement(parental_pin_cancel_button);
				programList = commonUtils.findElements(epg_program_tiles);
				commonUtils.findElement(age_icon).click();
				if (!commonUtils.displayed(parental_pin_input))
					throw new TestException(String.format("Program is unlocked without entering pin-TV Guide"));
				lockedProgramFound = true;
				System.out.println("Program found in TV Guide");
				break;
			} else {
				select_next_channel_from_tv_guide();
			}
			
//			for (int j = 0; j < programList.size(); j++) {
//				List<MobileElement> icon = new ArrayList<MobileElement>();
//				try {
//					icon = programList.get(j).findElements(age_icon);
//				} catch (Exception e) {
//					programList = commonUtils.findElements(epg_program_tiles);
//					try {
//						icon = programList.get(j).findElements(age_icon);
//					} catch (Exception err) {
//						System.out.println("No live  " + err);
//						break;
//					}
//				}
//				if (icon.isEmpty() || (programList.get(j).findElements(epg_video_title).size() < 1))
//					continue;
//				Thread.sleep(10000);
//				programList.get(j).findElement(epg_video_title).click();
//				if (!commonUtils.displayed(parental_pin_cancel_button))
//					throw new TestException(
//							String.format("Program is not locked even after enabling parental control-TV Guide"));
//				commonUtils.clickonElement(parental_pin_cancel_button);
//				programList = commonUtils.findElements(epg_program_tiles);
//				programList.get(j).findElement(epg_video_title).click();
//				if (!commonUtils.displayed(parental_pin_input))
//					throw new TestException(String.format("Program is unlocked without entering pin-TV Guide"));
////				commonUtils.clickonElement(parental_pin_cancel_button);
//				lockedProgramFound = true;
//				System.out.println("Program found in TV Guide");
//				break;
//			}
//			if (lockedProgramFound)
//				break;
//			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(lockedProgramFound);
	}
	
	@And("^User unlocks program temporarily from TV Guide$")
	public void user_unlocks_program_from_TV_Guide() throws Throwable {
		user_selects_locked_program_from_TVGuide();
		settingPage.enter_parental_pin_for_programs();
		try {
			commonUtils.waitTillInvisibility(lock_icon, 20);
		} catch (Exception e) {
			System.out.println("Program is locked even after entering parental pin");
		}
	}
}
