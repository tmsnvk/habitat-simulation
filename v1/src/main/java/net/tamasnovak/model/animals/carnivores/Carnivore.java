package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public class Carnivore extends Animal {
  private int hungerLevel;

  public Carnivore(Cell livingArea, int[] MAXIMUM_AGE, AnimalType animalType) {
    super(livingArea, MAXIMUM_AGE, animalType);
    this.hungerLevel = 0;
  }
}
