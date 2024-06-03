package slogo.view;

import java.io.IOException;
import java.util.List;
import slogo.model.parser.exceptions.InvalidCommandException;

/**
 * The SaveProgram class is responsible for saving the program's commands and user preferences.
 */
public class SaveProgram {

  private final Gui myGui;
  private final TurtlePane myTurtlePane;

  /**
   * Constructs a new SaveProgram object with the specified TurtlePane and Gui.
   *
   * @param turtlePane The TurtlePane object.
   * @param gui        The Gui object.
   */
  public SaveProgram(TurtlePane turtlePane, Gui gui) {
    myTurtlePane = turtlePane;
    myGui = gui;
  }

  /**
   * Saves the program's commands and user preferences.
   */
  protected void saveProgram() {
    List<String> commands = myGui.getCommandHistory().getCommandList();
    UserPreferences preferences;
    preferences = collectCurrentPreferences();

    try {
      PreferencesUtil.saveUserPreferences(preferences, myGui.getPreferencesFilePath());
      // showMessage(Alert.AlertType.INFORMATION, "Preferences saved successfully.");
    } catch (IOException e) {
      throw new GuiException("Failed to save preferences: " + e.getMessage());
    }
    ProgramSaver.saveProgram(commands, myGui.getPrimaryStage());
  }

  /**
   * Collects the current user preferences.
   *
   * @return The collected user preferences.
   */
  protected UserPreferences collectCurrentPreferences() {
    UserPreferences preferences = new UserPreferences();

    // Assuming controller and turtlePane are accessible and hold the required information
    try {
      preferences.setCommandLanguage(myGui.getLanguage());
    } catch (InvalidCommandException e) {
      throw new GuiException("Invalid Command Encountered While Setting Command Language: " +
          e.getMessage());
    }

    // Assuming you have a method in turtlePane to get the current background color as a hex string
    preferences.setBackgroundColor(myTurtlePane.getBackgroundColor());

    preferences.setPenColor(myTurtlePane.getPenColor());

    preferences.setTheme(myGui.getColorTheme()); // Assuming colorTheme holds the current theme

    // Default indexed values, turtle images, and starting number of
    // turtles would be collected similarly
    preferences.setStartingNumberOfTurtles(1);

    return preferences;
  }
}
