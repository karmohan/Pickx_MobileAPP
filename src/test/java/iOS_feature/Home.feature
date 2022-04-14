	Feature: Home Feature
Description: Home screen related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
Then User successfully log in to the Pickx app

@Home
Scenario: Validate Hero banner content
Given Hero content is present on the home screen
When User can see hero banner content are present

@Home
Scenario: Validate bottom navigation menu items
When User can see bottom navigation menu items

@Home
Scenario: Validate the build version
When User can see bottom navigation menu items
Then The User is on settings page

@HomeAfterLocator
Scenario: Validate homescreen categories
Given Home screen categories are displayed
When The user validates the displayed categories on the homepage

@Home
Scenario: Validate loading skeleton - Home
Given Validate homescreen loading skeleton

#Not executed
@HomeSpecial
Scenario: Validate parental control
Given User see locked Hero banner
When Tiles are properly locked 
Then User unlocks the locked hero banner

#Not executed
@Home1
Scenario: Validate swimlane and corresponding contents 
	Given User can see metadata for programs under Now on TV swimlane 
	When User can see metadata for programs under Recommended swimlane 
	And User can see metadata for programs under VOD swimlane 
	Then User can see metadata for programs under future swimlane 

@HomeStream
Scenario: Select and stream any live program from home screen 
	Given User selects ongoing program from home screen 
	Then Program starts streaming 
	
@HomeStream1
Scenario: Select and stream any replay program from home screen 
	Given User selects completed replay program from home screen 
	Then Program starts streaming 
	
@HomeStream
Scenario: Select and stream any VOD Asset from home screen 
	Given User selects VOD asset from home screen 
	Then Program starts streaming

@HomeScreenPerformanceTest
Scenario: Validate Hero banner content
Given Hero content is present on the home screen
When User can see hero banner content are present
	
@HomeScreenPerformanceTest
Scenario: Validate loading skeleton
Given Validate homescreen loading skeleton
	
@HomeScreenPerformanceTest
Scenario Outline: Validate bottom navigation menu items multiple times
When User can see bottom navigation menu "<menu>" items
Then The User is on settings page

Examples:
    | menu	|
    | 1	|
    | 2	|
    | 3	|
   
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
@HomeLanguage
Scenario: Validate the build version
When User can see bottom navigation menu items
Then The User is on settings page

@Homespl
Scenario: Validate Hero banner content
Given Use get app activity
Given Hero content is present on the home screen
When User can see hero banner content are present

