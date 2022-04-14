package Android_screens;

import org.openqa.selenium.By;


import io.appium.java_client.MobileBy;

public interface Recordings_Screen {

	By recorded_tab= MobileBy.AccessibilityId("Recorded");
	By planned_tab= MobileBy.AccessibilityId("Planned");
        By recordings_container = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recyclerview_recordings");
        By programs_in_recordings = MobileBy.xpath(
    	    	"//*[@resource-id='be.belgacom.mobile.tveverywhere:id/recyclerview_recordings']/android.view.ViewGroup");
        By recordings_poster = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_recording_thumbnail");
        By recordings_title = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_recording_title");
        By recordings_description = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_recorded_description");
        By other_options_in_recordings = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_recordings_other_options");
        By recordings_icons = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_icon_recording");
        By recordings_pop_up = MobileBy.id("be.belgacom.mobile.tveverywhere:id/recording_bottom_dialog_root");
        By option_in_recordings_pop_up = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_recording_option");
        By delete_recording_toast_message = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_toast_content");

}
