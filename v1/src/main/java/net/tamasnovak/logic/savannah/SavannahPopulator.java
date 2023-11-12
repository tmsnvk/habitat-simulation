package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.animals.carnivores.Lion;
import net.tamasnovak.model.animals.herbivores.Zebra;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.ui.logger.Logger;

import java.util.Random;
import java.util.Set;

public class SavannahPopulator {
  private final Random random;
  private final Matrix matrix;
  private final Logger logger;

  public SavannahPopulator(Random random, Matrix matrix, Logger logger) {
    this.random = random;
    this.matrix = matrix;
    this.logger = logger;
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

      placeAnimal(xCoordinate, yCoordinate, coinFlipValue);
      animalCounter++;
    }

    populatorRoutineEndingLogging();
  }

  private void placeAnimal(int xCoordinate, int yCoordinate, double coinFlipValue) {
    if (coinFlipValue <= SavannahConfiguration.CHANCE_OF_HERBIVORE) {
      Animal zebra = new Zebra(new Cell(xCoordinate, yCoordinate));
      matrix.placeAnimalInCoordinate(xCoordinate, yCoordinate, zebra);
    } else {
      Animal lion = new Lion(new Cell(xCoordinate, yCoordinate));
      matrix.placeAnimalInCoordinate(xCoordinate, yCoordinate, lion);
    }
  }

  private void populatorRoutineEndingLogging() {
    logger.logInfo(SavannahMessages.END_POPULATE_MATRIX);
    logger.logInfo(SavannahMessages.ANIMAL_STATISTICS);

    Set<AnimalType> animalTypesInMatrix = matrix.listAnimalTypes();

    for (AnimalType animalType : animalTypesInMatrix) {
      int numberOfAnimals = matrix.countAnimalType(animalType);
      logger.logInfo(String.format("%s %s live(s) in the simulation.", numberOfAnimals, animalType.name()));
    }
  }
}
