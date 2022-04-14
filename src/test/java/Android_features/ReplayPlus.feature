Feature: ReplayPlus Feature
Description: ReplayPlus scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
Then User logged in to the Pickx app 

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

@Replayplus
Scenario: Validate ReplayPlus subscription for live TV
Given User navigates to live TV page 
Then User validates live replayable program from live TV
Then User validates non-replayable live program from live TV

@Replayplus
Scenario: Validate ReplayPlus subscription for Swimlane
Given User validates live replayable program from Swimlane
Then User validates non-replayable live program from Swimlane
Then User validates replayable program from past on Swimlane
Then User validates upcoming replayable program on Swimlane
