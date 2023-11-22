package net.tamasnovak.model.animals.carnivores;

import net.tamasnovak.model.animals.Animal;
import net.tamasnovak.model.animals.AnimalType;
import net.tamasnovak.model.matrix.Cell;

public abstract class Carnivore extends Animal implements Hunting {
  protected int hungerLevel;

  public Carnivore(Cell livingArea, int maximumAge, AnimalType animalType) {
    super(livingArea, maximumAge, animalType);
    this.hungerLevel = 0;
  }

  int getHungerLevel() {
    return hungerLevel;
  }

  void setHungerLevel(int hungerLevel) {
    this.hungerLevel = hungerLevel;
  }
}
