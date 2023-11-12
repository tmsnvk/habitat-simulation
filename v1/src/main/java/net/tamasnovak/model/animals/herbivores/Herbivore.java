package net.tamasnovak.model.animals.herbivores;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.matrix.Cell;

public class Herbivore extends Animal {
  private static final int[] MAXIMUM_AGE = new int[]{ 11, 12, 13, 14 };

  public Herbivore(Cell livingArea) {
    super(MAXIMUM_AGE, livingArea);
  }
}
