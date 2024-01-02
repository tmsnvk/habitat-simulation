package net.tamasnovak.ui.logger;

import java.time.LocalDateTime;

public final class ConsoleLogger implements Logger {
  public ConsoleLogger() {}

  @Override
  public void logInfo(String message) {
    logMessage(MessageType.INFO, message);
  }

  @Override
  public void logError(String message) {
    logMessage(MessageType.ERROR, message);
  }

  private void logMessage(MessageType messageType, String message) {
    System.out.printf("[Timestamp]: %s | [MessageType]: %s | [Message]: %s%n", LocalDateTime.now(), messageType, message);
  }
}
