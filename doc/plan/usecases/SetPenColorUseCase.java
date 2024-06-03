// use case 4:
// The user sets the pen's color
// using the UI so subsequent lines drawn when the turtle moves use that color.

import java.awt.Color; // Import the Color class for setting pen color

public class SetPenColorUseCase {

  private SLogoApi api; // The SLogo API to interact with the model.

  public SetPenColorUseCase(SLogoApi api) {
    this.api = api;
  }

  //Sets the pen's color for the turtle.
  public void setPenColor(Color color) {
    // Set the pen color through the API
    api.setPenColor(color);

    // Assuming for now there's a method to update the UI with the new pen color
    api.updatePenColorInUI(color);
  }
}
