package net.tamasnovak.ui.display;

import java.util.List;

public final class Display {
  public Display() {}

  public void showGenericMessage(String message) {
    System.out.printf(message);
  }

  public void showGenericMessageWithColor(String message, Color color) {
    System.out.printf(message, color, Color.RESET);
  }

  public void showGenericMessageWithColor(String message, Color colorOne, Color colorTwo) {
    System.out.printf(message, colorOne, Color.RESET, colorTwo, Color.RESET);
  }

  public void showConfirmationMessage(String message, String confirmedValue) {
    System.out.printf(message, confirmedValue);
  }

  public void showConfirmationMessageWithColor(String message, Color color, String confirmedValue) {
    System.out.printf(message, color, confirmedValue, Color.RESET);
  }

  public <T extends Enum<T>> void showEnumCategories(List<T> values) {
    for (T value : values) {
      if (value.name().contains("_")) {
        System.out.printf("[%s] ", value.name().replaceAll("_", " "));
      } else {
        System.out.printf("[%s] ", value.name());
      }
    }

    System.out.println();
  }
}
