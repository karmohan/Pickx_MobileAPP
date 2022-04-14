package Performance_iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;


public interface Login_Screen {

	By home_button= MobileBy.AccessibilityId("home_tab_bar_item");
	By liveTV_button= MobileBy.AccessibilityId("livetv_tab_bar_item");
	By tvGuide_button= MobileBy.AccessibilityId("tvguide_tab_bar_item");
	By Recordings= MobileBy.AccessibilityId("recordings_tab_bar_item");
	By live_icon = MobileBy.xpath("(//XCUIElementTypeStaticText[@name=\"Now\"])[1]");
	By Categories= MobileBy.AccessibilityId("home_view_controller_categories");
	
}
