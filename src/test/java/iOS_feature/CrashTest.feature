Feature: Complete App Feature
Description: This feature is to test the App crashes.

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
Then User successfully log in to the Pickx app

@CrashTest
Scenario Outline: Validate bottom navigation menu items multiple times
When User can see bottom navigation menu "<menu>" items
Then The User is on settings page

Examples:
    | menu	|
    | 1	|
    | 2 |
    | 3	|
    | 4	|
    | 5	|
    
@CrashTestinloop1
Scenario: Validate Hero banner content
Given Hero content is present on the home screen
When User can see hero banner content are present

@CrashTestinloop
Scenario: Validate Hero banner content
Given Validate Hero banner content in loop

@CrashTestinloop1
Scenario: Validate program tiles 
Given User navigates to live TV page 
Then User can see metadata of the program tiles
	
@CrashTest
Scenario: Validate date structure
Given User is on the TV guide page
When The user clicks on the date picker
And The user validates the date shown in the date picker
Then The user check previous and the future day is shown in the date picker

@CrashTest
Scenario: Parental Control - Send feedback
Given The User is on settings page
When User click on send feedback option
And User enter feedback and email id
Then user submit the feedback

@CrashTest
Scenario: Live TV Player controls - User has a Replay subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control

@CrashTest
Scenario: Validate recordings overview 
	Given User is on the recordings page 
	When Validate the recordings tab in recordings page 
	And User selects planned tab 
	Then Validate planned recordings tab in recordings page
	
@CrashTest
Scenario: Validate EPG UX
Given User is on the TV guide page
When The user scroll body horizontally
Then User scroll the channel vertically
	
@CrashTestEPG
Scenario Outline: Validate EPG and scroll down to different channel
Given User is on the TV guide page
When The user clicks on the date picker
And The user validates the date shown in the date picker
And The user check previous and the future day is shown in the date picker
Then The user scroll body vertically and horizontally "<number>" continously

Examples:
    | menu	|
    | 1	|
    | 2 |
    | 3	|
    | 4 |
    | 5	|