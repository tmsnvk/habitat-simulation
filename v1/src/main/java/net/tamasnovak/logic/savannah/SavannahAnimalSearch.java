package net.tamasnovak.logic.savannah;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SavannahAnimalSearch {
  private final Matrix matrix;

  public SavannahAnimalSearch(Matrix matrix) {
    this.matrix = matrix;
  }

//  List<Herbivore> findNeighbourHerbivores(Animal animal) {
//    Cell animalPosition = animal.getLivingArea();
//    List<Animal> listNeighbourAnimals = findValidNeighbourCoordinates(animalPosition);
//
//    return listNeighbourAnimals.stream()
//      .filter(neighbour -> neighbour instanceof Herbivore)
//      .map(neighbour -> (Herbivore) neighbour)
//      .collect(Collectors.toList());
//  }

  <T extends Animal> List<T> findNeighbouringSameSpecies(Animal animal, Class<?> clazz) {
    Cell animalPosition = animal.getLivingArea();
    List<Animal> listNeighbourAnimals = findValidNeighbourCoordinates(animalPosition);

    return listNeighbourAnimals.stream()
      .filter(clazz::isInstance)
      .map(neighbour -> (T) neighbour)
      .collect(Collectors.toList());
  }

  private List<Animal> findValidNeighbourCoordinates(Cell animalPosition) {
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
