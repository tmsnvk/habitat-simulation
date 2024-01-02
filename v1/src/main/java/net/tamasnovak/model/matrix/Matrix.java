package net.tamasnovak.model.matrix;

import net.tamasnovak.logic.factory.animalFactory.AnimalFactory;
import net.tamasnovak.logic.factory.vegetationFactory.VegetationFactory;
import net.tamasnovak.model.nature.Nature;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.nature.vegetation.Vegetation;
import net.tamasnovak.model.nature.vegetation.VegetationSpecies;
import net.tamasnovak.model.nature.vegetation.VegetationType;

import java.util.ArrayList;
import java.util.List;
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
  private final Nature[][] matrix;
  private final VegetationFactory vegetationFactory;
  private AnimalFactory animalFactory;

  public Matrix(VegetationFactory vegetationFactory) {
    this.vegetationFactory = vegetationFactory;
    this.animalFactory = null;
    this.matrix = new Nature[LENGTH][WIDTH];
    createHabitat();
  }

  private void createHabitat() {
    for (int x = 0; x < LENGTH; x++) {
      for (int y = 0; y < WIDTH; y++) {
        Cell coordinates = new Cell(x, y);
        // replace this hardcoded value with whatever is in the config class after the user has chosen a habitat.
        matrix[x][y] = vegetationFactory.createVegetation(VegetationType.GRASS, VegetationSpecies.FINGER_GRASS, coordinates);
      }
    }
  }

  public void printMatrix() {
    for (Nature[] nature : matrix) {
      for (Nature thing : nature) {
        System.out.print(thing.getIcon() + thing.getCoordinates().xCoordinate() +" "+ thing.getCoordinates().yCoordinate() + " ");
      }

      System.out.println();
    }
  }

  public int getLength() {
    return LENGTH;
  }

  public int getWidth() {
    return WIDTH;
  }

  public void setAnimalFactory(AnimalFactory animalFactory) {
    this.animalFactory = animalFactory;
  }

  public Nature findPosition(int xCoordinate, int yCoordinate) {
    return matrix[xCoordinate][yCoordinate];
  }

  public Vegetation createVegetation(VegetationType vegetationType, VegetationSpecies vegetationSpecies, int xCoordinate, int yCoordinate) {
    Cell position = new Cell(xCoordinate, yCoordinate);

    return vegetationFactory.createVegetation(vegetationType,vegetationSpecies, position);
  }

  public Animal createAnimal(AnimalType animalType, AnimalSpecies animalSpecies, int xCoordinate, int yCoordinate) {
    Cell position = new Cell(xCoordinate, yCoordinate);

    return animalFactory.createAnimal(animalType, animalSpecies, position);
  }

  public void placeNatureInstanceByCoordinate(int xCoordinate, int yCoordinate, Nature natureInstance) {
    matrix[xCoordinate][yCoordinate] = natureInstance;
  }

  public Set<AnimalSpecies> findDistinctAnimalSpecies() {
    return Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Animal.class::isInstance)
      .map(Animal.class::cast)
      .map(Animal::getSpecies)
      .collect(Collectors.toSet());
  }

  public int countAnimalsBySpecies(AnimalSpecies animalSpecies) {
    return (int) Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Animal.class::isInstance)
      .map(Animal.class::cast)
      .filter(animal -> animal.getSpecies().equals(animalSpecies))
      .count();
  }

  public <T extends Animal> List<T> findNeighbourAnimalsByTypeOrSpecies(Animal animalInstance, Class<T> targetAnimalClass) {
    Cell animalPosition = animalInstance.getCoordinates();
    List<Nature> validNeighbourCoordinates = findValidNeighbourCoordinates(animalPosition);

    return validNeighbourCoordinates.stream()
      .filter(targetAnimalClass::isInstance)
      .map(neighbour -> (T) neighbour)
      .collect(Collectors.toList());
  }

  public List<Vegetation> findNeighbourVegetation(Animal animalInstance) {
    Cell animalPosition = animalInstance.getCoordinates();
    List<Nature> validNeighbourCoordinates = findValidNeighbourCoordinates(animalPosition);

    return validNeighbourCoordinates.stream()
      .filter(Vegetation.class::isInstance)
      .map(Vegetation.class::cast)
      .collect(Collectors.toList());
  }

  private List<Nature> findValidNeighbourCoordinates(Cell animalPosition) {
    List<Nature> neighbourAnimalsInValidCoordinates = new ArrayList<>();

    for (List<Integer> coordinate : POSSIBLE_NEARBY_COORDINATE_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + coordinate.get(0);
      int yCoordinate = animalPosition.yCoordinate() + coordinate.get(1);

      if (areCoordinatesValid(xCoordinate, yCoordinate)) {
        Nature animal = findPosition(xCoordinate, yCoordinate);

        neighbourAnimalsInValidCoordinates.add(animal);
      }
    }

    return neighbourAnimalsInValidCoordinates;
  }

  public List<Animal> findAliveAnimals() {
    return Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Animal.class::isInstance)
      .map(Animal.class::cast)
      .collect(Collectors.toList());
  }

  public void replaceDeadAnimalsWithVegetation(VegetationType vegetationType, VegetationSpecies vegetationSpecies) {
    Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Animal.class::isInstance)
      .map(Animal.class::cast)
      .filter(animal -> !animal.isAlive())
      .forEach(animal -> {
        int xCoordinate = animal.getCoordinates().xCoordinate();
        int yCoordinate = animal.getCoordinates().yCoordinate();

        Cell position = new Cell(xCoordinate, yCoordinate);
        Vegetation vegetation = vegetationFactory.createVegetation(vegetationType, vegetationSpecies, position);
        matrix[xCoordinate][yCoordinate] = vegetation;
      });
  }

  private boolean areCoordinatesValid(int xCoordinate, int yCoordinate) {
    return xCoordinate >= 0 && xCoordinate < LENGTH && yCoordinate >= 0 && yCoordinate < WIDTH;
  }

  public void setBreedingStatusToDefault() {
    Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Animal.class::isInstance)
      .map(Animal.class::cast)
      .forEach(animal -> animal.setDidAlreadyBreedInRunningYear(false));
  }
}
