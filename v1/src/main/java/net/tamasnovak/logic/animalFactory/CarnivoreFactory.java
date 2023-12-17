package net.tamasnovak.logic.animalFactory;

import net.tamasnovak.logic.routines.huntingRoutine.HuntingRoutine;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.carnivore.Leopard;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class CarnivoreFactory {
  private final Random random;
  private final HuntingRoutine huntingRoutine;

  public CarnivoreFactory(Random random, HuntingRoutine huntingRoutine) {
    this.random = random;
    this.huntingRoutine = huntingRoutine;
  }

  Carnivore createCarnivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Carnivore animal = null;

    switch (animalSpecies) {
      case LEOPARD -> animal = createLeopard(livingArea);
    }

    return animal;
  }

  private Leopard createLeopard(Cell livingArea) {
    return new Leopard(livingArea, huntingRoutine, random);
  }
}
