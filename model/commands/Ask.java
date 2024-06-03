package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.TurtleBale;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Ask Command Implementation.
 */
public class Ask implements Command {

  private final TurtleBale turtles;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement id;
  private ParameterStackElement commands;

  /**
   * Construct command without parameters.
   *
   * @param turtles            the turtle objects to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public Ask(TurtleBale turtles, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtles = turtles;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    List<Integer> previousStates = turtles.getActiveIds();
    Turtle t = turtles.getTurtlebyId((int) executeSubCommandList(id));
    t.setActivity(true);
    List<Integer> newStates = new ArrayList<>(List.of((int) executeSubCommandList(id)));
    turtles.setAsActive(newStates);
    double lastVal = executeSubCommandList(commands);
    turtles.setAsActive(previousStates);
    return lastVal;
  }

  @Override
  public int numV() {
    return 2;
  }

  @Override
  public void setParameters(ParameterStack parameters) {
    id = parameters.pop();
    commands = parameters.pop();
  }


}
