package net.tamasnovak.logic.factory.animalFactory;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.movementRoutine.MovementRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.herbivore.Herbivore;
import net.tamasnovak.model.nature.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class HerbivoreFactory {
  private final Random random;
  private final AgingRoutine agingRoutine;
  private final BreedingRoutine breedingRoutine;
  private final MovementRoutine movementRoutine;

  public HerbivoreFactory(Random random, AgingRoutine agingRoutine, BreedingRoutine breedingRoutine, MovementRoutine movementRoutine) {
    this.random = random;
    this.agingRoutine = agingRoutine;
    this.breedingRoutine = breedingRoutine;
    this.movementRoutine = movementRoutine;
  }

  Herbivore createHerbivore(AnimalSpecies animalSpecies, Cell coordinates) {
    Herbivore animal = null;

    switch (animalSpecies) {
      case ZEBRA -> animal = createZebra(coordinates);
    }

    return animal;
  }

  private Zebra createZebra(Cell coordinates) {
    return new Zebra(random, coordinates, agingRoutine, breedingRoutine, movementRoutine);
  }
}
