package Android_stepdefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.TestException;

import Android_screens.Home_Screen;
import Android_screens.LiveTV_Screen;
import Android_screens.Program_Details_Screen;
import Android_screens.Setting_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Live_Tv_Page implements Home_Screen, LiveTV_Screen, Setting_Screen, Program_Details_Screen {

	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Android_input_properties inputProperties = new Android_input_properties();
	public Setting_Page settingPage;
	public Program_Details_Page programDetailsPage;
	public String Bottom_navigation_LiveTV_text;

	public Live_Tv_Page() throws IOException {
		driver = Hooks.getDriver();
		commonUtils = new CommonUtils(driver);
		settingPage = new Setting_Page();
		programDetailsPage = new Program_Details_Page();
	}

	@Then("^User navigates to live TV page$")
	public void user_navigates_to_live_tv() throws Exception {
		if (commonUtils.displayed(parental_pin_input))
			commonUtils.clickonElement(parental_pin_cancel_button);
		//commonUtils.clickonElement(liveTV_button);
		Bottom_navigation_LiveTV_text = commonUtils.getTextBasedonLanguage_Android("Bottom_navigation_LiveTv");
		commonUtils.clickonElement(commonUtils.findElementByAccessibilityid(Bottom_navigation_LiveTV_text));
		try {
			commonUtils.waitTillVisibility(live_tv_channel_icon, 30);
		}
		catch (Exception e){
			commonUtils.waitTillVisibility(live_tv_channel_icon, 15);
		}
		Assert.assertTrue(commonUtils.getTextBasedonLanguage_Android("liveTvPageTitle").equalsIgnoreCase(commonUtils.getText(page_title)));
	}

	@Given("^Categories are displayed$")
	public void categories_are_displayed() {
		Assert.assertTrue(commonUtils.displayed(category_button_container));
	}

	@Then("^Validate the displayed categories$")
	public void validate_categories() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		while (counter < 3) {
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				if (!categoriesDisplayed.contains(categoryName))
					categoriesDisplayed.add(categoryName.toLowerCase());
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("all")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("movies")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("series")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("entertainment")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("sports")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("discovery")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("kids")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("music")));
		Assert.assertTrue(categoriesDisplayed.contains(commonUtils.getTextBasedonLanguage_Android("news")));
	}

	@When("^User can see metadata of the program tiles$")
	public void user_can_see_program_metadata() {
		try {
			commonUtils.findElements(program);
		} catch (Exception e) {
			throw new TestException(String.format("Programs not found in live TV"));
		}
		Assert.assertTrue(commonUtils.displayed(live_tv_program_poster));
		Assert.assertTrue(commonUtils.displayed(live_tv_channel_icon));
		Assert.assertTrue(commonUtils.displayed(live_tv_progress_bar));
		Assert.assertTrue(commonUtils.displayed(live_tv_program_title));
		Assert.assertTrue(commonUtils.displayed(live_tv_program_time));
	}

	@And("^User selects movies category$")
	public void user_selects_movies_category() {
		int counter = 0;
		String temp = "";
		boolean categoryFound = false;
		while (counter < 3) {
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.toLowerCase().contains(commonUtils.getTextBasedonLanguage_Android("movies"))) {
					category.click();
					categoryFound = true;
					break;
				}
			}
			if (categoryFound)
				break;
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
		if (!categoryFound)
			throw new TestException(String.format("Movies category not found"));
	}

	@And("^Verify if the program is unlocked$")
	public void verify_if_the_program_is_unlocked() throws InterruptedException {
		int counter = 0;
		String temp = "";
		String lastProgramTitle = null;
		boolean lockedProgramFound = false;
		while (counter < 3) {
			commonUtils.waitTillVisibility(program_description, 15);
			List<MobileElement> programList = commonUtils.findElements(program_description);
			try {
				lastProgramTitle = programList.get(programList.size() - 1).findElement(live_tv_program_title).getText();
			} catch (Exception e) {
				lastProgramTitle = programList.get(programList.size() - 2).findElement(live_tv_program_title).getText();
			}

			for (int i = 0; i < programList.size(); i++) {
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(i).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(program_description);
//					commonUtils.waitTillVisibility(age_icon, 15);
					icon = programList.get(i).findElements(age_icon);
				}
				if (icon.isEmpty())
					continue;
//				if (!programList.get(i).findElement(live_tv_program_title).getText().equalsIgnoreCase("Locked content"))
//					throw new TestException(String.format("Program not locked even after enabling parental control"));
				programList.get(i).click();
				settingPage.enter_parental_pin_for_programs();
				try {
					commonUtils.waitTillInvisibility(lock_icon, 30);
				} catch (Exception e) {
					throw new TestException(
							String.format("Program didn't unlock even after entering parental control pin(Lock icon)"));
				}
				String title;
				programList = commonUtils.findElements(program_description);
				title = programList.get(i).findElement(live_tv_program_title).getText();
				if (title.equalsIgnoreCase("Locked content"))
					throw new TestException(
							String.format("Program didn't unlock even after entering parental control pin(Title)"));
				lockedProgramFound = true;
				break;
			}
			if (lockedProgramFound)
				break;
			if (lastProgramTitle.equals(temp))
				counter++;
			temp = lastProgramTitle;
			commonUtils.swipeUpOverHomeScreen();
		}
	}

	@Then("^Verify the filters are horizontally scrollable$")
	public void verify_the_filters_are_horizontally_scrollable() {
		List<String> categoriesDisplayedBeforeSwipe = new ArrayList<String>();
		List<String> categoriesDisplayedAfterSwipe = new ArrayList<String>();
		List<MobileElement> categoryElements = commonUtils.findElements(categories);
		for (MobileElement category : categoryElements) {
			categoriesDisplayedBeforeSwipe.add(category.getText());
		}
		commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		categoryElements = commonUtils.findElements(categories);
		for (MobileElement category : categoryElements) {
			categoriesDisplayedAfterSwipe.add(category.getText());
		}
		if (categoriesDisplayedAfterSwipe.size() == 9) {
			System.out.println("Pickx app in Tablet in landscape mode ");
		} else if (categoriesDisplayedAfterSwipe.equals(categoriesDisplayedBeforeSwipe))
			throw new TestException(String.format("Live TV categories are not horizontally scrollable"));
		commonUtils.swipeRightOverElement(categoryElements.get(0));
	}

	/*@And("^Verify respective programs are displayed for each selected category$")
	public void verify_respective_programs_are_displayed_for_each_selected_category() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String previousProgram = "";
		while (counter < 3) {
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			System.out.println("categoryElements ................... " +categoryElements);
			System.out.println("lastCategory ................... " +lastCategory);
			for (MobileElement category : categoryElements) {
				System.out.println("category ................... " +category);
				if (!category.isDisplayed()) {
					continue;
				}
				String categoryName = category.getText();
				System.out.println("categoryName ................... " +categoryName);
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim().toLowerCase();
				System.out.println("categoryName ................... " +categoryName);
				if (categoriesDisplayed.contains(categoryName))
					continue;
				categoriesDisplayed.add(categoryName);
				System.out.println("categoriesDisplayed ................... " +categoriesDisplayed);
				//if(categoryName.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("all")))
				if(categoryName.equalsIgnoreCase("all"))
					continue;
				category.click();
				// Since category name is not displayed for the programs verifying if respective
				// programs are displayed for each category is impossible without backend data.
				// So checking if the program changes when new category is selected
				commonUtils.waitTillVisibility(live_tv_program_title, 30);
				String currentProgram = commonUtils.getElement(live_tv_program_title).getText();
				System.out.println("currentProgram ................... " +currentProgram);
				if (currentProgram.equalsIgnoreCase("Locked content")) {
					System.out.println("In parental control");
					commonUtils.getElement(live_tv_program_title).click();
					settingPage.enter_parental_pin_for_programs();
					try {
						commonUtils.waitTillInvisibility(lock_icon, 20);
					} catch (Exception e) {
						throw new TestException(
								String.format("Program was locked even after entering parental control pin."));
					}
					
					currentProgram = commonUtils.getElement(live_tv_program_title).getText();
				}
				System.out.println(currentProgram);
				if (previousProgram.equals(currentProgram))
					throw new TestException(
							String.format("Program list not changed when different category is selected"));
				previousProgram = currentProgram;
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
	}*/
	
	@And("^Verify respective programs are displayed for each selected category$")
	public void verify_respective_programs_are_displayed_for_each_selected_category() throws InterruptedException {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String previousProgram = "";
		while (counter < 3) {
			List<MobileElement> categoryElements = commonUtils.findElements(categories);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				Thread.sleep(10000);
				commonUtils.waitTillVisibility(program_description,30);
				if (!category.isDisplayed()) {
					continue;
				}
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim().toLowerCase();
				if (categoriesDisplayed.contains(categoryName))
					continue;
				categoriesDisplayed.add(categoryName);
				if(categoryName.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("all"))) 
				//if(categoryName.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("all_text")))
					continue;
				category.click();
				// Since category name is not displayed for the programs verifying if respective
				// programs are displayed for each category is impossible without backend data.
				// So checking if the program changes when new category is selected
				commonUtils.waitTillVisibility(live_tv_program_title, 30);
				String currentProgram = commonUtils.getElement(live_tv_program_title).getText();

				if (currentProgram.equalsIgnoreCase("Locked content")) {
					System.out.println("In parental control");
					commonUtils.getElement(live_tv_program_title).click();
					settingPage.enter_parental_pin_for_programs();
					try {
						commonUtils.waitTillInvisibility(lock_icon, 20);
					} catch (Exception e) {
						throw new TestException(
								String.format("Program was locked even after entering parental control pin."));
					}
					
					currentProgram = commonUtils.getElement(live_tv_program_title).getText();
				}
				System.out.println("currentProgram   " + currentProgram);
				if (previousProgram.equals(currentProgram))
					throw new TestException(
							String.format("Program list not changed when different category is selected"));
				previousProgram = currentProgram;
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			commonUtils.swipeLeftOverElement(categoryElements.get(categoryElements.size() - 1));
		}
	}

    @Given("^User records and validate episode of live airing from live TV$")
    public void user_records_and_validate_live_episode_from_livetv() {
	select_recordable_item_from_livetv(true);
	Map<String, String> programDetails = programDetailsPage.records_series_program_and_validate_updated_details(true);
	programDetailsPage.close_program_details_page_to_reach_livetv();
	MobileElement program = verify_recording_icon_present_over_program_in_livetv(programDetails);
	program.click();
	programDetailsPage.stop_recording_episode();
	programDetailsPage.delete_recording();
    }
    
	public void select_recordable_item_from_livetv(boolean isSeries) {
		boolean programFound = false;
		List<String> checkedPrograms = new ArrayList<String>();
		for (int i = 0; i < 15; i++) {
			List<MobileElement> programDescriptionList = commonUtils.findElements(program_description);
			for (int j = 0; j < programDescriptionList.size(); j++) {
				programDescriptionList = commonUtils.findElements(program_description);
				if (!programDescriptionList.get(j).isDisplayed())
					continue;
				String programName;
				try {
					programName = programDescriptionList.get(j).findElement(live_tv_program_title).getText();
				} catch (Exception e) {
					programDescriptionList = commonUtils.findElements(program_description);
					programName = programDescriptionList.get(j).findElement(live_tv_program_title).getText();
				}
				if (checkedPrograms.contains(programName))
					continue;
				checkedPrograms.add(programName);
				programDescriptionList.get(j).click();
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
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find a series program from live TV for recording"));
	}

	public MobileElement verify_recording_icon_present_over_program_in_livetv(Map<String, String> programDetails) {
		boolean programFound = false;
		List<MobileElement> programDescriptionList = commonUtils.findElements(program_description);
		String programName = programDetails.get("title");
		for (MobileElement programDescription : programDescriptionList) {
			programDescriptionList = commonUtils.findElements(program_description);
			if (programDescription.findElement(live_tv_program_title).getText().contains(programName)) {
				programFound = true;
				if (programDescription.findElements(recording_icon_in_homescreen).size() < 1)
					throw new TestException(
							String.format("Recording icon not displayed under the program tile in live tv page"));
				return programDescription.findElement(live_tv_program_title);
			}
		}
		if (!programFound)
			throw new TestException(String.format("Couldn't find " + programName + " in the live TV"));
		return null;
	}

	@Then("^User validates series recording of live airing from live TV$")
	public void validates_series_recording_of_live_airing_from_livetv() {
		select_recordable_item_from_livetv(true);
		Map<String, String> programDetails = programDetailsPage
				.records_series_program_and_validate_updated_details(false);
		programDetailsPage.close_program_details_page_to_reach_livetv();
		MobileElement program = verify_recording_icon_present_over_program_in_livetv(programDetails);
		program.click();
		programDetailsPage.stop_recording_series(false);
		programDetailsPage.delete_recording();
	}

	@Then("^User validates recording of live airing not part of series from live TV$")
	public void user_validates_recording_of_live_airing_not_part_of_series_from_livetv() {
		// Since movies category has mostly non-series item navigating to movies
		// category
		user_selects_movies_category();
		select_recordable_item_from_livetv(false);
		Map<String, String> programDetails = programDetailsPage
				.records_non_series_program_and_validate_updated_details(true);
		programDetailsPage.close_program_details_page_to_reach_livetv();
		MobileElement program = verify_recording_icon_present_over_program_in_livetv(programDetails);
		program.click();
		programDetailsPage.stop_recording_non_series_item();
		programDetailsPage.delete_recording();
	}

	@Given("^Validate liveTV loading skeleton$")
	public void validate_livetv_loading_skeleton() {
		Assert.assertTrue(commonUtils.displayed(category_button_container));
		Assert.assertTrue(commonUtils.displayed(liveTV_skeleton));
		Assert.assertTrue(commonUtils.displayed(toolbar));
	}

	@Then("^User validates live replayable program from live TV$")
	public void user_validates_live_replayable_program_from_live_TV() {
		boolean programFound = false;
		boolean isLive = true;
		for (int i = 0; i < 15; i++) {
			List<MobileElement> programDescriptionList = commonUtils.findElements(program_description);
			for (int j = 0; j < programDescriptionList.size(); j++) {
				String programName;
				programDescriptionList = commonUtils.findElements(program_description);
				if(programDescriptionList.isEmpty())
					continue;
				if (!programDescriptionList.get(j).isDisplayed())
					continue;
				try {
					programDescriptionList.get(j).findElement(live_tv_program_title).isDisplayed();
				} catch (Exception e) {
					System.out.println("Program title not displayed");
					continue;
				}
				try {
					programName = programDescriptionList.get(j).findElement(live_tv_program_title).getText();
					System.out.println(programName);

				} catch (Exception e) {
					programDescriptionList = commonUtils.findElements(program_description);
					programName = programDescriptionList.get(j).findElement(live_tv_program_title).getText();
				}
				programDescriptionList.get(j).click();
				if (commonUtils.displayed(parental_pin_input)) {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					programDescriptionList = commonUtils.findElements(program_description);
					programDescriptionList.get(j).click();
				}
//				Assert.assertTrue(commonUtils.displayed(player_screen_live));
				if (programDetailsPage.is_program_replayable(isLive)) {
					Assert.assertTrue(programDetailsPage.is_program_replayable(isLive));
					programFound = true;
					break;
				}
				programDetailsPage.close_program_details_page_to_reach_livetv();
			}

			if (programFound) {
				programDetailsPage.close_program_details_page_to_reach_livetv();
				break;
			}
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find live replayable program from live TV"));
		System.out.println("REPLAY");
	}

	@Then("^User validates non-replayable live program from live TV$")
	public void user_validates_non_replayable_live_program_from_live_TV() throws Throwable {
		System.out.println("NON REPLAY");
		boolean programFound = false;
		boolean isLive = true;
		String programName;
		for (int i = 0; i < 40; i++) {
			Thread.sleep(10000);
			List<MobileElement> programDescriptionList = commonUtils.findElements(program_description);
			for (int j = 0; j < programDescriptionList.size(); j++) {
				programDescriptionList = commonUtils.findElements(program_description);
				try {
					if (!programDescriptionList.get(j).isDisplayed()) {
						System.out.println("No program decriptionlist continue");
						continue;
					}
					programName = programDescriptionList.get(j).findElement(live_tv_program_title).getText();
					System.out.println(programName);
					programDescriptionList.get(j).click();
				} catch (Exception e) {
					System.out.println("Catch No program decriptionlist continue");
					continue;
				}
				if (commonUtils.displayed(parental_pin_input)) {
					try {
						settingPage.enter_parental_pin_for_programs();
						commonUtils.waitTillInvisibility(lock_icon, 20);
//						programDescriptionList = commonUtils.findElements(program_description);
//						programDescriptionList.get(j).click();
						continue;
					} catch (Exception e) {
						continue;
					}
				}
//				Assert.assertTrue(commonUtils.displayed(player_screen_live));
				if (!programDetailsPage.is_program_replayable(isLive)) {
					Assert.assertFalse(programDetailsPage.is_program_replayable(isLive));
					programFound = true;
					break;
				}
				programDetailsPage.close_program_details_page_to_reach_livetv();
			}
			if (programFound)
				break;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!programFound)
			throw new TestException(String.format("Failed to find non replayable program from live TV"));
	}

	@And("^User selects unlocked program from Live TV$")
	public void user_selects_unlocked_program_from_LiveTV() {
		int counter = 0;
		String temp = "";
		String lastProgramTitle = null;
		boolean unlockedProgramFound = false;
		while (counter < 3) {
//			Thread.sleep(40000);			
			commonUtils.waitTillVisibility(program_description, 15);
			List<MobileElement> programList = commonUtils.findElements(program_description);
			try {
				lastProgramTitle = programList.get(programList.size() - 1).findElement(live_tv_program_title).getText();
			} catch (Exception e) {
				lastProgramTitle = programList.get(programList.size() - 2).findElement(live_tv_program_title).getText();
			}

			for (int i = 0; i < programList.size(); i++) {
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(i).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(program_description);
					icon = programList.get(i).findElements(age_icon);
				}
				if (icon.isEmpty())
					continue;
				programList.get(i).click();

				if (commonUtils.displayed(parental_pin_input))
					throw new TestException(String.format("Program is locked even after disabling parental control"));
				unlockedProgramFound = true;
				programDetailsPage.close_program_details_page_to_reach_livetv();
				System.out.println("Program found in Live TV");
				break;
			}
			if (unlockedProgramFound)
				break;
			if (lastProgramTitle.equals(temp))
				counter++;
			temp = lastProgramTitle;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!unlockedProgramFound)
			throw new TestException(String.format("Unlocked program is not found in Live TV page"));
	}

	@And("^User selects program without age rating from Live TV$")
	public void user_selects_program_without_age_rating_from_LiveTV() {
		int counter = 0;
		String temp = "";
		String lastProgramTitle = null;
		boolean unlockedProgramFound = false;
		while (counter < 3) {
			commonUtils.waitTillVisibility(program_description, 15);
			List<MobileElement> programList = commonUtils.findElements(program_description);
			try {
				lastProgramTitle = programList.get(programList.size() - 1).findElement(live_tv_program_title).getText();
			} catch (Exception e) {
				lastProgramTitle = programList.get(programList.size() - 2).findElement(live_tv_program_title).getText();
			}

			for (int i = 0; i < programList.size(); i++) {
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(i).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(program_description);
					icon = programList.get(i).findElements(age_icon);
				}
				if (!icon.isEmpty())
					continue;
				programList.get(i).click();

				if (commonUtils.displayed(parental_pin_input))
					throw new TestException(String.format("Program is locked even after disabling parental control"));
				unlockedProgramFound = true;
				programDetailsPage.close_program_details_page_to_reach_livetv();
				break;
			}
			if (unlockedProgramFound)
				break;
			if (lastProgramTitle.equals(temp))
				counter++;
			temp = lastProgramTitle;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!unlockedProgramFound)
			throw new TestException(String.format("Program withour age rating is not found in Live TV page"));
	}
	
	@And("^User selects locked program from Live TV$")
	public void user_selects_locked_program_from_live_TV() {
		commonUtils.clickonElement(liveTV_button);
		boolean lockedProgramFound = false;
		for (int i = 0; i < 40; i++) {
			List<MobileElement> programList = commonUtils.findElements(program_description);
			if (commonUtils.findElements(age_icon).size() > 0) {
				commonUtils.findElement(age_icon).click();
				if (!commonUtils.displayed(parental_pin_cancel_button))
					throw new TestException(
							String.format("Program is not locked even after enabling parental control-Live TV"));
				commonUtils.clickonElement(parental_pin_cancel_button);
				programList = commonUtils.findElements(program_description);
				commonUtils.findElement(age_icon).click();
				if (!commonUtils.displayed(parental_pin_input))
					throw new TestException(String.format("Program is unlocked without entering pin-Live TV"));
				lockedProgramFound = true;
				System.out.println("Program found in Live TV");
				break;
			} else {
				commonUtils.swipeUpOverHomeScreen();
				commonUtils.swipeUpOverHomeScreen();
			}
			
//			
//			
//			for (int j = 0; j < programList.size(); j++) {
//				commonUtils.waitTillVisibility(program_description, 15);
//				programList = commonUtils.findElements(program_description);
//				List<MobileElement> icon = new ArrayList<MobileElement>();
//				try {
//					icon = programList.get(j).findElements(age_icon);
//				} catch (Exception e) {
//					programList = commonUtils.findElements(program_description);
//					icon = programList.get(j).findElements(age_icon);
//				}
//				if (icon.isEmpty())
//					continue;
//				System.out.println(programList.get(j).findElement(live_tv_program_title).getText());
//				programList.get(j).click();
//
//				if (!commonUtils.displayed(parental_pin_cancel_button))
//					throw new TestException(
//							String.format("Program is not locked even after enabling parental control-Live TV"));
//				commonUtils.clickonElement(parental_pin_cancel_button);
//				programList = commonUtils.findElements(program_description);
//				programList.get(j).click();
//				if (!commonUtils.displayed(parental_pin_input))
//					throw new TestException(String.format("Program is unlocked without entering pin-Live TV"));
//				lockedProgramFound = true;
//				System.out.println("Program found in Live TV");
//				break;
//			}
//			if (lockedProgramFound)
//				break;
//			commonUtils.swipeUpOverHomeScreen();
//			commonUtils.swipeUpOverHomeScreen();
		}
		if (!lockedProgramFound)
			throw new TestException(String.format("Locked Program not found in Live TV"));
	}

	@And("^User selects locked program from Live TV and rejects parental pin$")
	public void user_selects_locked_program_and_rejects_parental_pin() throws InterruptedException {
		user_selects_locked_program_from_live_TV();
		
	}

	@And("^User selects locked program from Live TV and enters wrong pin$")
	public void user_selects_locked_program_and_enters_wrong_pin() throws InterruptedException {
		user_selects_locked_program_and_rejects_parental_pin();
		settingPage.enter_wrong_parental_pin_for_programs();
//		Chnage xpath to invalid pincode
		if (!commonUtils.displayed(parental_pin_input)) {
			throw new TestException(String.format("Program is unlocked after entering wrong pin-Live TV"));
		} else {
			commonUtils.clickonElement(parental_pin_more_info);
			Assert.assertTrue(commonUtils.displayed(settings_more_info), "More Info page displayed");
		}
	}

	@And("^User unlocks program temporarily from Live TV$")
	public void user_unlocks_program_from_Live_TV() {
		user_selects_locked_program_from_live_TV();
		settingPage.enter_parental_pin_for_programs();
		try {
			commonUtils.waitTillInvisibility(lock_icon, 20);
		} catch (Exception e) {
			System.out.println("Program is locked even after entering parental pin");
		}
	}

	@And("^User selects locked program from Live TV and stream$")
	public void user_selects_program_from_live_TV() {
		user_unlocks_program_from_Live_TV();
		commonUtils.clickonElement(age_icon);
		commonUtils.waitTillVisibility(player_screen_live, 15);
		commonUtils.clickonElement(play_pause_button_on_player_screen);
	}

	@And("^User selects locked live program from Live TV and stream$")
	public void user_selects_live_program_from_live_TV_1() throws Throwable {
		boolean lockedProgramFound = false;
		for (int i = 0; i < 50; i++) {
			List<MobileElement> programList = commonUtils.findElements(program_description);
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(program_description, 15);
				programList = commonUtils.findElements(program_description);
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(j).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(program_description);
					icon = programList.get(j).findElements(age_icon);
				}
				if (icon.isEmpty())
					continue;
				try {
					programList.get(j - 1).findElement(live_tv_program_title).getText();
				}
				catch(Exception e) {
					continue;
				}
				System.out.println(programList.get(j - 1).findElement(live_tv_program_title).getText());
				programList.get(j - 1).click();
				programDetailsPage.user_on_program_details_page();
				programDetailsPage.click_on_play_pause_button_over_player_screen();
				Thread.sleep(30000);
				programDetailsPage.close_program_details_page_to_reach_livetv();
				programList = commonUtils.findElements(program_description);
				System.out.println(programList.get(j).findElement(live_tv_program_title).getText());
				programList.get(j).click();
				settingPage.enter_parental_pin_for_programs();
				try {
					commonUtils.waitTillInvisibility(lock_icon, 20);
				} catch (Exception e) {
					System.out.println("Program is locked even after entering parental pin");
				}
				programList = commonUtils.findElements(program_description);
				programList.get(j).click();
				programDetailsPage.user_on_program_details_page();
				programDetailsPage.click_on_play_pause_button_over_player_screen();
				Thread.sleep(30000);
				programDetailsPage.close_program_details_page_to_reach_livetv();
				lockedProgramFound = true;
				System.out.println("Program found in Live TV");
				break;
			}
			if (lockedProgramFound)
				break;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!lockedProgramFound)
			throw new TestException(String.format("Live parental locked program is not found-Live TV"));
	}

	@And("^User selects locked replay program from Live TV and stream$")
	public void user_selects_replay_program_from_live_TV_1() throws Throwable {
		boolean lockedProgramFound = false;
		for (int i = 0; i < 50; i++) {
			List<MobileElement> programList = commonUtils.findElements(program_description);
			for (int j = 0; j < programList.size(); j++) {
				commonUtils.waitTillVisibility(program_description, 15);
				programList = commonUtils.findElements(program_description);
				List<MobileElement> icon = new ArrayList<MobileElement>();
				try {
					icon = programList.get(j).findElements(age_icon);
				} catch (Exception e) {
					programList = commonUtils.findElements(program_description);
					icon = programList.get(j).findElements(age_icon);
				}
				if (icon.isEmpty())
					continue;
				try {
					programList.get(j - 1).findElement(live_tv_program_title).getText();
				}
				catch(Exception e) {
					continue;
				}
				System.out.println(programList.get(j - 1).findElement(live_tv_program_title).getText());
				if (!programList.get(j - 1).findElements(age_icon).isEmpty())
					continue;
				programList.get(j - 1).click();
				if (programDetailsPage.user_on_program_details_page()) {
					if (!commonUtils.displayed(details_page_replay_icon)) {
						programDetailsPage.close_program_details_page_to_reach_livetv();
						continue;
					}
				}
				programDetailsPage.click_on_play_pause_button_over_player_screen();
				Thread.sleep(10000);
				programDetailsPage.close_program_details_page_to_reach_livetv();
				programList = commonUtils.findElements(program_description);
				Thread.sleep(5000);
				System.out.println(programList.get(j).findElement(live_tv_program_title).getText());
				
				programList.get(j).click();
				settingPage.enter_parental_pin_for_programs();
				try {
					commonUtils.waitTillInvisibility(lock_icon, 20);
				} catch (Exception e) {
					System.out.println("Program is locked even after entering parental pin");
				}
				programList = commonUtils.findElements(program_description);
				programList.get(j).click();
				programDetailsPage.user_on_program_details_page();
				programDetailsPage.click_on_play_pause_button_over_player_screen();
				Thread.sleep(30000);
				programDetailsPage.close_program_details_page_to_reach_livetv();
				lockedProgramFound = true;
				System.out.println("Program found in Live TV");
				break;
			}
			if (lockedProgramFound)
				break;
			commonUtils.swipeUpOverHomeScreen();
		}
		if (!lockedProgramFound)
			throw new TestException(String.format("Replay parental locked program is not found-Live TV"));
	}

}
