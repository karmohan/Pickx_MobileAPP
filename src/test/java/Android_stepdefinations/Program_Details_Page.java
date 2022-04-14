package Android_stepdefinations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.TestException;

import Android_screens.Home_Screen;
import Android_screens.LiveTV_Screen;
import Android_screens.Program_Details_Screen;
import Android_screens.TVguide_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Program_Details_Page implements Program_Details_Screen, TVguide_Screen, Home_Screen, LiveTV_Screen {

    public static AppiumDriver<MobileElement> driver;
    public CommonUtils commonUtils;
	public Android_input_properties inputProperties;
	AndroidDriver<MobileElement> androidDriver;

	public Program_Details_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		//AndroidDriver<AndroidElement> driver = (AndroidDriver<AndroidElement>)driver;
		inputProperties = new Android_input_properties();
	}

	@Then("^Program starts streaming$")
	public void program_starts_streaming() {
	/*	if(commonUtils.displayed(streamNotAvailable_TryAgain)) {
			commonUtils.clickonElement(streamNotAvailable_TryAgain);
		} */
		user_click_on_try_again_on_the_playerscreen();
		click_on_play_pause_button_over_player_screen();
		commonUtils.waitTillVisibility(epg_live_mute_button, 60);
		Assert.assertTrue(commonUtils.displayed(epg_live_mute_button));
		Assert.assertTrue(commonUtils.displayed(epg_live_rewind_button));
	}
	
    @And("^User click on try again on the playerscreen$")
    public void user_click_on_try_again_on_the_playerscreen() {
    	if(commonUtils.displayed(streamNotAvailable_TryAgain)) {			
    	do {
    		commonUtils.clickonElement(streamNotAvailable_TryAgain);
    	} while(commonUtils.displayed(streamNotAvailable_TryAgain));
    	}
    }

	public void click_on_play_pause_button_over_player_screen() {
		try {
		commonUtils.waitTillVisibility(play_pause_button_on_player_screen, 10);
		}
		catch(Exception e) {
			throw new TestException(String.format("Program play button not displayed in program details page"));
		}
		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
		commonUtils.clickonElement(play_pause_button_on_player_screen);
	}

	@Then("^The user validates player control$")
	public void the_user_validates_player_control() {
		the_user_rewind_the_video();
	}

	@Then("^The user validates player control in Replay plus subscription$")
	public void the_user_validates_player_control_in_replay_plus_subscription() {
		the_user_rewind_the_video_multiple_times();
	}

	@And("^The user rewind the video$")
	public void the_user_rewind_the_video() {
		String current_time = commonUtils.getText(live_elapsed_time);
		System.out.println("current_time" + current_time);
		commonUtils.clickonElement(play_pause_button_on_player_screen);
		commonUtils.clickonElement(epg_live_rewind_button);
		String after_rewind_time = commonUtils.getText(live_elapsed_time);
		Assert.assertTrue(commonUtils.displayed(goto_live_icon));
		Assert.assertFalse(commonUtils.enabled(epg_live_forward_button));
		System.out.println("after_rewind_time" + after_rewind_time);
//		if (commonUtils.displayed(epg_goto_live_icon))
//			commonUtils.clickonElement(epg_goto_live_icon);
		float current_time_in_sec = (Float.parseFloat((current_time.split(":")[0])) * 60)
				+ (Float.parseFloat((current_time.split(":")[1])) * 60);
		float after_rewind_time_in_sec = (Float.parseFloat((after_rewind_time.split(":")[0])) * 60)
				+ (Float.parseFloat((after_rewind_time.split(":")[1])) * 60);
		System.out.println("number is  " + current_time_in_sec);
		System.out.println("number is  " + after_rewind_time_in_sec);
		boolean time_difference = current_time_in_sec >= after_rewind_time_in_sec;
		System.out.println("time_difference  " + time_difference);
		commonUtils.clickonElement(live_vidoe_playing_container);
	}

	@And("^The user rewind the video multiple times$")
	public void the_user_rewind_the_video_multiple_times() {
		String video_start_time = commonUtils.getText(live_elapsed_time);
		System.out.println("video_start_time" + video_start_time);
		commonUtils.clickonElement(play_pause_button_on_player_screen);
		commonUtils.clickonElement(epg_live_rewind_button);
		Assert.assertTrue(commonUtils.displayed(goto_live_icon));
		commonUtils.clickonElement(live_vidoe_playing_container);
		commonUtils.clickonElement(epg_live_rewind_button);
		commonUtils.clickonElement(live_vidoe_playing_container);
		commonUtils.clickonElement(epg_live_rewind_button);
		if(!commonUtils.displayed(epg_live_rewind_button)) {
			commonUtils.clickonElement(live_vidoe_playing_container);
		}
		String current_time = commonUtils.getText(live_elapsed_time);
		System.out.println("current_time" + current_time);
		commonUtils.clickonElement(live_vidoe_playing_container);
		commonUtils.clickonElement(epg_live_rewind_button);
//		if (!commonUtils.displayed(epg_live_forward_button)) {
//			commonUtils.clickonElement(live_vidoe_playing_container);
//		}
		commonUtils.clickonElement(live_vidoe_playing_container);
		commonUtils.clickonElement(epg_live_forward_button);
		if(!commonUtils.displayed(epg_live_forward_button)) {
			commonUtils.clickonElement(live_vidoe_playing_container);
		}
		String after_rewind_time = commonUtils.getText(live_elapsed_time);
		System.out.println("after_rewind_time" + after_rewind_time);
		float current_time_in_sec = (Float.parseFloat((current_time.split(":")[0])) * 60)
				+ (Float.parseFloat((current_time.split(":")[1])) * 60);
		float after_rewind_time_in_sec = (Float.parseFloat((after_rewind_time.split(":")[0])) * 60)
				+ (Float.parseFloat((after_rewind_time.split(":")[1])) * 60);
		System.out.println("number is  " + current_time_in_sec);
		System.out.println("number is  " + after_rewind_time_in_sec);
		boolean time_difference = current_time_in_sec >= after_rewind_time_in_sec;
		System.out.println("time_difference  " + time_difference);
		commonUtils.clickonElement(live_vidoe_playing_container);
	}

	public boolean user_on_program_details_page() {
		try {
			commonUtils.waitTillVisibility(player_screen, 20);
		} catch (Exception e) {
			commonUtils.clickonback();
			try {
				commonUtils.waitTillVisibility(player_screen, 20);
			} catch (Exception err) {
				throw new TestException(String.format("Program details page not displayed"));
			}
		}
		return true;
	}

	public boolean user_on_program_details_page_live_program() {
		try {
			commonUtils.waitTillVisibility(ongoing_program_title, 20);
		} catch (Exception e) {
			commonUtils.clickonback();
			try {
				commonUtils.waitTillVisibility(ongoing_program_title, 20);
			} catch (Exception err) {
				throw new TestException(String.format("Program details page not displayed"));
			}
		}
		return true;
	}

	@Then("^Metadata properly displayed for ongoing program$")
	public void metadata_properly_displayed_for_ongoing_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertTrue(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(live_icon));
		Assert.assertTrue(commonUtils.displayed(ongoing_program_title));
		Assert.assertTrue(commonUtils.displayed(ongoing_channel_logo));
		Assert.assertTrue(commonUtils.displayed(ongoing_program_time));
		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	@And("^Metadata properly displayed for non-playable live program$")
	public void metadata_properly_displayed_for_non_playable_live_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		if (commonUtils.displayed(player_screen_swimlane_Tab))
			Assert.assertTrue(commonUtils.displayed(player_screen_swimlane_Tab));
		else {
			Assert.assertTrue(commonUtils.displayed(player_screen));
			Assert.assertTrue(commonUtils.displayed(not_playable_icon));
		}
		Assert.assertFalse(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(live_icon));
		Assert.assertTrue(commonUtils.displayed(ongoing_program_title));
		Assert.assertTrue(commonUtils.displayed(ongoing_channel_logo));
		Assert.assertTrue(commonUtils.displayed(ongoing_program_time));
		Assert.assertFalse(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	@And("^Metadata properly displayed for non-series replay content$")
	public void metadata_properly_displayed_for_nonseries_replay_content() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertFalse(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(channel_logo));
		Assert.assertTrue(commonUtils.displayed(program_time));
		Assert.assertFalse(commonUtils.displayed(record_button));
		Assert.assertTrue(commonUtils.displayed(program_synopsis));
		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	public boolean is_program_replayable(boolean isLive) {
		boolean replayable = false;
		if (isLive)
			user_on_program_details_page_live_program();
		else
			user_on_program_details_page();
		if (commonUtils.displayed(details_page_replay_icon))
			replayable = true;
		return replayable;
	}

	public boolean verify_if_ongoing_program_is_part_of_series() {
		boolean programIsSeries = false;
		String recordButtonText = commonUtils.getText(record_button_text);
		// Method for verifying if the ongoing program is series or non-series. For
		// series program record button will be updated to manage recording. Also a
		// recording pop-up will be displayed on selecting the record button. For
		// non-series program there will be no recording pop-up and button will be
		// updated as stop recording (for ongoing program)/ cancel recording (upcoming
		// program)
		System.out.println("recordButtonText " + recordButtonText);
		if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("manage_recording"))
				|| recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record_series")))
			programIsSeries = true;
		else if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("stop_recording"))) {
			programIsSeries = false;
		} else if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record"))) {
			commonUtils.clickonElement(record_button_text);
			if (commonUtils.displayed(recording_pop_up)) {
				programIsSeries = true;
				// Closing the recording pop-up
				driver.navigate().back();
			} else {
				programIsSeries = false;
				try {
					commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("stop_recording"), record_button_text);
					commonUtils.clickonElement(record_button_text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return programIsSeries;
	}

	public boolean verify_if_completed_program_is_part_of_series() {
		boolean isSeries = false;
		// For completed programs if it is series, there will be record button and if
		// non-series there will be no record button
		if (commonUtils.displayed(record_button))
			isSeries = true;
		return isSeries;
	}

	@Then("^User closes program details page to reach tv guide$")
	public void close_program_details_page_to_reach_tvguide() { 
//		if(commonUtils.displayed(close_button)) {
//		commonUtils.clickonback();
		commonUtils.clickonElement(close_button);
		commonUtils.waitTillVisibility(tv_guide_program_poster, 30);
		commonUtils.waitTillVisibility(epg_video_title, 30);
//		Assert.assertTrue(commonUtils.displayed(tv_guide_program_poster));
//		Assert.assertTrue(commonUtils.displayed(epg_video_title));
//		}
	}

	@And("Metadata properly displayed for series replay content$")
	public void metadata_properly_displayed_for_series_replay_content() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertFalse(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(channel_logo));
		Assert.assertTrue(commonUtils.displayed(program_time));
		Assert.assertTrue(commonUtils.displayed(record_button));
		Assert.assertTrue(commonUtils.displayed(program_synopsis));
		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	public boolean verify_if_upcoming_program_is_part_of_series() {
		boolean programIsSeries = false;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String recordButtonText = commonUtils.getText(record_button_text);
		// For upcoming programs, if the program is series record button will be updated
		// to manage recording or recording pop-up will be displayed on selecting the
		// record button. For non-series program record button will be updated cancel
		// recording and there won't be any recording pop-up
//		if (recordButtonText == null) {
//			
//			return false;
//		}
		if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("manage_recording"))
				|| recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record_series"))) {
			programIsSeries = true;
		} else if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("cancel_recording")))
			programIsSeries = false;
		else if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record"))) {
			commonUtils.clickonElement(record_button_text);
			if (commonUtils.displayed(recording_pop_up)) {
				programIsSeries = true;
				// Cancel the recording pop-up
				driver.navigate().back();
			} else {
				programIsSeries = false;
				try {
					commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("cancel_recording"), record_button_text);
					commonUtils.clickonElement(record_button_text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return programIsSeries;
	}

	@And("^Metadata properly displayed for upcoming program$")
	public void metadata_properly_displayed_for_non_series_upcoming_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertFalse(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(channel_logo));
		Assert.assertTrue(commonUtils.displayed(program_time));
		Assert.assertTrue(commonUtils.displayed(record_button));
		Assert.assertTrue(commonUtils.displayed(program_synopsis));
		Assert.assertFalse(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	@Then("^Metadata properly displayed for non-playable upcoming program$")
	public void metadata_properly_displayed_for_non_playable_upcoming_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(player_screen));
		if(commonUtils.displayed(live_icon)) {
			Assert.assertFalse(commonUtils.displayed(progress_bar));
			Assert.assertTrue(commonUtils.displayed(live_icon));
			Assert.assertTrue(commonUtils.displayed(ongoing_program_title));
			Assert.assertTrue(commonUtils.displayed(ongoing_channel_logo));
			Assert.assertTrue(commonUtils.displayed(ongoing_program_time));
			Assert.assertFalse(commonUtils.displayed(play_pause_button_on_player_screen));
		}
		else {
			Assert.assertFalse(commonUtils.displayed(progress_bar));
//			Assert.assertTrue(commonUtils.displayed(not_playable_icon));
			Assert.assertTrue(commonUtils.displayed(program_title));
			Assert.assertTrue(commonUtils.displayed(channel_logo));
			Assert.assertTrue(commonUtils.displayed(program_time));
			Assert.assertFalse(commonUtils.displayed(play_pause_button_on_player_screen));
		}
	}

	public void close_program_details_page_to_reach_homescreen() {
		commonUtils.clickonElement(close_button);
		commonUtils.waitTillVisibility(swimlane_program_poster, 20);
		Assert.assertTrue(commonUtils.displayed(swimlane_program_poster));
	}

	public void delete_recording() {
		commonUtils.waitTillVisibility(player_screen, 20);
		boolean recordingDeleted = false;
		commonUtils.clickonElement(record_button_text);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
		for (MobileElement option : recordingOptions) {
			if (option.getText().contains(commonUtils.getTextBasedonLanguage_Android("delete_recording"))) {
				option.click();
				commonUtils.waitTillVisibility(toast_message_for_deletion, 5);
				commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("record"), record_button_text);
				recordingDeleted = true;
				break;
			}
		}
		if (commonUtils.displayed(recording_icon))
			throw new TestException(
					String.format("Record icon not removed from details page even after deletion of the program"));
		if (!recordingDeleted)
			throw new TestException(String.format("'Delete recording' option not found"));
	}

	public Map<String, String> record_series_program(boolean recordEpisode) {
		boolean recorded = false;
		commonUtils.clickonElement(record_button_text);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
		for (MobileElement option : recordingOptions) {
			if (option.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record_episode")) == recordEpisode) {
				option.click();
				commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("manage_recording"), record_button_text);
				recorded = true;
				break;
			}
		}
		String requiredOption;
		if (recordEpisode)
			requiredOption = "Record episode";
		else
			requiredOption = "Record series";
		if (!recorded)
			throw new TestException(String.format("'" + requiredOption + "' option not found"));
		Map<String, String> returnValue = new HashMap<String, String>();
		try {
			returnValue.put("title", commonUtils.getText(ongoing_program_title));
			returnValue.put("time", commonUtils.getText(ongoing_program_time));
		} catch (Exception e) {
			returnValue.put("title", commonUtils.getText(program_title));
			returnValue.put("time", commonUtils.getText(program_time));
		}
		return returnValue;
	}

	public void stop_recording_episode() {
		commonUtils.waitTillVisibility(player_screen, 20);
		boolean recordingStopped = false;
		commonUtils.clickonElement(record_button_text);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
		for (MobileElement option : recordingOptions) {
			if (option.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("stop_episode_recording"))) {
				option.click();
				// Expected text is 'manage recording' but actual result is 'record'
				commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("manage_recording"), record_button_text);
				recordingStopped = true;
				break;
			}
		}
		if (!recordingStopped)
			throw new TestException(String.format("'Stop episode recording' option not found"));
	}

    public boolean is_program_to_be_recorded() {
	boolean programToBeRecorded = false;
	String recordButtonText = commonUtils.getText(record_button_text);
	if (recordButtonText != null)
	    if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record"))
		    || recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record_series")))
		programToBeRecorded = true;
	return programToBeRecorded;
    }
    
    public Map<String, String> records_series_program_and_validate_updated_details(boolean recordEpisodeOnly) {
	Map<String, String> recordedProgramDetails = record_ongoing_or_future_series_program(recordEpisodeOnly);
	if (!commonUtils.displayed(recording_icon))
	    throw new TestException(
		    String.format("Recording icon not displayed in the program details page for ongoing recording"));
	return recordedProgramDetails;
    }
    
    public Map<String, String> record_ongoing_or_future_series_program(boolean recordEpisode) {
	boolean recorded = false;
	commonUtils.clickonElement(record_button_text);
	commonUtils.waitTillVisibility(recording_pop_up, 5);
	List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
	for (MobileElement option : recordingOptions) {
	    if (option.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record_episode")) == recordEpisode) {
		option.click();
		commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("manage_recording"), record_button_text);
		recorded = true;
		break;
	    }
	}
	String requiredOption;
	if (recordEpisode)
	    requiredOption = "Record episode";
	else
	    requiredOption = "Record series";
	if (!recorded)
	    throw new TestException(String.format("'" + requiredOption + "' option not found"));
	Map<String, String> returnValue = new HashMap<String, String>();
	By titleElement = program_title;
	By timeElement = program_time;
	if (commonUtils.displayed(ongoing_program_title)) {
	    titleElement = ongoing_program_title;
	    timeElement = ongoing_program_time;
	}
	returnValue.put("title", commonUtils.getText(titleElement));
	returnValue.put("time", commonUtils.getText(timeElement));
	return returnValue;
    }
    
    public void stop_recording_series(boolean isPastProgram) {
	commonUtils.waitTillVisibility(player_screen, 20);
	boolean recordingStopped = false;
	commonUtils.clickonElement(record_button_text);
	commonUtils.waitTillVisibility(recording_pop_up, 5);
	List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
	for (MobileElement option : recordingOptions) {
	    if (option.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("stop_series_recording"))) {
		option.click();
		tapOnYesPopUp();
		// Expected text is 'manage recording' but actual result is 'record'
		String expectedOutput = commonUtils.getTextBasedonLanguage_Android("manage_recording");
		if (isPastProgram)
		    expectedOutput = commonUtils.getTextBasedonLanguage_Android("record_series");
		commonUtils.waitTillTextChanges(expectedOutput, record_button_text);
		recordingStopped = true;
		break;
	    }
	}
	if (!recordingStopped)
	    throw new TestException(String.format("'Stop episode recording' option not found"));
    }
    
    public Map<String, String> records_completed_series_program_and_validate_updated_details() {
	Map<String, String> recordedProgramDetails = record_completed_series();
	if (!commonUtils.displayed(recording_icon))
	    throw new TestException(
		    String.format("Recording icon not displayed in the program details page for ongoing recording"));
	return recordedProgramDetails;
    }
    
    public Map<String, String> record_completed_series() {
	commonUtils.clickonElement(record_button_text);
	commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("manage_recording"), record_button_text);
	Map<String, String> returnValue = new HashMap<String, String>();
	returnValue.put("title", commonUtils.getText(program_title));
	returnValue.put("time", commonUtils.getText(program_time));
	return returnValue;
    }
    
    public void stop_series_recording_of_completed_program_and_validate_updated_details() {
	stop_recording_series(true);
	if (commonUtils.displayed(recording_icon))
	    throw new TestException(String.format(
		    "'Scheduled for recording' icon displayed in the program details page even after the record is stopped"));
    }
    
    public boolean is_completed_program_non_series() {
	boolean programIsNonSeries = false;
	if (commonUtils.displayed(record_button_text)) {
	    String recordButtonText = commonUtils.getText(record_button_text);
	    if (recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("record"))
		    || recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("stop_recording"))
		    || recordButtonText.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("cancel_recording")))
		throw new TestException(
			String.format("Record button text of completed program displayed as " + recordButtonText));
	} else
	    programIsNonSeries = true;
	return programIsNonSeries;
    }
    
    public void cancel_recording_episode() {
	commonUtils.waitTillVisibility(player_screen, 20);
	boolean recordingStopped = false;
	commonUtils.clickonElement(record_button_text);
	commonUtils.waitTillVisibility(recording_pop_up, 5);
	List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
	for (MobileElement option : recordingOptions) {
	    if (option.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("cancel_recording_this_episode"))) {
		option.click();
		// Expected text is 'manage recording' but actual result is 'record'
		commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("record"), record_button_text);
		recordingStopped = true;
		break;
	    }
	}
	if (!recordingStopped)
	    throw new TestException(String.format("'Stop episode recording' option not found"));
    }

	public void close_program_details_page_to_reach_livetv() {
		commonUtils.clickonElement(close_button);
		commonUtils.waitTillVisibility(live_tv_program_poster, 20);
		Assert.assertTrue(commonUtils.displayed(live_tv_program_poster));
	}

	public void stop_recording_series() {
		commonUtils.waitTillVisibility(player_screen, 20);
		boolean recordingStopped = false;
		commonUtils.clickonElement(record_button_text);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		List<MobileElement> recordingOptions = commonUtils.findElements(option_in_recording_pop_up);
		for (MobileElement option : recordingOptions) {
			if (option.getText().equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("stop_series_recording"))) {
				option.click();
				tapOnYesPopUp();
				// Expected text is 'manage recording' but actual result is 'record'
				commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("manage_recording"), record_button_text);
				recordingStopped = true;
				break;
			}
		}
		if (!recordingStopped)
			throw new TestException(String.format("'Stop episode recording' option not found"));
	}

	public void tapOnYesPopUp() {
		if (commonUtils.displayed(yes_button_in_pop_up))
			commonUtils.clickonElement(yes_button_in_pop_up);
	}

        public Map<String, String> records_non_series_program_and_validate_updated_details(boolean isOngoing) {
		Map<String, String> recordedProgramDetails = record_non_series(isOngoing);
		if (!commonUtils.displayed(recording_icon))
			throw new TestException(
					String.format("Recording icon not displayed in the program details page for ongoing recording"));
		return recordedProgramDetails;
	}

        public Map<String, String> record_non_series(boolean isOngoing) {
        	commonUtils.clickonElement(record_button_text);
        	String expectedButtonText = commonUtils.getTextBasedonLanguage_Android("stop_recording");
        	if (!isOngoing)
        	    expectedButtonText = commonUtils.getTextBasedonLanguage_Android("cancel_recording");
        	commonUtils.waitTillTextChanges(expectedButtonText, record_button_text);
        	Map<String, String> returnValue = new HashMap<String, String>();
        	By titleElement = program_title;
        	By timeElement = program_time;
        	if (commonUtils.displayed(ongoing_program_title)) {
        	    titleElement = ongoing_program_title;
        	    timeElement = ongoing_program_time;
        	}
        	returnValue.put("title", commonUtils.getText(titleElement));
        	returnValue.put("time", commonUtils.getText(timeElement));
        	return returnValue;
        }

	public void stop_recording_non_series_item() {
		commonUtils.waitTillVisibility(player_screen, 20);
		commonUtils.clickonElement(record_button_text);
		// Expected text is 'manage recording' but actual result is 'record'
		commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("manage_recording"), record_button_text);
	}
	
        public void cancel_recording_non_series_item() {
        	commonUtils.waitTillVisibility(player_screen, 20);
        	commonUtils.clickonElement(record_button_text);
        	commonUtils.waitTillTextChanges(commonUtils.getTextBasedonLanguage_Android("record"), record_button_text);
        }
}
