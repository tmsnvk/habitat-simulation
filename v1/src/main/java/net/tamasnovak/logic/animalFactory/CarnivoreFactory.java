package net.tamasnovak.logic.animalFactory;

import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.carnivore.Leopard;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class CarnivoreFactory {
  private final Random random;

  public CarnivoreFactory(Random random) {
    this.random = random;
  }

  Carnivore createCarnivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Carnivore animal = null;

    switch (animalSpecies) {
      case LEOPARD -> animal = createLeopard(livingArea);
    }

    return animal;
  }

  private Leopard createLeopard(Cell livingArea) {
    return new Leopard(livingArea, random);
  }
}
