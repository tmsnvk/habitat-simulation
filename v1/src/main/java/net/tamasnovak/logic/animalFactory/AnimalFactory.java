package net.tamasnovak.logic.animalFactory;

import net.tamasnovak.logic.animalFactory.carnivoreFactory.CarnivoreFactoryImpl;
import net.tamasnovak.logic.animalFactory.herbivoreFactory.HerbivoreFactoryImpl;
import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

import java.util.Random;

public final class AnimalFactory implements AbstractFactory<Animal> {
  private final Random random;
  private Animal animal;

  public AnimalFactory(Random random) {
    this.random = random;
  }

  public Animal createAnimal(AnimalType animalType, AnimalSpecies animalSpecies, Cell livingArea) {
    switch (animalType) {
      case CARNIVORE -> animal = new CarnivoreFactoryImpl().createAnimal(animalSpecies, livingArea, random);
      case HERBIVORE -> animal = new HerbivoreFactoryImpl().createAnimal(animalSpecies, livingArea, random);
    }

    return animal;
  }
}
