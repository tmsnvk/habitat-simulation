package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.matrix.Cell;

public class Carnivore extends Animal {
  private int hungerLevel;

  public Carnivore(Cell livingArea, int[] MAXIMUM_AGE) {
    super(livingArea, MAXIMUM_AGE);
    this.hungerLevel = 0;
  }
}
