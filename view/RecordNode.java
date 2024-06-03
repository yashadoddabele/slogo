package slogo.view;

/**
 * Represents a node in a record, containing information about a command.
 */
public class RecordNode {

  private final String myRecord;
  // You can add more properties as needed (e.g., execution status)

  /**
   * Constructs a RecordNode with the specified record.
   *
   * @param record The record containing information about a command.
   */
  public RecordNode(String record) {
    myRecord = record;
  }

  /**
   * Gets the command contained in this RecordNode.
   *
   * @return The command.
   */
  public String getCommand() {
    return myRecord;
  }

}

