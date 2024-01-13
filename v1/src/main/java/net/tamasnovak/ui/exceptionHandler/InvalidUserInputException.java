package net.tamasnovak.ui.exceptionHandler;

public class InvalidUserInputException extends RuntimeException {
  public InvalidUserInputException(String errorMessage) {
    super(errorMessage);
  }
}
