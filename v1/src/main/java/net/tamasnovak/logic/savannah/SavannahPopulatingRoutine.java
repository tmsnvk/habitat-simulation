package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.animal.carnivore.Lion;
import net.tamasnovak.model.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;
import java.util.Set;

public class SavannahPopulatingRoutine {
  private final Random random;
  private final Logger logger;
  private final Matrix matrix;

  public SavannahPopulatingRoutine(Random random, Logger logger, Matrix matrix) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
  }

  void runPopulator() {
    logger.logInfo(SavannahMessages.START_POPULATE_MATRIX);

    int animalCounter = 0;

    while (animalCounter < SavannahConfiguration.NUMBER_OF_ANIMALS) {
      int xCoordinate = random.nextInt(matrix.getLength());
      int yCoordinate = random.nextInt(matrix.getWidth());
      Animal coordinate = matrix.getCoordinate(xCoordinate, yCoordinate);

      if (coordinate != null) {
        continue;
      }

      double coinFlipValue = random.nextDouble(0, 1);

      addAnimalToMatrix(xCoordinate, yCoordinate, coinFlipValue);
      animalCounter++;
    }

    displayRoutineEndingLogging();
  }

  private void addAnimalToMatrix(int xCoordinate, int yCoordinate, double coinFlipValue) {
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

  private void displayRoutineEndingLogging() {
    logger.logInfo(SavannahMessages.END_POPULATE_MATRIX);
    logger.logInfo(SavannahMessages.ANIMAL_STATS_INTRO);

    Set<AnimalType> animalTypesInMatrix = matrix.findAllAnimaLType();

    for (AnimalType animalType : animalTypesInMatrix) {
      int numberOfAnimals = matrix.countAnimalType(animalType);
      logger.logInfo(String.format(SavannahMessages.ANIMAL_STATS_DETAIL, numberOfAnimals, animalType.name()));
    }
  }
}
