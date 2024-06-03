package slogo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that encapsulates activity on any number of turtles currently in the simulation.
 */
public class TurtleBale {

  //Hashmap to keep track of all indexed turtles
  private final Map<Integer, Turtle> turtles;
  private List<Integer> prevStates;

  public TurtleBale() {
    turtles = new HashMap<>();
    //Add a turtle just as a default
    //addTurtle(1);
  }

//  public void addTurtle(int id) {
//    Turtle newTurtle = new Turtle(0, 0, 0, true, true);
//    if (!turtles.containsKey(id)) {
//      turtles.put(id, newTurtle);
//      newTurtle.setActivity(true);
//    } else {
//      turtles.get(id).setActivity(true);
//    }
//  }

  public int getNumTurtles() {
    return turtles.size();
  }

  public Turtle getTurtlebyId(int id) {
    return turtles.getOrDefault(id, null);
  }

  public List<Integer> getActiveIds() {
    List<Integer> ids = new ArrayList<>();
    for (Map.Entry<Integer, Turtle> turtle : turtles.entrySet()) {
      if (turtle.getValue().isActive()) {
        ids.add(turtle.getKey());
      }
    }
    return ids;
  }

  public void addObserver(TurtleObserver observer) {
    for (Map.Entry<Integer, Turtle> turtle : turtles.entrySet()) {
      turtle.getValue().addObserver(observer);
    }
  }

  public void setAsActive(List<Integer> ids) {
    for (Map.Entry<Integer, Turtle> turtle : turtles.entrySet()) {
      if (!ids.contains(turtle.getKey())) {
        turtle.getValue().setActivity(false);
      }
    }
  }
}