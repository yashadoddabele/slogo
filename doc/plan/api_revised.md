# API Revised Overview

The API implementation that we have
decided to proceed with
serves as an intermediary between the
application's controller (part of the frontend)
and the model (backend), specifically
designed for the turtle graphics system that
we are trying to achieve in the project.

We have chosen this implementation
design to encapsulate the
functionality of the turtle graphics model,
making it easy for the frontend to issue
commands and retrieve state information without directly manipulating model objects. This design
follows the Model-View-Controller (MVC) architectural pattern, enhancing modularity and separation
of concerns.

## Design Goals and Functionality

### Ease of Use

We have tried to make the API design
to be intuitive for developers working on the frontend. By providing methods
like `sendCommand`, `updateLanguage`, and `getTurtlePositions`, it offers a straightforward way to
interact with the model. These methods abstract the complexities of command parsing and execution,
language settings, and state retrieval, enabling frontend developers to focus on user interface
design and user experience.

### Safety and Encapsulation

Based on the readings
and safety principles we have discussed about
APIs, we aim to make the API safe and encapsulated.
While we would want to keep the `Turtle` object private,
but as of now, we have a `getTurtle` method that returns the turtle object.
We plan to modify this perhaps at later stages so
that the direct manipulation of the turtle's state from
outside the model is restricted to reduce risk of inconsistencies.

We have the said method right now only as a placeholder
to potentially implement an observersList.

### Extensibility

While the current API
provides a specific set of
functionalities,
its design allows for future extensions.
New methods can be added to the API to
expose additional model features without
altering the existing contract with the frontend.
This approach supports the introduction of new commands,
features, or language supports without
disrupting the frontend's interaction
with the model.

### Error Handling

The API includes a mechanism for
reporting errors (`sendError` method)
back to the controller,
which can then relay this
information to the GUI.
This feature ensures that users are
informed of issues in a user-friendly manner,
enhancing the application's usability.

## Implementation Highlights

### Command Execution

The `sendCommand` method allows the frontend
to execute commands by passing a string to
the model. This method abstracts
the command parsing and execution
process, simplifying the frontend's
role in command processing.

### Language Support

The `updateLanguage` method provides flexibility in internationalization, enabling the application
to adapt to different languages for command parsing. This feature enhances the application's
accessibility and usability across diverse user bases.

### Turtle State Retrieval

The `getTurtlePositions` method offers a way to retrieve the current state of the turtle (position
and angle) without exposing the turtle object itself. This approach maintains the encapsulation of
the model's internal state while still providing necessary information to the frontend for rendering
or other purposes.

### Error Communication

Through the `sendError` method, the API provides a structured way to handle errors by communicating
them back to the frontend. This ensures that errors are handled gracefully, maintaining a smooth
user experience even when issues occur.

## Conclusion

We have attempted that our design of the API class within the
`slogo.model.api` package is a
well-designed interface that balances
ease of use, safety, extensibility,
and robust error handling.

By offering a set of methods the frontend to interact with the turtle graphics system,
our API design supports the development of a modular, maintainable, and user-friendly application.

Here is our Java implementation:

```java 
package slogo.model.api;

import slogo.controller.Controller;
import slogo.model.CommandParser;
import slogo.model.Turtle;

public class Api {

  private CommandParser parser;

  //Completely private - view should not be able to know this turtle obj even exists
  private Turtle turtle;
  private Controller controller;


  public Api(Controller controller) {
    this.controller = controller;
    this.turtle = new Turtle(0, 0, 0, true, true);
    this.parser = new CommandParser(turtle, this);
  }

  public void sendCommand(String commands) {
    parser.parseCommands(commands);
  }

  public void updateLanguage(String language) {
    parser.setLanguage(language);
  }

  public double[] getTurtlePositions() {
    double[] positions = new double[3];
    positions[0] = turtle.getxPos();
    positions[1] = turtle.getyPos();
    positions[2] = turtle.getAngle();
    return positions;
  }

  public void sendError(String message) {
    controller.sendErrorToGui(message);
  }

  public Turtle getTurtle() {
    return turtle;
  }
}
