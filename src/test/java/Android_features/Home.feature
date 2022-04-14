Feature: Home Feature
Description: Home screen related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application
#When The User selects the "UAT" environment
When The User selects the environment
Then User logged in to the Pickx app

@Home
Scenario: Validate Hero banner content
Given Hero content is present on the home screen
When User can see hero banner content are present

@Home
Scenario: Validate bottom navigation menu items
When User can see bottom navigation menu items

@HomeStream
Scenario: Select and stream any live program from home screen 
Given User selects ongoing program from home screen 
Then Program starts streaming 
	
@HomeStream
Scenario: Select and stream any replay program from home screen 
Given User selects completed replay program from home screen 
Then Program starts streaming 

@HomeStream
Scenario: Select and stream any VOD Asset from home screen 
Given User selects VOD asset from home screen 
Then Program starts streaming

@Home
Scenario: Validate swimlane and corresponding contents 
Given User can see metadata for programs under Now on TV swimlane 
When User can see metadata for programs under Recommended swimlane 
And User can see metadata for programs under VOD swimlane 
Then User can see metadata for programs under future swimlane 

@Home
Scenario: Validate homescreen categories
Given Home screen categories are displayed
When The user validates the displayed categories on the homepage

@Home
Scenario: Validate loading skeleton - Home
Given Validate homescreen loading skeleton

@Home
Scenario: Validate Replay subscription for Swimlane
Given User validates live replayable program from Swimlane
Then User validates non-replayable live program from Swimlane
Then User validates replayable program from past on Swimlane
Then User validates upcoming replayable program on Swimlane

@HomeSpecial
Scenario: Validate parental control - Home 
Given User see locked Hero banner
When Tiles are properly locked 
Then User unlocks the locked hero banner

#Executes in EN,FR,NL
@HomeLanguage
Scenario: Validate Hero banner content
Given Hero content is present on the home screen
When User can see hero banner content are present

#Executes in EN,FR,NL
@HomeLanguage
Scenario: Validate bottom navigation menu items
When User can see bottom navigation menu items

#Executes in EN,FR,NL
@HomeLanguage
Scenario: Validate loading skeleton - Home
Given Validate homescreen loading skeleton

#Executes in EN,FR,NL
@HomeStreamLanguage
Scenario: Select and stream any live program from home screen 
Given User selects ongoing program from home screen 
Then Program starts streaming

#Executes in EN,FR,NL
@HomeStreamLanguage
Scenario: Select and stream any VOD Asset from home screen 
Given User selects VOD asset from home screen 
Then Program starts streaming

#Executes in EN,FR,NL
@HomeLanguage
Scenario: Validate homescreen categories
Given Home screen categories are displayed
When The user validates the displayed categories on the homepage

#Executes in EN,FR,NL
@HomeStreamLanguage
Scenario: Select and stream any replay program from home screen 
Given User selects completed replay program from home screen 
Then Program starts streaming

#Executes in EN,FR,NL
@HomeLanguage
Scenario: Validate swimlane and corresponding contents 
Given User can see metadata for programs under Now on TV swimlane 
When User can see metadata for programs under Recommended swimlane 
And User can see metadata for programs under VOD swimlane 
Then User can see metadata for programs under future swimlane
