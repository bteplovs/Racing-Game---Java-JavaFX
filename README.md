# Team 010's SENG201 Project - Big Money Racer
Big Money Racer is a marathon style racing game that combines risk/reward mechanics with resource management.
The player competes in a long distance marathon where every decision counts: push the pedal to the metal and risk not finishing
or play it safe and settle for a smaller share of the prize pool. Unpredictable events can halt the race, wreck cars,
or reward the player. Along the way, meet quirky characters, each revealing fragments of the drivers purpose. The
ultimate measure of a racers success is the money earned by the end of the season... and  sometimes, simply surviving it.


**See how the game plays on Youtube:** https://youtu.be/xt3E5zvBL4w?si=HtH_IfccCtWc48ID

## What's Included
This project comes with some basic examples of the following (including dependencies in the build.gradle file):
- JavaFX
- Junit 5

We have also included a basic setup of the Gradle project and Tasks required for the course including:
- Required dependencies for the functionality above
- Test Coverage with JaCoCo
- Build plugins:
    - JavaFX Gradle plugin for working with (and packaging) JavaFX applications easily

## Importing Project (Using IntelliJ)
IntelliJ has built-in support for Gradle. To import your project:

- Launch IntelliJ and choose `Open` from the start-up window.
- Select the project and click open
- At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

**Note:** *If you run into dependency issues when running the app or the Gradle pop up doesn't appear then open the Gradle sidebar and click the Refresh icon.*

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app.
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team0-1.0-SNAPSHOT.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results should be printed to the command line

## Authors
- Bogdans Teplovs and Aljaz Smrekar

**Note from the developers:** Thanks for playing Big Money Racer! we hope you enjoy playing it as much as we enjoyed making it! 
