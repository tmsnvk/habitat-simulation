package net.tamasnovak.model.nature.animal.herbivore;

import net.tamasnovak.logic.routine.animalRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Position;

public abstract class Herbivore extends Animal {
  protected static final AnimalType TYPE = AnimalType.HERBIVORE;

  public Herbivore(String id, int maximumCellMovement, Position position, int maximumAge, String animalIcon, AnimalSpecies animalSpecies, AgingRoutine agingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    super(id, maximumCellMovement, position, maximumAge, animalIcon, animalSpecies, TYPE, agingRoutine, breedingRoutine, movementRoutine);
  }

  @Override
  public void doLifeCycleMethods() {
    agingRoutine.run(this);

    if (!isAlive) {
      return;
    }

    breedingRoutine.run(this);
    movementRoutine.run(this);
  }
}
