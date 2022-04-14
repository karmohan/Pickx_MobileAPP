Feature: TV guide Feature
Description: EPG scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
And User successfully log in to the Pickx app

@TVguide
Scenario: Validate loading skeleton - EPG
When  User is on the TV guide page
 Then Validate TV guide loading skeleton

@TVguide
Scenario: Validate EPG UX
Given  User is on the TV guide page
 Then The user scroll body horizontally
When User scroll the channel vertically

@TVguide
Scenario: Validate date structure
Given User is on the TV guide page
Then The user clicks on the date picker
When The user validates the date shown in the date picker
Then The user check previous and the future day is shown in the date picker

@TVguide
Scenario: Validate date picker
Given User is on the TV guide page
Then The user clicks on the date picker
When The user validates the program for different dates

@TVguide
Scenario: Validate parental control -TV Guide
	Given The User is on settings page 
	When User login to Parental control 
	And User turn on Parental control 
	And User open age restriction option 
	And User select the age restriction as ten  
	And User navigates back to home page 
	And User is on the TV guide page 
	Then Verify if the EPG program is unlocked
	
#	added by sree 30/08/21
@TVguide
Scenario: Validate non-playable channels UI 
Given User is on the TV guide page
	When User selects non-playable live program 
	Then Metadata properly displayed for non-playable live program

#	added by sree 24/09/21
@TVguideReplay
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

#	added by sree 02/09/21	
@TVguideStream
Scenario: Validate replay 7 days 
Given User is on the TV guide page
When The user clicks on the date picker
Then User select and stream past replayable program 	

@EPGPerformanceTest
Scenario: Validate loading skeleton
When  User is on the TV guide page
 Then Validate TV guide loading skeleton

@EPGPerformanceTest
Scenario: Validate EPG UX
Given  User is on the TV guide page
 Then The user scroll body horizontally
When User scroll the channel vertically

@EPGPerformanceTest
Scenario: Validate date structure
 Given User is on the TV guide page
 Then The user clicks on the date picker
When The user validates the date shown in the date picker
Then The user check previous and the future day is shown in the date picker

@EPGPerformanceTest
Scenario Outline: Validate EPG and scroll down to different channel
Given User is on the TV guide page
When The user clicks on the date picker
And The user validates the date shown in the date picker
And The user check previous and the future day is shown in the date picker
Then The user scroll body vertically and horizontally "<number>" continously

Examples:
    | menu	|
    | 1	|
    | 2	|
    | 3	|

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate loading skeleton - EPG
When  User is on the TV guide page
 Then Validate TV guide loading skeleton
 
 #Executes in EN,FR,NL
@TVguideLanguageNoPipeline
Scenario: Validate EPG UX
Given  User is on the TV guide page
 Then The user scroll body horizontally
When User scroll the channel vertically

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate date structure
Given User is on the TV guide page
Then The user clicks on the date picker
When The user validates the date shown in the date picker
Then The user check previous and the future day is shown in the date picker

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate parental control -TV Guide
	Given The User is on settings page 
	When User login to Parental control 
	And User turn on Parental control 
	And User open age restriction option 
	And User select the age restriction as ten  
	And User navigates back to home page 
	And User is on the TV guide page 
	Then Verify if the EPG program is unlocked
	
#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate non-playable channels UI 
Given User is on the TV guide page
	When User selects non-playable live program 
	Then Metadata properly displayed for non-playable live program

#Executes in EN,FR,NL
@TVguideStreamLanguage
Scenario: Validate replay 7 days 
Given User is on the TV guide page
When The user clicks on the date picker
Then User select and stream past replayable program 

#Executes in EN,FR,NL
@TVguideStreamLanguage
Scenario: Validate Replay subscription for Tv guide
Given User is on the TV guide page 
Then User validates live replayable program from TV guide 
And User closes program details page to reach tv guide
Then User validates replayable program from past on TV guide 
And User closes program details page to reach tv guide
Then User validates upcoming replayable program on TV guide 
And User closes program details page to reach tv guide

#Executes in EN,FR,NL
@TVguideLanguage
Scenario: Validate date picker
Given User is on the TV guide page
Then The user clicks on the date picker
When The user validates the program for different dates