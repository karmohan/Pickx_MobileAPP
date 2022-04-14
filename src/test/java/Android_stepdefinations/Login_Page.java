package Android_stepdefinations;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

import Android_screens.Environment_Screen;
import Android_screens.Home_Screen;
import Android_screens.Login_Screen;
import Android_screens.Setting_Screen;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

//@RunWith(Cucumber.class)
public class Login_Page implements Login_Screen, Home_Screen, Setting_Screen, Environment_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Setting_Page setting_page;
	public Connection_Number_Page connectionPage = new Connection_Number_Page();
	CloseableHttpResponse closeableHttpResponse;
	public int RESPONSE_CODE_200 = 200;

	public Login_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		setting_page = new Setting_Page();
	}

	@And("^User is on the login page$")
	public void User_is_on_the_login_page() throws Throwable {
		// getSystemIPAddress();
		commonUtils.switchcontext(commonUtils.getWebView());
		commonUtils.waitTillVisibility(new_user1, 30);
		Assert.assertTrue(commonUtils.displayed(new_user1));
	}

	@And("^The User enters credentials$")
	public void the_user_enters_credentials() throws Throwable {
		commonUtils.sendKey(new_user1, commonUtils.getUsername());
		commonUtils.sendKey(new_pwd1, commonUtils.getPassword());
		commonUtils.clickonElement(new_continue1);
//		if (commonUtils.enabled(new_continue1)) {
//			commonUtils.sendKey(new_pwd1, commonUtils.getPassword());
//			commonUtils.clickonElement(new_continue1);
//		}
		commonUtils.switchcontext(commonUtils.getNativeView());
		if (!commonUtils.displayed(home_button))
			connectionPage.handle_connection_number_page();
		try {
			commonUtils.waitTillVisibility(home_button, 30);
		} catch (Exception e) {
			if (!commonUtils.displayed(home_button))
				commonUtils.clickonElement(accept_cookies);
		}

	}

	@And("^The User enters credentials for epic user$")
	public void the_user_enters_credentials_for_epic_user() throws Throwable {
		commonUtils.sendKey(new_user1, commonUtils.getEpicUsername());
		// commonUtils.clickonElement(new_pwd);
		commonUtils.sendKey(new_pwd1, commonUtils.getEpicPassword());
		commonUtils.clickonElement(new_continue1);
		commonUtils.switchcontext(commonUtils.getNativeView());
		if (!commonUtils.displayed(home_button)) {
			connectionPage.handle_connection_number_page();
		}
		if (!commonUtils.displayed(home_button)) {
			commonUtils.clickonElement(accept_cookies);
		}
	}

	@Then("^The user log out from the app$")
	public void the_user_log_out_from_the_app() throws InterruptedException {
		try {
			setting_page.the_user_is_on_settings_page();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		commonUtils.clickonElement(logout_button);
		Assert.assertEquals(commonUtils.getText(logout_confirmation_message),
				commonUtils.getTextBasedonLanguage_Android("logout_confirmation_message"));
		commonUtils.clickonElement(logout_confirmation_button);
	}

	@And("^invalid \"([^\"]*)\" and \"([^\"]*)\" is entered$")
	public void invalid_something_and_something_is_entered(String username, String password) throws Throwable {
		if (!commonUtils.displayed(home_button)) {
		} else if (commonUtils.displayed(home_button)) {
			the_user_log_out_from_the_app();
		}
		user_select_the_login_option();
		User_is_on_the_login_page();
		commonUtils.sendKey(new_user1, username);
		// commonUtils.clickonElement(new_pwd);
		commonUtils.sendKey(new_pwd1, password);
		commonUtils.clickonElement(new_continue1);
	}

	@Then("^User successfully log in to the Pickx app$")
	public void user_successfully_login_to_the_pickx_app() throws Throwable {
		commonUtils.waitTillVisibility(home_button, 30);
		Assert.assertTrue(commonUtils.displayed(home_button));
	}

	@Then("^User should get an error message$")
	public void user_should_get_an_error_message() throws Throwable {
		Assert.assertTrue(commonUtils.displayed(new_user1));
	}

    @Given("User logged in to the Pickx app")
    public void user_logged_in_to_thepickx_app() throws Throwable {
		int count = 0;
		while (count < 2) {
			if (!commonUtils.displayed(home_button)) {
				if (commonUtils.displayed(terms_and_condition_accept_button)) {
					user_select_the_login_option();
					User_is_on_the_login_page();
					the_user_enters_credentials();
				}
				else if (commonUtils.displayed(login_button)) {
					commonUtils.clickonElement(login_button);
					User_is_on_the_login_page();
					the_user_enters_credentials();
				}
				else if (!commonUtils.displayed(home_button)) {
					count++;
				}
			}
			else if (commonUtils.displayed(home_button)) {
				break;
			}
		}
		user_successfully_login_to_the_pickx_app();
	}

	@When("^The user login as epic user$")
	public void the_user_login_as_epic_user() throws Throwable {
		user_select_the_login_option();
		User_is_on_the_login_page();
		the_user_enters_credentials_for_epic_user();
		user_successfully_login_to_the_pickx_app();
		Assert.assertFalse(commonUtils.displayed(Recordings));
	}

	@Given("^User opens the PickxTV application$")
	public void user_opens_the_pickxtv_application() {
		Assert.assertTrue(commonUtils.displayed(choose_env_bar));
	}

	@When("^The User selects the environment$")
	public void the_user_selects_the_environment() throws InterruptedException {
		commonUtils.clickonElement(prod_radio_button);
		Assert.assertTrue(commonUtils.enabled(env_continue_button));
		commonUtils.clickonElement(env_continue_button);
	}
	
	@When("The User selects the {string} environment")
    public void the_User_selects_the_environment(String string) {
		if(string.equalsIgnoreCase("UAT")) {
			commonUtils.clickonElement(uat_radio_button);
			Assert.assertTrue(commonUtils.enabled(env_continue_button));
			commonUtils.clickonElement(env_continue_button);
		}
		else if(string.equalsIgnoreCase("PROD")) {
			commonUtils.clickonElement(prod_radio_button);
			Assert.assertTrue(commonUtils.enabled(env_continue_button));
			commonUtils.clickonElement(env_continue_button);
		}
		
	}

	@Then("^The user accepts the terms and condition$")
	public void the_user_accepts_the_terms_and_condition() {
		if (commonUtils.displayed(terms_and_condition_accept_button)) {
			commonUtils.clickonElement(terms_and_condition_accept_button);
		} else {

		}
	}

	@And("^User select the login option$")
	public void user_select_the_login_option() {
		the_user_accepts_the_terms_and_condition();
		commonUtils.waitTillVisibility(login_button, 20);
		Assert.assertTrue(commonUtils.displayed(login_button));
		commonUtils.clickonElement(login_button);
	}

	public void getSystemIPAddress() throws ParseException, IOException {
		closeableHttpResponse = commonUtils.get(commonUtils.getEndpointURL());

		// Getting Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is --->" + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Status code is not 200");

		// Getting JSON response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Resonse body is ---->" + responseJSON);

		/*
		 * //Getting All headers Header[] getAllHeader =
		 * closeableHttpResponse.getAllHeaders();
		 * 
		 * HashMap<String,String> allHeaders = new HashMap<>(); for (Header herader :
		 * getAllHeader) {
		 * 
		 * allHeaders.put(herader.getName(), herader.getValue()); }
		 * 
		 * System.out.println("Headers are --->" + allHeaders);
		 */
	}

	@And("^User opens the app in the background$")
	public void user_opens_the_app_in_the_background() throws Throwable {
		driver.launchApp();
		the_user_selects_the_environment();
	}
}
