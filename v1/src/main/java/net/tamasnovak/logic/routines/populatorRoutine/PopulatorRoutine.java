package net.tamasnovak.logic.routines.populatorRoutine;

import net.tamasnovak.logic.animalFactory.AbstractFactory;
import net.tamasnovak.logic.habitat.HabitatConfiguration;
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
  private final HabitatConfiguration habitatConfiguration;
  private final AbstractFactory<Animal> animalFactory;
  private final PopulatorRoutineMessages routineMessages;

  public PopulatorRoutine(Random random, Logger logger, Matrix matrix, HabitatConfiguration habitatConfiguration, AbstractFactory<Animal> animalFactory, PopulatorRoutineMessages routineMessages) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
    this.habitatConfiguration = habitatConfiguration;
    this.animalFactory = animalFactory;
    this.routineMessages = routineMessages;
  }

  public void run() {
    logger.logInfo(routineMessages.START_POPULATE_MATRIX);

    int animalCounter = 0;

    while (animalCounter < habitatConfiguration.NUMBER_OF_ANIMALS) {
      int xCoordinate = random.nextInt(matrix.getLength());
      int yCoordinate = random.nextInt(matrix.getWidth());
      Animal animal = matrix.findAnimalInCoordinate(xCoordinate, yCoordinate);

      if (animal != null) {
        continue;
      }

      double coinFlipValue = random.nextDouble(0, 1);
      addAnimalToMatrix(xCoordinate, yCoordinate, coinFlipValue);

      animalCounter++;
    }

    displayRoutineEndingLogging();
  }

  private void addAnimalToMatrix(int xCoordinate, int yCoordinate, double coinFlipValue) {
    Animal animal;
    Cell livingArea = new Cell(xCoordinate, yCoordinate);

    if (coinFlipValue <= SavannahConfiguration.CHANCE_OF_HERBIVORE) {
      animal = animalFactory.createAnimal(AnimalType.HERBIVORE, AnimalSpecies.ZEBRA, livingArea);
    } else {
      animal = animalFactory.createAnimal(AnimalType.CARNIVORE, AnimalSpecies.LION, livingArea);
    }

    matrix.placeAnimalInCoordinate(xCoordinate, yCoordinate, animal);
  }

  public void displayRoutineEndingLogging() {
    logger.logInfo(routineMessages.END_POPULATE_MATRIX);
    logger.logInfo(routineMessages.ANIMAL_STATS_INTRO);

    Set<AnimalSpecies> animalTypesInMatrices = matrix.findAllAnimaLType();

    for (AnimalSpecies animalSpecies : animalTypesInMatrices) {
      int numberOfAnimals = matrix.countAnimalType(animalSpecies);
      logger.logInfo(String.format(routineMessages.ANIMAL_STATS_DETAIL, numberOfAnimals, animalSpecies.name()));
    }
  }
}
