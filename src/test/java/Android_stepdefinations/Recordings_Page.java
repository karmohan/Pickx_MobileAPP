package Android_stepdefinations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;

import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Recordings_Page implements Android_screens.Home_Screen, Android_screens.Recordings_Screen {

	   public static AppiumDriver<MobileElement> driver;
    public CommonUtils commonUtils;
    public Android_input_properties inputProperties;

    public Recordings_Page() throws IOException {
	driver = Hooks.getDriver();
	commonUtils = new CommonUtils(driver);
	inputProperties = new Android_input_properties();
    }

    @Given("^User is on the recordings page$")
    public void user_on_the_recordings_page()  {
	commonUtils.clickonElement(Recordings);
	commonUtils.waitTillVisibility(recorded_tab, 20);
	Assert.assertTrue(commonUtils.displayed(recorded_tab));
    }

    @When("^Validate the recordings tab in recordings page$")
    public void validate_recordings_tab_in_recordings_page() {
	Assert.assertTrue(commonUtils.displayed(recordings_container));
	try {
	    commonUtils.findElements(programs_in_recordings);
	    Assert.assertTrue(commonUtils.displayed(recordings_poster));
	    Assert.assertTrue(commonUtils.displayed(recordings_title));
	    Assert.assertTrue(commonUtils.displayed(recordings_description));
	    Assert.assertTrue(commonUtils.displayed(other_options_in_recordings));
	    Assert.assertTrue(commonUtils.displayed(recordings_icons));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @When("^User selects recorded tab$")
    public void user_selects_recorded_tab() {
	commonUtils.clickonElement(recorded_tab);
	Assert.assertTrue(commonUtils.selected(recorded_tab));
    }

    @And("^User selects planned tab$")
    public void user_selects_planned_tab() {
	commonUtils.clickonElement(planned_tab);
	Assert.assertTrue(commonUtils.selected(planned_tab));
    }

    @Then("^Validate planned recordings tab in recordings page$")
    public void validate_planned_recordings_tab_in_recordings_page() {
	Assert.assertTrue(commonUtils.displayed(recordings_container));
	try {
	    commonUtils.findElements(programs_in_recordings);
	    Assert.assertTrue(commonUtils.displayed(recordings_poster));
	    Assert.assertTrue(commonUtils.displayed(recordings_title));
	    Assert.assertTrue(commonUtils.displayed(recordings_description));
	    Assert.assertTrue(commonUtils.displayed(other_options_in_recordings));
	    Assert.assertTrue(commonUtils.displayed(recordings_icons));
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
	commonUtils.waitTillInvisibility(delete_recording_toast_message, 20);
	verify_if_recording_is_removed(index, programDetails);
    }

    @And("^Select option from recordings pop-up$")
    public void select_option_from_recordings_pop_up(String requiredOption) {
	boolean optionFound = false;
	if (commonUtils.displayed(recordings_pop_up)) {
	    List<MobileElement> recordingsOption = commonUtils.findElements(option_in_recordings_pop_up);
	    for (MobileElement option : recordingsOption) {
		if (option.getText().equalsIgnoreCase(requiredOption)) {
		    option.click();
		    optionFound = true;
		    break;
		}
	    }
	}
	if (!optionFound)
	    throw new TestException(String.format("'" + requiredOption + "' not found in the recording pop-up"));
    }

    public void verify_if_recording_is_removed(int index, Map<String, String> programDetails) {
	List<MobileElement> programList = commonUtils.findElements(programs_in_recordings);
	String displayedDescription = programList.get(index).findElement(recordings_description).getText();
	String displayedTitle = programList.get(index).findElement(recordings_title).getText();
	if (displayedTitle.equals(programDetails.get("title"))) {
	    if (displayedDescription.equals(programDetails.get("description"))) {
		try {
		    WebDriverWait wait = new WebDriverWait(driver, 30);
		    wait.until(ExpectedConditions.invisibilityOf(programList.get(index)));
		} catch (Exception e) {
		    throw new TestException(String.format(
			    programDetails.get("title") + " not removed from recordings list even after deleting it"));
		}
	    }
	}
    }

}
