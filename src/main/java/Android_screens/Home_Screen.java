package Android_screens;

import org.openqa.selenium.By;


import io.appium.java_client.MobileBy;


public interface Home_Screen {

	By home_button= MobileBy.AccessibilityId("Home");
	By liveTV_button= MobileBy.AccessibilityId("Live TV");
	By tvGuide_button= MobileBy.AccessibilityId("TV guide");
	By Recordings= MobileBy.AccessibilityId("Recordings");
	By swimlane_container = MobileBy.id("be.belgacom.mobile.tveverywhere:id/hub_swimlanes_container");
	By HomeScreen_layout = By.id("be.belgacom.mobile.tveverywhere:id/framelayout");
	By HomeScreen_layout_full = By.id("be.belgacom.mobile.tveverywhere:id/scrollview_hub");
	By HomeScreen_horizandal_scrollview = By.id("be.belgacom.mobile.tveverywhere:id/scrollview_category");
	By swimlane_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_swimlane_title");
	By live_icon_in_swimlane = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_label");
	By swimlane_program_poster = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_still");
	By program_title_under_swimlane = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_title");
        By swimlane_container_without_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recyclerview_swimlane");
	By replay_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_replay");
	By program_subtitle_under_swimlane = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_subtitle");
	By program_in_swimlane = MobileBy.xpath(
			"//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_swimlane']/android.view.ViewGroup");
	By swimlane_tiles = MobileBy.xpath(
			"//*[@resource-id='be.belgacom.mobile.tveverywhere:id/hub_swimlanes_container']/android.view.ViewGroup");
	By hero_banner_container = By.id("be.belgacom.mobile.tveverywhere:id/hero_banner_container");
	By hero_banner_channel_logo = By.id("be.belgacom.mobile.tveverywhere:id/imageview_swimlane_banner_channel_logo");
	By textview_hero_title = By.id("be.belgacom.mobile.tveverywhere:id/textview_swimlane_title");
	By textview_hero_metadata = By.id("be.belgacom.mobile.tveverywhere:id/textview_swimlane_metadata");
	By hero_banner_watch_button = By.id("be.belgacom.mobile.tveverywhere:id/button_swimlane_banner_watch");
	By hero_banner_icon_container = By.id("be.belgacom.mobile.tveverywhere:id/ll_swimlane_banner_icon_container");
	By channel_icon = By.id("be.belgacom.mobile.tveverywhere:id/imageview_logo_small");
	//By swimlane_view_all_button = By.id("be.belgacom.mobile.tveverywhere:id/button_swimlane_viewall");
	By swimlane_view_all_button_1 = By.xpath("//*[@text='All']");
	By home_category_container = By.id("be.belgacom.mobile.tveverywhere:id/linearlayout_category_container");
	By home_categories = By.id("be.belgacom.mobile.tveverywhere:id/button_category");
	By Recommended_in_Movies_and_Series = By.xpath("//*[@text='Recommended in Movies & Series']");
	By recording_icon_in_homescreen = By.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_recording");
	By my_videos_button = MobileBy.AccessibilityId("My videos");
	By movie_swimlane = MobileBy.xpath(
			"//*[@resource-id='be.belgacom.mobile.tveverywhere:id/swimlanecontainer_categorypage']/android.view.ViewGroup");
	By player_screen_swimlane = MobileBy.id("be.belgacom.mobile.tveverywhere:id/meta_thumbnail");
	By replay_icon_details = MobileBy.id("be.belgacom.mobile.tveverywhere:id/metadata_icon_id_replay");
}
