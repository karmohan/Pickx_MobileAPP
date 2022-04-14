package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;


public interface Login_Screen {
    By cred_continue_button = MobileBy.AccessibilityId("Continue");
    By con_button1 = MobileBy.xpath("//XCUIElementTypeButton[@name=\"Continue\"]");
	By error_message = MobileBy.AccessibilityId("The login is invalid.");
	By user_tab = MobileBy.xpath("//XCUIElementTypeOther[@name=\"ForgeRock Access Management\"]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeTextField");
	By pwd_tab = MobileBy.xpath("//XCUIElementTypeOther[@name=\"ForgeRock Access Management\"]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeSecureTextField");
	By confirm = MobileBy.AccessibilityId("Confirm");
	By before_confirm = MobileBy.xpath("//XCUIElementTypeOther[@name=\"ForgeRock Access Management\"]/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]");
	By hide_keyboard=MobileBy.AccessibilityId("Hide keyboard");
}
