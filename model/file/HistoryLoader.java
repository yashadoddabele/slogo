package slogo.model.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import slogo.model.CommandHistory;

/**
 * Loads command history from a file.
 */
public class HistoryLoader {

  private final String filePath;

  /**
   * Constructs a HistoryLoader with the specified file path.
   *
   * @param filePath the file path to read the command history from
   */
  public HistoryLoader(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Reads command history from the file and returns it as a CommandHistory object.
   *
   * @return the CommandHistory object containing the read command history
   */
  public CommandHistory readHistoryFromFile() {
    CommandHistory commandHistory = new CommandHistory();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        commandHistory.addCommand(line.trim());
      }
      System.out.println("Command history has been read from file: " + filePath);
    } catch (IOException e) {
      System.err.println("Error reading command history from file: " + e.getMessage());
    }
    return commandHistory;
  }
}
