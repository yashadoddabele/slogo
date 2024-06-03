package slogo.view;

import javafx.scene.control.Alert.AlertType;

/**
 * The GuiException class represents an exception specific to the GUI.
 */
public class GuiException extends RuntimeException {

  /**
   * Constructs a new GuiException with the specified message and cause.
   *
   * @param message The message to be displayed for the exception.
   * @param e       The cause of the exception.
   */
  public GuiException(String message, Exception e) {
    super(message, e);
    Gui.showMessage(AlertType.ERROR, message);
  }

  /**
   * Constructs a new GuiException with the specified message.
   *
   * @param message The message to be displayed for the exception.
   */
  public GuiException(String message) {
    super(message);
    Gui.showMessage(AlertType.ERROR, message);
  }

}
