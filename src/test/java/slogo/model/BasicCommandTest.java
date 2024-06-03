package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.Backward;
import slogo.model.commands.Forward;
import slogo.model.commands.HideTurtle;
import slogo.model.commands.Home;
import slogo.model.commands.Left;
import slogo.model.commands.PenDown;
import slogo.model.commands.PenUp;
import slogo.model.commands.Right;
import slogo.model.commands.SetHeading;
import slogo.model.commands.SetPosition;
import slogo.model.commands.SetTowards;
import slogo.model.commands.ShowTurtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import util.DukeApplicationTest;

public class BasicCommandTest extends DukeApplicationTest {

  private Turtle turtle;
  private VariableController variableController;
  private CommandRegistry commandRegistry;

  @BeforeEach
  void createTurtle() {
    turtle = new Turtle(0, 0, 0, true, true,0);
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();
  }

  @Test
  void testForward() {
    double fdDis = 100.0;
    Command fdCom = new Forward(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    fdCom.setParameters(parameterStack);
    fdCom.execute();
    assertEquals(turtle.getXpos(), fdDis);
  }

  @Test
  void testBk() {
    double bkDis = 100;
    Command bkCom = new Backward(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(bkDis);
    bkCom.setParameters(parameterStack);
    bkCom.execute();
    assertEquals(turtle.getXpos(), -bkDis);
    assertEquals(bkCom.numV(), 1);
  }

  @Test
  void testGo() {
    Command goCom = new SetPosition(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    parameterStack.pushDouble(50.0);
    goCom.setParameters(parameterStack);
    goCom.execute();
    assertEquals(turtle.getXpos(), 50);
    assertEquals(turtle.getYpos(), 100);

    assertEquals(goCom.numV(), 2);
  }

  @Test
  void testHome() {
    Command homeCom = new Home(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    homeCom.setParameters(parameterStack);
    homeCom.execute();
    assertEquals(turtle.getXpos(), 0);

    assertEquals(homeCom.numV(), 0);
  }

  @Test
  void testHtAndSt() {
    Command htCom = new HideTurtle(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    htCom.setParameters(parameterStack);
    htCom.execute();
    assertFalse(turtle.isShow());

    assertEquals(htCom.numV(), 0);

    Command stCom = new ShowTurtle(turtle, variableController, commandRegistry);
    stCom.setParameters(parameterStack);
    stCom.execute();
    assertTrue(turtle.isShow());

    assertEquals(stCom.numV(), 0);

  }

  @Test
  void testPdAndPu() {
    Command pdCom = new PenDown(turtle, variableController, commandRegistry);
    Command puCom = new PenUp(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    pdCom.setParameters(parameterStack);
    puCom.setParameters(parameterStack);

    pdCom.execute();
    assertTrue(turtle.isPenDown());
    puCom.execute();
    assertFalse(turtle.isPenDown());

    assertEquals(pdCom.numV(), 0);
    assertEquals(puCom.numV(), 0);
  }

  @Test
  void testRtAndLt() {
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    Command rtCom = new Right(turtle, variableController, commandRegistry);
    rtCom.setParameters(parameterStack);
    rtCom.execute();
    assertEquals(turtle.getAngle(), 100);
    Command lfCom = new Left(turtle, variableController, commandRegistry);
    parameterStack.pushDouble(70.0);
    lfCom.setParameters(parameterStack);
    lfCom.execute();
    assertEquals(turtle.getAngle(), 30);

    assertEquals(lfCom.numV(), 1);
    assertEquals(rtCom.numV(), 1);

  }

  @Test
  void testSeth() {
    Command sethCom = new SetHeading(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    sethCom.setParameters(parameterStack);
    sethCom.execute();
    assertEquals(turtle.getAngle(), 100);
    assertEquals(sethCom.numV(), 1);
  }

  @Test
  void testSetTow() {
    Command towards = new SetTowards(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    parameterStack.pushDouble(200.0);
    towards.setParameters(parameterStack);
    towards.execute();

    double targetAngleRadians = Math.atan2(100, 200);
    double targetAngleDegrees = Math.toDegrees(targetAngleRadians);
    double deltaAngle = Math.abs(targetAngleDegrees);

    assertEquals(turtle.getAngle(), deltaAngle);
    assertEquals(towards.numV(), 2);
  }


}
