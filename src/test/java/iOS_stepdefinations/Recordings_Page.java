package iOS_stepdefinations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.Recordings_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Recordings_Page implements Home_Screen, Recordings_Screen{
    
    public static AppiumDriver<MobileElement> driver;
    public CommonUtils commonUtils;
    public iOS_input_properties inputProperties;
    
    public Recordings_Page() throws IOException {
	driver = Hooks.getDriver();
	commonUtils = new CommonUtils(driver);
	inputProperties = new iOS_input_properties();
    }
    
    @Given("^User is on the recordings page$")
    public void user_on_the_recordings_page() throws InterruptedException {
	commonUtils.clickonElement(Recordings);
	commonUtils.waitTillVisibility(recorded_tab, 20);
	Assert.assertTrue(commonUtils.displayed(recorded_tab));
    }
    
    @When("^User selects recorded tab$")
    public void user_selects_recorded_tab() {
	commonUtils.clickonElement(recorded_tab);
	Assert.assertTrue(commonUtils.selected(recorded_tab));
    }
    
    @When("^Validate the recordings tab in recordings page$")
    public void validate_recordings_tab_in_recordings_page() {
	Assert.assertTrue(commonUtils.displayed(recordings_container));
	try {
	    commonUtils.findElements(programs_in_recordings);
	    Assert.assertTrue(commonUtils.enabled(recordings_poster));
	    Assert.assertTrue(commonUtils.displayed(recordings_title));
	    Assert.assertTrue(commonUtils.displayed(recordings_description));
	    Assert.assertTrue(commonUtils.displayed(other_options_in_recordings));
	    Assert.assertTrue(commonUtils.enabled(recordings_icons));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @And("^User select the program in recordings to stream$")
    public void user_select_the_program_in_recordings_to_stream() {
    	commonUtils.clickonElement(programs_in_recordings);
    	Assert.assertTrue(commonUtils.displayed(recordings_series_episode));	
    	commonUtils.clickonElement(recordings_series_episode);
    }
    
    @And("^User selects planned tab$")
    public void user_selects_planned_tab() {
	commonUtils.clickonElement(planned_tab);
	Assert.assertTrue(commonUtils.enabled(planned_tab));
    }
    
    @Then("^Validate planned recordings tab in recordings page$")
    public void validate_planned_recordings_tab_in_recordings_page() {
	Assert.assertTrue(commonUtils.displayed(recordings_container));
	try {
	    commonUtils.findElements(programs_in_recordings);
	    Assert.assertTrue(commonUtils.enabled(recordings_poster));
	    Assert.assertTrue(commonUtils.displayed(recordings_title));
	    //Assert.assertTrue(commonUtils.displayed(recordings_description));
	    Assert.assertTrue(commonUtils.displayed(other_options_in_recordings));
	    Assert.assertTrue(commonUtils.enabled(recordings_icons));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @Then("^Verify single record deletion from recordings using kebab$")
    public void verify_single_record_deletion_from_recordings_using_kebab() {
	int counter = 0;
	boolean programFound = false;
	String temp = "";
	int index = -1;
	Map<String, String> programDetails = new HashMap<String, String>();
	while (counter < 3) {
	    List<MobileElement> recordings = commonUtils.findElements(programs_in_recordings);
	    String lastProgram;
	    try {
		lastProgram = recordings.get(recordings.size() - 1).findElement(recordings_title).getText();
	    } catch (Exception e) {
		lastProgram = recordings.get(recordings.size() - 2).findElement(recordings_title).getText();
	    }
	    for (int i = 0; i < recordings.size(); i++) {
		// Verify if the recording is single or series
		String recordingDescription;
		try {
		    recordingDescription = recordings.get(i).findElement(recordings_description).getText();
		} catch (Exception e) {
		    continue;
		}
		if (recordingDescription.contains("ep."))
		    continue;
		String recordingName = recordings.get(i).findElement(recordings_title).getText();
		recordings.get(i).findElement(other_options_in_recordings).click();
		programFound = true;
		index = i;
		programDetails.put("title", recordingName);
		programDetails.put("description", recordingDescription);
		break;
	    }
	    if (programFound)
		break;
	    if (lastProgram.equals(temp))
		counter++;
	    temp = lastProgram;
	    commonUtils.swipeUpOverRecordingsScreen();
	}
	if (!programFound)
	    throw new TestException(String.format("Failed to find a single recording from recordings list"));
	select_option_from_recordings_pop_up(inputProperties.getDeleteRecording());
	//  toast message feature not found in ios. need to verify
//	commonUtils.waitTillInvisibility(delete_recording_toast_message, 20);
	verify_if_recording_is_removed(index, programDetails);
    }
    
    @And("^Select option from recordings pop-up$")
    public void select_option_from_recordings_pop_up(String requiredOption) {
	boolean optionFound = false;
	if (commonUtils.displayed(recordings_pop_up)) {
	    if(requiredOption.equalsIgnoreCase(inputProperties.getDeleteRecording())) {
		commonUtils.clickonElement(delete_recording_option);
		optionFound = true;
	    }else if(requiredOption.equalsIgnoreCase(inputProperties.getRecordSeries())) {
		commonUtils.clickonElement(record_series_option);
		optionFound = true;
	    }
	}
	if (!optionFound)
	    throw new TestException(String.format("'" + requiredOption + "' not found in the recording pop-up"));
    }
    
    public void verify_if_recording_is_removed(int index, Map<String, String> programDetails) {
	List<MobileElement> programList = commonUtils.findElements(programs_in_recordings);
	MobileElement elementToBeRemoved = programList.get(index); 
	String displayedDescription = elementToBeRemoved.findElement(recordings_description).getText();
	String displayedTitle = elementToBeRemoved.findElement(recordings_title).getText();
	if (displayedTitle.equals(programDetails.get("title"))) {
	    if (displayedDescription.equals(programDetails.get("description"))) {
		try {
		    WebDriverWait wait = new WebDriverWait(driver, 30);
		    wait.until(ExpectedConditions.invisibilityOf(elementToBeRemoved));
		} catch (Exception e) {
		    throw new TestException(String.format(
			    programDetails.get("title") + " not removed from recordings list even after deleting it"));
		}
	    }
	}
    }

}
