package slogo.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * PreferencesUtil class provides utility methods for saving and loading user preferences.
 */
public class PreferencesUtil {

  /**
   * Saves the user preferences to the specified file path.
   *
   * @param userPreferences The user preferences to be saved.
   * @param filePath        The file path where the preferences will be saved.
   * @throws IOException If an I/O error occurs while saving the preferences.
   */
  public static void saveUserPreferences(UserPreferences userPreferences, String filePath)
      throws IOException {
    Properties props = new Properties();

    // Example of safely setting properties, checking for null values
    safeSetProperty(props, "startingFileOfCode", userPreferences.getStartingFileOfCode());
    safeSetProperty(props, "commandLanguage", userPreferences.getCommandLanguage());
    safeSetProperty(props, "backgroundColor", userPreferences.getBackgroundColor());
    safeSetProperty(props, "turtleImage", userPreferences.getTurtleImage());
    safeSetProperty(props, "theme", userPreferences.getTheme());
    safeSetProperty(props, "penColor", userPreferences.getPenColor());
    // Convert integer to string safely, assuming this value is never null (primitive int)
    props.setProperty("startingNumberOfTurtles",
        String.valueOf(userPreferences.getStartingNumberOfTurtles()));

    try (FileOutputStream fos = new FileOutputStream(filePath)) {
      props.store(fos, "User Preferences");
    }
  }

  /**
   * Utility method to safely set properties, checking for null values.
   *
   * @param props The Properties object to which the property will be set.
   * @param key   The key of the property.
   * @param value The value of the property.
   */
  private static void safeSetProperty(Properties props, String key, String value) {
    if (value != null) {
      props.setProperty(key, value);
    }
  }

  /**
   * Loads user preferences from the specified file path.
   *
   * @param filePath The file path from which to load the preferences.
   * @return The user preferences loaded from the file.
   * @throws IOException If an I/O error occurs while loading the preferences.
   */
  public static UserPreferences loadUserPreferences(String filePath) throws IOException {
    Properties props = new Properties();

    try (FileInputStream fis = new FileInputStream(filePath)) {
      props.load(fis);
    }

    UserPreferences preferences = new UserPreferences();
    preferences.setStartingFileOfCode(props.getProperty("startingFileOfCode"));
    preferences.setCommandLanguage(props.getProperty("commandLanguage"));
    preferences.setBackgroundColor(props.getProperty("backgroundColor"));
    preferences.setTurtleImage(props.getProperty("turtleImage"));
    preferences.setTheme(props.getProperty("theme"));
    preferences.setStartingNumberOfTurtles(
        Integer.parseInt(props.getProperty("startingNumberOfTurtles", "1")));
    preferences.setPenColor(props.getProperty("penColor"));
    return preferences;
  }
}
