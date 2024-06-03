package slogo.model.parser.stackparser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.exceptions.ErrorHandling;
import slogo.model.parser.exceptions.InvalidCommandException;
import slogo.model.parser.nodeparser.CustomizeCommandNode;
import slogo.model.parser.nodeparser.CustomizeVariableNode;
import slogo.model.parser.nodeparser.DoubleNode;
import slogo.model.parser.nodeparser.GroupNode;
import slogo.model.parser.nodeparser.Node;
import slogo.model.parser.nodeparser.StrNode;
import slogo.model.parser.nodeparser.VariableNode;

/**
 * Parses a GroupNode into a list of executable commands.
 */
public class StackParser {

  private final Turtle turtle;
  private final Properties commandMappings;

  private final VariableController variableController;
  private final CommandRegistry commandRegistry;
  private ParameterStack parameterStack;
  private Stack<Integer> parameterNumStack;
  private Stack<Command> commandStack;
  private final String language;

  /**
   * Constructs a StackParser with the provided dependencies.
   *
   * @param turtle             the turtle objects to execute commands on
   * @param commandMappings    command mappings for translating command names
   * @param variableController controller for managing variables
   * @param commandRegistry    registry for storing available commands
   * @param language           the language identifier
   */
  public StackParser(Turtle turtle,
      Properties commandMappings, VariableController variableController, CommandRegistry
      commandRegistry, String language) {
    this.turtle = turtle;
    this.commandMappings = commandMappings;
    this.variableController = variableController;
    this.commandRegistry = commandRegistry;
    this.language = language;
  }

  /**
   * Parses a GroupNode into a list of executable commands.
   *
   * @param groupNode the GroupNode to parse
   * @return a list of commands parsed from the GroupNode
   * @throws InvalidCommandException if there is an error parsing the commands
   */
  public List<Command> parseToCommands(GroupNode groupNode) throws InvalidCommandException {
    try {
      List<Command> commandList = new ArrayList<>();
      Iterator<Node> iterator = groupNode.iterator();
      parameterStack = new ParameterStack();
      parameterNumStack = new Stack<>();
      commandStack = new Stack<>();
      // iterate over the nodes in the group node
      while (iterator.hasNext()) {
        Node node = iterator.next();
        switch (node.type()) {
          // add a double number as a parameter to parameter stack
          case 0:
            DoubleNode doubleNode = (DoubleNode) node;
            parameterStack.pushDouble(doubleNode.getValue());
            decrementParameterNumStackPeek();
            System.out.println(parameterStack.peek().getDoubleValue());
            break;
          // identify the str node as a command
          // push it in command stack
          case 1:
            StrNode strNode = (StrNode) node;
//            Command command = reflect(findCanonicalName(strNode.getValue()));
            Command command = reflect(strNode.getValue());
            commandStack.push(command);
            parameterNumStack.push(command.numV());
            System.out.println(strNode.getValue());
            break;
          // add a customize variable to parameter stack
          // ex :num
          case 2:
            VariableNode variableNode = (VariableNode) node;
            ParameterStackElement variableValue = variableController.getVariable(
                variableNode.getValue());
            parameterStack.pushElement(variableValue);
            decrementParameterNumStackPeek();
            System.out.println(parameterStack.peek().getDoubleValue());
            break;
          // if there is a group node inside the current group node,
          // create another stack parser and get the returned command list
          case 3:
            GroupNode newGroupNode = (GroupNode) node;
            StackParser newStackParser = new StackParser(turtle, commandMappings,
                variableController, commandRegistry, language);
            List<Command> newCommandList = newStackParser.parseToCommands(newGroupNode);
            parameterStack.pushCommandList(newCommandList);
            decrementParameterNumStackPeek();
            break;
          // the customize parameter is initialized
          // ex: %num
          case 4:
            CustomizeVariableNode customizeVariableNode = (CustomizeVariableNode) node;
            parameterStack.pushString(customizeVariableNode.getValue());
            decrementParameterNumStackPeek();
            break;
          // the customize command is initialized or called
          // ex: ^drawSquare
          case 5:
            CustomizeCommandNode customizeCommandNode = (CustomizeCommandNode) node;
            if (parameterNumStack.isEmpty()) {
              for (Command commandx : commandRegistry.getCommandGroup(
                  customizeCommandNode.getValue())) {
                commandStack.push(commandx);
              }
            } else {
              parameterStack.pushString(customizeCommandNode.getValue());
              decrementParameterNumStackPeek();
            }
            break;
          default:
            throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("InvalidCommand",
                language));
        }
        // check the parameters are ready to set to the command
        if (!parameterNumStack.isEmpty() && parameterNumStack.peek() == 0
            && commandStack.size() != 0) {
          parameterNumStack.pop();
          Command command = commandStack.peek();
          int numParams = command.numV();
          ParameterStack subParameterStack = new ParameterStack();
          for (int i = 0; i < numParams; i++) {
            ParameterStackElement parameterStackElement = parameterStack.pop();
            subParameterStack.pushElement(parameterStackElement);
          }
          command.setParameters(subParameterStack);
        }
      }
      // if there is no command in command stack
      // but there are list of commands in parameter stack
      // return the list of commands in parameter stack
      // ex: [ fd 50 ]
      if (commandStack.size() == 0) {
        while (!parameterStack.isEmpty()) {
          commandList.addAll(parameterStack.pop().getCommandListValue());
        }
      } else {
        // the program should raise an error if there are parameters left
        if (parameterNumStack.size() != 0) {
          throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("InvalidCommand",
              language));
        }
        //else return the commands in command stack with parameter set
        while (!commandStack.isEmpty()) {
          Command currentCommand = commandStack.pop();
          commandList.add(currentCommand);
        }
      }
      Collections.reverse(commandList);
      return commandList;
    } catch (Exception e) {
      throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("InvalidCommand",
          language));
    }
  }


  /**
   * Decrements the parameter count of the top element in the parameter number stack.
   */
  private void decrementParameterNumStackPeek() {
    if (parameterNumStack.size() != 0) {
      int topElement = parameterNumStack.peek();
      parameterNumStack.pop();
      parameterNumStack.push(topElement - 1);
    }
  }

  /**
   * Reflectively creates a command instance based on the command name.
   *
   * @param commandName the name of the command
   * @return the instantiated command object
   * @throws InvalidCommandException if there is an error creating the command
   */
  private Command reflect(String commandName) throws InvalidCommandException {
    try {
      String canonicalName = findCanonicalName(commandName);
      if (!canonicalName.isEmpty()) {
        Class<?> clazz = Class.forName("slogo.model.commands." + canonicalName);
        Command cmd = (Command) clazz.getDeclaredConstructor(turtle.getClass(),
            variableController.getClass(), commandRegistry.getClass()).newInstance(turtle,
            variableController, commandRegistry);
        return cmd;
      }
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
             InvocationTargetException | ClassNotFoundException e) {
      throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("InvalidCommand",
          language));
    }
    return null;
  }

  /**
   * The canonical name of the command.
   *
   * @param commandName the user-friendly name of the command
   * @return the canonical name of the command
   */
  private String findCanonicalName(String commandName) {
    commandName = commandName.toLowerCase().replaceAll(" ", "");
    String canonicalName = "";
    for (String key : commandMappings.stringPropertyNames()) {
      String value = commandMappings.getProperty(key);
      String[] parts = value.split("\\|");
      for (String part : parts) {
        if (part.equals(commandName)) {
          canonicalName = key;
          break;
        }
      }
      if (!canonicalName.isEmpty()) {
        break;
      }
    }
    return canonicalName;
  }

  /**
   * Adds brackets to the input string to ensure it's valid with brackets.
   *
   * @param input the input string to format
   * @return the formatted input string
   */
  public String addBrackets(String input) {
    try {
      input = input.replaceAll("\\s{2,}", " ");
      System.out.println("input: " + input);
      String[] tokens = input.split("\\s+");
      Stack<StrNode> commands = new Stack<>();
      StringBuilder result = new StringBuilder();
      Map<StrNode, Integer> paramsLeft = new HashMap<>();
      for (String token : tokens) {
        boolean notParam = checkBracketsOrCommand(token, result, commands, paramsLeft);
        if (!notParam) {
          checkParams(token, result, commands, paramsLeft);
        }
      }
      //Adds the rest of the ] when needed
      while (!commands.isEmpty()) {
        result.append(" ] ");
        commands.pop();
      }
      String res = result.toString().trim().replaceAll("\\s{2,}", " ");
      System.out.println(res.trim());
      return res.trim();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private boolean checkBracketsOrCommand(String token, StringBuilder result,
      Stack<StrNode> commands, Map<StrNode, Integer> paramsLeft) throws InvalidCommandException {
    try {
      if (token.charAt(0) == '[' || token.charAt(0) == ']') {
        result.append(" " + token.charAt(0) + " ");
        return true;
        //It's a command
      } else if (!findCanonicalName(token).isEmpty()) {
        int paramsCount = reflect(token).numV();
        if (paramsCount > 0) {
          StrNode command = new StrNode(token);
          commands.push(command);
          paramsLeft.put(command, paramsCount);
          result.append(" [ ").append(token);
        } else if (paramsCount == 0) {
          result.append(" [ " + token + " ] ");
        }
        return true;
      }
      return false;
    }
    catch (Exception e) {
      throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("ErrorMessage",
          language));
    }
  }

  private void checkParams(String token, StringBuilder result,
      Stack<StrNode> commands, Map<StrNode, Integer> paramsLeft) {
    //It's a param or variable
    if (Character.isDigit(token.charAt(0)) || !Character.isLetter(token.charAt(0))) {
      result.append(" ").append(token);
      if (!commands.isEmpty()) {
        //decrement params left needed
        paramsLeft.put(commands.peek(), paramsLeft.get(commands.peek()) - 1);
        if (paramsLeft.get(commands.peek()) == 0) {
          // We can now pop the command
          result.append(" ] ");
          commands.pop();
          //Since this was a completed command, it counts as a param for the previous command
          if (!commands.isEmpty()) {
            paramsLeft.put(commands.peek(), paramsLeft.get(commands.peek()) - 1);
          }
        }
      }
    }
  }
}
