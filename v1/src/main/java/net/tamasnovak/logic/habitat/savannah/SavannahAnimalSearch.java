package net.tamasnovak.logic.habitat.savannah;

import net.tamasnovak.logic.habitat.HabitatConfiguration;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SavannahAnimalSearch {
  private final Matrix matrix;
  private final HabitatConfiguration habitatConfiguration = new SavannahConfiguration();

  public SavannahAnimalSearch(Matrix matrix) {
    this.matrix = matrix;
  }

  <T extends Animal> List<T> findNeighbourAnimalTypeInstances(Animal animalToCheckAgainst, Class<?> animalTypeToFind) {
    Cell animalPosition = animalToCheckAgainst.getLivingArea();
    List<Animal> neighbourAnimals = findNeighbourAnimalsInValidCoordinates(animalPosition);

    return neighbourAnimals.stream()
      .filter(animalTypeToFind::isInstance)
      .map(neighbour -> (T) neighbour)
      .collect(Collectors.toList());
  }

  private List<Animal> findNeighbourAnimalsInValidCoordinates(Cell animalPosition) {
    List<Animal> neighbourAnimalsInValidCoordinates = new ArrayList<>();

    for (List<Integer> coordinate : habitatConfiguration.POSSIBLE_NEARBY_COORDINATE_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + coordinate.get(0);
      int yCoordinate = animalPosition.yCoordinate() + coordinate.get(1);

      if (areCoordinatesValid(xCoordinate, yCoordinate)) {
        Animal animal = matrix.findAnimalInCoordinate(xCoordinate, yCoordinate);

        neighbourAnimalsInValidCoordinates.add(animal);
      }
    }

    return neighbourAnimalsInValidCoordinates;
  }

  private boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
    return xCoordinate >= 0 && xCoordinate < matrix.getLength() && yCoordinate >= 0 && yCoordinate < matrix.getWidth();
  }
}
