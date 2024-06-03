package slogo.model.file;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import slogo.model.CommandHistory;

/**
 * Writes command history to a file.
 */
public class HistoryWriter {

  private final String filePath;

  /**
   * Constructs a HistoryWriter with the specified file path.
   *
   * @param filePath the file path to write the command history to
   */
  public HistoryWriter(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Writes the provided command history to the file.
   *
   * @param commandHistory the CommandHistory object containing the command history to write
   */
  public void writeHistoryToFile(CommandHistory commandHistory) {
    try (FileWriter writer = new FileWriter(filePath)) {
      List<String> commandList = commandHistory.getCommandList();
      for (String command : commandList) {
        writer.write(command + "\n");
      }
      System.out.println("Command history has been written to file: " + filePath);
    } catch (IOException e) {
      System.err.println("Error writing command history to file: " + e.getMessage());
    }
  }

}
