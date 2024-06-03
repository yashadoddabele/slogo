package slogo.model.parser.stackparser;

import java.util.List;
import java.util.Stack;
import slogo.model.Command;

/**
 * Represents a stack data structure specifically designed for storing ParameterStackElements. This
 * class provides methods to push and pop elements onto/from the stack.
 */
public class ParameterStack {

  private final Stack<ParameterStackElement> stack;

  /**
   * Constructs an empty ParameterStack.
   */
  public ParameterStack() {
    stack = new Stack<>();
  }

  public void pushDouble(Double value) {
    stack.push(new ParameterStackElement(value));
  }

  public void pushCommandList(List<Command> commands) {
    stack.push(new ParameterStackElement(commands));
  }

  public void pushElement(ParameterStackElement parameterStackElement) {
    stack.push(parameterStackElement);
  }

  public void pushString(String string) {
    stack.push(new ParameterStackElement(string));
  }

  public ParameterStackElement pop() {
    return stack.pop();
  }

  public ParameterStackElement peek() {
    return stack.peek();
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }
}
