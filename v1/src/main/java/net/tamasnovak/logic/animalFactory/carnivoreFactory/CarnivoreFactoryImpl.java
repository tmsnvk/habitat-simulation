package net.tamasnovak.logic.animalFactory.carnivoreFactory;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.carnivore.Carnivore;
import net.tamasnovak.model.animal.carnivore.Lion;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public class CarnivoreFactoryImpl implements CarnivoreFactory {
  private Carnivore animal;

  @Override
  public Lion createLion(Cell livingArea, Random random) {
    return new Lion(livingArea, random);
  }

  public Animal createCarnivore(AnimalSpecies animalSpecies, Cell livingArea, Random random) {
    switch (animalSpecies) {
      case LION -> animal = createLion(livingArea, random);
    }

    return animal;
  }
}
