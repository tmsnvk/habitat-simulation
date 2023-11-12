package net.tamasnovak.model.animals.herbivores;

import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public class Zebra extends Herbivore {
  private static final int[] MAXIMUM_AGE = new int[]{ 11, 12, 13, 14 };
  private static final AnimalType animalType = AnimalType.ZEBRA;

  public Zebra(Cell livingArea) {
    super(livingArea, MAXIMUM_AGE, animalType);
  }
}
