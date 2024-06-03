package slogo.view;

import java.util.HashMap;
import java.util.Map;

/**
 * The UserPreferences class records user's preferences.
 */
public class UserPreferences {

  private String startingFileOfCode = "";
  private String commandLanguage = "English"; // Default to English for simplicity
  private String backgroundColor = "#FFFFFF"; // Default color
  private String penColor = "BLACK";
  private String turtleImage = "default.png";
  private String theme = "Light"; // Default theme
  private Map<Integer, Double> defaultIndexedValues = new HashMap<>();
  private int startingNumberOfTurtles = 1;

  /**
   * Constructs a UserPreferences object with default values. This constructor is used for
   * deserialization.
   */
  public UserPreferences() {
    // Default constructor for deserialization
  }

  // Getters and setters...

  /**
   * Returns the starting file of code.
   *
   * @return the starting file of code
   */
  public String getStartingFileOfCode() {
    return startingFileOfCode;
  }

  /**
   * Sets the starting file of code.
   *
   * @param startingFileOfCode the starting file of code
   */
  public void setStartingFileOfCode(String startingFileOfCode) {
    this.startingFileOfCode = startingFileOfCode;
  }

  /**
   * Returns the command language.
   *
   * @return the command language
   */
  public String getCommandLanguage() {
    return commandLanguage;
  }

  /**
   * Sets the command language.
   *
   * @param commandLanguage the command language
   */
  public void setCommandLanguage(String commandLanguage) {
    this.commandLanguage = commandLanguage;
  }

  /**
   * Returns the background color.
   *
   * @return the background color
   */
  public String getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Sets the background color.
   *
   * @param backgroundColor the background color
   */
  public void setBackgroundColor(String backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * Returns the pen color.
   *
   * @return the pen color
   */
  public String getPenColor() {
    return penColor;
  }

  /**
   * Sets the pen color.
   *
   * @param penColor the pen color
   */
  public void setPenColor(String penColor) {
    this.penColor = penColor;
  }

  /**
   * Returns the turtle image.
   *
   * @return the turtle image
   */
  public String getTurtleImage() {
    return turtleImage;
  }

  /**
   * Sets the turtle image.
   *
   * @param turtleImage the turtle image
   */
  public void setTurtleImage(String turtleImage) {
    this.turtleImage = turtleImage;
  }

  /**
   * Returns the theme.
   *
   * @return the theme
   */
  public String getTheme() {
    return theme;
  }

  /**
   * Sets the theme.
   *
   * @param theme the theme
   */
  public void setTheme(String theme) {
    this.theme = theme;
  }

  /**
   * Returns the default indexed values.
   *
   * @return the default indexed values
   */
  public Map<Integer, Double> getDefaultIndexedValues() {
    return defaultIndexedValues;
  }

  /**
   * Sets the default indexed values.
   *
   * @param defaultIndexedValues the default indexed values
   */
  public void setDefaultIndexedValues(Map<Integer, Double> defaultIndexedValues) {
    this.defaultIndexedValues = defaultIndexedValues;
  }

  /**
   * Returns the starting number of turtles.
   *
   * @return the starting number of turtles
   */
  public int getStartingNumberOfTurtles() {
    return startingNumberOfTurtles;
  }

  /**
   * Sets the starting number of turtles.
   *
   * @param startingNumberOfTurtles the starting number of turtles
   */
  public void setStartingNumberOfTurtles(int startingNumberOfTurtles) {
    this.startingNumberOfTurtles = startingNumberOfTurtles;
  }
}
