package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.Ask;
import slogo.model.commands.Forward;
import slogo.model.commands.Turtles;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;

//public class MultipleTurtlesTests {
//
//  private VariableController variableController;
//  private CommandRegistry commandRegistry;
//  private TurtleBale turtles;
//
//  @BeforeEach
//  void createTurtles() {
//    turtles = new TurtleBale();
//    variableController = new VariableController();
//    commandRegistry = new CommandRegistry();
//  }
//
//  @Test
//  void testTellAndTurtles() {
//    Command turtComm = new Turtles(turtles, variableController, commandRegistry);
//    assertEquals(turtComm.execute(), 0);
//    Command tell = new Tell(turtles, variableController, commandRegistry);
//    ParameterStack parameterStack = new ParameterStack();
//    parameterStack.pushDouble(1.0);
//    tell.setParameters(parameterStack);
//    tell.execute();
//    assertEquals(1, turtComm.execute());
//    assertEquals(turtles.getActiveIds().get(0), 1);
//  }
//
//  @Test
//  void testFd() {
//    Command tell = new Tell(turtles, variableController, commandRegistry);
//    ParameterStack parameterStack = new ParameterStack();
//    parameterStack.pushDouble(1.0);
//    parameterStack.pushDouble(2.0);
//    tell.setParameters(parameterStack);
//    tell.execute();
//    tell.setParameters(parameterStack);
//    tell.execute();
//    Command fd = new Forward(turtles, variableController, commandRegistry);
//    ParameterStack fdStack = new ParameterStack();
//    fdStack.pushDouble(100.0);
//    fd.setParameters(fdStack);
//    fd.execute();
//    Command turtComm = new Turtles(turtles, variableController, commandRegistry);
//    assertEquals(turtComm.execute(), 2);
//    assertEquals(turtles.getTurtlebyId((int) 1.0).getXpos(), 100.0);
//    assertEquals(turtles.getTurtlebyId((int) 2.0).getXpos(), 100.0);
//  }
//
//  @Test
//  void testAsk() {
//    Command tell = new Tell(turtles, variableController, commandRegistry);
//    ParameterStack parameterStack = new ParameterStack();
//    parameterStack.pushDouble(1.0);
//    parameterStack.pushDouble(2.0);
//    tell.setParameters(parameterStack);
//    tell.execute();
//    tell.setParameters(parameterStack);
//    tell.execute();
//
//    Command fd = new Forward(turtles, variableController, commandRegistry);
//    ParameterStack fdStack = new ParameterStack();
//    fdStack.pushDouble(100.0);
//    fd.setParameters(fdStack);
//
//    Command ask = new Ask(turtles, variableController, commandRegistry);
//    ParameterStack askStack = new ParameterStack();
//    askStack.pushCommandList(new ArrayList<Command>(List.of(fd)));
//    askStack.pushDouble(2.0);
//    ask.setParameters(askStack);
//
//    ask.execute();
//
//    assertEquals(turtles.getTurtlebyId((int) 1.0).getXpos(), 0.0);
//    assertEquals(turtles.getTurtlebyId((int) 2.0).getXpos(), 100.0);
//
//  }
//
//}
