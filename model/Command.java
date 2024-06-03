package slogo.model;

import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * Interface representing a command to be executed.
 */

public interface Command {

  /**
   * Execute the command.
   *
   * @return the result of executing the command
   */
  double execute();

  /**
   * Record the number of parameters.
   *
   * @return the number of parameters required
   */
  int numV();

  /**
   * Set parameters after initialization.
   *
   * @param parameters the parameters to set for the command
   */
  void setParameters(ParameterStack parameters);

  /**
   * Executes a sublist of commands within the parameter stack element and returns the result. If
   * the element contains a double value, it is returned directly. Otherwise, the commands within
   * the list are executed, and their cumulative result is returned.
   *
   * @param element the parameter stack element containing either a double value or a list of
   *                commands
   * @return the result of executing the sublist of commands
   */
  default double executeSubCommandList(ParameterStackElement element) {
    if (element.getDoubleValue() != null) {
      return element.getDoubleValue();
    } else {
      double res = 0;
      for (Command command : element.getCommandListValue()) {
        res = command.execute();
      }
      return res;
    }

  }
}
