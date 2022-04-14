package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Home_Screen {
	
	By bottom_navigation_bar = MobileBy.AccessibilityId("navmenu_tab_bar");
	By home_button = MobileBy.AccessibilityId("home_tab_bar_item");
	By liveTV_button = MobileBy.AccessibilityId("livetv_tab_bar_item");
	By tvGuide_button = MobileBy.AccessibilityId("tvguide_tab_bar_item");
	By Recordings = MobileBy.AccessibilityId("recordings_tab_bar_item");
	By my_videos_button = MobileBy.AccessibilityId("myvideos_tab_bar_item");
	By hero_banner_container = MobileBy.AccessibilityId("home_view_controller_hero_banner");
	By home_category_container = MobileBy.AccessibilityId("home_view_controller_categories");
	By hero_banner_replay_icon = MobileBy.AccessibilityId("BannerSwimlaneTableViewCell_horizontalStackView");
    By hero_banner_channel_logo = MobileBy.AccessibilityId("BannerSwimlaneTableViewCell_channelLogoImageView");
    By textview_hero_title = MobileBy.AccessibilityId("BannerSwimlaneTableViewCell_titleLabel");
   By textview_hero_metadata = MobileBy.AccessibilityId("BannerSwimlaneTableViewCell_subtitleLabel");
    By hero_banner_watch_button = MobileBy.AccessibilityId("Watch");
    By hero_banner_more_info = MobileBy.AccessibilityId("More info");
    By HomeScreen_layout=MobileBy.className("XCUIElementTypeTable");
//	By HomeScreen_layout = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable");
    //By home_categories = MobileBy.AccessibilityId("home_view_controller_categories");
    By home_categories = By.name("CategoryCell_titleLabel");
//    By swimlane_container = replace with swimlane
//    By swimlane_program_poster = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_still");
    By swimlane = MobileBy.xpath(
	    	"//*[contains(@name,'home_view_controller_swimlane_id_<PxTV_Models_SwimLanes_SwimlaneViewModel:')]");
	By program_title_under_swimlane = MobileBy.name("RegularSwimlaneItemCell_titleLabel");
	By program_subtitle_under_swimlane = MobileBy.name("RegularSwimlaneItemCell_subtitleLabel");
	By live_icon_in_swimlane = MobileBy.xpath("(//XCUIElementTypeStaticText[@name=\"ContentMarkerLabel_label\"])");
	By swimlane_title = MobileBy.name("RegularSwimlaneTableViewCell_titleLabel");
	By replay_icon = MobileBy.name("replay");
	By Recommended_movies_series_viewAll = By.xpath("//*[contains(@value,'View all')]");
	By HomeView = MobileBy.AccessibilityId("HomeView");
	By Home_Music = MobileBy.AccessibilityId("Music");
	By Home_Kids = MobileBy.AccessibilityId("Kids");
	By Home_Discovery = MobileBy.AccessibilityId("Discovery");
	By Home_News = MobileBy.AccessibilityId("News");
	By Home_Entertainment = MobileBy.AccessibilityId("Entertainment");
	By Home_Sports = MobileBy.AccessibilityId("Sports");
	By Home_Series = MobileBy.AccessibilityId("Series");
	By Home_Movies = MobileBy.AccessibilityId("Movies");
	By lock_icon=MobileBy.AccessibilityId("LockView_$__lazy_storage_$_imageView");
	By live_fullscreen=MobileBy.AccessibilityId("BottomControlsView_fullScreenButton");
	By movies_categoryPage_title = MobileBy.id("Movies");
	By swimlane_view_all_button = MobileBy.id("ShowAllButton_$__lazy_storage_$_titleLabel");
//  By program_in_swimlane = MobileBy.xpath(
//		"//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_swimlane']/android.view.ViewGroup");
//  By swimlane_container = replace with swimlane
//  By swimlane_program_poster = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_still");

}
