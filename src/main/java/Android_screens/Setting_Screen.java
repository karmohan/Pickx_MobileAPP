package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Setting_Screen {
	
	By settings_button = By.id("be.belgacom.mobile.tveverywhere:id/imageview_settings_button");
	By logout_button = By.id("be.belgacom.mobile.tveverywhere:id/item_logout");
	By logout_confirmation_button = By.id("android:id/button1");
	By logout_cancel_button = By.id("android:id/button2");
	By logout_confirmation_message = By.id("android:id/message");
	By parental_control = By.id("be.belgacom.mobile.tveverywhere:id/item_parental_control");
	By send_feedback = By.id("be.belgacom.mobile.tveverywhere:id/item_feedback");
	By input_pin_0 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_0");
	By input_pin_1 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_1");
	By input_pin_2 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_2");
	By input_pin_3 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_3");
	By input_pin_4 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_4");
	By input_pin_5 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_5");
	By input_pin_6 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_6");
	By input_pin_7 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_7");
	By input_pin_8 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_8");
	By input_pin_9 = By.id("be.belgacom.mobile.tveverywhere:id/input_pin_9");
	By parental_control_toggle_id = By.id("be.belgacom.mobile.tveverywhere:id/item_parental_control_enabled");
	By also_hide_centor_titles_toggle_id = By.id("be.belgacom.mobile.tveverywhere:id/item_centor_titles");
	By age_restriction_toggle_id = By.id("be.belgacom.mobile.tveverywhere:id/item_age_restriction");
	By parental_control_change_pin = By.id("be.belgacom.mobile.tveverywhere:id/item_change_pin");
	By select_the_age_restriction = By.id("be.belgacom.mobile.tveverywhere:id/textview_value");
	By age_restriction_18_icon = By.id("be.belgacom.mobile.tveverywhere:id/icon_18");
	By age_restriction_16_icon = By.id("be.belgacom.mobile.tveverywhere:id/icon_16");
	By age_restriction_12_icon = By.id("be.belgacom.mobile.tveverywhere:id/icon_12");
	By age_restriction_10_icon = By.id("be.belgacom.mobile.tveverywhere:id/icon_10");
	By selected_age_value = By.id("be.belgacom.mobile.tveverywhere:id/ic_age_icon");
	By enter_pin_title_text = By.id("be.belgacom.mobile.tveverywhere:id/textview_pin_title");
	By feedback_text_box = By.id("be.belgacom.mobile.tveverywhere:id/edittext_feedback");
	By email_text_box = By.id("be.belgacom.mobile.tveverywhere:id/edittext_email");
	By feedback_submit_button = By.id("be.belgacom.mobile.tveverywhere:id/button_submit");
        By parental_control_toggle_point_on_id = By.id("be.belgacom.mobile.tveverywhere:id/id_switch");
        By parental_control_toggle_point_off = By.xpath("//*[contains(@text,'OFF')]");
        By parental_control_toggle_point_on = By.xpath("//*[contains(@text,'ON')]");
        By back_button = By.className("android.widget.ImageButton");
        By incorrect_pin_text= By.xpath("//*[contains(@text,'Enter your PIN code')]");
        By parental_pin_confirm_button = MobileBy.id("android:id/button1");
        By parental_pin_cancel_button = MobileBy.id("android:id/button2");
        By parental_pin_more_info = MobileBy.id("android:id/button3");
        By parental_pin_input = MobileBy.id("be.belgacom.mobile.tveverywhere:id/dialog_pin_input");
        By parental_age_arrow = MobileBy.id("be.belgacom.mobile.tveverywhere:id/imageview_arrow");
        By parental_seek_bar = MobileBy.id("be.belgacom.mobile.tveverywhere:id/seek_bar");      
        By settings_more_info = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_help_description");
        By loading_icon = MobileBy.id("be.belgacom.mobile.tveverywhere:id/progress_bar");
        By Please_connect_to_network_text = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_error");
       // By Please_connect_to_network_text = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_description");
        By you_are_offline_text = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_title");
        By try_again_button = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_error");
       // By try_again_button = MobileBy.id("be.belgacom.mobile.tveverywhere:id/button_action_primary");
        By incorrect_PIN_text = MobileBy.id("be.belgacom.mobile.tveverywhere:id/textview_toast_content");
}
