package slogo.view;

//public class GuiTest extends DukeApplicationTest {
//
//  // keep only if needed to call application methods in tests
//  private Gui myGui;
//  // keep GUI components used in multiple tests
//  private ImageView myTurtle;
//  private NewTerminal myTerminal;
//
//
//  // this method is run BEFORE EACH test to set up application in a fresh state
//  @Override
//  public void start(Stage stage) {
//    try {
//      myGui = new LightGui(stage);
//      Controller controller = new Controller(myGui);
//      Api api = new Api();
//
//      controller.setApi(api);
//      myGui.setController(controller);
//
//      myTerminal = myGui.getTerminal();
//      myTurtle = lookup("#turtleAvatar").query();
//
//    } catch (InvalidCommandException e) {
//      myGui.showMessage(AlertType.ERROR, e.getMessage());
//    }
//
//  }
//
//
//  // tests for different kinds of UI components
//  @Test
//  void testTerminalInput() {
//    String expected = "Writing to NewTerminal";
//    // GIVEN, the program first starts up
//    // WHEN, `Writing to NewTerminal` is typed it appears on the terminal
//    writeInputTo(myTerminal.terminal, expected);
//    // THEN, check terminal text has been updated to match input
//    assertTerminalText(expected);
//  }
//
//
//  @Test
//  void testForwardMovement() throws InterruptedException {
//    String forwardCommand = "forward 50";
//    // GIVEN, the program starts up and the turtle has an initial position facing right
//    double expected = myGui.getTurtleAvatar().getLayoutX() + 50;
//    // WHEN, `forward 50` is typed into the terminal and ENTER key is pressed
//    appendInputTo(myTerminal.terminal, forwardCommand);
//    push(KeyCode.ENTER);
//    // THEN, check whether the turtle avatar's position is changed by 50 in the forward direction
//    assertEquals(expected, myGui.getxPos());
//  }
//
//  @Test
//  void testBackwardMovement() {
//    String forwardCommand = "backward 50\n";
//    // GIVEN, the program starts up and the turtle has an initial position facing right
//    double expected = myTurtle.getLayoutX() - 50;
//    // WHEN, `backward 50` is typed into the terminal and ENTER key is pressed
//    appendInputTo(myTerminal.terminal, forwardCommand);
//    // THEN, check whether the turtle avatar's position is changed by 50 in the backward direction
//    assertEquals(expected, myGui.getxPos());
//  }
//
//  @Test
//  void testRightAngleRotation() throws InterruptedException {
//    String rightCommand = "right 90";
//    // GIVEN, the program starts and the turtle is facing 0 degrees to the right
//    double expected = 90;
//    // WHEN, 'right 90' is typed into the terminal and ENTER key is pressed
//    appendInputTo(myTerminal.terminal, rightCommand);
//    Thread.
//        sleep(10000);
//    push(KeyCode.ENTER);
//    // THEN, check whether the turtle avatar has turned to the right by 90 degrees
//    assertEquals(expected, myGui.getMyAngle());
//  }
//
//  @Test
//  void testLefttAngleRotation() {
//    String leftCommand = "left 60\n";
//    // GIVEN, the program starts and the turtle is facing 0 degrees to the right
//    double expected = 300;
//    // WHEN, 'left 60' is typed into the terminal and ENTER key is pressed
//    appendInputTo(myTerminal.terminal, leftCommand);
//    // THEN, check whether the turtle avatar has turned to the left by 60 degrees
//    assertEquals(expected, myGui.getMyAngle());
//  }
//
//  @Test
//  void testBackspaceInTerminal() throws InterruptedException {
//    String wrongCommand = "f";
//    // GIVEN, the program starts
//    String expected = "$";
//    // WHEN, the user puts `f` to the terminal and then backspaces
//    // the text should be erased from the terminal
//    appendInputTo(myTerminal.terminal, wrongCommand);
//    push(KeyCode.BACK_SPACE);
//    Thread.sleep(10000);
//    // THEN, the terminal erases the last character and appends the rest of the input text
//    assertTerminalText(expected);
//  }
//
//  @Test
//  void testLeftAndDeleteInTerminal() throws InterruptedException {
//    String wrongCommand = "f";
//    // GIVEN, the program starts
//    String expected = "$";
//    // WHEN, the user puts `f` to the terminal, then moves left and presses delete
//    // the text should be erased from the terminal
//    appendInputTo(myTerminal.terminal, wrongCommand);
////    while (true) {
//    push(KeyCode.LEFT);
//    push(KeyCode.DELETE);
//    Thread.sleep(10000);
////    }
//
//    // THEN, the terminal erases the last character and appends the rest of the input text
//    assertTerminalText(expected);
//  }
//
//  @Test
//  void testUpdateTurtlePosition() {
//    myGui.updateTurtle(200, 200, 90);
//    double expectedXCor = 200;
//    double expectedYCor = 200;
//    double expectedAngle = 90;
//    assertEquals(myGui.getMyAngle(), expectedAngle);
//    assertEquals(myGui.getxPos(), expectedXCor);
//    assertEquals(myGui.getyPos(), expectedYCor);
//  }
//
//  // everything tests text of the terminal
//  private void assertTerminalText(String expected) {
//    assertEquals(expected, myTerminal.terminal.getText());
//  }
//
//  /**
//   * Simulate typing given text in any text component
//   */
//  protected void appendInputTo(TextInputControl t, String text) {
//    simulateAction(t, () -> {
//      t.requestFocus();
//      // System.out.println(t.isFocused());
//      t.appendText(text);
//      // FIXME: should be just this instead of the hack below :(
//      // write(text);
//      if (text.endsWith("\n") && t instanceof TextField) {
//        t.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED,
//            "\n", KeyCode.ENTER.toString(), KeyCode.ENTER,
//            false, false, false, false));
//      }
//    });
//  }
//
//}
