package net.tamasnovak.logic.animalFactory.carnivoreFactory;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.carnivore.Lion;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public class CarnivoreFactory {
  public Animal createCarnivore(AnimalSpecies animalSpecies, Cell livingArea, Random random) {
    Carnivore animal = null;

    switch (animalSpecies) {
      case LION -> animal = createLion(livingArea, random);
    }

    return animal;
  }

  private Lion createLion(Cell livingArea, Random random) {
    return new Lion(livingArea, random);
  }
}
