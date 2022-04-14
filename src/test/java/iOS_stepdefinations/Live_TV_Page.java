package iOS_stepdefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Home_Screen;
import iOS_screens.LiveTV_Screen;
import iOS_screens.Program_Details_Screen;
import iOS_screens.Setting_Screen;
import iOS_screens.TVguide_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Live_TV_Page
		implements Home_Screen, LiveTV_Screen, Setting_Screen, Program_Details_Screen, TVguide_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public iOS_input_properties inputProperties;
	public Setting_Page settingPage;
	public iOS_stepdefinations.Program_Details_Page programDetailsPage;
	public String live_page_title_text;

	public Live_TV_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		inputProperties = new iOS_input_properties();
		settingPage = new Setting_Page();
		programDetailsPage = new iOS_stepdefinations.Program_Details_Page();
	}

	@Then("^User navigates to live TV page$")
	public void user_navigates_to_live_tv() {
		commonUtils.clickonElement(liveTV_button);
		commonUtils.waitTillPresenceOfElement(live_tv_channel_icon, 15);
		live_page_title_text = commonUtils.getTextBasedonLanguage("liveTvPageTitle");
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByAccessibilityid(live_page_title_text)));
	//	Assert.assertTrue(inputProperties.getLiveTvPageTitle().equalsIgnoreCase(commonUtils.getText(commonUtils.findElementByAccessibilityid(live_page_title_text))));
	}

	@Given("^Validate liveTV loading skeleton$")
	public void validate_livetv_loading_skeleton() {
		commonUtils.waitTillVisibility(livetv_navigationbar, 30);
		Assert.assertTrue(commonUtils.displayed(category_button_container));
		Assert.assertTrue(commonUtils.displayed(liveTV_skeleton));
		Assert.assertTrue(commonUtils.displayed(livetv_navigationbar));

	}

	@Given("^Categories are displayed$")
	public void categories_are_displayed() throws InterruptedException {
		Assert.assertTrue(commonUtils.displayed(category_button_container));
	}

	@Then("^Validate the displayed categoriesssssss$")
	public void validate_categories_new() throws InterruptedException {
		//List<String> categoriesDisplayed = new ArrayList<String>();
		 Set<String> categoriesDisplayedName = new HashSet<String>(); 
		int counter = 0;
		String temp = "";
		while (counter < 3) {
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			for (WebElement category : categoryElements){
				String categoryName = category.getText();
				System.out.println("categoryName  "+categoryName);
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				categoriesDisplayedName.add(categoryName.toLowerCase());
			}
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			//System.out.println("lastCategory  "+ counter  + "    "+ lastCategory);
			/*for (WebElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				categoriesDisplayed.add(categoryName.toLowerCase());
			}*/
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
		System.out.println("categoriesDisplayedName.size();     "+categoriesDisplayedName.size());
		System.out.println("categoriesDisplayedName  "+categoriesDisplayedName);
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("all")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("movies")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("series")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("entertainment")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("sports")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("discovery")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("kids")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("music")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("news")));
	}
	
	@Then("^Validate the displayed categories$")
	public void validate_categories() throws InterruptedException {
		//List<String> categoriesDisplayed = new ArrayList<String>();
		 Set<String> categoriesDisplayedName = new HashSet<String>(); 
		int counter = 0;
		String temp = "";
		while (counter < 1) {
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			for (WebElement category : categoryElements){
				try {
					String categoryName = category.getText();
					if (categoryName.contains("("))
						categoryName = categoryName.split("\\(")[0].trim();
					categoriesDisplayedName.add(categoryName.toLowerCase());
		        } catch (StaleElementReferenceException e) {
		        	String categoryName = category.getText();
		        	if (categoryName.contains("("))
						categoryName = categoryName.split("\\(")[0].trim();
					categoriesDisplayedName.add(categoryName.toLowerCase());
		        }
			}
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			if (lastCategory.contains("("))
				lastCategory = lastCategory.split("\\(")[0].trim();
			/*for (WebElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				categoriesDisplayed.add(categoryName.toLowerCase());
				new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.id("checkoutLink")));
driver.findElement(By.id("checkoutLink")).click();
			}*/
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			if (!categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("all"))) 
			commonUtils.swipeRightOverElement(categoryElements.get(categoryElements.size() - 1));
			for (WebElement category : categoryElements){
				try {
					String categoryName = category.getText();
					if (categoryName.contains("("))
						categoryName = categoryName.split("\\(")[0].trim();
					categoriesDisplayedName.add(categoryName.toLowerCase());
		        } catch (StaleElementReferenceException e) {
		        	String categoryName = category.getText();
		        	if (categoryName.contains("("))
						categoryName = categoryName.split("\\(")[0].trim();
					categoriesDisplayedName.add(categoryName.toLowerCase());
		        }
			}
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("all")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("movies")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("series")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("entertainment")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("sports")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("discovery")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("kids")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("music")));
		Assert.assertTrue(categoriesDisplayedName.contains(commonUtils.getTextBasedonLanguage("news")));
	}

	@When("^User can see metadata of the program tiles$")
	public void user_can_see_program_metadata() {
		try {
			commonUtils.findElements(program);
		} catch (Exception e) {
			throw new TestException(String.format("Programs not found in live TV"));
		}
		Assert.assertTrue(commonUtils.enabled(live_tv_program_poster));
		Assert.assertTrue(commonUtils.enabled(live_tv_channel_icon));
		Assert.assertTrue(commonUtils.enabled(live_tv_progress_bar));
		Assert.assertTrue(commonUtils.enabled(live_tv_program_title));
		Assert.assertTrue(commonUtils.enabled(live_tv_program_time));
	}

	@And("^User selects movies category$")
	public void user_selects_movies_category() {
		System.out.println("Entered");
//	int counter = 0;
//	String temp = "";
//	boolean categoryFound = false;
//	while (counter < 3) {
//		commonUtils.waitTillVisibility(categories, 40);
//		System.out.println("waited");
//	    List<MobileElement> categoryElements = commonUtils.findElements(categories);
//	    String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
//	    System.out.println("lastCategory      "+lastCategory);
//	    for (MobileElement category : categoryElements) {
//		String categoryName = category.getText();
//		System.out.println("categoryName      "+categoryName);
//		if (categoryName.toLowerCase().contains(inputProperties.getMovies())) {
//			System.out.println("If");
//		    category.click();
//		    categoryFound = true;
//		    break;
//		}
//	    }
//	    if (categoryFound)
//		break;
//	    if (temp.equals(lastCategory))
//		counter++;
//	    temp = lastCategory;
//	    System.out.println("Counter "+ counter );
//	    commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
//	}
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathContains("name", commonUtils.getTextBasedonLanguage("live_movies"))));
		commonUtils.findElement(commonUtils.findElementByXpathContains("name", commonUtils.getTextBasedonLanguage("live_movies"))).click();
		System.out.println("Movie Clicked");
//	if (!categoryFound)
//	    throw new TestException(String.format("Movies category not found"));
	}

	@And("^Verify if the program is unlocked$")
	public void verify_if_the_program_is_unlocked(){
		System.out.println("Pgm locked");
		int counter = 0;
		String temp = "";
		String lastProgramTitle = null;
		boolean lockedProgramFound = false;
		while (counter < 3) {
			commonUtils.waitTillVisibility(live_tv_title_metadata1, 20);
			List<MobileElement> programList = commonUtils.findElements(live_tv_title_metadata1);
			System.out.println(programList.size() + "    programList.size()");
			try {
				lastProgramTitle = programList.get(programList.size() - 1).findElement(live_tv_program_title).getText();
				System.out.println("lastProgramTitle  " + lastProgramTitle);
			} catch (Exception e) {
				lastProgramTitle = programList.get(programList.size() - 2).findElement(live_tv_program_title).getText();
				System.out.println("lastProgramTitle  " + lastProgramTitle);
			}
			for (int i = 0; i < programList.size(); i++) {
				commonUtils.waitTillVisibility(live_tv_title_metadata1, 20);
				programList = commonUtils.findElements(live_tv_title_metadata1);
				if (!programList.get(i).isDisplayed()) {
					continue;
				}
//				List<MobileElement> icon = new ArrayList<MobileElement>();
//				try {
//					icon = programList.get(i).findElements(age_icon);
//				} catch (Exception e) {
//					programList = commonUtils.findElements(program_description);
//					icon = programList.get(i).findElements(age_icon);
//				}
//				if (icon.isEmpty())
//					continue;
//				if (!programList.get(i).findElement(live_tv_program_title).getText().equalsIgnoreCase("Locked content"))
//					throw new TestException(String.format("Program not locked even after enabling parental control"));

				programList.get(i).click();
				programDetailsPage.check_streaming_Error();
				if (!commonUtils.displayed(parental_pin_input)) {
					commonUtils.findElement(close_button).click();
					continue;
				}
				programDetailsPage.check_streaming_Error();
				settingPage.enter_parental_pin_for_programs();
				commonUtils.waitTillInvisibility(Locked_icon, 30);
				programList = commonUtils.findElements(live_tv_title_metadata1);
				String title;
				title = programList.get(i).findElement(live_tv_program_title).getText();
				System.out.println("Title   " + title);
				if (title.equalsIgnoreCase("Locked content"))
					throw new TestException(
							String.format("Program didn't unlock even after entering parental control pin"));
				lockedProgramFound = true;
				break;
			}
			if (lockedProgramFound)
				break;
			if (lastProgramTitle.equals(temp))
				counter++;
			temp = lastProgramTitle;
			commonUtils.swipeUpOverScreen();
		}
	}

	@Then("^Verify the filters are horizontally scrollable$")
	public void verify_the_filters_are_horizontally_scrollable() {
		List<MobileElement> categoriesElementList = new ArrayList<MobileElement>();
		List<String> categoriesDisplayedBeforeSwipe = new ArrayList<String>();
		List<String> categoriesDisplayedAfterSwipe = new ArrayList<String>();
		List<MobileElement> categoryElements = new ArrayList<MobileElement>();

//		if (commonUtils.findElement(categories).isDisplayed()) {
//			categoryElements = commonUtils.findElements(categories);
//		}
//		System.out.println("categoryElements.size   " + categoryElements.size());
//		if(categoryElements.size()==0){
		categoryElements = commonUtils.findElements(categories);
		for (MobileElement category : categoryElements) {
			if (category.isDisplayed()) {
				System.out.println("category.getText()  " + category.getText());
				categoriesElementList.add(category);
			}
		}
		System.out.println("categoryElements.size   " + categoryElements.size());
//		}
		for (MobileElement category : categoriesElementList) {
			System.out.println("category.getText()  " + category.getText());
			categoriesDisplayedBeforeSwipe.add(category.getText());
		}
		commonUtils.swipeLeftOverElement(categoriesElementList.get(categoriesElementList.size() - 1));
		categoryElements = commonUtils.findElements(categories);
		for (MobileElement category : categoryElements) {
			System.out.println("category.getText()  " + category.getText());
			categoriesDisplayedAfterSwipe.add(category.getText());
		}
		System.out.println(categoriesDisplayedAfterSwipe.size());
		if (categoriesDisplayedAfterSwipe.size() == 9) {
			System.out.println("Pickx app in Tablet in landscape mode ");
		} else if (categoriesDisplayedAfterSwipe.equals(categoriesDisplayedBeforeSwipe))
			throw new TestException(String.format("Live TV categories are not horizontally scrollable"));
		commonUtils.swipeRightOverElement(categoryElements.get(0));
		commonUtils.swipeRightOverElement(categoryElements.get(0));
	}

	@And("^Verify respective programs are displayed for each selected category$")
	public void verify_respective_programs_are_displayed_for_each_selected_category() throws Throwable {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String previousProgram = "";
		while (counter < 3) {
			Thread.sleep(30000);
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText().split("\\(")[0].trim()
					.toLowerCase();
			System.out.println("categoryElements " + categoryElements.size());
//			for (int i = 0; i < categoryElements.size() - 1; i++) {
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				System.out.println("categoryName " + categoryName);
				if (categoryName == "")
					continue;

				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim().toLowerCase();
				System.out.println("categoryName " + categoryName);
				if (categoriesDisplayed.contains(categoryName))
					continue;
				categoriesDisplayed.add(categoryName);
				if(categoryName.equalsIgnoreCase(commonUtils.getTextBasedonLanguage("all"))) {
					continue;
				}
				System.out.println("categoriesDisplayed " + categoriesDisplayed);
				category.click();
				commonUtils.waitTillVisibility(live_tv_program_title, 30);
				String currentProgram = commonUtils.getElement(live_tv_program_title).getText();
				if (currentProgram.equalsIgnoreCase("Locked content")) {
					System.out.println("");
					commonUtils.clickonElement(live_tv_program_title);
					commonUtils.clickonElement(live_tv_program_title);
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					currentProgram = commonUtils.getElement(live_tv_program_title).getText();
				}
				System.out.println("currentProgram  " + currentProgram);
				if (previousProgram.equals(currentProgram))
					throw new TestException(
							String.format("Program list not changed when different category is selected"));
				previousProgram = currentProgram;
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			Thread.sleep(30000);
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
	}

	@Then("^User validates series recording of live airing from live TV$")
	public void validates_series_recording_of_live_airing_from_livetv() throws Throwable {
		System.out.println(" IN validates_series_recording_of_live_airing_from_livetv ");
		select_recordable_item_from_livetv(true);
		Map<String, String> programDetails = programDetailsPage
				.records_series_program_and_validate_updated_details(false);
		programDetailsPage.close_program_details_page_to_reach_livetv();
		MobileElement program = verify_recording_icon_present_over_program_in_livetv(programDetails);
		program.click();
		programDetailsPage.stop_recording_series(false);
		programDetailsPage.delete_recording();
	}

	public void select_recordable_item_from_livetv(boolean isSeries) {
		System.out.println("IN select_recordable_item_from_livetv");
		boolean programFound = false;
		List<String> checkedPrograms = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			List<MobileElement> programDescriptionList = commonUtils.findElements(program_description);
			System.out.println("programDescriptionList.size()" + programDescriptionList.size());
			for (int j = 0; j < programDescriptionList.size() - 1; j++) {
				String programName;
				System.out.println("Forloop");
				programDescriptionList = commonUtils.findElements(program_description);

				if (!commonUtils.findElements(live_tv_program_title).get(j).isDisplayed()) {
					continue;
				}

				try {
					System.out.println("commonUtils.findElements(program_title_live).get(j).getText()"
							+ commonUtils.findElements(live_tv_program_title).get(j).getText());
					programName = commonUtils.findElements(live_tv_program_title).get(j).getText();
//		    		programName = programDescriptionList.get(j).findElement(program_title_live).getText();

				} catch (Exception e) {
					programDescriptionList = commonUtils.findElements(program_description);
					System.out.println("Catch");
					programName = commonUtils.findElements(live_tv_program_title).get(j - 1).getText();
				}
				System.out.println("Programname   " + programName);
				if (checkedPrograms.contains(programName))
					continue;
				checkedPrograms.add(programName);
				System.out.println("checkedPrograms      " + checkedPrograms);
				commonUtils.findElements(live_tv_program_title).get(j).click();
//				programDescriptionList.get(0).click();
				System.out.println("Clicked");
				if (programDetailsPage.is_program_to_be_recorded()) {
					if (programDetailsPage.verify_if_ongoing_program_is_part_of_series() == isSeries) {
						programFound = true;
						break;
					}
				}
				programDetailsPage.close_program_details_page_to_reach_livetv();
			}
			if (programFound)
				break;
			System.out.println("SWIPE");
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find a series program from live TV for recording"));
	}

	public MobileElement verify_recording_icon_present_over_program_in_livetv(Map<String, String> programDetails) {
		boolean programFound = false;
		List<MobileElement> programDescriptionList = commonUtils.findElements(program_description);
		String programName = programDetails.get("title");
		System.out.println("verify_recording_icon_present_over_program_in_livetv");
		System.out.println("programName      " + programName);
		for (int j = 0; j < programDescriptionList.size() - 1; j++) {
			programDescriptionList = commonUtils.findElements(program_description);
			if (!commonUtils.findElements(live_tv_program_title).get(j).isDisplayed()) {
				continue;
			}
			if (commonUtils.findElements(live_tv_program_title).get(j).getText().contains(programName)) {
				programFound = true;
//		if (commonUtils.findElements(program_title_live).get(j).findElements(recording_icon_in_homescreen).size() < 1)
//		    throw new TestException(
//			    String.format("Recording icon not displayed under the program tile in live tv page"));
				return commonUtils.findElements(live_tv_program_title).get(j).findElement(live_tv_program_title);
			}
		}
		System.out.println("end    verify_recording_icon_present_over_program_in_livetv");
		if (!programFound)
			throw new TestException(String.format("Couldn't find " + programName + " in the live TV"));
		return null;
	}

	@Then("^User validates replayable program from live TV$")
	public void user_validates_replayable_program_from_live_TV() throws Throwable {
		boolean programFound = false;
		for (int i = 0; i < 15; i++) {
			commonUtils.waitTillVisibility(live_tv_program_title, 20);
			List<MobileElement> programList = commonUtils.findElements(live_tv_program_title);
			for (int j = 0; j < programList.size(); j++) {
				Thread.sleep(12000);
//				commonUtils.waitTillVisibility(live_tv_program_title, 20);
				programList = commonUtils.findElements(live_tv_program_title);
				if (!programList.get(j).isDisplayed()) {
					continue;
				}
				commonUtils.findElements(live_tv_program_title).get(j).click();
				programList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					break;
//					programList = commonUtils.findElements(live_tv_program_title);
//					programList.get(j).click();
				}
				programDetailsPage.check_streaming_Error();
//				Assert.assertTrue(commonUtils.displayed(player_screen_live));
				if (programDetailsPage.is_program_replayable()) {
					Assert.assertTrue(programDetailsPage.is_program_replayable());
					programDetailsPage.close_program_details_page_to_reach_livetv();
					programFound = true;
					break;
				}
				programDetailsPage.close_program_details_page_to_reach_livetv();
			}
			if(programFound)
				break;
			commonUtils.swipeUpOverScreen();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find live replayable program from live TV"));
	}

	@Then("^User validates non-replayable program from live TV$")
	public void user_validates_non_replayable_live_program_from_live_TV() throws InterruptedException {
		boolean programFound = false;
		commonUtils.waitTillVisibility(live_tv_program_title, 20);
		for (int i = 0; i < 15; i++) {
			List<MobileElement> programList = commonUtils.findElements(live_tv_program_title);
			System.out.println("programList.size() " + programList.size());
			for (int j = 0; j < programList.size(); j++) {
				Thread.sleep(10000);
//				commonUtils.waitTillVisibility(live_tv_program_title, 20);
				programList = commonUtils.findElements(live_tv_program_title);
				if (!programList.get(j).isDisplayed())
					continue;
				System.out.println(commonUtils.findElements(live_tv_program_title).get(j).getText());
				if(commonUtils.findElements(live_tv_program_title).get(j).getText().equalsIgnoreCase("Locked content")) {
					continue;
				}
				commonUtils.findElements(live_tv_program_title).get(j).click();
				programList.get(j).click();
				if (commonUtils.enabled(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(Locked_icon, 20);
					break;
//					programList = commonUtils.findElements(live_tv_program_title);
//					commonUtils.findElements(live_tv_program_title).get(j).click();
				}
				programDetailsPage.check_streaming_Error();
//        			Assert.assertTrue(commonUtils.displayed(player_screen_live));
        	    	if (!programDetailsPage.is_program_replayable()) {
        				Assert.assertFalse(programDetailsPage.is_program_replayable());
        				programDetailsPage.close_program_details_page_to_reach_livetv();
        				programFound=true;
        				break;
        		    }
        	    	programDetailsPage.close_program_details_page_to_reach_livetv();
        	    }
        	    if (programFound)
        	    	break;
        	    commonUtils.swipeUpOverScreen();
        	}	   
        	if (!programFound)
        	    throw new TestException(String.format("Failed to find non replayable program from live TV"));
    }
	

}
