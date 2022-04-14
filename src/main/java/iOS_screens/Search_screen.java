package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Search_screen {
	By search_navigation_button = MobileBy.AccessibilityId("tabbar search icon unselected");
	By search_navigation_button1 = By
			.xpath("//XCUIElementTypeTabBar[@name=\"navmenu_tab_bar\"]/XCUIElementTypeButton[3]");
	  By search_box = By.xpath("//XCUIElementTypeSearchField");
	By search_cancel = By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]");
	By search_x_button = MobileBy.AccessibilityId("Clear text");
	  By suggestion_search_icon= MobileBy.AccessibilityId("search_icon_magnifying_glass");
	  By suggestion_search_result= By.xpath("//XCUIElementTypeCell//XCUIElementTypeStaticText");
	  By suggestion_search_arrow= MobileBy.AccessibilityId("search_arrow_right");
	By search_result_radio_group = By.xpath(
			"//XCUIElementTypeOther[@name=\"SearchViewController_$__lazy_storage_$_filterView\"]/XCUIElementTypeCollectionView/XCUIElementTypeCell");
	By search_result_raido_group_absolute = By.xpath("//XCUIElementTypeOther[@name=\"SearchViewController_$__lazy_storage_$_filterView\"]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText");
	
//	By search_result_raido_group_absolute = By.xpath(
//			"//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeStaticText");
	  By search_result_suggestion = By.xpath("(//XCUIElementTypeCell[@name=\"SearchSuggestionViewCell\"])[1]");
//	By search_program_title = By.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/textview_title']");
	By search_program_name = By.name("SearchTVProgramViewCell_titleLabel");
	  By search_on_demand_program = By.name("SearchOnDemandViewCell_titleLabel");
	By search_program_description = MobileBy.AccessibilityId("AiringDetailView_descriptionLabel");
	By search_empty_page_text = MobileBy.AccessibilityId("BackgroundMessageView_$__lazy_storage_$_messageLabel");
	By search_empty_page_icon = MobileBy.AccessibilityId("BackgroundMessageView_imageView");

	By search_program_VOD_description = By.name("SearchOnDemandViewCell_titleLabel");
	By search_x_button_id = MobileBy.AccessibilityId("Clear text");

}
