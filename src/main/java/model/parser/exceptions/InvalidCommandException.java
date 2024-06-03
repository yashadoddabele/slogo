package slogo.model.parser.exceptions;

/**
 * InvalidCommandException class represents an exception that is thrown when a command is invalid.
 * It extends the Exception class.
 */
public class InvalidCommandException extends Exception {

  /**
   * Constructs a new InvalidCommandException with the specified error message.
   *
   * @param errorMessage The error message associated with the exception.
   */
  public InvalidCommandException(String errorMessage) {
    super(errorMessage);
  }

}
