package slogo.view;

import static slogo.view.TurtleAnimation.TRAVERSAL_RATE;

import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * TurtlePane class represents a pane containing the turtle graphics area and control buttons. It
 * provides methods to update the turtle's position and appearance, handle user input, and load
 * programs.
 */
public class TurtlePane {

  public static final double TURTLE_WINDOW_HEIGHT = 200.0;
  public static final double TURTLE_WINDOW_WIDTH = 1000.0;
  public static final double TURTLE_WINDOW_Y_CENTER = TURTLE_WINDOW_HEIGHT / 2;
  public static final double TURTLE_WINDOW_X_CENTER = TURTLE_WINDOW_WIDTH / 2;
  public static final int TURTLE_SIZE = 30;
  public static final int TURTLE_CENTER = TURTLE_SIZE / 2;
  private final Gui myGui;
  private final LoadProgram myLoader;
  private final SaveProgram mySaver;
  private BorderPane turtlePane;
  private Color lineColor;
  private TurtleAvatar turtleAvatar;
  private double xpos;
  private double ypos;
  private double myAngle;
  private SequentialTransition sequentialTransition;
  private TurtleAnimation myAnimation;
  private String backgroundColor;

  /**
   * Constructs a new TurtlePane with the specified GUI.
   *
   * @param gui The graphical user interface.
   */
  TurtlePane(Gui gui) {
    lineColor = Color.BLACK;
    backgroundColor = "WHITE";
    myGui = gui;
    myLoader = new LoadProgram(myGui);
    mySaver = new SaveProgram(this, myGui);
  }

  private static double keepWithinWindowBounds(double xcor, double limit) {
    if (xcor >= limit - TURTLE_SIZE) {
      xcor = limit - TURTLE_SIZE;
    } else if (xcor <= -limit) {
      xcor = -limit;
    }
    return xcor;
  }

  /**
   * sets sequentialTransition to a new instance of SequentialTransition
   */
  public void resetAnimation() {
    sequentialTransition = new SequentialTransition(turtleAvatar.getMyTurtleAvatar());
  }

  /**
   * Returns a StackPane containing the turtle window and control buttons.
   *
   * @param animation The animation handler for the turtle.
   * @return A StackPane containing the turtle window and control buttons.
   */
  public StackPane returnTurtleWindow(TurtleAnimation animation) {

    myAnimation = animation;
    turtleAvatar = new TurtleAvatar(1);
    turtleAvatar.returnTurtle();
    turtlePane = new BorderPane();
    turtlePane.setId("turtle-pane");
    resetAnimation();

    Button pauseBtn = GuiUtil.createButton("pause", e -> pauseAnimation());
    Button playBtn = GuiUtil.createButton("(re)play", e -> playAnimation());
    Button stepBtn = GuiUtil.createButton("step", e -> stepAnimation());
    Button newBtn = GuiUtil.createButton("new", e -> initializeNewSlogo());
    Button helpBtn = GuiUtil.createButton("Help", e -> myGui.showHelpView());
    Button saveButton = GuiUtil.createButton("Save Program", e -> mySaver.saveProgram());
    Button loadButton = GuiUtil.createButton("Load Program", e -> myLoader.loadProgramExistingIde());

    ColorPicker turtleWindowColorPicker = createColorPicker(e ->
        updateTurtleBackgroundColor(((ColorPicker) e.getSource()).getValue()));

    ColorPicker penColorPicker = createColorPicker(e ->
        updatePenColor(((ColorPicker) e.getSource()).getValue()));

    ComboBox<String> themeSelector = new ComboBox<>();
    themeSelector.getItems().addAll("Light Mode", "Dark Mode");
    themeSelector.setValue("Light Mode");
    themeSelector.setOnAction(event -> myGui.applyTheme(themeSelector.getValue()));

    centerTurtle();

    VBox vbox = new VBox();
    HBox buttons = new HBox(10);
    buttons.getChildren().addAll(newBtn, themeSelector, turtleWindowColorPicker, penColorPicker,
        pauseBtn, playBtn, stepBtn, helpBtn, saveButton, loadButton);
    vbox.getChildren().addAll(buttons);

    StackPane combinedWindows = GuiUtil.putAndAlignButtonsAboveWindows(vbox, turtlePane);

    setCoordinatesAndAngle(0, 0, 0);

    return combinedWindows;
  }

  private void centerTurtle() {
    turtleAvatar.getMyTurtleAvatar().setLayoutX(TURTLE_WINDOW_X_CENTER);
    turtleAvatar.getMyTurtleAvatar().setLayoutY(TURTLE_WINDOW_Y_CENTER);
    turtlePane.getChildren().add(turtleAvatar.getMyTurtleAvatar());
  }

  private double[] getTurtleCoordinatesInParent(Node child, BorderPane parent) {
    double[] coordinates = new double[2];
    Bounds boundsInScene = child.localToScene(child.getBoundsInLocal());
    Bounds boundsInParent = parent.sceneToLocal(boundsInScene);
    coordinates[0] = boundsInParent.getMinX() + TURTLE_CENTER;
    coordinates[1] = boundsInParent.getMinY() + TURTLE_CENTER;
    return coordinates;
  }

  private void stepAnimation() {
    double[] prevCoordinates = getTurtleCoordinatesInParent(turtleAvatar.getMyTurtleAvatar(),
        turtlePane);

    double astep = sequentialTransition.getCycleDuration().toMillis() / TRAVERSAL_RATE;
    sequentialTransition.jumpTo(
        sequentialTransition.getCurrentTime().add(new Duration(astep)));

    double[] postCoordinates = getTurtleCoordinatesInParent(turtleAvatar.getMyTurtleAvatar(),
        turtlePane);

    Line jumpPath = new Line(prevCoordinates[0], prevCoordinates[1], postCoordinates[0],
        postCoordinates[1]);

    turtlePane.getChildren().add(jumpPath);
  }


  private void pauseAnimation() {
    sequentialTransition.pause();
  }

  /**
   * play sequentialTransition at the start of animation or whenever play is pushed.
   */
  public void playAnimation() {
    sequentialTransition.play();
  }

  /**
   * Updates the pen color.
   *
   * @param selectedColor The selected color for the pen.
   */
  public void updatePenColor(Color selectedColor) {
    lineColor = selectedColor;
  }

  /**
   * Updates the background color of the turtle window.
   *
   * @param selectedColor The selected color for the background.
   */
  public void updateTurtleBackgroundColor(Color selectedColor) {
    this.backgroundColor = toHex(selectedColor);
    turtlePane.setStyle("-fx-background-color: #" + toHex(selectedColor));
  }

  /**
   * Updates the turtle's position and angle.
   *
   * @param xcor  The x-coordinate of the turtle.
   * @param ycor  The y-coordinate of the turtle.
   * @param angle The angle of the turtle.
   */

  public void updateTurtle(double xcor, double ycor, double angle) {
    setShowing();
    xcor = keepWithinWindowBounds(xcor, TURTLE_WINDOW_X_CENTER);
    ycor = keepWithinWindowBounds(ycor, TURTLE_WINDOW_Y_CENTER);

    myAnimation.makeAnimation(turtleAvatar.getMyTurtleAvatar(),
        xcor, ycor, angle - myAngle, sequentialTransition);

    setCoordinatesAndAngle(xcor, ycor, angle);
  }


  private void setShowing() {
    if (myGui.isShowing()) {
      turtleAvatar.getMyTurtleAvatar().setOpacity(1);
    } else {
      turtleAvatar.getMyTurtleAvatar().setOpacity(0);
    }
  }

  private void setCoordinatesAndAngle(double xcor, double ycor, double angle) {
    xpos = xcor;
    ypos = ycor;
    myAngle = angle;
  }

  private void setShapeToCircle() {
    turtlePane.getChildren().remove(turtleAvatar.getMyTurtleAvatar());
    turtleAvatar.setShapeToCircle();
    centerTurtle();
  }

  private void setShapeToSquare() {
    turtlePane.getChildren().remove(turtleAvatar.getMyTurtleAvatar());
    turtleAvatar.setShapeToSquare();
    centerTurtle();
  }


  private String toHex(Color color) {
    return String.format("%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }

  /**
   * Gets the x-coordinate of the turtle.
   *
   * @return the x-coordinate of the turtle
   */
  public double getXpos() {
    return xpos;
  }

  /**
   * Sets the x-coordinate of the turtle.
   *
   * @param x the x-coordinate of the turtle
   */
  public void setXpos(double x) {
    xpos = x;
  }

  /**
   * Gets the y-coordinate of the turtle.
   *
   * @return the y-coordinate of the turtle
   */
  public double getYpos() {
    return ypos;
  }

  /**
   * Sets the y-coordinate of the turtle.
   *
   * @param y the y-coordinate of the turtle
   */
  public void setYpos(double y) {
    ypos = y;
  }

  /**
   * Sets the angle of the turtle.
   *
   * @param angle the angle of the turtle
   */
  public void setMyAngle(double angle) {
    myAngle = angle;
  }

  /**
   * Gets the line color of the turtle.
   *
   * @return the line color of the turtle
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   * Gets the turtle avatar.
   *
   * @return the turtle avatar
   */
  public TurtleAvatar getTurtleAvatar() {
    return turtleAvatar;
  }

  /**
   * Gets the pen color as a hexadecimal string.
   *
   * @return the pen color as a hexadecimal string
   */
  public String getPenColor() {
    return toHex(lineColor);
  }

  /**
   * Gets the background color as a hexadecimal string.
   *
   * @return the background color as a hexadecimal string
   */
  public String getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Adjusts a value for the turtle center.
   *
   * @param adjustableValue the value to adjust
   * @return the adjusted value
   */
  public double adjustForTurtleCenter(double adjustableValue) {
    return adjustableValue + TURTLE_CENTER;
  }

  public BorderPane getTurtlePane() {
    return turtlePane;
  }


  // Method to create a color picker with specified action
  private ColorPicker createColorPicker(EventHandler<ActionEvent> handler) {
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(handler);
    return colorPicker;
  }

  // Method to initialize a new Slogo
  private void initializeNewSlogo() {
    InitiationControl newInitCont = new InitiationControl();
    newInitCont.setUpSlogo(new Stage());
  }
}







