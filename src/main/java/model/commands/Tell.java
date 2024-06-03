//package slogo.model.commands;
//
//import slogo.model.Command;
//import slogo.model.Turtle;
//import slogo.model.TurtleBale;
//import slogo.model.commands.utils.CommandRegistry;
//import slogo.model.commands.utils.VariableController;
//import slogo.model.parser.stackparser.ParameterStack;
//import slogo.model.parser.stackparser.ParameterStackElement;
//
///**
// * Tell Command Implementation.
// */
//public class Tell implements Command {
//
//  private final TurtleBale turtles;
//  private final VariableController variableController;
//  private final CommandRegistry commandRegistry;
//  private ParameterStackElement id;
//
//  /**
//   * Construct command without parameters.
//   *
//   * @param turtles            the turtle object to execute commands on
//   * @param variableController controller for managing variables
//   * @param commandRegistry    registry for storing available commands
//   */
//  public Tell(TurtleBale turtles, VariableController variableController,
//      CommandRegistry commandRegistry) {
//    this.turtles = turtles;
//    this.variableController = variableController;
//    this.commandRegistry = commandRegistry;
//  }
//
//  @Override
//  public double execute() {
//    int turtleId = (int) executeSubCommandList(id);
//    turtles.addTurtle(turtleId);
//    Turtle turtle = turtles.getTurtlebyId(turtleId);
//    if (turtle != null) {
//      turtle.setActivity(true);
//      return executeSubCommandList(id);
//    }
//    return 0;
//  }
//
//  @Override
//  public int numV() {
//    return 1;
//  }
//
//  @Override
//  public void setParameters(ParameterStack parameters) {
//    id = parameters.pop();
//  }
//
//}
