// use case
// Changing Turtle image by user

public class ChangeTurtleImageUseCase {

  private GuiSettings guiSettings; // Manages settings of the GUI

  public ChangeTurtleImageUseCase(GuiSettings guiSettings) {
    this.guiSettings = guiSettings;
  }

  public void changeTurtleImage(String imagePath) {
    // User selects a new image for the turtle from the local filesystem
    guiSettings.setTurtleImage(imagePath);
    // The GUI updates the turtle's appearance in the Turtle Graphics Window
  }
}
