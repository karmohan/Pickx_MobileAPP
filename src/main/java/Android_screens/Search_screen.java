package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Search_screen {	
	  By bottom_navigation_bar = By.id("be.belgacom.mobile.tveverywhere:id/bottom_navigation_view");
	  By search_navigation_button = MobileBy.xpath(
			    "//*[@resource-id='be.belgacom.mobile.tveverywhere:id/bottom_navigation_view']/android.view.ViewGroup/android.widget.FrameLayout[3]");
	  By search_box_before_tap = By.id("be.belgacom.mobile.tveverywhere:id/explore_search_button");
	  By search_box = By.id("be.belgacom.mobile.tveverywhere:id/search_src_text");
	  By search_hint_text = By.id("be.belgacom.mobile.tveverywhere:id/textview_search_hint");
	  By search_x_button_id = By.id("be.belgacom.mobile.tveverywhere:id/search_close_btn");
	  By search_x_button= MobileBy.AccessibilityId("Clear query");
	  By suggestion_search_icon= By.id("be.belgacom.mobile.tveverywhere:id/imageview_suggestion_icon");
	  By suggestion_search_result= By.id("be.belgacom.mobile.tveverywhere:id/textview_suggestion");
	  By suggestion_search_arrow= By.id("be.belgacom.mobile.tveverywhere:id/imageview_suggestion_arrow");
	  By search_result_radio_group= By.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/radio_group']/android.widget.RadioButton");
//	  By search_program_title = By.id("be.belgacom.mobile.tveverywhere:id/textview_title");
	  By search_program_title = By.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/textview_title']");
	  By search_program_description= By.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/layout_channel_description']");
	By empty_search_icon = By.id("be.belgacom.mobile.tveverywhere:id/imageview_no_results_icon");
	  By searc_program_VOD_description = By.id("be.belgacom.mobile.tveverywhere:id/view_bottom");
	  By search_program_cast = By.id("be.belgacom.mobile.tveverywhere:id/airing_meta_synopsis_subtext");
	By empty_search_error = By.id("be.belgacom.mobile.tveverywhere:id/textview_search_no_results");
	By sugestion_search_result = By.id("be.belgacom.mobile.tveverywhere:id/textview_suggestion");
}
