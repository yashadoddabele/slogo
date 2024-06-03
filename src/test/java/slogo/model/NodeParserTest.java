package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.parser.nodeparser.CustomizeCommandNode;
import slogo.model.parser.nodeparser.CustomizeVariableNode;
import slogo.model.parser.nodeparser.DoubleNode;
import slogo.model.parser.nodeparser.GroupNode;
import slogo.model.parser.nodeparser.GroupNodeIterator;
import slogo.model.parser.nodeparser.Node;
import slogo.model.parser.nodeparser.NodeParser;
import slogo.model.parser.nodeparser.StrNode;
import slogo.model.parser.nodeparser.VariableNode;
import util.DukeApplicationTest;

public class NodeParserTest extends DukeApplicationTest {

  private NodeParser parser;
  private final String command1 = "ifelse greater :position 100 [ if 1 [ fd 100 ] ] [ fd 100 ]";
  private List<Node> nodeList;

  @BeforeEach
  void initialization() {
    parser = new NodeParser();
    nodeList = new ArrayList<>();
    nodeList.add(new StrNode("fd"));
    nodeList.add(new DoubleNode(100));
  }

  @Test
  public void testParseCommand() {
    List<Node> nodeList = parser.parseCommand(command1);
    assertNotNull(nodeList);
    assertEquals(6, nodeList.size());
  }

  @Test
  public void testDoubleNode() {
    double value = 10;
    DoubleNode doubleNode = new DoubleNode(value);
    assertEquals(value, doubleNode.getValue());
    assertEquals(0, doubleNode.type());
  }

  @Test
  public void testStrNode() {
    String value = "fd";
    StrNode strNode = new StrNode(value);
    assertEquals(value, strNode.getValue());
    assertEquals(1, strNode.type());
  }

  @Test
  public void testVariableNode() {
    String value = ":num";
    VariableNode variableNode = new VariableNode(value);
    assertEquals(value, variableNode.getValue());
    assertEquals(2, variableNode.type());
  }

  @Test
  public void testGroupNode() {
    GroupNode groupNode = new GroupNode(nodeList);
    assertEquals(2, groupNode.getValue().size());
  }

  @Test
  public void testCustomizeCommandNode() {
    String value = "^customCommand";
    CustomizeCommandNode customizeCommandNode = new CustomizeCommandNode(value);
    assertEquals(value, customizeCommandNode.getValue());
    assertEquals(5, customizeCommandNode.type());
  }

  @Test
  public void testCustomizeVariableNode() {
    String value = "%customVariable";
    CustomizeVariableNode customizeVariableNode = new CustomizeVariableNode(value);
    assertNotNull(customizeVariableNode);
    assertEquals(":customVariable", customizeVariableNode.getValue());
    assertEquals(4, customizeVariableNode.type());
  }

  @Test
  public void testGroupNodeIterator() {
    GroupNodeIterator groupNodeIterator = new GroupNodeIterator(nodeList);
    assertTrue(groupNodeIterator.hasNext());
    Node node1 = groupNodeIterator.next();
    assertEquals("fd", node1.getValue());
    assertTrue(groupNodeIterator.hasNext());
    Node node2 = groupNodeIterator.next();
    assertEquals(100.0, node2.getValue());
    assertFalse(groupNodeIterator.hasNext());
  }
}
