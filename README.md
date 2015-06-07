#Hexiles#
##Gameplay##
Click [here](http://phillipjohnson.github.io/hexiles-web/game.html) to see a live version of the game.
##Technology##
This game was developed almost entirely in [Scala.js](http://www.scala-js.org/): A technology which converts Scala source code into JavaScript.

I have a few lines of vanilla JavaScript in the html file to support displaying the instructions, but all of the core application logic and rendering is built in Scala.
##Working With the Project##
 1. [Download and install sbt.](http://www.scala-sbt.org/0.13/tutorial/Setup.html)
 2. Clone [the project](https://github.com/phillipjohnson/hexiles-web.git).
 3. Navigate to the hexiles-web folder in your terminal.
 4. Run `sbt`.
 5. Run `test` to compile and test the project.
 6. Fire up a webserver and open game.html. (I use IntelliJ to make this super easy.)
 7. Start hacking on the project yourself!