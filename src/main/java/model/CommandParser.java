package slogo.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.exceptions.ErrorHandling;
import slogo.model.parser.exceptions.InvalidCommandException;
import slogo.model.parser.nodeparser.GroupNode;
import slogo.model.parser.nodeparser.NodeParser;
import slogo.model.parser.stackparser.StackParser;

/**
 * Parses commands provided as input strings and executes them.
 */
public class CommandParser {

  private final Turtle turtle;
  private Properties commandMappings;
  private String language;
  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private final CommandHistory commandHistory;

  /**
   * Constructs a CommandParser with the specified turtle.
   *
   * @param turtle the turtle to execute commands on
   * @throws InvalidCommandException if there is an error initializing the command parser
   */
  public CommandParser(Turtle turtle) throws InvalidCommandException {
    this.turtle = turtle;
    //Default
    this.language = "English";
    variableController = new VariableController();
    commandRegistry = new CommandRegistry();
    commandHistory = new CommandHistory();
    this.commandMappings = loadCommandMappings();
  }

  public static void main(String[] args) {

  }

  /**
   * Parses the provided input string and returns a list of commands.
   *
   * @param input the input string containing the commands to parse
   * @return a list of parsed commands
   * @throws InvalidCommandException if there is an error parsing the commands
   */
  public List<Command> parseCommands(String input) throws InvalidCommandException {
    try {
      commandHistory.addCommand(input);
      NodeParser nodeParser = new NodeParser();
      StackParser stackParser = new StackParser(turtle, commandMappings, variableController,
          commandRegistry, language);
      //If you are encountering bugs, comment the below line out
      input = stackParser.addBrackets(input);
      GroupNode groupNode = new GroupNode(nodeParser.parseCommand(input));
//      double res = stackParser.execute(stackParser.parseToCommands(groupNode));
      List<Command> commands = stackParser.parseToCommands(groupNode);
      for (Command i : commands) {
        double res = i.execute();
      }
      return commands;
      //String command = "make %pos 70";
      //String command = "ifelse [ greater? :pos 50 ] [ fd 100 ] [ fd 200 ]";
      //String command = "ifelse [ or [ greater? :pos 50 ] [ less? :pos 10 ] ] [ ifelse
      // [ greater? :pos 200 ] [ fd 50 ] [ fd 100 ] ] [ fd 200 ]";
      //String command = "fd [ sum 10 10 ]";
      //String command = "goto [ sum 50 50 ] 100";
      //String command = "repeat [ sum :num :num ] [ fd 10 ]";
      //String command = "to ^drawSquare [ fd 50 rt 90 fd 50 rt 90 fd 50 rt 90 fd 50 ] ^drawSquare
    } catch (Exception e) {
      throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("InvalidCommand",
          language));
    }
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) throws InvalidCommandException {
    this.language = language;
    this.commandMappings = loadCommandMappings();
  }

  public CommandHistory getCommandHistory() {
    return commandHistory;
  }

  public VariableController getVariableController() {
    return variableController;
  }

  public CommandRegistry getCommandRegistry() {
    return commandRegistry;
  }

  private Properties loadCommandMappings() throws InvalidCommandException {
    Properties properties = new Properties();
    try (InputStream inputStream = getClass().getResourceAsStream(
        "/slogo/example/languages/" + language + ".properties")) {
      properties.load(inputStream);
    } catch (IOException e) {
      throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("ErrorMessage",
          language));
    }
    return properties;
  }


}
