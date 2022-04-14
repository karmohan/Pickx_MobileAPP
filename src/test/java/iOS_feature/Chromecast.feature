Feature: Chromecast Feature
Description: chromecast related scenario

Background: The user opens the Pickx app and navigates to login page
Given User opens the PickxTV application 
When The User selects the environment
Then User logged in to the Pickx app 

#Executes in EN,FR,NL
@chromecast
Scenario: Connect to Chromecast device - Chromecast icon
Given The user tab on the Chromecast icon
When The user see a list of available casting option
And User select a device
Then User see the device is being connected to the selected device

#Executes in EN,FR,NL
@chromecast
Scenario: Disconnect from the Chromecast device - Chromecast icon
Given The user connected to the casting device
When The user disconnected cast
Then User can see Chromecast option is to cast a new device
