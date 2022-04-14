package iOS_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface Connection_Number_Screen {

    By Connection_continue_button = MobileBy.AccessibilityId("line-number-view-controller-confirm-button");
    By connection_number_text = By.xpath("(//XCUIElementTypeStaticText[@name='settings_connection_line_number'])");
    
}
