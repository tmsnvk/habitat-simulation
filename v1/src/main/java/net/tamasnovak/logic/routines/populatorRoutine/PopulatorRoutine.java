package net.tamasnovak.logic.routines.populatorRoutine;

import net.tamasnovak.logic.animalFactory.AbstractFactory;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;
import java.util.Set;

public final class PopulatorRoutine {
  private final Random random;
  private final Logger logger;
  private final Matrix matrix;
  private final SavannahConfiguration habitatConfiguration;
  private final AbstractFactory<Animal> animalFactory;

  public PopulatorRoutine(
    Random random,
    Logger logger,
    Matrix matrix,
    SavannahConfiguration habitatConfiguration,
    AbstractFactory<Animal> animalFactory) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
    this.habitatConfiguration = habitatConfiguration;
    this.animalFactory = animalFactory;
  }

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
      Animal animalByCoordinate = matrix.findAnimalByCoordinate(xCoordinate, yCoordinate);

      if (animalByCoordinate != null) {
        continue;
      }

      addAnimalToMatrix(xCoordinate, yCoordinate);

      animalCounter++;
    }
  }

  private void addAnimalToMatrix(int xCoordinate, int yCoordinate) {
    Cell livingArea = new Cell(xCoordinate, yCoordinate);
    Animal animal = createAnimal(livingArea);

    matrix.placeAnimalByCoordinate(xCoordinate, yCoordinate, animal);
  }

  private Animal createAnimal(Cell livingArea) {
    double coinFlipValue = random.nextDouble(0, 1);

    if (coinFlipValue <= habitatConfiguration.CHANCE_OF_HERBIVORE) {
      return animalFactory.createAnimal(AnimalType.HERBIVORE, habitatConfiguration.HERBIVORE, livingArea);
    } else {
      return animalFactory.createAnimal(AnimalType.CARNIVORE, habitatConfiguration.CARNIVORE, livingArea);
    }
  }

  private void displayPreRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.START_POPULATE_MATRIX);
  }

  private void displayPostRoutineLogs() {
    logger.logInfo(PopulatorRoutineMessages.END_POPULATE_MATRIX);
    logger.logInfo(PopulatorRoutineMessages.ANIMAL_STATS_INTRO);

    Set<AnimalSpecies> animalTypesInMatrices = matrix.findAllAnimalSpecies();

    for (AnimalSpecies animalSpecies : animalTypesInMatrices) {
      int numberOfAnimals = matrix.countAnimalsBySpecies(animalSpecies);
      logger.logInfo(String.format(
        numberOfAnimals == 1 ? PopulatorRoutineMessages.ANIMAL_STATS_SUMMARY_SINGULAR : PopulatorRoutineMessages.ANIMAL_STATS_SUMMARY_PLURAL,
        numberOfAnimals,
        animalSpecies.name()
      ));
    }
  }
}
