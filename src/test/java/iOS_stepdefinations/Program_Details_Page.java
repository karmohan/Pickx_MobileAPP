package iOS_stepdefinations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.TestException;

import iOS_screens.Home_Screen;
import iOS_screens.LiveTV_Screen;
import iOS_screens.Program_Details_Screen;
import iOS_screens.TVguide_Screen;
import iOS_screens.Vod_Details_Screen;
import base.iOS_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import utils.CommonUtils;

public class Program_Details_Page
		implements Home_Screen, LiveTV_Screen, Program_Details_Screen, TVguide_Screen, Vod_Details_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public iOS_input_properties inputProperties;

	public Program_Details_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new iOS_input_properties();
	}

	@Then("^Program starts streaming$")
	public void program_starts_streaming() {
		Assert.assertTrue(commonUtils.displayed(Play_pause_button_under_player_screen));
		commonUtils.clickonElement(Play_pause_button_under_player_screen);
	}

	public void check_streaming_Error() {
		if (commonUtils.displayed(live_tv_streaming_error)) {
			System.out.println("Try again");
			commonUtils.findElement(live_tv_streaming_error_tryagain).click();
		}
	}

	public boolean user_on_program_details_page() {
		try {
//				commonUtils.waitTillVisibility(player_screen, 20);
			check_streaming_Error();
			commonUtils.waitTillVisibility(program_title, 40);
		} catch (Exception checkStreamingError) {
// 			Checking if Streaming try again option is displayed
			check_streaming_Error();
			if (!commonUtils.displayed(program_title))
				commonUtils.clickonback();
			try {
				commonUtils.waitTillVisibility(program_title, 40);
			} catch (Exception err) {
				throw new TestException(String.format("Program details page not displayed"));
			}
		}
		return true;
	}

	public boolean user_on_VOD_program_details_page() {
		try {
//				commonUtils.waitTillVisibility(player_screen, 20);
			check_streaming_Error();
			commonUtils.waitTillVisibility(vod_program_title, 40);
		} catch (Exception e) {
//			Checking if Streaming try again option is displayed
			check_streaming_Error();
			if (!commonUtils.displayed(vod_program_title))
				commonUtils.clickonback();
			try {
				commonUtils.waitTillVisibility(vod_program_title, 30);
			} catch (Exception err) {
				throw new TestException(String.format("Program details page not displayed"));
			}
		}
		return true;
	}
	public Map<String, String> records_series_program_and_validate_updated_details(boolean recordEpisodeOnly) {
		Map<String, String> recordedProgramDetails = record_ongoing_or_future_series_program(recordEpisodeOnly);
//	if (!commonUtils.displayed(recording_icon))
//	    throw new TestException(
//		    String.format("Recording icon not displayed in the program details page for ongoing recording"));
		return recordedProgramDetails;
	}

	public Map<String, String> record_ongoing_or_future_series_program(boolean recordEpisode) {
		boolean recorded = false;
		commonUtils.clickonElement(record_button_text);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		if (recordEpisode) {
			commonUtils.clickonElement(record_this_episode_option);
			commonUtils.waitTillTextChangesForIos(inputProperties.getManageRecording(), record_button_text);
			recorded = true;
		}
		String requiredOption;
		if (recordEpisode)
			requiredOption = "Record episode";
		else
			requiredOption = "Record series";
		if (!recorded)
			throw new TestException(String.format("'" + requiredOption + "' option not found"));
		Map<String, String> returnValue = new HashMap<String, String>();
		By titleElement = ongoing_program_title;
		By timeElement = ongoing_program_time;
		returnValue.put("title", commonUtils.getText(titleElement));
		returnValue.put("time", commonUtils.getText(timeElement));
		return returnValue;
	}

	@Then("^User closes program details page to reach tv guide$")
	public void close_program_details_page_to_reach_tvguide() {
		commonUtils.clickonElement(close_button);
		commonUtils.waitTillVisibility(channel_selected_bar, 20);
		Assert.assertTrue(commonUtils.displayed(channel_selected_bar));
	}

	public void stop_recording_episode() {
//	commonUtils.waitTillVisibility(player_screen, 20);
		boolean recordingStopped = false;
		commonUtils.clickonElement(record_button_text);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		if (commonUtils.displayed(stop_recording_episode_popup_option)) {
			commonUtils.clickonElement(stop_recording_episode_popup_option);
			// Expected text is 'manage recording' but actual result is 'record'
			commonUtils.waitTillTextChangesForIos(inputProperties.getManageRecording(), record_button_text);
			recordingStopped = true;
		}
		if (!recordingStopped)
			throw new TestException(String.format("'Stop recording' option for stopping episode recording not found"));
	}

	public void delete_recording() throws Throwable {
//		commonUtils.waitTillVisibility(player_screen, 20);
	Thread.sleep(30000);
	boolean recordingDeleted = false;
	commonUtils.clickonElement(record_button_text);
	commonUtils.waitTillVisibility(recording_pop_up, 5);
	List<MobileElement> recordingOptions = commonUtils.findElements(button_record_list);
	for (MobileElement option : recordingOptions) {
		if (option.getText().contains(inputProperties.getDeleteRecording())) {
			option.click();
//				commonUtils.waitTillVisibility(toast_message_for_deletion, 5);
			Thread.sleep(30000);
//				commonUtils.waitTillTextChanges(inputProperties.getRecord(), record_button_text);
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

	public boolean is_program_to_be_recorded() {
		boolean programToBeRecorded = false;
		String recordButtonText = commonUtils.getText(record_button_text);
		if (recordButtonText != null)
			if (recordButtonText.equalsIgnoreCase(inputProperties.getRecord())
					|| recordButtonText.equalsIgnoreCase(inputProperties.getRecordSeries()))
				programToBeRecorded = true;
		return programToBeRecorded;
	}

	public boolean verify_if_ongoing_program_is_part_of_series() {
		boolean programIsSeries = false;
		By recordLocator = null;
		if (!commonUtils.findElements(record_button).isEmpty())
			recordLocator = record_button;
		else
			recordLocator = record_button_tab;
		String recordButtonText = commonUtils.getText(recordLocator);
		// Method for verifying if the ongoing program is series or non-series. For
		// series program record button will be updated to manage recording. Also a
		// recording pop-up will be displayed on selecting the record button. For
		// non-series program there will be no recording pop-up and button will be
		// updated as stop recording (for ongoing program)/ cancel recording (upcoming
		// program)

		if (recordButtonText == null)
			return false;

		System.out.println("recordButtonText " + recordButtonText);
		if (recordButtonText.equalsIgnoreCase(inputProperties.getManageRecording()))
			programIsSeries = true;
		else if (recordButtonText.equalsIgnoreCase(inputProperties.getStopRecording())) {
			programIsSeries = false;
		} else if (recordButtonText.equalsIgnoreCase(inputProperties.getRecord())) {
			commonUtils.clickonElement(recordLocator);
			if (commonUtils.displayed(recording_pop_up)) {
				if (commonUtils.displayed(record_series)) {
					programIsSeries = true;
					// Closing the recording pop-up
					commonUtils.clickonElement(cancel_recording_popup_option);
				} else {
					programIsSeries = false;
					commonUtils.clickonElement(cancel_recording_popup_option);
				}
			}
			if (commonUtils.displayed(popup_dismiss)) {
				commonUtils.clickonElement(popup_dismiss);
			}
		}
		return programIsSeries;
	}

	public void close_program_details_page_to_reach_livetv() {
		commonUtils.clickonElement(close_button);
		try {
			commonUtils.waitTillVisibility(liveTV_skeleton, 20);
			Assert.assertTrue(commonUtils.displayed(liveTV_skeleton));
		} catch (Exception tabElementErr) {
			System.out.println("liveTV_skeleton not laoded or displayed");
		}
	}

	public void stop_recording_series(boolean isPastProgram) throws Throwable {
//	    	commonUtils.waitTillVisibility(player_screen, 20);
		boolean recordingStopped = false;
		commonUtils.clickonElement(record_button);
		commonUtils.waitTillVisibility(recording_pop_up, 5);
		List<MobileElement> recordingOptions = commonUtils.findElements(button_record_list);
		System.out.println("recordingOptions     " + recordingOptions.size());
		for (MobileElement option : recordingOptions) {
			if (!option.isDisplayed()) {
				continue;
			}
			System.out.println("(option.getText()   " + option.getText());
			if (option.getText().equalsIgnoreCase(inputProperties.getStopSeriesRecording())) {
				option.click();
				System.out.println("stop clicked");
//	    		tapOnYesPopUp();
				// Expected text is 'manage recording' but actual result is 'record'
				String expectedOutput = inputProperties.getManageRecording();
				if (isPastProgram)
					expectedOutput = inputProperties.getRecordSeries();
				Thread.sleep(30000);
//	    		commonUtils.waitTillTextChanges(expectedOutput, record_button_text);
				recordingStopped = true;
				break;
			}
		}
		if (!recordingStopped)
			throw new TestException(String.format("'Stop episode recording' option not found"));
	}



	public boolean is_program_replayable() {

		boolean replayable = false;
		user_on_program_details_page();
		if (commonUtils.findElements(details_page_replay_icon).size() > 0)
			replayable = true;
		System.out.println("is_program_replayable " + replayable);
		return replayable;
	}

	public boolean verify_if_completed_program_is_part_of_series() {
		boolean isSeries = false;
		// For completed programs if it is series, there will be record button and if
		// non-series there will be no record button
		if (commonUtils.displayed(record_button) || commonUtils.displayed(record_button_tab))
			isSeries = true;
		System.out.println("verify_if_completed_program_is_part_of_series  " + isSeries);
		return isSeries;
	}

	public boolean verify_if_upcoming_program_is_part_of_series() {
		boolean programIsSeries = false;
		By recordLocator = null;
		if (!commonUtils.findElements(record_button).isEmpty())
			recordLocator = record_button;
		else
			recordLocator = record_button_tab;
		String recordButtonText = commonUtils.getText(recordLocator);
		System.out.println("recordButtonText  " + recordButtonText.toUpperCase());
		if (recordButtonText.isEmpty()) {
			System.out.println("No button");
			return false;
		}
		if (recordButtonText.equalsIgnoreCase(inputProperties.getManageRecording())
				|| recordButtonText.equalsIgnoreCase(inputProperties.getRecordSeries())) {
			programIsSeries = true;
		} else if (recordButtonText.equalsIgnoreCase(inputProperties.getCancelRecording()))
			programIsSeries = false;
		else if (recordButtonText.equalsIgnoreCase(inputProperties.getRecord())) {
			commonUtils.clickonElement(recordLocator);
			if (commonUtils.displayed(recording_pop_up)) {
				if (commonUtils.displayed(record_series)) {
					programIsSeries = true;
					// Closing the recording pop-up
					commonUtils.clickonElement(cancel_recording_popup_option);
				} else {
					programIsSeries = false;
					commonUtils.clickonElement(cancel_recording_popup_option);
				}
			}
			if (commonUtils.displayed(popup_dismiss)) {
				commonUtils.clickonElement(popup_dismiss);
			}
		}
		return programIsSeries;
	}
	
	@And("^Metadata properly displayed for non-series replay content$")
	public void metadata_properly_displayed_for_nonseries_replay_content() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(program_time));
		Assert.assertFalse(commonUtils.displayed(record_button));
		Assert.assertTrue(commonUtils.displayed(program_synopsis));
//		Assert.assertTrue(commonUtils.displayed(player_screen));
//		Assert.assertFalse(commonUtils.displayed(progress_bar));
//		Assert.assertTrue(commonUtils.enabled(channel_logo));
//		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	@And("Metadata properly displayed for series replay content$")
	public void metadata_properly_displayed_for_series_replay_content() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(program_time));
		if (commonUtils.displayed(record_button)) {
			Assert.assertTrue(commonUtils.displayed(record_button));
		} else {
			Assert.assertTrue(commonUtils.displayed(record_button_tab));
		}
		Assert.assertTrue(commonUtils.displayed(program_synopsis));
//		Assert.assertTrue(commonUtils.displayed(player_screen));
//		Assert.assertFalse(commonUtils.displayed(progress_bar));
//		Assert.assertTrue(commonUtils.enabled(channel_logo));
//		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	@Then("^Metadata properly displayed for ongoing program$")
	public void metadata_properly_displayed_for_ongoing_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertTrue(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(program_time));
//			Assert.assertTrue(commonUtils.displayed(player_screen));
//			Assert.assertTrue(commonUtils.displayed(live_icon));
//			Assert.assertTrue(commonUtils.displayed(channel_logo));
//			Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
	}

	@And("^Metadata properly displayed for non-playable live program$")
	public void metadata_properly_displayed_for_non_playable_live_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
		Assert.assertFalse(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(program_title));
		Assert.assertTrue(commonUtils.displayed(program_time));

//		Assert.assertTrue(commonUtils.displayed(player_screen));
//		Assert.assertTrue(commonUtils.displayed(channel_logo));
//		Assert.assertTrue(commonUtils.displayed(not_playable_icon));
//		Assert.assertTrue(commonUtils.displayed(live_icon));
//		Assert.assertFalse(commonUtils.displayed(Play_pause_button_under_player_screen));
	}

	@Then("^The user validates player control$")
	public void the_user_validates_player_control() throws InterruptedException {
		Thread.sleep(3000);
		check_streaming_Error();
		Assert.assertTrue(commonUtils.displayed(live_icon_player_screen));
		Assert.assertTrue(commonUtils.displayed(player_fullscreen));
		Assert.assertTrue(commonUtils.displayed(Play_pause_button_under_player_screen));
		// commonUtils.clickonElement(Play_pause_button_under_player_screen);
		Assert.assertTrue(commonUtils.displayed(progress_bar));
	}

	@Then("^The user validates player control in Replay plus subscription$")
	public void the_user_validates_player_control_in_replay_plus_subscription() throws InterruptedException {
		the_user_validates_player_control();
	}
	
	@And("^Metadata properly displayed for upcoming program$")
	public void metadata_properly_displayed_for_non_series_upcoming_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
//		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertFalse(commonUtils.displayed(progress_bar));
		Assert.assertTrue(commonUtils.displayed(program_title));
//		Assert.assertTrue(commonUtils.displayed(channel_logo));
		Assert.assertTrue(commonUtils.displayed(program_time));
		if (commonUtils.displayed(record_button)) {
			Assert.assertTrue(commonUtils.displayed(record_button));
		} else {
			Assert.assertTrue(commonUtils.displayed(record_button_tab));
		}
		Assert.assertTrue(commonUtils.displayed(program_synopsis));
		Assert.assertFalse(commonUtils.displayed(Play_pause_button_under_player_screen));
	}
	
	@Then("^Metadata properly displayed for non-playable upcoming program$")
	public void metadata_properly_displayed_for_non_playable_upcoming_program() {
		Assert.assertTrue(commonUtils.displayed(close_button));
//		Assert.assertTrue(commonUtils.displayed(player_screen));
		Assert.assertFalse(commonUtils.displayed(progress_bar));
//		Assert.assertTrue(commonUtils.displayed(not_playable_icon));
		Assert.assertTrue(commonUtils.displayed(program_title));
//		Assert.assertTrue(commonUtils.displayed(channel_logo));
		Assert.assertTrue(commonUtils.displayed(program_time));
		Assert.assertFalse(commonUtils.displayed(Play_pause_button_under_player_screen));
	}
	
//	@And("^The user rewind the video$")
//	public void the_user_rewind_the_video() {
//		String current_time = commonUtils.getText(live_elapsed_time);
//		System.out.println("current_time" + current_time);
//		commonUtils.clickonElement(Play_pause_button_under_player_screen);
//		commonUtils.clickonElement(epg_live_rewind_button);
//		String after_rewind_time = commonUtils.getText(live_elapsed_time);
//		Assert.assertTrue(commonUtils.displayed(goto_live_icon));
//		Assert.assertFalse(commonUtils.enabled(epg_live_forward_button));
//		System.out.println("after_rewind_time" + after_rewind_time);
////		if (commonUtils.displayed(epg_goto_live_icon))
////			commonUtils.clickonElement(epg_goto_live_icon);
//		float current_time_in_sec = (Float.parseFloat((current_time.split(":")[0])) * 60)
//				+ (Float.parseFloat((current_time.split(":")[1])) * 60);
//		float after_rewind_time_in_sec = (Float.parseFloat((after_rewind_time.split(":")[0])) * 60)
//				+ (Float.parseFloat((after_rewind_time.split(":")[1])) * 60);
//		System.out.println("number is  " + current_time_in_sec);
//		System.out.println("number is  " + after_rewind_time_in_sec);
//		boolean time_difference = current_time_in_sec >= after_rewind_time_in_sec;
//		System.out.println("time_difference  " + time_difference);
//		commonUtils.clickonElement(live_vidoe_playing_container);
//	}
}
