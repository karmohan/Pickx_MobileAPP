Feature: Recording Features 
	Description: Recording scenarios

Background: The user opens the Pickx app and navigates to login page 
	Given User opens the PickxTV application 
	When The User selects the environment 
And The user accepts the terms and condition
Then User successfully log in to the Pickx app
	
@recording
Scenario: Validate my videos overview 
	Given User is on my videos page 
	When Validate recordings tab in my videos 
	And User selects Playable tab in recordings of my videos 
	And Validate playable tab in recordings of my videos 
	Then User selects Recorded tab in recordings of my videos 
	And Validate recorded tab in recordings of my videos 
	Then User selects Planned tab in recordings of my videos 
	And Validate planned tab in recordings of my videos 
	Then User selects Continue Watching tab in my videos 
	
@recording
Scenario: Validate recordings overview 
	Given User is on the recordings page 
	When Validate the recordings tab in recordings page 
	And User selects planned tab 
	Then Validate planned recordings tab in recordings page 
	
@recording1
Scenario: Delete single recording from recordings overview (Kebab) 
	Given User is on the recordings page 
	Then Verify single record deletion from recordings using kebab 
	
@recording1
Scenario: Single recording of live airing (part of series) on EPG 
	Given User is on the TV guide page 
	Then User records and validate episode of live airing from EPG 
	
@RecordingPerformanceTest	
	Scenario: Validate recordings overview 
	Given User is on the recordings page 
	When Validate the recordings tab in recordings page 
	And User selects planned tab 
	Then Validate planned recordings tab in recordings page
	