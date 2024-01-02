package net.tamasnovak.ui.display;

import java.util.List;

public final class Display {
  public Display() {}

  public void showGenericMessage(String message) {
    System.out.printf(message);
  }

  public void showConfirmationMessage(String message, String confirmedValue) {
    System.out.printf(message, confirmedValue);
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
