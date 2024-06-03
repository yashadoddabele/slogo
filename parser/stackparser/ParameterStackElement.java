package slogo.model.parser.stackparser;

import java.util.List;
import slogo.model.Command;

/**
 * Represents an element in the parameter stack used for evaluating commands. This class
 * encapsulates different types of values including double, list of commands, and string values.
 */
public class ParameterStackElement {

  private Double doubleValue;
  private List<Command> commandListValue;
  private String stringValue;

  public ParameterStackElement(Double doubleValue) {
    this.doubleValue = doubleValue;
  }

  public ParameterStackElement(List<Command> commandListValue) {
    this.commandListValue = commandListValue;
  }

  public ParameterStackElement(String stringValue) {
    this.stringValue = stringValue;
  }

  public Double getDoubleValue() {
    return doubleValue;
  }

  public List<Command> getCommandListValue() {
    return commandListValue;
  }

  public String getStringValue() {
    return stringValue;
  }
}
