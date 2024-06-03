package slogo.model.parser.nodeparser;

/**
 * Represents a node for a string value in a command parsing context.
 */
public class StrNode implements Node {

  private final String value;

  /**
   * Constructs a StrNode with the provided string value.
   *
   * @param value the string value to be stored in the node
   */
  public StrNode(String value) {
    this.value = value;
  }

  /**
   * Return type.
   *
   * @return the integer value 1 representing the type of this node
   */
  @Override
  public int type() {
    return 1;
  }

  /**
   * Return value.
   *
   * @return the string value stored in the node
   */
  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "StrNode{" +
        "value='" + value + '\'' +
        '}';
  }
}
