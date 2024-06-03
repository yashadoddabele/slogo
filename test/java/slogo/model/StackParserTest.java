package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.Forward;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.stackparser.ParameterStack;
import slogo.model.parser.stackparser.ParameterStackElement;

public class StackParserTest {

  private Turtle turtle;
  private VariableController variableController;
  private CommandRegistry commandRegistry;
  private ParameterStack parameterStack;
  private Command fdCmd;

  @BeforeEach
  void initialization() {
    turtle = new Turtle(0, 0, 0, true, true, 0);
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();
    parameterStack = new ParameterStack();
    fdCmd = new Forward(turtle, variableController, commandRegistry);
    ParameterStack parameterStack = new ParameterStack();
    parameterStack.pushDouble(100.0);
    fdCmd.setParameters(parameterStack);
  }

  @Test
  public void testConstructor_DoubleValue() {
    Double doubleValue = 10.5;
    ParameterStackElement parameterStackElement = new ParameterStackElement(doubleValue);

    assertEquals(doubleValue, parameterStackElement.getDoubleValue());
    assertNull(parameterStackElement.getCommandListValue());
    assertNull(parameterStackElement.getStringValue());
  }

  @Test
  public void testConstructor_CommandListValue() {
    List<Command> commandListValue = new ArrayList<>();
    commandListValue.add(fdCmd);
    ParameterStackElement parameterStackElement = new ParameterStackElement(commandListValue);

    assertNull(parameterStackElement.getDoubleValue());
    assertEquals(commandListValue, parameterStackElement.getCommandListValue());
    assertNull(parameterStackElement.getStringValue());
  }

  @Test
  public void testConstructor_StringValue() {
    String stringValue = "fd";
    ParameterStackElement parameterStackElement = new ParameterStackElement(stringValue);

    assertNull(parameterStackElement.getDoubleValue());
    assertNull(parameterStackElement.getCommandListValue());
    assertEquals(stringValue, parameterStackElement.getStringValue());
  }

  @Test
  public void testPushDouble() {
    Double doubleValue = 10.0;
    parameterStack.pushDouble(doubleValue);
    ParameterStackElement poppedElement = parameterStack.pop();

    assertEquals(doubleValue, poppedElement.getDoubleValue());
    assertNull(poppedElement.getCommandListValue());
    assertNull(poppedElement.getStringValue());
  }

  @Test
  public void testPushCommandList() {
    List<Command> commandList = new ArrayList<>();
    commandList.add(fdCmd);
    parameterStack.pushCommandList(commandList);
    ParameterStackElement poppedElement = parameterStack.pop();

    assertNull(poppedElement.getDoubleValue());
    assertEquals(commandList, poppedElement.getCommandListValue());
    assertNull(poppedElement.getStringValue());
  }

  @Test
  public void testPushElement() {
    ParameterStackElement parameterStackElement = new ParameterStackElement("fd");
    parameterStack.pushElement(parameterStackElement);
    ParameterStackElement poppedElement = parameterStack.pop();

    assertEquals("fd", poppedElement.getStringValue());
    assertNull(poppedElement.getDoubleValue());
    assertNull(poppedElement.getCommandListValue());
  }

  @Test
  public void testPushString() {
    String stringValue = "fd";
    parameterStack.pushString(stringValue);
    ParameterStackElement poppedElement = parameterStack.pop();

    assertEquals(stringValue, poppedElement.getStringValue());
    assertNull(poppedElement.getDoubleValue());
    assertNull(poppedElement.getCommandListValue());
  }

  @Test
  public void testPop() {
    Double doubleValue = 100.0;
    parameterStack.pushDouble(doubleValue);
    ParameterStackElement poppedElement = parameterStack.pop();

    assertEquals(doubleValue, poppedElement.getDoubleValue());
    assertTrue(parameterStack.isEmpty());
  }

  @Test
  public void testPeek() {
    Double doubleValue = 100.0;
    parameterStack.pushDouble(doubleValue);
    ParameterStackElement peekedElement = parameterStack.peek();
    assertEquals(doubleValue, peekedElement.getDoubleValue());
    assertFalse(parameterStack.isEmpty());
  }
}