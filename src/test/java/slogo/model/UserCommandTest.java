package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.Backward;
import slogo.model.commands.Forward;
import slogo.model.commands.GreaterThan;
import slogo.model.commands.If;
import slogo.model.commands.IfElse;
import slogo.model.commands.MakeUserInstruction;
import slogo.model.commands.MakeVariable;
import slogo.model.commands.Repeat;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import util.DukeApplicationTest;

public class UserCommandTest extends DukeApplicationTest {

  private VariableController variableController;
  private Turtle turtle;
  private CommandRegistry commandRegistry;
  private List<Command> expr;
  private List<Command> expr2;
  private List<Command> cmds1;
  private List<Command> cmds2;

  private final double delta = 1E-7;


  @BeforeEach
  public void setUp() {
    turtle = new Turtle(0, 0, 0, true, true, 1);
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();

    Command greaterThan = new GreaterThan(turtle, variableController, commandRegistry);
    ParameterStack greaterThanParameterStack = new ParameterStack();
    greaterThanParameterStack.pushDouble(3.0);
    greaterThanParameterStack.pushDouble(2.0);
    greaterThan.setParameters(greaterThanParameterStack);
    expr = new ArrayList<>();
    expr.add(greaterThan);

    Command greaterThan2 = new GreaterThan(turtle, variableController, commandRegistry);
    ParameterStack greaterThanParameterStack2 = new ParameterStack();
    greaterThanParameterStack2.pushDouble(2.0);
    greaterThanParameterStack2.pushDouble(3.0);
    greaterThan2.setParameters(greaterThanParameterStack2);
    expr2 = new ArrayList<>();
    expr2.add(greaterThan2);

    Command fdCom = new Forward(turtle, variableController, commandRegistry);
    ParameterStack fdParameterStack = new ParameterStack();
    fdParameterStack.pushDouble(100.0);
    fdCom.setParameters(fdParameterStack);
    cmds1 = new ArrayList<>();
    cmds1.add(fdCom);

    Command bkCom = new Backward(turtle, variableController, commandRegistry);
    ParameterStack bkParameterStack = new ParameterStack();
    bkParameterStack.pushDouble(100.0);
    bkCom.setParameters(bkParameterStack);
    cmds2 = new ArrayList<>();
    cmds2.add(bkCom);
  }

  @Test
  public void testIfElse() {
    Command ifElseCmd = new IfElse(turtle, variableController, commandRegistry);
    ParameterStack ifElseParameterStack = new ParameterStack();
    ifElseParameterStack.pushCommandList(expr);
    ifElseParameterStack.pushCommandList(cmds1);
    ifElseParameterStack.pushCommandList(cmds2);
    ifElseCmd.setParameters(ifElseParameterStack);
    double res = ifElseCmd.execute();
    assertEquals(res, 100);
    assertEquals(ifElseCmd.numV(), 3);
  }

  @Test
  public void testIf() {
    If ifCom = new If(turtle, variableController, commandRegistry);
    ParameterStack ifParameterStack = new ParameterStack();

    ifParameterStack.pushCommandList(cmds1);
    ifParameterStack.pushCommandList(expr2);
    ifCom.setParameters(ifParameterStack);
    double res = ifCom.execute();
    assertEquals(res, 100);

  }

  @Test
  public void testMake() {
    Command makeCmd = new MakeVariable(turtle, variableController, commandRegistry);
    ParameterStack makeCmdStack = new ParameterStack();
    makeCmdStack.pushDouble(2.0);
    //this is a bit weird... in test case, you have to push :num
    //but in front end you need to type %num for make variable
    //because i cannot come up with a way to distinguish whether it is a parameter or it is a constant
    makeCmdStack.pushString(":num");
    makeCmd.setParameters(makeCmdStack);
    makeCmd.execute();
    assertEquals(variableController.getVariable(":num").getDoubleValue(), 2.0);
  }

  @Test
  public void testRepeat() {
    cmds1.addAll(cmds2);
    Repeat repeatCom = new Repeat(turtle, variableController, commandRegistry);
    ParameterStack rtParams = new ParameterStack();
    rtParams.pushCommandList(cmds1);
    rtParams.pushDouble(5.0);
    repeatCom.setParameters(rtParams);

    double finalResult = repeatCom.execute();
    assertEquals(100, finalResult);
    assertEquals(turtle.getYpos(), 0);
    assertEquals(turtle.getXpos(), 0);
    assertEquals(repeatCom.numV(), 2);
  }

  @Test
  public void testUserInstr() {
    Command userInstr = new MakeUserInstruction(turtle, variableController, commandRegistry);
    ParameterStack data = new ParameterStack();
    cmds1.addAll(cmds2);
    data.pushCommandList(cmds1);
    data.pushString("testCommand");
    userInstr.setParameters(data);
    assertEquals(1, userInstr.execute());
    assertEquals(turtle.getXpos(), 0);
    assertEquals(turtle.getYpos(), 0);
    assertEquals(commandRegistry.getCommandGroup("testCommand"), cmds1);
  }


}
