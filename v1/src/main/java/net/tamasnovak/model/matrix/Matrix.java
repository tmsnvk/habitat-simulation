package net.tamasnovak.model.matrix;

import net.tamasnovak.model.animals.Animal;

public class Matrix {
  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
  private final Animal[][] matrix;

  public Matrix() {
    this.matrix = new Animal[LENGTH][WIDTH];
    generateMatrix();
  }

  private void generateMatrix() {
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
