package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Left Command Implementation.
 */
public class Left implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement angle;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public Left(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    return turtle.rotateRight(-executeSubCommandList(angle));
  }

  @Override
  public int numV() {
    return 1;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    angle = parameters.pop();
  }
}