Feature: Live TV Feature 
Description: Live TV related scenario

Background: The user opens the Pickx app and navigates to login page 
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
Then User successfully log in to the Pickx app

@liveTV
Scenario: Validate loading skeleton - Live TV
Given User navigates to live TV page 
When Validate liveTV loading skeleton

@liveTV
Scenario: Validate live TV categories 
	Given User navigates to live TV page 
	When Categories are displayed 
	Then Validate the displayed categories

@liveTV 
Scenario: Validate program tiles 
	Given User navigates to live TV page 
	Then User can see metadata of the program tiles
	
@liveTV
Scenario: Validate parental control - Live Tv
	Given The User is on settings page 
	When User login to Parental control 
	And User turn on Parental control 
	And User open age restriction option 
	And User select the age restriction as ten  
	And User navigates back to home page 
	And User navigates to live TV page 
	And User selects movies category 
	Then Verify if the program is unlocked 

@liveTV
Scenario: Validate filters - Live TV
	Given User navigates to live TV page 
	Then Verify the filters are horizontally scrollable 
	And Verify respective programs are displayed for each selected category 
	
@LiveTVReplay
Scenario: Validate Replay subscription for live TV
Given User navigates to live TV page 
Then User validates replayable program from live TV
Then User validates non-replayable program from live TV

@liveTVPerformanceTest
Scenario: Validate loading skeleton
Given User navigates to live TV page 
When Validate liveTV loading skeleton

@liveTVPerformanceTest
Scenario: Validate program tiles 
	Given User navigates to live TV page 
	Then User can see metadata of the program tiles

@liveTVPerformanceTest
Scenario: Validate filters 
	Given User navigates to live TV page 
	Then Verify the filters are horizontally scrollable 
	And Verify respective programs are displayed for each selected category 
	
#Executes in EN,FR,NL
@liveTVLanguage
Scenario: Validate loading skeleton - Live TV
Given User navigates to live TV page 
When Validate liveTV loading skeleton

#Executes in EN,FR,NL
@liveTVLanguage
Scenario: Validate live TV categories 
	Given User navigates to live TV page 
	When Categories are displayed 
	Then Validate the displayed categories
	
#Executes in EN,FR,NL
@liveTVLanguage
Scenario: Validate program tiles 
	Given User navigates to live TV page 
	Then User can see metadata of the program tiles
	
#Executes in EN,FR,NL
@liveTVLanguageNoPipeline
Scenario: Validate filters - Live TV
	Given User navigates to live TV page 
	Then Verify the filters are horizontally scrollable 
	And Verify respective programs are displayed for each selected category 
	
#Executes in EN,FR,NL
@liveTVLanguage
Scenario: Validate parental control - Live Tv
	Given The User is on settings page 
	When User login to Parental control 
	And User turn on Parental control 
	And User open age restriction option 
	And User select the age restriction as ten  
	And User navigates back to home page 
	And User navigates to live TV page 
	And User selects movies category 
	Then Verify if the program is unlocked

#Executes in EN,FR,NL	
@LiveTVReplayLanguage
Scenario: Validate Replay subscription for live TV
Given User navigates to live TV page 
Then User validates replayable program from live TV
Then User validates non-replayable program from live TV

