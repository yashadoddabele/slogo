package slogo.view;

import java.util.Map;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import slogo.model.parser.stackparser.ParameterStackElement;

/**
 * The VariableTreeViewApp class represents an application for displaying variables in a tree view.
 */
public class VariableTreeViewApp {

  private final Map<String, ParameterStackElement> myVariableMap;

  /**
   * Constructs a new VariableTreeViewApp with the specified variable map.
   *
   * @param variableMap the map containing variables and their values
   */
  public VariableTreeViewApp(Map<String, ParameterStackElement> variableMap) {
    myVariableMap = variableMap;
  }

  /**
   * Returns a BorderPane containing a tree view of the variables.
   *
   * @return a BorderPane containing a tree view of the variables
   */
  public BorderPane returnVariablePane() {
    // Simulated list of executed commands

    // Build the command tree
    TreeItem<RecordNode> rootItem = new TreeItem<>(new RecordNode("Variables"));
    for (String variable : myVariableMap.keySet()) {
      rootItem.getChildren().add(buildVariableNode(
          variable + " " + myVariableMap.get(variable).getDoubleValue().toString()));
    }

    return GuiUtil.getBorderPaneFromTreeItem(rootItem);
  }

  /**
   * Builds a tree node for the given variable command.
   *
   * @param command the variable command to be displayed in the tree node
   * @return the tree item representing the variable command
   */
  private TreeItem<RecordNode> buildVariableNode(String command) {
    RecordNode node = new RecordNode(command);
    TreeItem<RecordNode> item = new TreeItem<>(node);
    item.getChildren().add(new TreeItem<>(new RecordNode("placeholder")));

    return item;
  }
}
