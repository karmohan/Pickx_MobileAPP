package iOS_stepdefinations;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.Assert;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.Setting_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Setting_Page implements Setting_Screen, Home_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public iOS_input_properties inputProperties;
	private String BUNDLE_ID_SETTINGS = "com.apple.Preferences";
	public String enterPinCode_fortheFirsttime_text;
	public By enterPinCode_fortheFirsttime;
	public String incorrectPinText;
	public By incorrect_pin_text;
	public String enter_new_pin_title_text;
	public By enter_new_pin_title;
	public String reenter_pin_title_text;
	public By reenter_pin_title;
	public String feedback;
	public String feedback_submit_button_text;
	public By feedback_submit_button;
	public int parentalControl;
	public String navigate_back_text;
	

	public Setting_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new iOS_input_properties();
	}
	
	public String getSettingsBundleID() { 
		return BUNDLE_ID_SETTINGS; 
		}

	@Given("^The User is on settings page$")
	public void the_user_is_on_settings_page() throws Throwable {
		commonUtils.clickonElement(settings_button);
		Assert.assertTrue(commonUtils.displayed(parental_control));
	}

	@When("^User login to Parental control$")
	public void user_login_to_parental_control() throws Throwable {
		commonUtils.clickonElement(parental_control);
		enterPinCode_fortheFirsttime_text = commonUtils.getTextBasedonLanguage("enterPinCode_fortheFirsttime");
		enterPinCode_fortheFirsttime = commonUtils.findElementByAccessibilityid(enterPinCode_fortheFirsttime_text);
		incorrectPinText = commonUtils.getTextBasedonLanguage("incorrectPinText");
		incorrect_pin_text = commonUtils.findElementByAccessibilityid(incorrectPinText);		
		enterPinCode(enterPinCode_fortheFirsttime, enterPinCode_fortheFirsttime_text,
				inputProperties.getpinValue());
		if (commonUtils.displayed(incorrect_pin_text)) {
			enterPinCode(enterPinCode_fortheFirsttime, enterPinCode_fortheFirsttime_text,
					inputProperties.getnewPin());
		}
		if (commonUtils.displayed(incorrect_pin_text)){
			enterPinCode(enterPinCode_fortheFirsttime, enterPinCode_fortheFirsttime_text,
					inputProperties.getnewPinSecond());
		}
		Assert.assertTrue(commonUtils.displayed(parental_control_toggle_id));
	}

//    	@And("^User turn on Parental control$")
//    	public void user_turn_on_parental_control() throws Throwable {	
//    		commonUtils.clickonElement(age_restriction);
//    		//if (commonUtils.displayed(parental_control_toggle_point_on)) {
//    		if (!commonUtils.displayed(seekBar)) {
//    			//commonUtils.clickonElement(back_navigator);	
//    			commonUtils.clickonElement(parental_control_toggle_id);
//    		} 
//    		else {
//    			//commonUtils.clickonElement(parental_control_toggle_id);	
//    			iosBackButtonLocationInUpperLeftCorner = PointOption.point(0, 44);
//    			TouchAction action = new TouchAction(driver);
//    			action.tap(iosBackButtonLocationInUpperLeftCorner).perform();
//    			//Assert.assertTrue(commonUtils.displayed(parental_control_toggle_point_on));
//    		}
//    	}

    	@Then("^User turn off Parental control$")
    	public void user_turn_off_parental_control() throws Throwable {
    		commonUtils.clickonElement(parental_control_toggle_id);
    		System.out.println("Parental control is turned off");
    	}
    	
        @When("^User open age restriction option$")
        public void user_open_age_restriction_option() throws Throwable {
        	commonUtils.waitTillVisibility(age_restriction, 20);
    		commonUtils.clickonElement(age_restriction);
        }

        @And("^User select the age restriction$")
        public void user_select_the_age_restriction() throws Throwable {
        	//commonUtils.waitTillVisibility(age_restriction_18_icon, 5);
        	Thread.sleep(2000);
    		Assert.assertTrue(commonUtils.displayed(age_restriction_18_icon));
    		if (commonUtils.selected(age_restriction_12_icon)) {
    			
    		} else {
    			commonUtils.clickonElement(age_restriction_12_icon);
    		}
        }
        
        @Then("^The selected age is updated accordingly$")
        public void the_selected_age_is_updated_accordingly() throws Throwable {
        	Assert.assertTrue(commonUtils.getText(selected_age_value).contains("12"));
        }
        
        @When("^User click on change pin code$")
        public void user_click_on_change_pin_code() throws Throwable {
        	commonUtils.clickonElement(parental_control_change_pin);
        	Assert.assertTrue(commonUtils.displayed(enterPinCode_fortheFirsttime));
        	enterPinCode(enterPinCode_fortheFirsttime,enterPinCode_fortheFirsttime_text,inputProperties.getpinValue());
        	if(commonUtils.displayed(incorrect_pin_text)) {
        		enterPinCode(enterPinCode_fortheFirsttime,enterPinCode_fortheFirsttime_text,inputProperties.getnewPin());
    		}
        	if (commonUtils.displayed(incorrect_pin_text)){
    			enterPinCode(enterPinCode_fortheFirsttime, enterPinCode_fortheFirsttime_text,
    					inputProperties.getnewPinSecond());
    		}
        }
        
        @And("^User enters new pin$")
        public void user_enters_new_pin() throws Throwable {
        	enter_new_pin_title_text = commonUtils.getTextBasedonLanguage("pinChangeText");
        	enter_new_pin_title = commonUtils.findElementByAccessibilityid(enter_new_pin_title_text);
        	commonUtils.waitTillVisibility(enter_new_pin_title, 5);
        	enterPinCode(enter_new_pin_title,enter_new_pin_title_text,inputProperties.getnewPin());
        }

        @And("^User enter pin again$")
        public void user_enter_pin_again() throws Throwable {
        	reenter_pin_title_text = commonUtils.getTextBasedonLanguage("reconfirmPinChangeText");
        	reenter_pin_title = commonUtils.findElementByAccessibilityid(reenter_pin_title_text);
        	commonUtils.waitTillVisibility(reenter_pin_title, 5);
        	enterPinCode(reenter_pin_title,reenter_pin_title_text,inputProperties.getnewPin());
        }

        @Then("^Pin code changed successfully$")
        public void pin_code_changed_successfully() throws Throwable {
        	commonUtils.waitTillVisibility(parental_control_change_pin, 10);
        	Assert.assertTrue(commonUtils.displayed(parental_control_change_pin));
        }
        
        @Given("^User click on send feedback option$")
        public void user_click_on_send_feedback_option() throws Throwable {
        	commonUtils.clickonElement(send_feedback);
        	Assert.assertTrue(commonUtils.displayed(your_feedback_text));
        }

        @When("^User enter feedback and email id$")
        public void user_enter_feedback_and_email_id() throws Throwable {
        	feedback = commonUtils.getTextBasedonLanguage("feedback");
    		commonUtils.sendKey(feedback_text_box, feedback);
    		commonUtils.sendKey(email_text_box, inputProperties.getemail());
        }

        @Then("^user submit the feedback$")
        public void user_submit_the_feedback() throws Throwable {
        	feedback_submit_button_text = commonUtils.getTextBasedonLanguage("Feedback_Submit");
        	feedback_submit_button = commonUtils.findElementByAccessibilityid(feedback_submit_button_text);
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

	@And("^User turn on Parental control$")
	public void user_turn_on_parental_control() throws Throwable {
		 parentalControl = Integer
				.parseInt(commonUtils.findElement(parental_control_toggle_point).getAttribute("value"));

		if (parentalControl == 1) {
			System.out.println("Parental control is already on.....");
		} else {
			System.out.println("Parental control is off.....");
			commonUtils.clickonElement(parental_control_toggle_id);
			Assert.assertTrue(parentalControl == 0);
		}
	}




	@And("^User select the age restriction as ten$")
	public void user_select_the_age_restriction_as_ten() throws Throwable {
		Thread.sleep(2000);
		Assert.assertTrue(commonUtils.enabled(age_restriction_18_icon));
		if (!commonUtils.selected(age_restriction_10_icon))
			commonUtils.clickonElement(age_restriction_10_icon);
	}

	@And("^User navigates back to home page$")
	public void user_navigates_back_to_home_page() {
		click_on_back_button();
		Assert.assertTrue(commonUtils.displayed(home_button));
	}

	public void click_on_back_button() {
		navigate_back_text = commonUtils.getTextBasedonLanguage("navigate_back");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(navigate_back_text));
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(navigate_back_text));
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(navigate_back_text));
	}

	public void enter_parental_pin_for_programs() {
		System.out.println("Parental pin");
		//commonUtils.enabled(parental_pin_input);
		if (commonUtils.enabled(parental_pin_input)) {
			commonUtils.sendKey(parental_pin_input, inputProperties.getpinValue());
			commonUtils.clickonElement(parental_pin_confirm_button);
		}
		if (commonUtils.displayed(invalid_parental_pin)) {
			commonUtils.sendKey(parental_pin_input, inputProperties.getnewPin());
			commonUtils.clickonElement(parental_pin_confirm_button);
		}
		if (commonUtils.displayed(invalid_parental_pin)) {
			commonUtils.sendKey(parental_pin_input, inputProperties.getnewPinSecond());
			commonUtils.clickonElement(parental_pin_confirm_button);
		}
	}
	
	public String getWifiStatus()
	{
	    String wifiStatus = null;
	    do {
	       wifiStatus = commonUtils.getElement(wifi_switch_in_settings).getAttribute("value");
	    } while (wifiStatus == null);
	    System.out.println("Current WiFi Status: " + wifiStatus);
	    return wifiStatus;
	}
	
	@And("^User turn off wifi$")
    public void user_turn_off_wifi() throws InterruptedException {	
	    HashMap<String, Object> iOSSettingsAppArgs = new HashMap<>();
	    iOSSettingsAppArgs.put("bundleId", getSettingsBundleID());
	 //   Hooks.getDriver().
	    driver.executeScript("mobile: terminateApp", iOSSettingsAppArgs);
	    driver.executeScript("mobile: launchApp", iOSSettingsAppArgs);
	    commonUtils.clickonElement(wifi_cell_in_settings);

	    // checking if WiFi is ON before turning it OFF
	    if (getWifiStatus().equalsIgnoreCase("1"))
	    {
	        System.out.println("WIFI turned ON, Turning it OFF ..");
	        do {
	        	commonUtils.clickonElement(wifi_switch_in_settings);
	            //Thread.sleep(2000);
	        } while (getWifiStatus().equalsIgnoreCase("1"));
	    }else if (getWifiStatus().equalsIgnoreCase("0")) {
	        System.out.println("WIFI already OFF ..");
	    }
	    driver.executeScript("mobile: terminateApp", iOSSettingsAppArgs);
	   // driver.runAppInBackground(Duration.ofSeconds(-1));
    }
	
    @And("^User put the app in the background$")
    public void user_put_the_app_in_the_background() {
    	driver.runAppInBackground(Duration.ofSeconds(6));
    }
	
	@And("^User turn on wifi$")
    public void user_turn_on_wifi() throws InterruptedException {
	    HashMap<String, Object> iOSSettingsAppArgs = new HashMap<>();
	    iOSSettingsAppArgs.put("bundleId", getSettingsBundleID());
	    driver.executeScript("mobile: terminateApp", iOSSettingsAppArgs);
	    driver.executeScript("mobile: launchApp", iOSSettingsAppArgs);
	    commonUtils.clickonElement(wifi_cell_in_settings);

	    // checking if WiFi is OFF before turning it ON
	    if (getWifiStatus().equalsIgnoreCase("0"))
	    {
	        System.out.println("WiFi turned OFF, Turning it ON ..");
	        do {
	        	commonUtils.clickonElement(wifi_switch_in_settings);
	            //Thread.sleep(2000);
	        } while (getWifiStatus().equalsIgnoreCase("0"));
	    }else if (getWifiStatus().equalsIgnoreCase("1")) {
	        System.out.println("WiFi already ON ..");
	    }
	    driver.executeScript("mobile: terminateApp", iOSSettingsAppArgs);
	}
	
	 @And("^User gets the offline message$")
	    public void user_gets_the_offline_message() {
		 Assert.assertTrue(commonUtils.getText(wifi_offline_text).equalsIgnoreCase(commonUtils.getTextBasedonLanguage("offline_Text")));
		 Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathValueName("name", commonUtils.getTextBasedonLanguage("try_again"))));
	    }
	
}
