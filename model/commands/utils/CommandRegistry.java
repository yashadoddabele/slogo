package slogo.model.commands.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.Command;

/**
 * CommandRegistry class manages the registration and retrieval of command groups. It associates
 * command names with lists of corresponding commands.
 */
public class CommandRegistry {

  // Map to store command groups, where keys are command names and values are lists of commands
  private final Map<String, List<Command>> commandMap = new HashMap<>();

  /**
   * Constructs a new CommandRegistry.
   */
  public CommandRegistry() {
  }

  /**
   * Adds a command group to the registry.
   *
   * @param name         The name of the command group.
   * @param commandGroup The list of commands to be associated with the name.
   * @return 1 indicating success.
   */
  public double addCommand(String name, List<Command> commandGroup) {
    commandMap.put(name, commandGroup);
    return 1;
  }

  /**
   * Retrieves the command group associated with the specified name.
   *
   * @param name The name of the command group to retrieve.
   * @return The list of commands associated with the name, or null if no such group exists.
   */
  public List<Command> getCommandGroup(String name) {
    return commandMap.get(name);
  }

  /**
   * Retrieves the entire command map.
   *
   * @return The map containing all registered command groups.
   */
  public Map<String, List<Command>> getCommandMap() {
    return commandMap;
  }
}

