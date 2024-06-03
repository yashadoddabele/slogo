package slogo.view;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.model.api.Api;
import slogo.model.parser.exceptions.InvalidCommandException;

/**
 * Class responsible for initializing the SLogo application.
 */
public class InitiationControl {

  public InitiationControl() {

  }

  /**
   * Sets up the SLogo application.
   *
   * @param primaryStage The primary stage of the application.
   */
  public void setUpSlogo(Stage primaryStage) {
    Gui gui = new LightGui(primaryStage);
    try {
      Controller controller = new Controller(gui);
      Api api = new Api();
      controller.setApi(api);
      gui.setController(controller);
      controller.observeTurtleChanges();
    } catch (InvalidCommandException e) {
      Gui.showMessage(AlertType.ERROR, e.getMessage());
    }
  }

}
