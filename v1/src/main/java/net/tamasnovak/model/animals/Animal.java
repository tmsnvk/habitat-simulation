package net.tamasnovak.model.animals;

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

  public Cell getLivingArea() {
    return livingArea;
  }

  public void setLivingArea(Cell livingArea) {
    this.livingArea = livingArea;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public AnimalType getAnimalType() {
    return animalType;
  }

  public void removeDeadAnimal() {
    this.livingArea = null;
  }

  public void age() {
    if (currentAge == maximumAge) {
      isAlive = false;
    } else {
      currentAge++;
    }
  }

  public void breed() {

  }

  public void move() {

  }
}
