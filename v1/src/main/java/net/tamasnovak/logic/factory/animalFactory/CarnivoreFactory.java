package net.tamasnovak.logic.factory.animalFactory;

import net.tamasnovak.logic.routine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.logic.routine.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.carnivore.Carnivore;
import net.tamasnovak.model.nature.animal.carnivore.Leopard;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class CarnivoreFactory {
  private final Random random;
  private final HuntingRoutine huntingRoutine;
  private final BreedingRoutine breedingRoutine;

  public CarnivoreFactory(Random random, HuntingRoutine huntingRoutine, BreedingRoutine breedingRoutine) {
    this.random = random;
    this.huntingRoutine = huntingRoutine;
    this.breedingRoutine = breedingRoutine;
  }

  Carnivore createCarnivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Carnivore animal = null;

    switch (animalSpecies) {
      case LEOPARD -> animal = createLeopard(livingArea);
    }

    return animal;
  }

  private Leopard createLeopard(Cell livingArea) {
    return new Leopard(random, livingArea, huntingRoutine, breedingRoutine);
  }
}
