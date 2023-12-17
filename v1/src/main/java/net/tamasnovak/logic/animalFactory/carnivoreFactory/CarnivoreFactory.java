package net.tamasnovak.logic.animalFactory.carnivoreFactory;

import net.tamasnovak.model.animal.Animal;
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

  public Animal createCarnivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Carnivore animal = null;

    switch (animalSpecies) {
      case LEOPARD -> animal = createLeopard(livingArea, random);
    }

    return animal;
  }

  private Leopard createLeopard(Cell livingArea, Random random) {
    return new Leopard(livingArea, random);
  }
}
