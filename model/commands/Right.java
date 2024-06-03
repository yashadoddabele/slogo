package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Right Command Implementation.
 */
public class Right implements Command {

  private final Turtle turtle;
  private VariableController variableController;
  private CommandRegistry commandRegistry;
  private ParameterStackElement angle;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public Right(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  public Right(Turtle turtle) {
    this.turtle = turtle;
  }

  @Override
  public double execute() {
    System.out.println("turtle turned right  " + executeSubCommandList(angle));
    return turtle.rotateRight(executeSubCommandList(angle));
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