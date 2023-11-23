package net.tamasnovak.model.animal.herbivore;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Herbivore extends Animal implements Hunted {
  public Herbivore(Cell livingArea, int maximumAge, AnimalType animalType) {
    super(livingArea, maximumAge, animalType);
  }

  @Override
  public void perishIfKilledByCarnivore() {
    this.setAlive(false);
  }
}
