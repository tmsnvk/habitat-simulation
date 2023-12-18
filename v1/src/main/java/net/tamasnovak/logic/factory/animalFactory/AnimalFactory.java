package net.tamasnovak.logic.factory.animalFactory;

import net.tamasnovak.model.nature.animal.Animal;
import net.tamasnovak.model.nature.animal.AnimalSpecies;
import net.tamasnovak.model.nature.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public final class AnimalFactory {
  private final CarnivoreFactory carnivoreFactory;
  private final HerbivoreFactory herbivoreFactory;

  public AnimalFactory(HerbivoreFactory herbivoreFactory, CarnivoreFactory carnivoreFactory) {
    this.herbivoreFactory = herbivoreFactory;
    this.carnivoreFactory = carnivoreFactory;
  }

  public Animal createAnimal(AnimalType animalType, AnimalSpecies animalSpecies, Cell livingArea) {
    Animal animal = null;

    switch (animalType) {
      case CARNIVORE -> animal = carnivoreFactory.createCarnivore(animalSpecies, livingArea);
      case HERBIVORE -> animal = herbivoreFactory.createHerbivore(animalSpecies, livingArea);
    }

    return animal;
  }
}
