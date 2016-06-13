# FOOS

##### Foosball social platform and league with leaderboards  | April 2016

#### By Chris Young

## Description

Foos is an Android mobile app that includes an implementation of the TrueSkill ranking algorithm, the same as is used on Xbox Live (http://research.microsoft.com/en-us/projects/trueskill/details.aspx). The project was build in Android Studio and uses a Firebase database for data persistence and user authentication. Each user in Foos has a TrueSkill rating that is associated with their profile. As matches are entered into the app, new ratings are calculated and the leaderboard is updated accordingly. The TrueSkill algorithm uses machine learning so the more matches that have been entered, the better the algorithm will be at calculating a rating for each user. 

This is an Epicodus project to demonstrate and practice Android development concepts. It is meant to serve as a project that is built on each week, implementing concepts and functionality as they are learned. I have continued to develop the app post-Epicodus and am excited in continue adding features. If interested in contibuting, please feel free to submit a pull request and/or reach out to me at ctopheryoung@gmail.com.

## How to use Foos
First, you will need to create an account and sign in to use the app. Once you are signed in, you can browse recent matches, view the leaderboard, and enter matches between two players.

## Setup/Installation Requirements
You will need the following programs installed on your computer.
* Android Studio
* Java JDK 8+
* Android SDK

### To Run Application
* In a terminal window, navigate to ~/AndroidStudioProjects
* Run `git clone https://github.com/ctopheryoung/Foos-Android.git`
* Navigate to ~/AndroidStudioProjects/Foos-Android
* Run on either an emulator or an Android OS Device connected to a computer

##### To set up an emulator
* Select Run > Run 'app'
* Click 'Create New Emulator'
* Select the device you would like to emulate (Recommended: Nexus 6)
* Select the API level you would like to run - click 'Download' if not available (Recommended: Marshmallow - ABI: x86)
* Click 'Finish' and allow Emulator to run

## Technologies Used

Java, Android Studio, ButterKnife, Robolectric and Espresso for testing

### Legal

Copyright (c) 2016 Chris Young

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
