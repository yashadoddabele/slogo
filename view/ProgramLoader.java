package slogo.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for loading programs from a file.
 */
public class ProgramLoader {

  /**
   * Loads the program from the specified file.
   *
   * @param file the file containing the program
   * @return a list of strings representing each line of the program
   * @throws IOException if an I/O error occurs while reading the file
   */
  public static List<String> loadProgram(File file) throws IOException {
    List<String> programLines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        programLines.add(line);
      }
    }
    return programLines;
  }
}

