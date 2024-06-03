package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.Heading;
import slogo.model.commands.IsPenDown;
import slogo.model.commands.IsShowing;
import slogo.model.commands.Xcoordinate;
import slogo.model.commands.Ycoordinate;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;

public class QueriesCommandTest {

  private Turtle turtle;
  private VariableController variableController;
  private CommandRegistry commandRegistry;
  private ParameterStack params;

  @BeforeEach
  void createInfo() {
    turtle = new Turtle(0, 0, 0, true, true, 1);
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();
    params = new ParameterStack();
    params.pushDouble(0.0);
    params.pushDouble(25.0);
  }

  @Test
  void testHeading() {
    Command heading = new Heading(turtle, variableController, commandRegistry);
    heading.setParameters(params);
    assertEquals(heading.execute(), 0);
    assertEquals(heading.numV(), 0);
  }

  @Test
  void testPenDown() {
    Command pd = new IsPenDown(turtle, variableController, commandRegistry);
    pd.setParameters(params);
    turtle.setPenDown(true);
    assertEquals(pd.numV(), 0);
    assertEquals(pd.execute(), 1);
  }

  @Test
  void testShowing() {
    Command show = new IsShowing(turtle, variableController, commandRegistry);
    show.setParameters(params);
    turtle.setShow(true);
    assertEquals(show.execute(), 1);
    assertEquals(show.numV(), 0);
  }

  @Test
  void testX() {
    Command x = new Xcoordinate(turtle, variableController, commandRegistry);
    x.setParameters(params);
    assertEquals(x.execute(), 0);
    assertEquals(x.numV(), 0);
  }

  @Test
  void testY() {
    Command y = new Ycoordinate(turtle, variableController, commandRegistry);
    y.setParameters(params);
    assertEquals(y.execute(), 0);
    assertEquals(y.numV(), 0);
  }

}


