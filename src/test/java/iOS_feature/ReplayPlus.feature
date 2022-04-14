Feature: Replay Plus Feature
Description: Replay Plus scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
And User successfully log in to the Pickx app

#	added by sree 02/09/21	
@Replayplus
Scenario: Validate ReplayPlus subscription for Tv guide
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
@Replayplus
Scenario: Validate ReplayPlus subscription for live TV
Given User navigates to live TV page 
Then User validates replayable program from live TV
Then User validates non-replayable program from live TV

#Executes in EN,FR,NL
@ReplayplusLanguage
Scenario: Validate ReplayPlus subscription for live TV
Given User navigates to live TV page 
Then User validates replayable program from live TV
Then User validates non-replayable program from live TV
