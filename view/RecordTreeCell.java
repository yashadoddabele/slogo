package slogo.view;

import javafx.scene.control.TreeCell;

/**
 * RecordTreeCell represents a custom tree cell for displaying command records in a TreeView. It
 * overrides the updateItem method to customize the appearance of each cell.
 */
public class RecordTreeCell extends TreeCell<RecordNode> {

  /**
   * Updates the content of the tree cell based on the specified item.
   *
   * @param item  The record node associated with this cell.
   * @param empty A boolean indicating whether the cell is empty.
   */
  @Override
  protected void updateItem(RecordNode item, boolean empty) {
    super.updateItem(item, empty);
    if (empty || item == null) {
      setText(null);
    } else {
      setText(item.getCommand());
      // Handle expand/collapse if necessary
      setGraphic(null); // Set graphic to null to use default tree cell behavior
    }
  }
}

