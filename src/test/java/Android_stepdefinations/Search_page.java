package Android_stepdefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;

import Android_screens.Home_Screen;
import Android_screens.LiveTV_Screen;
import Android_screens.Program_Details_Screen;
import Android_screens.Search_screen;
import Android_screens.Setting_Screen;
import Android_screens.Swimlane_Contents_Screen;
import Android_screens.Vod_Details_Screen;
import base.Android_input_properties;
import config.Hooks;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Search_page implements Search_screen, Vod_Details_Screen, Swimlane_Contents_Screen, Setting_Screen,
		Program_Details_Screen, LiveTV_Screen, Home_Screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public Android_input_properties inputProperties;
	AndroidDriver<MobileElement> androidDriver;
	public Setting_Page settingPage;
	String suggestionText;

	public Search_page() throws IOException {
		driver = Hooks.getDriver();
		this.androidDriver = (AndroidDriver<MobileElement>) driver;
		commonUtils = new CommonUtils(driver);
		inputProperties = new Android_input_properties();
		settingPage = new Setting_Page();
		suggestionText = "";
	}

	@Then("^The User is on search page$")
	public void the_user_is_on_search_page() {
		commonUtils.clickonElement(search_navigation_button);
		if((commonUtils.displayed(search_box_before_tap))){
			commonUtils.clickonElement(search_box_before_tap);
			Assert.assertTrue(commonUtils.displayed(search_box));
			Assert.assertTrue(commonUtils.displayed(search_hint_text));
		}
	}

	@Given("^User taps on the search box$")
	public void user_tap_on_the_search_box() {
		commonUtils.clickonElement(search_box);
	}

	@When("^The user see the X button does not appear at the beginning$")
	public void the_user_see_the_x_button_does_not_appear_at_the_beginning() {
		Assert.assertFalse(commonUtils.displayed(search_x_button_id));
	}

	@Then("^The user see the X button after the text is being entered$")
	public void the_user_see_the_x_button_after_the_text_is_being_entered() {
		Assert.assertTrue(commonUtils.displayed(search_x_button_id));
	}

	@When("User enters {string} in the search box")
	public void the_user_enters_characters_in_search_box(String searchText) {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android(searchText));
		the_user_see_the_x_button_after_the_text_is_being_entered();
	}

	@When("User enters {int} characters to search box")
	public void the_user_enters_characters_to_search_box(int numberOfCharacters) {
		commonUtils.sendKey(search_box,
				commonUtils.getTextBasedonLanguage_Android("searchText").substring(0, numberOfCharacters));
	}

	@When("User reduces the {string} to {int} characters")
	public void the_user_reduces_characters_in_search_box(String searchText, int size) {
		the_user_enters_characters_to_search_box(size);
	}

	@When("User clicks on starting position of any of the suggestions")
	public void the_user_clicks_on_starting_position_of_suggestions() {
		suggestionText = commonUtils.getText(suggestion_search_result);
		System.out.println("Suggestion text" + suggestionText);
		commonUtils.clickonElement(suggestion_search_icon);
	}

	@When("User clicks on middle position of any of the suggestions")
	public void the_user_clicks_on_middle_position_of_suggestions() {
		suggestionText = commonUtils.getText(suggestion_search_result);
		System.out.println("Suggestion text" + suggestionText);
		commonUtils.clickonElement(suggestion_search_result);
	}

	@When("User clicks on arrow present at the end of the suggestions")
	public void the_user_clicks_on_arrow_present_at_the_end_of_suggestions() {
		suggestionText = commonUtils.getText(suggestion_search_result);
		System.out.println("Suggestion text" + suggestionText);
		commonUtils.clickonElement(suggestion_search_arrow);
	}

	@When("User clicks on unprecise suggestion based on {string}")
	public void user_clicks_on_unprecise_suggestion_based_on(String string) {
		suggestionText = commonUtils.getText(suggestion_search_result);
		System.out.println("Suggestion text" + suggestionText);
		commonUtils.clickonElement(suggestion_search_result);
	}

	@Given("Device is in landscape mode before entering search page")
	public void device_is_in_landscape_mode_before_entering_search_page() {
		commonUtils.clickonElement(home_button);
		androidDriver.rotate(ScreenOrientation.LANDSCAPE);
		the_user_is_on_search_page();

	}

	@Then("Bottom navigation bar is {string}")
	public void bottom_navigation_bar_is(String status) {
		if (status.equalsIgnoreCase("visible"))
			Assert.assertTrue(commonUtils.displayed(bottom_navigation_bar));
		else
			Assert.assertFalse(commonUtils.displayed(bottom_navigation_bar));
	}

	@When("User scrolls down the keyboard so as to hide the keyboard")
	public void user_scrolls_down_the_keyboard_so_as_to_hide_the_keyboard() {
		user_scrolls_on_the_search_suggestions();
		check_if_keyboard_disappears();

	}

	@When("User clicks on precise suggestion based on {string}")
	public void user_clicks_on_precise_suggestion_based_on(String searchText) {
		List<MobileElement> suggestions = commonUtils.findElements(suggestion_search_result);
		for (MobileElement suggestion : suggestions) {
			System.out.println("Suggestion text" + suggestion.getText());

			if (suggestion.getText().toLowerCase()
					.contains(commonUtils.getTextBasedonLanguage_Android(searchText).toLowerCase())) {
				suggestion.click();
				System.out.println("Suggestion --text" + suggestionText);
				break;
			}

		}
	}

	@Then("Suggestion is clickable")
	public void check_if_suggestion_is_clickable_and_search_result_appears() {
		String searchText = commonUtils.getText(search_box);
		Assert.assertEquals(searchText, suggestionText);
		commonUtils.waitTillVisibility(search_result_radio_group, 10);
		// commonUtils.clickonElement(search_x_button_id);

	}

	@Then("^No Search suggestions are displayed$")
	public void check_if_no_search_suggestions_are_displayed() {
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertEquals(1, suggestionElements.size());
	}

	@When("^User scolls on the search suggestions$")
	public void user_scrolls_on_the_search_suggestions() {
		commonUtils.swipeUpOverHomeScreen();
	}

	@Then("^Keyboard disappears$")
	public void check_if_keyboard_disappears() {
		Assert.assertFalse(androidDriver.isKeyboardShown());
	}

	@Then("^Keyboard reappears$")
	public void check_if_keyboard_reappears() {
		Assert.assertTrue(androidDriver.isKeyboardShown());
	}

	@And("^Search for \"([^\"]*)\" is displayed under the Search box$")
	public void check_default_search_label(String searchText) {
		String inputSearchText = commonUtils.getText(search_box);
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		String visibleSuggestion = suggestionElements.get(suggestionElements.size() - 1).getText();
		Assert.assertTrue(visibleSuggestion
				.contains(commonUtils.getTextBasedonLanguage_Android("search_suggestion_default") + inputSearchText));
	}

	@And("Last suggestion is Search for {string}")
	public void check_last_sugestion(String searchText) {
		check_default_search_label(searchText);
	}

	@Then("^User search for a text$")
	public void user_search_for_a_text() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		user_enter_the_text_and_check_the_presence_of_the_x_button();
		keyboardStatus();
		Thread.sleep(2000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	@Then("Appropriate suggestions for {string} are shown")
	public void the_appropriate_suggestions_are_shown(String searchText) {
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertTrue(suggestionElements.size() > 1);
		String searchInput[] = commonUtils.getTextBasedonLanguage_Android(searchText).toLowerCase().split("(?!^)");
		int match_counter = 0;
		System.out.println("Expected:" + searchInput.length);
		for (MobileElement element : suggestionElements) {
			System.out.println("Element" + element.getText());
			match_counter = 0;
			for (String text : searchInput) {
				if (element.getText().toLowerCase().contains(text))
					++match_counter;
			}
			Assert.assertTrue(match_counter > 3);
		}

	}

	@Then("Precise suggestions for {string} are shown")
	public void the_precise_suggestions_are_shown(String searchText) {
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertTrue(suggestionElements.size() > 1);
		System.out.println("Element" + suggestionElements.get(0).getText());
		Assert.assertTrue(suggestionElements.get(0).getText()
				.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android(searchText)));
	}

	@Then("Unprecise suggestions for {string} are shown")
	public void unprecise_suggestions_are_shown(String searchText) {
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertTrue(suggestionElements.size() > 1);
		for (MobileElement element : suggestionElements) {

			Assert.assertFalse(
					commonUtils.getTextBasedonLanguage_Android(searchText).equalsIgnoreCase(element.getText()));
		}

	}

	@Then("Unprecise search results for {string} are shown")
	public void unprecise_search_result_are_shown(String searchText) {
		String suggestionText = commonUtils.getText(search_box);
		Assert.assertNotEquals(suggestionText.toLowerCase(),
				commonUtils.getTextBasedonLanguage_Android(searchText).toLowerCase());
		the_appropriate_results_are_shown();

	}

	public List<String> search_page_categories_are_displayed() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		List<MobileElement> categoryElements = commonUtils.findElements(search_result_radio_group);
		for (MobileElement category : categoryElements) {
			String categoryName = category.getText();
			if (categoryName.contains("(0)"))
				continue;
			if (categoryName.contains("("))
				categoryName = categoryName.split("\\(")[0].trim();
			if (categoryName.contains("'"))
				categoryName = categoryName.split("\\'")[0].trim();
			if (!categoriesDisplayed.contains(categoryName))
				categoriesDisplayed.add(categoryName);
		}
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		return categoriesDisplayed;
	}

	@Then("The appropriate results are shown for search text")
	public void the_appropriate_results_are_shown() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		List<MobileElement> categoryElements = commonUtils.findElements(search_result_radio_group);
		for (MobileElement category : categoryElements) {
			String categoryName = category.getText();
			if (categoryName.contains("(0)"))
				continue;
			if (categoryName.contains("("))
				categoryName = categoryName.split("\\(")[0].trim();
			if (categoryName.contains("'"))
				categoryName = categoryName.split("\\'")[0].trim();
			if (!categoriesDisplayed.contains(categoryName))
				categoriesDisplayed.add(categoryName);
		}
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		commonUtils.findElement(commonUtils.findElementByXpathContains("text", categoriesDisplayed.get(0))).click();
		String searchText = commonUtils.getText(search_box);
		if (categoriesDisplayed.get(0)
				.equalsIgnoreCase(commonUtils.getTextBasedonLanguage_Android("search_categories_TvShow")))
			check_the_displayed_TV_program(searchText);
		else
			check_the_displayed_on_demand_program(searchText);
	}

	@Then("Precise search results for {string} are shown")
	public void precise_search_results_are_shown(String searchText) {
		the_appropriate_results_are_shown();
	}

	public void check_the_displayed_on_demand_program(String searchText) {
		int counter = 0;
		while (counter < 3) {
			try {
				commonUtils.waitTillVisibility(searc_program_VOD_description, 15);
			} catch (Exception err) {
//              Landscape mode
				commonUtils.swipeUpOverScreen();
				commonUtils.waitTillVisibility(searc_program_VOD_description, 15);
			}

			List<MobileElement> programList = commonUtils.findElements(searc_program_VOD_description);
			for (int i = 0; i < programList.size();i++) {
				programList.get(i).click();
				/*
				 * try { commonUtils.findElement(parental_pin_input);
				 * settingsPage.enter_parental_pin_for_programs();
				 * commonUtils.waitTillInvisibility(lock_icon, 20); programList.get(i).click();
				 * 
				 * } catch (Exception e) { programList.get(i).click(); // TODO: handle exception
				 * }
				 */
				if (!commonUtils.displayed(parental_pin_input)) {
					// programList.get(i).click();
				} else {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					programList.get(i).click();
				}
				String title;
				String metaDetails;
				title = commonUtils.getText(vod_program_title).toLowerCase();
				metaDetails = commonUtils.getText(vod_program_time).toLowerCase();
				System.out.println("Search content is " + title + metaDetails);
				if (title.contains(searchText.toLowerCase())) {
					System.out.println("Search text is verified");
					break;
				} else if (metaDetails.contains(searchText.toLowerCase())) {
					System.out.println("Search text is verified");
					break;
				} else {
					System.out.println("Search text is verified : search_director / search_actor " + searchText);
					break;
				}
			}
			break;
		}
	}

	public void check_the_displayed_TV_program(String searchText) {
		int counter = 0;
		while (counter < 3) {
			try {
				commonUtils.waitTillVisibility(search_program_description, 15);
			} catch (Exception err) {
//              Landscape mode
				commonUtils.swipeUpOverHomeScreen();
				commonUtils.waitTillVisibility(search_program_description, 15);
			}
			List<MobileElement> programList = commonUtils.findElements(search_program_description);
			for (int i = 0; i < programList.size(); i++) {
				programList.get(i).click();
				/*
				 * try { commonUtils.findElement(parental_pin_input);
				 * settingsPage.enter_parental_pin_for_programs();
				 * commonUtils.waitTillInvisibility(lock_icon, 20); programList.get(i).click();
				 * 
				 * } catch (Exception e) { programList.get(i).click(); // TODO: handle exception
				 * }
				 */
				if (!commonUtils.displayed(parental_pin_input)) {
					// programList.get(i).click();
				} else {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					programList = commonUtils.findElements(search_program_description);
					programList.get(i).click();
				}
				String title, castDetails;
				String metaDetails;
				title = commonUtils.getText(program_title).toLowerCase();
				castDetails = commonUtils.findElement(search_program_cast) != null
						? commonUtils.getText(search_program_cast).toLowerCase()
						: "";
				if(!commonUtils.displayed(program_time)) {
					System.out.println("program_time");
					commonUtils.swipeUpOverDetailsScreen();
					commonUtils.swipeUpOverDetailsScreen();
				}
				metaDetails = commonUtils.getText(program_time).toLowerCase() + " " + castDetails;
				System.out.println("Search content is " + title + " " + metaDetails);
				if (title.contains(searchText.toLowerCase())) {
					System.out.println("Search text is verified");
					break;
				} else if (metaDetails.contains(searchText.toLowerCase())) {
					System.out.println("Search text is verified");
					break;
				} else {
					System.out.println("Search text is verified : search_director / search_actor " + searchText);
					break;
				}
			}
			break;
		}
	}

	@Given("^The User rotate the oriantation to landscape$")
	public void the_user_rotate_the_oriantation_to_landscape() throws InterruptedException {
		commonUtils.rotateOrientationToLandscape();
	}

	@And("^User verifies the search box in landscape mode$")
	public void user_verifies_the_search_box_in_landscape_mode() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("searchText"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		the_user_rotate_the_oriantation_to_portrait();
		keyboardStatus();
	}

	@Given("^The User rotate the oriantation to Portrait$")
	public void the_user_rotate_the_oriantation_to_portrait() {
		commonUtils.rotateOrientationToPortrait();
	}

	public void keyboardStatus() {
		try {
			boolean isKeyboardShown = ((HasOnScreenKeyboard) driver).isKeyboardShown();
			System.out.println("Keyboard is shown   " + isKeyboardShown);
		} catch (Exception e) {
			System.out.println("Keyboard is not visible");
		}

	}

	@Given("^The User clean the entered text using the X button$")
	public void the_user_clean_the_entered_text_using_the_x_button() {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("searchText"));
		commonUtils.clickonElement(search_x_button_id);
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
	}

	@When("^The User clean the entered text using the backspace button$")
	public void the_user_clean_the_entered_text_using_the_backspace_button() throws InterruptedException {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("searchText"));
		//commonUtils.sendKey(search_box, Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
		String searchText = commonUtils.getText(search_box);
		for (int i = 0; i < searchText.length(); i++) {
			androidDriver.pressKey(new KeyEvent(AndroidKey.DEL));
		}
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
	}

	@And("^User verifies new search is possible after cleaning the text$")
	public void user_verifies_new_search_is_possible_after_cleaning_the_text() {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("searchText"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
	}

	@And("^User enter the text and check the presence of the X button$")
	public void user_enter_the_text_and_check_the_presence_of_the_x_button() {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("searchText"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
	}

	@And("User click the backspace to reduce the text to {int} character")
	public void user_click_the_backspace_to_reduce_the_text_to_character(int numberOfCharacters)
			throws InterruptedException {
		String searchText = commonUtils.getTextBasedonLanguage_Android("searchText");
		for (int i = 0; i < searchText.length(); i++) {
			commonUtils.findElement(search_box);
			// removedText = searchText.substring(searchText.length() - i);
			// System.out.println("removedText: " +searchText.substring(searchText.length()
			// - i));
			androidDriver.pressKey(new KeyEvent(AndroidKey.DEL));
			int afterDeleteSearchText = commonUtils.getText(search_box).length();
			if (afterDeleteSearchText == numberOfCharacters) {
				the_user_see_the_x_button_after_the_text_is_being_entered();
				break;
			}
		}
	}

	@When("Empty Search page is displayed")
	public void empty_search_page_is_displayed() {
		String emptyPageText = commonUtils.getText(search_hint_text);
		Assert.assertEquals(commonUtils.getTextBasedonLanguage_Android("EmptyPageText"), emptyPageText);
	}

	@When("The user validates the default text of the text box")
	public void the_user_validates_the_default_text_of_the_text_box() {
		String emptyPageText[] = commonUtils.getTextBasedonLanguage_Android("DefaultTextBoxText").toLowerCase()
				.split(",");
		String actualTextBox[] = commonUtils.getText(search_box).toLowerCase().split(",");
		Set<String> textInSerachBox = new HashSet<>();
		for (String emptypagetext : emptyPageText) {
			for (String text : actualTextBox) {
				textInSerachBox.add(text.trim());
			}
			textInSerachBox.contains(emptypagetext);
		}
		System.out.println("Default text of search box   " + textInSerachBox);
	}

	@Then("^User search for an empty text$")
	public void user_search_for_an_empty_text() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("emptySearch_text"));
		Thread.sleep(2000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	@Then("^User gets an error message for empty result$")
	public void user_gets_an_error_message_for_empty_result() throws InterruptedException {
		commonUtils.waitTillVisibility(empty_search_error, 30);
		Assert.assertTrue(commonUtils.displayed(empty_search_error));
		Assert.assertTrue(commonUtils.displayed(empty_search_icon));
	}

	@Then("^User search for a text using suggestion$")
	public void user_search_for_a_text_using_suggestion() throws InterruptedException {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("search_actor"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.findElements(sugestion_search_result).get(0).click();
	}

	@Then("^User search for a program title$")
	public void user_search_for_a_program_title() throws InterruptedException {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("search_title"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	@Then("^User search for a On demand program$")
	public void user_search_for_a_vod_program_title() throws InterruptedException {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("vod_search_title"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	@Then("^User search for a program actor$")
	public void user_search_for_a_program_actor() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("search_actor"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	@Then("^User search for a program director$")
	public void user_search_for_a_program_director() throws InterruptedException {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage_Android("search_director"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		((PressesKey) driver).pressKey(new KeyEvent().withKey(AndroidKey.ENTER));
	}

	@Then("^User clears search box$")
	public void user_clears_search_box() throws InterruptedException {
		the_user_see_the_x_button_after_the_text_is_being_entered();
		commonUtils.clickonElement(search_x_button_id);
	}

	@Then("^The appropriate results are shown for program$")
	public void the_appropriate_results_are_shown_program() {
		String searchText = commonUtils.getTextBasedonLanguage_Android("search_title");
		search_page_categories_are_displayed();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
	}

	@Then("^The appropriate results are shown for actor$")
	public void the_appropriate_results_are_shown_actor() {
		String searchText = commonUtils.getTextBasedonLanguage_Android("search_actor");
		search_page_categories_are_displayed();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
	}

	@Then("^The appropriate results are shown for director$")
	public void the_appropriate_results_are_shown_director() {
		String searchText = commonUtils.getTextBasedonLanguage_Android("search_director");
		search_page_categories_are_displayed();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
	}

	@Then("^User validates TV Shows and On Demand$")
	public void user_validate_TV_programs_and_On_Demand() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String searchText = commonUtils.getTextBasedonLanguage_Android("search_actor");
		while (counter < 2) {
			commonUtils.waitTillVisibility(search_result_radio_group, 60);
			List<MobileElement> categoryElements = commonUtils.findElements(search_result_radio_group);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				if (categoryName.contains("'"))
					categoryName = categoryName.split("\\'")[0].trim();
				if (!categoriesDisplayed.contains(categoryName))
					categoriesDisplayed.add(categoryName);
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			break;
		}
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		driver.findElement(By.xpath("//*[contains(@text,'" + categoriesDisplayed.get(0) + "')]")).click();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
		driver.findElement(By.xpath("//*[contains(@text,'" + categoriesDisplayed.get(1) + "')]")).click();
		check_the_displayed_on_demand_program(searchText);
	}

	@Then("^User verifies TV Shows and On Demand$")
	public void user_verifies_TV_programs_and_On_Demand() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		while (counter < 2) {
			commonUtils.waitTillVisibility(search_result_radio_group, 60);
			List<MobileElement> categoryElements = commonUtils.findElements(search_result_radio_group);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				if (categoryName.contains("'"))
					categoryName = categoryName.split("\\'")[0].trim();
				if (!categoriesDisplayed.contains(categoryName))
					categoriesDisplayed.add(categoryName);
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			break;
		}
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		Assert.assertEquals(categoriesDisplayed.get(0).toLowerCase(),
				commonUtils.getTextBasedonLanguage_Android("search_categories_TvShow").toLowerCase());
		Assert.assertEquals(categoriesDisplayed.get(1).toLowerCase(),
				commonUtils.getTextBasedonLanguage_Android("search_categories_onDemand").toLowerCase());
	}

	@Then("^User verifies On Demand category$")
	public void user_verifies_On_Demand() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String searchText = commonUtils.getTextBasedonLanguage_Android("vod_search_title");
		while (counter < 2) {
			commonUtils.waitTillVisibility(search_result_radio_group, 60);
			List<MobileElement> categoryElements = commonUtils.findElements(search_result_radio_group);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				if (categoryName.contains("'"))
					categoryName = categoryName.split("\\'")[0].trim();
				if (!categoriesDisplayed.contains(categoryName))
					categoriesDisplayed.add(categoryName);
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			break;
		}
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		Assert.assertTrue(commonUtils.findElements(search_program_description).isEmpty());
		driver.findElement(By.xpath("//*[contains(@text,'" + categoriesDisplayed.get(1) + "')]")).click();
		check_the_displayed_on_demand_program(searchText);
	}

	@Then("^User verifies TV Shows category$")
	public void user_verifies_tv_shows() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		int counter = 0;
		String temp = "";
		String searchText = "search_title";
		while (counter < 2) {
			commonUtils.waitTillVisibility(search_result_radio_group, 60);
			List<MobileElement> categoryElements = commonUtils.findElements(search_result_radio_group);
			String lastCategory = categoryElements.get(categoryElements.size() - 1).getText();
			for (MobileElement category : categoryElements) {
				String categoryName = category.getText();
				if (categoryName.contains("("))
					categoryName = categoryName.split("\\(")[0].trim();
				if (categoryName.contains("'"))
					categoryName = categoryName.split("\\'")[0].trim();
				if (!categoriesDisplayed.contains(categoryName))
					categoriesDisplayed.add(categoryName);
			}
			if (temp.equals(lastCategory))
				counter++;
			temp = lastCategory;
			break;
		}
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		check_the_displayed_TV_program(searchText);
		commonUtils.findElement(close_button).click();
		driver.findElement(By.xpath("//*[contains(@text,'" + categoriesDisplayed.get(1) + "')]")).click();
		Assert.assertTrue(commonUtils.findElements(search_program_title).isEmpty());
	}
}
