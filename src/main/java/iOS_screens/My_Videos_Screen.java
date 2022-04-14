package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface My_Videos_Screen {
    
//    By my_videos_tab_layout = MobileBy.id("be.belgacom.mobile.tveverywhere:id/tablayout_my_videos");
    By my_videos_recordings_option1 = MobileBy.xpath("//XCUIElementTypeButton[@name='PxSegmentedControl_segmentButton']/XCUIElementTypeStaticText[@name='Recordings']");
    By my_videos_recordings_option = MobileBy.AccessibilityId("Recordings");
    By my_videos_downloads = MobileBy.AccessibilityId("Downloads");
    By my_videos_recordings_playable_tab = MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'Playable')]");
    By my_videos_recordings_recorded_tab = MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'Recorded')]");
    By my_videos_recordings_planned_tab = MobileBy.xpath("//XCUIElementTypeStaticText[contains(@name,'Planned')]");
//    By my_videos_recordings_programs = MobileBy.xpath(
//	    "//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_recordings']/android.view.ViewGroup");
//    By my_videos_recordings_program_poster = MobileBy
//	    .id("be.belgacom.mobile.tveverywhere:id/imageview_recording_thumbnail");
//    By my_videos_recordings_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_recording_title");
    By my_videos_recordings_program_other_option = MobileBy.name("option");
//    By my_videos_recordings_program_description = MobileBy
//	    .id("be.belgacom.mobile.tveverywhere:id/textview_recorded_description");
    By my_videos_recordings_program_series_recorded_icon = MobileBy.name("series_recorded");
    By my_videos_recordings_program_episode_recorded_icon = MobileBy.name("recorded_recording");
    By my_videos_recordings_program_series_planned_icon = MobileBy.name("series_planned");
    By my_videos_recordings_program_episode_planned_icon = MobileBy.name("planned_recording");
    By my_videos_continue_watching_option = MobileBy.AccessibilityId("Continue watching");
    By my_videos_recordings_tiles = MobileBy.name("MVCollectionViewCell");
    By my_videos_player_time = MobileBy.xpath("//XCUIElementTypeButton[@name=\"BottomControlsView_durationIndicatorButton\"]");
    By my_videos_recordings_tiles_series_play = MobileBy.xpath("//XCUIElementTypeTable[@name=\"SeriesDetailViewController_tableView\"]/XCUIElementTypeCell[2]");
    By my_videos_video_container = MobileBy.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther[1]");
    By program_title_of_searched_text = MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"SearchOnDemandViewCell_titleLabel\"]");
    By program_displayed_onDemand= MobileBy.xpath("//XCUIElementTypeCell[@name=\"SearchOnDemandViewCell\"]");
    By invalid_item_error_in_searchPage = MobileBy.AccessibilityId("InfoView_titleLabel");
    By downloaded_programs = MobileBy.xpath("//XCUIElementTypeCell[@name=\"MVCollectionViewCell\"]");
    By option_in_download_page = MobileBy.xpath("//XCUIElementTypeButton[@name=\"option\"]");
    By downloaded_content_SLA = MobileBy.AccessibilityId("seriesrecording_available_days_left-plural");
    By age_download_symbol= MobileBy.xpath("//XCUIElementTypeImage[@name=\"MVCollectionViewCell_metadataIcon\"]");
    By downloaded_tab_highligted = MobileBy.xpath("(//XCUIElementTypeCell[@name=\"ScrollableSegmentedControlCell\"])[2]/XCUIElementTypeOther/XCUIElementTypeOther[2]");
    By recordings_tab_highligted = MobileBy.xpath("(//XCUIElementTypeCell[@name=\"ScrollableSegmentedControlCell\"])[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]");
    By continues_watching_tab_highligted = MobileBy.xpath("(//XCUIElementTypeCell[@name=\"ScrollableSegmentedControlCell\"])[3]/XCUIElementTypeOther/XCUIElementTypeOther[2]");
    By title_of_downloaded_video = MobileBy.xpath("(//XCUIElementTypeCell[@name=\"MVCollectionViewCell\"])[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeStaticText");
    By metadata_of_downloaded_video = MobileBy.xpath("(//XCUIElementTypeCell[@name=\"MVCollectionViewCell\"])[1]/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText");
    By downloaded_video_playtime = MobileBy.xpath("//XCUIElementTypeButton[@name=\"BottomControlsView_durationIndicatorButton\"]/XCUIElementTypeStaticText");
    
}
