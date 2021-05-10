# Description
This is an Android Application, that is used to assist a golfer in picking the best hour to play a round of golf on their chosen golf course. This is done by giving a reccomendation based on which hour has the best weather conditions over a 12 hour period. It also helps the user record their score while playing so that the user can keep track of their previous games and try to improve their score over time.  
#Authors
Robert Donnelly <br>
Steven Joyce<br>
Evan Greaney
# Features
The app contains seven main features, these include: 

+ Current Weather Conditions (Home)
+ Next Twelve Hour Weather Conditions (Weather)
+ Saving Round Total and Nett Score (Scores)
+ Viewing Round Total and Nett Scores (User History)
+ Registering (Register)
+ Logging in (Login)
+ Personalised Settings (Settings)

## Current Weather Conditions
This Feature displays current weather conditions for your chosen golf course and gives a rating based on Five weather conditions,(Rainfall, Wind Speed, Temperature, Real Feel Temperature and Humidity). All these conditions are compared to values within the app and depending on how good the condition is, it will give it a rating between 0.0 and 1.0 all adding up to a value between 0.0 and 5.0. If the hour is before sunrise and after sunset, the rating will be automatically set to 0.0 as the user will have no vision in the dark. 

## Next Twelve Hour Weather Conditions
This Feature is exactly the same as the current weather conditions feature when outputted to the user. It displays the same five values and the rating for the hour and also displays for the next twelve hours showing the best hour to start a round of golf.

## Saving Round Total and Nett Score
For this feature of the app, we display a table to the user to input their scores along with the Par for the hole, the user will fil the score for all 18 holes and then their total par and score will be saved to the database.

## Viewing Round Total and Nett Scores 
This is where the user is able to see their previous games results that only match their UserID which is set in the settings page. The user presses the get results button and their score data will be shown. These results are retreived from the database and displays the score along with the time, date and the golf course they played on.

## Registering
To allow the user to create an account, this feature was created. It allows the user to create an account to store data from the application. To create an account , the user is required to provide an email and password. The user will receive an alert that the account was verified.   

## Logging in
We implented the login feature to allow the user to send the score data to the database and get back an instant response in the user history section of our app. Without this the user would not be able to output any saved data in User History.

## Personalised Settings
Within the settings feature of our app, the user is able to set their preferred golf course, their handicap and their UserID. These can be used throughout the application to cater the application to the needs of the user. 


# Compiling, Deploying and Running the Project

## IDE Installation 
The first thing to do is check if you have IntelliJ IDEA Ultimate is installed.<br>
It is included free with GitHub Student Developer pack <br>
If you only have the community version installed, uninstall this edition to avoid conflicts.<br>
Please download and install the JetBrains Toolbox - [JetBrains Toolbox download link](https://www.jetbrains.com/toolbox-app/)<br>
When toolbox has installed, please install IntelliJ IDEA Ultimate Edition, version: 2021.1.1 or latest version available.

## SDK Installation
To install an SDK, follow the guidelines set out on this webpage [SDK install guide](https://www.jetbrains.com/help/idea/sdk.html)<br>
The default AVD emulator used for the project is the Pixel 3A phone with Android 9.0(Pie) with API 28  
The AVD can be installed be following these steps;

    1. Open the Intellij Ultimate IDE Desktop App
    2. On the top toolbar click on Tools
    3. Then click the last choice called Android
    4. This will open up a menu - click on AVD Manager
    5. When the AVD manager opens up click on Create virtual device
    6. Choose the phone you wish to run the app on and set it to android 9.0(Pie).

## ADK Installation 
The default AVD emulator we used for testing the project is the Pixel 3 phone with Android 9.0(Pie) with API 28. \newline
Steps to install the Android Development Kit

    1. Open Intellij
    2. On the top toolbar click on Tools
    3. Then click the last choice called Android
    4. This will open up a menu - click on AVD Manager
    5. When the AVD manager opens up click on Create virtual device
    6. Choose the phone you wish to run the app on and set it to android 9.0(Pie)

## Cloning the Application
To deploy the application, you must first clone the Application from this GitHub Repository using the terminal or GitHub Desktop.

## Deployment
There are three seperate ways to deploy our application but reccomend only two methods

    1. Deploy to Emulator from APK File
    2. Deploy to Phone
    3. Deploy Android Emulator via IntelliJ

### Deploy to Android Emulator from APK File
Any Android Emulator should be capable of running the app-release.APK file but for this demonstration we are using the [Genymotion Android Emulator](https://www.genymotion.com)<br>

+ Download and install the Genymotion Android Emulator and set up the emulator as per [Genymotion Android Emulator Start Up](https://docs.genymotion.com/desktop/latest/01_Get_started.html#using-a-license-server)
+ When Setting up the virtual device make sure the Android version is set to 9.0 - API 28
+ Once the emulator is loaded drag the Apk file found in [APKRelease](https://github.com/stevenJoyce/4thYearGroupProject/tree/main/APKRelease) into the emulator.
+ The App should run

Please note: The App doesn't run as smoothly when deployed to this emulator compared to the being deployed on phones and on the Android Emulator included with the IntelliJ IDEA when the Android ADK is installed.

### Deploy to Mobile/Emulator 
For this section we will show how to deploy to the android emulator and mobile device from IntelliJ which can be done following [Build/Run Steps for Android](https://www.jetbrains.com/help/idea/create-your-first-android-application.html#build-run-Android-application).

You can deploy to mobile straight from IntellJ IDEA Ultimate to your android phone by changing the default phone option from the Android Emulator to your mobile device and then press the play button. 

