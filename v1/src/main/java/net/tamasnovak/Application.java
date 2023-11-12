package net.tamasnovak;

import net.tamasnovak.model.matrix.Matrix;
import net.tamasnovak.logic.savannah.Savannah;

public class Application {
  public static void main(String[] args) {
    Matrix matrix = new Matrix();
    Savannah savannah = new Savannah(matrix);

    savannah.runSimulation();
  }
}
