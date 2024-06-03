/**
 * A use case representing how a parser attempts to parse an incorrectly formatted command, and it
 * throws an exception to be handled by the API or a different wrapper class.
 */

public class ParserFindsErrorWithCommandUseCase {

  protected boolean parseCommand(String command) {
    try {
      // Apply regex rules here, split the command (see the demo in /src.../models/CommandParser
      // Use Java reflection here to create the command object
      return true;
    } catch (Exception e) {
      FileManager.showMessage("Your command is formatted wrong.");
      return false;
    }
  }

  protected boolean validateCommand() {
    //Returns true if a command is a valid Command subclass type, false if not
  }

}