/**
 * Use case representing when a user may want to save the current configuration of the file.
 */
public class SaveFileUseCase {

  private SLogoApi api;

  // Save a file to the user's computer
  public void saveFile() {
    try {
      String history = api.getHistory();
      //For each new line of the String, populate it into a file

      //Set the extension to be .slogo

      // Let the user choose a place to save the file
      File selectedFile = fileChooser.showSaveDialog(null);
      if (selectedFile != null) {
        StreamResult result = new StreamResult(selectedFile);
        transformer.transform(source, result);
      }
      showMessage("Successfully saved file!");
    }
    // Catch any exceptions
    catch (Exception e) {
      e.printStackTrace();
      showMessage("An error occurred");
    }
  }

  protected void showMessage() {
    //Have a method that displays some type of alert message here
  }

  //In the API, we have the following public method to interact with this class
  public void saveFile() {
    FileManager.saveFile();
  }

}