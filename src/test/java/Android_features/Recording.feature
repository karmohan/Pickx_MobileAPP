Feature: Recording Features 
	Description: Recording scenarios

Background: The user opens the Pickx app and navigates to login page 
	Given User opens the PickxTV application 
	When The User selects the environment
	Then User logged in to the Pickx app
	
@recording 
Scenario: Single recording of live airing (part of series) on home screen 
	Given User records and validate episode of live airing from homescreen 
	
@recording 
Scenario: Single recording of live airing (part of series) on live TV 
	Given User navigates to live TV page 
	Then User records and validate episode of live airing from live TV
	
@recording 
Scenario: Single recording of live airing (part of series) on EPG 
	Given User is on the TV guide page 
	Then User records and validate episode of live airing from EPG 
	
#p2	
@recording 
Scenario: Series recording of live airing on home screen 
	Given User validate series recording of live airing from homescreen
	
@recording 
Scenario: Series recording of live airing on live TV 
	Given User navigates to live TV page 
	Then User validates series recording of live airing from live TV
	
#p2
@recording 
Scenario: Series recording of live airing on EPG 
	Given User is on the TV guide page 
	Then User validates series recording of live airing from EPG 

#p2
@recording 
Scenario: Single recording of live airing (not part of series) on home screen 
	Given User records and validate live airing not part of series from homescreen
	
#p2
@recording
Scenario: Single recording of live airing (not part of series) on live TV 
	Given User navigates to live TV page 
	Then User validates recording of live airing not part of series from live TV
	
@recording 
Scenario: Single recording of live airing (not part of series) on EPG 
	Given User is on the TV guide page 
	Then User validates recording_of_live airing not part of series from EPG
	
@recording
Scenario: Series recording of replay airing (part of series) on homescreen 
	Given User validates series recording of replay airing from homescreen
	
@recording
Scenario: Recording of replay airing (not part of series) on homescreen 
	Given User validates recording of replay airing not part of series from homescreen
	
@recording 
Scenario: Recording of replay airing (not part of series) on EPG 
	Given User is on the TV guide page
	Then User validates recording of replay airing not part of series from EPG
	
@recording 
Scenario: Recording of future airing (part of series) on EPG 
	Given User is on the TV guide page
	Then User validates recording of future airing part of series from EPG
	
@recording
Scenario: Recording of future airing (not part of series) on homescreen 
	Given User validates recording of future airing not part of series from homescreen
	
@recording
Scenario: Validate recordings overview 
	Given User is on the recordings page 
	When User selects recorded tab 
	And Validate the recordings tab in recordings page 
	And User selects planned tab 
	Then Validate planned recordings tab in recordings page 
	
@recording
Scenario: Validate my videos overview 
	Given User is on my videos page 
	When User selects Recordings tab in my videos 
	And Validate recordings tab in my videos 
	And User selects Playable tab in recordings of my videos 
	And Validate playable tab in recordings of my videos 
	Then User selects Recorded tab in recordings of my videos 
	And Validate recorded tab in recordings of my videos 
	Then User selects Planned tab in recordings of my videos 
	And Validate planned tab in recordings of my videos 
	Then User selects Continue Watching tab in my videos 
	
@recording 
Scenario: Delete single recording from recordings overview (Kebab) 
	Given User is on the recordings page 
	Then Verify single record deletion from recordings using kebab