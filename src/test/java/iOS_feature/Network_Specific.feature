Feature: Network Specific Feature
Description: Network related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application

#Executes in EN,FR,NL
@Network
Scenario: Start app via wifi
Given User turn on wifi
When User opens the app in the background
And The user accepts the terms and condition
And User successfully log in to the Pickx app
Then User can see bottom navigation menu items

#Executes in EN,FR,NL
@Network
Scenario: Start stream via wifi
Given User turn on wifi
When User opens the app in the background
And The user accepts the terms and condition
And User successfully log in to the Pickx app
And User selects ongoing program from home screen 
Then Program starts streaming 

#Executes in EN,FR,NL
@Network
Scenario: Start app with connection disabled
When User turn off wifi
And User opens the app in the background
Then User gets the offline message
And User turn on wifi

#Executes in EN,FR,NL
@Network
Scenario: Use app while connection drops
Given User turn on wifi
When User opens the app in the background
And The user accepts the terms and condition
And User successfully log in to the Pickx app
And User selects ongoing program from home screen
When User turn off wifi
And User opens the app in the background
Then User gets the offline message

#Executes in EN,FR,NL
@Network
Scenario: Start app via wifi
Given User turn on wifi
