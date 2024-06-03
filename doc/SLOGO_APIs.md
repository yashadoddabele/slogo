# SLogo API Design Lab Discussion

### NAMES

### TEAM

## Planning Questions

* What behaviors (methods) should the Turtle have and what service should it provide?
    - Move certain distance in the current direction.
    - Rotation
    - Drop or lift the Turtle
* When does parsing need to take place and what does it need to start properly?
    - When the game is start and the user start to type commands
* What is the result of parsing (not the details of the algorithm) and who receives it?
    - It would be captured by the backend which returns a collection to the frontend.
    - The frontend can then execute the command by order.
* When are errors detected and how are they reported?
    - The errors can be detected in the backend during parsing, indicating the invalid commands from
      user.
* What do different commands need to know, when do they know it, and how do they get it?
    - Know the impact to the frontend update.
* What behaviors does the result of a command need to have to be used by the View?
    - Return the changes of position/color/shape
* How is the View updated after a command has completed execution?
    - Simultaneous update after execution.
* What value would Controller(s) have in mediating between the Model and View?
    - command list
    - command history
    - language

## APIs

### Model/Backend External API

* Goals
    - Efficiently execute the commands
    - Extensibility with new commands

* Contract
    - Input will be string format
    - Output the changes of turtle (position, color, shape)
    - Handle the error properly

* Services
    - `executeCommand(String command)`
    - `getPosition()`

### View/Frontend External API

* Goals
    - User friendly interface

* Contract
    - User input commands through textfields
    - Real-time visual update
    - Proper Error Handling

* Services
    - `setTurtlePosition(double x, double y)`
    - `setTurtleAngle(double angle)`
    - `showError(String errorMsg)`

### Model/Backend Internal API

* Goals
    - Contains the logic for handing commands

* Contract
    - Can read from xml for command string
    - Store command in List
* Services
    - `parseCommand(String command)`
    - `pareseFromFile(FileData fileData)`

### View/Frontend Internal API

* Goals
    - Manage GUI components
    - Interactive response to users

* Contract
    - Set GUI components correctly and interact with backend properly
* Services
    - `createXXButton()`
    - `updateScene()`

### Backend Design CRCs

```java
public class Turtle {

   private double xPos;
   private double yPos;
   private double angle;
}
 ```

```java
public interface Command {
   void execute();
}
public class MoveForwardCommand implements Command {
   private final Turtle turtle;
   private final double distance;

   public MoveForwardCommand(Turtle turtle, double distance) {
      this.turtle = turtle;
      this.distance = distance;
   }

   @Override
   public void execute() {
      turtle.moveForward(distance);
   }
}
```

### Frontend Design CRCs

```java

 ```

### Use Cases

* The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a
  trail, and has the command added to the environment's history.

* The user types '50 fd' in the command window and sees an error message that the command was not
  formatted correctly.

* The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once
  without a trail and once with a trail).

* The user changes the color of the environment's background.

