package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.ArcTangent;
import slogo.model.commands.Cosine;
import slogo.model.commands.Difference;
import slogo.model.commands.Minus;
import slogo.model.commands.NaturalLog;
import slogo.model.commands.Pi;
import slogo.model.commands.Power;
import slogo.model.commands.Product;
import slogo.model.commands.Quotient;
import slogo.model.commands.Random;
import slogo.model.commands.RandomRange;
import slogo.model.commands.Remainder;
import slogo.model.commands.Sine;
import slogo.model.commands.SquareRoot;
import slogo.model.commands.Sum;
import slogo.model.commands.Tangent;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;

public class MathCommandTest {

  private ParameterStack params;
  private Turtle turtle;
  private VariableController variableController;
  private CommandRegistry commandRegistry;

  @BeforeEach
  void createInfo() {
    turtle = new Turtle(0, 0, 0, true, true, 1);
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();
    params = new ParameterStack();
    params.pushDouble(1.0);
    params.pushDouble(25.0);
  }

  @Test
  void testArcTan() {
    Command arc = new ArcTangent(turtle, variableController, commandRegistry);
    arc.setParameters(params);
    assertEquals(arc.execute(), Math.toDegrees(Math.atan(25)));
    assertEquals(arc.numV(), 1);
  }

  @Test
  void testCos() {
    Command cos = new Cosine(turtle, variableController, commandRegistry);
    cos.setParameters(params);
    assertEquals(cos.execute(), Math.cos(Math.toRadians(25)));
    assertEquals(cos.numV(), 1);
  }

  @Test
  void testDiff() {
    Command diff = new Difference(turtle, variableController, commandRegistry);
    diff.setParameters(params);
    assertEquals(diff.execute(), 24);
    assertEquals(diff.numV(), 2);
  }

  @Test
  void testMinus() {
    Command minus = new Minus(turtle, variableController, commandRegistry);
    minus.setParameters(params);
    assertEquals(minus.execute(), -25);
    assertEquals(minus.numV(), 1);
  }

  @Test
  void tesNatLog() {
    Command natlog = new NaturalLog(turtle, variableController, commandRegistry);
    natlog.setParameters(params);
    assertEquals(natlog.execute(), Math.log(25));
    assertEquals(natlog.numV(), 1);
    params.pushDouble(-1.0);
    natlog.setParameters(params);
    assertThrows(IllegalArgumentException.class, () -> natlog.execute());
  }

  @Test
  void testPi() {
    Command pi = new Pi(turtle, variableController, commandRegistry);
    pi.setParameters(params);
    assertEquals(pi.execute(), Math.PI);
    assertEquals(pi.numV(), 0);
  }

  @Test
  void testPow() {
    Command pow = new Power(turtle, variableController, commandRegistry);
    pow.setParameters(params);
    assertEquals(pow.execute(), 25);
    assertEquals(pow.numV(), 2);
  }

  @Test
  void tesProd() {
    Command prod = new Product(turtle, variableController, commandRegistry);
    prod.setParameters(params);
    assertEquals(prod.execute(), 25);
    assertEquals(prod.numV(), 2);
  }

  @Test
  void testQuotient() {
    Command q = new Quotient(turtle, variableController, commandRegistry);
    q.setParameters(params);
    assertEquals(q.execute(), 25);
    assertEquals(q.numV(), 2);
  }

  @Test
  void testRand() {
    Command rand = new Random(turtle, variableController, commandRegistry);
    rand.setParameters(params);
    double val = rand.execute();
    assertTrue(val < 25 && val >= 0);
    assertEquals(rand.numV(), 1);
  }

  @Test
  void testRandRange() {
    Command range = new RandomRange(turtle, variableController, commandRegistry);
    params.pushDouble(10.0);
    range.setParameters(params);
    double val = range.execute();
    assertTrue(val < 25 && val >= 10);
    params.pushDouble(20.0);
    params.pushDouble(70.0);
    range.setParameters(params);
    assertThrows(IllegalArgumentException.class, () -> range.execute());
    assertEquals(range.numV(), 2);
  }

  @Test
  void testRemainder() {
    Command rem = new Remainder(turtle, variableController, commandRegistry);
    rem.setParameters(params);
    assertEquals(rem.execute(), 0);
    assertEquals(rem.numV(), 2);
  }

  @Test
  void testSine() {
    Command sin = new Sine(turtle, variableController, commandRegistry);
    sin.setParameters(params);
    assertEquals(sin.execute(), Math.sin(Math.toRadians(25)));
    assertEquals(sin.numV(), 1);
  }

  @Test
  void testSqrt() {
    Command sqrt = new SquareRoot(turtle, variableController, commandRegistry);
    sqrt.setParameters(params);
    assertEquals(sqrt.execute(), 5);
    assertEquals(sqrt.numV(), 1);
  }

  @Test
  void testSum() {
    Command sum = new Sum(turtle, variableController, commandRegistry);
    sum.setParameters(params);
    assertEquals(sum.execute(), 26);
    assertEquals(sum.numV(), 2);
  }

  @Test
  void testTan() {
    Command tan = new Tangent(turtle, variableController, commandRegistry);
    tan.setParameters(params);
    assertEquals(tan.execute(), Math.tan(Math.toRadians(25)));
    assertEquals(tan.numV(), 1);
  }
}
