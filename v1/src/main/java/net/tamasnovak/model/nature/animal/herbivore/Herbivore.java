package net.tamasnovak.model.nature.animal.herbivore;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Herbivore extends Animal implements Hunted {
  protected static final AnimalType TYPE = AnimalType.HERBIVORE;

  public Herbivore(
    String id,
    Cell coordinates,
    int maximumAge,
    String animalIcon,
    AnimalSpecies animalSpecies,
    AgingRoutine agingRoutine,
    BreedingRoutine breedingRoutine) {
    super(id, coordinates, maximumAge, animalIcon, animalSpecies, TYPE, agingRoutine, breedingRoutine);
  }

  @Override
  public void doLifeCycleMethods() {
    increaseAge();

    if (!isAlive) {
      return;
    }

    breed();
//    move();
  }

  @Override
  public void dieByBeingHunted() {
    die();
  }
}
