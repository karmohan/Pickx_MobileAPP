Feature: MyVideos Features 
	Description: MyVideos scenarios

Background: The user opens the Pickx app and navigates to my videos page 
Given User opens the PickxTV application 
When The User selects the environment
Then User logged in to the Pickx app
Then User is on my videos page

#Executes in EN,FR,NL
@MyVideos
Scenario: Validate my videos overview
	Given User selects Recordings tab in my videos 
	And Validate recordings tab in my videos 
	And User selects Playable tab in recordings of my videos 
	And Validate playable tab in recordings of my videos 
	Then User selects Recorded tab in recordings of my videos 
	And Validate recorded tab in recordings of my videos 
	Then User selects Planned tab in recordings of my videos 
	And Validate planned tab in recordings of my videos 
	Then User selects Continue Watching tab in my videos 

#Executes in EN,FR,NL
@MyVideosStream
Scenario: Validate continue watching in MyVideos
	Given User play a video in recording tab
	When The user see the streaming video
	And User navigates to continue watching the page
	Then User see the same program is available in continue watching
	 
@D2G
Scenario: Validate 'Download' tab in MyVideos
Given User is on my videos page
When User navigates to "Downloads" tab
Then User check the "Download" tab is highlighted
And User scroll down in the page
Then User scroll up in the page

#Not implemented completely, because features are not implemented yet
@D2G
Scenario: Validate Download of an SVOD
Given User select the SVOD content with the download option
When User taps the download button
#Then User verify the download spinner is displayed
And User verify the cancel download button is displayed
Then User waits until download is finished
And User taps the "Go to download" button
Then User verify the downloaded asset

@D2G
Scenario: Validate downloaded SVOD movie in MyVideos
Given User is on my videos page
When User navigates to "Downloads" tab
And User check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
And User taps on the downloaded content
Then User verify the meta details for the downloaded program in SVOD details page

@D2G
Scenario: Validate playback of a downloaded SVOD
Given User select the SVOD content which is already downloaded
Then User plays the content
And The user validate player controls of the content
Then User taps the "Go to download" button
Then User plays the content
And The user validate player controls of the content


@D2G
Scenario: Validate player of a download UX/UI
Given User is on my videos page
When User navigates to "Downloads" tab
And User check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
And User choose the downloaded video to play
Then The user see live airing portrait and horizontal

@D2G
Scenario: Validate watch progress of a download
Given User navigates to "Downloads" tab
When User check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
And User choose the downloaded video to play
#Then User moves the slider to the "middle" of the stream
And User exits playback and verify progress bar is displayed
And User choose the downloaded video to play
#Then User verify if the progress bar is displayed
When User navigates to continue watching the page
Then User verify previously watched video is not available in CW tab
When User navigates to homescreen 
Then User verify previously watched video is not available in CW SL

@D2G
Scenario: Validate end-of-playback of a download
Given User navigates to "Downloads" tab
When User check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
And User choose the downloaded video to play
Then User moves the slider to the "end" of the stream
And User verify progress is reset to beginning 

@D2G
Scenario: Validate player controls of a download
Given User is on my videos page
When User navigates to "Downloads" tab
And User check the "Download" tab is highlighted
And User verify the meta details for the downloaded program
And User choose the downloaded video to play
Then The user validate player controls of the content

@D2G
Scenario: Validate stop ongoing download of an SVOD
Given User select the SVOD content with the download option
When User taps the download button
#Then User verify the download spinner is displayed
And User verify the cancel download button is displayed
Then User taps on cancel download button
And User verify confirmation pop up
Then User cancels confirmation pop up and download resumes
Then User taps on cancel download button
And User verify confirmation pop up
Then User confirms cancel of download
And User verify download is stopped
And User verify stopped download is not displayed in downloads overview

@D2G
Scenario: Validate download button on SVOD detail non-playbale SVOD
Given User select the SVOD content which is non-playable
Then User verify download button is not displayed

@D2G
Scenario: Validate download button on detail pages non-SVODs
Given User selects Recordings tab in my videos 
And Validate recordings tab in my videos 
And User selects Playable tab in recordings of my videos 
Then User selects a recorded content
Then User verify download button is not displayed
And User goes back to My videos page
Given User is on the TV guide page 
Then User validates live replayable program from TV guide 
Then User verify download button is not displayed
And User closes program details page to reach tv guide
Then User validates replayable program from past on TV guide
Then User verify download button is not displayed
And User closes program details page to reach tv guide

@D2G
Scenario: Validate delete download of a downloaded asset
Given User navigates to "Downloads" tab
And User verify the meta details for the downloaded program
When User taps on the downloaded content
And User taps on delete recording button
Then User is redirected to Downloads tab
And User verify whether undo toast is diplayed
Then User taps on Undo toast
And User verify delete is cancelled and item is not removed from list
When User taps on the downloaded content
And User taps on delete recording button
Then User is redirected to Downloads tab
And User verify whether undo toast is diplayed
And User verify if the toast dissapears and the item is removed from list 

@D2G
Scenario: Validate parental control behavior for downloaded asset

