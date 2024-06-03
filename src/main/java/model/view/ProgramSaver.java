package slogo.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Utility class to save a list of SLOGO commands to a file.
 */
public class ProgramSaver {

  /**
   * Saves the provided list of commands to a file.
   *
   * @param commands The list of SLOGO commands to be saved.
   * @param stage    The stage used to display the file save dialog.
   */
  public static void saveProgram(List<String> commands, Stage stage) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save Program");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("SLOGO Files", "*.slogo"));
    fileChooser.setInitialFileName("program.slogo");

    try {
      String filePath = fileChooser.showSaveDialog(stage).getPath();
      BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
      for (String command : commands) {
        writer.write(command);
        writer.newLine();
      }
      Gui.showMessage(AlertType.INFORMATION, "Program saved successfully to " + filePath);
    } catch (IOException e) {
      Gui.showMessage(AlertType.ERROR, "Error saving program: " + e.getMessage());
    } catch (NullPointerException e) {
      Gui.showMessage(AlertType.WARNING, "No File Saved");
    }


  }
}
