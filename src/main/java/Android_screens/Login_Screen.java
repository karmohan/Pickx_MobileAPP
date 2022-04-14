package Android_screens;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;


public interface Login_Screen {

	By user_name_tab = MobileBy.AccessibilityId("userName");
	By password_tab= MobileBy.AccessibilityId("userPwd");
	By continue_button = By.id("be.belgacom.mobile.tveverywhere:id/button_continue");
	By user_tab = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.widget.EditText");
	By pwd_tab = By.xpath("	\r\n" + 
			"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View[2]/android.view.View[3]/android.widget.EditText");
	By con_button = By.xpath("//*[@text='Continue']");
	By err=By.xpath("//*[@resource-id='messages']/	android.view.View");
//	By error_message = By.xpath("//*[@class='alert-system alert-message alert alert-danger']");
//	By error_message = MobileBy.className("alert-system alert-message alert alert-danger");
//	By error_message1 = By.xpath("//*[@resource-id='messages']");
	By new_user1 = By.id("idToken1");
	By new_pwd1 = By.id("idToken2");
	By new_continue1 = By.id("loginButton_0");
//	By new_user1 = By.xpath("//*[@resource-id='idToken1']");
//	By new_pwd1= By.xpath("//*[@resource-id='idToken2']");
//	By new_continue1 = By.xpath("//*[@resource-id='loginButton_0']");
    By accept_cookies = By.className("android.widget.Button");
	
}
