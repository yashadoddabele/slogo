package slogo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import slogo.model.Command;
import slogo.model.CommandParser;
import slogo.model.Turtle;
import slogo.model.api.Api;
import slogo.model.parser.exceptions.InvalidCommandException;
import slogo.view.Gui;
import slogo.view.LightGui;
import util.DukeApplicationTest;

public class ControllerTest extends DukeApplicationTest {

  private CommandParser parser;
  private Api api;
  private Turtle turtle;
  private Controller controller;
  private LightGui gui;

  @Override
  public void start(Stage stage) {
    try {
      turtle = new Turtle(0, 0, 0, true, true, 1);
      gui = new LightGui(stage);
      controller = new Controller(gui);
      api = new Api();
      controller.setApi(api);
      gui.setController(controller);
      parser = new CommandParser(turtle);
    } catch (InvalidCommandException e) {
      Gui.showMessage(AlertType.ERROR, e.getMessage());
    }
  }

  @Test
  void testController() throws InvalidCommandException {
    List<Command> cmds = controller.pushCommand("fd 50");
    double res = 0;
    for (Command cmd : cmds) {
      res = cmd.execute();
    }
    try {
      controller.setLanguage("Spanish");
    } catch (InvalidCommandException e) {
    }
    assertEquals(res, 50);
  }

}
