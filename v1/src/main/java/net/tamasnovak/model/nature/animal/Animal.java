package net.tamasnovak.model.nature.animal;

import net.tamasnovak.logic.routine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.Nature;

public abstract class Animal extends Nature {
  private static final String DEAD_ANIMAL_ICON = "☠";
  protected int currentAge;
  protected final int maximumAge;
  protected boolean isAlive;
  protected final AnimalSpecies animalSpecies;
  protected final AnimalType animalType;
  protected final BreedingRoutine breedingRoutine;

  public Animal(
    String id,
    Cell coordinates,
    int maximumAge,
    String icon,
    AnimalSpecies animalSpecies,
    AnimalType animalType,
    BreedingRoutine breedingRoutine) {
    super(id, coordinates, icon);
    this.currentAge = 0;
    this.isAlive = true;
    this.coordinates = coordinates;
    this.maximumAge = maximumAge;
    this.animalSpecies = animalSpecies;
    this.animalType = animalType;
    this.breedingRoutine = breedingRoutine;
  }

  public Cell getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Cell coordinates) {
    this.coordinates = coordinates;
  }

  public int getCurrentAge() {
    return currentAge;
  }

  private void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public AnimalSpecies getAnimalSpecies() {
    return animalSpecies;
  }

  public AnimalType getAnimalType() {
    return animalType;
  }

  protected void die() {
    this.isAlive = false;
    this.icon = DEAD_ANIMAL_ICON;
  }

  public abstract void doLifeCycleMethods();

  protected void increaseAge() {
    if (this.getCurrentAge() == maximumAge) {
      die();
    } else {
      this.setCurrentAge(this.getCurrentAge() + 1);
    }
  }

  protected abstract boolean isAbleToBreed();

  protected void breed() {
    if (isAbleToBreed()) {
      breedingRoutine.run(this);
    }
  }

  protected abstract void move();
}
