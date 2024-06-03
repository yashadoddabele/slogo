package slogo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Turtle object in the backend.
 */
public class Turtle {

  private double xpos;
  private double ypos;
  private double angle;
  private boolean penDown;
  private boolean show;
  private int id;
  private boolean active;
  private final List<TurtleObserver> observers = new ArrayList<>();

  /**
   * Constructs a new Turtle object with the specified attributes.
   *
   * @param xpos    The initial x-coordinate of the turtle.
   * @param ypos    The initial y-coordinate of the turtle.
   * @param angle   The initial angle (orientation) of the turtle.
   * @param penDown A boolean indicating whether the turtle's pen is initially down (true) or up
   *                (false).
   * @param show    A boolean indicating whether the turtle is initially shown (true) or hidden
   *                (false).
   */

  public Turtle(double xpos, double ypos, double angle, boolean penDown, boolean show, int id) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.angle = angle;
    this.penDown = penDown;
    this.show = show;
    this.id = id;
    active = false;
  }

  /**
   * Adds a TurtleObserver to the list of observers.
   *
   * @param observer The observer to be added.
   */
  public void addObserver(TurtleObserver observer) {
    observers.add(observer);
  }

  /**
   * Notifies all observers about the current state of the turtle.
   */
  public void notifyObservers() {
    for (TurtleObserver observer : observers) {
      if (active) {
        observer.updateTurtle(xpos, ypos, angle, id);
      }
    }
  }

  /**
   * Moves the turtle forward by the specified distance and notifies observers.
   *
   * @param deltaDistance The distance by which the turtle should move forward.
   * @return The absolute value of the distance moved.
   */
  public double moveForward(double deltaDistance) {
    if (!active) {
      return 0;
    }
    double angleRadians = Math.toRadians(angle);

    double newxPos = xpos + deltaDistance * Math.cos(angleRadians);
    double newyPos = ypos + deltaDistance * Math.sin(angleRadians);

    // Only update position if the turtle is shown
    if (show) {
      xpos = newxPos;
      ypos = newyPos;
      notifyObservers();
    }

    return Math.abs(deltaDistance);
  }

  /**
   * Rotates the turtle to the right by the specified angle and notifies observers.
   *
   * @param deltaAngle The angle by which the turtle should rotate to the right.
   * @return The absolute value of the angle rotated.
   */
  public double rotateRight(double deltaAngle) {
    if (!active) {
      return 0;
    }
    angle += deltaAngle;
    normalizeAngle();
    notifyObservers();
    return Math.abs(deltaAngle);
  }

  /**
   * Rotates the turtle towards a specified target position and notifies observers.
   *
   * @param xtarget The x-coordinate of the target position.
   * @param ytarget The y-coordinate of the target position.
   * @return The absolute value of the angle rotated.
   */

  public double rotateTowards(double xtarget, double ytarget) {
    if (!active) {
      return 0;
    }
    double deltaX = xtarget - xpos;
    double deltaY = ytarget - ypos;
    double targetAngleRadians = Math.atan2(deltaY, deltaX);
    double targetAngleDegrees = Math.toDegrees(targetAngleRadians);
    double deltaAngle = targetAngleDegrees - angle;
    rotateRight(deltaAngle);
    return Math.abs(deltaAngle);
  }

  /**
   * Sets the turtle's position to the specified coordinates and notifies observers.
   *
   * @param xtarget The x-coordinate of the target position.
   * @param ytarget The y-coordinate of the target position.
   * @return The distance between the current position and the target position.
   */
  public double setPos(double xtarget, double ytarget) {
    if (!active) {
      return 0;
    }
    double distance = Math.sqrt(Math.pow(xtarget - xpos, 2) + Math.pow(ytarget - ypos, 2));
    xpos = xtarget;
    ypos = ytarget;
    notifyObservers();
    return distance;
  }

  /**
   * Sets the angle of the turtle to the specified value.
   *
   * @param deltaAngle The angle to set the turtle to, relative to its current angle.
   * @return The absolute difference between the previous angle and the new angle.
   */
  public double setAngle(double deltaAngle) {
    if (!active) {
      return 0;
    }
    double prevAngle = angle;
    angle = deltaAngle;
    normalizeAngle();
    notifyObservers();
    return Math.abs(prevAngle - angle);
  }

  /**
   * Normalizes the angle of the turtle to be within the range [0, 360).
   */
  private void normalizeAngle() {
    if (!active) {
      return;
    }
    angle %= 360;
    if (angle < 0) {
      angle += 360;
    }
  }

  /**
   * Sets the pen state of the turtle.
   *
   * @param penDown The new state of the pen (true for down, false for up).
   * @return 1 if the pen is down, 0 otherwise.
   */
  public int setPenDown(boolean penDown) {
    if (!active) {
      return 0;
    }
    this.penDown = penDown;
    notifyObservers();
    return penDown ? 1 : 0;
  }

  /**
   * Sets the visibility state of the turtle.
   *
   * @param show The new visibility state of the turtle (true for shown, false for hidden).
   * @return 1 if the turtle is shown, 0 otherwise.
   */
  public int setShow(boolean show) {
    if (!active) {
      return 0;
    }
    this.show = show;
    notifyObservers();
    return show ? 1 : 0;
  }

  public double getXpos() {
    return xpos;
  }

  public double getYpos() {
    return ypos;
  }

  public double getAngle() {
    return angle;
  }

  public boolean isPenDown() {
    return penDown;
  }

  public boolean isShow() {
    return show;
  }

  /**
   * Moves the turtle backward by the specified distance.
   *
   * @param deltaDistance The distance by which the turtle should move backward.
   * @return The absolute value of the distance moved.
   */
  public double moveBackward(double deltaDistance) {
    if (!active) {
      return 0;
    }
    double angleRadians = Math.toRadians(angle);

    double newxPos = xpos - deltaDistance * Math.cos(angleRadians);
    double newyPos = ypos - deltaDistance * Math.sin(angleRadians);

    // Only update position if the turtle is shown
    if (show) {
      xpos = newxPos;
      ypos = newyPos;
      notifyObservers();
    }
    return Math.abs(deltaDistance);
  }

  /**
   * Sets the activity of a turtle.
   *
   * @param active boolean switch to change turtle's activity
   */
  public void setActivity(boolean active) {
    this.active = active;
//    if (this.active) {
//      for (TurtleObserver observer : observers) {
//        observer.addActiveTurtle(id);
//      }
//    }
  }

  /**
   * Get activity status of the turtle.
   */
  public boolean isActive() {
    return this.active;
  }

  /**
   * Gets the integer ID associated with this turtle.
   *
   * @return turtle's ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the turtle's ID.
   *
   * @param id new integer ID to set the turtle to.
   */
  public void setId(int id) {
    this.id = id;
  }
}

