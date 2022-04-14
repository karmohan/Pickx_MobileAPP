Feature: TV guide Feature
Description: EPG scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And User logged in to the Pickx app
Then User is on the TV guide page

@TVguide
Scenario: Validate EPG UX
Given The user scroll body horizontally
When User scroll the channel vertically

@TVguide
Scenario: Validate date structure
Given The user clicks on the date picker
When The user validates the date shown in the date picker
Then The user check previous and the future day is shown in the date picker

@TVguide
Scenario: Validate non-playable channels UI 
	Given User selects non-playable live program 
	Then Metadata properly displayed for non-playable live program

@TVguide
Scenario: Validate loading skeleton - TV Guide
Given Validate TV guide loading skeleton

@TVguide
Scenario: Validate parental control - Tv Guide
	Given User navigates to home page
	When The User is on settings page 
	And User login to Parental control 
	And User turn on Parental control 
	And User open age restriction option 
	And User select the age restriction as ten 
	And User navigates back to home page 
	And User is on the TV guide page
	Then Verify if the EPG program is unlocked

@TVguide
Scenario: Validate Replay subscription for Tv guide
Given User is on the TV guide page 
Then User validates live replayable program from TV guide 
And User closes program details page to reach tv guide
Then User validates non-replayable live program from TV guide  
And User closes program details page to reach tv guide
Then User validates replayable program from past on TV guide 
And User closes program details page to reach tv guide
Then User validates upcoming replayable program on TV guide 
And User closes program details page to reach tv guide
	
@TVguideStream
Scenario: Validate date picker
Given The user clicks on the date picker
When The user validates the program for different dates

@TVguideStream
Scenario: Validate replay 7 days 
Given The user clicks on the date picker
Then User select and stream past replayable program 

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate loading skeleton - TV Guide
Given Validate TV guide loading skeleton

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate EPG UX
Given The user scroll body horizontally
When User scroll the channel vertically

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate date structure
Given The user clicks on the date picker
When The user validates the date shown in the date picker
Then The user check previous and the future day is shown in the date picker

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate non-playable channels UI 
	Given User selects non-playable live program 
	Then Metadata properly displayed for non-playable live program

#Executes in EN,FR,NL	
@TVguideStreamLanguage
Scenario: Validate date picker
Given The user clicks on the date picker
When The user validates the program for different dates

#Executes in EN,FR,NL	
@TVguideStreamLanguage
Scenario: Validate replay 7 days 
Given The user clicks on the date picker
Then User select and stream past replayable program

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate parental control - Tv Guide
	Given User navigates to home page
	When The User is on settings page 
	And User login to Parental control 
	And User turn on Parental control 
	And User open age restriction option 
	And User select the age restriction as ten 
	And User navigates back to home page 
	And User is on the TV guide page
	Then Verify if the EPG program is unlocked

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate Replay subscription for Tv guide
Given User is on the TV guide page 
Then User validates live replayable program from TV guide 
And User closes program details page to reach tv guide
Then User validates non-replayable live program from TV guide  
And User closes program details page to reach tv guide
Then User validates replayable program from past on TV guide 
And User closes program details page to reach tv guide
Then User validates upcoming replayable program on TV guide 
And User closes program details page to reach tv guide
