package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public class Lion extends Carnivore {
  private static final int[] MAXIMUM_AGE = new int[]{ 9, 10, 11, 12 };
  private static final AnimalType animalType = AnimalType.LION;

  public Lion(Cell livingArea) {
    super(livingArea, MAXIMUM_AGE);
  }
}
