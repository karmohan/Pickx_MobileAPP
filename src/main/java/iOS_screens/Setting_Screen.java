package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Setting_Screen {

	By parental_control_toggle_point = MobileBy.name("SettingsToggleCell_toggle");
	By back_button = MobileBy.AccessibilityId("Back");
	 By icon_login=By.xpath("//XCUIElementTypeOther[@name=\"ForgeRock Access Management\"]/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]");
//	 By parental_pin_input=By.xpath("//XCUIElementTypeAlert[@name=\"Parental control active\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther") ;
//	 By parental_pin_input=By.xpath("//XCUIElementTypeAlert[@name=\"Parental control active\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeScrollView[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField") ;
	 By parental_pin_input=By.className("XCUIElementTypeSecureTextField");
//			 
	 By parental_pin_confirm_button=MobileBy.AccessibilityId("Confirm") ;
	 By parental_pin_cancel_button=MobileBy.AccessibilityId("Cancel") ;
	 By invalid_parental_pin =MobileBy.AccessibilityId("Invalid PIN code. Please try again.");
	 
	 By settings_button = MobileBy.AccessibilityId("home_view_controller_settings_button");
	 By parental_control = By.xpath("(//XCUIElementTypeCell[@name=\"settings_cell\"])[2]");
	 By logout_button = By.xpath("(//XCUIElementTypeCell[@name=\"settings_cell\"])[5]");
	 By logout_confirmation_message = MobileBy.AccessibilityId("Are you sure you want to log out?");
	 By logout_confirmation_button = MobileBy.AccessibilityId("ok_button_in_pop_up");
	 By enter_pin_title_text = MobileBy.AccessibilityId("Enter your PIN code to change the parental control settings");
	 By input_pin_0 = By.xpath("//XCUIElementTypeButton[@name=\"0\"]");
		By input_pin_1 = By.xpath("//XCUIElementTypeButton[@name=\"1\"]");
		By input_pin_2 = By.xpath("//XCUIElementTypeButton[@name=\"2\"]");
		By input_pin_3 = By.xpath("//XCUIElementTypeButton[@name=\"3\"]");
		By input_pin_4 = By.xpath("//XCUIElementTypeButton[@name=\"4\"]");
		By input_pin_5 = By.xpath("//XCUIElementTypeButton[@name=\"5\"]");
		By input_pin_6 = By.xpath("//XCUIElementTypeButton[@name=\"6\"]");
		By input_pin_7 = By.xpath("//XCUIElementTypeButton[@name=\"7\"]");
		By input_pin_8 = By.xpath("//XCUIElementTypeButton[@name=\"8\"]");
		By input_pin_9 = By.xpath("//XCUIElementTypeButton[@name=\"9\"]");
		By incorrect_pin_text = MobileBy.AccessibilityId("The PIN you entered is incorrect. Please try again.");
//		By parental_control_toggle_id = By.xpath("//XCUIElementTypeSwitch[@name=\"Parental control\"]");
	By parental_control_toggle_id = MobileBy.name("SettingsToggleCell_toggle");
	By parental_control_toggle_point_off = By.xpath("//XCUIElementTypeSwitch[@name=\"Parental control\"]");
	By parental_control_toggle_point_on = By.xpath("//XCUIElementTypeSwitch[@name=\"Parental control\"]");
	By age_restriction = MobileBy.AccessibilityId("SettingsAgeRatingCell");
	By age_restriction_18_icon = MobileBy.AccessibilityId("SettingsAgeRestrictionViewController_eightTeenButton");
	By age_restriction_16_icon = MobileBy.AccessibilityId("SettingsAgeRestrictionViewController_sixTeenButton");
	By age_restriction_12_icon = MobileBy.AccessibilityId("SettingsAgeRestrictionViewController_twelveButton");
	By age_restriction_10_icon = MobileBy.AccessibilityId("SettingsAgeRestrictionViewController_tenButton");
//    	By age_restriction_18_icon1 = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton");
//    	By age_restriction_16_icon1 = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeButton");
//    	By age_restriction_12_icon1 = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeButton");
//    	By age_restriction_10_icon1 = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[4]/XCUIElementTypeButton");
    	By selected_age_value = MobileBy.AccessibilityId("SettingsAgeRestrictionViewController_ageRestrictionDetailDescription");
    	By parental_control_change_pin = MobileBy.AccessibilityId("settings_cell");
    	By enter_new_pin_title_text = MobileBy.AccessibilityId("Enter your new PIN code");
    	By reenter_pin_title_text = MobileBy.AccessibilityId("Repeat your new PIN code");
    	By send_feedback = By.xpath("(//XCUIElementTypeCell[@name=\"settings_cell\"])[4]");
    	By your_feedback_text = MobileBy.AccessibilityId("FeedbackViewController_feedbackLabel");
    	By feedback_text_box = MobileBy.AccessibilityId("FeedbackViewController_feedbackTextField");
    	By email_text_box = MobileBy.AccessibilityId("FeedbackViewController_emailTextField");
    	By feedback_submit_button = MobileBy.AccessibilityId("Submit");
    	By age_restriction_text_inside = MobileBy.AccessibilityId("Lock all TV programs and films with following age restrictions:");
    	By back_navigator = MobileBy.AccessibilityId("Back");
    	By seekBar = By.xpath("//XCUIElementTypeApplication[@name=\"Proximus Pickx\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSlider");
    	By wifi_cell_in_settings = MobileBy.AccessibilityId("Wi-Fi");
    	By wifi_switch_in_settings = MobileBy.xpath("//XCUIElementTypeSwitch[@name=\"Wi-Fi\"]");
    	//By wifi_switch_in_settings =MobileBy.AccessibilityId("Wifi");
    	By wifi_offline_alert = MobileBy.AccessibilityId("InfoView_iconImageView");
    	By wifi_offline_text = MobileBy.AccessibilityId("InfoView_titleLabel");
    	By wifi_offline_try_again = MobileBy.AccessibilityId("Try again");
    	//By wifi_offline_try_again = MobileBy.xpath("//XCUIElementTypeButton[@name=\"Probeer opnieuw\"]");
    	//	 By parental_pin_input = MobileBy.id("be.belgacom.mobile.tveverywhere:id/dialog_pin_input");
//	 By parental_pin_confirm_button = MobileBy.id("android:id/button1");
	    
}
