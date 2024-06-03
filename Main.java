package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.view.InitiationControl;

/**
 * This is the entrance of the program.
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    InitiationControl initControl = new InitiationControl();
    initControl.setUpSlogo(primaryStage);
  }


}
