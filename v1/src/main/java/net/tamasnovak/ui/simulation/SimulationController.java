package net.tamasnovak.ui.simulation;

import net.tamasnovak.logic.habitat.HabitatType;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;
import net.tamasnovak.ui.UiMessages;
import net.tamasnovak.ui.display.Color;
import net.tamasnovak.ui.display.Display;
import net.tamasnovak.ui.input.Input;
import net.tamasnovak.ui.logger.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class SimulationController {
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

  public SimulationUserInput startSimulationConfiguration() {
    SimulationUserInput simulationUserInput = new SimulationUserInput();
    runIntroduction();

    HabitatType selectedHabitatType = runHabitatTypeSelection();
    simulationUserInput.setHabitatType(selectedHabitatType);

    VegetationType selectedVegetationType = runVegetationTypeSelection(selectedHabitatType);
    simulationUserInput.setVegetationType(selectedVegetationType);

    VegetationSpecies selectedVegetationSpecies = runVegetationSpeciesSelection(selectedVegetationType);
    simulationUserInput.setVegetationSpecies(selectedVegetationSpecies);

    return simulationUserInput;
  }

  private void runIntroduction() {
    display.showGenericMessage(UiMessages.SIMULATION_INTRODUCTION);

    while (true) {
      try {
        display.showGenericMessageWithColor(UiMessages.OPTIONS_AT_INTRODUCTION, Color.GREEN_BOLD, Color.RED_BOLD);
        String userInput = input.getInputFromUser().toLowerCase();
        System.out.println();

        if (userInput.equals("start")) {
          return;
        } else if (userInput.equals("exit")) {
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
    List<HabitatType> habitatTypes = Arrays.stream(HabitatType.values()).collect(Collectors.toList());

    while (true) {
      try {
        displaySectionIntroMessages(UiMessages.OPTIONS_AT_HABITAT_TYPES_SELECTION, habitatTypes, UiMessages.PROMPT_TO_SELECT_HABITAT);

        String userInput = displaySectionUserInputMessages();
        Predicate<HabitatType> filterByUserInput = habitat -> habitat.name().equalsIgnoreCase(userInput);

        return findEnumType(HabitatType.values(), filterByUserInput);
      } catch (Exception error) {
        logger.logError(UiMessages.INCORRECT_USER_INPUT_WARNING);
      }
    }
  }

  private VegetationType runVegetationTypeSelection(HabitatType habitatType) {
    Predicate<VegetationType> filterVegetationTypeByHabitatType = vegetationType -> vegetationType.getHabitatType().equalsIgnoreCase(habitatType.name());
    List<VegetationType> vegetationTypesInHabitatType = findEnumTypes(VegetationType.values(), filterVegetationTypeByHabitatType);

    while (true) {
      try {
        displaySectionIntroMessages(UiMessages.OPTIONS_AT_VEGETATION_TYPES_SELECTION, vegetationTypesInHabitatType, UiMessages.PROMPT_TO_SELECT_VEGETATION_TYPE);

        String userInput = displaySectionUserInputMessages();
        Predicate<VegetationType> filterByUserInput = vegetationType -> vegetationType.name().equalsIgnoreCase(userInput);

        return findEnumType(VegetationType.values(), filterByUserInput);
      } catch (Exception error) {
        logger.logError(UiMessages.INCORRECT_USER_INPUT_WARNING);
      }
    }
  }

  private VegetationSpecies runVegetationSpeciesSelection(VegetationType vegetationType) {
    Predicate<VegetationSpecies> filterVegetationSpeciesByVegetationType = vegetationSpecies -> vegetationSpecies.getVegetationType().equalsIgnoreCase(vegetationType.name());
    List<VegetationSpecies> vegetationSpeciesInVegetationType = findEnumTypes(VegetationSpecies.values(), filterVegetationSpeciesByVegetationType);

    while (true) {
      try {
        displaySectionIntroMessages(UiMessages.OPTIONS_AT_VEGETATION_SPECIES_SELECTION, vegetationSpeciesInVegetationType, UiMessages.PROMPT_TO_SELECT_VEGETATION_SPECIES);

        String userInput = displaySectionUserInputMessages();
         Predicate<VegetationSpecies> filterByUserInput = vegetationSpecies -> vegetationSpecies.name().replaceAll("_", " ").equalsIgnoreCase(userInput);

        return findEnumType(VegetationSpecies.values(), filterByUserInput);
      } catch (Exception error) {
        logger.logError(UiMessages.INCORRECT_USER_INPUT_WARNING);
      }
    }
  }

  private <T extends Enum<T>> void displaySectionIntroMessages(String messageOne, List<T> enumValueList, String messageTwo) {
    display.showGenericMessage(messageOne);
    display.showEnumCategories(enumValueList);
    display.showGenericMessage(messageTwo);
  }

  private String displaySectionUserInputMessages() {
    String userInput = input.getInputFromUser();
    System.out.println();
    display.showConfirmationMessageWithColor(UiMessages.SELECTION_CONFIRMATION, Color.BLUE_BOLD, userInput);

    return userInput;
  }

  private <T extends Enum<T>> T findEnumType(T[] enumValues, Predicate<T> filterPredicate) {
    return Arrays.stream(enumValues)
      .filter(filterPredicate)
      .findFirst()
      .orElseThrow();
  }

  private <T extends Enum<T>> List<T> findEnumTypes(T[] enumValues, Predicate<T> filterPredicate) {
    return Arrays.stream(enumValues)
      .filter(filterPredicate)
      .collect(Collectors.toList());
  }

  public void promptEnterKey(String message) {
    display.showGenericMessage(message);
    scanner.nextLine();
  }
}
