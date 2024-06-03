package slogo.model.parser.exceptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ErrorHandling class provides methods for retrieving error messages based on the given type and
 * language.
 */

public class ErrorHandling {

  /**
   * Retrieves an error message based on the specified type and language.
   *
   * @param type     The type of error message to retrieve.
   * @param language The language in which the error message should be retrieved.
   * @return The error message corresponding to the given type and language.
   * @throws InvalidCommandException If an error occurs while retrieving the error message.
   */
  public static String retrieveErrorMessage(String type, String language)
      throws InvalidCommandException {
    Properties properties = new Properties();
    try (InputStream inputStream = ErrorHandling.class.getResourceAsStream(
        "/slogo/example/languages/" + language + ".properties")) {
      properties.load(inputStream);
      String value = properties.getProperty(type);
      return value;
    } catch (IOException e) {
      throw new InvalidCommandException(ErrorHandling.retrieveErrorMessage("ErrorMessage",
          language));
    }
  }
}
