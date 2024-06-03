package slogo.view;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * The TurtleAnimation class provides methods to animate the movement and rotation of a turtle.
 */
public class TurtleAnimation {

  public static final double TRAVERSAL_RATE = 100.0;
  public static final double ROTATION_DURATION = 1.0;
  public static final double DEFAULT_SEGMENT_DURATION = 0.001;
  private final TurtlePane turtlePane;
  private final Gui myGui;

  /**
   * Constructs a new TurtleAnimation with the specified GUI and TurtlePane.
   *
   * @param gui   The graphical user interface.
   * @param tpane The turtle pane.
   */
  public TurtleAnimation(Gui gui, TurtlePane tpane) {
    myGui = gui;
    turtlePane = tpane;
  }

  /**
   * Animates the movement and rotation of the turtle.
   *
   * @param agent         The turtle node.
   * @param endX          The x-coordinate of the end position.
   * @param endY          The y-coordinate of the end position.
   * @param rotateDegrees The rotation angle.
   * @param animation     The sequential transition to add the animations to.
   */
  public void makeAnimation(Node agent, double endX, double endY, double rotateDegrees,
      SequentialTransition animation) {

    animateRotation(rotateDegrees, animation);

    double distance = GuiUtil.returnDistance(endX, endY, turtlePane.getXpos(),
        turtlePane.getYpos());

    if (distance > 0) {
      animateMovement(agent, endX, endY, distance, animation);
    }

    turtlePane.setXpos(endX);
    turtlePane.setYpos(endY);
    turtlePane.setMyAngle(rotateDegrees);
  }

  private void animateRotation(double rotateDegrees, SequentialTransition animation) {
    RotateTransition rt = new RotateTransition(Duration.seconds(ROTATION_DURATION));
    rt.setByAngle(rotateDegrees);
    animation.getChildren().add(rt);
  }

  private void animateMovement(Node agent, double endX, double endY, double distance,
      SequentialTransition animation) {
    double duration = distance / TRAVERSAL_RATE;

    double startLineX = turtlePane.adjustForTurtleCenter(turtlePane.getXpos());
    double startLineY = turtlePane.adjustForTurtleCenter(turtlePane.getYpos());
    double endLineX = turtlePane.adjustForTurtleCenter(endX);
    double endLineY = turtlePane.adjustForTurtleCenter(endY);

    Path path = new Path();
    path.getElements().addAll(new MoveTo(startLineX, startLineY), new LineTo(endLineX, endLineY));

    PathTransition pt = new PathTransition(Duration.seconds(duration), path, agent);
    pt.setInterpolator(Interpolator.LINEAR);

    if (myGui.penIsDown()) {
      ParallelTransition parallelTransition = animateLine(agent, endX, endY, duration, pt);
      animation.getChildren().add(parallelTransition);
    } else {
      animation.getChildren().add(pt);
    }
  }

  private ParallelTransition animateLine(Node agent, double endX, double endY,
      double duration, PathTransition pt) {
    double xadjust = turtlePane.adjustForTurtleCenter(TurtlePane.TURTLE_WINDOW_X_CENTER);
    double yadjust = turtlePane.adjustForTurtleCenter(TurtlePane.TURTLE_WINDOW_Y_CENTER);

    int numSegments = (int) (duration / DEFAULT_SEGMENT_DURATION);

    double deltaX = (endX - turtlePane.getXpos()) / numSegments;
    double deltaY = (endY - turtlePane.getYpos()) / numSegments;

    ParallelTransition parallelTransition = new ParallelTransition(pt);

    addAllLineSegments(agent, duration, numSegments, xadjust, deltaX, yadjust, deltaY,
        parallelTransition);

    return parallelTransition;
  }

  private void addAllLineSegments(Node agent, double duration, int numSegments, double xadjust,
      double deltaX, double yadjust, double deltaY,
      ParallelTransition parallelTransition) {
    for (int i = 0; i < numSegments; i++) {
      double startX = turtlePane.getXpos() + xadjust + (i * deltaX);
      double startY = turtlePane.getYpos() + yadjust + (i * deltaY);
      double endXsegment = turtlePane.getXpos() + xadjust + ((i + 1) * deltaX);
      double endYsegment = turtlePane.getYpos() + yadjust + ((i + 1) * deltaY);

      Line pathSegment = new Line(startX, startY, endXsegment, endYsegment);
      pathSegment.setStroke(turtlePane.getLineColor());

      double pauseDuration = (i + 1) * duration / numSegments;

      PauseTransition pause = new PauseTransition(Duration.seconds(pauseDuration));
      pause.setOnFinished(
          e -> addLineToPaneIfNotPresent(((Pane) agent.getParent()), pathSegment));
      parallelTransition.getChildren().add(pause);
    }
  }

  private void addLineToPaneIfNotPresent(Pane pane, Line line) {
    boolean linePresent = false;

    for (Node node : pane.getChildren()) {
      if (node instanceof Line existingLine) {
        if (existingLine.getStartX() == line.getStartX() &&
            existingLine.getStartY() == line.getStartY() &&
            existingLine.getEndX() == line.getEndX() &&
            existingLine.getEndY() == line.getEndY()) {

          linePresent = true;
          break;
        }
      }
    }

    if (!linePresent) {
      pane.getChildren().add(line);
    }
  }
}
