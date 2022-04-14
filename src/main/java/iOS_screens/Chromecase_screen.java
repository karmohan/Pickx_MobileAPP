package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Chromecase_screen {
	By chromecast_button = MobileBy.AccessibilityId("home_view_controller_chromecast_button");
	By device_recyclerview = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell");
	//By chromecast_device_icon = By.id("be.belgacom.mobile.tveverywhere:id/imageview_cast_icon");
	By chromecast_device_name = MobileBy.AccessibilityId("Hall TV");
	By chromecast_connected_to_text = MobileBy.AccessibilityId("Connected to");
	By chromecast_connected_device_name = MobileBy.AccessibilityId("Hall TV");
	By chromecast_disconnect_button = By.xpath("//XCUIElementTypeButton[@name=\"Disconnect\"]");
	By chromecast_cancel_button = By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]");
	By chromecast_icon_insideCasePage = MobileBy.AccessibilityId("cast_disconnect");
	By chromecast_available_device_text = MobileBy.AccessibilityId("Choose a device");
	By chromecast_video_playing_text = MobileBy.AccessibilityId("Playing on Hall TV");
	By chromecast_miniplayer_progressBar = MobileBy.AccessibilityId("NewMiniPlayerView_progressBar");
	By chromecast_miniplayer_maximizeButton = MobileBy.AccessibilityId("NewMiniPlayerView_maximizeButton");
	By chromecast_miniplayer_NewMiniPlayerView_infoContainerView = MobileBy.AccessibilityId("NewMiniPlayerView_infoContainerView");
	By chromecast_miniplayer_playbutton = MobileBy.AccessibilityId("NewMiniPlayerView_playPauseButton");
	By chromecast_miniplayer_stopButton = MobileBy.AccessibilityId("NewMiniPlayerView_stopButton");
	By chromecast_miniplayer_player_close = MobileBy.AccessibilityId("miniplayer close");
	By playing_on_hall_tv = MobileBy.AccessibilityId("Playing on Hall TV");
	By player_minimize_button = MobileBy.AccessibilityId("player chevron down");
	By NewMiniPlayerView_maximizeButton = MobileBy.AccessibilityId("NewMiniPlayerView_maximizeButton");
	By NewMiniPlayerView_infoContainerView = MobileBy.AccessibilityId("NewMiniPlayerView_infoContainerView");
	By miniplayer_title = MobileBy.AccessibilityId("NewMiniPlayerView_$__lazy_storage_$_titleLabel");
	By miniplayer_subtitle = MobileBy.AccessibilityId("NewMiniPlayerView_$__lazy_storage_$_subtitleLabel");
	By NewMiniPlayerView_playPauseButton = MobileBy.AccessibilityId("NewMiniPlayerView_playPauseButton");
	By NewMiniPlayerView_stopButton = MobileBy.AccessibilityId("NewMiniPlayerView_stopButton");
	By player_close_button = MobileBy.AccessibilityId("player close");
}


