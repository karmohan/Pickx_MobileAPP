package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Vod_Details_Screen {

	By play_pause_button = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_play_pause_toggle");
	By vod_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_title");
	By vod_program_subtitle = MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_subtitle");
	By vod_program_time = MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_info");
	By download_spinner = MobileBy.id("be.belgacom.mobile.tveverywhere:id/progress_bar_image_text_button");
	By cancel_download = MobileBy.id("be.belgacom.mobile.tveverywhere:id/view_stop_icon");
	By download_button = MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_dtgbutton");
	By availability = MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_availability");
	By download_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_text");
	By play_on_decoder = MobileBy.id("be.belgacom.mobile.tveverywhere:id/view_overlay_not_playable");
	By movie_container = MobileBy.xpath(
			"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]");
	By movie_container_next = MobileBy.xpath(
			"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]");
//	By not_playable_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_not_playable");
// 	By synopsis =MobileBy.id("be.belgacom.mobile.tveverywhere:id/vod_meta_synopsis");
//  By close_button = MobileBy.xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/toolbar']/android.widget.ImageButton");
//  By player_screen = MobileBy.id("be.belgacom.mobile.tveverywhere:id/meta_thumbnail");
}
