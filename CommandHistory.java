package slogo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command history, storing a list of commands.
 */
public class CommandHistory {

  private final List<String> commandList;

  /**
   * Constructs a CommandHistory with an empty list of commands.
   */
  public CommandHistory() {
    this.commandList = new ArrayList<>();
  }

  public void addCommand(String command) {
    commandList.add(command);
  }

  /**
   * Get command based on index.
   *
   * @param index the index of the command to retrieve
   * @return the command at the specified index, or null if the index is out of bounds
   */
  public String getCommand(int index) {
    if (index >= 0 && index < commandList.size()) {
      return commandList.get(index);
    } else {
      return null;
    }
  }

  public List<String> getCommandList() {
    return commandList;
  }

  public int size() {
    return commandList.size();
  }

  public void clearHistory() {
    commandList.clear();
  }

  /**
   * Print the command history in a readable way.
   */
  public void printHistory() {
    System.out.println("Command History:");
    for (int i = 0; i < commandList.size(); i++) {
      System.out.println((i + 1) + ". " + commandList.get(i));
    }
  }
}
