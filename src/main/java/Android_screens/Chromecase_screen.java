package Android_screens;

import org.openqa.selenium.By;

public interface Chromecase_screen {
	By chromecast_button = By.id("be.belgacom.mobile.tveverywhere:id/imageview_cast_button");
	By device_recyclerview = By.id("be.belgacom.mobile.tveverywhere:id/recyclerview");
	By chromecast_device_icon = By.id("be.belgacom.mobile.tveverywhere:id/imageview_cast_icon");
	By chromecast_device_name = By.id("be.belgacom.mobile.tveverywhere:id/textview_title");
	By chromecast_connected_to_text = By.id("be.belgacom.mobile.tveverywhere:id/textview_connected_to");
	By chromecast_connected_device_name = By.id("be.belgacom.mobile.tveverywhere:id/textview_name");
	By chromecast_disconnect_button = By.id("be.belgacom.mobile.tveverywhere:id/button_disconnect");
	By chromecast_cancel_button = By.id("be.belgacom.mobile.tveverywhere:id/button_cancel");
	By chromecast_icon_insideCasePage = By.id("be.belgacom.mobile.tveverywhere:id/imageview_icon");
	By chromecast_available_device_text = By.id("be.belgacom.mobile.tveverywhere:id/toolbar_title");
	
}
