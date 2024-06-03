package slogo.model.parser.nodeparser;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator implementation for iterating over the nodes stored in a GroupNode.
 */
public class GroupNodeIterator implements Iterator<Node> {

  private final List<Node> nodeList; // The list of nodes to iterate over
  private int currentIndex; // The current index of the iterator

  /**
   * Constructs a GroupNodeIterator with the provided list of nodes.
   *
   * @param nodeList the list of nodes to iterate over
   */
  public GroupNodeIterator(List<Node> nodeList) {
    this.nodeList = nodeList;
    this.currentIndex = 0;
  }

  /**
   * Check whether there are more elements.
   *
   * @return true if there are more elements, false otherwise
   */
  @Override
  public boolean hasNext() {
    return currentIndex < nodeList.size();
  }

  /**
   * Return the next node.
   *
   * @return the next node in the iteration
   * @throws IndexOutOfBoundsException if there are no more elements in the list
   */
  @Override
  public Node next() {
    if (!hasNext()) {
      throw new IndexOutOfBoundsException("No more elements in the list");
    }
    Node nextNode = nodeList.get(currentIndex);
    currentIndex++;
    return nextNode;
  }
}
