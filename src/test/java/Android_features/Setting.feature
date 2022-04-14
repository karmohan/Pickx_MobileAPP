Feature: Setting Feature
Description: Settings page related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
And User logged in to the Pickx app
Then The User is on settings page

#Executes in EN,FR,NL
@Settings
Scenario: Parental Control - Switch on / off Parental control
Given User login to Parental control
When User turn on Parental control
Then User turn off Parental control

#Executes in EN,FR,NL
@Settings
Scenario: Parental Control - Adapt age rating
Given User login to Parental control
When User turn on Parental control
And User open age restriction option
And User select the age restriction
Then The selected age is updated accordingly

#Executes in EN,FR,NL
@Settings
Scenario: Parental Control - Change pin
Given User login to Parental control
When User turn on Parental control
And User click on change pin code
And User enters new pin
And User enter pin again
Then Pin code changed successfully

#Executes in EN,FR,NL
@Settings
Scenario: Parental Control - Send feedback
Given User click on send feedback option
When User enter feedback and email id
Then user submit the feedback

