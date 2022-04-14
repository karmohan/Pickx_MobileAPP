package iOS_stepdefinations;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Connection_Number_Screen;
import iOS_screens.Environment_Screen;
import iOS_screens.Home_Screen;
import iOS_screens.Login_Screen;
import iOS_screens.Setting_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

//@RunWith(Cucumber.class)
public class Login_Page
		implements Login_Screen, Home_Screen, Setting_Screen, Environment_Screen, Connection_Number_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	protected Connection_Number_Page ios_connectionPage;
	protected Setting_Page setting_page;
	public iOS_input_properties inputProperties;
	public String login_continue_text;
	//public String con_button;
	//public String runningPlatform;

	public Login_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		ios_connectionPage = new Connection_Number_Page();
		setting_page = new Setting_Page();
		inputProperties = new iOS_input_properties();
		//runningPlatform = Hooks.runningPlatform();
	}
	
	//public static String runningPlatform = base_probs.getRunningPlatformName();
	//String con_button = inputProperties.getElementString("con_button", commonUtils.getDeviceLanguage());

	@And("^User is on the login page$")
	public void User_is_on_the_login_page() throws Throwable {
		commonUtils.waitTillVisibility(user_tab, 30);
		Assert.assertTrue(commonUtils.displayed(user_tab));
	}

	@And("^The User enters credentials$")
	public void the_user_enters_credentials() throws Throwable {
		// added by sree coz login page loading issue 10/8/21
		commonUtils.waitTillVisibility(user_tab, 30);
		commonUtils.clickonElement(user_tab);
		commonUtils.sendKey(user_tab, commonUtils.getUsername());
		if (commonUtils.displayed(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard")))) {
			commonUtils.findElement(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard"))).click();
		}
		commonUtils.clickonElement(pwd_tab);
		commonUtils.sendKey(pwd_tab, commonUtils.getPassword());

		// driver.hideKeyboard();
		if (commonUtils.displayed(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard")))) {
			commonUtils.findElement(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard"))).click();
		}
		//commonUtils.clickonElement(cred_continue_button);
		//commonUtils.clickonElement(commonUtils.findElementByXpathContains("name", commonUtils.getTextBasedonLanguage("con_button")));
		String login_continue_text = commonUtils.getTextBasedonLanguage("con_button");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(login_continue_text));
		
		/*
		 * if (runningPlatform.equalsIgnoreCase("browserstack")) {
		 * commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(
		 * con_button)); } else { commonUtils.clickonElement(cred_continue_button); }
		 */

//		if (commonUtils.displayed(pwd_tab)) {
//			commonUtils.sendKey(pwd_tab, commonUtils.getPassword());
//			commonUtils.clickonElement(cred_continue_button);
//
//		}
		try {
			commonUtils.waitTillVisibility(home_button, 30);
		} catch (Exception e) {
			if (!commonUtils.displayed(home_button))
				ios_connectionPage.handle_connection_number_page();
		}
	}

//	Added by sree for validation run on 01/09/21
	@And("^The User enters credentials for epic user$")
	public void the_user_enters_credentials_for_epic_user() throws Throwable {
		commonUtils.waitTillVisibility(user_tab, 30);
		commonUtils.clickonElement(user_tab);
		commonUtils.sendKey(user_tab, commonUtils.getEpicUsername());
		if (commonUtils.displayed(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard")))) {
			commonUtils.findElement(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard"))).click();
		}
		commonUtils.clickonElement(pwd_tab);
		commonUtils.sendKey(pwd_tab, commonUtils.getEpicPassword());
		if (commonUtils.displayed(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard")))) {
			commonUtils.findElement(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("hide_keyboard"))).click();
		}
		commonUtils.clickonElement(cred_continue_button);
		if (!commonUtils.displayed(home_button))
			ios_connectionPage.handle_connection_number_page();

	}

	/*@Then("^The user log out from the app$")
	public void the_user_log_out_from_the_app() {
		try {
			setting_page.the_user_is_on_settings_page();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		commonUtils.clickonElement(logout_button);
		Assert.assertTrue(commonUtils.displayed(logout_confirmation_message));
		commonUtils.clickonElement(logout_confirmation_button);
	}*/
	
	@Then("^The user log out from the app$")
	public void the_user_log_out_from_the_app() throws InterruptedException {
		try {
			setting_page.the_user_is_on_settings_page();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		commonUtils.clickonElement(logout_button);
	//	String confirmation_message = commonUtils.getTextBasedonLanguage("logout_confirmation_message");
		String logout_message = commonUtils.getText(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("logout_confirmation_message")));
		System.out.println("Fetched value from properties file  " + logout_message);
		//Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("logout_confirmation_message"))));
		//Thread.sleep(2000);
		commonUtils.clickonElement(logout_confirmation_button);
		Assert.assertFalse(commonUtils.displayed(logout_confirmation_button));
	}
	
	public By findElementByAccessibilityid(String value) {
		try {
			return MobileBy.AccessibilityId(value);
		}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;
	}

	@And("^invalid \"([^\"]*)\" and \"([^\"]*)\" is entered$")
	public void invalid_something_and_something_is_entered(String username, String password) throws Throwable {
		if (!commonUtils.displayed(home_button)) {
		} else if (commonUtils.displayed(home_button)) {
			the_user_log_out_from_the_app();
		}
		//user_select_the_login_option();
		commonUtils.clickonElement(user_tab);
		commonUtils.sendKey(user_tab, username);
		commonUtils.clickonElement(pwd_tab);
		commonUtils.sendKey(pwd_tab, password);
		commonUtils.clickonElement(cred_continue_button);
	}

	@Then("^User successfully log in to the Pickx app$")
	public void user_successfully_login_to_the_pickx_app() throws Throwable {
		commonUtils.waitTillVisibility(home_button, 30);
		Assert.assertTrue(commonUtils.displayed(home_button));
	}

	@Then("^User should get an error message$")
	public void user_should_get_an_error_message() throws Throwable {
		Assert.assertTrue(commonUtils.displayed(user_tab));
	}

//	Added try catch by sree on 14/09/21 because of delay in loading login button and home page
	@Then("^User logged in to the Pickx app$")
	public void user_logged_in_to_thepickx_app() throws Throwable {
		try {
			commonUtils.waitTillVisibility(home_button, 30);
		} catch (Exception e) {
			System.out.println("Catch user_logged_in_to_thepickx_app");
			if (!commonUtils.displayed(home_button)) {
				user_select_the_login_option();
				the_user_enters_credentials();
			}
			user_successfully_login_to_the_pickx_app();
		}
	}

	@When("^The user login as epic user$")
	public void the_user_login_as_epic_user() throws Throwable {
		if (!commonUtils.displayed(home_button)) {
			if (commonUtils.displayed(terms_and_condition_accept_button)) {
				commonUtils.clickonElement(terms_and_condition_accept_button);
			}
		}
		user_select_the_login_option();
		User_is_on_the_login_page();
		the_user_enters_credentials_for_epic_user();
		user_successfully_login_to_the_pickx_app();
		Assert.assertFalse(commonUtils.displayed(Recordings));
	}

	@Given("^User opens the PickxTV application$")
	public void user_opens_the_pickxtv_application() throws Throwable {
		String runningPlatform = commonUtils.getRunningPlatformName();
		try {
			if (runningPlatform.equalsIgnoreCase("device")) {
				if (commonUtils.displayed(btnOk)) {
					commonUtils.clickonElement(btnOk);
					commonUtils.clickonElement(btnAllow);
					commonUtils.clickonElement(btnAllow_tracking);
				}

			} else if (runningPlatform.equalsIgnoreCase("browserstack")) {
				if (!commonUtils.displayed(btnOk)) {
					commonUtils.clickonElement(btnAllow);
				} else if (!commonUtils.displayed(btnAllow)) {
					commonUtils.clickonElement(btnOk);
					commonUtils.clickonElement(btnAllow);
					if (commonUtils.displayed(btnAllow))
						commonUtils.clickonElement(btnAllow);
					commonUtils.clickonElement(btnAllow_tracking);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(commonUtils.displayed(choose_env_bar));
	}

	@When("^The User selects the environment$")
	public void the_user_selects_the_environment() throws Throwable {
		commonUtils.clickonElement(prod_radio_button);
		Assert.assertTrue(commonUtils.enabled(continue_button));
		commonUtils.clickonElement(continue_button);
	}

//	Added try catch by sree on 14/09/21 because of delay in loading login button and home page
	@Then("^The user accepts the terms and condition$")
	public void the_user_accepts_the_terms_and_condition() throws Throwable {
		try {
			commonUtils.waitTillVisibility(home_button, 10);
		} catch (Exception e) {
			System.out.println("Catch  home_button");
			if (!commonUtils.displayed(home_button)) {
				if (!commonUtils.displayed(terms_and_condition_accept_button)) {
					if (commonUtils.displayed(login_button)) {
						user_select_the_login_option();
						the_user_enters_credentials();
					} else if (commonUtils.displayed(Connection_continue_button))
						ios_connectionPage.handle_connection_number_page();
				} else if (commonUtils.displayed(terms_and_condition_accept_button)) {
					commonUtils.clickonElement(terms_and_condition_accept_button);
					try {
						commonUtils.waitTillVisibility(Connection_continue_button, 10);
						if (commonUtils.displayed(Connection_continue_button))
							ios_connectionPage.handle_connection_number_page();
						else if (!commonUtils.displayed(home_button)) {
							user_select_the_login_option();
							the_user_enters_credentials();
						} else {

						}
					} catch (Exception err) {
						if (!commonUtils.displayed(home_button)) {
							user_select_the_login_option();
							the_user_enters_credentials();
						}
					}

				}
			}
		}
	}

	@And("^User select the login option$")
	public void user_select_the_login_option() throws Throwable {
		/*	Added by sree for validation run on 01/09/21 
		if (commonUtils.displayed(terms_and_condition_accept_button)) {
			commonUtils.clickonElement(terms_and_condition_accept_button);
		}
		*/
		try {
			commonUtils.waitTillVisibility(home_button, 10);
		} catch (Exception e) {
			if (!commonUtils.displayed(home_button)) {
				commonUtils.waitTillVisibility(login_button, 10);
				Assert.assertTrue(commonUtils.displayed(login_button));
				commonUtils.clickonElement(login_button);
			}
		}
	}

	@And("^User select the login during fresh install$")
	public void user_select_the_login_during_fresh_install() throws InterruptedException {
		if (!commonUtils.displayed(home_button)) {
			/*	Added by sree for validation run on 01/09/21 
			if (commonUtils.displayed(terms_and_condition_accept_button)) {
				commonUtils.clickonElement(terms_and_condition_accept_button);
			}
			*/
			Assert.assertTrue(commonUtils.displayed(login_button));
			commonUtils.clickonElement(login_button);
		} else if (commonUtils.displayed(home_button)) {
			the_user_log_out_from_the_app();
			commonUtils.clickonElement(login_button);
		}
	}
	
    @And("^User opens the app in the background$")
    public void user_opens_the_app_in_the_background() throws Throwable {
    	driver.launchApp();
    	the_user_selects_the_environment();
    }

}
