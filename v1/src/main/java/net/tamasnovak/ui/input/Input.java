package net.tamasnovak.ui.input;

import java.util.Scanner;

public final class Input {
  private final Scanner scanner;

  public Input(Scanner scanner) {
    this.scanner = scanner;
  }

  public String getInputFromUser() {
    while (true) {
      try {
        return scanner.nextLine();
      } catch (Exception error) {
        System.out.println("Enter a correct input option.");
      }
    }
  }
}
