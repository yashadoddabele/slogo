package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Backward Command Implementation.
 */
public class Backward implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement distance;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public Backward(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    if (distance != null) {
      return turtle.moveBackward(executeSubCommandList(distance));
    } else {
      return 0;
    }
  }

  @Override
  public int numV() {
    return 1;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    distance = parameters.pop();
  }
}