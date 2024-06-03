package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.exceptions.InvalidCommandException;
import slogo.model.parser.nodeparser.GroupNode;
import slogo.model.parser.nodeparser.NodeParser;
import slogo.model.parser.stackparser.StackParser;

public class ParserTest {

  private Turtle turtle;
  private StackParser parser;
  private NodeParser nodeParser;
  private final String testCmd1 = "fd 50 rt 90";
  private final String testCmd2 = "[ fd 50 ]";
  private final String testCmd3 = "ifelse [ > + 2 2 2 ] [ fd 100 ] [ fd 50 ]";
  private final String testCmd4 = "repeat 4 [ fd 50 rt 90 ]";
  private final String invalidCmd1 = "fd 100 100";
  private final String invalidCmd2 = "if [ > + 2 2 1 ] ";

  private Properties loadCommandMappings() {
    Properties properties = new Properties();
    try (InputStream inputStream = getClass().getResourceAsStream(
        "/slogo/example/languages/" + "English" + ".properties")) {
      properties.load(inputStream);
    } catch (IOException e) {
    }
    return properties;
  }

  @BeforeEach
  public void setUpParser() {
    this.turtle = new Turtle(0, 0, 0, true, true, 1);
    this.parser = new StackParser(turtle, loadCommandMappings(), new VariableController(), new
        CommandRegistry(), "English");
    this.nodeParser = new NodeParser();
  }

  @Test
  public void testBracketInput() {
    try {
      assertEquals("[ fd [ sum 1 [ random 100 ] ] ]", parser.addBrackets("fd sum 1 "
          + "random 100"));
      assertEquals("[ fd [ sum 10 [ sum 10 [ sum 10 [ sum 20 20 ] ] ] ] ]",
          parser.addBrackets("fd sum 10 sum 10 sum 10 sum 20 20"));
      assertEquals("[ if [ [ > 3 2 ] ] [ [ fd 100 ] ] ]",
          parser.addBrackets("if [ > 3 2 ] [ fd 100 ]"));
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testCmd() throws InvalidCommandException {
    List<Command> commandList1 = parser.parseToCommands(new
        GroupNode(nodeParser.parseCommand(parser.addBrackets(testCmd1))));
    List<Command> commandList2 = parser.parseToCommands(new
        GroupNode(nodeParser.parseCommand(parser.addBrackets(testCmd2))));
    List<Command> commandList3 = parser.parseToCommands(new
        GroupNode(nodeParser.parseCommand(parser.addBrackets(testCmd3))));
    List<Command> commandList4 = parser.parseToCommands(new
        GroupNode(nodeParser.parseCommand(parser.addBrackets(testCmd4))));
    assertEquals(90.0, executeResult(commandList1));
    assertEquals(50.0, executeResult(commandList2));
    assertEquals(100.0, executeResult(commandList3));
    assertEquals(90.0, executeResult(commandList4));
  }

  @Test
  public void testInvalidCmd() {
    assertThrows(InvalidCommandException.class, () -> {
      parser.parseToCommands(new
          GroupNode(nodeParser.parseCommand(parser.addBrackets(invalidCmd1))));
    });
    assertThrows(InvalidCommandException.class, () -> {
      parser.parseToCommands(new
          GroupNode(nodeParser.parseCommand(parser.addBrackets(invalidCmd2))));
    });
  }

  private double executeResult(List<Command> commandList) {
    double res = 0;
    for (Command command : commandList) {
      res = command.execute();
    }
    return res;
  }

}
