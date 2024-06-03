package slogo.model.parser.nodeparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is responsible for parsing a command string into a list of nodes, where each node
 * represents a component of the command.
 */
public class NodeParser {

  /**
   * Parses the provided command string into a list of nodes.
   *
   * @param command the command string to parse
   * @return a list of nodes representing the components of the command
   */
  public List<Node> parseCommand(String command) {
    List<Node> nodeList = new ArrayList<>();
    String[] tokens = command.split("\\s+");
    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      if (isNumeric(token)) {
        nodeList.add(new DoubleNode(Double.parseDouble(token)));
      } else if (token.startsWith(":")) {
        nodeList.add(new VariableNode(token));
      } else if (token.startsWith("%")) {
        nodeList.add(new CustomizeVariableNode(token));
      } else if (token.startsWith("^")) {
        nodeList.add(new CustomizeCommandNode(token));
      } else if (token.equals("[")) {
        nodeList.add(parseList(Arrays.copyOfRange(tokens, i, tokens.length)));
        int bracketCount = 1;
        while (bracketCount > 0) {
          i++;
          if (tokens[i].equals("[")) {
            bracketCount++;
          } else if (tokens[i].equals("]")) {
            bracketCount--;
          }
        }
      } else {
        nodeList.add(new StrNode(token));
      }
    }

    return nodeList;
  }

  /**
   * Parses a list of tokens enclosed in square brackets into a GroupNode.
   *
   * @param tokens the tokens representing the contents of the list
   * @return a GroupNode containing the parsed contents of the list
   */
  private GroupNode parseList(String[] tokens) {
    List<Node> listContents = new ArrayList<>();
    int bracketCount = 0;
    StringBuilder listContentBuilder = new StringBuilder();

    for (String token : tokens) {
      if (token.equals("[")) {
        bracketCount++;
        if (bracketCount > 1) {
          listContentBuilder.append(token).append(" ");
        }
      } else if (token.equals("]")) {
        bracketCount--;
        if (bracketCount == 0) {
          listContents.addAll(parseCommand(listContentBuilder.toString()));
          break;
        } else {
          listContentBuilder.append(token).append(" ");
        }
      } else {
        listContentBuilder.append(token).append(" ");
      }
    }

    return new GroupNode(listContents);
  }

  /**
   * Checks if a string represents a numeric value.
   *
   * @param str the string to check
   * @return true if the string represents a numeric value, false otherwise
   */
  private boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

}
