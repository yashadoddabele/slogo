package slogo.model.commands;

import slogo.model.Command;
import slogo.model.TurtleBale;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;

/**
 * Turtles Command Implementation.
 */
public class Turtles implements Command {

  private final TurtleBale turtles;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;

  /**
   * Construct command without parameters.
   *
   * @param turtles            the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public Turtles(TurtleBale turtles, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtles = turtles;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    System.out.println(turtles.getNumTurtles());
    return turtles.getNumTurtles();
  }

  @Override
  public int numV() {
    return 0;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
  }

}
