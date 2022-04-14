Feature: Player Feature
Description: Player related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
Then User logged in to the Pickx app

@PlayerStream
Scenario: Validate player live airing -Live airing in replay small and full screen
Given User selects ongoing program from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

@PlayerStream
Scenario: Validate player replay airing - replay airing in small and full screen
Given User selects completed replay program from home screen
When Program starts streaming 
Then The user see live airing portrait and horizontal

@PlayerStream
Scenario: Validate player VOD - VOD in small and full screen
Given User selects VOD asset from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

@PlayerStream
Scenario: Live TV Player controls - User has a Replay subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control

@PlayerReplayPlusStream
Scenario: Live TV Player controls - User has a Replay plus subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control in Replay plus subscription

@PlayerStream
Scenario: Validate player behavior - Live Forward and Rewind - User has a Replay subscription
Given User selects ongoing program from home screen
When Program starts streaming
Then User able to FRW and not able FFR the video

@PlayerStream
Scenario: Validate player behavior - Reply Forward and Rewind - User has a Replay subscription
Given User selects completed replay program from home screen
When Program starts streaming
Then User able to FRW and not able FFR the video

@PlayerStream
Scenario: Validate player behavior - VOD Forward and Rewind - User has a Replay subscription
Given User selects VOD asset from home screen 
Then Program starts streaming
Then User able to FRW and able FFR the video

@PlayerReplayPlusStream
Scenario: Validate player behavior - Live Forward and Rewind - User has a ReplayPlus subscription
Given User selects ongoing program from home screen
When Program starts streaming
Then User able to FRW and able FFR the video

@PlayerReplayPlusStream
Scenario: Validate player behavior - Replay Forward and Rewind - User has a ReplayPlus subscription
Given User selects completed replay program from home screen
When Program starts streaming
Then User able to FRW and able FFR the video

@PlayerReplayPlusStream
Scenario: Validate player behavior - VOD Forward and Rewind - User has a ReplayPlus subscription
Given User selects VOD asset from home screen 
Then Program starts streaming
Then User able to FRW and able FFR the video

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Validate player live airing -Live airing in replay small and full screen
Given User selects ongoing program from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Validate player VOD - VOD in small and full screen
Given User selects VOD asset from home screen 
When Program starts streaming 
Then The user see live airing portrait and horizontal

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Live TV Player controls - User has a Replay subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Validate player behavior - Live Forward and Rewind - User has a Replay subscription
Given User selects ongoing program from home screen
When Program starts streaming
Then User able to FRW and not able FFR the video

#Executes in EN,FR,NL
@PlayerStreamLanguage
Scenario: Validate player behavior - VOD Forward and Rewind - User has a Replay subscription
Given User selects VOD asset from home screen 
Then Program starts streaming
Then User able to FRW and able FFR the video

#Executes in EN,FR,NL
@PlayerReplayPlusStreamLanguage
Scenario: Validate player behavior - VOD Forward and Rewind - User has a ReplayPlus subscription
Given User selects VOD asset from home screen 
Then Program starts streaming
Then User able to FRW and able FFR the video

#Executes in EN,FR,NL
@PlayerReplayPlusStreamLanguage
Scenario: Live TV Player controls - User has a Replay plus subscription 
Given User selects ongoing program from home screen
When Program starts streaming
Then The user validates player control in Replay plus subscription

#Executes in EN,FR,NL
@PlayerReplayPlusStreamLanguage
Scenario: Validate player behavior - Live Forward and Rewind - User has a ReplayPlus subscription
Given User selects ongoing program from home screen
When Program starts streaming
Then User able to FRW and able FFR the video