package net.tamasnovak.logic.animalFactory;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalSpecies;
import net.tamasnovak.model.animal.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public interface AbstractFactory<T extends Animal> {
  T createAnimal(AnimalType animalType, AnimalSpecies animalSpecies, Cell livingArea);
}
