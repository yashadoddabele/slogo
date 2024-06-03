package slogo.model.commands.utils;

import java.util.HashMap;
import java.util.Map;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * VariableController class manages the storage and retrieval of variables. It associates variable
 * names with their corresponding values.
 */
public class VariableController {

  // Map to store variables, where keys are variable names and values are parameter stack elements
  private final Map<String, ParameterStackElement> variables = new HashMap<>();

  /**
   * Sets the value of a variable.
   *
   * @param name  The name of the variable.
   * @param value The value to be assigned to the variable.
   * @return 1 indicating success.
   */
  public double setVariable(String name, ParameterStackElement value) {
    variables.put(name, value);
    return 1;
  }

  /**
   * Retrieves the value of a variable.
   *
   * @param name The name of the variable to retrieve.
   * @return The value of the variable, or a new ParameterStackElement with value 0.0 if the
   * variable is not found.
   */
  public ParameterStackElement getVariable(String name) {
    return variables.getOrDefault(name, new ParameterStackElement(0.0));
  }

  /**
   * Retrieves the entire map of variables.
   *
   * @return The map containing all registered variables.
   */
  public Map<String, ParameterStackElement> getVariables() {
    return variables;
  }
}
