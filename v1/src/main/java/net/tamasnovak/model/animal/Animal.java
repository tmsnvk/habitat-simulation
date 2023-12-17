package net.tamasnovak.model.animal;

import net.tamasnovak.logic.routines.breedingRoutine.BreedingRoutine;
import net.tamasnovak.model.matrix.Cell;

public abstract class Animal {
  private static final String DEAD_ANIMAL_ICON = "☠";
  protected final String id;
  protected int currentAge;
  protected final int maximumAge;
  protected boolean isAlive;
  protected Cell livingArea;
  private String animalIcon;
  private final AnimalSpecies animalSpecies;
  private final AnimalType animalType;
  private final BreedingRoutine breedingRoutine;

  public Animal(
    String id,
    Cell livingArea,
    int maximumAge,
    String animalIcon,
    AnimalSpecies animalSpecies,
    AnimalType animalType,
    BreedingRoutine breedingRoutine) {
    this.id = id;
    this.currentAge = 0;
    this.isAlive = true;
    this.livingArea = livingArea;
    this.maximumAge = maximumAge;
    this.animalIcon = animalIcon;
    this.animalSpecies = animalSpecies;
    this.animalType = animalType;
    this.breedingRoutine = breedingRoutine;
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

  public String getAnimalIcon() {
    return animalIcon;
  }

  public AnimalSpecies getAnimalSpecies() {
    return animalSpecies;
  }

  public AnimalSpecies getAnimalType() {
    return animalSpecies;
  }

  protected void die() {
    this.isAlive = false;
    this.animalIcon = DEAD_ANIMAL_ICON;
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

  }

  protected abstract void move();
}
