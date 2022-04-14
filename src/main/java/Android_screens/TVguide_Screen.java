package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface TVguide_Screen {
	By recyclerview_dates = By.id("be.belgacom.mobile.tveverywhere:id/recyclerview_dates");
	By toggle_date_button = By.id("be.belgacom.mobile.tveverywhere:id/imageview_toggle_date_button");
	By dateselector = By.id("be.belgacom.mobile.tveverywhere:id/datebuttonview_dateselector");
	By epg_view_meta_info_background = By.id("be.belgacom.mobile.tveverywhere:id/view_meta_info_background");
	By epg_channel_icon = By.id("[\"be.belgacom.mobile.tveverywhere:id/imageview_channel_icon\"][3]");
	By epg_channel_icon_end = By.id("[\"be.belgacom.mobile.tveverywhere:id/imageview_channel_icon\"][3]");
	By date_picker_textview_date = By.id("be.belgacom.mobile.tveverywhere:id/textview_date");
	By date_picker = By.id("be.belgacom.mobile.tveverywhere:id/touch_taget");
	By epg_video_container = By.id("be.belgacom.mobile.tveverywhere:id/recyclerview_channel");
	By epg_video_imageview_airing = By.id("be.belgacom.mobile.tveverywhere:id/imageview_airing");
	By epg_video_title = By.id("be.belgacom.mobile.tveverywhere:id/textview_title");
	By epg_video_broadcast_time = By.id("be.belgacom.mobile.tveverywhere:id/textview_broadcast_time");
	By epg_video_airing_details = By.id("be.belgacom.mobile.tveverywhere:id/textview_airing_details");
	By epg_video_replay_icon = By.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_replay");
	By epg_live_now_icon = By.id("be.belgacom.mobile.tveverywhere:id/textview_live");
	By epg_recording_icon = By.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_recording");
	By epg_live_video_container = By.id("be.belgacom.mobile.tveverywhere:id/meta_thumbnail");
	By epg_live_play_pause_icon = By.id("be.belgacom.mobile.tveverywhere:id/button_play_pause_toggle");
	By epg_live_mute_button = By.id("be.belgacom.mobile.tveverywhere:id/button_mute");
	By streamNotAvailable_TryAgain = By.id("be.belgacom.mobile.tveverywhere:id/button_error");
	By live_vidoe_playing_container = By.id("be.belgacom.mobile.tveverywhere:id/video_renderer");	
	By epg_live_rewind_button = By.id("be.belgacom.mobile.tveverywhere:id/clickable_space_disabled_rewind_button");
	By epg_live_forward_button = By.id("be.belgacom.mobile.tveverywhere:id/tintable_imageview_fast_forward_button_foreground");
	By navigate_back= MobileBy.AccessibilityId("Navigate up");
	By epg_date_picker_today= By.xpath("//*[contains(@text,'Today')]");
        By tv_guide_program_poster = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_airing");
        By channel_cell = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_channel_icons']/android.view.ViewGroup");
        By tv_guide_channel_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_channel_icon");
        By channel_selected_bar = MobileBy.id("be.belgacom.mobile.tveverywhere:id/view_selected_bar");
        By not_playable_icon_in_tvguide = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_not_playable");
        By date_field = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_date_button");
        By date_list = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_date");
        By replay_icon_in_tvguide = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_replay");
        By search_button=MobileBy.id("be.belgacom.mobile.tveverywhere:id/search_button");
        By search_text_field=MobileBy.id("be.belgacom.mobile.tveverywhere:id/search_src_text");
        By epg_program_tiles = By.xpath(
    	    	"//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_channel']/android.view.ViewGroup");
        By channel_number = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_channel_number");
//        By channel_icons_tab = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recyclerview_channel_icons");
        By channel_icons = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_channel_icon");
        By channel_layout = MobileBy.id("be.belgacom.mobile.tveverywhere:id/viewpager_channels");     
}



