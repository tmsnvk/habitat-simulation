package net.tamasnovak.logic.routine.habitatRoutine.populatorRoutine;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.logic.routine.habitatRoutine.HabitatInstanceRoutine;
import net.tamasnovak.model.nature.Nature;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.ui.logger.Logger;
import net.tamasnovak.ui.simulation.SimulationController;

import java.util.Random;
import java.util.Set;

public final class PopulatorRoutine extends HabitatInstanceRoutine {
  private final HabitatConfiguration habitatConfiguration;
  private final SimulationController simulationController;

  public PopulatorRoutine(Random random, Logger logger, Matrix matrix, HabitatConfiguration habitatConfiguration, SimulationController simulationController) {
    super(random, logger, matrix);
    this.habitatConfiguration = habitatConfiguration;
    this.simulationController = simulationController;
  }

  @Override
  public void run() {
    displayPreRoutineLogs();
    populateMatrix();
    displayPostRoutineLogs();
  }

  private void populateMatrix() {
    int animalCounter = 0;

    while (animalCounter < habitatConfiguration.NUMBER_OF_ANIMALS) {
      int xCoordinate = random.nextInt(matrix.getLength());
      int yCoordinate = random.nextInt(matrix.getWidth());
      Nature position = matrix.findPosition(xCoordinate, yCoordinate);

      if (!(position instanceof Vegetation)) {
        continue;
      }

      addAnimalToMatrix(xCoordinate, yCoordinate);
      animalCounter++;
    }
  }

  private void addAnimalToMatrix(int xCoordinate, int yCoordinate) {
    Animal animal = generateAnimal(xCoordinate, yCoordinate);

    matrix.placeNatureInstanceByPosition(xCoordinate, yCoordinate, animal);
  }

  private Animal generateAnimal(int xCoordinate, int yCoordinate) {
    double coinFlipValue = random.nextDouble(0, 1);

    if (coinFlipValue <= habitatConfiguration.CHANCE_OF_HERBIVORE) {
      return matrix.createAnimal(AnimalType.HERBIVORE, habitatConfiguration.getHerbivore(), xCoordinate, yCoordinate);
    } else {
      return  matrix.createAnimal(AnimalType.CARNIVORE, habitatConfiguration.getCarnivore(), xCoordinate, yCoordinate);
    }
  }

  private void displayPreRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.START_POPULATE_MATRIX);
  }

  private void displayPostRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.END_POPULATE_MATRIX);

    simulationController.promptEnterKey(PopulatorRoutineMessages.PROMPT_TO_SEE_ANIMAL_STATS);
    logger.logInfo(PopulatorRoutineMessages.ANIMAL_STATS_INTRO);

    Set<AnimalSpecies> animalSpeciesInMatrix = matrix.findDistinctAnimalSpecies();

    for (AnimalSpecies animalSpecies : animalSpeciesInMatrix) {
      int numberOfAnimals = matrix.countAnimalsBySpecies(animalSpecies);
      logger.logInfo(String.format(
        numberOfAnimals == 1 ? PopulatorRoutineMessages.ANIMAL_STATS_SUMMARY_SINGULAR : PopulatorRoutineMessages.ANIMAL_STATS_SUMMARY_PLURAL,
        numberOfAnimals,
        animalSpecies.name()
      ));
    }
  }
}
