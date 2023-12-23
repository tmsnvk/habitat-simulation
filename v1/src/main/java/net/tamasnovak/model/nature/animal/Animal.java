package net.tamasnovak.model.nature.animal;

import net.tamasnovak.logic.routine.animalRoutine.agingRoutine.AgingRoutine;
import net.tamasnovak.logic.routine.animalRoutine.breedingRoutine.BreedingRoutine;
import net.tamasnovak.model.matrix.Cell;
import net.tamasnovak.model.nature.Nature;

public abstract class Animal extends Nature {
  private static final String DEAD_ANIMAL_ICON = "☠";
  protected int currentAge;
  protected final int maximumAge;
  protected boolean isAlive;
  protected boolean didAlreadyBreedInRunningYear;
  protected final AnimalSpecies species;
  protected final AnimalType type;
  protected final AgingRoutine agingRoutine;
  protected final BreedingRoutine breedingRoutine;

  public Animal(
    String id,
    Cell coordinates,
    int maximumAge,
    String icon,
    AnimalSpecies species,
    AnimalType type,
    AgingRoutine agingRoutine,
    BreedingRoutine breedingRoutine) {
    super(id, coordinates, icon);
    this.currentAge = 0;
    this.isAlive = true;
    this.didAlreadyBreedInRunningYear = false;
    this.coordinates = coordinates;
    this.maximumAge = maximumAge;
    this.species = species;
    this.type = type;
    this.agingRoutine = agingRoutine;
    this.breedingRoutine = breedingRoutine;
  }

  public void setCoordinates(Cell coordinates) {
    this.coordinates = coordinates;
  }

  public int getCurrentAge() {
    return currentAge;
  }

  public void setCurrentAge(int currentAge) {
    this.currentAge = currentAge;
  }

  public int getMaximumAge() {
    return maximumAge;
  }

  public boolean isAlive() {
    return isAlive;
  }

  public boolean didAlreadyBreedInGivenYear() {
    return didAlreadyBreedInRunningYear;
  }

  public void setDidAlreadyBreedInRunningYear(boolean didAlreadyBreedInRunningYear) {
    this.didAlreadyBreedInRunningYear = didAlreadyBreedInRunningYear;
  }

  public AnimalSpecies getSpecies() {
    return species;
  }

  public AnimalType getType() {
    return type;
  }

  public abstract void doLifeCycleMethods();

  public void perish() {
    this.isAlive = false;
    this.icon = DEAD_ANIMAL_ICON;
  }

  public abstract boolean isAbleToBreed();
  protected abstract void move();

  protected void breed() {
    if (isAbleToBreed()) {
      breedingRoutine.run(this);
    }
  }
}
