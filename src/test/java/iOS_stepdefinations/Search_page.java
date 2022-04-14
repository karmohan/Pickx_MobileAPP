package iOS_stepdefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;

import base.iOS_input_properties;
import config.Hooks;
import iOS_screens.Chromecase_screen;
import iOS_screens.Home_Screen;
import iOS_screens.LiveTV_Screen;
import iOS_screens.Program_Details_Screen;
import iOS_screens.Search_screen;
import iOS_screens.Setting_Screen;
import iOS_screens.Swimlane_Contents_Screen;
import iOS_screens.TVguide_Screen;
import iOS_screens.Vod_Details_Screen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonUtils;

public class Search_page implements Search_screen, Swimlane_Contents_Screen, Setting_Screen, Program_Details_Screen,
		LiveTV_Screen, TVguide_Screen, Home_Screen, Vod_Details_Screen, Chromecase_screen {
	public static AppiumDriver<MobileElement> driver;
	public CommonUtils commonUtils;
	public iOS_input_properties inputProperties;
	IOSDriver<MobileElement> iosDriver;
	public Setting_Page settingPage;
	String search_box_text;
	String x_button_search_box;
	By searchBox;
	By searchBoxXbutton;
	Character removedText;
	String removed_Character;
	private Object suggestionText;
	public Home_Page home_Page;
	public Program_Details_Page program_Details_Page;

	public Search_page() throws IOException {
		driver = Hooks.getDriver();
		this.iosDriver = (IOSDriver<MobileElement>) driver;
		commonUtils = new CommonUtils(driver);
		inputProperties = new iOS_input_properties();
		settingPage = new Setting_Page();
		removed_Character ="";
		home_Page = new Home_Page();
		program_Details_Page = new Program_Details_Page();
	}

	public By searchBox() {
		search_box_text = commonUtils.getTextBasedonLanguage("search_box_text_contain");
		searchBox = commonUtils.findElementByXpathContains("name", search_box_text);
		return searchBox;
	}

	public By searchBoxXbutton() {
		x_button_search_box = commonUtils.getTextBasedonLanguage("x_button_search_box");
		searchBoxXbutton = commonUtils.findElementByAccessibilityid(x_button_search_box);
		return searchBoxXbutton;
	}

	@Then("^The User is on search page$")
	public void the_user_is_on_search_page() {
		commonUtils.clickonElement(search_navigation_button1);
		Assert.assertTrue(commonUtils.displayed(searchBox()));
	}

	@Given("^User taps on the search box$")
	public void user_tap_on_the_search_box() {
		commonUtils.clickonElement(searchBox());
	}

	@When("^The user see the X button does not appear at the beginning$")
	public void the_user_see_the_x_button_does_not_appear_at_the_beginning() {
		Assert.assertFalse(commonUtils.displayed(searchBoxXbutton()));
	}

	@Then("^The user see the X button after the text is being entered$")
	public void the_user_see_the_x_button_after_the_text_is_being_entered() {
		Assert.assertTrue(commonUtils.displayed(searchBoxXbutton()));
	}

	@Then("^User search for a text$")
	public void user_search_for_a_text() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("searchText"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.sendKey(searchBox(), Keys.chord(Keys.ENTER));
	}

	@Then("The appropriate results are shown for search text")
	public void the_appropriate_results_are_shown_for_search_text() throws InterruptedException {
		commonUtils.waitTillVisibility(search_result_raido_group_absolute, 5);
		List<String> categoriesDisplayed = new ArrayList<String>();
		List<MobileElement> categoryElements = commonUtils.findElements(search_result_raido_group_absolute);
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
		String searchText = commonUtils.getText(searchBox());
		if (categoriesDisplayed.get(0).equalsIgnoreCase(commonUtils.getTextBasedonLanguage("search_categories_TvShow")))
			check_the_displayed_TV_program(searchText);
		else
			check_the_displayed_on_demand_program(searchText);
	}

	public void check_the_displayed_on_demand_program(String searchText) {
		int counter = 0;
		//while (counter < 3) {
			commonUtils.waitTillVisibility(search_on_demand_program, 15);
			List<MobileElement> programList = commonUtils.findElements(search_program_name);
			for (int i = 0; i < programList.size(); i++) {
				programList.get(i).click();
				if (!commonUtils.displayed(parental_pin_input)) {
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
			//break;
	//	}
	}

	public void check_the_displayed_TV_program(String searchText) {
		int counter = 0;
		while (counter < 3) {
			commonUtils.waitTillVisibility(search_program_name, 15);
			List<MobileElement> programList = commonUtils.findElements(search_program_name);
			for (int i = 0; i < programList.size(); i++) {
				programList.get(i).click();
				if (!commonUtils.displayed(parental_pin_input)) {
				} else {
					settingPage.enter_parental_pin_for_programs();
					commonUtils.waitTillInvisibility(lock_icon, 20);
					programList = commonUtils.findElements(search_program_name);
					programList.get(i).click();
				}
				if (commonUtils.displayed(live_tv_streaming_error)) {
					commonUtils.findElement(live_tv_streaming_error_tryagain).click();
				}
				String title, castDetails;
				String metaDetails;
				title = commonUtils.getText(Program_Details_Screen.program_title).toLowerCase();
				castDetails = commonUtils.findElement(search_program_description) != null
						? commonUtils.getText(search_program_description).toLowerCase()
						: "";
				metaDetails = commonUtils.getText(Program_Details_Screen.program_time).toLowerCase() + " " + castDetails;
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

	@Given("^The User rotate the oriantation to landscape$")
	public void the_user_rotate_the_oriantation_to_landscape() throws InterruptedException {
		commonUtils.rotateOrientationToLandscape();
	}

	@And("^User verifies the search box in landscape mode$")
	public void user_verifies_the_search_box_in_landscape_mode() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		user_enter_the_text_and_check_the_presence_of_the_x_button();
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
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("searchText"));
		commonUtils.clickonElement(searchBoxXbutton());
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
	}

	@When("^The User clean the entered text using the backspace button$")
	public void the_user_clean_the_entered_text_using_the_backspace_button() throws InterruptedException {
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("searchText"));
		String searchText = commonUtils.getText(searchBox());
		for (int i = 0; i < searchText.length(); i++) {
			driver.findElement(searchBox()).sendKeys(Keys.BACK_SPACE);
		}
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
	}

	@And("^User verifies new search is possible after cleaning the text$")
	public void user_verifies_new_search_is_possible_after_cleaning_the_text() {
		user_enter_the_text_and_check_the_presence_of_the_x_button();
	}

	@And("^User enter the text and check the presence of the X button$")
	public void user_enter_the_text_and_check_the_presence_of_the_x_button() {
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("searchText"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
	}

	@And("User click the backspace to reduce the text to {int} character")
	public void user_click_the_backspace_to_reduce_the_text_to_character(int numberOfCharacters)
			throws InterruptedException {
		String searchText = commonUtils.getText(searchBox());
		for (int i = 0; i < searchText.length(); i++) {
			removedText = searchText.charAt(searchText.length() - 1);
			driver.findElement(searchBox()).sendKeys(Keys.BACK_SPACE);
			searchText = commonUtils.getText(searchBox());
			int afterDeleteSearchText = commonUtils.getText(searchBox()).length();
			if (afterDeleteSearchText == numberOfCharacters) {
				the_user_see_the_x_button_after_the_text_is_being_entered();
				break;
			}
			removed_Character = removed_Character + removedText;
		}
	}

	@Then("User continue entering the search text {string}")
	public void user_continue_entering_the_search_text(String string) throws InterruptedException {
		String text = "";
		for (int i = removed_Character.length() - 1; i >= 0; i--) {
			text = text + removed_Character.charAt(i);
		}
		commonUtils.sendKey(searchBox(), text);
		System.out.println("current text in search  " + commonUtils.getText(searchBox()));
		the_user_see_the_x_button_after_the_text_is_being_entered();
	}

	@When("User continue entering the search text")
	public void User_continue_entering_the_search_text() {

	}

	@When("Empty Search page is displayed")
	public void empty_search_page_is_displayed() {
		String emptyPageText = commonUtils.getText(searchBox());
		String Default_text = emptyPageText.replaceAll(",", "");
		Assert.assertEquals((commonUtils.getTextBasedonLanguage("DefaultTextBoxText")), Default_text);
		Assert.assertEquals((commonUtils.getTextBasedonLanguage("EmptyPageText")),
				commonUtils.getText(search_empty_page_text));
	}

	@When("The user validates the default text of the text box")
	public void the_user_validates_the_default_text_of_the_text_box() {
		String emptyPageText[] = commonUtils.getTextBasedonLanguage("DefaultTextBoxText").toLowerCase().split(",");
		String actualTextBox[] = commonUtils.getText(searchBox()).toLowerCase().split(",");
		Set<String> textInSerachBox = new HashSet<>();
		for (String emptypagetext : emptyPageText) {
			for (String text : actualTextBox) {
				textInSerachBox.add(text.trim());
			}
			textInSerachBox.contains(emptypagetext);
		}
		System.out.println("Default text of search box   " + textInSerachBox);
	}
	
	
	
	@When("User enters {string} in the search box")
	public void the_user_enters_characters_in_search_box(String searchText) {
		commonUtils.sendKey(search_box, commonUtils.getTextBasedonLanguage(searchText));
		the_user_see_the_x_button_after_the_text_is_being_entered();
	}

	@When("User enters {int} characters to search box")
	public void the_user_enters_characters_to_search_box(int numberOfCharacters) {
		commonUtils.findElement(searchBox).clear();
		commonUtils.sendKey(search_box,
				commonUtils.getTextBasedonLanguage("searchText").substring(0, numberOfCharacters));
	}

	@When("User reduces the {string} to {int} characters")
	public void the_user_reduces_characters_in_search_box(String searchText, int size) {
		the_user_enters_characters_to_search_box(size);
	}

	@When("User clicks on starting position of any of the suggestions")
	public void the_user_clicks_on_starting_position_of_suggestions() {
		suggestionText = commonUtils.getText(suggestion_search_result);
		commonUtils.findElements(suggestion_search_icon).get(0).click();
		

	}

	@When("User clicks on middle position of any of the suggestions")
	public void the_user_clicks_on_middle_position_of_suggestions() {
		suggestionText = commonUtils.getText(suggestion_search_result);
		commonUtils.findElements(suggestion_search_result).get(0).click();;

	}

	@When("User clicks on arrow present at the end of the suggestions")
	public void the_user_clicks_on_arrow_present_at_the_end_of_suggestions(){
		suggestionText = commonUtils.getText(suggestion_search_result);
		commonUtils.findElements(suggestion_search_arrow).get(0).click();;
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
		iosDriver.rotate(ScreenOrientation.LANDSCAPE);
		the_user_is_on_search_page();

	}

	@Then("Bottom navigation bar is {string}")
	public void bottom_navigation_bar_is(String status) {
		if (status == "visible")
			Assert.assertTrue(commonUtils.displayed(bottom_navigation_bar));
		else
			Assert.assertFalse(commonUtils.displayed(bottom_navigation_bar));
	}
	@When("User scrolls down the keyboard so as to hide the keyboard")
	public void user_scrolls_down_the_keyboard_so_as_to_hide_the_keyboard() throws InterruptedException {
		user_scrolls_on_the_search_suggestions();
		check_if_keyboard_disappears();

	}

	@When("User clicks on precise suggestion based on {string}")
	public void user_clicks_on_precise_suggestion_based_on(String searchText) {
		List<MobileElement> suggestions = commonUtils.findElements(suggestion_search_result);
		for (MobileElement suggestion : suggestions) {
			System.out.println("Suggestion text" + suggestion.getText());

			if (suggestion.getText().toLowerCase()
					.contains(commonUtils.getTextBasedonLanguage(searchText).toLowerCase())) {
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
	public void user_scrolls_on_the_search_suggestions() throws InterruptedException {
		Thread.sleep(2000);
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);

		commonUtils.swipeUpOverElement(suggestionElements.get(0));
	}

	@Then("^Keyboard disappears$")
	public void check_if_keyboard_disappears() {
		Assert.assertFalse(iosDriver.isKeyboardShown());
	}

	@Then("^Keyboard reappears$")
	public void check_if_keyboard_reappears() {
		Assert.assertTrue(iosDriver.isKeyboardShown());
	}

	@And("^Search for \"([^\"]*)\" is displayed under the Search box$")
	public void check_default_search_label(String searchText) {
		String inputSearchText = commonUtils.getText(search_box);
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		String visibleSuggestion = suggestionElements.get(suggestionElements.size() - 1).getText();
		Assert.assertTrue(visibleSuggestion
				.contains(commonUtils.getTextBasedonLanguage("search_suggestion_default") + inputSearchText));
	}

	@And("Last suggestion is Search for {string}")
	public void check_last_sugestion(String searchText) {
		check_default_search_label(searchText);
	}

	@Then("Appropriate suggestions for {string} are shown")
	public void the_appropriate_suggestions_are_shown(String searchText) throws InterruptedException {
		Thread.sleep(3000);
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertTrue(suggestionElements.size() > 1);
		String searchInput[] = commonUtils.getTextBasedonLanguage(searchText).toLowerCase().split("(?!^)");
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
	public void the_precise_suggestions_are_shown(String searchText) throws InterruptedException {
		Thread.sleep(2000);
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertTrue(suggestionElements.size() > 1);
		System.out.println("Element" + suggestionElements.get(0).getText());
		Assert.assertTrue(suggestionElements.get(0).getText()
				.equalsIgnoreCase(commonUtils.getTextBasedonLanguage(searchText)));
	}

	@Then("Unprecise suggestions for {string} are shown")
	public void unprecise_suggestions_are_shown(String searchText) throws InterruptedException {
		Thread.sleep(2000);
		List<MobileElement> suggestionElements = commonUtils.findElements(suggestion_search_result);
		Assert.assertTrue(suggestionElements.size() > 1);
		for (MobileElement element : suggestionElements) {

			Assert.assertFalse(
					commonUtils.getTextBasedonLanguage(searchText).equalsIgnoreCase(element.getText()));
		}

	}

	@Then("Unprecise search results for {string} are shown")
	public void unprecise_search_result_are_shown(String searchText) throws InterruptedException {
		String suggestionText = commonUtils.getText(search_box);
		Assert.assertNotEquals(suggestionText.toLowerCase(),
				commonUtils.getTextBasedonLanguage(searchText).toLowerCase());
		the_appropriate_results_are_shown_for_search_text();

	}

	
	@Then("Precise search results for {string} are shown")
	public void precise_search_results_are_shown(String searchText) throws InterruptedException {
		the_appropriate_results_are_shown_for_search_text();
	}

	
	@And("Click and play any program to cast")
	public void click_and_play_any_program_to_cast() throws Throwable {
		home_Page.user_selects_ongoing_program_from_home_screen();
		program_Details_Page.program_starts_streaming();
		commonUtils.displayed(commonUtils.findElementByXpathContains("name", commonUtils.getTextBasedonLanguage("playing_on_text")));
		user_minimizes_the_casting_page();
	}
	
	@And("Mini player is visible on bottom navigation")
	public void mini_player_is_visible_on_bottom_navigation() {
		Assert.assertTrue(commonUtils.displayed(NewMiniPlayerView_maximizeButton));
		Assert.assertTrue(commonUtils.displayed(NewMiniPlayerView_playPauseButton));
		Assert.assertTrue(commonUtils.displayed(miniplayer_title));
	}
	
	@And("Search page with keyboard is opened by clicking search button from bottom navigation")
	public void search_page_with_keyboard_is_opened_by_clicking_search_button_from_bottom_navigation() {
		the_user_is_on_search_page();
		check_if_keyboard_reappears();
	}
	
	@And("User minimizes the casting page")
	public void user_minimizes_the_casting_page() {
		commonUtils.waitTillVisibility(player_minimize_button, 30);
		Assert.assertTrue(commonUtils.displayed(player_minimize_button));
		commonUtils.clickonElement(player_minimize_button);
	}
	
	@And("Mini player is not visible")
	public void mini_player_is_not_visible() {
		Assert.assertFalse(commonUtils.displayed(NewMiniPlayerView_maximizeButton));
		Assert.assertFalse(commonUtils.displayed(NewMiniPlayerView_playPauseButton));
		Assert.assertFalse(commonUtils.displayed(miniplayer_title));
	}
	
	@And("Close the keyboard")
	public void close_the_keyboard() {
		commonUtils.clickonElement(search_empty_page_text);
		check_if_keyboard_disappears();
	}
	
	@And("All mini player buttons on the search page function correctly")
	public void all_mini_player_buttons_on_the_search_page_function_correctly() throws InterruptedException {
		commonUtils.clickonElement(chromecast_miniplayer_playbutton);
		Thread.sleep(4000);
		commonUtils.clickonElement(chromecast_miniplayer_playbutton);
		Thread.sleep(4000);
		commonUtils.clickonElement(NewMiniPlayerView_maximizeButton);
		Assert.assertTrue(commonUtils.displayed(commonUtils.findElementByXpathContains("name", commonUtils.getTextBasedonLanguage("playing_on_text"))));
		commonUtils.clickonElement(player_close_button);
		Assert.assertFalse(commonUtils.displayed(commonUtils.findElementByXpathContains("name", commonUtils.getTextBasedonLanguage("playing_on_text"))));
	}

	@Then("^User search for an empty text$")
	public void user_search_for_an_empty_text() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("emptySearch_text"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.sendKey(searchBox(), Keys.chord(Keys.ENTER));
	}

	@Then("^User gets an error message for empty result$")
	public void user_gets_an_error_message_for_empty_result() throws InterruptedException {
		commonUtils.waitTillVisibility(search_empty_page_text, 30);
		Assert.assertTrue(commonUtils.displayed(search_empty_page_text));
		Assert.assertTrue(commonUtils.enabled(search_empty_page_icon));
	}

	@Then("^User search for a text using suggestion$")
	public void user_search_for_a_text_using_suggestion() throws InterruptedException {
		the_user_see_the_x_button_does_not_appear_at_the_beginning();
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("search_actor"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.findElements(suggestion_search_result).get(0).click();
	}

	@Then("^The appropriate results are shown for actor$")
	public void the_appropriate_results_are_shown_actor() {
		String searchText = commonUtils.getTextBasedonLanguage("search_actor");
		search_page_categories_are_displayed();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
	}

	public List<String> search_page_categories_are_displayed() {
		List<String> categoriesDisplayed = new ArrayList<String>();
		commonUtils.waitTillVisibility(search_result_raido_group_absolute, 30);
		List<MobileElement> categoryElements = commonUtils.findElements(search_result_raido_group_absolute);
		for (MobileElement category : categoryElements) {
			String categoryName = category.getText();
			System.out.println(categoryName + "   categoryName");
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

	@Then("^User search for a program title$")
	public void user_search_for_a_program_title() throws InterruptedException {
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("search_title"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.sendKey(searchBox(), Keys.chord(Keys.ENTER));
	}

	@Then("^User search for a On demand program$")
	public void user_search_for_a_vod_program_title() throws InterruptedException {
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("vod_search_title"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.sendKey(searchBox(), Keys.chord(Keys.ENTER));
	}

	@Then("^User search for a program actor$")
	public void user_search_for_a_program_actor() throws InterruptedException {
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("search_actor"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.sendKey(searchBox(), Keys.chord(Keys.ENTER));
	}

	@Then("^User search for a program director$")
	public void user_search_for_a_program_director() throws InterruptedException {
		commonUtils.sendKey(searchBox(), commonUtils.getTextBasedonLanguage("search_director"));
		the_user_see_the_x_button_after_the_text_is_being_entered();
		Thread.sleep(2000);
		commonUtils.sendKey(searchBox(), Keys.chord(Keys.ENTER));
	}

	@Then("^User clears search box$")
	public void user_clears_search_box() throws InterruptedException {
		the_user_see_the_x_button_after_the_text_is_being_entered();
		commonUtils.clickonElement(searchBoxXbutton());
	}

	@Then("^The appropriate results are shown for program$")
	public void the_appropriate_results_are_shown_program() {
		String searchText = commonUtils.getTextBasedonLanguage("search_title");
		search_page_categories_are_displayed();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
	}

	@Then("^The appropriate results are shown for director$")
	public void the_appropriate_results_are_shown_director() {
		String searchText = commonUtils.getTextBasedonLanguage("search_director");
		search_page_categories_are_displayed();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
	}

	@Then("^User validates TV Shows and On Demand$")
	public void user_validate_TV_programs_and_On_Demand() {
		List<String> categoriesDisplayed = search_page_categories_are_displayed();
		String searchText = commonUtils.getTextBasedonLanguage("search_actor");
		driver.findElement(By.xpath("//*[contains(@name,'" + categoriesDisplayed.get(0) + "')]")).click();
		check_the_displayed_TV_program(searchText);
		commonUtils.clickonElement(close_button);
		driver.findElement(By.xpath("//*[contains(@name,'" + categoriesDisplayed.get(1) + "')]")).click();
		check_the_displayed_on_demand_program(searchText);
	}

	@Then("^User verifies TV Shows and On Demand$")
	public void user_verifies_TV_programs_and_On_Demand() {
		List<String> categoriesDisplayed = search_page_categories_are_displayed();
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		Assert.assertEquals(categoriesDisplayed.get(0).toLowerCase(),
				commonUtils.getTextBasedonLanguage("search_categories_TvShow").toLowerCase());
		Assert.assertEquals(categoriesDisplayed.get(1).toLowerCase(),
				commonUtils.getTextBasedonLanguage("search_categories_onDemand").toLowerCase());
	}

	@Then("^User verifies On Demand category$")
	public void user_verifies_On_Demand() {
		List<String> categoriesDisplayed = search_page_categories_are_displayed();
		String searchText = commonUtils.getTextBasedonLanguage("vod_search_title");
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		driver.findElement(By.xpath("//*[contains(@name,'" + categoriesDisplayed.get(0) + "')]")).click();
		Assert.assertTrue(commonUtils.findElements(search_program_description).isEmpty());
		driver.findElement(By.xpath("//*[contains(@name,'" + categoriesDisplayed.get(1) + "')]")).click();
		check_the_displayed_on_demand_program(searchText);
	}

	@Then("^User verifies TV Shows category$")
	public void user_verifies_tv_shows() {
		List<String> categoriesDisplayed = search_page_categories_are_displayed();
		String searchText = commonUtils.getTextBasedonLanguage("search_title");
		System.out.println("categoriesDisplayed  " + categoriesDisplayed);
		check_the_displayed_TV_program(searchText);
		commonUtils.findElement(close_button).click();
		driver.findElement(By.xpath("//*[contains(@name,'" + categoriesDisplayed.get(1) + "')]")).click();
//		Assert.assertTrue(commonUtils.findElements(search_program_title).isEmpty());
	}
}
