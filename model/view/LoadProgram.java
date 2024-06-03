package slogo.view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;

/**
 * The LoadProgram class facilitates the loading of a program file in the SLogo IDE.
 */
public class LoadProgram {

  private final Gui myGui;

  /**
   * Constructs a LoadProgram object with the specified GUI.
   *
   * @param gui the GUI associated with the LoadProgram object
   */
  public LoadProgram(Gui gui) {
    myGui = gui;
  }

  /**
   * Prompts the user to select a program file to load and offers the option
   * to reset the environment. If a file is selected, it loads the program
   * and resets the environment based on the user's choice.
   */
  public void loadProgramExistingIde() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Load Program");
    File selectedFile = fileChooser.showOpenDialog(myGui.getPrimaryStage());
    if (selectedFile != null) {
      // Ask the user if they want to reset the environment
      Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
      confirmationAlert.setTitle("Load Program Confirmation");
      confirmationAlert.setHeaderText(
          "Do you want to reset the environment before loading the program?");
      confirmationAlert.setContentText("Choose your option:");

      ButtonType resetButtonType = new ButtonType("Reset Environment");
      ButtonType continueButtonType = new ButtonType("Continue from Current State");

      confirmationAlert.getButtonTypes()
          .setAll(resetButtonType, continueButtonType, ButtonType.CANCEL);

      Optional<ButtonType> result = confirmationAlert.showAndWait();
      if (result.isPresent()) {
        if (result.get() == resetButtonType) {
          myGui.loadFromSplashScreen();
        } else if (result.get() == continueButtonType) {
          loadProgram(selectedFile, false);
        }
      }
    }
  }

  /**
   * Loads the specified program file and executes it with the option to
   * reset the environment.
   *
   * @param selectedFile     the file containing the program to load
   * @param resetEnvironment true if the environment should be reset before loading
   *                         the program, false otherwise
   */
  private void loadProgram(File selectedFile, boolean resetEnvironment) {
    try {
      List<String> programLines = ProgramLoader.loadProgram(selectedFile);
      myGui.execute(programLines, resetEnvironment);
    } catch (IOException e) {
      Gui.showMessage(AlertType.ERROR, "Error loading program: " + e.getMessage());
    }
  }
}
