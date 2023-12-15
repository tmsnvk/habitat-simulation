package net.tamasnovak.logic.animalFactory.herbivoreFactory;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public class HerbivoreFactory {
  public Animal createHerbivore(AnimalSpecies animalSpecies, Cell livingArea, Random random) {
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
