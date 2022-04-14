package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface LiveTV_Screen {
	
	By replay_button = By.id("be.belgacom.mobile.tveverywhere:id/all_channels_ic_replay");
	By toolbar = By.id("be.belgacom.mobile.tveverywhere:id/toolbar");
	By live_now = By.id("be.belgacom.mobile.tveverywhere:id/channel_meta_live");
	By liveTV_skeleton = By.id("be.belgacom.mobile.tveverywhere:id/recyclerview_overview");
	By Navigate_up_button= MobileBy.AccessibilityId("Navigate up");
        By category_button_container = MobileBy.id("be.belgacom.mobile.tveverywhere:id/radio_group");
        By page_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/overview_title_textview");
        By categories = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/radio_group']/android.widget.RadioButton");
        By program = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_overview']/android.view.ViewGroup");
        By live_tv_program_poster = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_still");
        By live_tv_channel_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_logo_small");
        By live_tv_progress_bar = MobileBy.id("be.belgacom.mobile.tveverywhere:id/progress_bar");
        By live_tv_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_title");
        By live_tv_program_time = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_time");
        By program_description = MobileBy.id("be.belgacom.mobile.tveverywhere:id/layout_channel_description");
        By age_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_age");
        By lock_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_locked");
    	By live_elapsed_time = By.id("be.belgacom.mobile.tveverywhere:id/textview_elapsed_time");
    	By live_remaining_time = By.id("be.belgacom.mobile.tveverywhere:id/textview_remaining_time");
    	By live_fullscreen = By.id("be.belgacom.mobile.tveverywhere:id/toggle_fullscreen");
    	By live_loading_indicator = By.id("be.belgacom.mobile.tveverywhere:id/player_loading_indicator");
    	By goto_live_icon = By.id("be.belgacom.mobile.tveverywhere:id/button_goto_live");
    	By player_screen_live = MobileBy.id("be.belgacom.mobile.tveverywhere:id/meta_thumbnail");

}
