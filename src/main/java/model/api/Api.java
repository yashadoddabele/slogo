package slogo.model.api;

import java.util.List;
import slogo.model.Command;
import slogo.model.CommandHistory;
import slogo.model.CommandParser;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.exceptions.InvalidCommandException;

/**
 * Api class provides an interface for interacting with the Turtle Graphics system.
 */
public class Api {

  private final CommandParser parser; // Command parser instance
  private final Turtle turtle; // Turtle instance

  /**
   * Constructs a new Api object.
   *
   * @throws InvalidCommandException If an error occurs during initialization.
   */
  public Api() throws InvalidCommandException {
    // Initialize turtle with default settings (position at origin,
    // facing upwards, pen down, and shown)
    this.turtle = new Turtle(0, 0, 0, true, true, 1);
    turtle.setActivity(true);
    // Initialize command parser with the turtle
    this.parser = new CommandParser(turtle);
  }

  /**
   * Parses and executes the given commands.
   *
   * @param commands The string containing the commands to be executed.
   * @return The list of commands parsed from the input string.
   * @throws InvalidCommandException If an error occurs during command parsing or execution.
   */
  public List<Command> sendCommand(String commands) throws InvalidCommandException {
    return parser.parseCommands(commands);
  }

  /**
   * Updates the language used for parsing commands.
   *
   * @param language The new language identifier.
   * @throws InvalidCommandException If an error occurs while updating the language.
   */
  public void updateLanguage(String language) throws InvalidCommandException {
    parser.setLanguage(language);
  }

  /**
   * Retrieves the current language used for parsing commands.
   *
   * @return The current language identifier.
   */
  public String getLanguage() {
    return parser.getLanguage();
  }

  /**
   * Retrieves the current position and orientation of the turtle.
   *
   * @return An array containing the x-coordinate, y-coordinate, and orientation angle of the
   * turtle.
   */
  @Deprecated
  public double[] getTurtlePositions() {
    double[] positions = new double[3];
    positions[0] = turtle.getXpos();
    positions[1] = turtle.getYpos();
    positions[2] = turtle.getAngle();
    return positions;
  }

  /**
   * Retrieves the command history.
   *
   * @return The command history object.
   */
  public CommandHistory getCommandHistory() {
    return parser.getCommandHistory();
  }

  /**
   * Retrieves the variable controller.
   *
   * @return The variable controller object.
   */
  public VariableController getVariableController() {
    return parser.getVariableController();
  }

  /**
   * Retrieves the turtle instance.
   *
   * @return The turtle object.
   */
  public Turtle getTurtle() {
    return turtle;
  }

  /**
   * Retrieves the command registry.
   *
   * @return The command registry object.
   */
  public CommandRegistry getCommandRegistry() {
    return parser.getCommandRegistry();
  }

  /**
   * Checks if the turtle is currently shown.
   *
   * @return True if the turtle is shown, false otherwise.
   */
  public boolean turtleIsShowing() {
    return turtle.isShow();
  }

  /**
   * Checks if the pen is currently down.
   *
   * @return True if the pen is down, false otherwise.
   */
  public boolean penIsDown() {
    return turtle.isPenDown();
  }
}
