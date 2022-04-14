package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface TVguide_Screen {
    
    By tv_guide_program_poster = MobileBy.name("imageview_content_image");
    By tv_guide_live_icon = MobileBy.AccessibilityId("ContentMarkerLabel_label");
    By channel_cell = MobileBy.name("channel_list_in_tvguide");
    By channel_cell_tab = MobileBy.id("programs_in_tvGuide_for_iPad");
    By channel_selected_bar = MobileBy.xpath("//XCUIElementTypeCell[@name='channel_list_in_tvguide']/XCUIElementTypeOther/XCUIElementTypeOther[2]");
//    By epg_program_tiles1 = By.name("elements_of_each_program_in_tvGuide");
    By epg_program_tiles = By.xpath("//*[contains(@name,'EpgAiringTableViewCell')]");
//    By epg=MobileBy.xpath("//XCUIElementTypeCell[@name=\"EpgAiringsCollectionViewCell\"]/XCUIElementTypeOther/XCUIElementTypeTable");
    By epg=MobileBy.xpath("//XCUIElementTypeCell[@name=\"EpgAiringsCollectionViewCell\"]/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[@name=\"EpgAiringTableViewCell\"]");
//    By epg_video_title=MobileBy.xpath("//*[contains(@name,'textview_title')]");
//    By epg_video_title=MobileBy.xpath("//XCUIElementTypeStaticText[@name=\"textview_title\"]");
    By epg_video_title = By.name("textview_title");
    By epg_program=By.name("EpgAiringsCollectionViewCell");
    By epg_episode_genre = By.name("textview_season_episode_genre");
    By epg_video_broadcast_time1 = By.name("textview_broadcast_time");
    By epg_video_broadcast_time = By.xpath("//*[contains(@name,'textview_broadcast_time')]");
//    By elements_of_each_program_in_tvGuide = By.xpath("//*[contains(@name,'elements_of_each_program_in_tvGuide')]");
    By epg_date_picker_today = By.xpath("//*[contains(@value,'Today')]");
     By date_picker_today = MobileBy.AccessibilityId("EpgDatePicker_stackView");
     By date_picker= MobileBy.AccessibilityId("EpgDatePicker_$__lazy_storage_$_titleLabel");
     By date_picker_tomorrow = MobileBy.AccessibilityId("Tomorrow");
     
     By date_picker_yesterday = MobileBy.AccessibilityId("Yesterday");
//     By epg_live_play_pause_icon = MobileBy.AccessibilityId("player play");
	By epg_live_video_container = MobileBy.AccessibilityId("EpgLiveAiringTableViewCell");
	By date_picker_arrow_down = MobileBy.AccessibilityId("EpgDatePicker_arrowIcon");
	By channel_tab = MobileBy.AccessibilityId("EpgPhoneViewController_$__lazy_storage_$_channelsView");
	// By video_containers = MobileBy.AccessibilityId("programs_in_tvGuide");
	By date_picker_textview_date = By.name("EpgDatePickerTableViewCell_$__lazy_storage_$_label");
	By date_picker_container = MobileBy.AccessibilityId("EpgDatePicker_tableView");
//     By non_playable_decoder_text=MobileBy.AccessibilityId("This channel is only playable on your decoder");
//	By non_playable_decoder = By.name("NonPlayableChannelView_$__lazy_storage_$_label");
	By non_playable_decoder = By.xpath("//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText[@name=\"NonPlayableChannelView_$__lazy_storage_$_label\"]");
	By Locked_icon = MobileBy.AccessibilityId("LockView_$__lazy_storage_$_imageView");
	By epg_live_rewind_button = By.id("");
	By epg_live_forward_button = By.id("");

}
