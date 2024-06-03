package slogo.model.commands;

import java.util.List;
import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;

/**
 * This command represents the user defined commands.
 */
public class MakeUserInstruction implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private String name;
  private List<Command> value;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public MakeUserInstruction(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  public void setParameters(ParameterStack parameters) {
    name = parameters.pop().getStringValue();
    value = parameters.pop().getCommandListValue();
  }

  public double execute() {
    return commandRegistry.addCommand(name, value);
  }

  public int numV() {
    return 2;
  }

}
