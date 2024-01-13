package net.tamasnovak.model.nature.animal.carnivore;

import net.tamasnovak.logic.routine.animalRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.HuntingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Position;

public abstract class Carnivore extends Animal {
  protected static final AnimalType TYPE = AnimalType.CARNIVORE;
  protected int hungerLevel;
  private final int maximumHungerLevel;
  private final HuntingRoutine huntingRoutine;

  public Carnivore(String id, int maximumHungerLevel, int maximumCellMovement, Position position, int maximumAge, String animalIcon, AnimalSpecies animalSpecies, AgingRoutine agingRoutine, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    super(id, maximumCellMovement, position, maximumAge, animalIcon, animalSpecies, TYPE, agingRoutine, breedingRoutine, movementRoutine);
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
