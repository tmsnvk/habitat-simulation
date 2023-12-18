package net.tamasnovak.model.nature.animal.carnivore;

import net.tamasnovak.logic.routine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Carnivore extends Animal implements Hunting {
  protected static final AnimalType TYPE = AnimalType.CARNIVORE;
  protected int hungerLevel;
  private final int maximumHungerLevel;
  private final HuntingRoutine huntingRoutine;

  public Carnivore(
    String id,
    int maximumHungerLevel,
    Cell coordinates,
    int maximumAge,
    String animalIcon,
    AnimalSpecies animalSpecies,
    HuntingRoutine huntingRoutine,
    BreedingRoutine breedingRoutine) {
    super(id, coordinates, maximumAge, animalIcon, animalSpecies, TYPE, breedingRoutine);
    this.hungerLevel = 0;
    this.maximumHungerLevel = maximumHungerLevel;
    this.huntingRoutine = huntingRoutine;
  }

  @Override
  public void doLifeCycleMethods() {
    increaseAge();
    hunt();
    breed();
//    move();
  }

  @Override
  public void hunt() {
    if (this.isAlive) {
      huntingRoutine.run(this);
      dieIfTooHungry();
    }
  }

  @Override
  public void dieIfTooHungry() {
    if (this.hungerLevel == maximumHungerLevel) {
      die();
    }
  }

  @Override
  public void increaseHungerLevel() {
    this.hungerLevel++;
  }

  @Override
  public void resetHungerLevel() {
    this.hungerLevel = 0;
  }
}
