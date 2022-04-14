Feature: Airing details page features 
Description: Airing scenario

Background: The user opens the Pickx app and navigates to login page 
Given User opens the PickxTV application 
When The User selects the environment
And The user accepts the terms and condition
Then User logged in to the Pickx app

@airingDetail
Scenario: Validate airing detail for replay airing - EPG
	Given User is on the TV guide page
	Then User selects yesterday in TV guide
	Then User selects series replay program from TV guide 
	And Metadata properly displayed for series replay content
	Then User closes program details page to reach tv guide
	When User selects non-series replay program from TV guide
	Then Metadata properly displayed for non-series replay content 

@airingDetail
Scenario: Validate airing detail for live airing - EPG
	Given User is on the TV guide page  
	When User selects series live program from TV guide 
	And Metadata properly displayed for ongoing program 
	Then User closes program details page to reach tv guide 
	And User selects non-series live program from TV guide 
	And Metadata properly displayed for ongoing program 
	Then User closes program details page to reach tv guide
	And User selects non-playable live program
	Then Metadata properly displayed for non-playable live program 
	
@airingDetail
Scenario: Validate airing detail for future airing - EPG
	Given User is on the TV guide page
	Then User selects tomorrow in TV guide 
	And User selects series upcoming program from TV guide
	And Metadata properly displayed for upcoming program
	Then User closes program details page to reach tv guide
	And User selects non-series upcoming program from TV guide
	And Metadata properly displayed for upcoming program 
	Then User closes program details page to reach tv guide
	And User selects non-playable upcoming program
	Then Metadata properly displayed for non-playable upcoming program
	
#works but app crash
@airingDetail1
Scenario: Validate airing detail of SVOD items 
	Given User selects recommended in movies and series swimlane 
	When User selects program from movie category
	And Metadata properly displayed for playable vod item 
	Then User navigates back to swimlane content page 
	And User selects program from series category
	And Metadata properly displayed for playable vod item 
	Then User navigates back to swimlane content page 
	And User selects a non-playable vod item
	Then Metadata properly displayed for non-playable vod item 
	
@EPGAiringDetailPerformanceTest
Scenario: Validate airing detail for replay airing - EPG
	Given User is on the TV guide page
	Then User selects yesterday in TV guide
	Then User selects series replay program from TV guide 
	And Metadata properly displayed for series replay content
	Then User closes program details page to reach tv guide
	When User selects non-series replay program from TV guide
	Then Metadata properly displayed for non-series replay content 

@EPGAiringDetailPerformanceTest
Scenario: Validate airing detail for live airing - EPG
	Given User is on the TV guide page  
	When User selects series live program from TV guide 
	And Metadata properly displayed for ongoing program 
	Then User closes program details page to reach tv guide 
	And User selects non-series live program from TV guide 
	And Metadata properly displayed for ongoing program 
	Then User closes program details page to reach tv guide
	And User selects non-playable live program
	Then Metadata properly displayed for non-playable live program 