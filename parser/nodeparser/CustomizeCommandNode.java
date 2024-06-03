package slogo.model.parser.nodeparser;

/**
 * Represents a node for a customized command in a command parsing context. This node type stores a
 * string value representing the customized command.
 */
public class CustomizeCommandNode implements Node {

  private final String value;

  /**
   * Constructs a CustomizeCommandNode with the provided value.
   *
   * @param value the string value representing the customized command
   */
  public CustomizeCommandNode(String value) {
    this.value = value;
  }

  /**
   * Return type.
   *
   * @return the integer value 5 representing the type of this node
   */
  @Override
  public int type() {
    return 5;
  }

  /**
   * Return the value.
   *
   * @return the string value representing the customized command
   */
  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "CustomizeCommandNode{" +
        "value='" + value + '\'' +
        '}';
  }
}

