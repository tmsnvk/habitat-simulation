package net.tamasnovak.logic.routine.habitatRoutine.populatorRoutine;

import net.tamasnovak.logic.factory.animalFactory.AnimalFactory;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.logic.routine.habitatRoutine.HabitatInstanceRoutine;
import net.tamasnovak.model.nature.Nature;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;
import java.util.Set;

public final class PopulatorRoutine extends HabitatInstanceRoutine {
  private final SavannahConfiguration habitatConfiguration;
  private final AnimalFactory animalFactory;

  public PopulatorRoutine(
    Random random,
    Logger logger,
    Matrix matrix,
    SavannahConfiguration habitatConfiguration,
    AnimalFactory animalFactory) {
    super(random, logger, matrix);
    this.habitatConfiguration = habitatConfiguration;
    this.animalFactory = animalFactory;
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
    Cell livingArea = new Cell(xCoordinate, yCoordinate);
    Animal animal = generateAnimal(livingArea);

    matrix.placeNatureInstanceByCoordinate(xCoordinate, yCoordinate, animal);
  }

  private Animal generateAnimal(Cell livingArea) {
    double coinFlipValue = random.nextDouble(0, 1);

    if (coinFlipValue <= habitatConfiguration.CHANCE_OF_HERBIVORE) {
      return animalFactory.createAnimal(AnimalType.HERBIVORE, habitatConfiguration.getHerbivore(), livingArea);
    } else {
      return animalFactory.createAnimal(AnimalType.CARNIVORE, habitatConfiguration.getCarnivore(), livingArea);
    }
  }

  private void displayPreRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.START_POPULATE_MATRIX);
  }

  private void displayPostRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.END_POPULATE_MATRIX);
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
