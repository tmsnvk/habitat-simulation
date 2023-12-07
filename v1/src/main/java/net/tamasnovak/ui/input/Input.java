package net.tamasnovak.ui.input;

import java.util.Scanner;

public class Input {
  public String getInputFromUser() {
    while (true) {
      try {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
      } catch (Exception error) {
        System.out.println("Enter a correct input option.");
      }
    }
  }
}
