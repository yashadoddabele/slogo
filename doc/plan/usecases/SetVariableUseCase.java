// use case 3:
// The user sets a variable's value and
// sees it updated in the UI's Variable view.

public class SetVariableUseCase {

  private SLogoApi api; // The SLogo API to interact with the model.

  public SetVariableUseCase(SLogoApi api) {
    this.api = api;
  }

  // Sets a variable's value and updates the UI.

  public void setVariable(String variableName, double value) {
    // Set the variable value through the API
    api.setVariable(variableName, value);

    // Update the variable view through the API
    api.updateVariableView(variableName, value);
  }
}