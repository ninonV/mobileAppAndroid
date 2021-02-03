# Application mobile on Android Studio
Made by Ninon Vrignaud for CS 8803-MAS.

## Repository presentation

This repository contains the code to launch the Android application for the first programming assignment.
The implementation is based on the sample [Motion](https://github.com/android/animation-samples/tree/main/Motion).
This sample shows how to implement motion design using Material Design on Android.

## What I added

I used this application to become more familiar with Android Studio, and then I added a login page. 
I connected this application with an API REST created for this projet that can be found at [mas-hw1-restApi](https://github.com/DuvalSim/mas-hw1-restApi). 
This application will check that the data received by the API REST correspond to the login information the user provided. 

## How to run
* Download this repository
* Change the address of the server running in local in the function loginRequest() in the file "LoginActivity.java"
* Connect your device by allowing the developper mode or use an emulator
* Run the app, this will launch the parent activity which is "LoginActivity.java"

The application will be installed on the device. 
