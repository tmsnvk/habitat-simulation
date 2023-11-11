package net.tamasnovak.model.savannah;

import net.tamasnovak.model.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Savannah {
  private static final int LENGTH_OF_SIMULATION = 100;
  private static final int NUMBER_OF_ANIMALS = 200;
  private static final double CHANCE_OF_HERBIVORE = 0.65;
  private static final List<List<Integer>> POSSIBLE_NEARBY_COORDINATE_DIFFERENCES = List.of(
    List.of(-1, 0),
    List.of(0, 1),
    List.of(1, 0),
    List.of(0, -1)
  );
  private final Matrix matrix;

  public Savannah(Matrix matrix) {
    this.matrix = matrix;
  }

  public void runSimulation() {
    populateMatrix();

    for (int i = 1; i <= LENGTH_OF_SIMULATION; i++) {
      performAnnualRoutine();
    }
  }

  private void populateMatrix() {

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
    for (List<Integer> coordinate : POSSIBLE_NEARBY_COORDINATE_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + coordinate.get(0);
      int yCoordinate = animalPosition.yCoordinate() + coordinate.get(1);
      Animal animalInCell = matrix.getMatrix()[xCoordinate][yCoordinate];

      if (animalInCell != null) {
        neighbourAnimals.add(animalInCell);
      }
    }
  }
}
