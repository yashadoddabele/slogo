package slogo.model.commands;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Make Command Implementation.
 */
public class MakeVariable implements Command {

  private final Turtle turtle;

  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private String name;
  private ParameterStackElement value;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public MakeVariable(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    name = parameters.pop().getStringValue();
    value = parameters.pop();
  }

  @Override
  public double execute() {
    return variableController.setVariable(name, value);
  }

  @Override
  public int numV() {
    return 2;
  }

}
