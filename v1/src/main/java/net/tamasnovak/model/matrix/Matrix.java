package net.tamasnovak.model.matrix;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Matrix {
  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
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

  public Set<AnimalSpecies> findAllAnimalSpecies() {
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
