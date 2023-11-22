package net.tamasnovak.model.matrix;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.animals.AnimalType;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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

//  public Animal[][] getMatrix() {
//    return matrix;
//  }

  public Animal getCoordinate(int xCoordinate, int yCoordinate) {
    return matrix[xCoordinate][yCoordinate];
  }

  public void placeAnimalInCoordinate(int xCoordinate, int yCoordinate, Animal animal) {
    matrix[xCoordinate][yCoordinate] = animal;
  }

  public Set<AnimalType> listAnimalTypes() {
    Set<AnimalType> animalTypes = new HashSet<>();

    Stream.of(matrix)
      .flatMap(Stream::of)
      .forEach(animal -> {
        if (animal != null) {
          animalTypes.add(animal.getAnimalType());
        }
      });

    return animalTypes;
  }

  public int countAnimalType(AnimalType animalType) {
    return (int) Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(animal -> animal != null && animal.getAnimalType() == animalType)
      .count();
  }

  public List<Animal> getAnimalsCurrentlyOnSavannah() {
    return Stream.of(matrix)
      .flatMap(Stream::of)
      .filter(Objects::nonNull)
      .collect(Collectors.toList());
  }
}
