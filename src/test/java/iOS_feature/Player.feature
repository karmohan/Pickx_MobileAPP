Feature: Player Feature
Description: Player related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
Then User successfully log in to the Pickx app

@Player
Scenario: Live TV Player controls - User has a Replay subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control

@PlayerReplayPLus
Scenario: Live TV Player controls - User has a Replay plus subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control in Replay plus subscription

@PlayerStream
Scenario: Validate player live airing -Live airing in replay small and full screen
Given User selects ongoing program from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

@PlayerStream
Scenario: Validate player VOD - VOD in small and full screen
Given User selects VOD asset from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

@PlayerStreamRecording
Scenario: Validate player recording - recordings in small and full screen
Given User is on the recordings page
When User select the program in recordings to stream
And Program starts streaming
Then The user see live airing portrait and horizontal

@PlayerPerformanceTest
Scenario: Live TV Player controls - User has a Replay subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Live TV Player controls - User has a Replay subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control

#Executes in EN,FR,NL
@PlayerReplayPLusLanguage
Scenario: Live TV Player controls - User has a Replay plus subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control in Replay plus subscription

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Validate player live airing -Live airing in replay small and full screen
Given User selects ongoing program from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

#Executes in EN,FR,NL
@PlayerStreamLanguage1
Scenario: Validate player VOD - VOD in small and full screen
Given User selects VOD asset from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal