package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * IfElse Command Implementation.
 */
public class IfElse implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement expr;
  private ParameterStackElement trueCommands;
  private ParameterStackElement falseCommands;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public IfElse(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    double lastValue;
    if (executeSubCommandList(expr) != 0) {
      lastValue = executeSubCommandList(trueCommands);
    } else {
      lastValue = executeSubCommandList(falseCommands);
    }
    return lastValue;
  }

  @Override
  public int numV() {
    return 3;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    expr = parameters.pop();
    trueCommands = parameters.pop();
    falseCommands = parameters.pop();
  }
}
