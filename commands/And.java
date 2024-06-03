package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * And Command Implementation.
 */
public class And implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;

  private ParameterStackElement expr1;
  private ParameterStackElement expr2;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public And(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    return (executeSubCommandList(expr1) != 0 && executeSubCommandList(expr2) != 0) ? 1 : 0;
  }

  @Override
  public int numV() {
    return 2;
  }

  public void setParameters(ParameterStack parameters) {
    expr1 = parameters.pop();
    expr2 = parameters.pop();
  }
}
