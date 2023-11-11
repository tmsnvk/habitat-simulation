package net.tamasnovak.model.animals.carnivors;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.savannah.Cell;

public class Carnivore extends Animal {
  private static final int[] MAXIMUM_AGE = new int[]{ 9, 10, 11, 12 };
  private int hungerLevel;

  public Carnivore(Cell livingArea) {
    super(MAXIMUM_AGE, livingArea);
    this.hungerLevel = 0;
  }
}
