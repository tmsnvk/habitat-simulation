package net.tamasnovak.logic.factory.animalFactory;

import net.tamasnovak.logic.routine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.herbivore.Herbivore;
import net.tamasnovak.model.nature.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class HerbivoreFactory {
  private final Random random;
  private final BreedingRoutine breedingRoutine;

  public HerbivoreFactory(Random random, BreedingRoutine breedingRoutine) {
    this.random = random;
    this.breedingRoutine = breedingRoutine;
  }

  Herbivore createHerbivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Herbivore animal = null;

    switch (animalSpecies) {
      case ZEBRA -> animal = createZebra(livingArea);
    }

    return animal;
  }

  private Zebra createZebra(Cell livingArea) {
    return new Zebra(random, livingArea, breedingRoutine);
  }
}
