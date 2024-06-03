package slogo.model.parser.nodeparser;

import java.util.Iterator;
import java.util.List;

/**
 * Represents a node for a group of nodes in a command parsing context. This node type stores a list
 * of child nodes.
 */
public class GroupNode implements Node {

  private final List<Node> nodeList;

  /**
   * Constructs a GroupNode with the provided list of child nodes.
   *
   * @param nodeList the list of child nodes to be stored in the group node
   */
  public GroupNode(List<Node> nodeList) {
    this.nodeList = nodeList;
  }

  /**
   * Return type.
   *
   * @return the integer value 3 representing the type of this node
   */
  @Override
  public int type() {
    return 3;
  }

  /**
   * Return value.
   *
   * @return the list of child nodes stored in the group node
   */
  @Override
  public List<Node> getValue() {
    return nodeList;
  }

  /**
   * Build the iterator.
   *
   * @return an iterator over the child nodes
   */
  public Iterator<Node> iterator() {
    return new GroupNodeIterator(nodeList);
  }

  @Override
  public String toString() {
    return "GroupNode{" +
        "value=" + nodeList +
        '}';
  }
}
