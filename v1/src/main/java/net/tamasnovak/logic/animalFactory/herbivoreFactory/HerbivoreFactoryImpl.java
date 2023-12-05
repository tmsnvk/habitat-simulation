package net.tamasnovak.logic.animalFactory.herbivoreFactory;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.herbivore.Herbivore;
import net.tamasnovak.model.animal.herbivore.Zebra;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public class HerbivoreFactoryImpl implements HerbivoreFactory {
  private Herbivore animal;

  @Override
  public Zebra createZebra(Cell livingArea, Random random) {
    return new Zebra(livingArea, random);
  }

  public Animal createAnimal(AnimalSpecies animalSpecies, Cell livingArea, Random random) {
    switch (animalSpecies) {
      case ZEBRA -> animal = createZebra(livingArea, random);
    }

    return animal;
  }
}
