package net.tamasnovak.model.animal;

import net.tamasnovak.model.matrix.Cell;

public abstract class Animal {
  private int currentAge;
  private final int maximumAge;
  protected boolean isAlive;
  private Cell livingArea;
  private final AnimalType animalType;

  public Animal(Cell livingArea, int maximumAge, AnimalType animalType) {
    this.currentAge = 0;
    this.isAlive = true;
    this.livingArea = livingArea;
    this.maximumAge = maximumAge;
    this.animalType = animalType;
  }

  public int getCurrentAge() {
    return currentAge;
  }

  private void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }

  public Cell getLivingArea() {
    return livingArea;
  }

  public void setLivingArea(Cell livingArea) {
    this.livingArea = livingArea;
  }

  public boolean isAlive() {
    return isAlive;
  }

  protected void setAlive(boolean alive) {
    isAlive = alive;
  }

  public AnimalType getAnimalType() {
    return animalType;
  }

  public void removeDeadAnimal() {
    this.livingArea = null;
  }

  public void age() {
    if (this.getCurrentAge() == maximumAge) {
      isAlive = false;
    } else {
      this.setCurrentAge(this.getCurrentAge() + 1);
    }
  }

  public abstract boolean canBreed();

  public abstract Animal makeNewAnimal();

  public void move() {

  }
}
