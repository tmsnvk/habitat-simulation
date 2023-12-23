package net.tamasnovak.logic.factory.animalFactory;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.movementRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.carnivore.Carnivore;
import net.tamasnovak.model.nature.animal.carnivore.Leopard;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class CarnivoreFactory {
  private final Random random;
  private final AgingRoutine agingRoutine;
  private final HuntingRoutine huntingRoutine;
  private final BreedingRoutine breedingRoutine;
  private final MovementRoutine movementRoutine;

  public CarnivoreFactory(Random random, AgingRoutine agingRoutine, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    this.random = random;
    this.agingRoutine = agingRoutine;
    this.huntingRoutine = huntingRoutine;
    this.breedingRoutine = breedingRoutine;
    this.movementRoutine = movementRoutine;
  }

  Carnivore createCarnivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Carnivore animal = null;

    switch (animalSpecies) {
      case LEOPARD -> animal = createLeopard(livingArea);
    }

    return animal;
  }

  private Leopard createLeopard(Cell livingArea) {
    return new Leopard(random, livingArea, agingRoutine, huntingRoutine, breedingRoutine, movementRoutine);
  }
}
