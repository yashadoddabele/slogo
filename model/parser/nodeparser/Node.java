package slogo.model.parser.nodeparser;

/**
 * This interface represents a generic node in a command.
 *
 * @param <T> the type of the value stored in the node
 */
public interface Node<T> {

  /**
   * Returns the type of the node.
   *
   * @return an integer representing the type of the node: 0: double, 1: string, 2: variable, 3:
   * list, 4: customize name, 5: customize command
   */
  int type();

  /**
   * Returns the value stored in the node.
   *
   * @return the value stored in the node
   */
  T getValue();
}
