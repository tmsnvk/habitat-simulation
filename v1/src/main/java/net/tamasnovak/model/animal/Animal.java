package net.tamasnovak.model.animal;

import net.tamasnovak.model.matrix.Cell;

public abstract class Animal {
  protected int currentAge;
  protected final int maximumAge;
  protected boolean isAlive;
  protected Cell livingArea;
  protected final AnimalSpecies animalSpecies;

  public Animal(Cell livingArea, int maximumAge, AnimalSpecies animalSpecies) {
    this.currentAge = 0;
    this.isAlive = true;
    this.livingArea = livingArea;
    this.maximumAge = maximumAge;
    this.animalSpecies = animalSpecies;
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
    this.isAlive = alive;
  }

  public AnimalSpecies getAnimalType() {
    return animalSpecies;
  }

  public void removeDeadAnimal() {
    this.livingArea = null;
  }

  public void increaseAge() {
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
