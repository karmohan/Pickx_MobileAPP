package iOS_stepdefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.TestException;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.LiveTV_Screen;
import iOS_screens.Program_Details_Screen;
import iOS_screens.Setting_Screen;
import iOS_screens.TVguide_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class TVguide_Page
		implements Home_Screen, LiveTV_Screen, TVguide_Screen, Program_Details_Screen, Setting_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public iOS_input_properties inputProperties;
	public Setting_Page settingPage;
	public List<String> displayed_dates;
	public Program_Details_Page programDetailsPage;

	public TVguide_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new iOS_input_properties();
		settingPage = new Setting_Page();
		programDetailsPage = new Program_Details_Page();
	}

	@Given("^User is on the TV guide page$")
	public void user_is_on_tv_guide_page() {
		commonUtils.waitTillVisibility(tvGuide_button, 10);
		commonUtils.clickonElement(tvGuide_button);
		commonUtils.waitTillVisibility(epg_live_video_container, 30);
		Assert.assertTrue(commonUtils.displayed(epg_live_video_container));

	}

	@And("^User selects non-series replay program from TV guide$")
	public void user_selects_non_series_replay_program_from_tvguide() throws Throwable {
		commonUtils.waitTillVisibility(epg_program, 30);
		boolean displayed = false;
		boolean programFound = false;
//		In iOS all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				displayed = false;
				programTiles = commonUtils.findElements(epg_program);
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 30);
					programFilteredList.get(j).click();
				}
				if (programDetailsPage.is_program_replayable()) {
					if (!programDetailsPage.verify_if_completed_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		if (!programFound)
			throw new TestException(String.format("Non-series replay program is not found from TV guide ."));
	}

	@When("^User selects yesterday in TV guide$")
	public void user_selects_yesterday_in_tvguide() {
		Assert.assertTrue(commonUtils.displayed(date_picker_today));
		boolean dateSelected = false;
		
		if (!commonUtils.getText(date_picker).equalsIgnoreCase(commonUtils.getTextBasedonLanguage("yesterday"))) {
			commonUtils.clickonElement(date_picker_arrow_down);
			List<MobileElement> dateLIst = commonUtils.findElements(date_picker_textview_date);
			for (MobileElement date : dateLIst) {
				if (date.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage("yesterday"))) {
					date.click();
					Assert.assertTrue(commonUtils.getText(date_picker_yesterday)
							.equalsIgnoreCase(commonUtils.getTextBasedonLanguage("yesterday")));
					dateSelected = true;
					break;
				}
			}
		}
		if (!dateSelected)
			throw new TestException(String.format("Failed to select yesterday from day selector"));
	}

	@When("^User selects tomorrow in TV guide$")
	public void user_selects_tomorrow_in_tvguide() {
		Assert.assertTrue(commonUtils.displayed(date_picker_today));
		boolean dateSelected = false;
		if (!commonUtils.getText(date_picker).equalsIgnoreCase(commonUtils.getTextBasedonLanguage("tomorrow"))) {
			commonUtils.clickonElement(date_picker_arrow_down);
			List<MobileElement> dateLIst = commonUtils.findElements(date_picker_textview_date);
			for (MobileElement date : dateLIst) {
				System.out.println("Date  " + date.getText());
				if (date.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage("tomorrow"))) {
					date.click();
//					Assert.assertTrue(commonUtils.getText(date_picker_yesterday)
//							.equalsIgnoreCase(inputProperties.getYesterday()));
					dateSelected = true;
					break;
				}
			}
		}
		if (!dateSelected)
			throw new TestException(String.format("Failed to select tomorrow from day selector"));
	}

	@And("^User selects series replay program from TV guide$")
	public void user_selects_series_replay_program_from_tvguide() {
		commonUtils.waitTillVisibility(epg_program, 30);
		boolean programFound = false;
//		In iphone all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				boolean displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 30);
					programFilteredList.get(j).click();
				}
				if (programDetailsPage.is_program_replayable()) {
					if (programDetailsPage.verify_if_completed_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		if (!programFound)
			throw new TestException(String.format("Series replay program from TV guide is not found."));
	}

	@Then("^User selects non-playable live program$")
	public void user_selects_non_playable_live_program() {
		boolean nonPlayableItemFound = false;
		boolean isLiveProgram = false;
//	Tablet - Since there are 3 channel list in iPad checking in each of them.
		for (int i = 0; i < 20; i++) {
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				if (programTiles.get(j).findElements(non_playable_decoder).size() > 0) {
					isLiveProgram = select_ongoing_program_from_tv_guide(programTiles.get(j));
					if (isLiveProgram) {
						nonPlayableItemFound = true;
						break;
					}
				}
			}
			if (isLiveProgram)
				break;
			select_next_channel_from_tv_guide();
		}
		Assert.assertTrue(nonPlayableItemFound);
	}

	@Then("^User records and validate episode of live airing from EPG$")
	public void user_records_and_validate_live_episode_airing_from_EPG() throws Throwable {
		select_recordable_live_program_from_epg(true);
		Map<String, String> programDetails = programDetailsPage
				.records_series_program_and_validate_updated_details(true);
		programDetailsPage.close_program_details_page_to_reach_tvguide();
		MobileElement program = verify_recording_icon_present_over_program_in_epg(programDetails);
		commonUtils.clickonElement(tv_guide_live_icon);
		programDetailsPage.stop_recording_episode();
		// Delete recording option not found in IOS
//	programDetailsPage.delete_recording();
	}

	public void select_recordable_live_program_from_epg(boolean isSeries) {
		boolean programFound = false;
		for (int i = 0; i < 15; i++) {
			if (isSeries)
				user_selects_series_live_program_from_tvguide();
			else
				user_selects_non_series_live_program_from_tv_guide();
			if (programDetailsPage.is_program_to_be_recorded()) {
				programFound = true;
				break;
			}
			programDetailsPage.close_program_details_page_to_reach_tvguide();
			select_next_channel_from_tv_guide();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find a series program from live TV for recording"));
	}

	@Then("^User selects series live program from TV guide$")
	public void user_selects_series_live_program_from_tvguide() {
		boolean isLive = false;
		boolean liveReplayProgramFound = false;
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				isLive = select_ongoing_program_from_tv_guide(programTiles.get(j));
				if (isLive) {
					if (commonUtils.displayed(record_button) || commonUtils.displayed(record_button_tab)) {
						if (programDetailsPage.verify_if_ongoing_program_is_part_of_series()) {
							liveReplayProgramFound = true;
							break;
						}
					}
					programDetailsPage.close_program_details_page_to_reach_tvguide();
				}
			}
			if (liveReplayProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
	}

	@And("^User selects ongoing program from TV guide$")
	public boolean select_ongoing_program_from_tv_guide(MobileElement channelLiveProgram) {
		if (!channelLiveProgram.findElement(tv_guide_live_icon).isDisplayed()) {
			return false;
		}
		channelLiveProgram.findElement(tv_guide_live_icon).click();
		programDetailsPage.check_streaming_Error();
		if (commonUtils.enabled(parental_pin_input)) {
			settingPage.enter_parental_pin_for_programs();
			commonUtils.waitTillInvisibility(lock_icon, 20);
			channelLiveProgram.findElement(tv_guide_live_icon).click();
		}
		try {
			commonUtils.waitTillVisibility(close_button, 30);
			Assert.assertTrue(commonUtils.displayed(close_button));
		} catch (Exception e) {
			commonUtils.clickonback();
			programDetailsPage.check_streaming_Error();
			Assert.assertTrue(commonUtils.displayed(close_button));
		}
		return true;
	}

	public void select_next_channel_from_tv_guide() {
		List<MobileElement> channelList = commonUtils.findElements(channel_cell);
		if (!commonUtils.findElements(channel_cell_tab).isEmpty()) {
			commonUtils.swipeRightChannel();
		}
		try {
			channelList.get(0).findElements(channel_selected_bar);
		} catch (Exception e) {
			commonUtils.swipeRightChannel();
			commonUtils.waitTillVisibility(channel_cell, 30);
			channelList = commonUtils.findElements(channel_cell);
			System.out.println("channelList Catch" + channelList.size());
		}
		Assert.assertFalse(channelList.isEmpty());
		for (int i = 1; i < channelList.size() - 1; i++) {
			if (channelList.get(i).findElements(channel_selected_bar).size() > 0) {
				channelList.get(i + 1).click();
				break;
			}
		}
	}

	@Given("^User selects non-series live program from TV guide$")
	public void user_selects_non_series_live_program_from_tv_guide() {
		boolean isLive = false;
		boolean liveReplayProgramFound = false;
//		int channelsTab = commonUtils.findElements(channel_cell_tab).size();
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				isLive = select_ongoing_program_from_tv_guide(programTiles.get(j));
				if (isLive) {
					if (commonUtils.displayed(record_button) || commonUtils.displayed(record_button_tab)) {
						if (!programDetailsPage.verify_if_ongoing_program_is_part_of_series()) {
							liveReplayProgramFound = true;
							break;
						}
					}
					programDetailsPage.close_program_details_page_to_reach_tvguide();
				}
			}
			if (liveReplayProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
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
				// Replace with recording icons. In ios different recording icon has different
				// id
//		if (programCell.findElements(epg_recording_icon).size() < 1)
//		    throw new TestException(
//			    String.format("Recording icon not displayed under the program tile in EPG"));
				return programCell.findElement(epg_video_title);
			}
		}
		if (!programFound)
			throw new TestException(String.format("Couldn't find " + programDetails.get("title") + " in the live TV"));
		return null;
	}

	@Given("^Validate TV guide loading skeleton$")
	public void validate_tv_guide_loading_skeleton() {
		Assert.assertTrue(commonUtils.displayed(date_picker_today));
		Assert.assertTrue(commonUtils.displayed(date_picker_arrow_down));
		Assert.assertTrue(commonUtils.displayed(channel_cell));
		Assert.assertTrue(commonUtils.displayed(epg_live_video_container));
	}

	@When("^The user scroll body horizontally$")
	public void user_scroll_boday_horizontally() throws Throwable {
		String beforeScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("Before horizontal scroll the video title is :" + beforeScroll_titile);
		commonUtils.scrollHorizantal();
		commonUtils.scrollHorizantal();
		Thread.sleep(8000);
		String afterScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("After horizontal scroll the video title is :" + afterScroll_titile);
		Assert.assertNotEquals(beforeScroll_titile, afterScroll_titile);
	}

	@Then("^The user scroll body vertically and horizontally \"([^\"]*)\" continously$")
	public void the_user_scroll_body_vertically_and_horizontally_something_continously(String number) throws Throwable {
		String beforeScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("Before horizontal scroll the vidoe title is :" + beforeScroll_titile);
		commonUtils.scrollHorizantal();
		commonUtils.scrollHorizantal();
		commonUtils.scrollHorizantal();
		commonUtils.swipeUpOverScreen();
		commonUtils.swipeUpOverScreen();
		commonUtils.swipeUpOverScreen();
		// commonUtils.waitTillVisibility(epg_video_title, 8);
		String afterScroll_titile = commonUtils.getText(epg_video_title);
		System.out.println("After horizontal scroll the vidoe title is :" + afterScroll_titile);
		Assert.assertNotEquals(beforeScroll_titile, afterScroll_titile);
	}

	@Then("^User scroll the channel vertically$")
	public void user_scroll_channel_vertically() {
		List<MobileElement> programList = commonUtils.findElements(epg_video_title);
		String beforeScroll_titile = null;
		String afterScroll_titile = null;
		for (int i = 0; i < programList.size(); i++) {
			if (programList.get(i).isDisplayed()) {
				beforeScroll_titile = programList.get(i).getText();
				break;
			}
		}
		System.out.println("Before vertical scroll the video title is :" + beforeScroll_titile);
		commonUtils.swipeUpOverScreen();
		commonUtils.swipeUpOverScreen();
		programList = commonUtils.findElements(epg_video_title);
		for (int i = 0; i < programList.size(); i++) {
			if (programList.get(i).isDisplayed()) {
				afterScroll_titile = programList.get(i).getText();
				break;
			}
		}
		System.out.println("After vertical scroll the video title is :" + afterScroll_titile);
		Assert.assertNotEquals(beforeScroll_titile, afterScroll_titile);
	}

	@Given("^The user clicks on the date picker$")
	public void the_user_clicks_on_the_date_picker() {
		commonUtils.clickonElement(date_picker_today);
		// commonUtils.waitTillVisibility(date_picker_textview_date, 8);
		Assert.assertTrue(commonUtils.displayed(date_picker_container));
	}

	@When("^The user validates the date shown in the date picker$")
	public void the_user_validates_the_date_shown_in_the_date_picker() {
		displayed_dates = new ArrayList<String>();
		List<MobileElement> date = commonUtils.findElements(date_picker_textview_date);
		for (MobileElement day_value : date) {
			//String value = day_value.getText().toLowerCase();
			String value = day_value.getText();
			displayed_dates.add(value);
			if (displayed_dates.contains(commonUtils.getTextBasedonLanguage("yesterday"))) {
				displayed_dates.contains(commonUtils.getTextBasedonLanguage("tomorrow"));
				displayed_dates.contains(commonUtils.getTextBasedonLanguage("today"));
			}
		}
		System.out.println("List of previous week value :" + displayed_dates);
	}

	@Then("^The user check previous and the future day is shown in the date picker$")
	public void the_user_check_previous_and_the_future_day_is_shown_in_the_date_picker() {
		commonUtils.swipeDownScreen();
		Assert.assertTrue(displayed_dates.contains(commonUtils.previousDay_picker_ios("sixthday")));
		commonUtils.swipeUpOverScreen();
		Assert.assertTrue(displayed_dates.contains(commonUtils.futureDay_picker_ios("sixthday")));
		/*if (displayed_dates.contains(commonUtils.previousDay_picker_ios("sixthday"))) {
			System.out.println(commonUtils.previousDay_picker_ios("sixthday"));
		}

		if (displayed_dates.contains(commonUtils.futureDay_picker_ios("sixthday"))) {
			System.out.println(commonUtils.futureDay_picker_ios("sixthday"));
		}*/
	}

	@When("^The user validates the program for different dates$")
	public void the_user_validates_the_program_for_different_dates() throws Throwable {
		user_select_the_previous_day_program();
		user_select_today_program();
		user_select_future_day_program();
	}

	@And("^User select the previous day program$")
	public void user_select_the_previous_day_program() throws Throwable {
		System.out.println("PREVIOUS DAY");
		String text = commonUtils.previousDay_picker_ios("thirdday");
		driver.findElement(By.xpath("//*[contains(@value,'" + text + "')]")).click();
//		Assert.assertTrue(commonUtils.displayed(elements_of_each_program_in_tvGuide));
		user_check_for_displayed_program_details();
//		Assert.assertTrue(commonUtils.displayed(replay_icon));
//		Assert.assertTrue(commonUtils.displayed(epg_episode_genre));
		// Assert.assertTrue(commonUtils.displayed(epg_video_airing_details));
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
		commonUtils.waitTillVisibility(epg_program, 30);
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programTiles = commonUtils.findElements(epg_program);
//		Filtering displayed programs from full list programs
		for (int j = 0; j < programTiles.size(); j++) {
			boolean displayed = false;
			programFullList = programTiles.get(j).findElements(epg);
			for (int k = 0; k < programFullList.size(); k++) {
				if (programFullList.get(k).isDisplayed()) {
					try {
						if (!programFullList.get(k).findElement(epg_video_title).isDisplayed())
							continue;
					} catch (Exception e) {
						System.out.println(
								"catch error in todays program - now icon not displayed and title not displayed");
						continue;
					}
					System.out.println("Title  " + programFullList.get(k).findElement(epg_video_title).getText());
					System.out.println(
							"Title  " + programFullList.get(k).findElement(epg_video_broadcast_time1).getText());
					Assert.assertTrue(programFullList.get(k).findElement(epg_video_title).isDisplayed());
					Assert.assertTrue(programFullList.get(k).findElement(epg_video_broadcast_time1).isDisplayed());
					Assert.assertTrue(programFullList.get(k).findElement(epg_episode_genre).isDisplayed());
					displayed = true;
					break;
				}
				k = k + 2;
				if (displayed)
					break;
			}
			if (displayed)
				break;
		}
	}

	@And("^User select today program$")
	public void user_select_today_program() throws Throwable {
		System.out.println("TODAY");
		the_user_clicks_on_the_date_picker();
		commonUtils.clickonElement(commonUtils.findElementByXpathContains("value", commonUtils.getTextBasedonLanguage("today_text_for_Xpath")));
		user_check_for_displayed_program_details();
		Assert.assertFalse(commonUtils.findElements(tv_guide_live_icon).isEmpty());
		System.out.println("Live size " + commonUtils.findElements(tv_guide_live_icon).size());
		List<MobileElement> liveProgramList = commonUtils.findElements(tv_guide_live_icon);
		for (MobileElement liveprogram : liveProgramList) {
			if (liveprogram.isDisplayed()) {
				System.out.println(" Dispalyed live");
				liveprogram.click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 30);
					liveprogram.click();
				}
				break;
			}
		}
		programDetailsPage.check_streaming_Error();
		commonUtils.waitTillVisibility(Play_pause_button_under_player_screen, 20);
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(Play_pause_button_under_player_screen));
		commonUtils.clickonElement(close_button);
	}

	@And("^User select future day program$")
	public void user_select_future_day_program() throws Throwable {
		the_user_clicks_on_the_date_picker();
		String text = commonUtils.futureDay_picker_ios("thirdday");
		if (!driver.findElement(By.xpath("//*[contains(@value,'" + text + "')]")).isDisplayed()) {
			commonUtils.swipeUpOverScreen();
		}
		driver.findElement(By.xpath("//*[contains(@value,'" + text + "')]")).click();
		user_check_for_displayed_program_details();
	}

	@Then("^Verify if the EPG program is unlocked$")
	public void verify_if_the_program_is_unlocked() throws InterruptedException {
		boolean lockedProgramFound = false;
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs.In iOS locators are not defined for age icon, 
//			so each program is selected and checked for locked content.
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				boolean displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				commonUtils.waitTillVisibility(epg_program, 40);
				programFilteredList.get(j).click();
				programDetailsPage.check_streaming_Error();
				if (commonUtils.findElements(parental_pin_input).size() < 1) {
					commonUtils.findElement(close_button).click();
					continue;
				}
				settingPage.enter_parental_pin_for_programs();
				commonUtils.waitTillInvisibility(Locked_icon, 30);
				String title;
				title = programFilteredList.get(j).getText();
				if (title.equalsIgnoreCase("Locked content"))
					throw new TestException(
							String.format("Program didn't unlock even after entering parental control pin"));
				lockedProgramFound = true;
				break;
			}
			if (lockedProgramFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		Assert.assertTrue(lockedProgramFound);
	}

	@Given("^User validates live replayable program from TV guide$")
	public void user_validates_live_replayable_program_from_TV_guide() {
		boolean liveReplayProgramFound = false;
		boolean isLive = true;
//		int channelsTab = commonUtils.findElements(channel_cell_tab).size();
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				isLive = select_ongoing_program_from_tv_guide(programTiles.get(j));
				if (isLive) {
					if (programDetailsPage.is_program_replayable()) {
						Assert.assertTrue(programDetailsPage.is_program_replayable());
						liveReplayProgramFound = true;
						break;
					}
					programDetailsPage.close_program_details_page_to_reach_tvguide();
				}
			}
			if (liveReplayProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
		if (!liveReplayProgramFound)
			throw new TestException(String.format("Live replayable program not found"));
	}

	@Then("^User validates non-replayable live program from TV guide$")
	public void user_validates_non_replayable_live_program_from_TV_guide() throws Throwable {
		boolean liveReplayProgramFound = false;
		boolean isLive = true;
//		int channelsTab = commonUtils.findElements(channel_cell_tab).size();
		for (int i = 0; i < 30; i++) {
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				isLive = select_ongoing_program_from_tv_guide(programTiles.get(j));
				if (isLive) {
					if (!programDetailsPage.is_program_replayable()) {
						Assert.assertFalse(programDetailsPage.is_program_replayable());
						liveReplayProgramFound = true;
						break;
					}
					programDetailsPage.close_program_details_page_to_reach_tvguide();
				}
			}
			if (liveReplayProgramFound)
				break;
			select_next_channel_from_tv_guide();
		}
		if (!liveReplayProgramFound)
			throw new TestException(String.format("Non-replayable Live program is not found"));
	}

	@Then("^User validates replayable program from past on TV guide$")
	public void user_validates_replayable_program_from_past_on_TV_guide() throws Throwable {
//		In iphone all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		boolean pastProgramFound = false;
		the_user_clicks_on_the_date_picker();
		user_select_the_previous_day_program();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				boolean displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					programFilteredList.get(j).click();
				}
				programDetailsPage.check_streaming_Error();
				if (programDetailsPage.is_program_replayable()) {
					Assert.assertTrue(programDetailsPage.user_on_program_details_page());
					pastProgramFound = true;
					break;
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (pastProgramFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		if (!pastProgramFound)
			throw new TestException(String.format("Replayable past program was not found"));
	}

	@Then("^User validates upcoming replayable program on TV guide$")
	public void user_validates_upcoming_replayable_program_on_TV_guide() throws Throwable {
//		In iphone all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		boolean upcomingProgramFound = false;
		user_select_future_day_program();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				boolean displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					programFilteredList.get(j).click();
				}
				programDetailsPage.check_streaming_Error();
				if (programDetailsPage.is_program_replayable()) {
					Assert.assertTrue(programDetailsPage.user_on_program_details_page());
					upcomingProgramFound = true;
					break;
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (upcomingProgramFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		if (!upcomingProgramFound)
			throw new TestException(String.format("Replayable upcoming program was not found"));
	}

	@Then("^User select and stream past replayable program$")
	public void User_select_and_stream_replay_of_program() {
		boolean replayProgramFound = false;
		String text = commonUtils.previousDay_picker_ios("sixthday");
		if (driver.findElement(By.xpath("//*[contains(@value,'" + text + "')]")).isDisplayed()) {
			driver.findElement(By.xpath("//*[contains(@value,'" + text + "')]")).click();
		} else {
			commonUtils.swipeDownScreen();
			driver.findElement(By.xpath("//*[contains(@value,'" + text + "')]")).click();
		}
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				boolean displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					programFilteredList.get(j).click();
				}
				programDetailsPage.check_streaming_Error();
				if (programDetailsPage.is_program_replayable()) {
					Assert.assertTrue(programDetailsPage.user_on_program_details_page());
					programDetailsPage.program_starts_streaming();
					replayProgramFound = true;
					break;
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (replayProgramFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		Assert.assertTrue(replayProgramFound);
	}

	@And("^User selects series upcoming program from TV guide$")
	public void user_selects_series_upcoming_program_from_tvguide() {
		boolean displayed = false;
		boolean programFound = false;
//		In iphone all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}
			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					programFilteredList.get(j).click();
				}
				if (commonUtils.displayed(record_button) || commonUtils.displayed(record_button_tab)) {
					if (programDetailsPage.verify_if_upcoming_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		Assert.assertTrue(programFound);
	}

	@And("^User selects non-series upcoming program from TV guide$")
	public void user_selects_non_series_upcoming_program_from_tvguide() throws Throwable {
		boolean displayed = false;
		boolean programFound = false;
//		In iphone all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
		for (int i = 0; i < 30; i++) {
//			Filtering displayed programs from full list programs
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				displayed = false;
				programFullList = programTiles.get(j).findElements(epg);
				for (int k = 0; k < programFullList.size();) {
					if (programFullList.get(k).isDisplayed()) {
						programFilteredList.add(programFullList.get(k));
						displayed = true;
						k = k + 1;
						continue;
					}
					k = k + 2;
					if (displayed)
						break;
				}

			}
			for (int j = 0; j < programFilteredList.size(); j++) {
				programFilteredList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					programFilteredList.get(j).click();
				}
				if (commonUtils.displayed(record_button) || commonUtils.displayed(record_button_tab)) {
					if (!programDetailsPage.verify_if_upcoming_program_is_part_of_series()) {
						programFound = true;
						break;
					}
				}
				programDetailsPage.close_program_details_page_to_reach_tvguide();
			}
			if (programFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		Assert.assertTrue(programFound);
	}

	@And("^User selects non-playable upcoming program$")
	public void user_selects_non_playable_upcoming_program() {
		boolean displayed = false;
		boolean nonPlayableItemFound = false;
//	In iphone all programs in a channel are returned - so we filter the displayed programs from the full list 
		List<MobileElement> programFullList = new ArrayList<MobileElement>();
		List<MobileElement> programFilteredList = new ArrayList<MobileElement>();
//	Tablet - Since there are 3 channel list in iPad checking in each of them.
//		if (channelsTab > 0) {
		for (int i = 0; i < 20; i++) {
			List<MobileElement> programTiles = commonUtils.findElements(epg_program);
			for (int j = 0; j < programTiles.size(); j++) {
				if (programTiles.get(j).findElements(non_playable_decoder).size() > 0) {
					displayed = false;
					programFullList = programTiles.get(j).findElements(epg);
					for (int k = 0; k < programFullList.size();) {
						if (programFullList.get(k).isDisplayed()) {
							programFilteredList.add(programFullList.get(k));
							displayed = true;
							k = k + 1;
							continue;
						}
						k = k + 2;
						if (displayed)
							break;
					}
					programFilteredList.get(0).click();
					nonPlayableItemFound = true;
					break;
				}
			}
			if (nonPlayableItemFound)
				break;
			select_next_channel_from_tv_guide();
			programFilteredList.clear();
		}
		Assert.assertTrue(nonPlayableItemFound);
	}
}
