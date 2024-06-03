package slogo.model.parser.nodeparser;

/**
 * Represents a node for a variable in a command parsing context.
 */

public class VariableNode implements Node {

  private final String value;

  /**
   * Constructs a VariableNode with the provided string value.
   *
   * @param value the string value representing the variable to be stored in the node
   */
  public VariableNode(String value) {
    this.value = value;
  }

  /**
   * Return type.
   *
   * @return the integer value 2 representing the type of this node
   */
  @Override
  public int type() {
    return 2;
  }

  /**
   * Return value.
   *
   * @return the string value representing the variable stored in the node
   */
  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "VariableNode{" +
        "value='" + value + '\'' +
        '}';
  }
}
