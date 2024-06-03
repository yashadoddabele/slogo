# slogo

## TEAM NUMBER: 06

## NAMES: Yasha, Yinuo, Saad, Divyansh

### Timeline

* Start Date: 02/19/24

* Finish Date: 03/08/24

* Hours Spent: Collectively, around 40-60 hours

### Running the Program

* Main class: Main.java

* Data files needed:
  * To start the program, you need some sort of image file to use as the turtle display.

* Interesting data files:
  * The pinwheel in particular demonstrates our animations in a great way.
  * Furthermore, we used the resource files for Commands, Errors/Exception messages,
  and language translations.

* Key/Mouse inputs:
  * Most keyboard letters to be used in the terminal to type commands
  * Use your mouse to click the various GUI buttons to interact with the user interface

### Notes/Assumptions

* Assumptions or Simplifications:
  * For particularly difficult commands, we had to simplify them a bit to work with our parser. For 
  example, the method to assign custom commands, to, is used with the ^ symbol: for example,
  to ^square [ repeat 4 [ fd 50 rt 90 ] ]. This is also evident in our backend implementation of the
  multiple turtle commands, such as Ask/Tell, which we were unable to implement lists of IDs and instead
  are currently implemented to only support 1 ID in the parameters at a time.

* Known Bugs:
  * Some test files do not render exactly correctly in the front end due to slower animations,
  but they are functional in the backend.

* Features implemented:
  * We implemented all commands (though some are simplified), and most core GUI features.

* Features unimplemented:
  * We were unable to connect multiple turtles to the front, but there is a premature implementation
  in the backend.

* Noteworthy Features:
  * Turtle animations follow the exact order of commands sent.
  * You can change pen and background colors, as well as change the icon of the turtle.
  * Commands and error messages can be written and displayed in Spanish and English.
  * You can save/load XML command files in the program. 
  * Our parser in the backend uses an abstract syntax tree with reflection.


