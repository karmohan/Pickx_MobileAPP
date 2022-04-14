Feature: SVOD Feature
Description: SVOD scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
Then User successfully log in to the Pickx app

@svod
Scenario: SVOD Asset Check
Given User selects recommended in movies and series swimlane
When The user checks the assert available in the svod

@svod
Scenario: SVOD parental control Asset Check
Given The user sees the locked video content
When The user validates the metadata in locked content

#works but app crash
@svod
Scenario: Validate SVOD content
Given User selects recommended in movies and series swimlane
Then User selects program from movie category
And  Metadata properly displayed for programs
And User navigates back to swimlane content page
Then User selects program from series category
And  Metadata properly displayed for programs

@svod
Scenario: Validate SVOD details page according to user selected language
Given The user selects program from home screen
Then Validate movie details corresponding user selected language

#works but app crash
@svodStream
Scenario: SVOD asset check and stream content
Given User selects recommended in movies and series swimlane
Then User Validates the assets of SVOD movie content
And User stream SVOD content

#works but app crash
@svodStream
Scenario: Select and stream any SVOD asset from movies category in home page
Given The user selects movie genre from home page
Then Validate movie details and Stream content

#Executes in EN,FR,NL
@svodLanguage
Scenario: SVOD Asset Check
Given User selects recommended in movies and series swimlane
When The user checks the assert available in the svod

#Executes in EN,FR,NL
@svodLanguage
Scenario: Validate SVOD details page according to user selected language
Given The user selects program from home screen
Then Validate movie details corresponding user selected language

@svodPix
Scenario: SVOD Asset Check
Given User selects SVOD swimlane
When The user checks the assert available in the svod