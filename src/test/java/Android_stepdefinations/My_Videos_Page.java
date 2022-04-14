package Android_stepdefinations;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.TestException;

import Android_screens.LiveTV_Screen;
import Android_screens.Program_Details_Screen;
import Android_screens.Search_screen;
import Android_screens.Setting_Screen;
import Android_screens.Swimlane_Contents_Screen;
import Android_screens.TVguide_Screen;
import Android_screens.Vod_Details_Screen;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class My_Videos_Page
		implements Android_screens.Home_Screen, Android_screens.My_Videos_Screen, Program_Details_Screen,
		TVguide_Screen, Setting_Screen, Search_screen, Vod_Details_Screen, LiveTV_Screen, Swimlane_Contents_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public String Program_Title_Value;
	public String Program_Title_Value_in_continue_watching;
	public String Bottom_navigation_MyVideos_text;
	public String Recordings_text;
	public String Continue_watching_text;
	public Setting_Page settingPage;
	public Search_page searchPage;
	public Vod_Details_Page vod_details_page;
	public Program_Details_Page programDetalsPage;
	public Player_page playerpage;
	public String downloadedContentTitle;

	public My_Videos_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		settingPage = new Setting_Page();
		searchPage = new Search_page();
		vod_details_page = new Vod_Details_Page();
		programDetalsPage = new Program_Details_Page();
		playerpage = new Player_page();
	}

	@Given("^User is on my videos page$")
	public void user_is_on_my_videos_page() {
		Bottom_navigation_MyVideos_text = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_MyVideos");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(Bottom_navigation_MyVideos_text));
		commonUtils.waitTillVisibility(my_videos_tab_layout, 20);
		Assert.assertTrue(commonUtils.displayed(my_videos_tab_layout));
	}

	@When("^User selects Recordings tab in my videos$")
	public void user_selects_Recordings_tab_in_my_videos() throws InterruptedException {
		Recordings_text = commonUtils.getTextBasedonLanguage_Android("Recordings_text");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(Recordings_text));
		Thread.sleep(5000);
		commonUtils.waitTillVisibility(my_videos_recordings_playable_tab, 20);
		Assert.assertTrue(commonUtils.displayed(my_videos_recordings_playable_tab));
	}

	@And("^User selects Playable tab in recordings of my videos$")
	public void user_selects_playable_tab_in_recordings_of_my_videos() {
		commonUtils.clickonElement(my_videos_recordings_playable_tab);
	}

	@And("^Validate recordings tab in my videos$")
	public void validate_recordings_tab_in_my_videos() throws InterruptedException {
		Recordings_text = commonUtils.getTextBasedonLanguage_Android("Recordings_text");
		Assert.assertTrue(commonUtils.selected(commonUtils.findElementByAccessibilityid(Recordings_text)));
		Thread.sleep(5000);
		commonUtils.waitTillVisibility(my_videos_recordings_playable_tab, 20);
		Assert.assertTrue(commonUtils.displayed(my_videos_recordings_playable_tab));
		Assert.assertTrue(commonUtils.displayed(my_videos_recordings_recorded_tab));
		Assert.assertTrue(commonUtils.displayed(my_videos_recordings_planned_tab));
	}

	@And("^Validate playable tab in recordings of my videos$")
	public void validate_playable_tab_in_recordings_of_my_videos() {
		try {
			commonUtils.findElements(my_videos_recordings_programs);
			Thread.sleep(5000);
			commonUtils.waitTillVisibility(my_videos_recordings_program_poster, 20);
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_poster));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_channel_icon));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_title));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_other_option));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_description));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_recorded_icon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^User selects Recorded tab in recordings of my videos$")
	public void user_selects_recorded_tab_in_recordings_of_my_videos() {
		commonUtils.clickonElement(my_videos_recordings_recorded_tab);
	}

	@And("^Validate recorded tab in recordings of my videos$")
	public void validate_recorded_tab_in_recordings_of_my_videos() {
		try {
			commonUtils.findElements(my_videos_recordings_programs);
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_poster));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_channel_icon));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_title));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_other_option));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_description));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_recorded_icon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^User selects Planned tab in recordings of my videos$")
	public void user_selects_planned_tab_in_recordings_of_my_videos() {
		commonUtils.clickonElement(my_videos_recordings_planned_tab);
	}

	@And("^Validate planned tab in recordings of my videos$")
	public void validate_planned_tab_in_recordings_of_my_videos() {
		try {
			commonUtils.findElements(my_videos_recordings_programs);
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_poster));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_channel_icon));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_title));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_other_option));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_description));
			Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_recorded_icon));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("^User selects Continue Watching tab in my videos$")
	public void user_selects_continue_watching_tab_in_my_videos() {
		Continue_watching_text = commonUtils.getTextBasedonLanguage_Android("Continue_watching");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(Continue_watching_text));
	}

	@Given("^User play a video in recording tab$")
	public void user_play_a_video_in_recording_tab() throws InterruptedException {
		validate_recordings_tab_in_my_videos();
		user_selects_playable_tab_in_recordings_of_my_videos();
		validate_playable_tab_in_recordings_of_my_videos();
		commonUtils.clickonElement(my_videos_recordings_program_poster);
		if (!commonUtils.displayed(my_videos_recordings_tiles_series_play)) {
			System.out.println("Clicked on episode....");
		} else {
			commonUtils.clickonElement(my_videos_recordings_tiles_series_play);
			System.out.println("Clicked on episode....");
		}
	}

	@When("^The user see the streaming video$")
	public void the_user_see_the_streaming_video() throws InterruptedException {
		Assert.assertTrue(commonUtils.displayed(play_pause_button_on_player_screen));
		commonUtils.clickonElement(play_pause_button_on_player_screen);
		if (commonUtils.displayed(streamNotAvailable_TryAgain)) {
			commonUtils.clickonElement(streamNotAvailable_TryAgain);
		}
		Program_Title_Value = Program_Title();
		String Program_time_and_details = commonUtils.getAttribute(my_videos_recording_program_time, "text");
		System.out.println("Program time and details  " + Program_time_and_details);
		waitOnemins();
	}

	public String Program_Title() {
		String Program_Title_Value = commonUtils.getAttribute(my_videos_recording_program_title, "text");
		System.out.println("Program title value  " + Program_Title_Value);
		return Program_Title_Value;
	}

	public void waitOnemins() throws InterruptedException {
		int counter = 0;
		while (counter < 6) {
			Thread.sleep(12000);
			commonUtils.clickonElement(my_videos_video_container);
			System.out.println("The counter value of wait time  " + counter);
			counter++;
		}
	}

	@And("User navigates to continue watching the page")
	public void user_navigates_to_continue_watching_the_page() {
		commonUtils.clickonElement(close_button);
		/*
		 * if(!commonUtils.displayed(my_videos_continue_watching_option)) {
		 * commonUtils.clickonElement(SeriesDetailViewController_backButton); }
		 */
		commonUtils.clickonElement(SeriesDetailViewController_backButton);
		Continue_watching_text = commonUtils.getTextBasedonLanguage_Android("Continue_watching");
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(Continue_watching_text)));
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(Continue_watching_text));
	}

	@Then("User see the same program is available in continue watching")
	public void user_see_the_same_program_is_available_in_continue_watching() throws InterruptedException {
		commonUtils.clickonElement(my_videos_continue_watching_item);
		if (commonUtils.displayed(parental_pin_input)) {
			settingPage.enter_parental_pin_for_programs();
		}
		if (!commonUtils.displayed(continue_watching_item_title)) {
			Program_Title_Value_in_continue_watching = commonUtils.getAttribute(continue_watching_single_program_title,
					"text");
		} else {
			Program_Title_Value_in_continue_watching = commonUtils.getAttribute(continue_watching_item_title, "text");
		}
		System.out.println("Program title value in Continue Watching  " + Program_Title_Value_in_continue_watching);
		Assert.assertEquals(Program_Title_Value, Program_Title_Value_in_continue_watching);
	}

	@When("User navigates to {string} tab")
	public void user_navigates_to_tab(String string) {
		Assert.assertTrue(commonUtils.displayed(commonUtils
				.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage_Android("Downloads_text"))));
		commonUtils.clickonElement(
				commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage_Android("Downloads_text")));
		if (commonUtils.displayed(downloaded_programs)) {
			System.out.println("Downloaded programs are there under MyVideos Downloads");
			// user_verify_the_meta_details_for_the_downloaded_program();
			Assert.assertTrue(commonUtils.displayed(title_of_downloaded_video));
			Assert.assertTrue(commonUtils.displayed(download_symbol));
		} else {
			System.out.println("No downloaded video is available");
		}
	}

	@When("User check the {string} tab is highlighted")
	public void Use_check_the_tab_is_highlighted(String myvideo_tab) {
		if (myvideo_tab.equalsIgnoreCase("Download")) {
			// commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage_Android("Downloads_text"))
			Assert.assertTrue(commonUtils.selected(commonUtils
					.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage_Android("Downloads_text"))));
			// driver.findElementByAccessibilityId(commonUtils.getTextBasedonLanguage_Android("Downloads_text")).isSelected();
			// Assert.assertTrue(commonUtils.displayed(downloaded_tab_highligted));
		} else if (myvideo_tab.equalsIgnoreCase("Recording")) {
			Assert.assertFalse(commonUtils.selected(commonUtils
					.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage_Android("Recordings_text"))));
		} else if (myvideo_tab.equalsIgnoreCase("Continue Watching")) {
			Assert.assertFalse(commonUtils.selected(commonUtils
					.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage_Android("Continue_watching"))));
		}
	}

	@Given("User scroll down in the page")
	public void user_scroll_down_in_the_page() {
		commonUtils.swipeUpOverScreen();
	}

	@Given("User scroll up in the page")
	public void user_scroll_up_in_the_page() {
		commonUtils.swipeDownScreen();
	}

	@Given("User select the SVOD content with the download option")
	public void user_select_the_svod_content_with_the_download_option() throws InterruptedException {
		searchPage.the_user_is_on_search_page();
		searchDownloadabledProgram("DownloadableMovie");
//	    	if(!commonUtils.displayed(download_button)) {
//	    		commonUtils.clickonElement(navigate_back);
//	    		commonUtils.clickonElement(search_x_button_id);
//	    		searchDownloadabledProgram("DownloadableMovie_second");
//	    	}
		if (!commonUtils.displayed(synopsis)) {
			searchDownloadabledProgram("DownloadableMovie");
		}
		vod_details_page.metadata_properly_displayed_for_programs();
		Assert.assertTrue(commonUtils.displayed(download_button));
	}

	@Given("User taps the download button")
	public void user_taps_the_download_button() throws InterruptedException {
		Assert.assertTrue(commonUtils.displayed(download_button));
		downloadedContentTitle = commonUtils.getText(vod_program_title);
		System.out.println("downloadedContentTitle" + downloadedContentTitle);
		commonUtils.clickonElement(download_button);
	}

	@Then("User verify the download spinner is displayed")
	public void user_verify_the_download_spinner_is_displayed() throws InterruptedException {
		Assert.assertTrue(commonUtils.displayed(download_spinner));
	}

	@Then("User waits until download is finished")
	public void user_waits_until_download_is_finished() throws InterruptedException {
		commonUtils.waitTillpresenceOfElement(commonUtils.findElementByXpathValueName("text",
				commonUtils.getTextBasedonLanguage_Android("Go_To_Downloads")), 600);
	}

	@Then("User taps the \"Go to download\" button")
	public void user_taps_the_go_to_download_button() throws InterruptedException {

		commonUtils.clickonElement(commonUtils.findElementByXpathValueName("text",
				commonUtils.getTextBasedonLanguage_Android("Go_To_Downloads")));
	}

	@Then("User verify the downloaded asset")
	public void user_verify_the_downloaded_asset() throws InterruptedException {

		Assert.assertTrue(commonUtils.displayed(downloaded_content_title));
		System.out.println(commonUtils.getText(downloaded_content_title));
		Assert.assertEquals(downloadedContentTitle, commonUtils.getText(downloaded_content_title));
	}

	@Then("User verify the cancel download button is displayed")
	public void user_verify_the_cancel_download_button_is_displayed() throws InterruptedException {
		commonUtils.waitTillVisibility(cancel_download, 120);
		Assert.assertTrue(commonUtils.displayed(cancel_download));
	}

//	public void searchDownloadabledProgram(String movieName) throws InterruptedException {
//		searchPage.the_user_enters_characters_in_search_box(movieName);
//		searchPage.keyboardStatus();
//		Thread.sleep(1000);
//		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
//
//	}
	
	public void searchDownloadabledProgram(String movieName) throws InterruptedException {
		searchPage.the_user_enters_characters_in_search_box(movieName);
		searchPage.keyboardStatus();
		Thread.sleep(1000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));		
		List<String> categoriesDisplayed = searchPage.search_page_categories_are_displayed();		
		driver.findElement(By.xpath("//*[contains(@text,'" + categoriesDisplayed.get(1) + "')]")).click();		
		List<MobileElement> swimlanes = commonUtils.findElements(program_displayed_onDemand);		
		System.out.println("Number of program listed  " + swimlanes.size());
		for (int i=0;i<swimlanes.size();i++) {	
			System.out.println("Movie text    " + swimlanes.get(i).findElement(program_title_of_searched_text).getText());			
			if (swimlanes.get(i).findElement(program_title_of_searched_text).getText().contains(commonUtils.getTextBasedonLanguage(movieName))) {
				swimlanes.get(i).findElement(program_title_of_searched_text).click();				
				if(!commonUtils.displayed(download_button)) {
					commonUtils.clickonElement(navigate_back);
		    		commonUtils.clickonElement(search_x_button_id);					
					continue;
				}
				else {
					break;
				}
			}
			else {
				continue;
			}
		}
	}
	

	@And("User verify the meta details for the downloaded program")
	public void user_verify_the_meta_details_for_the_downloaded_program() {
		Assert.assertTrue(commonUtils.displayed(option_in_download_page));
		Assert.assertTrue(commonUtils.displayed(download_symbol));
		// Assert.assertTrue(commonUtils.displayed(age_symbol));
		Assert.assertTrue(commonUtils.displayed(title_of_downloaded_video));
		// Assert.assertTrue(commonUtils.displayed(metadata_of_downloaded_video));
	}

	@And("User taps on the downloaded content")
	public void user_taps_on_the_downloaded_content() {
		List<MobileElement> downloaded_program = commonUtils.findElements(downloaded_programs);
		downloaded_program.get(1).click();
	}

	@Then("User verify confirmation pop up")
	public void user_verify_confirmation_pop_up() {

	}

	@Then("User cancels confirmation pop up and download resumes")
	public void user_cancels_confirmation_pop_up_and_download_resumes() {

	}

	@Then("User taps on cancel download button")
	public void user_taps_on_cancel_download_button() {
		commonUtils.clickonElement(cancel_download);

	}

	@Then("User confirms cancel of download")
	public void user_confirms_cancel_of_download() {

	}

	@Then("User verify download is stopped")
	public void user_verify_download_is_stopped() {

	}

	@Then("User verify stopped download is not displayed in downloads overview")
	public void user_verify_stopped_download_is_not_displayed_in_downloads_overview() {
	}

	@Then("User verify the meta details for the downloaded program in SVOD details page")
	public void user_verify_the_meta_details_for_the_downloaded_program_in_svod_details_page() {
		Assert.assertTrue(commonUtils.displayed(downloaded_content_title));
		Assert.assertTrue(commonUtils.displayed(downloaded_content_metadata));
		Assert.assertTrue(commonUtils.displayed(downloaded_content_delete_download));
		Assert.assertTrue(commonUtils.displayed(my_videos_downloaded_item_play_icon));
		// System.out.println("This will be implemneted after complete implementation of
		// D2G");
	}

	@And("User choose the downloaded video to play")
	public void user_choose_the_downloaded_video_to_play() throws InterruptedException {
		List<MobileElement> downloaded_program = commonUtils.findElements(downloaded_programs);
		downloaded_program.get(0).click();
		programDetalsPage.program_starts_streaming();
		downloadedContentTitle = commonUtils.getText(vod_program_title);
		Thread.sleep(3000);
	}

	@Given("User select the SVOD content which is already downloaded")
	public void user_select_the_SVOD_content_which_is_already_downloaded() throws InterruptedException {

		searchPage.the_user_is_on_search_page();
		String movieTitle = commonUtils.getTextBasedonLanguage_Android("DownloadableMovie");
		searchDownloadabledProgram("DownloadableMovie");
		if (!commonUtils.displayed(download_button)) {
			commonUtils.clickonElement(navigate_back);
			commonUtils.clickonElement(search_x_button_id);
			searchDownloadabledProgram("DownloadableMovie_second");
			movieTitle = commonUtils.getTextBasedonLanguage_Android("DownloadableMovie_second");

		}
		select_the_asset(movieTitle);
		vod_details_page.metadata_properly_displayed_for_programs();
		commonUtils.findElementByXpathValueName("text", commonUtils.getTextBasedonLanguage_Android("Go_To_Downloads"));

	}

	private void select_the_asset(String movieTitle) {
		List<MobileElement> swimlanes = commonUtils.findElements(search_program_title);
		for (MobileElement swimlane : swimlanes) {
			if (swimlane.getText().contains(movieTitle)) {
				swimlane.click();
				break;
			} else {
				continue;
			}
		}
	}

	@Then("User plays the content")
	public void user_plays_the_content() throws InterruptedException {
		programDetalsPage.program_starts_streaming();
		Thread.sleep(3000);

	}

	@Then("The user validate player controls of the content")
	public void the_user_validate_player_controls_of_a_download() throws InterruptedException {
		// Assert.assertTrue(commonUtils.displayed(parental_seek_bar));
		// Assert.assertTrue(commonUtils.displayed(epg_live_mute_button));
		// commonUtils.clickonElement(epg_live_mute_button);
		playerpage.user_able_to_frw_and_able_ffr_the_video();
		if (!commonUtils.displayed(live_fullscreen)) {
			commonUtils.clickonElement(live_vidoe_playing_container);
		}
		commonUtils.clickonElement(play_pause_button_on_player_screen);
		Assert.assertTrue(commonUtils.displayed(live_fullscreen));
		commonUtils.clickonElement(live_fullscreen);
		ScreenOrientation orientation_afterFullScreen = driver.getOrientation();
		System.out.println("Orientation after FullScreen " + orientation_afterFullScreen);
	}

	@Then("User moves the slider to the {string} of the stream")
	public void user_moves_the_slider_across_the_stream(String position) {

		if (position.equalsIgnoreCase("middle")) {
			System.out.println("Moving slider to middle");

		}

	}

	@Then("User verify progress is reset to beginning")
	public void user_verify_progress_is_reset_to_beginning() {

	}

	@Then("User exits playback and verify progress bar is displayed")
	public void user_exits_playback_and_verify_progress_bar_is_displayed() {

		commonUtils.clickonback();

	}

	@Then("User verify if the progress bar is displayed")
	public void user_verify_if_the_progress_bar_is_displayed() {

	}

	@Then("User verify previously watched video is not available in CW tab")
	public void user_verify_previously_watched_video_is_not_available_in_cw_tab() {

		List<MobileElement> cwItems = commonUtils.findElements(my_videos_continue_watching_item_title);
		for (MobileElement cwitem : cwItems) {
			Assert.assertFalse(cwitem.getText().equals(downloadedContentTitle));

		}

	}

	@When("User navigates to homescreen")
	public void user_navigates_to_homescreen() {
		commonUtils.clickonElement(home_button);

	}

	@Then("User verify previously watched video is not available in CW SL")
	public void user_verify_previously_watched_video_is_not_available_in_cw_sl() {

		int counter = 0;
		boolean found = false;
		List<MobileElement> swimlaneList = null;
		while (counter < 5) {
			swimlaneList = commonUtils.findElements(swimlane_tiles);
			try {
				System.out.println(swimlaneList.get(0).findElement(swimlane_title).getText());
				if (swimlaneList.get(0).findElement(swimlane_title).getText().equalsIgnoreCase("Continue watching"))
					if (swimlaneList.get(0).findElement(program_title_under_swimlane).getText()
							.equalsIgnoreCase(downloadedContentTitle)) {
						found = true;
						break;
					}
			} catch (Exception e) {
				System.out.println("catch ");
				if (swimlaneList.get(1).findElement(swimlane_title).getText().equalsIgnoreCase("Continue watching"))
					if (swimlaneList.get(1).findElement(program_title_under_swimlane).getText()
							.equalsIgnoreCase(downloadedContentTitle)) {
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

	@Given("User select the SVOD content which is non-playable")
	public void user_select_the_svod_content_which_is_non_playable() throws InterruptedException {

		searchPage.the_user_is_on_search_page();
		searchDownloadabledProgram("NonPlayableMovie");
		driver.findElement(By.xpath("//*[contains(@text,'"
				+ commonUtils.getTextBasedonLanguage_Android("search_categories_onDemand") + "')]")).click();
		select_the_asset(commonUtils.getTextBasedonLanguage_Android("NonPlayableMovie"));
		Assert.assertFalse(commonUtils.displayed(not_playable_icon));
	}

	@Then("User verify download button is not displayed")
	public void user_verify_download_button_is_not_displayed() {
		Assert.assertFalse(commonUtils.displayed(download_button));

	}

	@Then("User selects a recorded content")
	public void user_selects_a_recorder_content() {
		commonUtils.clickonElement(my_videos_recordings_program_title);
		commonUtils.clickonElement(my_videos_recording_item);

	}

	@Then("User goes back to My videos page")
	public void user_goes_back_to_My_videos_page() {
		commonUtils.clickonback();
		if (!commonUtils.displayed(my_videos_tab_layout))
			commonUtils.clickonback();

	}

	@When("User taps on delete recording button")
	public void user_taps_on_delete_recording_button() {
		downloadedContentTitle = commonUtils.getText(downloaded_content_title);
		commonUtils.clickonElement(commonUtils.findElementByXpathContains("text",
				commonUtils.getTextBasedonLanguage_Android("Delete_download")));

	}

	@Then("User is redirected to Downloads tab")
	public void user_is_redirected_to_downloads_tab() {
		user_verify_the_meta_details_for_the_downloaded_program();

	}

	@Then("User verify whether undo toast is diplayed")
	public void user_verify_whether_undo_toast_is_diplayed() {

	}

	@Then("User taps on Undo toast")
	public void user_taps_on_undo_toast() {

	}

	@Then("User verify delete is cancelled and item is not removed from list")
	public void user_verify_delete_is_cancelled_and_item_is_not_removed_from_list() {

	}

	@Then("User verify if the toast dissapears and the item is removed from list")
	public void user_verify_if_the_toast_dissapears_and_the_item_is_removed_from_list() {

	}

}
