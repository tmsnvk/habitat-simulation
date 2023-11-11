package net.tamasnovak.model.savannah;

import net.tamasnovak.model.animals.Animal;

public class Matrix {
  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
  private final Animal[][] matrix;

  public Matrix() {
    this.matrix = new Animal[20][20];
    generateSavannahMatrix();
  }

  private void generateSavannahMatrix() {
    for (int x = 0; x < LENGTH; x++) {
      for (int y = 0; y < WIDTH; y++) {
        matrix[x][y] = null;
      }
    }
  }

  public Animal[][] getMatrix() {
    return matrix;
  }
}
