package slogo.model.parser.nodeparser;

/**
 * This node type stores a string value representing the customized variable for initialization. The
 * value is modified to replace the '%' character with ':' upon instantiation.
 */
public class CustomizeVariableNode implements Node {

  private final String value;

  /**
   * Constructs a CustomizeVariableNode with the provided value. The '%' character in the value is
   * replaced with ':'.
   *
   * @param value the string value representing the customized variable
   */
  public CustomizeVariableNode(String value) {
    this.value = value.replaceFirst("%", ":");
  }

  /**
   * Return type.
   *
   * @return the integer value 4 representing the type of this node
   */
  @Override
  public int type() {
    return 4;
  }

  /**
   * Return value.
   *
   * @return the string value representing the customized variable
   */
  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "CustomizeVariableNode{" +
        "value='" + value + '\'' +
        '}';
  }
}
