package net.tamasnovak.model.animal.herbivore;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Herbivore extends Animal implements Hunted {
  protected static final AnimalType TYPE = AnimalType.HERBIVORE;

  public Herbivore(Cell livingArea, int maximumAge, String animalIcon, AnimalSpecies animalSpecies) {
    super(livingArea, maximumAge, animalIcon, animalSpecies, TYPE);
  }

  @Override
  public void doLifeCycleMethods() {
    increaseAge();

    if (!isAlive) {
      return;
    }

    breed();
    move();
  }

  @Override
  public void dieByBeingHunted() {
    die();
  }
}
