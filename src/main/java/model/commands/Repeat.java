package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Repeat Command Implementation.
 */
public class Repeat implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement num;
  private ParameterStackElement commands;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public Repeat(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    double lastValue = 0;
    for (int i = 1; i <= executeSubCommandList(num); i++) {
      lastValue = executeSubCommandList(commands);
    }
    return lastValue;
  }

  @Override
  public int numV() {
    return 2;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    num = parameters.pop();
    commands = parameters.pop();
  }
}
