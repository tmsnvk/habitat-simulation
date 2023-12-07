package net.tamasnovak.ui.simulation;

import net.tamasnovak.logic.habitat.Habitat;
import net.tamasnovak.logic.habitat.HabitatType;
import net.tamasnovak.ui.UiMessages;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.input.Input;
import net.tamasnovak.ui.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class SimulationController {
  private final Display display;
  private final Input input;
  private final Logger logger;
  private final UiMessages uiMessages;
  private final List<Habitat> habitats;

  public SimulationController(Display display, Input input, Logger logger, UiMessages uiMessages) {
    this.display = display;
    this.input = input;
    this.logger = logger;
    this.uiMessages = uiMessages;
    this.habitats = new ArrayList<>();
  }

  public void addHabitat(Habitat habitat) {
    habitats.add(habitat);
  }

  public void runSimulation() {
    Habitat selectedHabitat = selectHabitat();
    selectedHabitat.runHabitat();
  }

  private Habitat selectHabitat() {
    while (true) {
      try {
        display.displayMessage(uiMessages.INTRO_TEXT);
        printEnumCategories(HabitatType.values());
        display.displayMessage(uiMessages.PROMPT_TO_SELECT_HABITAT);

        String userInput = input.getInputFromUser();

        return habitats.stream()
          .filter(habitat -> habitat.getHabitatType().toString().equalsIgnoreCase(userInput))
          .findFirst()
          .orElseThrow();
      } catch (Exception error) {
        logger.logError(uiMessages.INCORRECT_USER_INPUT_WARNING);
      }
    }
  }

  private <T extends Enum<T>> void printEnumCategories(T[] values) {
    for (T value : values) {
      System.out.printf("[%s] ", value.name());
    }

    System.out.println();
  }
}
