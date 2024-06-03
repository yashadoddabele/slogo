package slogo.view;

import static slogo.view.TurtlePane.TURTLE_CENTER;
import static slogo.view.TurtlePane.TURTLE_SIZE;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * TurtleAvatar class represents the graphical avatar of the turtle in the user interface.
 */
public class TurtleAvatar {

  public static final String TURTLE_AVATAR_ID = "turtleAvatar";
  private final double id;
  private Node myTurtleAvatar;

  TurtleAvatar(double id) {
    this.id = id;
  }


  /**
   * @return The image selected from the file by the user
   */
  private Image setTurtleImage() {
    File selectedTurtleImage = TurtleAvatarChooser.makeChooser().showOpenDialog(new Stage());
    Image image;

    try {
      image = new Image(selectedTurtleImage.toURI().toString());
    } catch (NullPointerException | IllegalArgumentException e) {
      throw new GuiException("Invalid File Selected: " + e.getMessage());
    }
    return image;
  }

  /**
   * Loads the turtle avatar image from the specified file.
   */
  public void returnTurtle() {
    Image turtleImage = setTurtleImage();
    ImageView turtleShape = new ImageView(turtleImage);
    turtleShape.setFitHeight(TURTLE_SIZE);
    turtleShape.setFitWidth(TURTLE_SIZE);
    turtleShape.setId(TURTLE_AVATAR_ID);
    myTurtleAvatar = turtleShape;
  }

  /**
   * Sets the turtle avatar shape to a circle.
   */
  public void setShapeToCircle() {
    Circle turtleCircle = new Circle(TURTLE_CENTER);
    turtleCircle.setCenterX(TURTLE_CENTER);
    turtleCircle.setCenterY(TURTLE_CENTER);
    turtleCircle.setId(TURTLE_AVATAR_ID);
    myTurtleAvatar = turtleCircle;
  }

  /**
   * Sets the turtle avatar shape to a square.
   */
  public void setShapeToSquare() {
    Shape turtleSquare = new Rectangle(TURTLE_SIZE, TURTLE_SIZE);
    turtleSquare.setId(TURTLE_AVATAR_ID);
    myTurtleAvatar = turtleSquare;
  }

  /**
   * Retrieves the turtle avatar node.
   *
   * @return The node representing the turtle avatar.
   */
  public Node getMyTurtleAvatar() {
    return myTurtleAvatar;
  }

  /**
   * Retrieves the turtle id.
   *
   * @return id
   */
  public double getId() {
    return id;
  }
}
