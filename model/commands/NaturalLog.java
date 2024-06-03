package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * NaturalLog Command Implementation.
 */
public class NaturalLog implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement expr;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public NaturalLog(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    if (executeSubCommandList(expr) <= 0) {
      throw new IllegalArgumentException("Negative number alert.");
    }
    return Math.log(executeSubCommandList(expr));
  }

  @Override
  public int numV() {
    return 1;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    expr = parameters.pop();
  }
}



