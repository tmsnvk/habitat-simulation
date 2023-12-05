package net.tamasnovak.model.matrix;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {
  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
  private final Animal[][] matrix;

  public Matrix() {
    this.matrix = new Animal[LENGTH][WIDTH];
    generateEmptyMatrix();
  }

  private void generateEmptyMatrix() {
    for (int x = 0; x < LENGTH; x++) {
      for (int y = 0; y < WIDTH; y++) {
        matrix[x][y] = null;
      }
    }
  }

  public int getLength() {
    return LENGTH;
  }

  public int getWidth() {
    return WIDTH;
  }

  public Animal[][] getMatrix() {
    return matrix;
  }

  public Animal findAnimalInCoordinate(int xCoordinate, int yCoordinate) {
    return matrix[xCoordinate][yCoordinate];
  }

  public void placeAnimalInCoordinate(int xCoordinate, int yCoordinate, Animal animal) {
    matrix[xCoordinate][yCoordinate] = animal;
  }

  public void removeDeadAnimal(Animal animal) {
    int xCoordinate = animal.getLivingArea().xCoordinate();
    int yCoordinate = animal.getLivingArea().yCoordinate();

    matrix[xCoordinate][yCoordinate] = null;
  }

  public Set<AnimalSpecies> findAllAnimaLType() {
    Set<AnimalSpecies> animalSpecies = new HashSet<>();

    Stream.of(matrix)
      .flatMap(Stream::of)
      .forEach(animal -> {
        if (animal != null) {
          animalSpecies.add(animal.getAnimalType());
        }
      });

    return animalSpecies;
  }

  public int countAnimalType(AnimalSpecies animalSpecies) {
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
}
