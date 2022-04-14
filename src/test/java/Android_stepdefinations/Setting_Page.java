package Android_stepdefinations;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import Android_screens.Home_Screen;
import Android_screens.Login_Screen;
import Android_screens.Setting_Screen;
import base.Android_input_properties;
import config.Hooks;
import Android_stepdefinations.Program_Details_Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Setting_Page implements Setting_Screen, Home_Screen, Login_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Android_input_properties inputProperties;
	AndroidDriver<MobileElement> androidDriver;
	public Program_Details_Page program_Details_Page;
	public String enterPinCode_fortheFirsttime_Text;
	public String pinChange_Text;
	public String reconfirmPinChange_Text;
	public String feedback_Text;

	public Setting_Page() throws IOException {
		driver = Hooks.getDriver();
		this.androidDriver=(AndroidDriver<MobileElement>) driver;
		commonUtils = new CommonUtils(driver);
		inputProperties = new Android_input_properties();
		program_Details_Page = new Program_Details_Page();
	}

	@Given("^The User is on settings page$")
	public void the_user_is_on_settings_page()  {
		commonUtils.clickonElement(settings_button);
		commonUtils.waitTillVisibility(parental_control, 30);
		Assert.assertTrue(commonUtils.displayed(parental_control));
	}

	@When("^User login to Parental control$")
	public void user_login_to_parental_control()  {
		commonUtils.clickonElement(parental_control);
		enterPinCode_fortheFirsttime_Text = commonUtils.getTextBasedonLanguage_Android("enterPinCode_fortheFirsttime");
		enterPinCode(enter_pin_title_text,enterPinCode_fortheFirsttime_Text,inputProperties.getpinValue());
		if(commonUtils.displayed(incorrect_PIN_text)) {
			enterPinCode(enter_pin_title_text,enterPinCode_fortheFirsttime_Text,inputProperties.getnewPin());
		}
		Assert.assertTrue(commonUtils.displayed(parental_control_toggle_id));
	}

	@And("^User turn on Parental control$")
	public void user_turn_on_parental_control()  {
		commonUtils.clickonElement(select_the_age_restriction);
		//if (commonUtils.displayed(parental_control_toggle_point_on)) {
		if (!commonUtils.displayed(parental_seek_bar)) {
			commonUtils.clickonElement(parental_control_toggle_id);
		} else {
			driver.navigate().back();
			//Assert.assertTrue(commonUtils.displayed(parental_control_toggle_point_on));
		}
	}

	@Then("^User turn off Parental control$")
	public void user_turn_off_parental_control() {
		commonUtils.clickonElement(select_the_age_restriction);
		try {
			commonUtils.waitTillVisibility(parental_seek_bar, 15);
			driver.navigate().back();
			commonUtils.clickonElement(parental_control_toggle_id);
		} catch (Exception e) {
			System.out.println("Catch");
			if (!commonUtils.displayed(parental_seek_bar)) {
				System.out.println(" not Displayed seek bar");
			}
		}
	}
	
//	@Then("^User turn off Parental control$")
//	public void user_turn_off_parental_control() {
//		if (commonUtils.enabled(parental_control_toggle_point_on)) {
//			commonUtils.clickonElement(parental_control_toggle_id);
//			Assert.assertTrue(commonUtils.displayed(parental_control_toggle_point_off));
//		} else {
//
//		}
//	}

    @When("^User open age restriction option$")
    public void user_open_age_restriction_option()  {
		commonUtils.clickonElement(age_restriction_toggle_id);
    }

    @And("^User select the age restriction$")
    public void user_select_the_age_restriction()  {
    	commonUtils.waitTillVisibility(age_restriction_18_icon, 5);
		Assert.assertTrue(commonUtils.displayed(age_restriction_18_icon));
		if (commonUtils.selected(age_restriction_12_icon)) {
			
		} else {
			commonUtils.clickonElement(age_restriction_12_icon);
		}
    }
    
    @Then("^The selected age is updated accordingly$")
    public void the_selected_age_is_updated_accordingly()  {
    	Assert.assertTrue(commonUtils.displayed(selected_age_value));
    }
    
    @When("^User click on change pin code$")
    public void user_click_on_change_pin_code()  {
    	commonUtils.clickonElement(parental_control_change_pin);
    	Assert.assertTrue(commonUtils.displayed(enter_pin_title_text));
    	enterPinCode(enter_pin_title_text,enterPinCode_fortheFirsttime_Text,inputProperties.getpinValue());
    	if(commonUtils.displayed(incorrect_PIN_text)) {
    		enterPinCode(enter_pin_title_text,enterPinCode_fortheFirsttime_Text,inputProperties.getnewPin());
		}
    }
    
    @And("^User enters new pin$")
    public void user_enters_new_pin()  {
    	commonUtils.waitTillVisibility(enter_pin_title_text, 5);
    	pinChange_Text = commonUtils.getTextBasedonLanguage_Android("pinChangeText");
    	enterPinCode(enter_pin_title_text,pinChange_Text,inputProperties.getnewPin());
    }

    @And("^User enter pin again$")
    public void user_enter_pin_again()  {
    	commonUtils.waitTillVisibility(enter_pin_title_text, 5);
    	reconfirmPinChange_Text = commonUtils.getTextBasedonLanguage_Android("reconfirmPinChangeText");
    	enterPinCode(enter_pin_title_text,reconfirmPinChange_Text,inputProperties.getnewPin());
    }

    @Then("^Pin code changed successfully$")
    public void pin_code_changed_successfully()  {
    	Assert.assertTrue(commonUtils.displayed(parental_control_change_pin));
    }
    
    @Given("^User click on send feedback option$")
    public void user_click_on_send_feedback_option()  {
    	commonUtils.clickonElement(send_feedback);
    	Assert.assertTrue(commonUtils.displayed(feedback_text_box));
    }

    @When("^User enter feedback and email id$")
    public void user_enter_feedback_and_email_id()  {
    	feedback_Text = commonUtils.getTextBasedonLanguage_Android("feedback");
		commonUtils.sendKey(feedback_text_box, feedback_Text);
		commonUtils.sendKey(email_text_box, inputProperties.getemail());
    }

    @Then("^user submit the feedback$")
    public void user_submit_the_feedback()  {
    	commonUtils.clickonElement(feedback_submit_button);
    	Assert.assertTrue(commonUtils.displayed(send_feedback));
    }
    
    public void enterPinCode(By locator, String message, String pinvalue) {
    	String text = commonUtils.getText(locator);
		if (text.equalsIgnoreCase(message)) {
			String pin = pinvalue;
			for (int i = 0; i < pin.length(); i++) {
				char pinValue = pin.charAt(i);
				switch (pinValue) {
				case '0':
					commonUtils.clickonElement(input_pin_0);
					break;
				case '1':
					commonUtils.clickonElement(input_pin_1);
					break;
				case '2':
					commonUtils.clickonElement(input_pin_2);
					break;
				case '3':
					commonUtils.clickonElement(input_pin_3);
					break;
				case '4':
					commonUtils.clickonElement(input_pin_4);
					break;
				case '5':
					commonUtils.clickonElement(input_pin_5);
					break;
				case '6':
					commonUtils.clickonElement(input_pin_6);
					break;
				case '7':
					commonUtils.clickonElement(input_pin_7);
					break;
				case '8':
					commonUtils.clickonElement(input_pin_8);
					break;
				case '9':
					commonUtils.clickonElement(input_pin_9);
					break;
				}

			}
		}
    }
    
    @And("^User select the age restriction as ten$")
    public void user_select_the_age_restriction_as_ten()  {
		commonUtils.waitTillVisibility(age_restriction_18_icon, 10);
	Assert.assertTrue(commonUtils.displayed(age_restriction_18_icon));
	if (!commonUtils.selected(age_restriction_10_icon))
	    commonUtils.clickonElement(age_restriction_10_icon);
    }

    @And("^User navigates back to home page$")
    public void user_navigates_back_to_home_page() {
	click_on_back_button();
	if(!commonUtils.displayed(home_button)) {
		commonUtils.clickonElement(back_button);
	}
		commonUtils.waitTillVisibility(home_button, 30);
	Assert.assertTrue(commonUtils.displayed(home_button));
    }

	public void click_on_back_button() {
		commonUtils.clickonElement(back_button);
		commonUtils.clickonElement(back_button);
		commonUtils.clickonElement(back_button);
	}

	public void enter_parental_pin_for_programs() {
		commonUtils.displayed(parental_pin_input);
		commonUtils.sendKey(parental_pin_input, inputProperties.getpinValue());
		commonUtils.clickonElement(parental_pin_confirm_button);
		if (commonUtils.displayed(parental_pin_input)) {
			commonUtils.sendKey(parental_pin_input, inputProperties.getnewPin());
			commonUtils.clickonElement(parental_pin_confirm_button);
		}
		
		if (commonUtils.displayed(parental_pin_input)) {
			commonUtils.sendKey(parental_pin_input, inputProperties.newSecondPin());
			commonUtils.clickonElement(parental_pin_confirm_button);
		}
		
	}
	
	public void enter_wrong_parental_pin_for_programs() {
		commonUtils.displayed(parental_pin_input);
		commonUtils.sendKey(parental_pin_input, inputProperties.wrongPin());
		commonUtils.clickonElement(parental_pin_confirm_button);
	}

	@Given("^User navigates to home page$")
	public void user_navigates_to_home_page() {
		commonUtils.clickonElement(home_button);
		Assert.assertTrue(commonUtils.displayed(home_button));
	}
	
	@And("^User turn on wifi$")
    public void user_turn_on_wifi() throws InterruptedException{
		wifi_enable();
		Thread.sleep(5000);
	}
	
	@And("^User turn off wifi$")
    public void user_turn_off_wifi() throws InterruptedException{
		wifi_disable();
		Thread.sleep(2000);
	}
	
	public void wifi_disable() {
		ConnectionState wifi_disable_state = ((AndroidDriver<MobileElement>) driver).setConnection(new ConnectionStateBuilder()
				.withWiFiDisabled()
				.build());
	}
	
	public void wifi_enable() {
	ConnectionState wifi_disable_state = ((AndroidDriver<MobileElement>) driver).setConnection(new ConnectionStateBuilder()
			.withWiFiEnabled()
			.build());
	}
	
	 @And("^User gets the offline notification$")
	    public void user_gets_the_offline_notification() throws InterruptedException {
		 Thread.sleep(3000);
		 commonUtils.displayed(loading_icon);
		 Assert.assertTrue(!commonUtils.enabled(continue_button));
	    }
	 
	    @And("^User gets the offline message while using app$")
	    public void user_gets_the_offline_message_while_using_app() {
			 program_Details_Page.click_on_play_pause_button_over_player_screen();
	    	Assert.assertTrue(commonUtils.displayed(Please_connect_to_network_text));
	    	Assert.assertTrue(commonUtils.displayed(try_again_button));
	    	//Assert.assertTrue(commonUtils.displayed(you_are_offline_text));
	    }
}