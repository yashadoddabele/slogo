// use case:
// The user sets the turtle's pen down
// using the UI so when the turtle moves, it leaves a trail.

import java.awt.Color; // Import the Color class for setting pen color

public class PenDownUseCase {

  private SLogoApi api; // The SLogo API to interact with the model.

  public PenDownUseCase(SLogoApi api) {
    this.api = api;
  }

  //Sets the pen's color for the turtle.
  public void penDown() {
    // Set the pen down through the API
    api.penDown();

    // Assuming for now there's a method to update the UI with the pen set down
    // and the turtle leaving a trail
    api.penDownInUI();
  }
}
