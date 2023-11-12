package net.tamasnovak.model.animals.herbivores;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Herbivore extends Animal {
  public Herbivore(Cell livingArea, int[] MAXIMUM_AGE, AnimalType animalType) {
    super(livingArea, MAXIMUM_AGE, animalType);
  }
}
