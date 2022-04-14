package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Swimlane_Contents_Screen {

    By movies_and_series_page_title = MobileBy.xpath("//*[@text='Movies & Series']");
    By category = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_category");
//    By movies_page_title1 = MobileBy.xpath("//*[@text='Movies']");
    By movies_page_title1 = By.id("be.belgacom.mobile.tveverywhere:id/imageview_category");
    //xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_categories']/android.view.ViewGroup[2]/android.widget.ImageView");
    By movies_page_title = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_categories']/android.view.ViewGroup[2]/android.widget.ImageView");
    By swimlane_content_page_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_title");
    By programs_in_series = MobileBy.xpath(
	    "//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_episodes']/android.view.ViewGroup");
    By programs_in_movies = MobileBy.xpath(
	    "//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_movies']/android.view.ViewGroup");
    By non_playable_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/view_overlay_not_playable");
    By swimlane_content_page_close_button = MobileBy
	    .xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/toolbar']/android.widget.ImageButton");
    By new_this_week_page_title = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_categories']/android.view.ViewGroup[1]/android.widget.ImageView");
//    By new_this_week_page_title = By.id("be.belgacom.mobile.tveverywhere:id/imageview_category");
//    By series_page_title = MobileBy.xpath("//*[@text='series']");
    By new_this_week_page_title1 = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_categories']/android.view.ViewGroup[1]/android.widget.ImageView");
//    By new_this_week_page_title1 = By.id("be.belgacom.mobile.tveverywhere:id/imageview_category");
 //   By series_page_title = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_categories']/android.view.ViewGroup[3]/android.widget.ImageView");
    By series_page_title = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/toolbar']/android.widget.TextView");
    By entertainment_page_title = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_vod_categories']/android.view.ViewGroup[4]/android.widget.ImageView");
    By series_page_title1 = By.id("be.belgacom.mobile.tveverywhere:id/imageview_category");
    By episode_subtitle = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_subtitle");
	By svod_icon_locked = By.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_locked");
	By view_all = By.xpath("//*[@text='All']");
	By svod_icon_age = By.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_age");
	By image_view =MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview");
	By synopsis = MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_synopsis");
}
