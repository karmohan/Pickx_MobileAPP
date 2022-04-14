package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Program_Details_Screen {

    By player_screen = MobileBy.id("be.belgacom.mobile.tveverywhere:id/meta_thumbnail");
    By play_pause_button_on_player_screen = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_play_pause_toggle");
    By timer_under_player = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_duration_countdown");
    By ongoing_program_record_button = MobileBy.id("be.belgacom.mobile.tveverywhere:id/channel_meta_btnrecord");
    By record_button_text = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_text");
    By close_button = MobileBy
	    .xpath("//*[@resource-id='be.belgacom.mobile.tveverywhere:id/toolbar']/android.widget.ImageButton");
    By recording_pop_up = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recording_bottom_dialog_root");
    By option_in_recording_pop_up = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_recording_option");
    By progress_bar = MobileBy.id("be.belgacom.mobile.tveverywhere:id/seek_bar");
    By live_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/channel_meta_live");
    By ongoing_program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/channel_meta_title");
    By ongoing_channel_logo = MobileBy.id("be.belgacom.mobile.tveverywhere:id/channel_meta_channellogo");
    By ongoing_program_time = MobileBy.id("be.belgacom.mobile.tveverywhere:id/channel_meta_datetime");
    By not_playable_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_not_playable");
    By details_page_replay_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/metadata_icon_id_replay");
    By record_button = MobileBy.id("be.belgacom.mobile.tveverywhere:id/airing_meta_btnrecord");
    By program_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/airing_meta_title");
    By channel_logo = MobileBy.id("be.belgacom.mobile.tveverywhere:id/airing_meta_channellogo");
    By program_time = MobileBy.id("be.belgacom.mobile.tveverywhere:id/airing_meta_datetime");
    By program_synopsis = MobileBy.id("be.belgacom.mobile.tveverywhere:id/airing_meta_synopsis");
    By program_synopsis_subText = MobileBy.id("be.belgacom.mobile.tveverywhere:id/airing_meta_synopsis_subtext");
    By recording_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/metadata_icon_id_recording");
    By toast_message_for_deletion = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_toast_content");
    By yes_button_in_pop_up = MobileBy.id("android:id/button1");
	By player_screen_swimlane_Tab = MobileBy.id("be.belgacom.mobile.tveverywhere:id/player_root_constraintlayout");;
}
