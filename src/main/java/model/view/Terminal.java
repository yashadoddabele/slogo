package slogo.view;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;
import slogo.model.CommandHistory;

/**
 * NewTerminal class represents a terminal component for user input and output. It contains methods
 * to interact with the terminal.
 */
public class Terminal {

  private static final String PROMPT = "$ ";
  private final ScrollPane terminalScrollPane;
  private final TextArea terminal;
  private String curInput;
  private CommandHistory commandHistory;
  private int historyIndex;
  private String currentInput;

  /**
   * Constructs a new NewTerminal component with the specified GUI.
   *
   * @param gui The graphical user interface.
   */
  public Terminal(Gui gui) {
    this.terminal = new TextArea();
    terminalScrollPane = new ScrollPane(this.terminal);

    terminal.setText(PROMPT);
    terminal.setEditable(true);
    terminal.setWrapText(true);

    terminal.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
      int caretPosition = terminal.getCaretPosition();
      int lastPromptIndex = terminal.getText().lastIndexOf(PROMPT) + PROMPT.length();

      if (event.getCode() == KeyCode.BACK_SPACE) {
        if (caretPosition <= lastPromptIndex) {
          event.consume();
        }
      } else if (event.getCode() == KeyCode.DELETE) {
        if (caretPosition < lastPromptIndex) {
          event.consume();
        }
      } else if (event.getCode() == KeyCode.LEFT) {
        if (caretPosition <= lastPromptIndex) {
          event.consume();
        }
      } else if (event.getCode() == KeyCode.ENTER) {
        event.consume();
        setTerminalInput(lastPromptIndex);
        gui.updateUserData(curInput);
        terminal.appendText("\n" + PROMPT);
        terminal.positionCaret(terminal.getText().length());
        commandHistory = gui.getCommandHistory();
        historyIndex = commandHistory.size();
      } else if (event.getCode() == KeyCode.UP) {
        if (commandHistory != null) {
          event.consume();
          if (historyIndex == commandHistory.size()) {
            historyIndex = commandHistory.size() - 1;
            currentInput = terminal.getText().substring(getLastPromptIndex()).trim();
          } else if (historyIndex > 0) {
            historyIndex--;
          }
          updateTerminalWithHistory();
        }
      } else if (event.getCode() == KeyCode.DOWN) {
        if (commandHistory != null) {
          if (historyIndex != commandHistory.size() && historyIndex < commandHistory.size() - 1) {
            historyIndex++;
            updateTerminalWithHistory();
          } else if (historyIndex == commandHistory.size() - 1) {
            historyIndex = commandHistory.size();
            updateTerminalWithCurrentInput();
          }
        }
      }
    });
    terminal.requestFocus();
    terminal.positionCaret(terminal.getText().length());
  }

  protected ScrollPane getTerminalShape() {

    expandTerminalInPane();

    return terminalScrollPane;
  }

  private void expandTerminalInPane() {
    // Ensure that the terminal resizes with the ScrollPane
    this.terminal.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

    // Allow the terminal to grow as needed to fill the available space
    terminalScrollPane.setFitToWidth(true);
    terminalScrollPane.setFitToHeight(true);
  }


  private int getLastPromptIndex() {
    return terminal.getText().lastIndexOf(PROMPT) + PROMPT.length();
  }

  private void updateTerminalWithHistory() {
    terminal.replaceText(getLastPromptIndex(), terminal.getLength(),
        commandHistory.getCommand(historyIndex));
  }

  private void updateTerminalWithCurrentInput() {
    terminal.replaceText(getLastPromptIndex(), terminal.getLength(), currentInput);
  }


  private void setTerminalInput(int lastPromptIndex) {
    String text = terminal.getText();
    this.curInput = text.substring(lastPromptIndex).trim();
  }
}
