package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Program_Details_Screen {

		By recording_pop_up = MobileBy.AccessibilityId("record_options_list");
		By close_button = MobileBy.AccessibilityId("player close");
//	    By option_in_recording_pop_up = By.xpath("//XCUIElementTypeSheet[@name=\"record_options_list\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
	  By option_in_recording_pop_up = By.xpath("XCUIElementTypeButton");
	    By button_record_list = By.xpath("//XCUIElementTypeButton");
	    By recording_icon = MobileBy.AccessibilityId("series_recording");
	    By program_title = MobileBy.AccessibilityId("AiringDetailView_titleLabel");
	    By program_time = MobileBy.AccessibilityId("AiringDetailView_airingTimeLabel");
	    By ongoing_program_title = MobileBy.AccessibilityId("series_recording");
	    By program_logo = MobileBy.AccessibilityId("AiringDetailView_channelLogo");
	    By program_recording_icon = MobileBy.AccessibilityId("AiringDetailView_recordingIcon");
	    By program_HD_icon = MobileBy.AccessibilityId("AiringDetailView_hdIcon");
	    By SeriesDetailViewController_backButton = MobileBy.AccessibilityId("SeriesDetailViewController_backButton");
	     
	    By ongoing_program_time = MobileBy.AccessibilityId("series_recording");
	    
	    By record_series=MobileBy.AccessibilityId("Record series");
	    By record_episode=MobileBy.AccessibilityId("Record this episode");
	    

	    //By play_button_under_player_screen = MobileBy.AccessibilityId("player play");
	    //By pause_button_under_player_screen = MobileBy.AccessibilityId("BottomControlsView_playButton");
	    By Play_pause_button_under_player_screen = MobileBy.AccessibilityId("BottomControlsView_playButton");
	    By live_icon_player_screen = MobileBy.AccessibilityId("BottomControlsView_durationIndicatorButton");
	    By player_fullscreen = MobileBy.AccessibilityId("BottomControlsView_fullScreenButton");
	    By progress_bar = MobileBy.AccessibilityId("BottomControlsView_progressBar");
//	    By player_screen = MobileBy.id("be.belgacom.mobile.tveverywhere:id/meta_thumbnail");

//	    By recording_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/metadata_icon_id_recording");
	By record_this_episode_option = MobileBy.AccessibilityId("Record this episode");
	By record_series_option = MobileBy.AccessibilityId("Record series");
	By cancel_recording_popup_option = MobileBy.AccessibilityId("Cancel");
	By stop_recording_episode_popup_option = MobileBy.AccessibilityId("Stop recording");
	By popup_dismiss = MobileBy.AccessibilityId("PopoverDismissRegion");
//	    -------------------------------------------------------------------------------------------------
	By player_screen = MobileBy.xpath(
			"//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
	By details_page_replay_icon = MobileBy.AccessibilityId("AiringDetailView_replayIcon");
	By record_button = MobileBy.AccessibilityId("AiringDetailView_recordButton");
	By record_button_tab = MobileBy.AccessibilityId("AiringViewController_recordButton");
	By channel_logo = MobileBy.AccessibilityId("channel_logo_in_program_details_page");
	By program_synopsis = MobileBy.AccessibilityId("AiringDetailView_descriptionLabel");
	By record_button_text = MobileBy.AccessibilityId("AiringDetailView_recordButton");

//	    By ongoing_channel_logo = MobileBy.id("");
////	    By live_icon = MobileBy.id("");
//	    By ongoing_program_record_button = MobileBy.id("");
//	    By not_playable_icon = MobileBy.id("");
}
