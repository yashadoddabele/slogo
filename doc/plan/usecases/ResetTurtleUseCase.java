// use case
// user resets turtle to home position

public class ResetTurtleUseCase {

  private TurtleController turtleController; // Controller for turtle actions

  public ResetTurtleUseCase(TurtleController turtleController) {
    this.turtleController = turtleController;
  }

  public void resetTurtle() {
    // User clicks a button to reset the turtle to the home position
    turtleController.resetToHome();
    // The Turtle Graphics Window updates to show the turtle at the home position
  }
}
