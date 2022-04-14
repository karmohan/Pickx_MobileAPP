package Performance_Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Login_Screen {
	By home_button= MobileBy.AccessibilityId("Home");
	By liveTV_button= MobileBy.AccessibilityId("Live TV");
	By tvGuide_button= MobileBy.AccessibilityId("TV guide");
	By Recordings= MobileBy.AccessibilityId("Recordings");
	By swimlane_title = MobileBy.id("be.belgacom.mobile.adeleeverywhere:id/textview_swimlane_title");
	By swimlane_container = MobileBy.id("be.belgacom.mobile.adeleeverywhere:id/recyclerview_swimlane");
	By live_icon = MobileBy.id("be.belgacom.mobile.adeleeverywhere:id/textview_label");
}