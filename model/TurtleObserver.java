package slogo.model;

/**
 * TurtleObserver interface defines the contract for objects that observe the state changes of a
 * turtle.
 */
public interface TurtleObserver {

  /**
   * Updates the turtle's position and orientation.
   *
   * @param x     The new x-coordinate of the turtle.
   * @param y     The new y-coordinate of the turtle.
   * @param angle The new orientation angle of the turtle.
   * @param id
   */
  void updateTurtle(double x, double y, double angle, int id);
}