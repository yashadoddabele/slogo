package slogo.view;

import static slogo.view.TurtlePane.TURTLE_WINDOW_HEIGHT;
import static slogo.view.TurtlePane.TURTLE_WINDOW_WIDTH;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandHistory;
import slogo.model.TurtleObserver;
import slogo.model.commands.utils.CommandRegistry;
import slogo.model.commands.utils.VariableController;
import slogo.model.parser.exceptions.InvalidCommandException;

/**
 * Gui class is an abstract class that extends Application and implements TurtleObserver. It serves
 * as the main graphical user interface for the sLogo application.
 */
public abstract class Gui extends Application implements TurtleObserver {

  public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.view.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
  public static final int SCENE_WIDTH = 1200;
  public static final int SCENE_HEIGHT = 430;
  public static final int HISTORY_WINDOW_HEIGHT = 200;
  public static final int HISTORY_WINDOW_WIDTH = 200;
  public static final int TERMINAL_WIDTH = 1000;
  public static final int TERMINAL_HEIGHT = 200;
  public static final int VARIABLE_AND_COMMAND_PANE_HEIGHT = 230;
  public static final int VARIABLE_AND_COMMAND_PANE_WIDTH = 200;
  private static final String PREFERENCES_FILE_PATH = "userPreferences.properties";
  public static String colorTheme;
  private Terminal terminal;
  private VBox historyContentBox;
  private Color historyTextColor;
  private BorderPane variableContentBox;
  private BorderPane commandContentBox;
  private TurtlePane turtlePane;
  private TurtleAnimation animation;
  private Controller controller;
  private StackPane commandContentPane;
  private StackPane variableContentPane;
  private Scene scene;
  private Stage primaryStage;

  /**
   * Constructor for Gui class.
   *
   * @param primaryStage The primary stage of the application.
   */
  public Gui(Stage primaryStage) {
    super();
    start(primaryStage);
  }

  /**
   * Displays a message using an Alert dialog.
   *
   * @param type    The type of the message.
   * @param message The message to display.
   */
  public static void showMessage(AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.turtlePane = new TurtlePane(this);
    showSplashScreen();
  }

  /**
   * Displays the splash screen with buttons to start a new session, change language, or load a
   * saved session.
   */
  private void showSplashScreen() {
    VBox splashScreen = createSplashScreen();

    primaryStage.setTitle("sLogo");
    primaryStage.setScene(new Scene(splashScreen, SCENE_WIDTH, SCENE_HEIGHT));
    centerStage(primaryStage);
    primaryStage.show();
  }

  /**
   * Executes the program by iterating through each line and updating user data.
   *
   * @param programLines The list of program lines to execute.
   */
  private void executeProgram(List<String> programLines) {
    for (String line : programLines) {
      double result = updateUserData(line);
      // Check if the command execution failed
      if (result == 0) {
        // Display an error message to the user
        showMessage(AlertType.ERROR, "Error executing program: "
            + "Invalid command encountered");
        break;
      }
    }
  }

  /**
   * Creates the splash screen with buttons for starting a new session, changing language, and
   * loading a saved session.
   *
   * @return The VBox containing the splash screen.
   */
  private VBox createSplashScreen() {
    VBox splashScreen = GuiUtil.createVboxWithStyle("-fx-background-color: lightgray;");

    Label titleLabel = GuiUtil.createLabelWithStyle("sLogo",
        "-fx-font-size: 24px; -fx-font-weight: bold;");

    Button newSessionButton = GuiUtil.createButton("Start New Session", e -> startNewSession());
    Button changeLanguageButton = GuiUtil.createButton("Choose Language", e -> changeLanguage());
    Button loadSessionButton = GuiUtil.createButton("Load Saved Session", e ->
        loadFromSplashScreen());

    splashScreen.getChildren().addAll(
        titleLabel,
        newSessionButton,
        changeLanguageButton,
        loadSessionButton
    );

    return splashScreen;
  }

  /**
   * Changes the language of the application.
   */
  private void changeLanguage() {
    List<String> choices = Arrays.asList("English", "Spanish"); // Add more languages as needed
    ChoiceDialog<String> dialog = new ChoiceDialog<>("English", choices);
    dialog.setTitle("Language Selection");
    dialog.setHeaderText("Choose a language:");
    dialog.setContentText("Language:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(language -> {
      try {
        controller.setLanguage(language);
      } catch (InvalidCommandException e) {
        throw new GuiException("Invalid Command Encountered While Setting Language: "
            + e.getMessage());
      }
      showMessage(AlertType.INFORMATION, "Language set to: " + language);
    });
  }

  /**
   * Starts a new session by setting the scene with a new session layout.
   */
  private void startNewSession() {
    primaryStage.setScene(makeScene());
  }

  /**
   * Adds a new workspace tab to the application.
   */
  public void addNewWorkspaceWindow() {
    Stage newStage = new Stage();

    newStage.setScene(makeScene());
    newStage.setTitle("New Workspace");
    newStage.show();
  }

  /**
   * Updates user data based on the provided command.
   *
   * @param command The command to update user data with.
   * @return 1 if the update is successful, 0 otherwise.
   */

  protected double updateUserData(String command) {
    try {

      turtlePane.resetAnimation();
      controller.pushCommand(command);
      turtlePane.playAnimation();

      updateCommandHistory();
      updateVariables();
      updateUserCommands();

      return 1;
    } catch (InvalidCommandException e) {
      showMessage(AlertType.ERROR, e.getMessage());
      return 0;
    }
  }

  /**
   * Loads a program from a file chosen by the user from the splash screen. Also applies loaded
   * preferences if available.
   */
  protected void loadFromSplashScreen() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Load Program");
    File selectedFile = fileChooser.showOpenDialog(primaryStage);
    if (selectedFile != null) {
      try {
        primaryStage.setScene(makeScene());
        List<String> programLines = ProgramLoader.loadProgram(selectedFile);
        executeProgram(programLines); // Execute the loaded program
      } catch (IOException e) {
        showMessage(Alert.AlertType.ERROR, "Error loading program: " + e.getMessage());
      }
    }

    try {
      UserPreferences loadedPreferences = PreferencesUtil.loadUserPreferences(
          PREFERENCES_FILE_PATH);
      applyPreferences(loadedPreferences);
      // Notify success if needed
      // showMessage(Alert.AlertType.INFORMATION, "Preferences loaded successfully.");
    } catch (IOException e) {
      showMessage(Alert.AlertType.ERROR, "Error loading preferences: " + e.getMessage());
    } catch (InvalidCommandException e) {
      throw new GuiException(e.getMessage());
    }
  }

  /**
   * Applies loaded preferences to the application.
   *
   * @param preferences The preferences to apply.
   * @throws InvalidCommandException If an invalid command is encountered.
   */
  protected void applyPreferences(UserPreferences preferences) throws InvalidCommandException {

    controller.setLanguage(preferences.getCommandLanguage());
    turtlePane.updateTurtleBackgroundColor(Color.web(preferences.getBackgroundColor()));
    turtlePane.updatePenColor(Color.web(preferences.getPenColor()));
    //bg color
    //pen color
    //turtle image
    //starting # turtles
  }

  private void updateUserCommands() {
    CommandTreeViewApp treeViewApp = new CommandTreeViewApp(getCommandRegistry().getCommandMap());
    commandContentBox = treeViewApp.returnCommandPane();
    commandContentPane.getChildren().add(commandContentBox);

  }

  /**
   * Creates the main scene for the application.
   *
   * @return The main scene of the application.
   */
  protected Scene makeScene() {
    this.terminal = new Terminal(this);
    terminal.getTerminalShape().setPrefSize(TERMINAL_WIDTH, TERMINAL_HEIGHT);
    // Create VBox for history window
    ScrollPane historyWindow = setHistoryWindow();

    historyWindow.setPrefSize(HISTORY_WINDOW_WIDTH, HISTORY_WINDOW_HEIGHT);

    HBox lowBox = new HBox();
    lowBox.getChildren().addAll(historyWindow, terminal.getTerminalShape());

    // Create BorderPane for turtle pane
    turtlePane = new TurtlePane(this);
    animation = new TurtleAnimation(this, turtlePane);
    StackPane turtleWindow = turtlePane.returnTurtleWindow(animation);

    StackPane variableAndCommandPane = setVariableAndCommandWindows();
    variableAndCommandPane.setPrefSize(VARIABLE_AND_COMMAND_PANE_WIDTH,
        VARIABLE_AND_COMMAND_PANE_HEIGHT);

    HBox highBox = new HBox();
    highBox.getChildren().addAll(variableAndCommandPane, turtleWindow);

    // Create VBox to contain turtle pane and history window with terminal
    VBox vbox = new VBox(10);
    vbox.getChildren().addAll(highBox, lowBox);

    // Set preferred size for turtle pane
    turtleWindow.setPrefSize(TURTLE_WINDOW_WIDTH, TURTLE_WINDOW_HEIGHT);

    // Create scene with VBox containing turtle pane and history window with terminal
    scene = new Scene(vbox, SCENE_WIDTH, SCENE_HEIGHT);

    applyTheme("Light Mode");

    return scene;
  }

  /**
   * Creates a ScrollPane for displaying the command history.
   *
   * @return The ScrollPane containing the command history.
   */
  private ScrollPane setHistoryWindow() {
    historyContentBox = new VBox(10);

    StackPane historyContentPane = new StackPane();
    historyContentPane.getChildren().add(historyContentBox);

    // Create a ScrollPane and set its content
    ScrollPane scrollPane = new ScrollPane(historyContentPane);
    scrollPane.setFitToWidth(true); // Allow the ScrollPane to resize horizontally
    scrollPane.setFitToHeight(true); // Allow the ScrollPane to resize vertically
    return scrollPane;
  }

  /**
   * Executes a list of program lines, updating user data for each line.
   *
   * @param programLines     The list of program lines to execute.
   * @param resetEnvironment Boolean indicating whether to reset the environment or not.
   */
  protected void execute(List<String> programLines, boolean resetEnvironment) {
    for (String line : programLines) {
      double result = updateUserData(line);
      // Check if the command execution failed
      if (result == 0) {
        // Display an error message to the user
        showMessage(AlertType.ERROR, "Error executing program: Invalid command encountered");
        break;
      }
    }
  }

  /**
   * Updates the command history displayed on the UI.
   */
  public void updateCommandHistory() {
    historyContentBox.getChildren().clear();

    for (String command : getCommandHistory().getCommandList()) {
      Text text = new Text(command);
      text.setFill(historyTextColor);
      historyContentBox.getChildren().add(text);
    }
  }

  /**
   * Method to update the variables displayed in the variable content pane.
   */
  public void updateVariables() {
    VariableTreeViewApp treeViewApp = new VariableTreeViewApp(
        getVariableController().getVariables());
    variableContentBox = treeViewApp.returnVariablePane();
    variableContentPane.getChildren().add(variableContentBox);
  }

  /**
   * Creates a stack pane containing the variable and command windows along with buttons to switch
   * between them.
   *
   * @return The stack pane containing variable and command windows.
   */
  private StackPane setVariableAndCommandWindows() {
//    variableContentPane = new StackPane();
    StackPane variablePane = new StackPane(setVariableWindow());

//    commandContentPane = new StackPane();
    StackPane commandPane = new StackPane(setCommandWindow());

    Button varWinBtn = GuiUtil.createButton("Variable Window", e -> variablePane.toFront());
    Button comWinBtn = GuiUtil.createButton("Command Window", e -> commandPane.toFront());
    Button addWorkspaceBtn = GuiUtil.createButton("Add Workspace", e -> addNewWorkspaceWindow());

    StackPane varAndComWindows = new StackPane(variablePane, commandPane);

    // Combine everything into a single layout
    VBox vbox = new VBox();
    HBox buttons = new HBox(10); // Add spacing between buttons
    buttons.getChildren().addAll(varWinBtn, comWinBtn, addWorkspaceBtn);
    vbox.getChildren().addAll(buttons);

    return GuiUtil.putAndAlignButtonsAboveWindows(vbox, varAndComWindows);
  }

  // Method to pause animation

  /**
   * Centers the stage on the primary screen.
   *
   * @param stage The stage to be centered.
   */
  private void centerStage(Stage stage) {
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
    stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
  }

  /**
   * Clears the screen and returns the distance moved by the turtle.
   *
   * @return The distance moved by the turtle after clearing the screen.
   */
  public double clearScreen() {
    double distance = GuiUtil.returnDistance(turtlePane.getXpos(), turtlePane.getYpos(), 0, 0);

    ObservableList<Node> children = turtlePane.getTurtlePane().getChildren();
    List<Node> childrenToRemove = new ArrayList<>(children);

// Remove all children except the specific one
    childrenToRemove.stream().filter(child -> child !=
        turtlePane.getTurtleAvatar().getMyTurtleAvatar()).forEach(children::remove);

    return distance;
  }

  /**
   * Applies the specified theme to the scene.
   *
   * @param theme The theme to be applied ("Light Mode" or "Dark Mode").
   */
  protected void applyTheme(String theme) {
    scene.getStylesheets().clear(); // Clear existing stylesheets
    if (theme.equals("Light Mode")) {

      scene.getStylesheets().add(
          getClass().getResource(DEFAULT_RESOURCE_FOLDER + "lightMode.css")
              .toExternalForm()); // Apply light mode stylesheet
      stylehistoryContentBox("grey", Color.BLACK);
    } else if (theme.equals("Dark Mode")) {
      scene.getStylesheets().add(
          getClass().getResource(DEFAULT_RESOURCE_FOLDER + "darkMode.css")
              .toExternalForm()); // Apply dark mode stylesheet
      stylehistoryContentBox("black", Color.WHITE);

    }
    updateCommandHistory();
  }

  private void stylehistoryContentBox(String bgColor, Color textColor) {
    historyContentBox.setStyle("-fx-background-color: " + bgColor + ";");
    setHistoryTextColor(textColor);
  }

  /**
   * Displays the help view, showing available commands.
   */
  public void showHelpView() {
    // Check if the controller is initialized
    if (controller == null) {
      showMessage(AlertType.ERROR, "Controller is not initialized");
      return;
    }

    // Retrieve the command registry from the controller
    CommandRegistry commandRegistry = controller.getCommandRegistry();
    // Check if the command registry is initialized
    if (commandRegistry == null) {
      showMessage(AlertType.ERROR, "Command registry is not initialized");
      return;
    }

    // Retrieve the command map from the command registry
    Map<String, List<Command>> commandMap = commandRegistry.getCommandMap();

    // Create a StringBuilder to build the help content
    StringBuilder helpContent = new StringBuilder("Available Commands:\n");

    // Iterate over the command map to build the help content
    for (String commandName : commandMap.keySet()) {
      helpContent.append(commandName).append("\n");
    }

    // Create an Alert dialog to display the help content
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Help");
    alert.setHeaderText(null);
    alert.setContentText(helpContent.toString());

    // Show the Alert dialog
    alert.showAndWait();
  }

  /**
   * Updates the turtle's position and orientation.
   *
   * @param xcor  The new x-coordinate of the turtle.
   * @param ycor  The new y-coordinate of the turtle.
   * @param angle The new orientation angle of the turtle.
   */
  public void updateTurtle(double xcor, double ycor, double angle, int id) {
    turtlePane.updateTurtle(xcor, ycor, angle);
  }

  /**
   * Retrieves the primary stage of the GUI.
   *
   * @return The primary stage.
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }

  /**
   * Checks if the GUI is currently showing.
   *
   * @return True if the GUI is showing, false otherwise.
   */
  public boolean isShowing() {
    return controller.turtleIsShowing();
  }

  /**
   * Checks if the pen is down.
   *
   * @return True if the pen is down, false otherwise.
   */
  public boolean penIsDown() {
    return controller.penIsDown();
  }

  /**
   * Sets the controller for the GUI.
   *
   * @param controller The controller to set.
   */
  public void setController(Controller controller) {
    this.controller = controller;
  }

  /**
   * Retrieves the terminal of the GUI.
   *
   * @return The terminal.
   */
  public Terminal getTerminal() {
    return terminal;
  }

  /**
   * Sets the text color for the command history.
   *
   * @param textColor The color to set for the command history text.
   */
  protected void setHistoryTextColor(Color textColor) {
    historyTextColor = textColor;
  }

  /**
   * Retrieves the command history.
   *
   * @return The command history.
   */
  public CommandHistory getCommandHistory() {
    return controller.getCommandHistory();
  }

  /**
   * Retrieves the variable controller.
   *
   * @return The variable controller.
   */
  public VariableController getVariableController() {
    return controller.getVariableController();
  }

  /**
   * Retrieves the command registry.
   *
   * @return The command registry.
   */
  public CommandRegistry getCommandRegistry() {
    return controller.getCommandRegistry();
  }

  /**
   * Creates a scroll pane containing the variable window.
   *
   * @return The scroll pane containing the variable window.
   */

  private ScrollPane setVariableWindow() {
    variableContentBox = new BorderPane();
    variableContentBox.setId("variable-content-box");
    variableContentBox.getChildren().add(new Text("Variable Window"));
    variableContentPane = new StackPane();
    variableContentPane.getChildren().add(variableContentBox);

    // Create a ScrollPane and set its content
    ScrollPane scrollPane = new ScrollPane(variableContentPane);
    scrollPane.setFitToWidth(true); // Allow the ScrollPane to resize horizontally
    scrollPane.setFitToHeight(true); // Allow the ScrollPane to resize vertically
    return scrollPane;
  }

  /**
   * Creates and configures the command window scroll pane.
   *
   * @return The configured ScrollPane for the command window.
   */
  private ScrollPane setCommandWindow() {
    commandContentBox = new BorderPane();
    commandContentBox.setId("command-content-box");
    commandContentBox.getChildren().add(new Text("Command Window"));
    commandContentPane = new StackPane();
    commandContentPane.getChildren().add(commandContentBox);

    // Create a ScrollPane and set its content
    ScrollPane scrollPane = new ScrollPane(commandContentPane);
    scrollPane.setFitToWidth(true); // Allow the ScrollPane to resize horizontally
    scrollPane.setFitToHeight(true); // Allow the ScrollPane to resize vertically
    return scrollPane;
  }

  /**
   * Retrieves the language from the controller.
   *
   * @return The language.
   * @throws InvalidCommandException If an invalid command is encountered while retrieving the
   *                                 language.
   */
  public String getLanguage() throws InvalidCommandException {
    return controller.getLanguage();
  }

  /**
   * Retrieves the preferences file path.
   *
   * @return The preferences file path.
   */
  public String getPreferencesFilePath() {
    return PREFERENCES_FILE_PATH;
  }

  /**
   * Retrieves the color theme.
   *
   * @return The color theme.
   */
  public String getColorTheme() {
    return colorTheme;
  }

}
