package slogo.controller;

import java.util.List;
import javafx.scene.control.Alert.AlertType;
import slogo.model.Command;
import slogo.model.CommandHistory;
import slogo.model.CommandParser;
import slogo.model.api.Api;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.exceptions.InvalidCommandException;
import slogo.view.Gui;

/**
 * Controller class serves as the intermediary between the GUI and the backend API.
 */
public class Controller {

  // Connection to the backend
  private Api api;
  private final CommandParser commandParser;

  private final Gui gui;

  /**
   * Constructs a new Controller with the specified GUI.
   *
   * @param gui The graphical user interface.
   * @throws InvalidCommandException If an error occurs during initialization.
   */

  public Controller(Gui gui) throws InvalidCommandException {
//    observeTurtleChanges();
    this.gui = gui;
    this.api = new Api();
    this.commandParser = new CommandParser(api.getTurtle()); // Added this line
    observeTurtleChanges();
  }

  /**
   * Observes changes in the turtle state and updates the GUI accordingly.
   */
  public void observeTurtleChanges() {
    // Check if api is not null and the turtle is not null before adding observer
    if (!api.equals(null)) {
      api.getTurtle().addObserver(gui);
    }
  }

  /**
   * Updates the turtle view in the GUI based on the current turtle position and orientation.
   */
  @Deprecated
  public void updateTurtleView() {
    double[] positions = api.getTurtlePositions();
    gui.updateTurtle(positions[0], positions[1], positions[2], api.getTurtle().getId());
  }

  /**
   * Parses and executes the specified command.
   *
   * @param command The command to be executed.
   * @return The list of commands parsed from the input string.
   * @throws InvalidCommandException If an error occurs during command parsing or execution.
   */
  public List<Command> pushCommand(String command) throws InvalidCommandException {
    if (!command.isEmpty()) {
      return api.sendCommand(command);
    }
    return null;
  }

  /**
   * Retrieves the current language used for parsing commands.
   *
   * @return The current language identifier.
   * @throws InvalidCommandException If an error occurs while retrieving the language.
   */
  public String getLanguage() throws InvalidCommandException {
    return api.getLanguage();
  }

  /**
   * Sets the language used for parsing commands.
   *
   * @param language The new language identifier.
   * @throws InvalidCommandException If an error occurs while updating the language.
   */
  public void setLanguage(String language) throws InvalidCommandException {
    api.updateLanguage(language);
  }

  /**
   * Sends an error message to the GUI for display.
   *
   * @param message The error message to be displayed.
   */
  @Deprecated
  public void sendErrorToGui(String message) {
    Gui.showMessage(AlertType.ERROR, message);
  }

  /**
   * Sets the backend API.
   *
   * @param api The new backend API.
   */
  public void setApi(Api api) {
    this.api = api;
  }

  /**
   * Retrieves the command history.
   *
   * @return The command history object.
   */
  public CommandHistory getCommandHistory() {
    return api.getCommandHistory();
  }

  /**
   * Retrieves the variable controller.
   *
   * @return The variable controller object.
   */
  public VariableController getVariableController() {
    return api.getVariableController();
  }

  /**
   * Retrieves the command registry.
   *
   * @return The command registry object.
   */
  public CommandRegistry getCommandRegistry() {
    return api.getCommandRegistry();
  }


  /**
   * Checks if the turtle is currently shown.
   *
   * @return True if the turtle is shown, false otherwise.
   */
  public boolean turtleIsShowing() {
    return api.turtleIsShowing();
  }

  /**
   * Checks if the pen is currently down.
   *
   * @return True if the pen is down, false otherwise.
   */
  public boolean penIsDown() {
    return api.penIsDown();
  }

//  public boolean isActiveTurtle(int id) {
//    List<Integer> activeTurtles = api.getTurtle().getActiveIds();
//    return activeTurtles.contains(id);
//  }
}

