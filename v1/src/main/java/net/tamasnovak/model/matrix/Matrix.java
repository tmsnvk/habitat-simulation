package net.tamasnovak.model.matrix;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Matrix {
  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
  private static final List<List<Integer>> POSSIBLE_NEARBY_COORDINATE_DIFFERENCES = List.of(
    // up-down, left-right
    List.of(0, 1),
    List.of(0, -1),
    List.of(-1, 0),
    List.of(1, 0)
    // across
//    List.of(1, 1),
//    List.of(1, -1),
//    List.of(-1, -1),
//    List.of(-1, 1)
  );
  private final Random random;
  private final Animal[][] matrix;

  public Matrix(Random random) {
    this.random = random;
    this.matrix = new Animal[LENGTH][WIDTH];
  }

  public int getLength() {
    return LENGTH;
  }

  public int getWidth() {
    return WIDTH;
  }

  public Animal findAnimalByCoordinate(int xCoordinate, int yCoordinate) {
    return matrix[xCoordinate][yCoordinate];
  }

  public void placeAnimalByCoordinate(int xCoordinate, int yCoordinate, Animal animal) {
    matrix[xCoordinate][yCoordinate] = animal;
  }

  public Set<AnimalSpecies> findDistinctAnimalSpecies() {
    return Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Objects::nonNull)
      .map(Animal::getAnimalType)
      .collect(Collectors.toSet());
  }

  public int countAnimalsBySpecies(AnimalSpecies animalSpecies) {
    return (int) Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(animal -> animal != null && animal.getAnimalType().equals(animalSpecies))
      .count();
  }

  public <T extends Animal> List<T> findNeighbourAnimalTypeInstances(Animal animalToCheckAgainst, Class<T> animalTypeToFind) {
    Cell animalPosition = animalToCheckAgainst.getLivingArea();
    List<Animal> neighbourAnimals = findNeighbourAnimalsInValidCoordinates(animalPosition);

    return neighbourAnimals.stream()
      .filter(animalTypeToFind::isInstance)
      .map(neighbour -> (T) neighbour)
      .collect(Collectors.toList());
  }

  private List<Animal> findNeighbourAnimalsInValidCoordinates(Cell animalPosition) {
    List<Animal> neighbourAnimalsInValidCoordinates = new ArrayList<>();

    for (List<Integer> coordinate : POSSIBLE_NEARBY_COORDINATE_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + coordinate.get(0);
      int yCoordinate = animalPosition.yCoordinate() + coordinate.get(1);

      if (areCoordinatesValid(xCoordinate, yCoordinate)) {
        Animal animal = findAnimalByCoordinate(xCoordinate, yCoordinate);

        neighbourAnimalsInValidCoordinates.add(animal);
      }
    }

    return neighbourAnimalsInValidCoordinates;
  }

  private boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
    return xCoordinate >= 0 && xCoordinate < LENGTH && yCoordinate >= 0 && yCoordinate < WIDTH;
  }

  public List<Animal> findAliveAnimals() {
    return Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(animal -> animal != null && animal.isAlive())
      .collect(Collectors.toList());
  }

  public void removeDeadAnimals() {
    Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(animal -> animal != null && !animal.isAlive())
      .collect(Collectors.toList())
      .forEach(animal -> {
        int xCoordinate = animal.getLivingArea().xCoordinate();
        int yCoordinate = animal.getLivingArea().yCoordinate();

        matrix[xCoordinate][yCoordinate] = null;
      });
  }
}
