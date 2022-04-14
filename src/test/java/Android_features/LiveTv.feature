Feature: Live TV Feature 
Description: Live TV related scenario

Background: The user opens the Pickx app and navigates to login page 
Given User opens the PickxTV application 
When The User selects the environment
Then User logged in to the Pickx app

#Executes in EN,FR,NL
@liveTV
Scenario: Validate live TV categories 
	Given User navigates to live TV page 
	When Categories are displayed 
	Then Validate the displayed categories

#Executes in EN,FR,NL
@liveTV
Scenario: Validate program tiles - Live TV
	Given User navigates to live TV page 
	Then User can see metadata of the program tiles 

#Executes in EN,FR,NL
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

#Executes in EN,FR,NL
@liveTV
Scenario: Validate filters 
	Given User navigates to live TV page 
	Then Verify the filters are horizontally scrollable 
	And Verify respective programs are displayed for each selected category

#Executes in EN,FR,NL
@liveTV
Scenario: Validate loading skeleton - Live TV
Given User navigates to live TV page 
When Validate liveTV loading skeleton

#Executes in EN,FR,NL
@liveTV
Scenario: Validate Replay subscription for live TV
Given User navigates to live TV page 
Then User validates live replayable program from live TV
Then User validates non-replayable live program from live TV
