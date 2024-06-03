package slogo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * The GuiUtil class provides utility methods for GUI components.
 */
public class GuiUtil {

  private GuiUtil() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Combines a VBox containing buttons with a pane and aligns them in a StackPane.
   *
   * @param vbox the VBox containing buttons
   * @param pane the pane to be combined with the VBox
   * @return a StackPane containing the combined VBox and pane
   */
  public static StackPane putAndAlignButtonsAboveWindows(VBox vbox, Pane pane) {
    // Add vbox and pane to combinedWindows
    StackPane combinedWindows = new StackPane();
    combinedWindows.getChildren().addAll(vbox, pane);

    // Set pane to be centered in combinedWindows
    StackPane.setAlignment(pane, Pos.CENTER);

    // Set margin for pane to position it above vbox
    StackPane.setMargin(pane, new Insets(30, 0, 0, 0));
    return combinedWindows;
  }

  /**
   * Calculates the distance between two points.
   *
   * @param endX   the x-coordinate of the end point
   * @param endY   the y-coordinate of the end point
   * @param startX the x-coordinate of the start point
   * @param startY the y-coordinate of the start point
   * @return the distance between the two points
   */
  public static double returnDistance(double endX, double endY, double startX, double startY) {
    return Math.sqrt(Math.pow((endX - startX), 2) + Math.pow((endY - startY), 2));
  }

  /**
   * Creates a BorderPane containing a TreeView from the specified root item.
   *
   * @param rootItem the root item of the tree
   * @return a BorderPane containing the TreeView
   */
  public static BorderPane getBorderPaneFromTreeItem(TreeItem<RecordNode> rootItem) {
    TreeView<RecordNode> treeView = new TreeView<>(rootItem);
    treeView.setShowRoot(false); // Hide the root node
    treeView.setCellFactory(param -> new RecordTreeCell());

    BorderPane root = new BorderPane();
    root.setCenter(treeView);

    return root;
  }

  /**
   * Creates a VBox with the specified style.
   *
   * @param style the CSS style for the VBox
   * @return a VBox with the specified style
   */
  public static VBox createVboxWithStyle(String style) {
    VBox vBox = new VBox(10);
    vBox.setStyle(style);
    return vBox;
  }

  /**
   * Creates a Label with the specified text and style.
   *
   * @param text  the text to be displayed on the Label
   * @param style the CSS style for the Label
   * @return a Label with the specified text and style
   */
  public static Label createLabelWithStyle(String text, String style) {
    Label label = new Label(text);
    label.setStyle(style);
    return label;
  }

  /**
   * Creates a Button with the specified text and action.
   *
   * @param text    the text to be displayed on the Button
   * @param handler the action to be performed when the Button is clicked
   * @return a Button with the specified text and action
   */
  public static Button createButton(String text, EventHandler<ActionEvent> handler) {
    Button button = new Button(text);
    button.setOnAction(handler);
    return button;
  }
}
