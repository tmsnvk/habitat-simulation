package net.tamasnovak.logic.routines.populatorRoutine;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.logic.habitat.savannah.SavannahConfiguration;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.animal.carnivore.Lion;
import net.tamasnovak.model.animal.herbivore.Zebra;
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
  private final PopulatorRoutineMessages messages;

  public PopulatorRoutine(Random random, Logger logger, Matrix matrix, HabitatConfiguration habitatConfiguration, PopulatorRoutineMessages messages) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
    this.habitatConfiguration = habitatConfiguration;
    this.messages = messages;
  }

  public void run() {
    logger.logInfo(messages.START_POPULATE_MATRIX);

    int animalCounter = 0;

    while (animalCounter < habitatConfiguration.NUMBER_OF_ANIMALS) {
      int xCoordinate = random.nextInt(matrix.getLength());
      int yCoordinate = random.nextInt(matrix.getWidth());
      Animal coordinate = matrix.findAnimalInCoordinate(xCoordinate, yCoordinate);

      if (coordinate != null) {
        continue;
      }

      double coinFlipValue = random.nextDouble(0, 1);

      addAnimalToMatrix(xCoordinate, yCoordinate, coinFlipValue, Zebra.class);
      animalCounter++;
    }

    displayRoutineEndingLogging();
  }

  private void addAnimalToMatrix(int xCoordinate, int yCoordinate, double coinFlipValue, Class<?> classToCreate) {
    if (coinFlipValue <= SavannahConfiguration.CHANCE_OF_HERBIVORE) {
      Cell livingArea = new Cell(xCoordinate, yCoordinate);
      Animal zebra = new Zebra(livingArea, random);

      matrix.placeAnimalInCoordinate(xCoordinate, yCoordinate, zebra);
    } else {
      Cell livingArea = new Cell(xCoordinate, yCoordinate);
      Animal lion = new Lion(livingArea, random);

      matrix.placeAnimalInCoordinate(xCoordinate, yCoordinate, lion);
    }
  }

  public void displayRoutineEndingLogging() {
    logger.logInfo(messages.END_POPULATE_MATRIX);
    logger.logInfo(messages.ANIMAL_STATS_INTRO);

    Set<AnimalType> animalTypesInMatrix = matrix.findAllAnimaLType();

    for (AnimalType animalType : animalTypesInMatrix) {
      int numberOfAnimals = matrix.countAnimalType(animalType);
      logger.logInfo(String.format(messages.ANIMAL_STATS_DETAIL, numberOfAnimals, animalType.name()));
    }
  }
}
