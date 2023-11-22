package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.animals.carnivores.Carnivore;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Savannah {
  private final Logger logger;
  private final Matrix matrix;
  private final SavannahPopulator savannahPopulator;

  public Savannah(Logger logger, Matrix matrix, SavannahPopulator savannahPopulator) {
    this.logger = logger;
    this.matrix = matrix;
    this.savannahPopulator = savannahPopulator;
  }

  public void runSimulation() {
    logger.logInfo(SavannahMessages.START_SIMULATION);
    savannahPopulator.runPopulator();

    int iterator = 0;

    while (iterator < SavannahConfiguration.LENGTH_OF_SIMULATION_YEARS) {
      performAnnualAnimalRoutine();
      performAnnualCleanUpRoutine();
      iterator++;
    }
  }

  private void performAnnualAnimalRoutine() {
    List<Animal> eligibleAnimalsForTheYear = matrix.getAnimalsCurrentlyOnSavannah();
    Collections.shuffle(eligibleAnimalsForTheYear);

    for (Animal animal : eligibleAnimalsForTheYear) {
      boolean isAnimalAlive = ageAnimal(animal);

      if (isAnimalAlive) {
        continue;
      }

      consumeFood(animal);
      animal.breed();
      animal.move();
    }
  }

  private boolean ageAnimal(Animal animal) {
    animal.age();

    return animal.isAlive();
  }

  private void consumeFood(Animal animal) {
    List<Animal> neighbourAnimals = listNeighbourAnimals(animal);

    if (animal instanceof Carnivore carnivore) {
      carnivore.eat(neighbourAnimals);
    }
  }

  private void performAnnualCleanUpRoutine() {
    // remove dead animals from matrix.
  }

  private List<Animal> listNeighbourAnimals(Animal animal) {
    List<Animal> neighbourAnimals = new ArrayList<>();
    Cell animalPosition = animal.getLivingArea();

    checkNeighbourCoordinates(neighbourAnimals, animalPosition);

    return neighbourAnimals;
  }

  private void checkNeighbourCoordinates(List<Animal> neighbourAnimals, Cell animalPosition) {
    for (List<Integer> coordinate : SavannahConfiguration.POSSIBLE_NEARBY_COORDINATE_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + coordinate.get(0);
      int yCoordinate = animalPosition.yCoordinate() + coordinate.get(1);

      if (areCoordinatesValid(xCoordinate, yCoordinate)) {
        Animal animalInCell = matrix.getCoordinate(xCoordinate, yCoordinate);

        if (animalInCell != null) {
          neighbourAnimals.add(animalInCell);
        }
      }
    }
  }

  private boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
    return xCoordinate >= 0 && xCoordinate < matrix.getLength() && yCoordinate >= 0 && yCoordinate < matrix.getWidth();
  }
}
