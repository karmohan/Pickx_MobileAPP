Feature: Network Specific Feature
Description: Network related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application

#Executes in EN,FR,NL
@Network
Scenario: Start app via wifi
Given User turn on wifi
When The User selects the environment
And The user accepts the terms and condition
And User successfully log in to the Pickx app

#Executes in EN,FR,NL
@Network
Scenario: Start stream via wifi
Given User turn on wifi
When The User selects the environment
And The user accepts the terms and condition
And User successfully log in to the Pickx app
And User selects ongoing program from home screen 
Then Program starts streaming 

#Executes in EN,FR,NL
@Network
Scenario: Start app with connection disabled
When User turn off wifi
When The User selects the environment
Then User gets the offline notification
And User turn on wifi

#Executes in EN,FR,NL
@Network
Scenario: Use app while connection drops
Given User turn on wifi
And The User selects the environment
And The user accepts the terms and condition
And User logged in to the Pickx app
And User selects ongoing program from home screen
And User click on try again on the playerscreen
When User turn off wifi
Then User gets the offline message while using app
And User turn on wifi

#Executes in EN,FR,NL
@Network
Scenario: Start app via wifi
Given User turn on wifi
