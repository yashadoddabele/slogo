package slogo.view;


import java.util.List;
import java.util.Map;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import slogo.model.Command;

/**
 * Utility class for creating a command tree view based on a map of commands.
 */
public class CommandTreeViewApp {

  private final Map<String, List<Command>> myCommandMap;

  /**
   * Constructs a CommandTreeViewApp with the specified command map.
   *
   * @param commandMap The map containing commands categorized by command names.
   */
  public CommandTreeViewApp(Map<String, List<Command>> commandMap) {
    myCommandMap = commandMap;
  }

  /**
   * Returns a BorderPane containing the command tree view.
   *
   * @return The BorderPane containing the command tree view.
   */
  public BorderPane returnCommandPane() {
    // Simulated list of executed commands

    // Build the command tree
    TreeItem<RecordNode> rootItem = new TreeItem<>(new RecordNode("Commands"));
    for (String command : myCommandMap.keySet()) {
      rootItem.getChildren().add(buildCommandNode(command));
    }

    TreeView<RecordNode> treeView = new TreeView<>(rootItem);
    treeView.setShowRoot(false); // Hide the root node
    treeView.setCellFactory(param -> new RecordTreeCell());

    BorderPane root = new BorderPane();
    root.setCenter(treeView);

    return root;
  }

  /**
   * Builds a tree item for the specified command.
   *
   * @param command The command for which to build the tree item.
   * @return The tree item representing the specified command.
   */
  private TreeItem<RecordNode> buildCommandNode(String command) {
    RecordNode node = new RecordNode(command);
    TreeItem<RecordNode> item = new TreeItem<>(node);

    List<Command> subcommandList = myCommandMap.get(command);
    if (subcommandList != null) {
      for (Command subCommand : myCommandMap.get(command)) {
        item.getChildren().add(new TreeItem<>(new RecordNode(subCommand.toString())));
      }
    }

    return item;
  }

}
