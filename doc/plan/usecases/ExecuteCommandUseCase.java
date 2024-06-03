// use case 1 :
// The user types 'fd 50' in the command window,
// and sees the turtle move in the display window leaving a trail,
// and the command is added to the environment's history.

public class ExecuteCommandUseCase {

  private SLogoApi api; // This would be our SLogo API to interact with the model.
  private Animation animation; // This is the animation class to show movement in the GUI. Full implementation pending.

  public ExecuteCommandUseCase(SLogoApi api, Animation animation) {
    this.api = api;
    this.animation = animation;
  }

  //executing command here after parsing.
  public void executeForwardCommand() {
    // User types 'fd 50' which is received by the GUI
    String command = "fd 50";

    // The GUI sends the command to the backend through the API
    boolean success = api.sendCommand(command);
    // As of now, the send command is the command that would then call the parser

    // If the command is successful, the Animation class will be called to move the turtle
    if (success) {
      animation.updateTurtleMovement();
      // Add command to history through API which interacts with CommandHistory class
      api.updateCommandHistory(command);
    } else {
      // Display error through GUI if the command fails
      // This is a simple representation, actual implementation will depend on GUI framework
      System.out.println("Error: Command failed to execute.");
    }
  }
}

// The Parser class is not directly shown here, but it is involved in the `api.sendCommand(command)` method.
// It parses the command string and if the command is valid, it creates a Command subclass instance
// using reflection as indicated in the design plan and executes it.