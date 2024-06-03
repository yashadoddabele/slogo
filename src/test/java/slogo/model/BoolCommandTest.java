package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.And;
import slogo.model.commands.Equal;
import slogo.model.commands.GreaterEqual;
import slogo.model.commands.GreaterThan;
import slogo.model.commands.LessEqual;
import slogo.model.commands.LessThan;
import slogo.model.commands.Not;
import slogo.model.commands.NotEqual;
import slogo.model.commands.Or;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import util.DukeApplicationTest;

public class BoolCommandTest extends DukeApplicationTest {

  private Turtle turtle;
  private VariableController variableController;
  private CommandRegistry commandRegistry;
  private ParameterStack params;
  private ParameterStack params2;

  @BeforeEach
  void createInfo() {
    turtle = new Turtle(0, 0, 0, true, true, 1);
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();
    params = new ParameterStack();
    params.pushDouble(0.0);
    params.pushDouble(25.0);

    params2 = new ParameterStack();
    params2.pushDouble(30.0);
    params2.pushDouble(30.0);
  }

  @Test
  void testAnd() {
    Command and = new And(turtle, variableController, commandRegistry);
    and.setParameters(params);
    assertEquals(and.execute(), 0);
    and.setParameters(params2);
    assertEquals(and.execute(), 1);
    assertEquals(and.numV(), 2);
  }

  @Test
  void testEqual() {
    Command equal = new Equal(turtle, variableController, commandRegistry);
    equal.setParameters(params);
    assertEquals(equal.execute(), 0);

    equal.setParameters(params2);
    assertEquals(equal.execute(), 1);

    assertEquals(equal.numV(), 2);
  }

  @Test
  void testGreaterEqual() {
    Command gt = new GreaterEqual(turtle, variableController, commandRegistry);
    gt.setParameters(params2);
    assertEquals(gt.execute(), 1);
    params.pushDouble(3.0);
    gt.setParameters(params);
    assertEquals(gt.execute(), 0);

    assertEquals(gt.numV(), 2);
  }

  @Test
  void testGreater() {
    Command gteq = new GreaterThan(turtle, variableController, commandRegistry);
    gteq.setParameters(params);
    assertEquals(gteq.execute(), 1);
    gteq.setParameters(params2);
    assertEquals(gteq.execute(), 0);

    assertEquals(gteq.numV(), 2);
  }

  @Test
  void testLess() {
    Command less = new LessThan(turtle, variableController, commandRegistry);
    less.setParameters(params);
    assertEquals(less.execute(), 0);

    params2.pushDouble(2.0);
    less.setParameters(params2);
    assertEquals(less.execute(), 1);

    assertEquals(less.numV(), 2);
  }

  @Test
  void testLessorEq() {
    Command less = new LessEqual(turtle, variableController, commandRegistry);
    less.setParameters(params);
    assertEquals(less.execute(), 0);

    less.setParameters(params2);
    assertEquals(less.execute(), 1);

    assertEquals(less.numV(), 2);
  }

  @Test
  void testNot() {
    Command not = new Not(turtle, variableController, commandRegistry);
    params.pop();
    not.setParameters(params);

    assertEquals(not.execute(), 1);
    params.pushDouble(40.0);
    not.setParameters(params);
    assertEquals(not.execute(), 0);

    assertEquals(not.numV(), 1);
  }

  @Test
  void testNotEq() {
    Command notEq = new NotEqual(turtle, variableController, commandRegistry);
    notEq.setParameters(params);
    assertEquals(notEq.execute(), 1);

    notEq.setParameters(params2);
    assertEquals(notEq.execute(), 0);

    assertEquals(notEq.numV(), 2);
  }

  @Test
  void testOr() {
    Command or = new Or(turtle, variableController, commandRegistry);
    or.setParameters(params);
    assertEquals(or.execute(), 1);

    params.pushDouble(0.0);
    params.pushDouble(0.0);
    or.setParameters(params);
    assertEquals(or.execute(), 0);

    assertEquals(or.numV(), 2);
  }


}


