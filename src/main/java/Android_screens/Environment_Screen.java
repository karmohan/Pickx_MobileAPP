package Android_screens;

import org.openqa.selenium.By;

public interface Environment_Screen {
	
	By prod_radio_button = By.xpath("//*[@text='PROD']");
	By uat_radio_button = By.xpath("//*[@text='UAT']");
	By env_continue_button = By.id("be.belgacom.mobile.tveverywhere:id/button_continue");
	By choose_env_bar = By.id("be.belgacom.mobile.tveverywhere:id/toolbar");
	By login_button = By.id("be.belgacom.mobile.tveverywhere:id/button_login");
	By terms_and_condition_accept_button = By.id("be.belgacom.mobile.tveverywhere:id/button_agree");

}
