package net.tamasnovak.ui.simulation;

import net.tamasnovak.logic.habitat.HabitatType;
import net.tamasnovak.ui.UiMessages;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.input.Input;
import net.tamasnovak.ui.logger.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class SimulationController {
  private final Scanner scanner;
  private final Display display;
  private final Input input;
  private final Logger logger;

  public SimulationController(Scanner scanner, Display display, Input input, Logger logger) {
    this.scanner = scanner;
    this.display = display;
    this.input = input;
    this.logger = logger;
  }

  public SimulationUserInputData startSimulation() {
    SimulationUserInputData simulationUserInputData = new SimulationUserInputData();

    runIntroduction();

    HabitatType selectedHabitatType = runHabitatTypeSelection();
    simulationUserInputData.setHabitatType(selectedHabitatType);



    return simulationUserInputData;
  }

  private void runIntroduction() {
    display.displayMessage(UiMessages.SIMULATION_INTRODUCTION);

    while (true) {
      try {

        String userInput = input.getInputFromUser();
        System.out.println();

        if (userInput.equalsIgnoreCase("start")) {
          return;
        } else if (userInput.equalsIgnoreCase("exit")) {
          System.exit(0);
        } else {
          throw new Exception();
        }
      } catch (Exception error) {
        logger.logError(UiMessages.INCORRECT_USER_INPUT_WARNING);
      }
    }
  }

  private HabitatType runHabitatTypeSelection() {
    while (true) {
      try {
        display.displayMessage(UiMessages.INTRO_TEXT);
        printEnumCategories(HabitatType.values());

        display.displayMessage(UiMessages.PROMPT_TO_SELECT_HABITAT);

        String userInput = input.getInputFromUser();
        System.out.println();

        return Arrays.stream(HabitatType.values())
          .filter(habitat -> habitat.name().equalsIgnoreCase(userInput))
          .findFirst()
          .orElseThrow();
      } catch (Exception error) {
        logger.logError(UiMessages.INCORRECT_USER_INPUT_WARNING);
      }
    }
  }

  public void promptEnterKey(String promptMessage) {
    System.out.printf(promptMessage);
    scanner.nextLine();
  }

  private <T extends Enum<T>> void printEnumCategories(T[] values) {
    for (T value : values) {
      System.out.printf("[%s] ", value.name());
    }

    System.out.println();
    System.out.println();
  }
}
