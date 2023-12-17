package net.tamasnovak.model.animal.carnivore;

import net.tamasnovak.logic.routines.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Carnivore extends Animal implements Hunting {
  protected static final AnimalType TYPE = AnimalType.CARNIVORE;
  protected int hungerLevel;
  private final HuntingRoutine huntingRoutine;

  public Carnivore(Cell livingArea, int maximumAge, String animalIcon, AnimalSpecies animalSpecies, HuntingRoutine huntingRoutine) {
    super(livingArea, maximumAge, animalIcon, animalSpecies, TYPE);
    this.hungerLevel = 0;
    this.huntingRoutine = huntingRoutine;
  }

  int getHungerLevel() {
    return hungerLevel;
  }

  private void setHungerLevel(int hungerLevel) {
    this.hungerLevel = hungerLevel;
  }

  @Override
  public void doLifeCycleMethods() {
    increaseAge();
    hunt();
    breed();
    move();
  }

  @Override
  public void hunt() {
    if (this.isAlive) {
      huntingRoutine.run(this);
      dieIfTooHungry();
    }
  }

  @Override
  public void increaseHungerLevel() {
    this.setHungerLevel(this.getHungerLevel() + 1);
  }

  @Override
  public void resetHungerLevel() {
    this.setHungerLevel(0);
  }
}
