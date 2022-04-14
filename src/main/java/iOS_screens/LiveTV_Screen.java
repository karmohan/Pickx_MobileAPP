package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface LiveTV_Screen {
	By liveTV_skeleton = By.xpath(
			"//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView");
//	By category_button_container = MobileBy.xpath(
//			"//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]");
	By category_button_container =MobileBy.id("CategoryFilterViewController_collectionView");
	By livetv_navigationbar = MobileBy.id("livetv_navigationbar");
	By live_tv_channel_icon = By.xpath("(//XCUIElementTypeImage[@name=\"livetv_program_channel_logo\"])[1]");
//	By live_page_title = MobileBy.xpath("//*[@name='livetv_navigationbar']/XCUIElementTypeStaticText");
	By live_page_title = MobileBy.xpath("//*[@name='Live TV']");
//	By live_page_title = MobileBy.AccessibilityId("Live TV");
	By live_page_title1 = MobileBy.id("Live TV");
	By live_fullscreen = MobileBy.AccessibilityId("player fullscreen");
	By live_loading_indicator = MobileBy.AccessibilityId("In progress");
	By goto_live_icon = By.xpath("//XCUIElementTypeButton[@name=\"LIVE\"]");
	By live_play_button = MobileBy.AccessibilityId("player play");
	By live_pause_button = MobileBy.AccessibilityId("player pause");
	By live_tv_program_title = MobileBy.AccessibilityId("livetv_program_title");
//	By program_title_live=By.xpath("//XCUIElementTypeStaticText[@name=\"livetv_program_title\"]");
	By live_tv_channel_logo = MobileBy.AccessibilityId("channel_logo_in_program_details_page");
	By live_tv_metadata = MobileBy.AccessibilityId("metadata_icons_in_program_details_page");
	By live_tv_replay_id = MobileBy.AccessibilityId("replay");
	By live_tv_HD_id = MobileBy.AccessibilityId("HD");
	By live_tv_airing_time_in_program_details_page = MobileBy.AccessibilityId("airing_time_in_program_details_page");
	By live_tv_record = MobileBy.AccessibilityId("record_button_in_program_details_page");
	By Live_program_description = MobileBy.AccessibilityId("synopsis_in_program_details_page");
	By live_tv_player_close = MobileBy.AccessibilityId("player close");
	By live_now = By.xpath("(//XCUIElementTypeStaticText[@name=\"Now\"])[2]");
	By Now_on_TV = By.xpath("(//XCUIElementTypeStaticText[@name=\"regular_swimlane_cell_title\"])[1]");

	By movies=By.xpath("//*[contains(@name, 'Movies')]");
	By program_description=By.xpath( 
			"//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther");
	By live_tv_title_metadata =MobileBy.AccessibilityId("livetv_program_metadata_container");
	By live_tv_title_metadata1=MobileBy.xpath("//XCUIElementTypeOther[@name=\"livetv_program_metadata_container\"]");
	By recording_icon_in_homescreen=By.xpath("(//XCUIElementTypeImage[@name=\"livetv_program_metadata_icon\"])[2]");
	By categories = MobileBy.xpath("//*[@type='XCUIElementTypeOther']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText");
	By program = MobileBy.xpath("//*[@type='XCUIElementTypeCollectionView']/XCUIElementTypeCell/XCUIElementTypeOther");
	By live_tv_program_poster = MobileBy.AccessibilityId("livetv_program_poster");
	By live_tv_progress_bar = MobileBy.AccessibilityId("livetv_program_progress");
	By live_tv_program_time = MobileBy.AccessibilityId("livetv_program_metadata_stack");
	By live_tv_streaming_error = MobileBy.AccessibilityId("InfoView_descriptionLabel");
	By live_tv_streaming_error_tryagain = MobileBy.AccessibilityId("InfoView_allowButton");
}
