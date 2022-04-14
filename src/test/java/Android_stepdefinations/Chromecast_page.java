package Android_stepdefinations;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import Android_screens.Chromecase_screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;
	
	public class Chromecast_page implements Chromecase_screen {
		public static AppiumDriver<MobileElement> driver;
		public CommonUtils commonUtils;
		public Android_input_properties inputProperties;

		public Chromecast_page() throws IOException {
			driver = Hooks.getDriver();
			commonUtils = new CommonUtils(driver);
			inputProperties = new Android_input_properties();
		}
 
		@Given("^The user tab on the Chromecast icon$")
	    public void the_user_tab_on_the_chromecast_icon() {
		   Assert.assertTrue(commonUtils.displayed(chromecast_button));
		   commonUtils.clickonElement(chromecast_button);
	    }

	    @When("^The user see a list of available casting option$")
	    public void the_user_see_a_list_of_available_casting_option() {
	    	Assert.assertEquals(commonUtils.getText(chromecast_available_device_text), commonUtils.getTextBasedonLanguage_Android("Choose_a_device_text"));
	    	Assert.assertTrue(commonUtils.displayed(chromecast_device_icon));
	    	//String displayed_deviceName= commonUtils.getText(chromecast_device_name);
	    }
	    
	    @And("^User select a device$")
	    public void user_select_a_device() {
	    	 List<MobileElement> displayed_deviceName = (List<MobileElement>) commonUtils.findElements(chromecast_device_name);
	    	 for(MobileElement devicename : displayed_deviceName) {
	    		 String value = devicename.getText();
	    		 if(value.equalsIgnoreCase(inputProperties.getchromecast_devicename())) {
	    			 devicename.click();
	    		 }
	    	 }
	    }
	    
	    @Then("^User see the device is being connected to the selected device$")
	    public void user_see_the_device_is_being_connected_to_the_selected_device() {
			 Assert.assertTrue(commonUtils.displayed(chromecast_button));
			   commonUtils.clickonElement(chromecast_button);
			   String connected_device = commonUtils.getText(chromecast_connected_device_name);
			   assertEquals(connected_device, inputProperties.getchromecast_devicename());
		    	Assert.assertEquals(commonUtils.getText(chromecast_disconnect_button), commonUtils.getTextBasedonLanguage_Android("Disconnect_text"));
			  // Assert.assertTrue(commonUtils.displayed(chromecast_disconnect_button));
	    }
	    
	    @Given("^The user connected to the casting device$")
	    public void the_user_connected_to_the_casting_device() {
	    	the_user_tab_on_the_chromecast_icon();
	    	the_user_see_a_list_of_available_casting_option();
	    	user_select_a_device();
	    	user_see_the_device_is_being_connected_to_the_selected_device();
	    }

	    @When("^The user disconnected cast$")
	    public void the_user_disconnected_cast() {
	    	commonUtils.clickonElement(chromecast_disconnect_button);
	    }

	    @Then("^User can see Chromecast option is to cast a new device$")
	    public void user_can_see_chromecast_option_is_to_cast_a_new_device() {
	    	the_user_tab_on_the_chromecast_icon();
	    	the_user_see_a_list_of_available_casting_option();
	    }
}
