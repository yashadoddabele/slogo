package slogo.model.parser.nodeparser;

/**
 * Represents a node for a double value in a command parsing context.
 */
public class DoubleNode implements Node {

  private final double value;

  /**
   * Constructs a DoubleNode with the provided double value.
   *
   * @param value the double value to be stored in the node
   */
  public DoubleNode(double value) {
    this.value = value;
  }

  /**
   * Return type.
   *
   * @return the integer value 0 representing the type of this node
   */
  @Override
  public int type() {
    return 0;
  }

  /**
   * Return value.
   *
   * @return the double value stored in the node
   */
  public Double getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "DoubleNode{" +
        "value=" + value +
        '}';
  }
}
