package net.tamasnovak.model.nature.animal;

import net.tamasnovak.logic.routine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.Nature;

public abstract class Animal extends Nature {
  private static final String DEAD_ANIMAL_ICON = "☠";
  protected int currentAge;
  protected final int maximumAge;
  protected boolean isAlive;
  protected boolean didAlreadyBreedInGivenYear;
  protected final AnimalSpecies species;
  protected final AnimalType type;
  protected final BreedingRoutine breedingRoutine;

  public Animal(
    String id,
    Cell coordinates,
    int maximumAge,
    String icon,
    AnimalSpecies species,
    AnimalType type,
    BreedingRoutine breedingRoutine) {
    super(id, coordinates, icon);
    this.currentAge = 0;
    this.isAlive = true;
    this.didAlreadyBreedInGivenYear = false;
    this.coordinates = coordinates;
    this.maximumAge = maximumAge;
    this.species = species;
    this.type = type;
    this.breedingRoutine = breedingRoutine;
  }

  public void setCoordinates(Cell coordinates) {
    this.coordinates = coordinates;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public boolean didAlreadyBreedInGivenYear() {
    return didAlreadyBreedInGivenYear;
  }

  public void setDidAlreadyBreedInGivenYear(boolean didAlreadyBreedInGivenYear) {
    this.didAlreadyBreedInGivenYear = didAlreadyBreedInGivenYear;
  }

  public AnimalSpecies getSpecies() {
    return species;
  }

  public AnimalType getType() {
    return type;
  }

  protected void die() {
    this.isAlive = false;
    this.icon = DEAD_ANIMAL_ICON;
  }

  public abstract void doLifeCycleMethods();

  protected abstract boolean isAbleToBreed();
  protected abstract void move();

  protected void increaseAge() {
    if (this.currentAge == maximumAge) {
      die();
    } else {
      this.currentAge++;
    }
  }

  protected void breed() {
    if (isAbleToBreed()) {
      breedingRoutine.run(this);
    }
  }
}
