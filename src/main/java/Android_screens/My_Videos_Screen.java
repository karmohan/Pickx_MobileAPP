package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface My_Videos_Screen {

    By my_videos_tab_layout = MobileBy.id("be.belgacom.mobile.tveverywhere:id/tablayout_my_videos");
    By my_videos_recordings_option = MobileBy.AccessibilityId("Recordings");
    By my_videos_recordings_playable_tab = MobileBy.id("be.belgacom.mobile.tveverywhere:id/my_videos_filter_playable");
    By my_videos_recordings_recorded_tab = MobileBy.id("be.belgacom.mobile.tveverywhere:id/my_videos_filter_recorded");
    By my_videos_recordings_planned_tab = MobileBy.id("be.belgacom.mobile.tveverywhere:string/filter_planned");
    By my_videos_recordings_programs = MobileBy.xpath(
	    "//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_recordings']/android.view.ViewGroup");
    By my_videos_recordings_program_poster = MobileBy
	    .id("be.belgacom.mobile.tveverywhere:id/imageview_recording_thumbnail");
    By my_videos_recordings_program_channel_icon = MobileBy
	    .id("be.belgacom.mobile.tveverywhere:id/imageview_logo_small");
    By my_videos_recordings_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_recording_title");
    By my_videos_recordings_program_other_option = MobileBy
	    .id("be.belgacom.mobile.tveverywhere:id/button_recordings_other_options");
    By my_videos_recordings_program_description = MobileBy
	    .id("be.belgacom.mobile.tveverywhere:id/textview_recorded_description");
    By my_videos_recordings_program_recorded_icon = MobileBy
	    .id("be.belgacom.mobile.tveverywhere:id/imageview_icon_recording");
    By my_videos_recording_item = MobileBy.xpath("//android.view.ViewGroup[2]");
    By my_videos_continue_watching_option = MobileBy.AccessibilityId("Continue watching");
    By my_videos_recordings_tiles_series_play = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_recordings']/android.view.ViewGroup[2]");
    By my_videos_recording_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recording_meta_title");
    By my_videos_recording_program_time = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recording_meta_info");
    By my_videos_video_container = MobileBy.id("be.belgacom.mobile.tveverywhere:id/video_renderer");
    By SeriesDetailViewController_backButton = MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageButton");
    By my_videos_continue_watching_item = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_continue_watching_item_thumbnail");
    By continue_watching_item_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_title");
    By continue_watching_single_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recording_meta_title");
    By downloaded_programs = MobileBy.id("be.belgacom.mobile.tveverywhere:id/root");
    By title_of_downloaded_video = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_download_title");
    By downloaded_content_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/download_meta_title");
    By downloaded_content_metadata = MobileBy.id("be.belgacom.mobile.tveverywhere:id/download_meta_info");
    By downloaded_content_delete_download = MobileBy.id("be.belgacom.mobile.tveverywhere:id/download_meta_options_button");
    By my_videos_downloaded_item_play_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_play_pause_toggle");
    By my_videos_continue_watching_item_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_continue_watching_item_title");
    By option_in_download_page = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_download_options");
    By age_symbol= MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_age");
    By download_symbol = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_download_state");		
    By program_displayed_onDemand = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_search']/android.view.ViewGroup");		
    By program_title_of_searched_text = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_title");
    
}

