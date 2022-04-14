Feature: Parental control Feature
Description: Parental control related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And User logged in to the Pickx app
Then The User is on settings page
Then User login to Parental control

@ParentalControl
Scenario: Parental Control Off - Verify (age rated) programs are unlocked
Given User turn off Parental control
Then User navigates back to home page
And User selects unlocked program from home screen 
Then User is on the TV guide page 
And User selects unlocked program from TV Guide
Then User navigates to live TV page
And User selects unlocked program from Live TV

@ParentalControl
Scenario: Parental Control On - - Verify programs (without age rating) are unlocked
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction
Then User navigates back to home page
And User selects program without age rating from home screen 
Then User is on the TV guide page 
And User selects program without age rating from TV Guide
Then User navigates to live TV page
And User selects program without age rating from Live TV

@ParentalControl
Scenario: Parental Control On - - Verify (age rated) programs are locked
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten
Then User navigates back to home page
And User selects locked program from home screen
Then User is on the TV guide page 
And User selects locked program from TV Guide
Then User navigates to live TV page
And User selects locked program from Live TV

@ParentalControl
Scenario: Parental Control On - Reject parental control pin
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten 
Then User navigates back to home page
Then User navigates to live TV page
And User selects locked program from Live TV and rejects parental pin

@ParentalControl
Scenario: Parental Control On - Verify entering wrong pin
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten 
Then User navigates back to home page
Then User navigates to live TV page
And User selects locked program from Live TV and enters wrong pin

@ParentalControl
Scenario: Validate temporary parental lock - Home screen
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten 
Then User navigates back to home page
And User unlocks program temporarily from home screen
And User selects unlocked program from home screen
Then User waits for programs to lock after temporary unlock
And User selects locked program from home screen

@ParentalControl
Scenario: Validate temporary parental lock - TV Guide
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten 
Then User navigates back to home page
And User is on the TV guide page
Then User unlocks program temporarily from TV Guide
And User selects unlocked program from TV Guide
Then User waits for programs to lock after temporary unlock
And User selects locked program from TV Guide

@ParentalControl
Scenario: Validate temporary parental lock - Live TV
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten 
Then User navigates back to home page
And User navigates to live TV page
Then User unlocks program temporarily from Live TV
And User selects unlocked program from Live TV
Then User waits for programs to lock after temporary unlock
And User selects locked program from Live TV

@ParentalControl
Scenario: Validate parental Control - Home
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten
Then User navigates back to home page
And User verify if the program is unlocked

@ParentalControl
Scenario: Validate temporary parental control period
Given User turn on Parental control
Then User open age restriction option
And User select the age restriction as ten 
Then User navigates back to home page
And User unlocks program temporarily from home screen
Then User locks age restricted programs from settings
Then User navigates back to home page
And User selects locked program from home screen
Then User waits ten_minutes for temporary unlock
And User selects locked program from home screen

@ParentalControlStream
Scenario: Validate and stream parental blocked live airing
Given User turn on Parental control
When User open age restriction option
Then User select the age restriction as ten 
Then User navigates back to home page
And User navigates to live TV page
Then User selects locked live program from Live TV and stream

@ParentalControlStream
Scenario: Validate and stream parental blocked replay airing
Given User turn on Parental control
When User open age restriction option
Then User select the age restriction as ten 
Then User navigates back to home page
And User navigates to live TV page
Then User selects locked replay program from Live TV and stream

@ParentalControlStream
Scenario: Stream age rated VOD and validate in continue watching swimlane
Given User turn off Parental control
When User navigates back to home page
Then User validate age rated VOD asset from home screen 
And Program starts streaming
Then User validate VOD asset in CW swimlane
And User locks age restricted programs from settings
And User navigates back to home page
Then User validates locked VOD asset in CW swimlane

@ParentalControl
Scenario: Recording parental locked
Given User turn on Parental control
When User validates and starts recording age rated program from home screen
Then User is on my videos page
#Then User stream recorded program
#And User selects unlocked program from home screen

