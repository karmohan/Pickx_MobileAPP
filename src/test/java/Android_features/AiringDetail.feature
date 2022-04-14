Feature: Airing details page features 
Description: Airing scenario

Background: The user opens the Pickx app and navigates to login page 
Given User opens the PickxTV application 
When The User selects the environment
Then User logged in to the Pickx app 

#Executes in EN,FR,NL
@airingDetail
Scenario: Validate airing detail for live airing 
	Given User is on the TV guide page 
	When User selects series live program from TV guide 
	And Metadata properly displayed for ongoing program 
	Then User closes program details page to reach tv guide
	And User selects non-series live program from TV guide 
	And Metadata properly displayed for ongoing program 
	Then User closes program details page to reach tv guide 
	And User selects non-playable live program 
	Then Metadata properly displayed for non-playable live program 

#Executes in EN,FR,NL
@airingDetail
Scenario: Validate airing detail for replay airing 
	Given User is on the TV guide page
	When User selects yesterday in TV guide
	Then User selects series replay program from TV guide
	And Metadata properly displayed for series replay content
	Then User closes program details page to reach tv guide
	And User selects non-series replay program from TV guide
	And Metadata properly displayed for non-series replay content

#Executes in EN,FR,NL
@airingDetail
Scenario: Validate airing detail for future airing 
	Given User is on the TV guide page 
	When User selects tomorrow in TV guide 
	And User selects series upcoming program from TV guide 
	And Metadata properly displayed for upcoming program 
	Then User closes program details page to reach tv guide
	And User selects non-series upcoming program from TV guide 
	And Metadata properly displayed for upcoming program 
	Then User closes program details page to reach tv guide
	And User selects non-playable upcoming program 
	Then Metadata properly displayed for non-playable upcoming program 

#Executes in EN,FR,NL
@airingDetail
Scenario: Validate airing detail of SVOD items 
	Given User selects recommended in movies and series swimlane 
	When User selects a playable vod movie 
	And Metadata properly displayed for playable vod item
	Then User navigates back to swimlane content page 
	And User selects a playable vod series 
	And Metadata properly displayed for playable vod item 
	Then User navigates back to swimlane content page
