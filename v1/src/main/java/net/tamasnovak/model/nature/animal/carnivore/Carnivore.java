package net.tamasnovak.model.nature.animal.carnivore;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.movementRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Carnivore extends Animal {
  protected static final AnimalType TYPE = AnimalType.CARNIVORE;
  protected int hungerLevel;
  private final int maximumHungerLevel;
  private final HuntingRoutine huntingRoutine;

  public Carnivore(String id, int maximumHungerLevel, int maximumCellMovement, Cell coordinates, int maximumAge, String animalIcon, AnimalSpecies animalSpecies, AgingRoutine agingRoutine, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    super(id, maximumCellMovement, coordinates, maximumAge, animalIcon, animalSpecies, TYPE, agingRoutine, breedingRoutine, movementRoutine);
    this.hungerLevel = 0;
    this.maximumHungerLevel = maximumHungerLevel;
    this.huntingRoutine = huntingRoutine;
  }

  public int getHungerLevel() {
    return hungerLevel;
  }

  public void setHungerLevel(int hungerLevel) {
    this.hungerLevel = hungerLevel;
  }

  public int getMaximumHungerLevel() {
    return maximumHungerLevel;
  }

  @Override
  public void doLifeCycleMethods() {
    agingRoutine.run(this);

    if (!isAlive) {
      return;
    }

    huntingRoutine.run(this);
    breedingRoutine.run(this);
    movementRoutine.run(this);
  }
}
