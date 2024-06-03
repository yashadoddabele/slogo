/**
 * A use case to edit and retrieve command history. The user should be able to get the command
 * history by clicking the history tab on the GUI, but the backend will dynamically update every
 * command to the history without user specification.
 **/
public class CommandHistoryEditUseCase {

  private SLogoApi api;

  private StringBuilder history;

  // Returns a stringified version of the history
  protected String getHistory() {
    return history.toString();
  }

  // Adds a valid command (one that didn't throw an error) to the history
  protected void addCommandToHistory(String command) {
    // Potentially, each command object will have a boolean instance variable called "topLevel",
    // which specifies if they were the top-level command of a nested command
    if (command.getTopLevel()) {
      history.append(command + "\n");
    }
  }

  // Clears the history
  protected void clearHistory() {
    history.length() = 0;
  }

  //In the API, we will have wrapper classes that do the following:
  public void getHistory() {
    String history = CommandHistory.getHistory();
    Gui.updateHistory(history);
  }

  public void sendCommand(String command) {
    //.... misc. code here
    CommandHistory.addCommandToHistory(command);
    getHistory();
  }

}