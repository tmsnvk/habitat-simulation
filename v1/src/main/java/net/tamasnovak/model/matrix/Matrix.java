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
import net.tamasnovak.ui.simulation.SimulationUserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Matrix {
  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
  private static final List<List<Integer>> POSSIBLE_NEARBY_POSITION_DIFFERENCES = List.of(
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
  private final SimulationUserInput simulationUserInput;
  private final VegetationFactory vegetationFactory;
  private AnimalFactory animalFactory;

  public Matrix(SimulationUserInput simulationUserInput, VegetationFactory vegetationFactory) {
    this.simulationUserInput = simulationUserInput;
    this.vegetationFactory = vegetationFactory;
    this.animalFactory = null;
    this.matrix = new Nature[LENGTH][WIDTH];
    createHabitat();
  }

  private void createHabitat() {
    for (int x = 0; x < LENGTH; x++) {
      for (int y = 0; y < WIDTH; y++) {
        Position position = new Position(x, y);

        matrix[x][y] = vegetationFactory.createVegetation(simulationUserInput.getVegetationType(), simulationUserInput.getVegetationSpecies(), position);
      }
    }
  }

  public void printMatrix() {
    for (Nature[] nature : matrix) {
      for (Nature thing : nature) {
        System.out.print(thing.getIcon() + thing.getPosition().xCoordinate() +" "+ thing.getPosition().yCoordinate() + " ");
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
    Position position = new Position(xCoordinate, yCoordinate);

    return vegetationFactory.createVegetation(vegetationType,vegetationSpecies, position);
  }

  public Animal createAnimal(AnimalType animalType, AnimalSpecies animalSpecies, int xCoordinate, int yCoordinate) {
    Position position = new Position(xCoordinate, yCoordinate);

    return animalFactory.createAnimal(animalType, animalSpecies, position);
  }

  public void placeNatureInstanceByPosition(int xCoordinate, int yCoordinate, Nature natureInstance) {
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
    Position animalPosition = animalInstance.getPosition();
    List<Nature> validNeighbourPositions = findValidNeighbourPositions(animalPosition);

    return validNeighbourPositions.stream()
      .filter(targetAnimalClass::isInstance)
      .map(neighbour -> (T) neighbour)
      .collect(Collectors.toList());
  }

  public List<Vegetation> findNeighbourVegetation(Animal animalInstance) {
    Position animalPosition = animalInstance.getPosition();
    List<Nature> validNeighbourPositions = findValidNeighbourPositions(animalPosition);

    return validNeighbourPositions.stream()
      .filter(Vegetation.class::isInstance)
      .map(Vegetation.class::cast)
      .collect(Collectors.toList());
  }

  private List<Nature> findValidNeighbourPositions(Position animalPosition) {
    List<Nature> neighbourAnimalsInValidPositions = new ArrayList<>();

    for (List<Integer> position : POSSIBLE_NEARBY_POSITION_DIFFERENCES) {
      int xCoordinate = animalPosition.xCoordinate() + position.get(0);
      int yCoordinate = animalPosition.yCoordinate() + position.get(1);

      if (isPositionValid(xCoordinate, yCoordinate)) {
        Nature animal = findPosition(xCoordinate, yCoordinate);

        neighbourAnimalsInValidPositions.add(animal);
      }
    }

    return neighbourAnimalsInValidPositions;
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
        int xCoordinate = animal.getPosition().xCoordinate();
        int yCoordinate = animal.getPosition().yCoordinate();

        Position position = new Position(xCoordinate, yCoordinate);
        Vegetation vegetation = vegetationFactory.createVegetation(vegetationType, vegetationSpecies, position);
        matrix[xCoordinate][yCoordinate] = vegetation;
      });
  }

  private boolean isPositionValid(int xCoordinate, int yCoordinate) {
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
