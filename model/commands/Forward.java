package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Forward Command Implementation.
 */
public class Forward implements Command {

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
  public Forward(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  //For multiple turtles
//  public double execute() {
//    double res = 0;
//    for (int id: turtles.getActiveIds()) {
//      res = turtles.getTurtlebyId(id).moveForward(executeSubCommandList(distance));
//      System.out.println("turtle " + id + " moved " + executeSubCommandList(distance));
//    }
//    return res;
//  }
  public double execute() {
    return turtle.moveForward(executeSubCommandList(distance));
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