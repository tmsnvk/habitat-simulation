package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.animals.carnivores.Carnivore;
import net.tamasnovak.model.animals.herbivores.Herbivore;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.ui.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Savannah {
  private final Random random;
  private final Logger logger;
  private final Matrix matrix;
  private final SavannahPopulator savannahPopulator;

  public Savannah(Random random, Logger logger, Matrix matrix, SavannahPopulator savannahPopulator) {
    this.random = random;
    this.logger = logger;
    this.matrix = matrix;
    this.savannahPopulator = savannahPopulator;
  }

  public void runSimulation() {
    logger.logInfo(SavannahMessages.START_SIMULATION);
    savannahPopulator.runPopulator();

    int iterator = 0;

    while (iterator < SavannahConfiguration.LENGTH_OF_SIMULATION_YEARS) {
      performPreAnnualRoutine();
      performAnnualAnimalRoutine();
      performPostAnnualRoutine();

      iterator++;
//      System.out.printf("zebra - %s%n", matrix.countAnimalType(AnimalType.ZEBRA));
//      System.out.printf("lion - %s%n", matrix.countAnimalType(AnimalType.LION));
    }
  }

  private void performPreAnnualRoutine() {
  }

  private void performAnnualAnimalRoutine() {
    List<Animal> eligibleAnimalsForTheYear = matrix.getAnimalsCurrentlyLivingOnSavannah();
    Collections.shuffle(eligibleAnimalsForTheYear);

    for (Animal animal : eligibleAnimalsForTheYear) {
      if (!animal.isAlive()) {
        continue;
      }

      increaseAge(animal);

      if (!animal.isAlive()) {
        continue;
      }

      makeCarnivoreHunt(animal);

      animal.breed();
      animal.move();
    }
  }

  private void performPostAnnualRoutine() {
  }

  private void increaseAge(Animal animal) {
    animal.age();
  }

  private void makeCarnivoreHunt(Animal animal) {
    if (animal instanceof Carnivore carnivore) {
      List<Herbivore> neighbourHerbivores = findNeighbourHerbivores(animal);

      if (neighbourHerbivores.isEmpty()) {
        carnivore.increaseHungerLevelAfterUnsuccessfulHunt();
      } else {
        int randomNumber = random.nextInt(neighbourHerbivores.size());
        Herbivore randomNeighbourHerbivore = neighbourHerbivores.get(randomNumber);
        randomNeighbourHerbivore.perishIfKilledByCarnivore();
      }
    }
  }

//  private void removeDeadAnimal(Animal animal) {
//    matrix.removeDeadAnimal(animal);
//  }

  private List<Herbivore> findNeighbourHerbivores(Animal animal) {
    Cell animalPosition = animal.getLivingArea();
    List<Animal> listNeighbourAnimals = listNeighbourCoordinates(animalPosition);

    return listNeighbourAnimals.stream()
      .filter(neighbour -> neighbour instanceof Herbivore)
      .map(neighbour -> (Herbivore) neighbour)
      .collect(Collectors.toList());
  }

  private List<Animal> listNeighbourCoordinates(Cell animalPosition) {
    List<Animal> validNeighbourCoordinates = new ArrayList<>();

    for (List<Integer> coordinate : SavannahConfiguration.POSSIBLE_NEARBY_COORDINATE_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + coordinate.get(0);
      int yCoordinate = animalPosition.yCoordinate() + coordinate.get(1);

      if (areCoordinatesValid(xCoordinate, yCoordinate)) {
        Animal animalInCell = matrix.getCoordinate(xCoordinate, yCoordinate);

        validNeighbourCoordinates.add(animalInCell);
      }
    }

    return validNeighbourCoordinates;
  }

  private boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
    return xCoordinate >= 0 && xCoordinate < matrix.getLength() && yCoordinate >= 0 && yCoordinate < matrix.getWidth();
  }
}
