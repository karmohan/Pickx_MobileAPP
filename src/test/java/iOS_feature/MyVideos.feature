Feature: MyVideos Features 
	Description: MyVideos scenarios

Background: The user opens the Pickx app and navigates to login page 
Given User opens the PickxTV application 
When The User selects the environment 
And The user accepts the terms and condition
And User successfully log in to the Pickx app

#Executes in EN,FR,NL
@MyVideos
Scenario: Validate my videos overview
	Given User is on my videos page
	And Validate recordings tab in my videos 
	When User selects Playable tab in recordings of my videos 
	And Validate playable tab in recordings of my videos 
	Then User selects Recorded tab in recordings of my videos
	And Validate recorded tab in recordings of my videos 
	Then User selects Planned tab in recordings of my videos 
	And Validate planned tab in recordings of my videos 
	Then User selects Continue Watching tab in my videos

#Executes in EN,FR,NL
@MyVideosStream
Scenario: Validate continue watching in MyVideos
	Given User is on my videos page
	And User play a video in recording tab
	When The user see the streaming video
	And User navigates to continue watching the page
	Then User see the same program is available in continue watching
	
@D2G
Scenario: Validate 'Download' tab in MyVideos
Given User is on my videos page
When User navigates to "Downloads" tab
Then Use check the "Download" tab is highlighted
And User scroll down in the page
Then User scroll up in the page

#not implemented completely, because features are not implemented yet
@D2G
Scenario: Validate Download of an SVOD
Given User select the SVOD content with the download option

@D2G
Scenario: Validate downloaded SVOD movie in MyVideos
Given User is on my videos page
When User navigates to "Downloads" tab
And Use check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
Then User verify the meta details for the downloaded program in SVOD details page

@D2G
Scenario: Validate player of a download UX/UI
Given User is on my videos page
When User navigates to "Downloads" tab
And Use check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
And User choose the downloaded video to play
Then The user see live airing portrait and horizontal
