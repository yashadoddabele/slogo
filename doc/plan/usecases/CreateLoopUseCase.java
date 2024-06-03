// use case
// user creates a loop of commands

public class CreateLoopUseCase {

  private SLogoApi api; // The SLogo API to interact with the model

  public CreateLoopUseCase(SLogoApi api) {
    this.api = api;
  }

  public void createLoop(String loopCommand) {
    // User enters a loop command (e.g., "repeat 4 [ fd 100 rt 90 ]") into the command prompt
    boolean success = api.sendCommand(loopCommand);
    // Depending on the success, the GUI may show the turtle moving in a square or an error message
  }
}
