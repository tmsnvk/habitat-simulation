package net.tamasnovak.ui.logger;

import java.time.LocalDateTime;

public class ConsoleLogger implements Logger {
  public ConsoleLogger() {}

  @Override
  public void logInfo(String message) {
    logMessage(MessageTypes.INFO, message);
  }

  @Override
  public void logError(String message) {
    logMessage(MessageTypes.ERROR, message);
  }

  private void logMessage(MessageTypes messageType, String message) {
    System.out.printf("[Timestamp]: %s | [MessageType]: %s | [Message]: %s", LocalDateTime.now(), messageType, message);
  }
}
