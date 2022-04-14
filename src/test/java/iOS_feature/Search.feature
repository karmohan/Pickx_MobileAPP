Feature: Search Feature
Description: Search related test cases

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
And User successfully log in to the Pickx app

#Executes in EN,FR,NL
@Search
Scenario: Search text in the search box
Given The User is on search page
When User search for a text
Then The appropriate results are shown for search text

#Executes in EN,FR,NL
@SearchNotWorking
Scenario: Search icon is displayed correctly in the landscape mode
Given The User rotate the oriantation to landscape
When The User is on search page
And User verifies the search box in landscape mode

#Executes in EN,FR,NL
@Search
Scenario: Search a text by cleaning the entered text using backspace and X button
Given The User is on search page
When The User clean the entered text using the X button
And The User clean the entered text using the backspace button
Then User verifies new search is possible after cleaning the text

#Executes in EN,FR,NL
@Search
Scenario: Search is continued after partially clearing the Search text
Given The User is on search page
When User enter the text and check the presence of the X button
And User click the backspace to reduce the text to 3 character
Then User continue entering the search text "again"

#Executes in EN,FR,NL
@Search
Scenario: Empty Search page is displayed on clicking on the Search icon in the Homescreen
Given The User is on search page
When Empty Search page is displayed

#Executes in EN,FR,NL
@Search
Scenario: Bottom bar navigation is not present on top of device keyboard on clicking on Search in the Homescreen
Given The User is on search page
When Bottom navigation bar is "invisible"

#Executes in EN,FR,NL
@Search
Scenario: The Search page has a search box with the text 'Title, actor or director'
Given The User is on search page
When The user validates the default text of the text box

#Executes in EN,FR,NL
@Search
Scenario: Search suggestion is displayed when entering 3 invalid characters
Given The User is on search page
When User enters "invalidSearchText" in the search box
Then No Search suggestions are displayed
And Search for "invalidSearchText" is displayed under the Search box

#Executes in EN,FR,NL
@Search
Scenario: Search suggestion is not displayed when the Search text is less than 3 characters
Given The User is on search page
When User enters 1 characters to search box
Then No Search suggestions are displayed
Then User enters 2 characters to search box
Then No Search suggestions are displayed

#Executes in EN,FR,NL
@Search
Scenario: Search suggestion disappears when the Search text is cleared upto 2 characters
Given The User is on search page
When User enters "searchText" in the search box
Then Appropriate suggestions for "searchText" are shown
When User reduces the "searchText" to 2 characters
Then No Search suggestions are displayed
And Search for "searchText" is displayed under the Search box

#Executes in EN,FR,NL
@Search
Scenario: Search sugeestion appears for a search text with error
Given The User is on search page
When User enters "searchTextWithError" in the search box
Then Appropriate suggestions for "searchTextWithError" are shown

#Executes in EN,FR,NL
@SearchNoPipeline
Scenario: Entire search suggestion line is clickable
Given The User is on search page
When User enters "searchText" in the search box
Then Appropriate suggestions for "searchText" are shown
When User clicks on starting position of any of the suggestions
Then Suggestion is clickable
When User reduces the "searchText" to 4 characters
When User clicks on middle position of any of the suggestions
Then Suggestion is clickable
When User reduces the "searchText" to 3 characters
When User clicks on arrow present at the end of the suggestions
Then Suggestion is clickable

#Executes in EN,FR,NL
@Search
Scenario: Keyboard disappears on scrolling the search suggestions
Given The User is on search page
When User enters "searchText" in the search box
Then Appropriate suggestions for "searchText" are shown
When User scolls on the search suggestions
Then Keyboard disappears

#Executes in EN,FR,NL
@Search
Scenario: Keyboard reappers on tapping the Search box
Given The User is on search page
When User enters "searchText" in the search box 
Then Appropriate suggestions for "searchText" are shown
When User scolls on the search suggestions
Then Keyboard disappears
When User taps on the search box
Then Keyboard reappears

#Executes in EN,FR,NL
@Search
Scenario: Precise search is done on clicking on a suggestion
Given The User is on search page
When User enters "searchText" in the search box 
Then Precise suggestions for "searchText" are shown
When User clicks on precise suggestion based on "searchText" 
Then Precise search results for "searchText" are shown

#Executes in EN,FR,NL
@Search
Scenario: Unprecise search is done on not using suggestions
Given The User is on search page
When User enters "unpreciseSearchText" in the search box 
Then Unprecise suggestions for "unpreciseSearchText" are shown
When User clicks on unprecise suggestion based on "unpreciseSearchText" 
Then Unprecise search results for "unpreciseSearchText" are shown

#Executes in EN,FR,NL
@Search
Scenario: Last suggestion is Search for Text written by user
Given The User is on search page
When User enters "searchText" in the search box
Then Appropriate suggestions for "searchText" are shown
And Last suggestion is Search for "searchText" 
	
#Executes in EN,FR,NL
@Search
Scenario: Only 'Search for Text written by user' suggestion is displayed with Search text < 3 characters
Given The User is on search page
When User enters 2 characters to search box
Then Search for "searchText" is displayed under the Search box

#Executes in EN,FR,NL
@SearchNotWorking
Scenario: Bottom navigation is visible in landscape mode with keyboard not nvisible
Given Device is in landscape mode before entering search page
When The User is on search page
Then Bottom navigation bar is "not visible"
When User scrolls down the keyboard so as to hide the keyboard
Then Bottom navigation bar is "visible"

#Executes in EN,FR,NL
@SearchNoPipeline
Scenario: Chromecast mini player test
Given User connect to chromecast device
When Click and play any program to cast
And Mini player is visible on bottom navigation
And Search page with keyboard is opened by clicking search button from bottom navigation
And Mini player is not visible
And Close the keyboard

#Executes in EN,FR,NL
@SearchNoPipeline
Scenario: Chromecast - All mini player buttons on the search page function correctly
Given User connect to chromecast device
When Click and play any program to cast
Then Mini player is visible on bottom navigation
And All mini player buttons on the search page function correctly

@Search
Scenario: Search text with empty results
Given The User is on search page
When User search for an empty text
Then User gets an error message for empty result

@Search
Scenario: Search text using suggestions in search box
Given The User is on search page
Given User search for a text using suggestion
Then The appropriate results are shown for actor

@Search
Scenario: Search for program title, actor and director
Given The User is on search page
When User search for a program title
Then The appropriate results are shown for program
And User clears search box
When User search for a program actor
Then The appropriate results are shown for actor
And User clears search box
When User search for a program director
Then The appropriate results are shown for director
And User clears search box

@Search
Scenario: Validate TV Shows and On demand categories
Given The User is on search page
When User search for a program actor
Then User validates TV Shows and On Demand

@Search
Scenario: Verify 'TV Shows' and 'On Demand' are displayed
Given The User is on search page
When User search for a program title
Then The appropriate results are shown for program
And User verifies TV Shows and On Demand 

@Search
Scenario: Verify 'On Demand' programs are displayed
Given The User is on search page
When User search for a On demand program
And User verifies On Demand category

@Search
Scenario: Verify 'TV Shows' programs are displayed
Given The User is on search page
When User search for a program title
Then User verifies TV Shows category
