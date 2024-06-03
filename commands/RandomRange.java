package slogo.model.commands;

import java.util.Random;
import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * RandomRange Command Implementation.
 */
public class RandomRange implements Command {

  private final Turtle turtle;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStackElement min;
  private ParameterStackElement max;

  /**
   * Construct command without parameters.
   *
   * @param turtle             the turtle object to execute commands on
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   */
  public RandomRange(Turtle turtle, VariableController variableController,
      CommandRegistry commandRegistry) {
    this.turtle = turtle;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
  }

  @Override
  public double execute() {
    double minimum = executeSubCommandList(min);
    double maximum = executeSubCommandList(max);
    if (minimum > maximum) {
      throw new IllegalArgumentException("Min should be less than Max.");
    }
    Random random = new java.util.Random();
    return minimum + (maximum - minimum + 1) * random.nextDouble();
  }

  @Override
  public int numV() {
    return 2;
  }

  public void setParameters(ParameterStack parameters) {
    min = parameters.pop();
    max = parameters.pop();
  }
}

