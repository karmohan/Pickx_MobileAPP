package iOS_stepdefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.Assert;

import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.My_Videos_Screen;
import iOS_screens.Program_Details_Screen;
import iOS_screens.Setting_Screen;
import iOS_screens.Vod_Details_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class My_Videos_Page implements Home_Screen, My_Videos_Screen, Program_Details_Screen, Setting_Screen, Vod_Details_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public String Program_Title_Value;
	public String my_videos_recordings;
	public String my_videos_recordings_playable;
	public String my_videos_recordings_recorded;
	public String my_videos_recordings_planned;
	public String my_videos_continue_watching;
	public String name = "name";
	public String myVideoContinueWatchingText;
	public Setting_Page settingPage;
	public Program_Details_Page programDetalsPage;
	public Search_page searchPage;
	public Vod_Details_Page vodDetailsPage;

	public My_Videos_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		settingPage = new Setting_Page();
		programDetalsPage = new Program_Details_Page();
		searchPage = new Search_page();
		vodDetailsPage = new Vod_Details_Page();
    }
    
    @Given("^User is on my videos page$")
    public void user_is_on_my_videos_page() {
	commonUtils.clickonElement(my_videos_button);
	my_videos_recordings = commonUtils.getTextBasedonLanguage("Recordings_text");
	my_videos_continue_watching = commonUtils.getTextBasedonLanguage("Continue_watching");
	my_videos_recordings_playable = commonUtils.getTextBasedonLanguage("Playable_text");
	my_videos_recordings_recorded = commonUtils.getTextBasedonLanguage("Recorded_text");
	my_videos_recordings_planned = commonUtils.getTextBasedonLanguage("Planned_text");
	commonUtils.waitTillVisibility(commonUtils.findElementByAccessibilityid(my_videos_recordings), 20);
	Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(my_videos_recordings)));
	//commonUtils.waitTillVisibility(my_videos_recordings_option, 20);
	//Assert.assertTrue(commonUtils.displayed(my_videos_recordings_option));
    }
    
    @When("^User selects Recordings tab in my videos$")
    public void user_selects_Recordings_tab_in_my_videos() {
	commonUtils.clickonElement(my_videos_recordings_option);
	commonUtils.waitTillVisibility(commonUtils.findElementByXpathContains(name,my_videos_recordings_playable), 20);
	Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathContains(name,my_videos_recordings_playable)));
    }
    
    @And("^Validate recordings tab in my videos$")
    public void validate_recordings_tab_in_my_videos() {   	
	Assert.assertTrue(commonUtils.enabled(commonUtils.findElementByAccessibilityid(my_videos_recordings)));
	   //Disconnect_text = inputProperties.getElementString("Disconnect_text", commonUtils.getDeviceLanguage());
	//my_videos_recordings_playable_tab= driver.findElement(By.xpath("//*[@name='"+my_videos_recordings_playable+"']"));
	commonUtils.waitTillVisibility(commonUtils.findElementByXpathContains(name,my_videos_recordings_playable), 20);
	Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathContains(name,my_videos_recordings_playable)));
	Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathContains(name,my_videos_recordings_recorded)));
	Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathContains(name,my_videos_recordings_planned)));
    }
    
    @And("^User selects Playable tab in recordings of my videos$")
    public void user_selects_playable_tab_in_recordings_of_my_videos() {
	commonUtils.clickonElement(commonUtils.findElementByXpathContains(name,my_videos_recordings_playable));
    }
    
    @And("^Validate playable tab in recordings of my videos$")
    public void validate_playable_tab_in_recordings_of_my_videos() {
	try {
//	    commonUtils.findElements(my_videos_recordings_programs);
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_poster));
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_title));
	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_other_option));
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_description));
	    Thread.sleep(3000);
	    if(commonUtils.enabled(my_videos_recordings_program_series_recorded_icon) || commonUtils.enabled(my_videos_recordings_program_episode_recorded_icon)) {
	    	System.out.println("Recordings Icon not found in Playable");
	    }
	    else {
	    	System.out.println("Recordings Icon not found in Playable");
	    }
	   // Assert.assertTrue(commonUtils.enabled(my_videos_recordings_program_series_recorded_icon) || commonUtils.enabled(my_videos_recordings_program_episode_recorded_icon));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @Then("^User selects Recorded tab in recordings of my videos$")
    public void user_selects_recorded_tab_in_recordings_of_my_videos() {
	commonUtils.clickonElement(commonUtils.findElementByXpathContains(name,my_videos_recordings_recorded));
    }
    
    @And("^Validate recorded tab in recordings of my videos$")
    public void validate_recorded_tab_in_recordings_of_my_videos() {
	try {
//	    commonUtils.findElements(my_videos_recordings_programs);
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_poster));
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_title));
	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_other_option));
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_description));
	    Thread.sleep(3000);
	    if(commonUtils.enabled(my_videos_recordings_program_series_recorded_icon) || commonUtils.enabled(my_videos_recordings_program_episode_recorded_icon)) {
	    	System.out.println("Recordings Icon not found in recorded");
	    }
	    else {
	    	System.out.println("Recordings Icon not found in Recorded");
	    }
	   // Assert.assertTrue(commonUtils.enabled(my_videos_recordings_program_series_recorded_icon) || commonUtils.enabled(my_videos_recordings_program_episode_recorded_icon));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @Then("^User selects Planned tab in recordings of my videos$")
    public void user_selects_planned_tab_in_recordings_of_my_videos() {
	commonUtils.clickonElement(commonUtils.findElementByXpathContains(name,my_videos_recordings_planned));
    }
    
    @And("^Validate planned tab in recordings of my videos$")
    public void validate_planned_tab_in_recordings_of_my_videos() {
	try {
//	    commonUtils.findElements(my_videos_recordings_programs);
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_poster));
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_title));
	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_other_option));
//	    Assert.assertTrue(commonUtils.displayed(my_videos_recordings_program_description));
	    Thread.sleep(3000);
	    if(commonUtils.enabled(my_videos_recordings_program_series_recorded_icon) || commonUtils.enabled(my_videos_recordings_program_episode_recorded_icon)) {
	    	System.out.println("Recordings Icon not found in Planned");
	    }
	    else {
	    	System.out.println("Recordings Icon not foundin Planned");
	    }
	   // Assert.assertTrue(commonUtils.enabled(my_videos_recordings_program_series_planned_icon) || commonUtils.enabled(my_videos_recordings_program_episode_planned_icon));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    @Then("^User selects Continue Watching tab in my videos$")
    public void user_selects_continue_watching_tab_in_my_videos() {
	commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(my_videos_continue_watching));
    }
    
    @Given("^User play a video in recording tab$")
    public void user_play_a_video_in_recording_tab() {
    	validate_recordings_tab_in_my_videos();
    	user_selects_playable_tab_in_recordings_of_my_videos();
    	validate_playable_tab_in_recordings_of_my_videos();
    	commonUtils.clickonElement(my_videos_recordings_tiles);	
		if (commonUtils.enabled(parental_pin_input)) {
			settingPage.enter_parental_pin_for_programs();
			commonUtils.clickonElement(my_videos_recordings_tiles);
		}
    	// Assert.assertTrue(commonUtils.displayed(my_videos_recordings_tiles_series_play));
		if(!commonUtils.displayed(my_videos_recordings_tiles_series_play)) {
    		System.out.println("Clicked on episode....");
    	}
    	else {
    		commonUtils.clickonElement(my_videos_recordings_tiles_series_play);
       	 System.out.println("Clicked on episode....");
    	}
		String Time=commonUtils.getAttribute(my_videos_player_time, "label");
		System.out.println("Time   " +Time);
		//driver.findElement(my_videos_player_time).ge
    }

	@When("^The user see the streaming video$")
	public void the_user_see_the_streaming_video() throws InterruptedException {
		programDetalsPage.check_streaming_Error();
		//commonUtils.clickonElement(Play_pause_button_under_player_screen);
		Assert.assertTrue(commonUtils.displayed(Play_pause_button_under_player_screen));
		Program_Title_Value = Program_Title();
		if (Program_Title_Value == null)
			programDetalsPage.check_streaming_Error();
		String Program_time_and_details = commonUtils.getAttribute(program_time, "value");
		System.out.println("Program time and details  " + Program_time_and_details);
		waitOnemins();
	}

	public String Program_Title() {
		String Program_Title_Value = commonUtils.getAttribute(program_title, "value");
		System.out.println("Program title value  " + Program_Title_Value);
		return Program_Title_Value;
    }
    
    public void waitOnemins() throws InterruptedException {
    	int counter = 0;
    	while (counter < 6) {
    		Thread.sleep(12000);
    		commonUtils.clickonElement(my_videos_video_container);
    		System.out.println("The counter value of wait time  " + counter);
        	counter ++;
    	}
    }

    @And("^User navigates to continue watching the page$")
    public void user_navigates_to_continue_watching_the_page() throws InterruptedException {
    	commonUtils.clickonElement(close_button);
    	/*if(!commonUtils.displayed(my_videos_continue_watching_option)) {
    		commonUtils.clickonElement(SeriesDetailViewController_backButton);
    	}*/
    	commonUtils.clickonElement(SeriesDetailViewController_backButton);
		String myVideoContinueWatchingText = commonUtils.getTextBasedonLanguage("Continue_watching");
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(myVideoContinueWatchingText)));
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(myVideoContinueWatchingText));
    	//commonUtils.clickonElement(my_videos_continue_watching_option);
    	Thread.sleep(10000);
    }
    
    @Then("^User see the same program is available in continue watching$")
    public void user_see_the_same_program_is_available_in_continue_watching() throws InterruptedException {
    	commonUtils.waitTillVisibility(my_videos_recordings_tiles, 10);
    	commonUtils.clickonElement(my_videos_recordings_tiles);
    	String Program_Title_Value_in_continue_watching = commonUtils.getAttribute(program_title, "value");
    	System.out.println("Program title value in Continue Watching  " + Program_Title_Value_in_continue_watching);
    	Assert.assertEquals(Program_Title_Value, Program_Title_Value_in_continue_watching);
    }
    
    @Given("User select the SVOD content with the download option")
    public void user_select_the_svod_content_with_the_download_option() {
    	searchPage.the_user_is_on_search_page();
    	searchDownloadabledProgram("DownloadableMovie");
    	if(commonUtils.displayed(invalid_item_error_in_searchPage)) {
    		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("Back_Text")));
    		commonUtils.clickonElement(searchPage.searchBoxXbutton());
    		searchDownloadabledProgram("DownloadableMovie_second");
    	}
		vodDetailsPage.metadata_properly_displayed_for_programs();
    }
    
    public void searchDownloadabledProgram(String movieName) {
    	searchPage.the_user_enters_characters_in_search_box(movieName);
		commonUtils.sendKey(searchPage.searchBox(), Keys.chord(Keys.ENTER));
		List<MobileElement> swimlanes = commonUtils.findElements(program_displayed_onDemand);
		System.out.println("Number of program listed  " + swimlanes.size());
		for (MobileElement swimlane : swimlanes) {
			if (swimlane.findElement(program_title_of_searched_text).getText().contains(commonUtils.getTextBasedonLanguage(movieName))) {
				swimlane.findElement(program_title_of_searched_text).click();
				break;
			}
			else {
				continue;
			}
		}
    }
    
    @When("User navigates to {string} tab")
    public void user_navigates_to_tab(String string) {
    	Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("Downloads_text"))));
    	commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(commonUtils.getTextBasedonLanguage("Downloads_text")));
    	if(commonUtils.displayed(downloaded_programs)) {
    		System.out.println("Downloaded programs are there under MyVideos Downloads");
    		//user_verify_the_meta_details_for_the_downloaded_program();
    		Assert.assertTrue(commonUtils.displayed(age_download_symbol));
    		Assert.assertTrue(commonUtils.displayed(downloaded_content_SLA));
    	}
    	else {
    		System.out.println("No downloaded video is available");
    	}
    	
    }
  
    @When("Use check the {string} tab is highlighted")
    public void Use_check_the_tab_is_highlighted(String myvideo_tab) {
    	if (myvideo_tab.equalsIgnoreCase("Download")) {
    		Assert.assertTrue(commonUtils.displayed(downloaded_tab_highligted));
    	}
    	else if (myvideo_tab.equalsIgnoreCase("Recording")) {
    		Assert.assertFalse(commonUtils.displayed(recordings_tab_highligted));
    	}
    	else if (myvideo_tab.equalsIgnoreCase("Continue Watching")) {
    		Assert.assertFalse(commonUtils.displayed(continues_watching_tab_highligted));
    	}
    }

    @Given("^User scroll down in the page$")
    public void user_scroll_down_in_the_page() {
    	commonUtils.swipeUpOverScreen();
    }
    
    @Given("^User scroll up in the page$")
    public void user_scroll_up_in_the_page() {
    	commonUtils.swipeDownScreen();
    }
    
    @And("User verify the meta details for the downloaded program")
    public void user_verify_the_meta_details_for_the_downloaded_program() {
		Assert.assertTrue(commonUtils.displayed(option_in_download_page));
		Assert.assertTrue(commonUtils.displayed(downloaded_content_SLA));
		Assert.assertTrue(commonUtils.displayed(age_download_symbol));
		Assert.assertTrue(commonUtils.displayed(title_of_downloaded_video));
		Assert.assertTrue(commonUtils.displayed(metadata_of_downloaded_video));	
    }
    
    @Then("^User verify the meta details for the downloaded program in SVOD details page$")
    public void user_verify_the_meta_details_for_the_downloaded_program_in_svod_details_page() {
    	System.out.println("This will be implemneted after complete implementation of D2G");
    }
    
    @And("^User choose the downloaded video to play$")
    public void user_choose_the_downloaded_video_to_play() throws InterruptedException {
    	List<MobileElement> downloaded_program = commonUtils.findElements(downloaded_programs);
    	downloaded_program.get(0).click();
    	programDetalsPage.program_starts_streaming();
    	commonUtils.waitTillVisibility(live_icon_player_screen, 10);
    	int count=0;
    	while (count <10) {
    		if(commonUtils.getText(Play_pause_button_under_player_screen).equalsIgnoreCase("player play")) {
    			programDetalsPage.program_starts_streaming();
    			Thread.sleep(5000);
    			if(commonUtils.getText(Play_pause_button_under_player_screen).equalsIgnoreCase("player pause")) {
    				System.out.println("Downloaded content is playing");
    				//if(!commonUtils.getText(downloaded_video_playtime).equalsIgnoreCase(" ")) {
    				if(!commonUtils.getText(downloaded_video_playtime).isEmpty()) {
        				break;
        			}
    			}
    			else {
    				System.out.println("Downloaded video is not playing");
    				count++;
    			}
        	}
    		else if(commonUtils.getText(Play_pause_button_under_player_screen).equalsIgnoreCase("player pause")) {
    			System.out.println("Downloaded content is playing on the first hit");
				//if(!commonUtils.getText(downloaded_video_playtime).equalsIgnoreCase(" ")) {
				if(!commonUtils.getText(downloaded_video_playtime).isEmpty()) {
    				break;
    			}
    		}
    	}
    }

}
