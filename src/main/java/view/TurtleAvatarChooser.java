package slogo.view;

import java.io.File;
import javafx.stage.FileChooser;

/**
 * TurtleAvatarChooser class provides a method to create a FileChooser instance for selecting turtle
 * graphics files.
 */
public class TurtleAvatarChooser {

  public static final String TURTLE_GRAPHIC_FILE_FOLDER =
      System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
          + File.separator + "resources" + File.separator + "slogo" + File.separator
          + "turtle_graphic";


  TurtleAvatarChooser() {

  }

  /**
   * Creates a FileChooser instance for selecting turtle graphics files.
   *
   * @return FileChooser instance configured for turtle graphics files selection.
   */
  public static FileChooser makeChooser() {
    FileChooser result = new FileChooser();
    result.setTitle("Turtle Image Selector");
    result.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
            "*.jpeg")
    );
    result.setInitialDirectory(new File(TURTLE_GRAPHIC_FILE_FOLDER));
    return result;
  }

}
