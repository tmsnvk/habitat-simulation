package net.tamasnovak.model.animal.carnivore;

import net.tamasnovak.model.animal.Animal;
import net.tamasnovak.model.animal.AnimalType;
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

  private void setHungerLevel(int hungerLevel) {
    this.hungerLevel = hungerLevel;
  }

  @Override
  public void increaseHungerLevelAfterUnsuccessfulHunt() {
    this.setHungerLevel(this.getHungerLevel() + 1);
  }

  @Override
  public void resetHungerLevelAfterSuccessfulHunt() {
    this.setHungerLevel(0);
  }
}
