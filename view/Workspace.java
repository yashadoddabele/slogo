package slogo.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Workspace class represents the main workspace of the application. It contains various components
 * such as command prompt, turtle graphics area, command history, and variable view.
 */
public class Workspace {

  private final BorderPane content;

  public Workspace() {
    this.content = new BorderPane();
    initializeComponents();
  }

  private void initializeComponents() {
    // Create components for the workspace
    VBox leftPane = new VBox();
    leftPane.getChildren().addAll(new Label("Command Prompt"), new TextField());
    leftPane.setAlignment(Pos.CENTER);
    leftPane.setSpacing(10);

    VBox rightPane = new VBox();
    rightPane.getChildren().addAll(new Label("Turtle Graphics Area"), new Pane());
    rightPane.setAlignment(Pos.CENTER);
    rightPane.setSpacing(10);

    HBox topPane = new HBox();
    topPane.getChildren().addAll(new Label("Command History"), new ListView<>());
    topPane.setAlignment(Pos.CENTER);
    topPane.setSpacing(10);

    HBox bottomPane = new HBox();
    bottomPane.getChildren().addAll(new Label("Variable View"), new ListView<>());
    bottomPane.setAlignment(Pos.CENTER);
    bottomPane.setSpacing(10);

    content.setLeft(leftPane);
    content.setRight(rightPane);
    content.setTop(topPane);
    content.setBottom(bottomPane);
  }

  /**
   * Retrieves content.
   *
   * @return content
   */
  public Node getContent() {
    return content;
  }
}
