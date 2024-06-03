/**
 * Use case that represents a functionality the Parser class will have to unnest nested commands.
 */
public class ParserUnnestsCommandUseCase {

  protected List<String> unnestCommand(String command) {
    List<String> commands = new ArrayList<>();
    int index = command.length();
    while (index >= 0) {
      //Do regex parsing, etc
      int endingIndex = findCommand(index);
      String comm = command.subString(endingIndex, index);
      commands.add(comm);
      index = endingIndex;
      index--;
    }
  }

  protected int findCommand(int StartingIndex) {
    //Some method to determine where a command begins
  }

  //In the API, this is all done in the sendCommand()
  sendCommand(String command) {
    parser.parseCommand(command);
    //parseCommand calls unnestCommand, which calls findCommand
  }

}