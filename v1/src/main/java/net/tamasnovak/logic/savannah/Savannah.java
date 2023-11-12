package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class Savannah {
  private final Matrix matrix;
  private final SavannahPopulator savannahPopulator;
  private final Logger logger;

  public Savannah(Matrix matrix, SavannahPopulator savannahPopulator, Logger logger) {
    this.matrix = matrix;
    this.savannahPopulator = savannahPopulator;
    this.logger = logger;
  }

  public void runSimulation() {
    logger.logInfo(SavannahMessages.START_SIMULATION);
    savannahPopulator.runPopulator();

    for (int i = 1; i <= SavannahConfiguration.LENGTH_OF_SIMULATION_YEARS; i++) {
      performAnnualRoutine();
    }
  }

  private void performAnnualRoutine() {

  }

  private Cell findAnimalPosition(Animal animal) {
    return animal.getLivingArea();
  }

  private void perishAnimal(Animal animal) {
    animal.removeDeadAnimal();
  }

  private void moveAnimal(Animal animal) {
    animal.move();
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
      Animal animalInCell = matrix.getCoordinate(xCoordinate, yCoordinate);

      if (animalInCell != null) {
        neighbourAnimals.add(animalInCell);
      }
    }
  }
}
