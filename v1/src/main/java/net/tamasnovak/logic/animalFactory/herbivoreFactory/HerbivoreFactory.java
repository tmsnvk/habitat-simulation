package net.tamasnovak.logic.animalFactory.herbivoreFactory;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class HerbivoreFactory {
  private final Random random;

  public HerbivoreFactory(Random random) {
    this.random = random;
  }

  public Animal createHerbivore(AnimalSpecies animalSpecies, Cell livingArea) {
    Herbivore animal = null;

    switch (animalSpecies) {
      case ZEBRA -> animal = createZebra(livingArea, random);
    }

    return animal;
  }

  private Zebra createZebra(Cell livingArea, Random random) {
    return new Zebra(livingArea, random);
  }
}
